package cn.xports.order;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.order.OrderContract;
import cn.xports.parser.OrderListParser;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n¸\u0006\u0000"}, d2 = {"cn/xports/order/OrderPresenter$getOrderList$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/OrderListParser;", "next", "", "value", "onComplete", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPresenter.kt */
public final class OrderPresenter$getOrderList$$inlined$apply$lambda$1 extends ProcessObserver<OrderListParser> {
    final /* synthetic */ int $page$inlined;
    final /* synthetic */ String $state$inlined;
    final /* synthetic */ OrderPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OrderPresenter$getOrderList$$inlined$apply$lambda$1(String str, OrderPresenter orderPresenter, int i, String str2) {
        super(str);
        this.this$0 = orderPresenter;
        this.$page$inlined = i;
        this.$state$inlined = str2;
    }

    public void next(@Nullable OrderListParser orderListParser) {
        OrderContract.View view = (OrderContract.View) this.this$0.getRootView();
        if (view != null) {
            view.showOrderList(orderListParser);
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        OrderContract.View view = (OrderContract.View) this.this$0.getRootView();
        if (view != null) {
            view.hideLoading();
        }
        OrderContract.View view2 = (OrderContract.View) this.this$0.getRootView();
        if (view2 != null) {
            view2.showOrderList((OrderListParser) null);
        }
    }

    public void onComplete() {
        super.onComplete();
        OrderContract.View view = (OrderContract.View) this.this$0.getRootView();
        if (view != null) {
            view.hideLoading();
        }
    }
}
