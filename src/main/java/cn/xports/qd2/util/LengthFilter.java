package cn.xports.qd2.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import com.blankj.utilcode.util.ToastUtils;

public class LengthFilter implements InputFilter {
    private final int mMax;
    private String msg;

    public LengthFilter(int i) {
        this.mMax = i;
    }

    public LengthFilter(int i, String str) {
        this.mMax = i;
        this.msg = str;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        int length = this.mMax - (spanned.length() - (i4 - i3));
        if (length <= 0 && !TextUtils.isEmpty(this.msg)) {
            ToastUtils.showShort(this.msg);
        }
        if (length <= 0) {
            return "";
        }
        if (length >= i2 - i) {
            return null;
        }
        int i5 = length + i;
        if (!Character.isHighSurrogate(charSequence.charAt(i5 - 1)) || i5 - 1 != i) {
            return charSequence.subSequence(i, i5);
        }
        return "";
    }

    public int getMax() {
        return this.mMax;
    }
}
