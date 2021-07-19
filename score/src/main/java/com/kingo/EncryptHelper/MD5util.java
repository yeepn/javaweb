package com.kingo.EncryptHelper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5util {
    public static String getMD5String_lower(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(s.getBytes());
        return new BigInteger(messageDigest.digest()).toString(16);
    }
    public static String getMD5String_Upper(String s) throws NoSuchAlgorithmException {
        return getMD5String_lower(s).toUpperCase();
    }

    }




