package cn.xports.util;

import android.text.TextUtils;

public class StringUtil {
    public static String removeRange(String str, String str2, String str3) {
        int indexOf;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        while (true) {
            int indexOf2 = str.indexOf(str2);
            if (indexOf2 == -1 || (indexOf = str.indexOf(str3, indexOf2)) == -1) {
                return str;
            }
            str = str.substring(0, indexOf2) + str.substring(indexOf + str3.length());
        }
    }
}
