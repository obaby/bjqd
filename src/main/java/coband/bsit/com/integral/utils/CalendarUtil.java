package coband.bsit.com.integral.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import coband.bsit.com.integral.bean.DateBean;
import com.alibaba.android.arouter.utils.Consts;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CalendarUtil {
    public static int dateToPosition(int i, int i2, int i3, int i4) {
        return (((i - i3) * 12) + i2) - i4;
    }

    public static List<DateBean> getMonthDate(int i, int i2, Map<String, String> map) {
        int i3;
        int i4;
        int i5;
        int i6;
        ArrayList arrayList = new ArrayList();
        int i7 = i2 - 1;
        int firstWeekOfMonth = SolarUtil.getFirstWeekOfMonth(i, i7);
        if (i2 == 1) {
            i4 = i - 1;
            i3 = 12;
        } else {
            i3 = i7;
            i4 = i;
        }
        int monthDays = SolarUtil.getMonthDays(i4, i3);
        int monthDays2 = SolarUtil.getMonthDays(i, i2);
        if (i2 == 12) {
            i6 = i + 1;
            i5 = 1;
        } else {
            i5 = i2 + 1;
            i6 = i;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < firstWeekOfMonth; i9++) {
            arrayList.add(initDateBean(i4, i3, (monthDays - firstWeekOfMonth) + 1 + i9, 0, map));
        }
        int i10 = 0;
        while (i10 < monthDays2) {
            i10++;
            arrayList.add(initDateBean(i, i2, i10, 1, map));
        }
        while (i8 < ((getMonthRows(i, i2) * 7) - monthDays2) - firstWeekOfMonth) {
            i8++;
            arrayList.add(initDateBean(i6, i5, i8, 2, map));
        }
        return arrayList;
    }

    private static DateBean initDateBean(int i, int i2, int i3, int i4, Map<String, String> map) {
        DateBean dateBean = new DateBean();
        dateBean.setSolar(i, i2, i3);
        if (map == null) {
            String[] solarToLunar = LunarUtil.solarToLunar(i, i2, i3);
            dateBean.setLunar(new String[]{solarToLunar[0], solarToLunar[1]});
            dateBean.setLunarHoliday(solarToLunar[2]);
        } else {
            if (map.containsKey(i + Consts.DOT + i2 + Consts.DOT + i3)) {
                dateBean.setLunar(new String[]{"", map.get(i + Consts.DOT + i2 + Consts.DOT + i3), ""});
            } else {
                dateBean.setLunar(new String[]{"", "", ""});
            }
        }
        dateBean.setType(i4);
        dateBean.setTerm(LunarUtil.getTermString(i, i2 - 1, i3));
        if (i4 == 0) {
            dateBean.setSolarHoliday(SolarUtil.getSolarHoliday(i, i2, i3 - 1));
        } else {
            dateBean.setSolarHoliday(SolarUtil.getSolarHoliday(i, i2, i3));
        }
        return dateBean;
    }

    public static DateBean getDateBean(int i, int i2, int i3) {
        return initDateBean(i, i2, i3, 1, (Map<String, String>) null);
    }

    public static int getMonthRows(int i, int i2) {
        int firstWeekOfMonth = SolarUtil.getFirstWeekOfMonth(i, i2 - 1) + SolarUtil.getMonthDays(i, i2);
        int i3 = firstWeekOfMonth % 7 == 0 ? firstWeekOfMonth / 7 : (firstWeekOfMonth / 7) + 1;
        if (i3 == 4) {
            return 5;
        }
        return i3;
    }

    public static int[] positionToDate(int i, int i2, int i3) {
        int i4 = (i / 12) + i2;
        int i5 = (i % 12) + i3;
        if (i5 > 12) {
            i5 %= 12;
            i4++;
        }
        return new int[]{i4, i5};
    }

    public static int[] getCurrentDate() {
        Calendar instance = Calendar.getInstance();
        return new int[]{instance.get(1), instance.get(2) + 1, instance.get(5)};
    }

    public static int[] strToArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            iArr[i] = Integer.valueOf(split[i]).intValue();
        }
        return iArr;
    }

    public static long dateToMillis(int[] iArr) {
        int i = iArr.length == 2 ? 1 : iArr[2];
        Calendar instance = Calendar.getInstance();
        instance.set(iArr[0], iArr[1], i);
        return instance.getTimeInMillis();
    }

    public static int getPxSize(Context context, int i) {
        return i * context.getResources().getDisplayMetrics().densityDpi;
    }

    public static int getTextSize1(Context context, int i) {
        return (int) (((float) i) * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int getTextSize(Context context, int i) {
        return (int) TypedValue.applyDimension(0, (float) i, context.getResources().getDisplayMetrics());
    }
}
