package cn.xports.qd2;

import android.support.v7.widget.GridLayoutManager;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"cn/xports/qd2/CardRechargeActivity$initView$1", "Landroid/support/v7/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSize", "", "position", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
public final class CardRechargeActivity$initView$1 extends GridLayoutManager.SpanSizeLookup {
    final /* synthetic */ CardRechargeActivity this$0;

    CardRechargeActivity$initView$1(CardRechargeActivity cardRechargeActivity) {
        this.this$0 = cardRechargeActivity;
    }

    public int getSpanSize(int i) {
        return this.this$0.mItems.get(i) instanceof String ? 3 : 1;
    }
}
