package cn.xports.order;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.qdplugin.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderListActivity.kt */
final class OrderListActivity$initView$4 implements View.OnClickListener {
    final /* synthetic */ OrderListActivity this$0;

    OrderListActivity$initView$4(OrderListActivity orderListActivity) {
        this.this$0 = orderListActivity;
    }

    public final void onClick(View view) {
        RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvMyOrder);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMyOrder");
        if (recyclerView.getScrollState() == 2) {
            ((RecyclerView) this.this$0._$_findCachedViewById(R.id.rvMyOrder)).stopScroll();
        }
        this.this$0.orderState = "2";
        this.this$0.refresh();
        ((TextView) this.this$0._$_findCachedViewById(R.id.tvToPay)).setTextColor(this.this$0.getResources().getColor(R.color.blue_2e6));
        View _$_findCachedViewById = this.this$0._$_findCachedViewById(R.id.v_to_pay);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "v_to_pay");
        _$_findCachedViewById.setVisibility(0);
        ((TextView) this.this$0._$_findCachedViewById(R.id.tvAll)).setTextColor(this.this$0.getResources().getColor(R.color.gray_888));
        View _$_findCachedViewById2 = this.this$0._$_findCachedViewById(R.id.v_all);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "v_all");
        _$_findCachedViewById2.setVisibility(4);
    }
}
