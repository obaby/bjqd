package cn.xports.qd2.credit;

import android.support.v4.view.ViewPager;
import cn.xports.baselib.bean.DataMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u000b"}, d2 = {"cn/xports/qd2/credit/MyCreditActivity$listener$1", "Landroid/support/v4/view/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "p0", "", "onPageScrolled", "p1", "", "p2", "onPageSelected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCreditActivity.kt */
public final class MyCreditActivity$listener$1 implements ViewPager.OnPageChangeListener {
    final /* synthetic */ MyCreditActivity this$0;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    MyCreditActivity$listener$1(MyCreditActivity myCreditActivity) {
        this.this$0 = myCreditActivity;
    }

    public void onPageSelected(int i) {
        MyCreditActivity myCreditActivity = this.this$0;
        Object obj = this.this$0.cardList.get(i);
        Intrinsics.checkExpressionValueIsNotNull(obj, "cardList[p0]");
        myCreditActivity.getCreditDetails((DataMap) obj);
    }
}
