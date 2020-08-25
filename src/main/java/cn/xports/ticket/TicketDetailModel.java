package cn.xports.ticket;

import cn.xports.base.BaseBusModel;
import cn.xports.baselib.http.RxUtil;
import cn.xports.entity.OrderInfo;
import cn.xports.parser.TicketDetailParser;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.WebViewDetailActivity;
import cn.xports.ticket.TicketDetailContract;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J.\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0016¨\u0006\u0010"}, d2 = {"Lcn/xports/ticket/TicketDetailModel;", "Lcn/xports/base/BaseBusModel;", "Lcn/xports/ticket/TicketDetailContract$Model;", "()V", "getTicketInfo", "Lio/reactivex/Observable;", "Lcn/xports/parser/TicketDetailParser;", "timeId", "", "ticketTypeId", "ticketOrder", "Lcn/xports/entity/OrderInfo;", "ticketInfo", "date", "venueId", "serviceId", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TicketDetailModel.kt */
public final class TicketDetailModel extends BaseBusModel implements TicketDetailContract.Model {
    @NotNull
    public Observable<OrderInfo> ticketOrder(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "ticketInfo");
        Intrinsics.checkParameterIsNotNull(str2, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str3, K.venueId);
        Intrinsics.checkParameterIsNotNull(str4, K.serviceId);
        Observable<OrderInfo> compose = this.service.orderTicket(str2, str, str4, str3).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.orderTicket(date…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<TicketDetailParser> getTicketInfo(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "timeId");
        Intrinsics.checkParameterIsNotNull(str2, "ticketTypeId");
        Observable<TicketDetailParser> compose = this.service.getTicketInfo(str2, str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getTicketInfo(ti…pose(applyErrorsWithIO())");
        return compose;
    }
}
