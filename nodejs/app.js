var SoftwaresKe = require("./SoftwaresKe");


        
/*********************** sending an sms  *********************/ 
        
//      sample code
var softwaresKe_send_sms = new SoftwaresKe();
softwaresKe_send_sms.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
softwaresKe_send_sms.setAPIToken("5ja61548236543");
softwaresKe_send_sms.setSmsFrom("SOFTWARESKE");
softwaresKe_send_sms.setSmsTo("+254711243807, +254711243808");
softwaresKe_send_sms.setSmsText("Sending my first sms text");
//optional schedule
softwaresKe_send_sms.setScheduleDate("2019-01-27 15:03:37");
//call method to send sms, with a callback function
softwaresKe_send_sms.sendSMS(function(cb) {
  console.log("Printing sms result:");
  console.log(cb);
});
        
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
var softwaresKe_check_sms_status = new SoftwaresKe();
softwaresKe_check_sms_status.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
softwaresKe_check_sms_status.setAPIToken("5ja61548236543");
//call method to check send sms status, with a group_id(in this case we use 155 as our group_id) and a callback function
softwaresKe_check_sms_status.checkSMSStatus(169, function(cb) {
  console.log("printing send sms status:");
  console.log(cb);
});

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
var softwaresKe_balance = new SoftwaresKe();
softwaresKe_balance.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
softwaresKe_balance.setAPIToken("5ja61548236543");
//call method to send sms, with a callback function
softwaresKe_balance.getCreditBalance(function(cb) {
  console.log("Printing credit balance:");
  console.log(cb);
});

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
var softwaresKe_uread_sms = new SoftwaresKe();
softwaresKe_uread_sms.setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
softwaresKe_uread_sms.setAPIToken("5ja61548236543");
//call method to request unread sms, with a callback function
softwaresKe_uread_sms.readSMSInbox(function(cb) {
  console.log("Printing unread messages result:");
  console.log(cb);
});

// Sample Success Response (JSON)

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
