package Main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.resource.instance.Account;
import com.ct.db.DBManager;
import com.ct.db.db.db.activesessions.Activesessions;
import com.ct.db.db.db.phoneextensions.Phoneextensions;
import com.ct.db.db.db.sexters.Sexters;
import com.ct.db.db.db.users.Users;
import com.speedment.manager.Manager;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import spark.Request;
import spark.Response;
import spark.Route;

public class SMSController {

	static String ourUsersNumber = "+15622842958";
	static String ourSextersNumber = "+15622842958";
    public static final String ACCOUNT_SID = "AC0ff7317d059cfccc68dd06ad0d14d8aa";
    public static final String AUTH_TOKEN = "c7a5192a45f3c6c14ecf80ff7dde97c0";
    public static final boolean isTrial = true;
    public static final Double smsCost = 1.0;
    public static final Double mmsCost = 2.0;
    final static Manager<Sexters> sexters = DBManager.Instatnce().managerOf(Sexters.class);
    final static Manager<Users> users = DBManager.Instatnce().managerOf(Users.class);
    final static Manager<Phoneextensions> phoneExtensions = DBManager.Instatnce().managerOf(Phoneextensions.class);
    final static Manager<Activesessions> sessions = DBManager.Instatnce().managerOf(Activesessions.class);
    
	public static void init() {
	}

	public static Route receiveSMS = (Request request, Response response) -> {
		init();
		String from_number = request.queryParams("From");
		String to_number = request.queryParams("To");
		String text = request.queryParams("Body");
		System.out.println("Message received - From: " + from_number + ", To: " + to_number + ", Text: " + text);
		
		if (!didHandleKeywords(from_number, to_number, text)) {
			if (!register(from_number, to_number, text)) {
				System.out.println("User or sexter : " + from_number + " is during the registration process.");
				return "";
			}		
			
			Activesessions session = getSession(from_number, to_number);
			if (session == null)
				if (createSession(from_number)) {
					session = getSession(from_number, to_number);
					handleMsg(session, to_number, text);
				}
				else 
					sendMsg(to_number, from_number, "We are very sorry but we couldn't connect you with the other party at this time, please try again later");
			else 
				handleMsg(session, to_number, text);		
		}
		
		return "";

	};

	private static String chooseSexter() {
		Optional<Sexters> sextersResult = sexters.stream()
				.filter(Sexters.IS_ONLINE.equal(1))
				.findAny();
		if (sextersResult.isPresent())
			return sextersResult.get().getPhoneNumber();
		else 
			System.out.println("No available sexter to assign to the session");
			return "No sexter available";

	}

	private static Boolean didHandleKeywords(String from_number, String to_number, String text) {
		Optional<Sexters> sexter = sexters.stream()
				.filter(Sexters.PHONE_NUMBER.equal(from_number))
				.findAny();
		
		if (!sexter.isPresent()){ 
			//System.out.println("Used a keyword without being a registered sexter");
			return false;
		}
		
		switch(text.toLowerCase()) {
		   case "startsexting" :
			   setSexterStatus(1, from_number, sexter.get().getBalance().get(), sexter.get().getRegistrationPhase().get());
			   return true;
		   
		   case "stopsexting" :
			   setSexterStatus(0, from_number, sexter.get().getBalance().get(), sexter.get().getRegistrationPhase().get());
			   return true;
		      
		   case "endsession" :
			   endSextingSession(from_number, to_number);
			   return true;
			  
		   case "checkcredit" :
			   checkSexterCredit(from_number, to_number);
			   return true;
			   
		   case "helpme" :
			   sendMsg(to_number, from_number,"Available Keywaord : StartSexting, StopSexting, EndSession, CheckCredit, HelpMe. Full details available in - to www.cybertexting.co/keywords");
			   return true;
			   
		   default :
			   return false;
				   
		}
	}

	private static void checkSexterCredit(String from_number, String to_number) {
		Optional<Sexters> sextersResult = sexters.stream()
				.filter(Sexters.PHONE_NUMBER.equal(from_number))
				.findAny();
		if (sextersResult.isPresent())
			sendMsg(to_number,from_number,"Your credit is : " + sextersResult.get().getBalance().get());
		else 
			System.out.println("Can't check credit of a sexter that does not exit");
	}

	private static Double checkUserCredit(String userPhoneNumber) {
		Optional<Users> user = users.stream()
				.filter(Users.PHONE_NUMBER.equal(userPhoneNumber))
				.findAny();
		if (user.isPresent())
			return user.get().getCredit().get();
		else 
			System.out.println("Can't check credit of a user that does not exit");
		
		return 0.0;
	}
	
	private static void endSextingSession(String from_number, String to_number) {
		Optional<Activesessions> result = sessions.stream()
				.filter(Activesessions.SEXTERS_PHONE_NUMBER.equal(from_number))
				.filter(Activesessions.PHONEEXTENSIONS_PHONE_NUMBER.equal(to_number))
				.findAny();
		if (result.isPresent())
			result.get().remove();
		else 
			System.out.println("Can't end session that does not exist");

	}

	private static void setSexterStatus(int sexterStatus, String sexterPhoneNumber, Double balance, String registrationPhase) {
		sexters.newEmptyEntity()
		.setPhoneNumber(sexterPhoneNumber)
		.setIsOnline(sexterStatus)
		.setBalance(balance)
		.setRegistrationPhase(registrationPhase)
		.update();
	}

	private static Boolean createSession(String user_number) {
		String sexter = chooseSexter();
		String extension = chooseNumber();

		if (sexter.contains("No") || extension.contains("No")) 
			System.out.println("Can't create session");
		else { 
			sessions.newEmptyEntity()
				.setPhoneextensionsPhoneNumber(extension)
				.setSextersPhoneNumber(sexter)
				.setUsersPhoneNumber(user_number)
				.persist();
			return true;
		}
		
		return false;
	}

	private static Activesessions getSession(String from_number, String to_number) {
		if (to_number.equals(ourUsersNumber) && (from_number.contains("9648"))){ //Need to remove the && after testing ended
			Optional<Activesessions> result = sessions.stream()
					.filter(Activesessions.USERS_PHONE_NUMBER.equal(from_number))
					.findAny();
			if (!result.isPresent()) 
				return null;
			else
				return result.get();
		}
		
		Optional<Activesessions> result = sessions.stream()
				.filter(Activesessions.SEXTERS_PHONE_NUMBER.equal(from_number))
				.filter(Activesessions.PHONEEXTENSIONS_PHONE_NUMBER.equal(to_number))
				.findAny();
		if (!result.isPresent()) 
			return null;
		else
			return result.get();
		
	}

	private static void handleMsg(Activesessions session, String to_number, String text) {
		if (to_number.equals(ourUsersNumber) && (text.contains("#"))) {
			Double userCredits = checkUserCredit(session.getUsersPhoneNumber());
			if ( userCredits < 11.0)
				sendMsg(ourUsersNumber, session.getUsersPhoneNumber(), "You credits are running low, you have " + userCredits +" make sure you get some more to continue sexting.");
			if (credit(session.getUsersPhoneNumber(),session.getSextersPhoneNumber(),smsCost))
				sendMsg(session.getPhoneextensionsPhoneNumber(), session.getSextersPhoneNumber(), text); //To sexter
			else 
				sendMsg(ourUsersNumber, session.getUsersPhoneNumber(), "Seems like you are out of credit, in order to continue sexting please get some more credits."); 	
		} else 
			sendMsg(ourUsersNumber, session.getUsersPhoneNumber(), text); //To user
	}


	private static Boolean credit(String credit_from, String credit_to, Double amount) {
			Optional<Sexters> sexter = sexters.stream()
					.filter(Sexters.PHONE_NUMBER.equal(credit_to))
					.findAny();
			Optional<Users> user = users.stream()
					.filter(Users.PHONE_NUMBER.equal(credit_from))
					.findAny();
			
			Double userCurrentCredit = user.get().getCredit().get();
			if (userCurrentCredit < amount)
				return false;
			
			users.newEmptyEntity()
				.setPhoneNumber(credit_from)
				.setCredit(userCurrentCredit - amount)
				.setRegistrationPhase("2 - Ready to use platform")
				.update();
			
			Double sexterCurrentBalance = sexter.get().getBalance().get();
			sexters.newEmptyEntity()
				.setPhoneNumber(credit_to)
				.setBalance(sexterCurrentBalance + amount)
				.setRegistrationPhase("2 - Ready to use the platform")
				.update();
			
			return true;
 
	}

	private static String chooseNumber() {
		Optional<Phoneextensions> result = phoneExtensions.stream()
				.filter(Phoneextensions.IS_ACTIVE.equal(0))
				.findAny();
		if (result.isPresent())
			return result.get().getPhoneNumber();
		else 
			System.out.println("No available number to assign to the session");
			return "No available number";

	}

	private static void sendMsg(String from, String to, String text) {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account account = client.getAccount();

        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", to)); 
        params.add(new BasicNameValuePair("From", from)); 
        params.add(new BasicNameValuePair("Body", text));
        try {
			Message sms = messageFactory.create(params);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Boolean register(String from_number, String to_number, String text) {
		final String finalUsersRegistrationStep = "2";
		final String finalSextersRegistrationStep = "2";
		// if to_number is for users
		if (to_number.equals(ourUsersNumber) && (text.contains("#"))) { //Need to remove the # when we get another number
			Optional<Users> userResult = users.stream()
					.filter(Users.PHONE_NUMBER.contains(from_number))
					.findAny();
			if (!userResult.isPresent()) {
				System.out.println("user does not exist, phone number received: " + from_number);
				sendMsg(to_number, from_number, "Welcome to CyberTexting. YOU MUST BE OVER 18 AND AGREE TO THE TERMS (http://www.cybertexting.co/terms) BEFORE CONTINUING. Please reply I AGREE to continue.");
				users.newEmptyEntity()
					.setPhoneNumber(from_number)
					.setCredit(0.0)
					.setRegistrationPhase("1 - Waiting for terms approval")
					.persist();
				return false;
			} else { //Handle the next steps of the registration
				if (userResult.get().getRegistrationPhase().get().contains("1")) {
					if (text.toLowerCase().contains("i agree")) {
						users.newEmptyEntity()
						.setPhoneNumber(from_number)
						.setCredit(100.0)
						.setRegistrationPhase("2 - Ready to use platform")
						.update();
						System.out.println("User accepted the terms of usage");
						if (isTrial) {
							sendMsg(to_number, from_number,"We are currently running a Beta so you get to enjoy 100 free credits! Say Hi to your sexter...");
						} else {
							//TODO - Need to send Paypal link to the user.
						}
					} else 
						sendMsg(to_number, from_number,"YOU MUST BE OVER 18 AND AGREE TO THE TERMS (http://www.cybertexting.co/terms) BEFORE CONTINUING. Please reply I AGREE to continue.");
				}
			}
			
			if (userResult.get().getRegistrationPhase().get().contains(finalUsersRegistrationStep))
				return true;
			
			return false;
			
		} else {
			// if to_number is for sexters	
			Optional<Sexters> sextersResult = sexters.stream()
					.filter(Sexters.PHONE_NUMBER.contains(from_number))
					.findAny();
			if (!sextersResult.isPresent()) {
				System.out.println("sexters does not exist, phone number received: " + from_number);
				sendMsg(to_number, from_number, "Welcome to CyberTexting. In order to be a Sexter YOU MUST BE OVER 18 AND AGREE TO THE TERMS (http://www.cybertexting.co/terms) BEFORE CONTINUING. Please reply I AGREE to continue.");
				sexters.newEmptyEntity()
					.setPhoneNumber(from_number)
					.setRegistrationPhase("1 - Waiting for terms approval")
					.setBalance(0.0)
					.setIsOnline(0)
					.persist();
				return false;
			} else { //Handle the next steps of the registration
				if (sextersResult.get().getRegistrationPhase().get().contains("1")) {
					if (text.toLowerCase().contains("i agree")) {
						sexters.newEmptyEntity()
						.setPhoneNumber(from_number)
						.setBalance(0.0)
						.setRegistrationPhase("2 - Ready to use the platform")
						.update();
						System.out.println("Sexter accepted the terms of usage");
						sendMsg(to_number, from_number,"Thank you for joining our Sexters community, please see check out our web-site for important key words (www.cybertexting.co/keywords)");
					} else { 
						sendMsg(to_number, from_number,"YOU MUST BE OVER 18 AND AGREE TO THE TERMS (http://www.cybertexting.co/terms) BEFORE CONTINUING. Please reply I AGREE to continue.");
					}
				}
			}
			
			if (sextersResult.get().getRegistrationPhase().get().contains(finalSextersRegistrationStep))
				return true;
			
			return false;
		}
	}

}
