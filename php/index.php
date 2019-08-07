<?php
//include ('./SoftwaresKe.php');

require_once( dirname(__FILE__) . '/SoftwaresKe.php');
?>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
        /*         * ********************* sending an sms  ******************** */
//      sample code

        $sms = new SoftwaresKe();
        $sms->setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        $sms->setAPIToken("5ja61548236543");
        $sms->setSmsFrom("SOFTWARESKE");
        $sms->setSmsTo("+254711243807, +254711243808");
        $sms->setSmsText("Sending my first sms text");
        //optional schedule
        $sms->setScheduleDate(date("Y-m-d H:m:s", time()));
        echo "Printing send sms result<br/>";
        $result = $sms->sendSMS();
        echo $result;
        echo "<br/><br/>";

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


        /*         * ********************* end sending an sms  ******************** */




        /*         * ********************** Checking Sms status for an already send or queued sms *********** */

//       sample code
        echo "printing send sms status<br/>";
        $sms_status = new SoftwaresKe();
        $sms_status->setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        $sms_status->setAPIToken("5ja61548236543");
        //using previous result from send sms to get group id
        $group_id = (int) json_decode($result)->group_id;
        $status = $sms_status->checkSMSStatus($group_id);
        echo($status);
        echo "<br/><br/>";

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

        /*         * ********************** End checking Sms status for an already send or queued sms *********** */





        /*         * ********************** Checking credit balance *********** */

        //checking credit balance
        //sample code
        echo "Print credit balance<br/>";
        $credit_balance = new SoftwaresKe();
        $credit_balance->setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        $credit_balance->setAPIToken("5ja61548236543");
        $balance = $credit_balance->getCreditBalance();
        echo($balance);
        echo "<br/><br/>";
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


        /*         * ********************** end Checking credit balance *********** */





        /*         * ********************** Checking unread messages in the inbox *********** */

        //checking unread messages
//      sample code
        echo "printing unread messages<br/>";
        $unread_sms = new SoftwaresKe();
        $unread_sms->setAPIKey("u0fqtxd59dftJOlHF6W2E4waLNah45EV");
        $unread_sms->setAPIToken("5ja61548236543");
        $inbox = $unread_sms->readSMSInbox();
        echo($inbox);

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


        /*         * ********************** end Checking unread messages in the inbox *********** */
        ?>
    </body>
</html>
