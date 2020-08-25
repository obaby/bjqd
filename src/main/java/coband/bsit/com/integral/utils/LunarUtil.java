package coband.bsit.com.integral.utils;

import android.support.v4.internal.view.SupportMenu;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class LunarUtil {
    private static final int[] LUNAR_INFO = {19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448};
    private static final int MAX_YEAR = 2049;
    private static final int MIN_YEAR = 1900;
    private static final String START_DATE = "19000130";
    private static final String[] dayInfo = {"", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] monthInfo = {"", "正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    private static final String[] solarTerm = {"小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"};
    private static final int[] solarTermInfo = {0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758};
    private static GregorianCalendar utcCal = null;

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0088, code lost:
        r0 = getYearDays(r8);
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0091 A[LOOP:0: B:18:0x0083->B:22:0x0091, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] solarToLunar(int r7, int r8, int r9) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = ""
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r0 = 10
            if (r8 >= r0) goto L_0x002a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "0"
            r1.append(r7)
            r1.append(r8)
            java.lang.String r7 = r1.toString()
            goto L_0x0039
        L_0x002a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            r1.append(r8)
            java.lang.String r7 = r1.toString()
        L_0x0039:
            if (r9 >= r0) goto L_0x0050
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r7)
            java.lang.String r7 = "0"
            r8.append(r7)
            r8.append(r9)
            java.lang.String r7 = r8.toString()
            goto L_0x005f
        L_0x0050:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r7)
            r8.append(r9)
            java.lang.String r7 = r8.toString()
        L_0x005f:
            java.text.SimpleDateFormat r8 = new java.text.SimpleDateFormat
            java.lang.String r9 = "yyyyMMdd"
            r8.<init>(r9)
            r9 = 0
            java.util.Date r7 = r8.parse(r7)     // Catch:{ ParseException -> 0x0075 }
            java.lang.String r0 = "19000130"
            java.util.Date r8 = r8.parse(r0)     // Catch:{ ParseException -> 0x0073 }
            goto L_0x007b
        L_0x0073:
            r8 = move-exception
            goto L_0x0077
        L_0x0075:
            r8 = move-exception
            r7 = r9
        L_0x0077:
            r8.printStackTrace()
            r8 = r9
        L_0x007b:
            int r7 = daysBetween(r8, r7)
            r8 = 1900(0x76c, float:2.662E-42)
            r9 = 0
            r0 = 0
        L_0x0083:
            r1 = 2049(0x801, float:2.871E-42)
            r2 = 1
            if (r8 > r1) goto L_0x0095
            int r0 = getYearDays(r8)
            int r1 = r7 - r0
            if (r1 >= r2) goto L_0x0091
            goto L_0x0095
        L_0x0091:
            int r8 = r8 + 1
            r7 = r1
            goto L_0x0083
        L_0x0095:
            int r1 = getLeapMonth(r8)
            if (r1 <= 0) goto L_0x009d
            r3 = 1
            goto L_0x009e
        L_0x009d:
            r3 = 0
        L_0x009e:
            r4 = r7
            r7 = 1
            r5 = 0
        L_0x00a1:
            r6 = 12
            if (r7 > r6) goto L_0x00c5
            int r6 = r1 + 1
            if (r7 != r6) goto L_0x00b4
            if (r3 == 0) goto L_0x00b4
            int r0 = getLeapMonthDays(r8)
            int r7 = r7 + -1
            r3 = 0
            r5 = 1
            goto L_0x00be
        L_0x00b4:
            int r6 = getMonthDays(r8, r7)     // Catch:{ Exception -> 0x00ba }
            r0 = r6
            goto L_0x00be
        L_0x00ba:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00be:
            int r4 = r4 - r0
            if (r4 > 0) goto L_0x00c2
            goto L_0x00c5
        L_0x00c2:
            int r7 = r7 + 1
            goto L_0x00a1
        L_0x00c5:
            int r4 = r4 + r0
            r0 = 3
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            if (r7 != r1) goto L_0x00d2
            r1 = 1
            goto L_0x00d3
        L_0x00d2:
            r1 = 0
        L_0x00d3:
            r1 = r1 & r5
            if (r1 == 0) goto L_0x00da
            java.lang.String r1 = "闰"
            goto L_0x00dc
        L_0x00da:
            java.lang.String r1 = ""
        L_0x00dc:
            r3.append(r1)
            java.lang.String r1 = getLunarMonth(r7)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0[r9] = r1
            java.lang.String r9 = getLunarDay(r4)
            r0[r2] = r9
            r9 = 2
            java.lang.String r7 = getLunarHoliday(r8, r7, r4)
            r0[r9] = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coband.bsit.com.integral.utils.LunarUtil.solarToLunar(int, int, int):java.lang.String[]");
    }

    private static int getMonthDays(int i, int i2) throws Exception {
        if (i2 > 31 || i2 < 0) {
            throw new Exception("月份有错！");
        }
        return ((LUNAR_INFO[i + -1900] & SupportMenu.USER_MASK) & (1 << (16 - i2))) == 0 ? 29 : 30;
    }

    private static int daysBetween(Date date, Date date2) {
        int i;
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        int i2 = instance.get(1);
        int i3 = instance2.get(1);
        if (instance.before(instance2)) {
            i = (0 - instance.get(6)) + instance2.get(6);
            instance2 = instance;
        } else {
            i = (0 - instance2.get(6)) + instance.get(6);
        }
        for (int i4 = 0; i4 < Math.abs(i3 - i2); i4++) {
            i += instance2.getActualMaximum(6);
            instance2.add(1, 1);
        }
        return i;
    }

    private static int getYearDays(int i) {
        int i2 = 348;
        for (int i3 = 32768; i3 >= 8; i3 >>= 1) {
            if ((LUNAR_INFO[i - 1900] & 65520 & i3) != 0) {
                i2++;
            }
        }
        return i2 + getLeapMonthDays(i);
    }

    private static int getLeapMonthDays(int i) {
        if (getLeapMonth(i) != 0) {
            return (LUNAR_INFO[i + -1900] & 983040) == 0 ? 29 : 30;
        }
        return 0;
    }

    private static int getLeapMonth(int i) {
        return LUNAR_INFO[i - 1900] & 15;
    }

    private static String getLunarMonth(int i) {
        return monthInfo[i];
    }

    private static String getLunarDay(int i) {
        if (i == 10) {
            return "初十";
        }
        if (i == 20) {
            return "二十";
        }
        if (i == 30) {
            return "三十";
        }
        String str = "";
        int i2 = i / 10;
        if (i2 == 0) {
            str = "初";
        } else if (i2 == 1) {
            str = "十";
        } else if (i2 == 2) {
            str = "廿";
        } else if (i2 == 3) {
            str = "卅";
        }
        return str + dayInfo[i % 10];
    }

    private static String getLunarHoliday(int i, int i2, int i3) {
        if (i2 == 1 && i3 == 1) {
            return "春节";
        }
        if (i2 == 1 && i3 == 15) {
            return "元宵节";
        }
        if (i2 == 2 && i3 == 2) {
            return "龙抬头";
        }
        if (i2 == 5 && i3 == 5) {
            return "端午节";
        }
        if (i2 == 7 && i3 == 7) {
            return "七夕";
        }
        if (i2 == 8 && i3 == 15) {
            return "中秋节";
        }
        if (i2 == 9 && i3 == 9) {
            return "重阳节";
        }
        if (i2 == 12 && i3 == 8) {
            return "腊八";
        }
        if (i2 == 12 && i3 == 23) {
            return "小年";
        }
        if (i2 != 12) {
            return "";
        }
        if ((daysInLunarMonth(i, i2) == 29 && i3 == 29) || (daysInLunarMonth(i, i2) == 30 && i3 == 30)) {
            return "除夕";
        }
        return "";
    }

    public static int daysInLunarMonth(int i, int i2) {
        return (LUNAR_INFO[i + -1900] & (65536 >> i2)) == 0 ? 29 : 30;
    }

    public static String getTermString(int i, int i2, int i3) {
        int i4 = i2 * 2;
        if (getSolarTermDay(i, i4) == i3) {
            return solarTerm[i4];
        }
        int i5 = i4 + 1;
        if (getSolarTermDay(i, i5) == i3) {
            return solarTerm[i5];
        }
        return "";
    }

    private static int getSolarTermDay(int i, int i2) {
        return getUTCDay(getSolarTermCalendar(i, i2));
    }

    private static Date getSolarTermCalendar(int i, int i2) {
        return new Date((((long) (i - 1900)) * 31556925974L) + (((long) solarTermInfo[i2]) * 60000) + UTC(MIN_YEAR, 0, 6, 2, 5, 0));
    }

    private static synchronized int getUTCDay(Date date) {
        int i;
        synchronized (LunarUtil.class) {
            makeUTCCalendar();
            synchronized (utcCal) {
                utcCal.clear();
                utcCal.setTimeInMillis(date.getTime());
                i = utcCal.get(5);
            }
        }
        return i;
    }

    private static void makeUTCCalendar() {
        if (utcCal == null) {
            utcCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        }
    }

    private static synchronized long UTC(int i, int i2, int i3, int i4, int i5, int i6) {
        long timeInMillis;
        synchronized (LunarUtil.class) {
            makeUTCCalendar();
            synchronized (utcCal) {
                utcCal.clear();
                utcCal.set(i, i2, i3, i4, i5, i6);
                timeInMillis = utcCal.getTimeInMillis();
            }
        }
        return timeInMillis;
    }
}
