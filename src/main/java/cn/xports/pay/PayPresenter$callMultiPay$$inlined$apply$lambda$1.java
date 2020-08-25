package cn.xports.pay;

import cn.xports.base.Constant;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.bean.Response;
import cn.xports.http.SodaService;
import cn.xports.parser.ThirdPayAccountParser;
import com.blankj.utilcode.util.GsonUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcn/xports/baselib/bean/Response;", "kotlin.jvm.PlatformType", "it", "Lcn/xports/parser/ThirdPayAccountParser;", "apply", "cn/xports/pay/PayPresenter$callMultiPay$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
final class PayPresenter$callMultiPay$$inlined$apply$lambda$1<T, R> implements Function<ThirdPayAccountParser, Observable<Response>> {
    final /* synthetic */ int $cash$inlined;
    final /* synthetic */ ArrayList $payGroup$inlined;
    final /* synthetic */ SodaService $this_apply;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ PayPresenter this$0;

    PayPresenter$callMultiPay$$inlined$apply$lambda$1(SodaService sodaService, PayPresenter payPresenter, String str, ArrayList arrayList, int i) {
        this.$this_apply = sodaService;
        this.this$0 = payPresenter;
        this.$tradeId$inlined = str;
        this.$payGroup$inlined = arrayList;
        this.$cash$inlined = i;
    }

    public final Observable<Response> apply(@NotNull ThirdPayAccountParser thirdPayAccountParser) {
        Intrinsics.checkParameterIsNotNull(thirdPayAccountParser, "it");
        JSONObject jSONObject = new JSONObject();
        for (DataMap dataMap : this.$payGroup$inlined) {
            if (Intrinsics.areEqual(dataMap.getString(this.this$0.PAY_MODE), Constant.ALI_PAY)) {
                dataMap.put("thirdAccountId", Integer.valueOf(thirdPayAccountParser.getAlipayAccountId()));
                jSONObject.put("thirdAccountId", thirdPayAccountParser.getAlipayAccountId());
                jSONObject.put("payModeCode", Constant.ALI_PAY);
            } else if (Intrinsics.areEqual(dataMap.getString(this.this$0.PAY_MODE), Constant.WECHAT_PAY)) {
                this.this$0.acountId = thirdPayAccountParser.getWechatAccountId();
                dataMap.put("thirdAccountId", Integer.valueOf(thirdPayAccountParser.getWechatAccountId()));
                jSONObject.put("thirdAccountId", thirdPayAccountParser.getWechatAccountId());
                jSONObject.put("payModeCode", Constant.WECHAT_PAY);
            }
        }
        jSONObject.put(this.this$0.CASH, this.$cash$inlined);
        if (this.$cash$inlined == 0) {
            return this.$this_apply.saveTradeAttach(GsonUtils.toJson(this.$payGroup$inlined), "", this.$tradeId$inlined);
        }
        return this.$this_apply.saveTradeAttach(GsonUtils.toJson(this.$payGroup$inlined), jSONObject.toString(), this.$tradeId$inlined);
    }
}
