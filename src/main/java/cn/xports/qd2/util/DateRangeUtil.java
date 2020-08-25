package cn.xports.qd2.util;

import android.text.TextUtils;

public class DateRangeUtil {
    public static String dateRange(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            return "";
        }
        String substring = str.substring(0, 10);
        String substring2 = str2.substring(0, 10);
        if (TextUtils.isEmpty(substring) || TextUtils.isEmpty(substring2)) {
            return "";
        }
        if (!z) {
            return substring.replace("-", "/") + " - " + substring2.replace("-", "/");
        } else if (substring.equals(substring2)) {
            return str.substring(0, 15).replace("-", "/") + " - " + str2.substring(11, 16);
        } else {
            return str.substring(0, 16).replace("-", "/") + " - " + str2.substring(0, 16).replace("-", "/");
        }
    }

    public static String dateRange(String str, String str2) {
        return dateRange(str, str2, true);
    }
}
