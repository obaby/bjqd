package cn.xports.sportCoaching;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateShowUtils {
    public static String getPostFriendlyTime(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        long time = (new Date().getTime() - date.getTime()) / 1000;
        if (time <= 0) {
            return date.toLocaleString();
        }
        if (time / 2592000 > 0) {
            return new SimpleDateFormat("yyyy年MM月dd日").format(date);
        }
        if (time / 604800 > 0) {
            return new SimpleDateFormat("MM月dd日").format(date);
        }
        long j = time / 86400;
        if (j > 0) {
            return j + "天前";
        }
        long j2 = time / 3600;
        if (j2 > 0) {
            return j2 + "小时前";
        }
        long j3 = time / 60;
        if (j3 <= 0) {
            return "刚刚";
        }
        return j3 + "分钟前";
    }

    public static String dateToYYYYMMDDHHMM(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(date);
    }
}
