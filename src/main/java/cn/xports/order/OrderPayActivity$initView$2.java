package cn.xports.order;

import android.view.View;
import cn.xports.base.Router;
import com.blankj.utilcode.util.GsonUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
final class OrderPayActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ OrderPayActivity this$0;

    OrderPayActivity$initView$2(OrderPayActivity orderPayActivity) {
        this.this$0 = orderPayActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.getMCash() != 0) {
            this.this$0.showMsg("当前订单只能使用第三方支付");
            return;
        }
        Collection cardList = this.this$0.getCardList();
        if (cardList == null || cardList.isEmpty()) {
            this.this$0.showMsg("尚未获得卡列表");
            return;
        }
        Map hashMap = new HashMap();
        String tag = this.this$0.getTAG();
        Intrinsics.checkExpressionValueIsNotNull(tag, "TAG");
        hashMap.put("from", tag);
        hashMap.put("tradeId", this.this$0.getTradeId());
        String json = GsonUtils.toJson(this.this$0.getCardList());
        Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(cardList)");
        hashMap.put("cardList", json);
        Router.startWithNoAnim(Router.getIntent(Router.SELECT_CARD_PAY, hashMap));
    }
}
