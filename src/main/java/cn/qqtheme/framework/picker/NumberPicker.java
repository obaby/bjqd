package cn.qqtheme.framework.picker;

import android.app.Activity;

public class NumberPicker extends OptionPicker {
    public NumberPicker(Activity activity) {
        super(activity, new String[0]);
    }

    public void setRange(int i, int i2) {
        setRange(i, i2, 1);
    }

    public void setRange(int i, int i2, int i3) {
        while (i <= i2) {
            this.options.add(String.valueOf(i));
            i += i3;
        }
    }

    public void setSelectedItem(int i) {
        setSelectedItem(String.valueOf(i));
    }
}
