/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareske.api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

/**
 *
 * @author Walton Humphrey
 */
public class SoftwaresKEAPI {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        
/*********************** sending an sms  *********************/ 
        
//      sample code
        SoftwaresKe send_sms = new SoftwaresKe();
        send_sms.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        send_sms.setAPIToken("5ja61548236543");
        send_sms.setSmsFrom("SOFTWARESKE");
        send_sms.setSmsTo("+254711243807, +254711243808");
        send_sms.setSmsText("Sending my first sms text");
        //optional schedule
        send_sms.setScheduleDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-01-27 15:03:37"));
        //execute send sms
        String result = send_sms.sendSMS();
        
//        Sample Success Response (JSON)
//        {
//            "request": "sendsms",
//            "status": "queued",
//            "group_id": "1234",
//            "date": "2019-01-29 11:29:39"
//        }   


//        Sample Error Response (JSON)
//        {
//            "request": "sendsms",
//            "status": "error",
//            "message": "Sender ID not allowed"
//        }   

        
/*********************** end sending an sms  *********************/ 
        
                        


/************************ Checking Sms status for an already send or queued sms ************/        
              
//       sample code
        SoftwaresKe sms_status = new SoftwaresKe();
        sms_status.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        sms_status.setAPIToken("5ja61548236543");
        //we use a group_id from our previous result when we send an sms
        JSONObject jSONObject = new JSONObject(result);
        int group_id = Integer.parseInt(jSONObject.getString("group_id"));
        String status = sms_status.checkSMSStatus(group_id);
        System.out.println(status);
//      Sample Success Response (JSON)
//        {
//            "request": "groupstatus",
//            "status": "success",
//            "group_id": "1234",
//            "group_status": "sent",
//            "recipients": [
//                {
//                    "id": "21",
//                    "to": "231235433",
//                    "status": "failed"
//                },
//                {
//                    "id": "22",
//                    "to": "4471235433",
//                    "status": "sent"
//                },
//                {
//                    "id": "25",
//                    "to": "1871235433",
//                    "status": "queued"
//                }
//            ],
//            "date": "2019-01-29 11:29:39"
//        }   
                        

//        Sample Error Response (JSON)
//        {
//            "request": "groupstatus",
//            "status": "error",
//            "message": "Error message",
//        }   
                  
        
/************************ end Checking Sms status for an already send or queued sms ************/  
        
        
        
        

/************************ Checking credit balance ************/  

        //checking credit balance
        //sample code
        SoftwaresKe credit_balance = new SoftwaresKe();
        credit_balance.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        credit_balance.setAPIToken("5ja61548236543");
        String balance = credit_balance.getCreditBalance();
        System.out.println(balance);
        
//      Sample Success Response (JSON)
//        {
//            "balance": "12345"
//        }   
                
//        Sample Error Response (JSON)
//        {
//            "request": "balance",
//            "status": "error",
//            "message": "Invalid user",
//        }           
        
/************************ end Checking credit balance ************/  





/************************ Checking unread messages in the inbox ************/

        //checking unread messages    
//      sample code
        SoftwaresKe uread_sms = new SoftwaresKe();
        uread_sms.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        uread_sms.setAPIToken("5ja61548236543");
        String inbox = uread_sms.readSMSInbox();
        System.out.println(inbox);
        
//        Sample Success Response (JSON)

//        {
//            "request": "getinbox",
//            "status": "success",
//            "inbox": [
//                {
//                    "id": "12",
//                    "from": "123456789",
//                    "to": "2347015272",
//                    "message": "Hi. This is message 1",
//                    "date": "2019-01-29 11:29:39"
//                },
//                {
//                    "id": "26",
//                    "from": "447882892",
//                    "to": "2347015272",
//                    "message": "Hi again. This is message 2",
//                    "date": "2019-01-29 11:29:39"
//                }
//           ]
//        }   
                        

//        Sample Error Response (JSON)

//        {
//            "request": "getinbox",
//            "status": "error",
//            "message": "Error",
//        }   
        
/************************ end Checking unread messages in the inbox ************/  

    }

}
