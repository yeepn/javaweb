package com.kingo.EncryptHelper;
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class RSAutl {
    private static final String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCas4d50ICb7CndbHiSZbxnSHKwLFPSl" +
            "EYDLP6JCAI21LumZ9aQslzTYEdbUoE2PfxfEROrYJ6tgZn8wHrCwXRT1RjS84VeV3Cu8u78Kr1ZgpJj9USj-CF4jiL7RztjkleWQr4b0H" +
            "GP54DSgDoqp7R9j0r-IFlEyb-FarSwqk7eAwIDAQAB";

    private static final String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIR5Q78yidl14R1O" +
            "u-EKjs0_tQg4-0APwKDDe2NJ04OMRSnYUoTJI3rv-8cZ548kvwioh6IcWp6yzTd9QQK3lpJdpko2ouVnrInUIlZAJ4l9FCq36N" +
            "AGb2Rzh6zoP5s5lUugPj_ZbRuPpoyRZLHy3SZHoIjosCvvjD7BkJm7snTHAgMBAAECgYBPKhhuHcl7BpKsbOyhoymLRlLswwCCW-" +
            "eFKsyFnQylRCHgy8EkUP6-7MLNTJGwXQk8J1pGaiNNSxSP4G4FLajwmqAp7-PUAt8aiSh578n-hSyBM9lMB77LC_-AFoTLNnbkqziu" +
            "5QWyrwsOe6ekoSTf2Wko1k_j2xicCrmUmcBZQQJBALpyoXq9oRdX8in5XsrZqHOPgRevQH937rUieB0FvEpKiV3ySbl-NxrNWd1BnH" +
            "vNWhul7bgmd5rxqOc4H7nGuukCQQC15Dj1Hl8R82WXrlHK4cz2TpOA7_WLPHflSNznA5RX_q2kSOojdYW_xdb20qEbsjZ-mFv4jbeANo3" +
            "8t7TXZoQvAkEAtHkzH4kgvmTNrp2IiRfou3tD_PYRm5EuybyEwasEmHDPyNU3Ucr_cf0mKEpTO28J8stJcMAjdCLJWI71_rCDyQJAcxOT8Y" +
            "ioj1vVX5SbDOe02_Q0oDOwvsmf9UEW-VUrakynoTO8Zni5CO5rJTd3VGV40rkkHunSOdzKEiRL1qd2YwJAAy5Nl_4J2UlLa-jR6pWoasf" +
            "0WNIsLbMrvCwtoO28KcSJW09UyyENL8-I7qSLhv0qV1A8lHlr_1U6308ER0wRlA";

    private static RSAPublicKey getPublicKey() throws Exception {
        X509EncodedKeySpec keyspec = new X509EncodedKeySpec(Base64util.decode(public_key));
        PublicKey key = KeyFactory.getInstance("RSA").generatePublic(keyspec);
        return (RSAPublicKey) key;

    }

    private static RSAPrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64util.decode(private_key));
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
        return (RSAPrivateKey) privateKey;

    }

    public static String RSAEncrypt(String s) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, getPublicKey());
        byte[] bt_result = cipher.doFinal(s.getBytes());
        return Base64util.encode(bt_result);
    }

    public static String RSAEncryptWithMD5(String s) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        RSAPrivateKey privateKey = getPrivateKey();
        cipher.init(1, privateKey);
        return Base64util.encode(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, s.getBytes("UTF-8"), privateKey.getModulus().bitLength()));
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }




}