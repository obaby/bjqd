package cn.xports.venue;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.parser.TicketParser;
import cn.xports.venue.VenueDetailContract;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/venue/VenueDetailPresenter$getTicketSaleList$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/TicketParser;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueDetailPresenter.kt */
public final class VenueDetailPresenter$getTicketSaleList$$inlined$apply$lambda$1 extends ProcessObserver<TicketParser> {
    final /* synthetic */ String $serviceId$inlined;
    final /* synthetic */ String $venueId$inlined;
    final /* synthetic */ VenueDetailPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VenueDetailPresenter$getTicketSaleList$$inlined$apply$lambda$1(IView iView, VenueDetailPresenter venueDetailPresenter, String str, String str2) {
        super(iView);
        this.this$0 = venueDetailPresenter;
        this.$venueId$inlined = str;
        this.$serviceId$inlined = str2;
    }

    public void next(@Nullable TicketParser ticketParser) {
        VenueDetailContract.View view = (VenueDetailContract.View) this.this$0.getRootView();
        if (view != null) {
            view.onGetTicketScle(ticketParser);
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        VenueDetailContract.View view = (VenueDetailContract.View) this.this$0.getRootView();
        if (view != null) {
            view.onGetTicketScle((TicketParser) null);
        }
    }
}
