package cn.xports.qd2.campaign;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u000b"}, d2 = {"cn/xports/qd2/campaign/CampaignListActivity$initView$4", "Landroid/support/v4/view/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "p0", "", "onPageScrolled", "p1", "", "p2", "onPageSelected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CampaignListActivity.kt */
public final class CampaignListActivity$initView$4 implements ViewPager.OnPageChangeListener {
    final /* synthetic */ CampaignListActivity this$0;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    CampaignListActivity$initView$4(CampaignListActivity campaignListActivity) {
        this.this$0 = campaignListActivity;
    }

    public void onPageSelected(int i) {
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llRowRound);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llRowRound");
        if (linearLayout.getChildCount() > 0) {
            View childAt = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llRowRound)).getChildAt(this.this$0.lastPos);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "llRowRound.getChildAt(lastPos)");
            childAt.setEnabled(false);
            this.this$0.lastPos = i;
            View childAt2 = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llRowRound)).getChildAt(this.this$0.lastPos);
            Intrinsics.checkExpressionValueIsNotNull(childAt2, "llRowRound.getChildAt(lastPos)");
            childAt2.setEnabled(true);
        }
    }
}
