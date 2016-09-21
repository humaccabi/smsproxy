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

	public static void init() {
	}

	public static Route receiveSMS = (Request request, Response response) -> {
		init();
		String from_number = request.queryParams("From");
		String to_number = request.queryParams("To");
		String text = request.queryParams("Body");
		System.out.println("Message received - From: " + from_number + ", To: " + to_number + ", Text: " + text);
			
		if (isShouldRegister(from_number, to_number, text)) {
			sendMsg(to_number, from_number, "Welcome to CyberTexting");
			return "";
		}
		
		
		
		return "";

	};

	private static String chooseSexter(String randNumber) {
		return randNumber;

	}

	private static String chooseNumber(String from_number) {
		return from_number;

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

	private static Boolean isShouldRegister(String from_number, String to_number, String text) {
		
		final Manager<Users> users = DBManager.Instatnce().managerOf(Users.class);
		Optional<Users> userResult = users.stream()
			    .filter(Users.PHONE_NUMBER.contains(from_number))
			    .findAny();		
			if (!userResult.isPresent()) {
				System.out.println("user does not exist, phone number received: " + from_number);
				//send welcome and register sms
			}
		if (text.contains("#sexter"))
			return true;
		return false;
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

}
