package cn.qqtheme.framework.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.qqtheme.framework.util.LogUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends ScrollView {
    private static final int DELAY = 50;
    public static final int LINE_COLOR = -8139290;
    public static final int OFF_SET = 1;
    public static final int TEXT_COLOR_FOCUS = -16611122;
    public static final int TEXT_COLOR_NORMAL = -4473925;
    public static final int TEXT_SIZE = 20;
    /* access modifiers changed from: private */
    public Context context;
    private int displayItemCount;
    /* access modifiers changed from: private */
    public int initialY;
    private boolean isUserScroll = false;
    /* access modifiers changed from: private */
    public int itemHeight = 0;
    private LinkedList<String> items = new LinkedList<>();
    /* access modifiers changed from: private */
    public int lineColor = LINE_COLOR;
    /* access modifiers changed from: private */
    public boolean lineVisible = true;
    /* access modifiers changed from: private */
    public int offset = 1;
    private OnWheelViewListener onWheelViewListener;
    /* access modifiers changed from: private */
    public Paint paint;
    private float previousY = 0.0f;
    private Runnable scrollerTask = new ScrollerTask();
    /* access modifiers changed from: private */
    public int[] selectedAreaBorder;
    /* access modifiers changed from: private */
    public int selectedIndex = 1;
    private int textColorFocus = TEXT_COLOR_FOCUS;
    private int textColorNormal = TEXT_COLOR_NORMAL;
    private int textSize = 20;
    /* access modifiers changed from: private */
    public int viewWidth;
    private LinearLayout views;

    public interface OnWheelViewListener {
        void onSelected(boolean z, int i, String str);
    }

    public WheelView(Context context2) {
        super(context2);
        init(context2);
    }

    public WheelView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        init(context2);
    }

    public WheelView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        init(context2);
    }

    private void init(Context context2) {
        this.context = context2;
        setFadingEdgeLength(0);
        if (Build.VERSION.SDK_INT >= 9) {
            setOverScrollMode(2);
        }
        setVerticalScrollBarEnabled(false);
        this.views = new LinearLayout(context2);
        this.views.setOrientation(1);
        addView(this.views);
    }

    /* access modifiers changed from: private */
    public void startScrollerTask() {
        this.initialY = getScrollY();
        postDelayed(this.scrollerTask, 50);
    }

    private void initData() {
        this.displayItemCount = (this.offset * 2) + 1;
        this.views.removeAllViews();
        Iterator it = this.items.iterator();
        while (it.hasNext()) {
            this.views.addView(createView((String) it.next()));
        }
        refreshItemView(this.itemHeight * (this.selectedIndex - this.offset));
    }

    private TextView createView(String str) {
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setText(str);
        textView.setTextSize((float) this.textSize);
        textView.setGravity(17);
        int dip2px = dip2px(15.0f);
        textView.setPadding(dip2px, dip2px, dip2px, dip2px);
        if (this.itemHeight == 0) {
            this.itemHeight = getViewMeasuredHeight(textView);
            LogUtils.debug((Object) this, "itemHeight: " + this.itemHeight);
            this.views.setLayoutParams(new FrameLayout.LayoutParams(-1, this.itemHeight * this.displayItemCount));
            setLayoutParams(new LinearLayout.LayoutParams(((LinearLayout.LayoutParams) getLayoutParams()).width, this.itemHeight * this.displayItemCount));
        }
        return textView;
    }

    private void refreshItemView(int i) {
        int i2 = (i / this.itemHeight) + this.offset;
        int i3 = i % this.itemHeight;
        int i4 = i / this.itemHeight;
        if (i3 == 0) {
            i2 = this.offset + i4;
        } else if (i3 > this.itemHeight / 2) {
            i2 = i4 + this.offset + 1;
        }
        int childCount = this.views.getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            TextView textView = (TextView) this.views.getChildAt(i5);
            if (textView != null) {
                if (i2 == i5) {
                    textView.setTextColor(this.textColorFocus);
                } else {
                    textView.setTextColor(this.textColorNormal);
                }
                i5++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onSelectedCallBack() {
        if (this.onWheelViewListener != null) {
            int i = this.selectedIndex - this.offset;
            LogUtils.verbose("isUserScroll=" + this.isUserScroll + ",selectedIndex=" + this.selectedIndex + ",realIndex=" + i);
            this.onWheelViewListener.onSelected(this.isUserScroll, i, this.items.get(this.selectedIndex));
        }
    }

    /* access modifiers changed from: private */
    public int dip2px(float f) {
        return (int) ((f * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int getViewMeasuredHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        return view.getMeasuredHeight();
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(new LineDrawable());
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        refreshItemView(i2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        LogUtils.debug((Object) this, "w: " + i + ", h: " + i2 + ", oldw: " + i3 + ", oldh: " + i4);
        this.viewWidth = i;
        setBackgroundDrawable((Drawable) null);
    }

    public void fling(int i) {
        super.fling(i / 3);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.isUserScroll = true;
        switch (motionEvent.getAction()) {
            case 0:
                this.previousY = motionEvent.getY();
                break;
            case 1:
                LogUtils.debug((Object) this, String.format("items=%s, offset=%s", new Object[]{Integer.valueOf(this.items.size()), Integer.valueOf(this.offset)}));
                LogUtils.debug((Object) this, "selectedIndex=" + this.selectedIndex);
                float y = motionEvent.getY() - this.previousY;
                LogUtils.debug((Object) this, "delta=" + y);
                if (this.selectedIndex != this.offset || y <= 0.0f) {
                    if (this.selectedIndex == (this.items.size() - this.offset) - 1 && y < 0.0f) {
                        setSelectedIndex(0);
                        break;
                    } else {
                        startScrollerTask();
                        break;
                    }
                } else {
                    setSelectedIndex((this.items.size() - (this.offset * 2)) - 1);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void _setItems(List<String> list) {
        this.items.clear();
        this.items.addAll(list);
        for (int i = 0; i < this.offset; i++) {
            this.items.addFirst("");
            this.items.addLast("");
        }
        initData();
    }

    public void setItems(List<String> list) {
        _setItems(list);
        setSelectedIndex(0);
    }

    public void setItems(List<String> list, int i) {
        _setItems(list);
        setSelectedIndex(i);
    }

    public void setItems(List<String> list, String str) {
        _setItems(list);
        setSelectedItem(str);
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }

    public int getTextColor() {
        return this.textColorFocus;
    }

    public void setTextColor(@ColorInt int i, @ColorInt int i2) {
        this.textColorNormal = i;
        this.textColorFocus = i2;
    }

    public void setTextColor(@ColorInt int i) {
        this.textColorFocus = i;
    }

    public boolean isLineVisible() {
        return this.lineVisible;
    }

    public void setLineVisible(boolean z) {
        this.lineVisible = z;
    }

    public int getLineColor() {
        return this.lineColor;
    }

    public void setLineColor(@ColorInt int i) {
        this.lineColor = i;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(@IntRange(from = 1, to = 4) int i) {
        if (i < 1 || i > 4) {
            throw new IllegalArgumentException("Offset must between 1 and 4");
        }
        this.offset = i;
    }

    private void setSelectedIndex(@IntRange(from = 0) final int i) {
        this.isUserScroll = false;
        post(new Runnable() {
            public void run() {
                WheelView.this.smoothScrollTo(0, i * WheelView.this.itemHeight);
                int unused = WheelView.this.selectedIndex = i + WheelView.this.offset;
                WheelView.this.onSelectedCallBack();
            }
        });
    }

    public void setSelectedItem(String str) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).equals(str)) {
                setSelectedIndex(i - this.offset);
                return;
            }
        }
    }

    public String getSelectedItem() {
        return this.items.get(this.selectedIndex);
    }

    public int getSelectedIndex() {
        return this.selectedIndex - this.offset;
    }

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener2) {
        this.onWheelViewListener = onWheelViewListener2;
    }

    private class ScrollerTask implements Runnable {
        private ScrollerTask() {
        }

        public void run() {
            if (WheelView.this.itemHeight == 0) {
                LogUtils.debug((Object) this, "itemHeight is zero");
                return;
            }
            if (WheelView.this.initialY - WheelView.this.getScrollY() == 0) {
                final int access$500 = WheelView.this.initialY % WheelView.this.itemHeight;
                final int access$5002 = WheelView.this.initialY / WheelView.this.itemHeight;
                LogUtils.debug((Object) this, "initialY: " + WheelView.this.initialY + ", remainder: " + access$500 + ", divided: " + access$5002);
                if (access$500 == 0) {
                    int unused = WheelView.this.selectedIndex = access$5002 + WheelView.this.offset;
                    WheelView.this.onSelectedCallBack();
                } else if (access$500 > WheelView.this.itemHeight / 2) {
                    WheelView.this.post(new Runnable() {
                        public void run() {
                            WheelView.this.smoothScrollTo(0, (WheelView.this.initialY - access$500) + WheelView.this.itemHeight);
                            int unused = WheelView.this.selectedIndex = access$5002 + WheelView.this.offset + 1;
                            WheelView.this.onSelectedCallBack();
                        }
                    });
                } else {
                    WheelView.this.post(new Runnable() {
                        public void run() {
                            WheelView.this.smoothScrollTo(0, WheelView.this.initialY - access$500);
                            int unused = WheelView.this.selectedIndex = access$5002 + WheelView.this.offset;
                            WheelView.this.onSelectedCallBack();
                        }
                    });
                }
            } else {
                WheelView.this.startScrollerTask();
            }
        }
    }

    private class LineDrawable extends Drawable {
        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }

        public LineDrawable() {
            if (WheelView.this.viewWidth == 0) {
                int unused = WheelView.this.viewWidth = ((Activity) WheelView.this.context).getWindowManager().getDefaultDisplay().getWidth();
                LogUtils.debug((Object) this, "viewWidth: " + WheelView.this.viewWidth);
            }
            if (WheelView.this.lineVisible && WheelView.this.paint == null) {
                Paint unused2 = WheelView.this.paint = new Paint();
                WheelView.this.paint.setColor(WheelView.this.lineColor);
                WheelView.this.paint.setStrokeWidth((float) WheelView.this.dip2px(1.0f));
            }
        }

        public void draw(Canvas canvas) {
            if (WheelView.this.selectedAreaBorder == null) {
                int[] unused = WheelView.this.selectedAreaBorder = new int[2];
                WheelView.this.selectedAreaBorder[0] = WheelView.this.itemHeight * WheelView.this.offset;
                WheelView.this.selectedAreaBorder[1] = WheelView.this.itemHeight * (WheelView.this.offset + 1);
            }
            Canvas canvas2 = canvas;
            canvas2.drawLine((float) (WheelView.this.viewWidth / 6), (float) WheelView.this.selectedAreaBorder[0], (float) ((WheelView.this.viewWidth * 5) / 6), (float) WheelView.this.selectedAreaBorder[0], WheelView.this.paint);
            canvas2.drawLine((float) (WheelView.this.viewWidth / 6), (float) WheelView.this.selectedAreaBorder[1], (float) ((WheelView.this.viewWidth * 5) / 6), (float) WheelView.this.selectedAreaBorder[1], WheelView.this.paint);
        }
    }
}
