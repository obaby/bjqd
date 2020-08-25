package cn.xports.baselib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class FakeBoldText extends TextView {
    public FakeBoldText(Context context) {
        this(context, (AttributeSet) null);
    }

    public FakeBoldText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FakeBoldText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getPaint().setFakeBoldText(true);
        setText(getText());
    }
}
