package cn.xports.order;

import cn.xports.baselib.bean.Response;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.order.OrderPayContract;
import cn.xports.parser.AliPaySignParser;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005¨\u0006\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcn/xports/parser/AliPaySignParser;", "it", "Lcn/xports/baselib/bean/Response;", "apply", "cn/xports/order/OrderPayPresenter$callAli$1$1$1", "cn/xports/order/OrderPayPresenter$$special$$inlined$apply$lambda$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
final class OrderPayPresenter$callAli$$inlined$apply$lambda$1<T, R> implements Function<Response, Observable<AliPaySignParser>> {
    final /* synthetic */ int $cash$inlined;
    final /* synthetic */ OrderPayContract.Model $this_apply$inlined;
    final /* synthetic */ int $totalFee$inlined;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ OrderPayPresenter this$0;

    OrderPayPresenter$callAli$$inlined$apply$lambda$1(OrderPayContract.Model model, OrderPayPresenter orderPayPresenter, String str, int i, int i2) {
        this.$this_apply$inlined = model;
        this.this$0 = orderPayPresenter;
        this.$tradeId$inlined = str;
        this.$totalFee$inlined = i;
        this.$cash$inlined = i2;
    }

    @NotNull
    public final Observable<AliPaySignParser> apply(@NotNull Response response) {
        Intrinsics.checkParameterIsNotNull(response, "it");
        OrderPayContract.Model model = this.$this_apply$inlined;
        String cent2Yuan = MoneyUtil.cent2Yuan(this.$totalFee$inlined + this.$cash$inlined);
        Intrinsics.checkExpressionValueIsNotNull(cent2Yuan, "MoneyUtil.cent2Yuan(totalFee + cash)");
        return model.getAliPayOrder("场地票/次票销售", "场地次票", cent2Yuan, this.$tradeId$inlined);
    }
}
