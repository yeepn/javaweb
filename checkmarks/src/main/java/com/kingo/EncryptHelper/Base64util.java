package com.kingo.EncryptHelper;

import java.util.Base64;


public class Base64util {
    public static String encode(String src) {
        return Base64.getUrlEncoder().encodeToString(src.getBytes());
    }

    public static byte[] decode(String encodeData) {
        return Base64.getUrlDecoder().decode(encodeData);
    }
    public static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

}


