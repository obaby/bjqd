package cn.xports.baselib.util;

import android.text.TextUtils;
import cn.xports.qd2.entity.K;
import com.alibaba.android.arouter.utils.Consts;
import java.math.BigDecimal;

public class MoneyUtil {
    private static BigDecimal toYuanBigDecimal(long j) {
        double d = (double) j;
        Double.isNaN(d);
        return BigDecimal.valueOf(d / 100.0d).setScale(2, 4);
    }

    public static int yuan2CentInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return new BigDecimal(str).multiply(new BigDecimal(100)).setScale(0, 4).intValue();
    }

    public static String yuan2CentString(int i) {
        return BigDecimal.valueOf((long) i).divide(new BigDecimal(100), 2, 4).toString();
    }

    public static String cent2Yuan(BigDecimal bigDecimal) {
        return formatPrice(bigDecimal.intValue());
    }

    public static String cent2Yuan(int i) {
        return cent2Yuan(new BigDecimal(i));
    }

    public static String simpleYuan(int i) {
        String cent2Yuan = cent2Yuan(new BigDecimal(i));
        if (cent2Yuan.endsWith(".00")) {
            return cent2Yuan.replace(".00", "");
        }
        return (!cent2Yuan.contains(Consts.DOT) || !cent2Yuan.endsWith(K.k0)) ? cent2Yuan : cent2Yuan.substring(0, cent2Yuan.length() - 1);
    }

    public static String simpleYuan(String str) {
        return simpleYuan(str2Int(str));
    }

    public static String cent2Yuan(String str) {
        return cent2Yuan(str2Int(str));
    }

    public static String formatPrice(int i) {
        return new BigDecimal(String.valueOf(i)).divide(new BigDecimal(String.valueOf(100)), 2, 4).toString();
    }

    public static int add(int i, int i2) {
        return new BigDecimal(String.valueOf(i)).add(new BigDecimal(String.valueOf(i2))).intValue();
    }

    public static BigDecimal subtract(int i, int i2) {
        return new BigDecimal(String.valueOf(i)).subtract(new BigDecimal(String.valueOf(i2)));
    }

    public static boolean isBigger(int i, int i2) {
        return new BigDecimal(String.valueOf(i)).compareTo(new BigDecimal(String.valueOf(i2))) > 0;
    }

    public static boolean isBigger(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.compareTo(bigDecimal2) > 0;
    }

    public static boolean isSmaller(int i, int i2) {
        return new BigDecimal(String.valueOf(i)).compareTo(new BigDecimal(String.valueOf(i2))) < 0;
    }

    public static boolean isSmaller(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.compareTo(bigDecimal2) < 0;
    }

    public static boolean isBiggerOrEquals(int i, int i2) {
        return new BigDecimal(String.valueOf(i)).compareTo(new BigDecimal(String.valueOf(i2))) >= 0;
    }

    public static boolean isBiggerOrEquals(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.compareTo(bigDecimal2) >= 0;
    }

    public static boolean isSamllerOrEquals(int i, int i2) {
        return new BigDecimal(String.valueOf(i)).compareTo(new BigDecimal(String.valueOf(i2))) <= 0;
    }

    public static boolean isEqueals(int i, int i2) {
        return new BigDecimal(String.valueOf(i)).compareTo(new BigDecimal(String.valueOf(i2))) == 0;
    }

    public static boolean isEqueals(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.compareTo(bigDecimal2) == 0;
    }

    public static int str2Int(String str) {
        if (TextUtils.isEmpty(str) || !TextUtils.isDigitsOnly(str)) {
            return 0;
        }
        return Integer.valueOf(str).intValue();
    }
}
