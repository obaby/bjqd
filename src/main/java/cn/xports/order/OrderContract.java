package cn.xports.order;

import cn.xports.baselib.bean.Response;
import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import cn.xports.parser.OrderInfoParser;
import cn.xports.parser.OrderListParser;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/order/OrderContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderContract.kt */
public final class OrderContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\u0006H&J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H&¨\u0006\u0010"}, d2 = {"Lcn/xports/order/OrderContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "cancelPay", "Lio/reactivex/Observable;", "Lcn/xports/baselib/bean/Response;", "tradeId", "", "cancelTickets", "ticketList", "getOrderDetail", "Lcn/xports/parser/OrderInfoParser;", "getOrderList", "Lcn/xports/parser/OrderListParser;", "page", "", "orderState", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: OrderContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<Response> cancelPay(@NotNull String str);

        @NotNull
        Observable<Response> cancelTickets(@NotNull String str);

        @NotNull
        Observable<OrderInfoParser> getOrderDetail(@NotNull String str);

        @NotNull
        Observable<OrderListParser> getOrderList(int i, @NotNull String str);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcn/xports/order/OrderContract$View;", "Lcn/xports/baselib/mvp/IView;", "onCancelOrder", "", "msg", "", "showOrderDetail", "infoParser", "Lcn/xports/parser/OrderInfoParser;", "showOrderList", "listParser", "Lcn/xports/parser/OrderListParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: OrderContract.kt */
    public interface View extends IView {
        void onCancelOrder(@NotNull String str);

        void showOrderDetail(@Nullable OrderInfoParser orderInfoParser);

        void showOrderList(@Nullable OrderListParser orderListParser);
    }
}
