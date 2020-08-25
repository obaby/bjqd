package cn.xports.qd2.coupons;

import android.widget.CompoundButton;
import cn.xports.qd2.R;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.ColorUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 15})
/* compiled from: CouponGetActivity.kt */
final class CouponGetActivity$initView$3 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ CouponGetActivity this$0;

    CouponGetActivity$initView$3(CouponGetActivity couponGetActivity) {
        this.this$0 = couponGetActivity;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctGetCoupon)).setBgColor(ColorUtils.getColor(R.color.blue_2e6));
        } else {
            ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctGetCoupon)).setBgColor(ColorUtils.getColor(R.color.gray_d9d));
        }
        CornerTextView cornerTextView = (CornerTextView) this.this$0._$_findCachedViewById(R.id.ctGetCoupon);
        Intrinsics.checkExpressionValueIsNotNull(cornerTextView, "ctGetCoupon");
        cornerTextView.setEnabled(z);
    }
}
