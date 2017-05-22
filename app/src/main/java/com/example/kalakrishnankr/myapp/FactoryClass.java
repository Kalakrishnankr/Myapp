package com.example.kalakrishnankr.myapp;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

/**
 * Created by kalakrishnan.kr on 17/5/17.
 */
public class FactoryClass {

    public String key;
    private static final String PARTNER_KEY ="X-PartnerKey" ;
    public String url ="http://192.168.1.156:8091/";
    public ResponseMessage executeRequest(String urlString, String nm, String pass) throws JSONException, IOException, GeneralSecurityException {

        ResponseMessage responseMessage = null;
        String REQUEST;
        String json = "";
        HttpURLConnection conn = null;
        int statusCode;
        InputStream in = null;
        OutputStream os;

        try {
            responseMessage = new ResponseMessage();
            URL httpUrl = new URL(url.concat(urlString));
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(60 * 1000 /*milliseconds*/);
            conn.setConnectTimeout(60 * 1000  /*milliseconds */);
            System.setProperty("http.keepAlive", "false");
            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestMethod("GET");

            conn.setRequestProperty(PARTNER_KEY, key);
            conn.setRequestProperty("Authorization", "Basic " + Base64Coder.encodeString(nm + ":" + pass));

            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            statusCode = conn.getResponseCode();
            BufferedReader reader;

            if (statusCode == 200 || statusCode == 201) {

                in = new BufferedInputStream(conn.getInputStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
            } else {
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
                in = new BufferedInputStream(conn.getErrorStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
            }

        } catch (Exception e) {

            if (e.getMessage().contains("authentication challenge")) {
            } else {
                if (conn != null) {
                }
            }

        } finally {
            if (conn != null)
                conn.disconnect();
            if (in != null) {
                in.close();
            }

        }
        return responseMessage;
    }

    public class ResponseMessage {
        public int responseCode;
        public String message;
    }
}


