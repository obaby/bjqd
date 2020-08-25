package cn.xports.qd2.coupons;

import android.view.View;
import cn.xports.qd2.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CouponListActivity.kt */
final class CouponListActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ CouponListActivity this$0;

    CouponListActivity$initView$2(CouponListActivity couponListActivity) {
        this.this$0 = couponListActivity;
    }

    public final void onClick(View view) {
        this.this$0.dropWindow.show(this.this$0._$_findCachedViewById(R.id.v_line));
    }
}
