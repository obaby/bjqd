package cn.xports.ticket;

import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.TradeTicket;
import cn.xports.parser.TradeTicketParser;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/ticket/VenueTicketContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueTicketContract.kt */
public final class VenueTicketContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"}, d2 = {"Lcn/xports/ticket/VenueTicketContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "getTodayTicket", "Lio/reactivex/Observable;", "Lcn/xports/parser/TradeTicketParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: VenueTicketContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<TradeTicketParser> getTodayTicket();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&¨\u0006\u0007"}, d2 = {"Lcn/xports/ticket/VenueTicketContract$View;", "Lcn/xports/baselib/mvp/IView;", "showTickets", "", "ticketList", "", "Lcn/xports/entity/TradeTicket;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: VenueTicketContract.kt */
    public interface View extends IView {
        void showTickets(@Nullable List<? extends TradeTicket> list);
    }
}
