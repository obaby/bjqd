package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.qqtheme.framework.util.DateUtils;
import cn.qqtheme.framework.widget.WheelView;
import cn.xports.qd2.entity.K;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class DatePicker extends WheelPicker {
    public static final int MONTH_DAY = 2;
    public static final int YEAR_MONTH = 1;
    public static final int YEAR_MONTH_DAY = 0;
    private String dayLabel;
    /* access modifiers changed from: private */
    public ArrayList<String> days;
    private int endDay;
    private int endMonth;
    private int endYear;
    /* access modifiers changed from: private */
    public int mode;
    private String monthLabel;
    /* access modifiers changed from: private */
    public ArrayList<String> months;
    private OnDatePickListener onDatePickListener;
    /* access modifiers changed from: private */
    public int selectedDayIndex;
    /* access modifiers changed from: private */
    public int selectedMonthIndex;
    /* access modifiers changed from: private */
    public int selectedYearIndex;
    private int startDay;
    private int startMonth;
    private int startYear;
    private String yearLabel;
    /* access modifiers changed from: private */
    public ArrayList<String> years;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    protected interface OnDatePickListener {
    }

    public interface OnMonthDayPickListener extends OnDatePickListener {
        void onDatePicked(String str, String str2);
    }

    public interface OnYearMonthDayPickListener extends OnDatePickListener {
        void onDatePicked(String str, String str2, String str3);
    }

    public interface OnYearMonthPickListener extends OnDatePickListener {
        void onDatePicked(String str, String str2);
    }

    public DatePicker(Activity activity) {
        this(activity, 0);
    }

    public DatePicker(Activity activity, int i) {
        super(activity);
        this.years = new ArrayList<>();
        this.months = new ArrayList<>();
        this.days = new ArrayList<>();
        this.yearLabel = "年";
        this.monthLabel = "月";
        this.dayLabel = "日";
        this.startYear = 2010;
        this.startMonth = 1;
        this.startDay = 1;
        this.endYear = 2020;
        this.endMonth = 12;
        this.endDay = 31;
        this.selectedYearIndex = 0;
        this.selectedMonthIndex = 0;
        this.selectedDayIndex = 0;
        this.mode = 0;
        this.mode = i;
    }

    public void setLabel(String str, String str2, String str3) {
        this.yearLabel = str;
        this.monthLabel = str2;
        this.dayLabel = str3;
    }

    @Deprecated
    public void setRange(int i, int i2) {
        this.startYear = i;
        this.endYear = i2;
        changeYearData();
    }

    public void setRangeStart(int i, int i2, int i3) {
        this.startYear = i;
        this.startMonth = i2;
        this.startDay = i3;
    }

    public void setRangeEnd(int i, int i2, int i3) {
        this.endYear = i;
        this.endMonth = i2;
        this.endDay = i3;
    }

    public void setRangeStart(int i, int i2) {
        if (this.mode == 0) {
            throw new IllegalArgumentException();
        } else if (this.mode == 1) {
            this.startYear = i;
            this.startMonth = i2;
        } else {
            this.startMonth = i;
            this.startDay = i2;
        }
    }

    public void setRangeEnd(int i, int i2) {
        if (this.mode == 0) {
            throw new IllegalArgumentException();
        } else if (this.mode == 1) {
            this.endYear = i;
            this.endMonth = i2;
        } else {
            this.endMonth = i;
            this.endDay = i2;
        }
    }

    private int findItemIndex(ArrayList<String> arrayList, int i) {
        int binarySearch = Collections.binarySearch(arrayList, Integer.valueOf(i), new Comparator<Object>() {
            public int compare(Object obj, Object obj2) {
                String obj3 = obj.toString();
                String obj4 = obj2.toString();
                if (obj3.startsWith(K.k0)) {
                    obj3 = obj3.substring(1);
                }
                if (obj4.startsWith(K.k0)) {
                    obj4 = obj4.substring(1);
                }
                return Integer.parseInt(obj3) - Integer.parseInt(obj4);
            }
        });
        if (binarySearch < 0) {
            return 0;
        }
        return binarySearch;
    }

    public void setSelectedItem(int i, int i2, int i3) {
        changeYearData();
        changeMonthData(i);
        changeDayData(i, i2);
        this.selectedYearIndex = findItemIndex(this.years, i);
        this.selectedMonthIndex = findItemIndex(this.months, i2);
        this.selectedDayIndex = findItemIndex(this.days, i3);
    }

    public void setSelectedItem(int i, int i2) {
        int i3 = Calendar.getInstance(Locale.CHINA).get(1);
        changeYearData();
        if (this.mode == 2) {
            changeMonthData(i3);
            changeDayData(i3, i);
            this.selectedMonthIndex = findItemIndex(this.months, i);
            this.selectedDayIndex = findItemIndex(this.days, i2);
            return;
        }
        changeMonthData(i3);
        this.selectedYearIndex = findItemIndex(this.years, i);
        this.selectedMonthIndex = findItemIndex(this.months, i2);
    }

    public void setOnDatePickListener(OnDatePickListener onDatePickListener2) {
        this.onDatePickListener = onDatePickListener2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public View makeCenterView() {
        if (this.months.size() == 0) {
            int i = Calendar.getInstance(Locale.CHINA).get(1);
            changeYearData();
            changeDayData(i, changeMonthData(i));
        }
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        WheelView wheelView = new WheelView(this.activity.getBaseContext());
        wheelView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView.setTextSize(this.textSize);
        wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView.setLineVisible(this.lineVisible);
        wheelView.setLineColor(this.lineColor);
        wheelView.setOffset(this.offset);
        linearLayout.addView(wheelView);
        TextView textView = new TextView(this.activity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize((float) this.textSize);
        textView.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.yearLabel)) {
            textView.setText(this.yearLabel);
        }
        linearLayout.addView(textView);
        final WheelView wheelView2 = new WheelView(this.activity.getBaseContext());
        wheelView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView2.setTextSize(this.textSize);
        wheelView2.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView2.setLineVisible(this.lineVisible);
        wheelView2.setLineColor(this.lineColor);
        wheelView2.setOffset(this.offset);
        linearLayout.addView(wheelView2);
        TextView textView2 = new TextView(this.activity);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView2.setTextSize((float) this.textSize);
        textView2.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.monthLabel)) {
            textView2.setText(this.monthLabel);
        }
        linearLayout.addView(textView2);
        final WheelView wheelView3 = new WheelView(this.activity.getBaseContext());
        wheelView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView3.setTextSize(this.textSize);
        wheelView3.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView3.setLineVisible(this.lineVisible);
        wheelView3.setLineColor(this.lineColor);
        wheelView3.setOffset(this.offset);
        linearLayout.addView(wheelView3);
        TextView textView3 = new TextView(this.activity);
        textView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView3.setTextSize((float) this.textSize);
        textView3.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.dayLabel)) {
            textView3.setText(this.dayLabel);
        }
        linearLayout.addView(textView3);
        if (this.mode == 1) {
            wheelView3.setVisibility(8);
            textView3.setVisibility(8);
        } else if (this.mode == 2) {
            wheelView.setVisibility(8);
            textView.setVisibility(8);
        }
        if (this.mode != 2) {
            if (!TextUtils.isEmpty(this.yearLabel)) {
                textView.setText(this.yearLabel);
            }
            changeYearData();
            if (this.selectedYearIndex == 0) {
                wheelView.setItems(this.years);
            } else {
                wheelView.setItems((List<String>) this.years, this.selectedYearIndex);
            }
            wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    int unused = DatePicker.this.selectedYearIndex = i;
                    int trimZero = DateUtils.trimZero(str);
                    DatePicker.this.changeDayData(trimZero, DatePicker.this.changeMonthData(trimZero));
                    wheelView2.setItems((List<String>) DatePicker.this.months, DatePicker.this.selectedMonthIndex);
                    wheelView3.setItems((List<String>) DatePicker.this.days, DatePicker.this.selectedDayIndex);
                }
            });
        }
        if (!TextUtils.isEmpty(this.monthLabel)) {
            textView2.setText(this.monthLabel);
        }
        if (this.selectedMonthIndex == 0) {
            wheelView2.setItems(this.months);
        } else {
            wheelView2.setItems((List<String>) this.months, this.selectedMonthIndex);
        }
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                int unused = DatePicker.this.selectedMonthIndex = i;
                if (DatePicker.this.mode != 1) {
                    DatePicker.this.changeDayData(DateUtils.trimZero((String) DatePicker.this.years.get(DatePicker.this.selectedYearIndex)), DateUtils.trimZero(str));
                    wheelView3.setItems((List<String>) DatePicker.this.days, DatePicker.this.selectedDayIndex);
                }
            }
        });
        if (this.mode != 1) {
            if (!TextUtils.isEmpty(this.dayLabel)) {
                textView3.setText(this.dayLabel);
            }
            wheelView3.setItems((List<String>) this.days, this.selectedDayIndex);
            wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    int unused = DatePicker.this.selectedDayIndex = i;
                }
            });
        }
        return linearLayout;
    }

    private void changeYearData() {
        this.years.clear();
        if (this.startYear == this.endYear) {
            this.years.add(String.valueOf(this.startYear));
        } else if (this.startYear < this.endYear) {
            for (int i = this.startYear; i <= this.endYear; i++) {
                this.years.add(String.valueOf(i));
            }
        } else {
            for (int i2 = this.startYear; i2 >= this.endYear; i2--) {
                this.years.add(String.valueOf(i2));
            }
        }
    }

    /* access modifiers changed from: private */
    public int changeMonthData(int i) {
        String str = this.months.size() > this.selectedMonthIndex ? this.months.get(this.selectedMonthIndex) : null;
        this.months.clear();
        if (this.startYear == this.endYear) {
            for (int i2 = this.startMonth; i2 <= this.endMonth; i2++) {
                this.months.add(DateUtils.fillZero(i2));
            }
        } else if (i == this.startYear) {
            for (int i3 = this.startMonth; i3 <= 12; i3++) {
                this.months.add(DateUtils.fillZero(i3));
            }
        } else {
            int i4 = 1;
            if (i == this.endYear) {
                while (i4 <= this.endMonth) {
                    this.months.add(DateUtils.fillZero(i4));
                    i4++;
                }
            } else {
                while (i4 <= 12) {
                    this.months.add(DateUtils.fillZero(i4));
                    i4++;
                }
            }
        }
        this.selectedMonthIndex = (str == null || !this.months.contains(str)) ? 0 : this.months.indexOf(str);
        return DateUtils.trimZero(this.months.get(this.selectedMonthIndex));
    }

    /* access modifiers changed from: private */
    public void changeDayData(int i, int i2) {
        String str = this.days.size() > this.selectedDayIndex ? this.days.get(this.selectedDayIndex) : null;
        this.days.clear();
        int calculateDaysInMonth = DateUtils.calculateDaysInMonth(i, i2);
        int i3 = 0;
        if (i == this.startYear && i2 == this.startMonth) {
            for (int i4 = this.startDay; i4 <= calculateDaysInMonth; i4++) {
                this.days.add(DateUtils.fillZero(i4));
            }
            if (str != null && this.days.contains(str)) {
                i3 = this.days.indexOf(str);
            }
            this.selectedDayIndex = i3;
            return;
        }
        if (i == this.endYear && i2 == this.endMonth) {
            for (int i5 = 1; i5 <= this.endDay; i5++) {
                this.days.add(DateUtils.fillZero(i5));
            }
            if (str != null && this.days.contains(str)) {
                i3 = this.days.indexOf(str);
            }
            this.selectedDayIndex = i3;
            return;
        }
        for (int i6 = 1; i6 <= calculateDaysInMonth; i6++) {
            this.days.add(DateUtils.fillZero(i6));
        }
        if (this.selectedDayIndex >= calculateDaysInMonth) {
            this.selectedDayIndex = this.days.size() - 1;
        }
    }

    /* access modifiers changed from: protected */
    public void onSubmit() {
        if (this.onDatePickListener != null) {
            String selectedYear = getSelectedYear();
            String selectedMonth = getSelectedMonth();
            String selectedDay = getSelectedDay();
            switch (this.mode) {
                case 1:
                    ((OnYearMonthPickListener) this.onDatePickListener).onDatePicked(selectedYear, selectedMonth);
                    return;
                case 2:
                    ((OnMonthDayPickListener) this.onDatePickListener).onDatePicked(selectedMonth, selectedDay);
                    return;
                default:
                    ((OnYearMonthDayPickListener) this.onDatePickListener).onDatePicked(selectedYear, selectedMonth, selectedDay);
                    return;
            }
        }
    }

    public String getSelectedYear() {
        return this.years.get(this.selectedYearIndex);
    }

    public String getSelectedMonth() {
        return this.months.get(this.selectedMonthIndex);
    }

    public String getSelectedDay() {
        return this.days.get(this.selectedDayIndex);
    }
}
