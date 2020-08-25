package coband.bsit.com.integral.utils;

import android.text.TextUtils;
import cn.xports.qd2.util.PictureUtil;
import coband.bsit.com.integral.BuildConfig;
import java.util.Calendar;

public class SolarUtil {
    public static String getSolarHoliday(int i, int i2, int i3) {
        double d = (double) i2;
        double pow = Math.pow(10.0d, i3 >= 10 ? 2.0d : 1.0d);
        Double.isNaN(d);
        double d2 = d * pow;
        double d3 = (double) i3;
        Double.isNaN(d3);
        String str = "";
        switch ((int) (d2 + d3)) {
            case 11:
                str = "元旦";
                break;
            case 38:
                str = "妇女节";
                break;
            case 41:
                str = "愚人节";
                break;
            case 51:
                str = "劳动节";
                break;
            case 54:
                str = "青年节";
                break;
            case 61:
                str = "儿童节";
                break;
            case 71:
                str = "建党节";
                break;
            case 81:
                str = "建军节";
                break;
            case 101:
                str = "国庆节";
                break;
            case 214:
                str = "情人节";
                break;
            case 312:
                str = "植树节";
                break;
            case 512:
                str = "护士节";
                break;
            case 910:
                str = "教师节";
                break;
            case PictureUtil.TAKE_PHOTO:
                str = "光棍节";
                break;
            case 1224:
                str = "平安夜";
                break;
            case 1225:
                str = "圣诞节";
                break;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (i2 == 4) {
            return chingMingDay(i, i3);
        }
        if (i2 != 5) {
            return (i2 == 6 && i3 == motherFatherDay(i, i2, 2)) ? "父亲节" : str;
        }
        if (i3 == motherFatherDay(i, i2, 1)) {
            return "母亲节";
        }
        return str;
    }

    private static int motherFatherDay(int i, int i2, int i3) {
        int firstWeekOfMonth = getFirstWeekOfMonth(i, i2 - 1);
        if (firstWeekOfMonth == 0) {
            firstWeekOfMonth = 7;
        }
        return (7 - firstWeekOfMonth) + 1 + (i3 * 7);
    }

    public static String chingMingDay(int i, int i2) {
        if (i2 < 4 || i2 > 6) {
            return "";
        }
        if (i <= 1999) {
            int i3 = i - 1900;
            double d = (double) i3;
            Double.isNaN(d);
            double d2 = (double) (i3 / 4);
            Double.isNaN(d2);
            return ((int) (((d * 0.2422d) + 5.59d) - d2)) == i2 ? "清明节" : "";
        }
        int i4 = i - 2000;
        double d3 = (double) i4;
        Double.isNaN(d3);
        double d4 = (double) (i4 / 4);
        Double.isNaN(d4);
        if (((int) (((d3 * 0.2422d) + 4.81d) - d4)) == i2) {
            return "清明节";
        }
        return "";
    }

    public static int getMonthDays(int i, int i2) {
        switch (i2) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return ((i % 4 != 0 || i % 100 == 0) && i % BuildConfig.VERSION_CODE != 0) ? 28 : 29;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return -1;
        }
    }

    public static int getFirstWeekOfMonth(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, 1);
        return instance.get(7) - 1;
    }
}
