package Main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;
//import com.plivo.helper.xml.elements.Message;
import com.plivo.helper.xml.elements.PlivoResponse;

import spark.Request;
import spark.Response;
import spark.Route;




public class SMSController {

	static Set<String> sexters = new HashSet<String>();
	static Set<String> freeNumbers = new HashSet<String>();
	static HashMap<String, String> usersToRandNumberMap = new HashMap<String, String>();
	static HashMap<String, String> randNumberToSexterMap = new HashMap<String, String>();
	static String ourNumber = "+15622842958";
    public static final String ACCOUNT_SID = "AC0ff7317d059cfccc68dd06ad0d14d8aa";
    public static final String AUTH_TOKEN = "c7a5192a45f3c6c14ecf80ff7dde97c0";
	// user to new random number
	// rand number to sexter
	//

	public static void init() {
		if (sexters.isEmpty())
			sexters.add("14085135253");

		if (freeNumbers.isEmpty())
			freeNumbers.add(ourNumber);
	}

	public static Route receiveSMS = (Request request, Response response) -> {
		init();
		String from_number = request.queryParams("From");
		String to_number = request.queryParams("To");
		String text = request.queryParams("Body");
		System.out.println("Message received - From: " + from_number + ", To: " + to_number + ", Text: " + text);
		

		if (isShouldRegister(text)) {
			sexters.add(text);
			sendMsg(ourNumber, from_number, "Welcome to CyberTexting");
		}
		
		return "";
/*
		String from_forward = chooseNumber(from_number);
		System.out.println(from_forward);
		String to_forward = chooseSexter(from_number);
		System.out.println(to_forward);
		sendMsg(from_number, to_forward, text);
		//response.raw().addHeader("Content-Type", "text/xml");
		return "";
		*/
	};

	private static String chooseSexter(String randNumber) {
		String user = getKeyByValue(randNumberToSexterMap, randNumber);
		if (user != null)
			return user;

		Random generator = new Random();
		Object[] values = sexters.toArray();
		Object randomValue = values[generator.nextInt(values.length)];
		randNumberToSexterMap.put(randNumber, (String) randomValue);
		return (String) randomValue;
	}

	private static String chooseNumber(String from_number) {
		String randNumber = usersToRandNumberMap.get(from_number);
		if (randNumber != null)
			return randNumber;

		Random generator = new Random();
		Object[] values = freeNumbers.toArray();
		Object randomValue = values[generator.nextInt(values.length)];
		randNumberToSexterMap.put(randNumber, (String) randomValue);
		return (String) randomValue;
	}

	private static void sendMsg(String from, String to, String text) {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account account = client.getAccount();

        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", to)); 
        params.add(new BasicNameValuePair("From", ourNumber)); 
        params.add(new BasicNameValuePair("Body", text));
        try {
			Message sms = messageFactory.create(params);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Boolean isShouldRegister(String text) {
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
