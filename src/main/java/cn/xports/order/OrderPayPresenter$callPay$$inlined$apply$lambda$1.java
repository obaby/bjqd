package cn.xports.order;

import cn.xports.base.Constant;
import cn.xports.baselib.bean.Response;
import cn.xports.order.OrderPayContract;
import cn.xports.parser.ThirdPayAccountParser;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcn/xports/baselib/bean/Response;", "it", "Lcn/xports/parser/ThirdPayAccountParser;", "apply", "cn/xports/order/OrderPayPresenter$callPay$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
final class OrderPayPresenter$callPay$$inlined$apply$lambda$1<T, R> implements Function<ThirdPayAccountParser, Observable<Response>> {
    final /* synthetic */ int $cash$inlined;
    final /* synthetic */ String $payMode$inlined;
    final /* synthetic */ OrderPayContract.Model $this_apply;
    final /* synthetic */ int $totalFee$inlined;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ OrderPayPresenter this$0;

    OrderPayPresenter$callPay$$inlined$apply$lambda$1(OrderPayContract.Model model, OrderPayPresenter orderPayPresenter, String str, String str2, int i, int i2) {
        this.$this_apply = model;
        this.this$0 = orderPayPresenter;
        this.$tradeId$inlined = str;
        this.$payMode$inlined = str2;
        this.$totalFee$inlined = i;
        this.$cash$inlined = i2;
    }

    @NotNull
    public final Observable<Response> apply(@NotNull ThirdPayAccountParser thirdPayAccountParser) {
        Intrinsics.checkParameterIsNotNull(thirdPayAccountParser, "it");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(this.this$0.PAY_MODE, this.$payMode$inlined);
        jSONObject2.put(this.this$0.PAY_MONEY, this.$totalFee$inlined);
        jSONObject2.put(this.this$0.REAL_PAY, this.$totalFee$inlined);
        if (Intrinsics.areEqual(this.$payMode$inlined, Constant.ALI_PAY)) {
            jSONObject2.put("thirdAccountId", thirdPayAccountParser.getAlipayAccountId());
            jSONObject.put("thirdAccountId", thirdPayAccountParser.getAlipayAccountId());
            jSONObject.put("payModeCode", Constant.ALI_PAY);
        } else {
            this.this$0.acountId = thirdPayAccountParser.getWechatAccountId();
            jSONObject2.put("thirdAccountId", thirdPayAccountParser.getWechatAccountId());
            jSONObject.put("thirdAccountId", thirdPayAccountParser.getWechatAccountId());
            jSONObject.put("payModeCode", Constant.WECHAT_PAY);
        }
        jSONArray.put(jSONObject2);
        jSONObject.put(this.this$0.CASH, this.$cash$inlined);
        OrderPayContract.Model model = this.$this_apply;
        String jSONArray2 = jSONArray.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONArray2, "payGroup.toString()");
        String jSONObject3 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject3, "cashInfo.toString()");
        return model.saveTradeAttach(jSONArray2, jSONObject3, this.$tradeId$inlined);
    }
}
