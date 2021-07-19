package com.kingo.Task;

import com.kingo.EncryptHelper.FakeNDKEncrypt;

import netscape.javascript.JSObject;

import java.io.*;
import java.lang.annotation.Retention;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Requests {
    private  HttpURLConnection connection;
    private  URL url;

    public  String post(String urlstr, HashMap body) {
        String ret = "";
        try {
            url = new URL(urlstr);
            connection =(HttpURLConnection) url.openConnection();
            connectionInit();
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(hashMaptoRequestBody(body).getBytes());
            outputStream.flush();
            outputStream.close();
            if (connection.getResponseCode()==200){
                     ret = getResponseText();

            }
            else return "非200相应状态";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.trim();

    }
    private  void connectionInit(){
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        connection.setConnectTimeout(6000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
    }
    private  String hashMaptoRequestBody(HashMap hashMap){
        StringBuilder stringBuilder = new StringBuilder();
        hashMap.forEach((key, value) -> {
            stringBuilder.append(key + "=" + value + "&");
        });
        String date = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        return date;
    }
    private  String getResponseText() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String tmp = null;
            while ((tmp = br.readLine()) != null) {
                stringBuilder.append(tmp + "\r\n");
            }
                br.close();
        return stringBuilder.toString();

        }

}

