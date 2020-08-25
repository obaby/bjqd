package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.h;

public final class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&");
        if (split.length == 0) {
            return "";
        }
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        for (String str6 : split) {
            if (TextUtils.isEmpty(str2)) {
                if (!str6.contains("biz_type")) {
                    str2 = null;
                } else {
                    str2 = e(str6);
                }
            }
            if (TextUtils.isEmpty(str3)) {
                if (!str6.contains("biz_no")) {
                    str3 = null;
                } else {
                    str3 = e(str6);
                }
            }
            if (TextUtils.isEmpty(str4)) {
                if (!str6.contains(c.H) || str6.startsWith(c.G)) {
                    str4 = null;
                } else {
                    str4 = e(str6);
                }
            }
            if (TextUtils.isEmpty(str5)) {
                if (!str6.contains("app_userid")) {
                    str5 = null;
                } else {
                    str5 = e(str6);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            sb.append("biz_type=" + str2 + h.f735b);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("biz_no=" + str3 + h.f735b);
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append("trade_no=" + str4 + h.f735b);
        }
        if (!TextUtils.isEmpty(str5)) {
            sb.append("app_userid=" + str5 + h.f735b);
        }
        String sb2 = sb.toString();
        return sb2.endsWith(h.f735b) ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    private static String b(String str) {
        if (!str.contains("biz_type")) {
            return null;
        }
        return e(str);
    }

    private static String c(String str) {
        if (!str.contains("biz_no")) {
            return null;
        }
        return e(str);
    }

    private static String d(String str) {
        if (!str.contains(c.H) || str.startsWith(c.G)) {
            return null;
        }
        return e(str);
    }

    private static String e(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        return str2.contains("\"") ? str2.replaceAll("\"", "") : str2;
    }

    private static String f(String str) {
        if (!str.contains("app_userid")) {
            return null;
        }
        return e(str);
    }
}
