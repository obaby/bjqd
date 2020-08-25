package cn.xports.qd2.entity;

import android.text.TextUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BeginDate implements Serializable {
    ArrayList<String> dates = new ArrayList<>();
    private Calendar day;
    private boolean isCheck = false;

    public BeginDate(int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(6, i);
        this.day = instance;
    }

    public Calendar getDay() {
        return this.day;
    }

    public String getDate() {
        return String.format("%02d月%02d日", new Object[]{Integer.valueOf(this.day.get(2) + 1), Integer.valueOf(this.day.get(5))});
    }

    public String getFormatDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this.day.getTime());
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }

    public String getValidDate(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            if (!TextUtils.isEmpty(str2)) {
                String substring = str2.substring(str2.length() - 1);
                int intValue = Integer.valueOf(str2.substring(0, str2.length() - 1)).intValue();
                char c2 = 65535;
                int hashCode = substring.hashCode();
                if (hashCode != 77) {
                    if (hashCode != 100) {
                        if (hashCode == 121) {
                            if (substring.equals("y")) {
                                c2 = 0;
                            }
                        }
                    } else if (substring.equals("d")) {
                        c2 = 2;
                    }
                } else if (substring.equals("M")) {
                    c2 = 1;
                }
                switch (c2) {
                    case 0:
                        instance.add(1, intValue);
                        break;
                    case 1:
                        instance.add(2, intValue);
                        break;
                    case 2:
                        instance.add(6, intValue);
                        break;
                }
            }
            return simpleDateFormat.format(instance.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getValidDateList(String str, String str2, int i) {
        String validDate = getValidDate(str, str2);
        this.dates.add(validDate);
        if (i == 1) {
            return this.dates;
        }
        return getValidDateList(validDate, str2, i - 1);
    }

    public String getAllStartDate(String str, String str2, int i) {
        this.dates.clear();
        this.dates.add(str);
        return join(":", getValidDateList(str, str2, i - 1));
    }

    public String getAllEndDate(String str, String str2, int i) {
        this.dates.clear();
        String beforeDate = getBeforeDate(getValidDate(str, str2));
        this.dates.add(beforeDate);
        return join(":", getValidDateList(beforeDate, str2, i - 1));
    }

    private String join(String str, List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String append : list) {
            sb.append(append);
            sb.append(str);
        }
        if (sb.length() > 0) {
            sb = sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    public String getBeforeDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date parse = simpleDateFormat.parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            instance.add(6, -1);
            return simpleDateFormat.format(instance.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return str;
        }
    }
}
