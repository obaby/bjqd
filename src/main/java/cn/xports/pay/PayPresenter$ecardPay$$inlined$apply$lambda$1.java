package cn.xports.pay;

import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.BalanceInfo;
import cn.xports.export.EventHandler;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007¸\u0006\u0000"}, d2 = {"cn/xports/pay/PayPresenter$ecardPay$3$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onError", "", "result", "Lorg/json/JSONObject;", "onSuccess", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
public final class PayPresenter$ecardPay$$inlined$apply$lambda$1 extends ResponseJsonWrap {
    final /* synthetic */ BalanceInfo $balanceInfo$inlined;
    final /* synthetic */ JSONArray $payGroup$inlined;
    final /* synthetic */ JSONObject $payInfo$inlined;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ PayPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PayPresenter$ecardPay$$inlined$apply$lambda$1(IView iView, PayPresenter payPresenter, JSONObject jSONObject, BalanceInfo balanceInfo, JSONArray jSONArray, String str) {
        super(iView);
        this.this$0 = payPresenter;
        this.$payInfo$inlined = jSONObject;
        this.$balanceInfo$inlined = balanceInfo;
        this.$payGroup$inlined = jSONArray;
        this.$tradeId$inlined = str;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        EventHandler.getInstance().setPayResult("1");
    }

    public void onError(@Nullable JSONObject jSONObject) {
        super.onError(jSONObject);
        if (jSONObject != null) {
            EventHandler.getInstance().setPayResult(jSONObject.getString(K.message));
        }
    }
}
