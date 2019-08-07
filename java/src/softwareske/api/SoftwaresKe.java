/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareske.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 *
 * @author Walton Humphrey
 */
public class SoftwaresKe {

    private final String baseUrl = "https://account.softwareske.com/smsAPI";
    private URL url;
    private String APIKey;
    private String APIToken;
    private String smsFrom;
    private String smsTo;
    private String smsText;
    private Date scheduleDate;

    /**
     * @return String return the baseUrl
     */
    private String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @return String return the url
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * @return String return the APIKey
     */
    public String getAPIKey() {
        return APIKey;
    }

    /**
     * @param APIKey the APIKey to set
     */
    public void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
    }

    /**
     * @return String return the APIToken
     */
    public String getAPIToken() {
        return APIToken;
    }

    /**
     * @param APIToken the APIToken to set
     */
    public void setAPIToken(String APIToken) {
        this.APIToken = APIToken;
    }

    /**
     * @return String return the smsFrom
     */
    public String getSmsFrom() {
        return smsFrom;
    }

    /**
     * @param smsFrom the smsFrom to set
     */
    public void setSmsFrom(String smsFrom) {
        this.smsFrom = smsFrom;
    }

    /**
     * @return String return the smsTo
     */
    public String getSmsTo() {
        return smsTo;
    }

    /**
     * @param smsTo the smsTo to set
     */
    public void setSmsTo(String smsTo) {
        this.smsTo = smsTo;
    }

    /**
     * @return String return the smsText
     */
    public String getSmsText() {
        return smsText;
    }

    /**
     * @param smsText the smsText to set
     */
    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    /**
     * @return String return the scheduleDate
     */
    public Date getScheduleDate() {
        return scheduleDate;
    }

    /**
     * @param scheduleDate the scheduleDate to set
     */
    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String sendSMS() throws IOException {
        String validation;
        if (!("validated".equals(validation = this.validateInput("sendsms")))) {
            return validation;
        }
        //set url for the sms
        setSendSmsUrl();
        //execute send sms
        String result = executeGetRequest(getUrl());
        return result;
    }

    public String checkSMSStatus(int group_id) throws IOException {
        String validation;
        if (!("validated".equals(validation = this.validateInput("general")))) {
            return validation;
        }
        //set url for checking sms status
        setCheckSmsStatusUrl(group_id);
        //execute check sms stautus
        String result = executeGetRequest(getUrl());
        return result;
    }

    public String readSMSInbox() throws IOException {
        String validation;
        if (!("validated".equals(validation = this.validateInput("general")))) {
            return validation;
        }
        //set url for get unread smses
        setReadSMSInboxUrl();
        //execute read sms inbox
        String result = executeGetRequest(getUrl());
        return result;
    }

    public String getCreditBalance() throws IOException {
        String validation;
        if (!("validated".equals(validation = this.validateInput("general")))) {
            return validation;
        }
        //set url for checking credit balance
        setCreditBalanceUrl();
        //execute read credit balance
        String result = executeGetRequest(getUrl());
        return result;
    }

    private String validateInput(String validation_type) {
        //validate values for building url
        if (!("general".equals(validation_type))) {
            if (getAPIKey()==null || getSmsFrom().isEmpty()) {
                return "Sender ID missing";
            }
            if (getAPIKey()==null || getSmsTo().isEmpty()) {
                return "Message recipients missing";
            }
            if (getAPIKey()==null || getSmsText().isEmpty()) {
                return "Sms message missing";
            }
        }
        if (getAPIKey()==null || getAPIKey().isEmpty()) {
            return "API Key missing";
        }
        if (getAPIKey()==null || getAPIToken().isEmpty()) {
            return "API Token missing";
        }
        return "validated";
    }

    private void setSendSmsUrl() throws MalformedURLException, UnsupportedEncodingException {
        String urlString = this.getBaseUrl();
        urlString += "?";
        urlString += "sendsms=" + true;
        urlString += "&apikey=" + this.getAPIKey();
        urlString += "&apitoken=" + this.getAPIToken();
        urlString += "&type=sms";
        urlString += "&from=" + URLEncoder.encode(this.getSmsFrom(), "UTF-8");
        urlString += "&to=" + URLEncoder.encode(this.getSmsTo(), "UTF-8");
        urlString += "&text=" + URLEncoder.encode(this.getSmsText(), "UTF-8");
        if (!(getScheduleDate() == null)) {
            urlString += "&scheduledate=" + URLEncoder.encode(getScheduleDate().toString(), "UTF-8");
        }
        setUrl(new URL(urlString));
    }

    private void setCheckSmsStatusUrl(int group_id) throws MalformedURLException {
        String urlString = this.getBaseUrl();
        urlString += "?";
        urlString += "groupstatus=" + true;
        urlString += "&apikey=" + this.getAPIKey();
        urlString += "&apitoken=" + this.getAPIToken();
        urlString += "&groupid=" + group_id;
        setUrl(new URL(urlString));
    }

    private void setReadSMSInboxUrl() throws MalformedURLException {
        String urlString = this.getBaseUrl();
        urlString += "?";
        urlString += "getinbox=" + true;
        urlString += "&apikey=" + this.getAPIKey();
        urlString += "&apitoken=" + this.getAPIToken();
        urlString += "&list=unread";
        setUrl(new URL(urlString));
    }

    private void setCreditBalanceUrl() throws MalformedURLException {
        String urlString = this.getBaseUrl();
        urlString += "?";
        urlString += "balance=" + true;
        urlString += "&apikey=" + this.getAPIKey();
        urlString += "&apitoken=" + this.getAPIToken();
        setUrl(new URL(urlString));
    }

    public static String executeGetRequest(URL url) throws IOException {
        String readLine;
        HttpURLConnection conection = (HttpURLConnection) url.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()))) {
                response = new StringBuilder();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
            }
            return response.toString();
        } else {
            return "request failed with code: " + responseCode;
        }

    }
}
