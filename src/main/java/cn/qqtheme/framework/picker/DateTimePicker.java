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

public class DateTimePicker extends WheelPicker {
    public static final int HOUR = 4;
    public static final int HOUR_OF_DAY = 3;
    public static final int MONTH_DAY = 2;
    public static final int YEAR_MONTH = 1;
    public static final int YEAR_MONTH_DAY = 0;
    private String dayLabel = "日";
    /* access modifiers changed from: private */
    public ArrayList<String> days = new ArrayList<>();
    private String hourLabel = "时";
    private String minuteLabel = "分";
    /* access modifiers changed from: private */
    public int mode;
    private String monthLabel = "月";
    /* access modifiers changed from: private */
    public ArrayList<String> months = new ArrayList<>();
    private OnDateTimePickListener onDateTimePickListener;
    /* access modifiers changed from: private */
    public int selectedDayIndex = 0;
    /* access modifiers changed from: private */
    public String selectedHour = "";
    /* access modifiers changed from: private */
    public String selectedMinute = "";
    /* access modifiers changed from: private */
    public int selectedMonthIndex = 0;
    /* access modifiers changed from: private */
    public int selectedYearIndex = 0;
    private String yearLabel = "年";
    /* access modifiers changed from: private */
    public ArrayList<String> years = new ArrayList<>();

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    protected interface OnDateTimePickListener {
    }

    public interface OnMonthDayPickListener extends OnDateTimePickListener {
        void onDateTimePicked(String str, String str2, String str3, String str4);
    }

    public interface OnYearMonthDayTimePickListener extends OnDateTimePickListener {
        void onDateTimePicked(String str, String str2, String str3, String str4, String str5);
    }

    public interface OnYearMonthPickListener extends OnDateTimePickListener {
        void onDateTimePicked(String str, String str2, String str3, String str4);
    }

    public DateTimePicker(Activity activity, int i) {
        super(activity);
        this.textSize = 16;
        this.mode = i;
        for (int i2 = 2000; i2 <= 2050; i2++) {
            this.years.add(String.valueOf(i2));
        }
        for (int i3 = 1; i3 <= 12; i3++) {
            this.months.add(DateUtils.fillZero(i3));
        }
        for (int i4 = 1; i4 <= 31; i4++) {
            this.days.add(DateUtils.fillZero(i4));
        }
        this.selectedHour = DateUtils.fillZero(Calendar.getInstance().get(11));
        this.selectedMinute = DateUtils.fillZero(Calendar.getInstance().get(12));
    }

    /* access modifiers changed from: protected */
    @NonNull
    public View makeCenterView() {
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
        WheelView wheelView2 = new WheelView(this.activity.getBaseContext());
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
        WheelView wheelView4 = new WheelView(this.activity);
        wheelView4.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView4.setTextSize(this.textSize);
        wheelView4.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView4.setLineVisible(this.lineVisible);
        wheelView4.setLineColor(this.lineColor);
        linearLayout.addView(wheelView4);
        TextView textView4 = new TextView(this.activity);
        textView4.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView4.setTextSize((float) this.textSize);
        textView4.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.hourLabel)) {
            textView4.setText(this.hourLabel);
        }
        linearLayout.addView(textView4);
        WheelView wheelView5 = new WheelView(this.activity);
        wheelView5.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView5.setTextSize(this.textSize);
        wheelView5.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView5.setLineVisible(this.lineVisible);
        wheelView5.setLineColor(this.lineColor);
        wheelView5.setOffset(this.offset);
        linearLayout.addView(wheelView5);
        TextView textView5 = new TextView(this.activity);
        textView5.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView5.setTextSize((float) this.textSize);
        textView5.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.minuteLabel)) {
            textView5.setText(this.minuteLabel);
        }
        linearLayout.addView(textView5);
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
            if (this.selectedYearIndex == 0) {
                wheelView.setItems(this.years);
            } else {
                wheelView.setItems((List<String>) this.years, this.selectedYearIndex);
            }
            wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    int unused = DateTimePicker.this.selectedYearIndex = i;
                    DateTimePicker.this.days.clear();
                    int calculateDaysInMonth = DateUtils.calculateDaysInMonth(DateUtils.trimZero(str), DateUtils.trimZero((String) DateTimePicker.this.months.get(DateTimePicker.this.selectedMonthIndex)));
                    for (int i2 = 1; i2 <= calculateDaysInMonth; i2++) {
                        DateTimePicker.this.days.add(DateUtils.fillZero(i2));
                    }
                    if (DateTimePicker.this.selectedDayIndex >= calculateDaysInMonth) {
                        int unused2 = DateTimePicker.this.selectedDayIndex = DateTimePicker.this.days.size() - 1;
                    }
                    wheelView3.setItems((List<String>) DateTimePicker.this.days, DateTimePicker.this.selectedDayIndex);
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
                int unused = DateTimePicker.this.selectedMonthIndex = i;
                if (DateTimePicker.this.mode != 1) {
                    DateTimePicker.this.days.clear();
                    int calculateDaysInMonth = DateUtils.calculateDaysInMonth(DateUtils.trimZero((String) DateTimePicker.this.years.get(DateTimePicker.this.selectedYearIndex)), DateUtils.trimZero(str));
                    for (int i2 = 1; i2 <= calculateDaysInMonth; i2++) {
                        DateTimePicker.this.days.add(DateUtils.fillZero(i2));
                    }
                    if (DateTimePicker.this.selectedDayIndex >= calculateDaysInMonth) {
                        int unused2 = DateTimePicker.this.selectedDayIndex = DateTimePicker.this.days.size() - 1;
                    }
                    wheelView3.setItems((List<String>) DateTimePicker.this.days, DateTimePicker.this.selectedDayIndex);
                }
            }
        });
        if (this.mode != 1) {
            if (!TextUtils.isEmpty(this.dayLabel)) {
                textView3.setText(this.dayLabel);
            }
            if (this.selectedDayIndex == 0) {
                wheelView3.setItems(this.days);
            } else {
                wheelView3.setItems((List<String>) this.days, this.selectedDayIndex);
            }
            wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    int unused = DateTimePicker.this.selectedDayIndex = i;
                }
            });
        }
        ArrayList arrayList = new ArrayList();
        if (this.mode == 4) {
            for (int i = 1; i <= 12; i++) {
                arrayList.add(DateUtils.fillZero(i));
            }
        } else {
            for (int i2 = 0; i2 < 24; i2++) {
                arrayList.add(DateUtils.fillZero(i2));
            }
        }
        wheelView4.setItems((List<String>) arrayList, this.selectedHour);
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < 60; i3++) {
            arrayList2.add(DateUtils.fillZero(i3));
        }
        wheelView5.setItems((List<String>) arrayList2, this.selectedMinute);
        wheelView4.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                String unused = DateTimePicker.this.selectedHour = str;
            }
        });
        wheelView5.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                String unused = DateTimePicker.this.selectedMinute = str;
            }
        });
        return linearLayout;
    }

    /* access modifiers changed from: protected */
    public void onSubmit() {
        if (this.onDateTimePickListener != null) {
            String selectedYear = getSelectedYear();
            String selectedMonth = getSelectedMonth();
            String selectedDay = getSelectedDay();
            switch (this.mode) {
                case 1:
                    ((OnYearMonthPickListener) this.onDateTimePickListener).onDateTimePicked(selectedYear, selectedMonth, this.selectedHour, this.selectedMinute);
                    return;
                case 2:
                    ((OnMonthDayPickListener) this.onDateTimePickListener).onDateTimePicked(selectedMonth, selectedDay, this.selectedHour, this.selectedMinute);
                    return;
                default:
                    ((OnYearMonthDayTimePickListener) this.onDateTimePickListener).onDateTimePicked(selectedYear, selectedMonth, selectedDay, this.selectedHour, this.selectedMinute);
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

    public void setLabel(String str, String str2, String str3, String str4, String str5) {
        this.yearLabel = str;
        this.monthLabel = str2;
        this.dayLabel = str3;
        this.hourLabel = str4;
        this.minuteLabel = str5;
    }

    public void setRange(int i, int i2) {
        this.years.clear();
        while (i <= i2) {
            this.years.add(String.valueOf(i));
            i++;
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

    public void setSelectedItem(int i, int i2, int i3, int i4, int i5) {
        this.selectedYearIndex = findItemIndex(this.years, i);
        this.selectedMonthIndex = findItemIndex(this.months, i2);
        this.selectedDayIndex = findItemIndex(this.days, i3);
        this.selectedHour = DateUtils.fillZero(i4);
        this.selectedMinute = DateUtils.fillZero(i5);
    }

    public void setSelectedItem(int i, int i2, int i3, int i4) {
        if (this.mode == 2) {
            this.selectedMonthIndex = findItemIndex(this.months, i);
            this.selectedDayIndex = findItemIndex(this.days, i2);
        } else {
            this.selectedYearIndex = findItemIndex(this.years, i);
            this.selectedMonthIndex = findItemIndex(this.months, i2);
        }
        this.selectedHour = DateUtils.fillZero(i3);
        this.selectedMinute = DateUtils.fillZero(i4);
    }

    public void setOnDateTimePickListener(OnDateTimePickListener onDateTimePickListener2) {
        this.onDateTimePickListener = onDateTimePickListener2;
    }
}
