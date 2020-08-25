package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ActivityChooserView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Field;
import java.util.List;

public abstract class NoLimitPagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private boolean isNoLimit = false;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private ViewPager mViewPager;
    private List<T> models;

    public interface OnItemClickListener {
        void OnItemClick(View view);
    }

    /* access modifiers changed from: protected */
    public abstract void fillItem(View view, T t, int i);

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    public NoLimitPagerAdapter(List<T> list) {
        this.models = list;
    }

    public NoLimitPagerAdapter(List<T> list, boolean z) {
        this.models = list;
        this.isNoLimit = z;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        if (this.models == null || this.models.size() == 0) {
            return 0;
        }
        return !this.isNoLimit ? this.models.size() : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        int size = i % this.models.size();
        View inflateItemView = inflateItemView();
        fillItem(inflateItemView, this.models.get(size), size);
        viewGroup.addView(inflateItemView);
        return inflateItemView;
    }

    public void attachViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        viewPager.setOverScrollMode(2);
        setPagerChangeDuration();
    }

    private void setPagerChangeDuration() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, new Scroller(Utils.getApp()) {
                public void startScroll(int i, int i2, int i3, int i4) {
                    super.startScroll(i, i2, i3, i4, 1000);
                }

                public void startScroll(int i, int i2, int i3, int i4, int i5) {
                    super.startScroll(i, i2, i3, i4, 1000);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View inflateItemView() {
        return View.inflate(Utils.getApp(), getLayoutId(), (ViewGroup) null);
    }

    public int getOriginPosition(int i) {
        return i % this.models.size();
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(i % this.models.size(), f, i2);
        }
    }

    public void onPageSelected(int i) {
        int size = i % this.models.size();
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(size);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    public NoLimitPagerAdapter<T> setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
        return this;
    }
}
