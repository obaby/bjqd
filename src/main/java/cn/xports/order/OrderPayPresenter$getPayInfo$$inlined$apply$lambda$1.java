package cn.xports.order;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.order.OrderPayContract;
import cn.xports.parser.OrderPayParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/order/OrderPayPresenter$getPayInfo$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/OrderPayParser;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
public final class OrderPayPresenter$getPayInfo$$inlined$apply$lambda$1 extends ProcessObserver<OrderPayParser> {
    final /* synthetic */ String $ecardNo$inlined;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ OrderPayPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OrderPayPresenter$getPayInfo$$inlined$apply$lambda$1(IView iView, OrderPayPresenter orderPayPresenter, String str, String str2) {
        super(iView);
        this.this$0 = orderPayPresenter;
        this.$tradeId$inlined = str;
        this.$ecardNo$inlined = str2;
    }

    public void next(@Nullable OrderPayParser orderPayParser) {
        OrderPayContract.View view = (OrderPayContract.View) this.this$0.getRootView();
        if (view != null) {
            view.showOrderPayInfo(orderPayParser);
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        OrderPayContract.View view = (OrderPayContract.View) this.this$0.getRootView();
        if (view != null) {
            if (responseThrowable == null) {
                Intrinsics.throwNpe();
            }
            String message = responseThrowable.getMessage();
            if (message == null) {
                message = "";
            }
            view.showMsg(message);
        }
    }
}
