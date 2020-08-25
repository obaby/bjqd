package cn.xports.order;

import android.content.Intent;
import cn.xports.base.GlobalDialog;
import cn.xports.order.OrderListAdapter;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/order/OrderListActivity$initView$2", "Lcn/xports/order/OrderListAdapter$OnPayOption;", "onCancelPay", "", "tradeId", "", "onPayClick", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderListActivity.kt */
public final class OrderListActivity$initView$2 implements OrderListAdapter.OnPayOption {
    final /* synthetic */ OrderListActivity this$0;

    OrderListActivity$initView$2(OrderListActivity orderListActivity) {
        this.this$0 = orderListActivity;
    }

    public void onPayClick(@Nullable String str) {
        this.this$0.startActivity(new Intent(this.this$0, OrderPayActivity.class).putExtra("tradeId", str));
    }

    public void onCancelPay(@Nullable String str) {
        new GlobalDialog(this.this$0, "确定取消订单？").setButtonClick(new OrderListActivity$initView$2$onCancelPay$1(this, str));
    }
}
