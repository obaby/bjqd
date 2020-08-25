package cn.xports.venue;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.parser.VenueListParser;
import cn.xports.venue.VenueMainContract;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/venue/VenueMainPresenter$getVenueList$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/VenueListParser;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainPresenter.kt */
public final class VenueMainPresenter$getVenueList$$inlined$apply$lambda$1 extends ProcessObserver<VenueListParser> {
    final /* synthetic */ String $serviceId$inlined;
    final /* synthetic */ VenueMainPresenter this$0;

    public void next(@Nullable VenueListParser venueListParser) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VenueMainPresenter$getVenueList$$inlined$apply$lambda$1(String str, VenueMainPresenter venueMainPresenter, String str2) {
        super(str);
        this.this$0 = venueMainPresenter;
        this.$serviceId$inlined = str2;
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        String str;
        super.onError(responseThrowable);
        VenueMainContract.View view = (VenueMainContract.View) this.this$0.getRootView();
        if (view != null) {
            if (responseThrowable == null || (str = responseThrowable.getMessage()) == null) {
                str = "获取失败";
            }
            view.showMsg(str);
        }
    }
}
