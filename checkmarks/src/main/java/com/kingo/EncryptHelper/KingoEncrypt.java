package com.kingo.EncryptHelper;


public class KingoEncrypt {
    private static final String chartable = "0123456789abcdefghijklmnopqrstuvwxyz";
    public static String kingoEncrypt( String str2,String str){
        int length = str2.length();
        int length2 = str.length();
        double d = (double) length;
        int ceil = (int) Math.ceil((1.0d * d) / ((double) length2));
        int ceil2 = (((int) Math.ceil((((d * 3.0d) * 6.0d) / 9.0d) / 6.0d)) * 6) % length2;
        int i = 0;
        String str4 = "";
        for (int i2 = 0; i2 < ceil; i2++) {
            for (int i3 = 1; i3 <= length2; i3++) {
                int i4 = (i2 * length2) + i3;
                String substring = str2.substring(i4 - 1, i4);
                String substring2 = str.substring(i3 - 1, i3);
                String str5 = "000" + ((substring.toCharArray()[0] + substring2.toCharArray()[0]+ ceil2)+"").toString();
//                String str5 = "000" + String.valueOf(Integer.valueOf(substring.toCharArray()[0]).intValue()+ Integer.valueOf(substring2.toCharArray()[0]).intValue()+ ceil2);
                str4 = str4 + str5.substring(str5.length() - 3, str5.length());
                if (i4 == length) {
                    break;
                }
            }
        }

        String str3 = "";
        while (i < str4.length()) {
            int i5 = i + 9;
            String substring3 = str4.substring(i, i5 >= str4.length() ? str4.length() : i5);
            String str6 = "000000" + DecTo36(Long.valueOf(substring3).longValue());
            str3 = str3 + str6.substring(str6.length() - 6, str6.length());

            i = i5;
        }
        return str3;



            }
    private static String DecTo36(long j) {
        if (j < 0) {
            return "-" + DecTo36(Math.abs(j));
        }
        String str = "";
        do {
            String ch = chartable.toCharArray()[Integer.valueOf((int)(j % 36))]+"";
            if ("".equals(str)) {
                str = ch;
            } else {
                str = ch + str;
            }
            j /= 36;
        } while (j > 0);
        return str;

    }


}






