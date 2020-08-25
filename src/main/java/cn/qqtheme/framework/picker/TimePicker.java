package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.qqtheme.framework.util.DateUtils;
import cn.qqtheme.framework.widget.WheelView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimePicker extends WheelPicker {
    @Deprecated
    public static final int HOUR = 1;
    public static final int HOUR_12 = 1;
    public static final int HOUR_24 = 0;
    @Deprecated
    public static final int HOUR_OF_DAY = 0;
    private int endHour;
    private int endMinute;
    private String hourLabel;
    private String minuteLabel;
    private int mode;
    private OnTimePickListener onTimePickListener;
    /* access modifiers changed from: private */
    public String selectedHour;
    /* access modifiers changed from: private */
    public String selectedMinute;
    private int startHour;
    private int startMinute;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTimePickListener {
        void onTimePicked(String str, String str2);
    }

    public TimePicker(Activity activity) {
        this(activity, 0);
    }

    public TimePicker(Activity activity, int i) {
        super(activity);
        this.hourLabel = "时";
        this.minuteLabel = "分";
        this.selectedHour = "";
        this.selectedMinute = "";
        this.startMinute = 0;
        this.endMinute = 59;
        this.mode = i;
        if (i == 1) {
            this.startHour = 1;
            this.endHour = 12;
        } else {
            this.startHour = 0;
            this.endHour = 23;
        }
        this.selectedHour = DateUtils.fillZero(Calendar.getInstance().get(11));
        this.selectedMinute = DateUtils.fillZero(Calendar.getInstance().get(12));
    }

    public void setLabel(String str, String str2) {
        this.hourLabel = str;
        this.minuteLabel = str2;
    }

    public void setRangeStart(int i, int i2) {
        boolean z = true;
        boolean z2 = i < 0 || i2 < 0 || i2 > 59;
        if (this.mode == 1 && (i == 0 || i > 12)) {
            z2 = true;
        }
        if (this.mode != 0 || i < 24) {
            z = z2;
        }
        if (!z) {
            this.startHour = i;
            this.startMinute = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setRangeEnd(int i, int i2) {
        boolean z = true;
        boolean z2 = i < 0 || i2 < 0 || i2 > 59;
        if (this.mode == 1 && (i == 0 || i > 12)) {
            z2 = true;
        }
        if (this.mode != 0 || i < 24) {
            z = z2;
        }
        if (!z) {
            this.endHour = i;
            this.endMinute = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setSelectedItem(int i, int i2) {
        this.selectedHour = DateUtils.fillZero(i);
        this.selectedMinute = DateUtils.fillZero(i2);
    }

    public void setOnTimePickListener(OnTimePickListener onTimePickListener2) {
        this.onTimePickListener = onTimePickListener2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public View makeCenterView() {
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        WheelView wheelView = new WheelView(this.activity);
        wheelView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView.setTextSize(this.textSize);
        wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView.setLineVisible(this.lineVisible);
        wheelView.setLineColor(this.lineColor);
        linearLayout.addView(wheelView);
        TextView textView = new TextView(this.activity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize((float) this.textSize);
        textView.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.hourLabel)) {
            textView.setText(this.hourLabel);
        }
        linearLayout.addView(textView);
        final WheelView wheelView2 = new WheelView(this.activity);
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
        if (!TextUtils.isEmpty(this.minuteLabel)) {
            textView2.setText(this.minuteLabel);
        }
        linearLayout.addView(textView2);
        ArrayList arrayList = new ArrayList();
        for (int i = this.startHour; i <= this.endHour; i++) {
            arrayList.add(DateUtils.fillZero(i));
        }
        wheelView.setItems((List<String>) arrayList, this.selectedHour);
        wheelView2.setItems((List<String>) changeMinuteData(this.selectedHour), this.selectedMinute);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                String unused = TimePicker.this.selectedHour = str;
                wheelView2.setItems(TimePicker.this.changeMinuteData(str));
            }
        });
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            public void onSelected(boolean z, int i, String str) {
                String unused = TimePicker.this.selectedMinute = str;
            }
        });
        return linearLayout;
    }

    /* access modifiers changed from: private */
    public ArrayList<String> changeMinuteData(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        int trimZero = DateUtils.trimZero(str);
        if (trimZero == this.startHour) {
            for (int i = this.startMinute; i <= 59; i++) {
                arrayList.add(DateUtils.fillZero(i));
            }
            this.selectedMinute = "00";
        } else {
            int i2 = 0;
            if (trimZero == this.endHour) {
                while (i2 <= this.endMinute) {
                    arrayList.add(DateUtils.fillZero(i2));
                    i2++;
                }
                this.selectedMinute = "00";
            } else {
                while (i2 <= 59) {
                    arrayList.add(DateUtils.fillZero(i2));
                    i2++;
                }
            }
        }
        return arrayList;
    }

    public void onSubmit() {
        if (this.onTimePickListener != null) {
            this.onTimePickListener.onTimePicked(this.selectedHour, this.selectedMinute);
        }
    }

    public String getSelectedHour() {
        return this.selectedHour;
    }

    public String getSelectedMinute() {
        return this.selectedMinute;
    }
}
