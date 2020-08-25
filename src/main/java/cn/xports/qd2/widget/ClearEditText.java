package cn.xports.qd2.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import cn.xports.qd2.R;

public class ClearEditText extends AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {
    private Drawable mClearTextIcon;
    private View.OnFocusChangeListener mOnFocusChangeListener;
    private View.OnTouchListener mOnTouchListener;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public ClearEditText(Context context) {
        super(context);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_clear));
        DrawableCompat.setTint(wrap, getCurrentHintTextColor());
        this.mClearTextIcon = wrap;
        this.mClearTextIcon.setBounds(0, 0, this.mClearTextIcon.getIntrinsicHeight(), this.mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    public void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    public void onFocusChange(View view, boolean z) {
        boolean z2 = false;
        if (z) {
            if (getText().length() > 0) {
                z2 = true;
            }
            setClearIconVisible(z2);
        } else {
            setClearIconVisible(false);
        }
        if (this.mOnFocusChangeListener != null) {
            this.mOnFocusChangeListener.onFocusChange(view, z);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        if (this.mClearTextIcon.isVisible() && x > (getWidth() - getPaddingRight()) - this.mClearTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == 1) {
                setError((CharSequence) null);
                setText("");
            }
            return true;
        } else if (this.mOnTouchListener == null || !this.mOnTouchListener.onTouch(view, motionEvent)) {
            return false;
        } else {
            return true;
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (isFocused()) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }

    private void setClearIconVisible(boolean z) {
        this.mClearTextIcon.setVisible(z, false);
        Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], z ? this.mClearTextIcon : null, compoundDrawables[3]);
    }
}
