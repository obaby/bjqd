package cn.xports.stepCountUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class StepDateUtils {
    private static ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT = new ThreadLocal<>();

    StepDateUtils() {
    }

    private static SimpleDateFormat getDateFormat() {
        SimpleDateFormat simpleDateFormat = SIMPLE_DATE_FORMAT.get();
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat();
        SIMPLE_DATE_FORMAT.set(simpleDateFormat2);
        return simpleDateFormat2;
    }

    public static String getCurrentDate(String str) {
        getDateFormat().applyPattern(str);
        return getDateFormat().format(new Date(System.currentTimeMillis()));
    }

    private static long getDateMillis(String str, String str2) {
        getDateFormat().applyPattern(str2);
        try {
            return getDateFormat().parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static String dateFormat(long j, String str) {
        getDateFormat().applyPattern(str);
        return getDateFormat().format(new Date(j));
    }

    private static String dateFormat(String str, String str2, String str3) {
        long dateMillis = getDateMillis(str, str2);
        if (0 == dateMillis) {
            return str;
        }
        return dateFormat(dateMillis, str3);
    }
}
