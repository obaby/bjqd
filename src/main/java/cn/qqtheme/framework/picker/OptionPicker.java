package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.qqtheme.framework.widget.WheelView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionPicker extends WheelPicker {
    private String label = "";
    private OnOptionPickListener onOptionPickListener;
    protected ArrayList<String> options = new ArrayList<>();
    /* access modifiers changed from: private */
    public int selectedOption = 0;

    public interface OnOptionPickListener {
        void onOptionPicked(int i, String str);
    }

    public OptionPicker(Activity activity, String[] strArr) {
        super(activity);
        this.options.addAll(Arrays.asList(strArr));
    }

    public OptionPicker(Activity activity, ArrayList<String> arrayList) {
        super(activity);
        this.options.addAll(arrayList);
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setSelectedIndex(int i) {
        if (i >= 0 && i < this.options.size()) {
            this.selectedOption = i;
        }
    }

    public void setSelectedItem(String str) {
        setSelectedIndex(this.options.indexOf(str));
    }

    public void setOnOptionPickListener(OnOptionPickListener onOptionPickListener2) {
        this.onOptionPickListener = onOptionPickListener2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public View makeCenterView() {
        if (this.options.size() != 0) {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(17);
            WheelView wheelView = new WheelView(this.activity);
            wheelView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            wheelView.setTextSize(this.textSize);
            wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
            wheelView.setLineVisible(this.lineVisible);
            wheelView.setLineColor(this.lineColor);
            wheelView.setOffset(this.offset);
            linearLayout.addView(wheelView);
            TextView textView = new TextView(this.activity);
            textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            textView.setTextColor(this.textColorFocus);
            textView.setTextSize((float) this.textSize);
            linearLayout.addView(textView);
            if (!TextUtils.isEmpty(this.label)) {
                textView.setText(this.label);
            }
            wheelView.setItems((List<String>) this.options, this.selectedOption);
            wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    int unused = OptionPicker.this.selectedOption = i;
                }
            });
            return linearLayout;
        }
        throw new IllegalArgumentException("please initial options at first, can't be empty");
    }

    public void onSubmit() {
        if (this.onOptionPickListener != null) {
            this.onOptionPickListener.onOptionPicked(this.selectedOption, this.options.get(this.selectedOption));
        }
    }

    public String getSelectedOption() {
        return this.options.get(this.selectedOption);
    }

    public int getSelectedPosition() {
        return this.selectedOption;
    }
}
