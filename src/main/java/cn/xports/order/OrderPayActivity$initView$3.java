package cn.xports.order;

import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.entity.BalanceInfo;
import cn.xports.qdplugin.R;
import cn.xports.widget.PaySelectView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onPaySelect"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
final class OrderPayActivity$initView$3 implements PaySelectView.PaySelectListener {
    final /* synthetic */ OrderPayActivity this$0;

    OrderPayActivity$initView$3(OrderPayActivity orderPayActivity) {
        this.this$0 = orderPayActivity;
    }

    public final void onPaySelect(String str) {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardPayMoney);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCardPayMoney");
        textView.setText("");
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.crCardDiscount);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "crCardDiscount");
        linearLayout.setVisibility(8);
        this.this$0.setTotal(BalanceInfo.shouldPayParse(this.this$0.getCommonPay()));
        this.this$0.cardPayMoney = 0;
        if (!Intrinsics.areEqual(this.this$0.couponNo, "")) {
            OrderPayActivity orderPayActivity = this.this$0;
            Integer intValue = this.this$0.coupon.getIntValue("balance");
            Intrinsics.checkExpressionValueIsNotNull(intValue, "coupon.getIntValue(\"balance\")");
            orderPayActivity.couponMoney = intValue.intValue();
            if (this.this$0.couponMoney >= this.this$0.getTotal()) {
                this.this$0.couponMoney = this.this$0.getTotal();
            }
        }
        this.this$0.setShouldPayText();
        OrderPayActivity orderPayActivity2 = this.this$0;
        PaySelectView paySelectView = (PaySelectView) this.this$0._$_findCachedViewById(R.id.paySelectView);
        Intrinsics.checkExpressionValueIsNotNull(paySelectView, "paySelectView");
        String payMode = paySelectView.getPayMode();
        Intrinsics.checkExpressionValueIsNotNull(payMode, "paySelectView.payMode");
        orderPayActivity2.setPayMode(payMode);
        this.this$0.setSelectCard((BalanceInfo) null);
    }
}
