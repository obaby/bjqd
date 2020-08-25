package cn.xports.order;

import cn.xports.base.BaseBusModel;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.RxUtil;
import cn.xports.order.OrderContract;
import cn.xports.parser.OrderInfoParser;
import cn.xports.parser.OrderListParser;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\bH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016¨\u0006\u0012"}, d2 = {"Lcn/xports/order/OrderModel;", "Lcn/xports/base/BaseBusModel;", "Lcn/xports/order/OrderContract$Model;", "()V", "cancelPay", "Lio/reactivex/Observable;", "Lcn/xports/baselib/bean/Response;", "tradeId", "", "cancelTickets", "ticketList", "getOrderDetail", "Lcn/xports/parser/OrderInfoParser;", "getOrderList", "Lcn/xports/parser/OrderListParser;", "page", "", "orderState", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderModel.kt */
public final class OrderModel extends BaseBusModel implements OrderContract.Model {
    @NotNull
    public Observable<Response> cancelTickets(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "ticketList");
        Observable<Response> compose = this.service.cancelTickets(str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.cancelTickets(ti…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<OrderListParser> getOrderList(int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "orderState");
        Observable<OrderListParser> compose = this.service.getUserOrders(i, str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getUserOrders(pa…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<OrderInfoParser> getOrderDetail(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Observable<OrderInfoParser> compose = this.service.getOrderInfo(str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getOrderInfo(tra…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<Response> cancelPay(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Observable<Response> compose = this.service.cancelPay(str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.cancelPay(tradeI…pose(applyErrorsWithIO())");
        return compose;
    }
}
