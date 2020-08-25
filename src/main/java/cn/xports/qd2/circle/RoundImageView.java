package cn.xports.qd2.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class RoundImageView extends AppCompatImageView {
    float height;
    float width;

    public RoundImageView(Context context) {
        this(context, (AttributeSet) null);
        init(context, (AttributeSet) null);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        init(context, attributeSet);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, (Paint) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.width = (float) getWidth();
        this.height = (float) getHeight();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.width >= 12.0f && this.height > 12.0f) {
            Path path = new Path();
            path.moveTo(12.0f, 0.0f);
            path.lineTo(this.width - 12.0f, 0.0f);
            path.quadTo(this.width, 0.0f, this.width, 12.0f);
            path.lineTo(this.width, this.height - 12.0f);
            path.quadTo(this.width, this.height, this.width - 12.0f, this.height);
            path.lineTo(12.0f, this.height);
            path.quadTo(0.0f, this.height, 0.0f, this.height - 12.0f);
            path.lineTo(0.0f, 12.0f);
            path.quadTo(0.0f, 0.0f, 12.0f, 0.0f);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
