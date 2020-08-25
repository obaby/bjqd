package cn.xports.order;

import android.view.View;
import cn.xports.base.Router;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
final class OrderPayActivity$initView$4 implements View.OnClickListener {
    final /* synthetic */ OrderPayActivity this$0;

    OrderPayActivity$initView$4(OrderPayActivity orderPayActivity) {
        this.this$0 = orderPayActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.getMCash() != 0) {
            this.this$0.showMsg("当前订单只能使用第三方支付");
            return;
        }
        Router.startWithNoAnim(Router.getIntent("/couponPay?tradeId=" + this.this$0.getTradeId()));
    }
}
