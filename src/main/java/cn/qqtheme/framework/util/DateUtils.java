package cn.qqtheme.framework.util;

import android.support.annotation.NonNull;
import cn.xports.base.Constant;
import cn.xports.qd2.entity.K;
import coband.bsit.com.integral.BuildConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtils extends android.text.format.DateUtils {
    public static final int Day = 3;
    public static final int Hour = 2;
    public static final int Minute = 1;
    public static final int Second = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DifferenceMode {
    }

    public static long calculateDifferentSecond(Date date, Date date2) {
        return calculateDifference(date, date2, 0);
    }

    public static long calculateDifferentMinute(Date date, Date date2) {
        return calculateDifference(date, date2, 1);
    }

    public static long calculateDifferentHour(Date date, Date date2) {
        return calculateDifference(date, date2, 2);
    }

    public static long calculateDifferentDay(Date date, Date date2) {
        return calculateDifference(date, date2, 3);
    }

    public static long calculateDifferentSecond(long j, long j2) {
        return calculateDifference(j, j2, 0);
    }

    public static long calculateDifferentMinute(long j, long j2) {
        return calculateDifference(j, j2, 1);
    }

    public static long calculateDifferentHour(long j, long j2) {
        return calculateDifference(j, j2, 2);
    }

    public static long calculateDifferentDay(long j, long j2) {
        return calculateDifference(j, j2, 3);
    }

    public static long calculateDifference(long j, long j2, int i) {
        return calculateDifference(new Date(j), new Date(j2), i);
    }

    public static long calculateDifference(Date date, Date date2, int i) {
        long[] calculateDifference = calculateDifference(date, date2);
        if (i == 1) {
            return calculateDifference[2];
        }
        if (i == 2) {
            return calculateDifference[1];
        }
        if (i == 3) {
            return calculateDifference[0];
        }
        return calculateDifference[3];
    }

    private static long[] calculateDifference(Date date, Date date2) {
        return calculateDifference(date2.getTime() - date.getTime());
    }

    private static long[] calculateDifference(long j) {
        long j2 = j / 86400000;
        long j3 = j % 86400000;
        long j4 = j3 / 3600000;
        long j5 = j3 % 3600000;
        long j6 = j5 / 60000;
        long j7 = j5 % 60000;
        long j8 = j7 / 1000;
        LogUtils.debug(String.format(Locale.CHINA, "different: %d ms, %d days, %d hours, %d minutes, %d seconds", new Object[]{Long.valueOf(j7), Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j6), Long.valueOf(j8)}));
        return new long[]{j2, j4, j6, j8};
    }

    public static int calculateDaysInMonth(int i) {
        return calculateDaysInMonth(0, i);
    }

    public static int calculateDaysInMonth(int i, int i2) {
        String[] strArr = {"1", "3", "5", "7", "8", Constant.COUPON_PAY, Constant.DEPOSIT_PAY};
        String[] strArr2 = {"4", "6", "9", Constant.ECARD_PAY};
        List asList = Arrays.asList(strArr);
        List asList2 = Arrays.asList(strArr2);
        if (asList.contains(String.valueOf(i2))) {
            return 31;
        }
        if (asList2.contains(String.valueOf(i2))) {
            return 30;
        }
        if (i <= 0) {
            return 29;
        }
        if ((i % 4 != 0 || i % 100 == 0) && i % BuildConfig.VERSION_CODE != 0) {
            return 28;
        }
        return 29;
    }

    @NonNull
    public static String fillZero(int i) {
        StringBuilder sb;
        String str;
        if (i < 10) {
            sb = new StringBuilder();
            str = K.k0;
        } else {
            sb = new StringBuilder();
            str = "";
        }
        sb.append(str);
        sb.append(i);
        return sb.toString();
    }

    public static int trimZero(@NonNull String str) {
        if (str.startsWith(K.k0)) {
            str = str.substring(1);
        }
        return Integer.parseInt(str);
    }

    public static boolean isSameDay(Date date) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(date);
            if (instance.get(0) == instance2.get(0) && instance.get(1) == instance2.get(1) && instance.get(6) == instance2.get(6)) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("date is null");
    }

    public static Date parseDate(String str, String str2) {
        try {
            return new Date(new SimpleDateFormat(str2).parse(str).getTime());
        } catch (Exception e) {
            LogUtils.warn((Throwable) e);
            return null;
        }
    }

    public static Date parseDate(String str) {
        return parseDate(str, "yyyy-MM-dd HH:mm:ss");
    }
}
