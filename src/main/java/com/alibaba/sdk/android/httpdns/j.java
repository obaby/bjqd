package com.alibaba.sdk.android.httpdns;

import cn.xports.qd2.entity.K;
import java.security.MessageDigest;
import java.util.regex.Pattern;

class j {

    /* renamed from: a  reason: collision with root package name */
    private static Pattern f475a = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

    static String a(String str) {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes());
        byte[] digest = instance.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b2 : digest) {
            String hexString = Integer.toHexString(b2 & 255);
            while (hexString.length() < 2) {
                hexString = K.k0 + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    static boolean b(String str) {
        if (str != null) {
            char[] charArray = str.toCharArray();
            if (charArray.length > 0 && charArray.length <= 255) {
                for (char c2 : charArray) {
                    if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && ((c2 < '0' || c2 > '9') && c2 != '.' && c2 != '-'))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static boolean c(String str) {
        return str != null && str.length() >= 7 && str.length() <= 15 && !str.equals("") && f475a.matcher(str).matches();
    }
}
