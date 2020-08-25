package cn.xports.ticket;

import android.support.v4.app.NotificationCompat;
import cn.xports.base.BaseBusModel;
import cn.xports.baselib.http.RxUtil;
import cn.xports.http.SodaService;
import cn.xports.parser.TradeTicketParser;
import cn.xports.ticket.VenueTicketContract;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcn/xports/ticket/VenueTicketModel;", "Lcn/xports/base/BaseBusModel;", "Lcn/xports/ticket/VenueTicketContract$Model;", "()V", "getTodayTicket", "Lio/reactivex/Observable;", "Lcn/xports/parser/TradeTicketParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueTicketModel.kt */
public final class VenueTicketModel extends BaseBusModel implements VenueTicketContract.Model {
    @NotNull
    public Observable<TradeTicketParser> getTodayTicket() {
        SodaService sodaService = this.service;
        Intrinsics.checkExpressionValueIsNotNull(sodaService, NotificationCompat.CATEGORY_SERVICE);
        Observable<TradeTicketParser> compose = sodaService.getTodayTickets().compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.todayTickets.compose(applyErrorsWithIO())");
        return compose;
    }
}
