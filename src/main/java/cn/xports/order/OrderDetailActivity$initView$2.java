package cn.xports.order;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import cn.xports.qdplugin.R;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderDetailActivity.kt */
final class OrderDetailActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ OrderDetailActivity this$0;

    OrderDetailActivity$initView$2(OrderDetailActivity orderDetailActivity) {
        this.this$0 = orderDetailActivity;
    }

    public final void onClick(View view) {
        Button button = (Button) this.this$0._$_findCachedViewById(R.id.btnPayOption);
        Intrinsics.checkExpressionValueIsNotNull(button, "btnPayOption");
        if (Intrinsics.areEqual(button.getText(), "确认退票")) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.this$0.orderItems.iterator();
            while (it.hasNext()) {
                ItemOrderWrap itemOrderWrap = (ItemOrderWrap) it.next();
                Intrinsics.checkExpressionValueIsNotNull(itemOrderWrap, "item");
                if (itemOrderWrap.isSelected()) {
                    arrayList.add(itemOrderWrap.getTicket());
                }
            }
            if (arrayList.size() > 0) {
                OrderPresenter orderPresenter = (OrderPresenter) this.this$0.getPresenter();
                if (orderPresenter != null) {
                    orderPresenter.cancelTickets(arrayList);
                    return;
                }
                return;
            }
            this.this$0.showMsg("请选择相应的票");
            return;
        }
        this.this$0.startActivity(new Intent(this.this$0, OrderPayActivity.class).putExtra("tradeId", this.this$0.tradeId));
    }
}
