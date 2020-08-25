package cn.xports.ticket;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.parser.TicketDetailParser;
import cn.xports.ticket.TicketDetailContract;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n¸\u0006\u0000"}, d2 = {"cn/xports/ticket/TicketDetailPresenter$getTicketInfo$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/TicketDetailParser;", "next", "", "value", "onComplete", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TicketDetailPresenter.kt */
public final class TicketDetailPresenter$getTicketInfo$$inlined$apply$lambda$1 extends ProcessObserver<TicketDetailParser> {
    final /* synthetic */ String $ticketTypeId$inlined;
    final /* synthetic */ String $timeId$inlined;
    final /* synthetic */ TicketDetailPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TicketDetailPresenter$getTicketInfo$$inlined$apply$lambda$1(String str, TicketDetailPresenter ticketDetailPresenter, String str2, String str3) {
        super(str);
        this.this$0 = ticketDetailPresenter;
        this.$timeId$inlined = str2;
        this.$ticketTypeId$inlined = str3;
    }

    public void next(@Nullable TicketDetailParser ticketDetailParser) {
        TicketDetailContract.View view = (TicketDetailContract.View) this.this$0.getRootView();
        if (view != null) {
            view.onGetTicketInfo(ticketDetailParser);
        }
    }

    public void onComplete() {
        super.onComplete();
        TicketDetailContract.View view = (TicketDetailContract.View) this.this$0.getRootView();
        if (view != null) {
            view.hideLoading();
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        TicketDetailContract.View view;
        super.onError(responseThrowable);
        if (!(responseThrowable == null || (view = (TicketDetailContract.View) this.this$0.getRootView()) == null)) {
            String message = responseThrowable.getMessage();
            if (message == null) {
                message = "获取票信息失败";
            }
            view.showMsg(message);
        }
        TicketDetailContract.View view2 = (TicketDetailContract.View) this.this$0.getRootView();
        if (view2 != null) {
            view2.hideLoading();
        }
    }
}
