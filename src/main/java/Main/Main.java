package Main;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
    	port(8080);   	

        post("/receive_sms",           SMSController.receiveSMS);
//        
//        String authId  = "MAZDQWNJFJMDK3NTY4N2";
//        String authToken  = "NTI1YTM5YTA1ZjgzMGNlNDQwMGFhNWJlMTliMjZh";
//        //RestAPI api = new RestAPI(authId, authToken, "v1");
//        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
//        parameters.put("src", "16575295295"); // Sender's phone number with country code
//        parameters.put("dst", "972547963359"); // Receiver's phone number with country code
//        parameters.put("text", "Hi, text from Eli"); // Your SMS text message
//        parameters.put("method", "GET"); // The method used to call the url

        try {
//            // Send the message
//            MessageResponse msgResponse = api.sendMessage(parameters);
//            // Print the response
//            System.out.println(msgResponse);
//
//            if (msgResponse.serverCode == 202) {
//                System.out.println("Message UUID : " + msgResponse.messageUuids.get(0).toString());
//            } else {
//                System.out.println(msgResponse.error);
//            }
            
//            MessageFactory msg = api.getMessages();
//            // Print the complete response
//            System.out.println(msg);
//            LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
//            params.put("record_id", msg.messageList.get(2).messageUUID);
//            api = new RestAPI(authId, authToken, "v1");
//            Message message = api.getMessage(params);
//            System.out.println(message);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
    }
}
