package com.kingo.EncryptHelper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;

public class FakeNDKEncrypt {
    private String input;
    private String encrptSecretKey;
    private String time;
    private String param;
    private String param2;
    private String echo;
    public enum MODE {GETTOKE, OPERATE};
    private  MODE mode;

    public FakeNDKEncrypt(String input, MODE mode) {

        this.input = input;
        this.time = Long.toString(new Date().getTime());

        this.param2 = generate_param2();
        if (mode == MODE.GETTOKE) {
            this.mode = MODE.GETTOKE;
            this.param = generate_param("yt6n78");
            this.encrptSecretKey = generate_encrptSecretKey("yt6n78");
        } else {
            this.mode = MODE.OPERATE;
            this.param = generate_param("26");
            this.encrptSecretKey = generate_encrptSecretKey("26");

        }
        this.echo = generate_echo();
    }

    private String generate_param(String key) {
        return KingoEncrypt.kingoEncrypt(input,key);
    }

    private String generate_param2() {

        try {
            String pre_param2 = MD5util.getMD5String_lower(input);
            pre_param2 = removeChar(pre_param2, 2);
            pre_param2 = removeChar(pre_param2, 8);
            pre_param2 = removeChar(pre_param2, 14);
            pre_param2 = removeChar(pre_param2, 21);
            return MD5util.getMD5String_lower(pre_param2);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";

    }

    private String generate_encrptSecretKey(String s) {
        try {
            s = RSAutl.RSAEncrypt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private String generate_xqerSign() {
        String date = "param=" + param + "&param2=" + param2 + "&timestamp=" + time + "&echo=" + echo + encrptSecretKey;
        try {

            return RSAutl.RSAEncryptWithMD5(MD5util.getMD5String_Upper(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private String generate_echo() {

        try {
            return MD5util.getMD5String_Upper(time);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String removeChar(String str, int index) {

        return str.substring(0, index) + str.substring(index + 1);
    }

    public HashMap doFinal_GetToken() throws Exception {
        HashMap map = new HashMap();
       if (mode ==MODE.GETTOKE){
           map.put("encrptSecretKey", this.encrptSecretKey);
           map.put("param", this.param);
           map.put("xqerSign", generate_xqerSign());
           map.put("appinfo", "android2.6.303");
           map.put("echo", echo);
           map.put("param2", param2);
           map.put("timestamp", time);
           map.put("token", "00000");
           return map;
       }
       else {

           throw new Exception();
       }
    }

    public HashMap doFinal_Operate(String token) throws Exception {
        if (mode ==MODE.OPERATE ){
            HashMap map = new HashMap();
            map.put("encrptSecretKey", this.encrptSecretKey);
            map.put("param", this.param);
            map.put("xqerSign", generate_xqerSign());
            map.put("appinfo", "android2.6.303");
            map.put("echo", echo);
            map.put("param2", param2);
            map.put("timestamp", time);
            map.put("token", token);
            return map;
        }
        else {
            throw new Exception();
        }

    }
}

