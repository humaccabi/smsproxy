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
			
		if (register(from_number, to_number, text)) {
			sendMsg(to_number, from_number, "Welcome to CyberTexting");
			return "";
		}		
		
		Activesessions session = getSession(from_number, to_number);
		if (session == null)
			createSession(from_number);
		
		handleMsg(session, to_number, text);
		
		return "";

	};

	private static String chooseSexter() {
		Optional<Sexters> sextersResult = sexters.stream()
				.filter(Sexters.IS_ONLINE.equal(1))
				.findAny();
		return sextersResult.get().getPhoneNumber();

	}

	private static void createSession(String user_number) {
		String sexter = chooseSexter();
		String extension = chooseNumber();
		
		sessions.newEmptyEntity()
			.setPhoneExtensionsPhoneNumber(extension)
			.setSextersPhoneNumber(sexter)
			.setUsersPhoneNumber(user_number)
			.persist();

	}

	private static Activesessions getSession(String from_number, String to_number) {
		if (to_number.equals(ourUsersNumber)){
			Optional<Activesessions> result = sessions.stream()
					.filter(Activesessions.USERS_PHONE_NUMBER.equal(from_number))
					.findAny();
			return result.orElseGet(null);
		}
		
		Optional<Activesessions> result = sessions.stream()
				.filter(Activesessions.SEXTERS_PHONE_NUMBER.equal(from_number))
				.filter(Activesessions.PHONE_EXTENSIONS_PHONE_NUMBER.equal(to_number))
				.findAny();
		return result.orElseGet(null);
		
	}

	private static void handleMsg( Activesessions session, String to_number, String text) {
		if (to_number.equals(ourUsersNumber)){
			sendMsg(session.getPhoneExtensionsPhoneNumber(), session.getSextersPhoneNumber(), text);
		} else 
			sendMsg(ourUsersNumber, session.getUsersPhoneNumber(), text);
	}


	private static String chooseNumber() {
		Optional<Phoneextensions> result = phoneExtensions.stream()
				.filter(Phoneextensions.IS_ACTIVE.equal(0))
				.findAny();
		return result.get().getPhoneNumber();

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
		// if to_number is for users
		
		Optional<Users> userResult = users.stream()
				.filter(Users.PHONE_NUMBER.contains(from_number))
				.findAny();
		if (!userResult.isPresent()) {
			System.out.println("user does not exist, phone number received: " + from_number);
			// send welcome and register sms
			users.newEmptyEntity().setPhoneNumber(from_number).persist();
		}

		// if to_number is for sexters	
		Optional<Sexters> sextersResult = sexters.stream()
				.filter(Sexters.PHONE_NUMBER.contains(from_number))
				.findAny();
		if (!sextersResult.isPresent()) {
			System.out.println("sexters does not exist, phone number received: " + from_number);
			// send welcome sms 
			sexters.newEmptyEntity().setPhoneNumber(from_number).persist();
		}
		return false;
	}

}
