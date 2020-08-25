package cn.xports.ticket;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.parser.TradeTicketParser;
import cn.xports.ticket.VenueTicketContract;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n¸\u0006\u0000"}, d2 = {"cn/xports/ticket/VenueTicketPresenter$getTickets$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/TradeTicketParser;", "next", "", "value", "onComplete", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueTicketPresenter.kt */
public final class VenueTicketPresenter$getTickets$$inlined$apply$lambda$1 extends ProcessObserver<TradeTicketParser> {
    final /* synthetic */ VenueTicketPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VenueTicketPresenter$getTickets$$inlined$apply$lambda$1(String str, VenueTicketPresenter venueTicketPresenter) {
        super(str);
        this.this$0 = venueTicketPresenter;
    }

    public void next(@Nullable TradeTicketParser tradeTicketParser) {
        VenueTicketContract.View view = (VenueTicketContract.View) this.this$0.getRootView();
        if (view != null) {
            view.showTickets(tradeTicketParser != null ? tradeTicketParser.getTradeTickets() : null);
        }
    }

    public void onComplete() {
        super.onComplete();
        VenueTicketContract.View view = (VenueTicketContract.View) this.this$0.getRootView();
        if (view != null) {
            view.hideLoading();
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        VenueTicketContract.View view = (VenueTicketContract.View) this.this$0.getRootView();
        if (view != null) {
            view.hideLoading();
        }
    }
}
