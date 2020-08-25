package cn.xports.qd2;

import cn.xports.base.Router;
import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/CardBuyDetailActivity$createOrder$4", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardBuyDetailActivity.kt */
public final class CardBuyDetailActivity$createOrder$4 extends ResponseJsonWrap {
    final /* synthetic */ CardBuyDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardBuyDetailActivity$createOrder$4(CardBuyDetailActivity cardBuyDetailActivity, IView iView) {
        super(iView);
        this.this$0 = cardBuyDetailActivity;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            this.this$0.fromPayBottom = false;
            CardBuyDetailActivity cardBuyDetailActivity = this.this$0;
            cardBuyDetailActivity.startActivity(Router.getIntent("/orderPay?tradeId=" + jSONObject.getString("tradeId")));
        }
    }
}
