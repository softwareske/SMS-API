"use strict";
const querystring = require("querystring");
const https = require("https");

class SoftwaresKe {
  constructor() {
    this.baseUrl = "https://account.softwareske.com/smsAPI";
  }
  setAPIKey(APIKey) {
    this.APIKey = APIKey;
  }

  setAPIToken(APIToken) {
    this.APIToken = APIToken;
  }

  setSmsFrom(smsFrom) {
    this.smsFrom = smsFrom;
  }

  setSmsTo(smsTo) {
    this.smsTo = smsTo;
  }

  setSmsText(smsText) {
    this.smsText = smsText;
  }

  setScheduleDate(scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  sendSMS(cb) {
    //validate values for building url
    if (this.smsFrom == undefined || this.smsFrom == null) {
      cb("Sender ID missing");
      return;
    }
    if (this.smsTo == undefined || this.smsTo == null) {
      cb("Message recipients missing");
      return;
    }
    if (this.smsText == undefined || this.smsText == null) {
      cb("Sms message missing");
      return;
    }
    if (this.APIKey == undefined || this.APIKey == null) {
      cb("API Key missing");
      return;
    }
    if (this.APIToken == undefined || this.APIToken == null) {
      cb("API Token missing");
      return;
    }

    //seturl for the sms
    this.setSendSmsUrl();
    //execute send sms
    this.executeGetRequest(function(callback) {
      cb(callback);
    });
  }

  checkSMSStatus(group_id, cb) {
    //validate values set to build url
    if (this.APIKey == undefined || this.APIKey == null) {
      cb("API Key missing");
      return;
    }
    if (this.APIToken == undefined || this.APIToken == null) {
      cb("API Token missing");
      return;
    }
    //seturl for checking sms status
    this.setCheckSmsStatusUrl(group_id);
    // //execute check sms stautus
    this.executeGetRequest(function(callback) {
      cb(callback);
    });
  }

  readSMSInbox(cb) {
    //validate values set to build url

    if (this.APIKey == undefined || this.APIKey == null) {
      cb("API Key missing");
      return;
    }
    if (this.APIToken == undefined || this.APIToken == null) {
      cb("API Token missing");
      return;
    }
    //seturl for getunread smses
    this.setReadSMSInboxUrl();
    // //execute read sms inbox
    this.executeGetRequest(function(callback) {
      cb(callback);
    });
  }

  getCreditBalance(cb) {
    //validate values set to build url
    if (this.APIKey == undefined || this.APIKey == null) {
      cb("API Key missing");
      return;
    }
    if (this.APIToken == undefined || this.APIToken == null) {
      cb("API Token missing");
      return;
    }
    //seturl for checking credit balance
    this.setCreditBalanceUrl();
    // //execute read credit balance
    this.executeGetRequest(function(callback) {
      cb(callback);
    });
  }

  setSendSmsUrl() {
    this.url =
      this.baseUrl +
      "?" +
      querystring.stringify({
        sendsms: true,
        apikey: this.APIKey,
        apitoken: this.APIToken,
        type: "sms",
        from: this.smsFrom,
        to: this.smsTo,
        text: this.smsText,
        scheduledate: !(this.scheduleDate == undefined) ? this.scheduledate : ""
      });
  }

  setCheckSmsStatusUrl(group_id) {
    this.url =
      this.baseUrl +
      "?" +
      querystring.stringify({
        groupstatus: true,
        apikey: this.APIKey,
        apitoken: this.APIToken,
        groupid: group_id
      });
  }

  setReadSMSInboxUrl() {
    this.url =
      this.baseUrl +
      "?" +
      querystring.stringify({
        getinbox: true,
        apikey: this.APIKey,
        apitoken: this.APIToken,
        list: "unread"
      });
  }

  setCreditBalanceUrl() {
    this.url =
      this.baseUrl +
      "?" +
      querystring.stringify({
        balance: true,
        apikey: this.APIKey,
        apitoken: this.APIToken
      });
  }

  executeGetRequest(callback) {
    https
      .get(this.url, resp => {
        let data = "";
        // A chunk of data has been recieved.
        resp.on("data", chunk => {
          data += chunk;
        });
        resp.on("end", () => {
          callback(data);
        });
      })
      .on("error", err => {
        callback({ Error: err.message });
      });
  }
}
module.exports = SoftwaresKe;
