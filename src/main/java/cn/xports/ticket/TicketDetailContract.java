package cn.xports.ticket;

import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.OrderInfo;
import cn.xports.parser.TicketDetailParser;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/ticket/TicketDetailContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TicketDetailContract.kt */
public final class TicketDetailContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J.\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H&¨\u0006\u000e"}, d2 = {"Lcn/xports/ticket/TicketDetailContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "getTicketInfo", "Lio/reactivex/Observable;", "Lcn/xports/parser/TicketDetailParser;", "timeId", "", "ticketTypeId", "ticketOrder", "Lcn/xports/entity/OrderInfo;", "ticketInfo", "date", "venueId", "serviceId", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TicketDetailContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<TicketDetailParser> getTicketInfo(@NotNull String str, @NotNull String str2);

        @NotNull
        Observable<OrderInfo> ticketOrder(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcn/xports/ticket/TicketDetailContract$View;", "Lcn/xports/baselib/mvp/IView;", "onGetTicketInfo", "", "value", "Lcn/xports/parser/TicketDetailParser;", "onGetTradeId", "tradeId", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TicketDetailContract.kt */
    public interface View extends IView {
        void onGetTicketInfo(@Nullable TicketDetailParser ticketDetailParser);

        void onGetTradeId(@NotNull String str);
    }
}
