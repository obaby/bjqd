package cn.xports.baselib.util;

import java.security.MessageDigest;

public class MD5Utils {
    public static String getMD5(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes("utf-8");
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            char[] cArr2 = new char[(r1 * 2)];
            int i = 0;
            for (byte b2 : instance.digest()) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b2 >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b2 & 15];
            }
            String str2 = new String(cArr2);
            str2.substring(8, 24);
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
