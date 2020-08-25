package cn.bingoogolapple.bgabanner;

import android.content.Context;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BGAViewPager extends ViewPager {
    private boolean mAllowUserScrollable = true;
    private AutoPlayDelegate mAutoPlayDelegate;

    public interface AutoPlayDelegate {
        void handleAutoPlayActionUpOrCancel(float f);
    }

    public BGAViewPager(Context context) {
        super(context);
    }

    public BGAViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setPageTransformer(boolean z, ViewPager.PageTransformer pageTransformer) {
        Class<ViewPager> cls = ViewPager.class;
        boolean z2 = pageTransformer != null;
        try {
            Field declaredField = cls.getDeclaredField("mPageTransformer");
            declaredField.setAccessible(true);
            boolean z3 = z2 != (((ViewPager.PageTransformer) declaredField.get(this)) != null);
            declaredField.set(this, pageTransformer);
            Method declaredMethod = cls.getDeclaredMethod("setChildrenDrawingOrderEnabledCompat", new Class[]{Boolean.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[]{Boolean.valueOf(z2)});
            Field declaredField2 = cls.getDeclaredField("mDrawingOrder");
            declaredField2.setAccessible(true);
            if (z2) {
                declaredField2.setInt(this, z ? 2 : 1);
            } else {
                declaredField2.setInt(this, 0);
            }
            if (z3) {
                Method declaredMethod2 = cls.getDeclaredMethod("populate", new Class[0]);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(this, new Object[0]);
            }
        } catch (Exception unused) {
        }
    }

    public void setPageChangeDuration(int i) {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, new BGABannerScroller(getContext(), i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBannerCurrentItemInternal(int i, boolean z) {
        Class<ViewPager> cls = ViewPager.class;
        try {
            Method declaredMethod = cls.getDeclaredMethod("setCurrentItemInternal", new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[]{Integer.valueOf(i), Boolean.valueOf(z), true});
            ViewCompat.postInvalidateOnAnimation(this);
        } catch (Exception unused) {
        }
    }

    public void setAllowUserScrollable(boolean z) {
        this.mAllowUserScrollable = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mAllowUserScrollable) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mAllowUserScrollable) {
            return false;
        }
        if (this.mAutoPlayDelegate == null || (motionEvent.getAction() != 3 && motionEvent.getAction() != 1)) {
            return super.onTouchEvent(motionEvent);
        }
        this.mAutoPlayDelegate.handleAutoPlayActionUpOrCancel(getXVelocity());
        return false;
    }

    private float getXVelocity() {
        Class<ViewPager> cls = ViewPager.class;
        try {
            Field declaredField = cls.getDeclaredField("mVelocityTracker");
            declaredField.setAccessible(true);
            VelocityTracker velocityTracker = (VelocityTracker) declaredField.get(this);
            Field declaredField2 = cls.getDeclaredField("mActivePointerId");
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("mMaximumVelocity");
            declaredField3.setAccessible(true);
            velocityTracker.computeCurrentVelocity(1000, (float) declaredField3.getInt(this));
            return VelocityTrackerCompat.getXVelocity(velocityTracker, declaredField2.getInt(this));
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public void setAutoPlayDelegate(AutoPlayDelegate autoPlayDelegate) {
        this.mAutoPlayDelegate = autoPlayDelegate;
    }
}
