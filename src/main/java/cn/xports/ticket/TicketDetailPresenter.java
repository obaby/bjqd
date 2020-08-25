package cn.xports.ticket;

import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.WebViewDetailActivity;
import cn.xports.ticket.TicketDetailContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ&\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n¨\u0006\u0011"}, d2 = {"Lcn/xports/ticket/TicketDetailPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/ticket/TicketDetailContract$Model;", "Lcn/xports/ticket/TicketDetailContract$View;", "model", "view", "(Lcn/xports/ticket/TicketDetailContract$Model;Lcn/xports/ticket/TicketDetailContract$View;)V", "getTicketInfo", "", "timeId", "", "ticketTypeId", "ticketOrder", "ticketInfo", "date", "venueId", "serviceId", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TicketDetailPresenter.kt */
public final class TicketDetailPresenter extends BasePresenter<TicketDetailContract.Model, TicketDetailContract.View> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TicketDetailPresenter(@NotNull TicketDetailContract.Model model, @NotNull TicketDetailContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getTicketInfo(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "timeId");
        Intrinsics.checkParameterIsNotNull(str2, "ticketTypeId");
        TicketDetailContract.Model model = (TicketDetailContract.Model) getModel();
        if (model != null) {
            TicketDetailContract.View view = (TicketDetailContract.View) getRootView();
            if (view != null) {
                view.showLoading();
            }
            model.getTicketInfo(str, str2).subscribe(new TicketDetailPresenter$getTicketInfo$$inlined$apply$lambda$1(getTAG(), this, str, str2));
        }
    }

    public final void ticketOrder(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "ticketInfo");
        Intrinsics.checkParameterIsNotNull(str2, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str3, K.venueId);
        Intrinsics.checkParameterIsNotNull(str4, K.serviceId);
        TicketDetailContract.Model model = (TicketDetailContract.Model) getModel();
        if (model != null) {
            TicketDetailContract.View view = (TicketDetailContract.View) getRootView();
            if (view != null) {
                view.showLoading();
            }
            model.ticketOrder(str, str2, str3, str4).subscribe(new TicketDetailPresenter$ticketOrder$$inlined$apply$lambda$1(getTAG(), this, str, str2, str3, str4));
        }
    }
}
