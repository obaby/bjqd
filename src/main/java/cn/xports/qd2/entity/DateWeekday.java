package cn.xports.qd2.entity;

import android.text.TextUtils;
import com.blankj.utilcode.util.TimeUtils;
import java.io.Serializable;

public class DateWeekday implements Serializable {
    private String date;
    private boolean isSelect;

    public boolean isSelect() {
        return this.isSelect;
    }

    public DateWeekday setSelect(boolean z) {
        this.isSelect = z;
        return this;
    }

    public String getDate() {
        return this.date;
    }

    public DateWeekday setDate(String str) {
        this.date = str;
        return this;
    }

    public String getDayOfMonth() {
        if (this.date == null) {
            return "";
        }
        return this.date.substring(8, 10);
    }

    public String getWeekday() {
        if (this.date == null) {
            return "";
        }
        return TimeUtils.getChineseWeek(this.date);
    }

    public boolean isToday() {
        if (TextUtils.isEmpty(this.date)) {
            return false;
        }
        return TimeUtils.isToday(this.date);
    }
}
