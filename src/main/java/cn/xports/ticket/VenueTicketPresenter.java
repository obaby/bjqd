package cn.xports.ticket;

import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.ticket.VenueTicketContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcn/xports/ticket/VenueTicketPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/ticket/VenueTicketContract$Model;", "Lcn/xports/ticket/VenueTicketContract$View;", "model", "view", "(Lcn/xports/ticket/VenueTicketContract$Model;Lcn/xports/ticket/VenueTicketContract$View;)V", "getTickets", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueTicketPresenter.kt */
public final class VenueTicketPresenter extends BasePresenter<VenueTicketContract.Model, VenueTicketContract.View> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VenueTicketPresenter(@NotNull VenueTicketContract.Model model, @NotNull VenueTicketContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getTickets() {
        VenueTicketContract.View view = (VenueTicketContract.View) getRootView();
        if (view != null) {
            view.hideNoData();
        }
        VenueTicketContract.Model model = (VenueTicketContract.Model) getModel();
        if (model != null) {
            VenueTicketContract.View view2 = (VenueTicketContract.View) getRootView();
            if (view2 != null) {
                view2.showLoading();
            }
            model.getTodayTicket().subscribe(new VenueTicketPresenter$getTickets$$inlined$apply$lambda$1(getTAG(), this));
        }
    }
}
