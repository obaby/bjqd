package cn.xports.qd2.coupons;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CouponGetActivity.kt */
final class CouponGetActivity$initView$4 implements View.OnClickListener {
    final /* synthetic */ CouponGetActivity this$0;

    CouponGetActivity$initView$4(CouponGetActivity couponGetActivity) {
        this.this$0 = couponGetActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.checkPhoneNum()) {
            CouponGetPresenter couponGetPresenter = (CouponGetPresenter) this.this$0.getPresenter();
            if (couponGetPresenter != null) {
                EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.etPhoneNum);
                Intrinsics.checkExpressionValueIsNotNull(editText, "etPhoneNum");
                couponGetPresenter.sendVerifyCode(editText.getText().toString());
            }
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvGetCode);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvGetCode");
            textView.setEnabled(false);
            this.this$0.downTime();
        }
    }
}
