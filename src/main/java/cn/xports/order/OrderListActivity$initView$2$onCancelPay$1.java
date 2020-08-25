package cn.xports.order;

import cn.xports.base.GlobalDialog;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderListActivity.kt */
final class OrderListActivity$initView$2$onCancelPay$1 implements GlobalDialog.OnButtonClick {
    final /* synthetic */ String $tradeId;
    final /* synthetic */ OrderListActivity$initView$2 this$0;

    OrderListActivity$initView$2$onCancelPay$1(OrderListActivity$initView$2 orderListActivity$initView$2, String str) {
        this.this$0 = orderListActivity$initView$2;
        this.$tradeId = str;
    }

    public final void onClick(int i) {
        String str;
        OrderPresenter orderPresenter;
        if (i == 1 && (str = this.$tradeId) != null && (orderPresenter = (OrderPresenter) this.this$0.this$0.getPresenter()) != null) {
            orderPresenter.cancelPay(str);
        }
    }
}
