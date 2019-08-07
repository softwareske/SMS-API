<?php

/**
 * Description of SoftwaresKe
 *
 * @author Walton Humphrey
 */
class SoftwaresKe {

    private $baseUrl = "https://account.softwareske.com/smsAPI";
    private $url;
    private $APIKey;
    private $APIToken;
    private $smsFrom;
    private $smsTo;
    private $smsText;
    private $scheduleDate;

    public function getAPIKey() {
        return $this->APIKey;
    }

    public function getAPIToken() {
        return $this->APIToken;
    }

    public function getSmsFrom() {
        return $this->smsFrom;
    }

    public function getSmsTo() {
        return $this->smsTo;
    }

    public function getSmsText() {
        return $this->smsText;
    }

    public function getScheduleDate() {
        return $this->scheduleDate;
    }

    public function setAPIKey($APIKey) {
        $this->APIKey = $APIKey;
    }

    public function setAPIToken($APIToken) {
        $this->APIToken = $APIToken;
    }

    public function setSmsFrom($smsFrom) {
        $this->smsFrom = $smsFrom;
    }

    public function setSmsTo($smsTo) {
        $this->smsTo = $smsTo;
    }

    public function setSmsText($smsText) {
        $this->smsText = $smsText;
    }

    public function setScheduleDate($scheduleDate) {
        $this->scheduleDate = $scheduleDate;
    }

    public function getUrl() {
        return $this->url;
    }

    private function getBaseUrl() {
        return $this->baseUrl;
    }

    public function sendSMS() {
        if (!(($validation = $this->validateInput("sendsms")) == "validated")) {
            return $validation;
        }
        //set url for the sms
        $this->setSendSmsUrl();
        //execute send sms
        $result = $this->executeGetRequest();
        return $result;
    }

    public function checkSMSStatus($group_id) {
        if (empty($group_id)) {
            return "Group id not supplied";
        }
        if (!(($validation = $this->validateInput()) == "validated")) {
            return $validation;
        }
        //set url for checking sms status
        $this->setCheckSmsStatusUrl($group_id);
        //execute check sms stautus
        $result = $this->executeGetRequest();
        return $result;
    }

    public function readSMSInbox() {
        if (!(($validation = $this->validateInput()) == "validated")) {
            return $validation;
        }
        //set url for get unread smses
        $this->setReadSMSInboxUrl();
        //execute read sms inbox
        $result = $this->executeGetRequest();
        return $result;
    }

    public function getCreditBalance() {
        if (!(($validation = $this->validateInput()) == "validated")) {
            return $validation;
        }
        //set url for checking credit balance
        $this->setCreditBalanceUrl();
        //execute read credit balance
        $result = $this->executeGetRequest();
        return $result;
    }

    private function validateInput($validation_type = "general") {
        //validate values for building url
        if (!($validation_type == "general")) {
            if (empty($this->smsFrom)) {
                return "Sender ID missing";
            }
            if (empty($this->smsTo)) {
                return "Message recipients missing";
            }
            if (empty($this->smsText)) {
                return "Sms message missing";
            }
        }
        if (empty($this->APIKey)) {
            return "API Key missing";
        }
        if (empty($this->APIToken)) {
            return "API Token missing";
        }
        return "validated";
    }

    private function setSendSmsUrl() {
        $this->url = sprintf("%s?%s", $this->getBaseUrl(), http_build_query(array(
            "sendsms" => true,
            "apikey" => $this->getAPIKey(),
            "apitoken" => $this->getAPIToken(),
            "type" => "sms",
            "from" => $this->getSmsFrom(),
            "to" => $this->getSmsTo(),
            "text" => $this->getSmsText(),
            "scheduledate" => !empty($this->getScheduleDate()) ? $this->getScheduleDate() : date("Y-m-d H:m:s", time())
        )));
    }

    private function setCheckSmsStatusUrl($group_id) {
        $this->url = sprintf("%s?%s", $this->getBaseUrl(), http_build_query(array(
            "groupstatus" => true,
            "apikey" => $this->getAPIKey(),
            "apitoken" => $this->getAPIToken(),
            "groupid" => $group_id
        )));
    }

    private function setReadSMSInboxUrl() {
        $this->url = sprintf("%s?%s", $this->getBaseUrl(), http_build_query(array(
            "getinbox" => true,
            "apikey" => $this->getAPIKey(),
            "apitoken" => $this->getAPIToken(),
            "list" => "unread"
        )));
    }

    private function setCreditBalanceUrl() {
        $this->url = sprintf("%s?%s", $this->getBaseUrl(), http_build_query(array(
            "balance" => true,
            "apikey" => $this->getAPIKey(),
            "apitoken" => $this->getAPIToken()
        )));
    }

    private function executeGetRequest() {
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, $this->getUrl());
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        $result = curl_exec($curl);
        if (!$result) {
            die("Connection Failure");
        }
        curl_close($curl);
        return $result;
    }

}
