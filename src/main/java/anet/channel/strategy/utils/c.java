package anet.channel.strategy.utils;

import android.text.TextUtils;
import anet.channel.util.ALog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/* compiled from: Taobao */
public class c {
    public static String c(String str) {
        return str == null ? "" : str;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char[] charArray = str.toCharArray();
        if (charArray.length < 7 || charArray.length > 15) {
            return false;
        }
        int i = 0;
        for (char c2 : charArray) {
            if (c2 >= '0' && c2 <= '9') {
                i = ((i * 10) + c2) - 48;
                if (i > 255) {
                    return false;
                }
            } else if (c2 != '.') {
                return false;
            } else {
                i = 0;
            }
        }
        return true;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char[] charArray = str.toCharArray();
        if (charArray.length <= 0 || charArray.length > 255) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < charArray.length; i++) {
            if ((charArray[i] >= 'A' && charArray[i] <= 'Z') || ((charArray[i] >= 'a' && charArray[i] <= 'z') || charArray[i] == '*')) {
                z = true;
            } else if (!((charArray[i] >= '0' && charArray[i] <= '9') || charArray[i] == '.' || charArray[i] == '-')) {
                return false;
            }
        }
        return z;
    }

    public static String a(long j) {
        StringBuilder sb = new StringBuilder(16);
        long j2 = 1000000000;
        do {
            sb.append(j / j2);
            sb.append('.');
            j %= j2;
            j2 /= 1000;
        } while (j2 > 0);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static String a(Map<String, String> map, String str) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(64);
        try {
            for (Map.Entry next : map.entrySet()) {
                if (next.getKey() != null) {
                    sb.append(URLEncoder.encode((String) next.getKey(), str));
                    sb.append("=");
                    sb.append(URLEncoder.encode(c((String) next.getValue()), str).replace("+", "%20"));
                    sb.append("&");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
        } catch (UnsupportedEncodingException e) {
            ALog.e("Request", "format params failed", (String) null, e, new Object[0]);
        }
        return sb.toString();
    }
}
