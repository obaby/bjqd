package cn.xports.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"cn/xports/order/OrderListActivity$initView$1", "Landroid/support/v7/widget/RecyclerView$OnScrollListener;", "onScrolled", "", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "dx", "", "dy", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderListActivity.kt */
public final class OrderListActivity$initView$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ LinearLayoutManager $layotManager;
    final /* synthetic */ OrderListActivity this$0;

    OrderListActivity$initView$1(OrderListActivity orderListActivity, LinearLayoutManager linearLayoutManager) {
        this.this$0 = orderListActivity;
        this.$layotManager = linearLayoutManager;
    }

    public void onScrolled(@NotNull RecyclerView recyclerView, int i, int i2) {
        OrderPresenter orderPresenter;
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        if (this.this$0.mNextPage != 0 && this.$layotManager.getItemCount() - 1 == this.$layotManager.findLastVisibleItemPosition() && (orderPresenter = (OrderPresenter) this.this$0.getPresenter()) != null) {
            OrderListActivity orderListActivity = this.this$0;
            orderListActivity.pageNo = orderListActivity.pageNo + 1;
            orderPresenter.getOrderList(orderListActivity.pageNo, this.this$0.orderState);
        }
    }
}
