package cn.xports.pay;

import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.http.SodaService;
import cn.xports.parser.WeChatPayParser;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcn/xports/parser/WeChatPayParser;", "kotlin.jvm.PlatformType", "it", "Lcn/xports/baselib/bean/Response;", "apply", "cn/xports/pay/PayPresenter$callWexin$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
final class PayPresenter$callWexin$$inlined$apply$lambda$1<T, R> implements Function<Response, Observable<WeChatPayParser>> {
    final /* synthetic */ int $cash$inlined;
    final /* synthetic */ int $couponMoney$inlined;
    final /* synthetic */ String $title$inlined;
    final /* synthetic */ int $totalFee$inlined;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ PayPresenter this$0;

    PayPresenter$callWexin$$inlined$apply$lambda$1(PayPresenter payPresenter, String str, int i, int i2, int i3, String str2) {
        this.this$0 = payPresenter;
        this.$title$inlined = str;
        this.$totalFee$inlined = i;
        this.$couponMoney$inlined = i2;
        this.$cash$inlined = i3;
        this.$tradeId$inlined = str2;
    }

    public final Observable<WeChatPayParser> apply(@NotNull Response response) {
        Intrinsics.checkParameterIsNotNull(response, "it");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("accountId", this.this$0.acountId);
        return ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getWeChatSign(this.$title$inlined, jSONObject.toString(), (this.$totalFee$inlined - this.$couponMoney$inlined) + this.$cash$inlined, this.$tradeId$inlined);
    }
}
