package cn.xports.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class ExTextView extends TextView {
    public ExTextView(Context context) {
        super(context);
    }

    public ExTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ExTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.translate(0.0f, (float) (getHeight() - getBaseline()));
        super.onDraw(canvas);
    }
}
