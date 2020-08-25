package cn.xports.order;

import android.support.v4.widget.SwipeRefreshLayout;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onRefresh"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderListActivity.kt */
final class OrderListActivity$initView$6 implements SwipeRefreshLayout.OnRefreshListener {
    final /* synthetic */ OrderListActivity this$0;

    OrderListActivity$initView$6(OrderListActivity orderListActivity) {
        this.this$0 = orderListActivity;
    }

    public final void onRefresh() {
        this.this$0.refresh();
    }
}
