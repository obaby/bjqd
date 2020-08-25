package anylife.scrolltextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScrollTextView extends SurfaceView implements SurfaceHolder.Callback {
    private final String TAG;
    private boolean clickEnable;
    boolean isDestroy;
    public boolean isHorizontal;
    boolean isScrollForever;
    boolean isSetNewText;
    /* access modifiers changed from: private */
    public int needScrollTimes;
    private Paint paint;
    /* access modifiers changed from: private */
    public boolean pauseScroll;
    private ScheduledExecutorService scheduledExecutorService;
    /* access modifiers changed from: private */
    public int speed;
    /* access modifiers changed from: private */
    public boolean stopScroll;
    private SurfaceHolder surfaceHolder;
    private String text;
    private int textColor;
    private float textSize;
    private float textWidth;
    /* access modifiers changed from: private */
    public float textX;
    /* access modifiers changed from: private */
    public float textY;
    private int viewHeight;
    /* access modifiers changed from: private */
    public int viewWidth;
    /* access modifiers changed from: private */
    public float viewWidth_plus_textLength;

    static /* synthetic */ int access$906(ScrollTextView scrollTextView) {
        int i = scrollTextView.needScrollTimes - 1;
        scrollTextView.needScrollTimes = i;
        return i;
    }

    public ScrollTextView(Context context) {
        super(context);
        this.TAG = "ScrollTextView";
        this.paint = null;
        this.stopScroll = false;
        this.pauseScroll = false;
        this.clickEnable = false;
        this.isHorizontal = true;
        this.speed = 1;
        this.text = "";
        this.textSize = 15.0f;
        this.needScrollTimes = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.viewWidth = 0;
        this.viewHeight = 0;
        this.textWidth = 0.0f;
        this.textX = 0.0f;
        this.textY = 0.0f;
        this.viewWidth_plus_textLength = 0.0f;
        this.isSetNewText = false;
        this.isScrollForever = true;
        this.isDestroy = false;
    }

    public ScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "ScrollTextView";
        this.paint = null;
        this.stopScroll = false;
        this.pauseScroll = false;
        this.clickEnable = false;
        this.isHorizontal = true;
        this.speed = 1;
        this.text = "";
        this.textSize = 15.0f;
        this.needScrollTimes = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.viewWidth = 0;
        this.viewHeight = 0;
        this.textWidth = 0.0f;
        this.textX = 0.0f;
        this.textY = 0.0f;
        this.viewWidth_plus_textLength = 0.0f;
        this.isSetNewText = false;
        this.isScrollForever = true;
        this.isDestroy = false;
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.paint = new Paint();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ScrollTextViewLib);
        this.clickEnable = obtainStyledAttributes.getBoolean(R.styleable.ScrollTextViewLib_st_clickEnable, this.clickEnable);
        this.isHorizontal = obtainStyledAttributes.getBoolean(R.styleable.ScrollTextViewLib_st_isHorizontal, this.isHorizontal);
        this.speed = obtainStyledAttributes.getInteger(R.styleable.ScrollTextViewLib_st_speed, this.speed);
        this.text = obtainStyledAttributes.getString(R.styleable.ScrollTextViewLib_st_text);
        this.textColor = obtainStyledAttributes.getColor(R.styleable.ScrollTextViewLib_st_text_color, ViewCompat.MEASURED_STATE_MASK);
        this.textSize = obtainStyledAttributes.getDimension(R.styleable.ScrollTextViewLib_st_text_size, this.textSize);
        this.needScrollTimes = obtainStyledAttributes.getInteger(R.styleable.ScrollTextViewLib_st_times, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        this.isScrollForever = obtainStyledAttributes.getBoolean(R.styleable.ScrollTextViewLib_st_isScrollForever, true);
        this.paint.setColor(this.textColor);
        this.paint.setTextSize(this.textSize);
        setZOrderOnTop(true);
        getHolder().setFormat(-3);
        setFocusable(true);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int fontHeight = getFontHeight(this.textSize);
        this.viewWidth = View.MeasureSpec.getSize(i);
        this.viewHeight = View.MeasureSpec.getSize(i2);
        if (getLayoutParams().width == -2 && getLayoutParams().height == -2) {
            setMeasuredDimension(this.viewWidth, fontHeight);
            this.viewHeight = fontHeight;
        } else if (getLayoutParams().width == -2) {
            setMeasuredDimension(this.viewWidth, this.viewHeight);
        } else if (getLayoutParams().height == -2) {
            setMeasuredDimension(this.viewWidth, fontHeight);
            this.viewHeight = fontHeight;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder2, int i, int i2, int i3) {
        Log.d("ScrollTextView", "arg0:" + surfaceHolder2.toString() + "  arg1:" + i + "  arg2:" + i2 + "  arg3:" + i3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder2) {
        if (!this.isDestroy) {
            this.stopScroll = false;
            this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            this.scheduledExecutorService.scheduleAtFixedRate(new ScrollTextThread(), 100, 100, TimeUnit.MILLISECONDS);
            Log.d("ScrollTextView", "ScrollTextTextView is created");
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder2) {
        this.stopScroll = true;
        this.scheduledExecutorService.shutdownNow();
        Log.d("ScrollTextView", "ScrollTextTextView is destroyed");
    }

    public void destroy() {
        this.isDestroy = true;
    }

    private int getFontHeight(float f) {
        Paint paint2 = new Paint();
        paint2.setTextSize(f);
        Paint.FontMetrics fontMetrics = paint2.getFontMetrics();
        return (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
    }

    public void setTimes(int i) {
        if (i > 0) {
            this.needScrollTimes = i;
            this.isScrollForever = false;
            return;
        }
        throw new IllegalArgumentException("times was invalid integer, it must between > 0");
    }

    public void setHorizontal(boolean z) {
        this.isHorizontal = z;
    }

    public void setText(String str) {
        this.isSetNewText = true;
        this.stopScroll = false;
        this.text = str;
        measureVarious();
    }

    public void setTextColor(@ColorInt int i) {
        this.textColor = i;
        this.paint.setColor(this.textColor);
    }

    public void setSpeed(int i) {
        if (i > 10 || i < 0) {
            throw new IllegalArgumentException("Speed was invalid integer, it must between 0 and 10");
        }
        this.speed = i;
    }

    public void setScrollForever(boolean z) {
        this.isScrollForever = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.clickEnable && motionEvent.getAction() == 0) {
            this.pauseScroll = !this.pauseScroll;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void drawVerticalScroll() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= this.text.length()) {
                break;
            }
            while (this.paint.measureText(this.text.substring(i2, i)) < ((float) this.viewWidth) && i < this.text.length()) {
                i++;
            }
            if (i == this.text.length()) {
                arrayList.add(this.text.substring(i2, i));
                break;
            }
            i--;
            arrayList.add(this.text.substring(i2, i));
            i2 = i;
        }
        float f = this.paint.getFontMetrics().bottom - this.paint.getFontMetrics().top;
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        float f2 = ((float) (this.viewHeight / 2)) + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            float f3 = ((float) this.viewHeight) + f;
            while (f3 > (-f)) {
                if (!this.stopScroll && !this.isSetNewText) {
                    if (this.pauseScroll) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Log.e("ScrollTextView", e.toString());
                        }
                    } else {
                        Canvas lockCanvas = this.surfaceHolder.lockCanvas();
                        lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                        lockCanvas.drawText((String) arrayList.get(i3), 0.0f, f3, this.paint);
                        this.surfaceHolder.unlockCanvasAndPost(lockCanvas);
                        float f4 = f3 - f2;
                        if (f4 < 4.0f && f4 > 0.0f) {
                            if (!this.stopScroll) {
                                try {
                                    Thread.sleep((long) (this.speed * 1000));
                                } catch (InterruptedException e2) {
                                    Log.e("ScrollTextView", e2.toString());
                                }
                            } else {
                                return;
                            }
                        }
                    }
                    f3 -= 3.0f;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void draw(float f, float f2) {
        Canvas lockCanvas = this.surfaceHolder.lockCanvas();
        lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        lockCanvas.drawText(this.text, f, f2, this.paint);
        this.surfaceHolder.unlockCanvasAndPost(lockCanvas);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        setVisibility(i);
    }

    /* access modifiers changed from: private */
    public void measureVarious() {
        this.textWidth = this.paint.measureText(this.text);
        this.viewWidth_plus_textLength = ((float) this.viewWidth) + this.textWidth;
        this.textX = (float) (this.viewWidth - (this.viewWidth / 5));
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        this.textY = ((float) (this.viewHeight / 2)) + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom);
    }

    class ScrollTextThread implements Runnable {
        ScrollTextThread() {
        }

        public void run() {
            ScrollTextView.this.measureVarious();
            while (!ScrollTextView.this.stopScroll) {
                if (!ScrollTextView.this.isHorizontal) {
                    ScrollTextView.this.drawVerticalScroll();
                    ScrollTextView.this.isSetNewText = false;
                    ScrollTextView.access$906(ScrollTextView.this);
                } else if (ScrollTextView.this.pauseScroll) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Log.e("ScrollTextView", e.toString());
                    }
                } else {
                    ScrollTextView.this.draw(((float) ScrollTextView.this.viewWidth) - ScrollTextView.this.textX, ScrollTextView.this.textY);
                    float unused = ScrollTextView.this.textX = ScrollTextView.this.textX + ((float) ScrollTextView.this.speed);
                    if (ScrollTextView.this.textX > ScrollTextView.this.viewWidth_plus_textLength) {
                        float unused2 = ScrollTextView.this.textX = 0.0f;
                        ScrollTextView.access$906(ScrollTextView.this);
                    }
                }
                if (ScrollTextView.this.needScrollTimes <= 0 && ScrollTextView.this.isScrollForever) {
                    boolean unused3 = ScrollTextView.this.stopScroll = true;
                }
            }
        }
    }
}
