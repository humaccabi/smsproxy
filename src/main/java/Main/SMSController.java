package Main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.Message;
import com.plivo.helper.xml.elements.PlivoResponse;

import spark.Request;
import spark.Response;
import spark.Route;

public class SMSController {

	static Set<String> sexters = new HashSet<String>();
	static Set<String> freeNumbers = new HashSet<String>();
	static HashMap<String, String> usersToRandNumberMap = new HashMap<String, String>();
	static HashMap<String, String> randNumberToSexterMap = new HashMap<String, String>();
	static String ourNumber = "16575295295";
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
		String text = request.queryParams("Text");
		System.out.println("Message received - From: " + from_number + ", To: " + to_number + ", Text: " + text);

		if (isShouldRegister(text)) {
			sexters.add(text);
			sendMsg(ourNumber, from_number, "Welcome to CyberTexting");
		}

		String from_forward = chooseNumber(from_number);
		System.out.println(from_forward);
		String to_forward = chooseSexter(from_number);
		System.out.println(to_forward);
		sendMsg(from_number, to_forward, text);
		//response.raw().addHeader("Content-Type", "text/xml");
		return "";
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
		String authId = "MAZDQWNJFJMDK3NTY4N2";
		String authToken = "NTI1YTM5YTA1ZjgzMGNlNDQwMGFhNWJlMTliMjZh";
		RestAPI api = new RestAPI(authId, authToken, "v1");
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("src", "16575295295"); // Sender's phone number with //									
		parameters.put("dst", "14085135253"); // Receiver's phone number with //										
		parameters.put("text", "Hi, text from Eli"); // Your SMS text message
		MessageResponse msgResponse;
		try {
			msgResponse = api.sendMessage(parameters);
			System.out.println(msgResponse);
			if (msgResponse.serverCode == 202) {
				System.out.println("Message UUID : " + msgResponse.messageUuids.get(0).toString());
			} else {
				System.out.println(msgResponse.error);
			}
		} catch (PlivoException e) {
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
