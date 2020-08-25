package cn.xports.baselib.util;

import cn.xports.qd2.entity.K;

public class SegmentUtils {
    public static String getStartTime(int i) {
        String str;
        if (i % 2 == 0) {
            str = (i / 2) + ":00";
        } else {
            str = (i / 2) + ":30";
        }
        if (str.length() != 4) {
            return str;
        }
        return K.k0 + str;
    }

    public static String getEndTime(int i) {
        String str;
        int i2 = i + 1;
        if (i2 % 2 == 0) {
            str = (i2 / 2) + ":00";
        } else {
            str = (i2 / 2) + ":30";
        }
        if (str.length() != 4) {
            return str;
        }
        return K.k0 + str;
    }
}
