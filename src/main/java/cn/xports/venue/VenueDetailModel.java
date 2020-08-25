package cn.xports.venue;

import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BaseModel;
import cn.xports.http.SodaService;
import cn.xports.parser.FieldSaleParser;
import cn.xports.parser.TicketParser;
import cn.xports.parser.VenueDetailParser;
import cn.xports.qd2.entity.K;
import cn.xports.venue.VenueDetailContract;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcn/xports/venue/VenueDetailModel;", "Lcn/xports/baselib/mvp/BaseModel;", "Lcn/xports/venue/VenueDetailContract$Model;", "()V", "service", "Lcn/xports/http/SodaService;", "kotlin.jvm.PlatformType", "getFieldSaleList", "Lio/reactivex/Observable;", "Lcn/xports/parser/FieldSaleParser;", "venueId", "", "serviceId", "getTicketSaleList", "Lcn/xports/parser/TicketParser;", "getVenueDetail", "Lcn/xports/parser/VenueDetailParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueDetailModel.kt */
public final class VenueDetailModel extends BaseModel implements VenueDetailContract.Model {
    private final SodaService service = ((SodaService) RetrofitUtil.getInstance().create(SodaService.class));

    @NotNull
    public Observable<FieldSaleParser> getFieldSaleList(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        Observable<FieldSaleParser> compose = this.service.getFieldSaleList(str2, str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getFieldSaleList…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<TicketParser> getTicketSaleList(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        Observable<TicketParser> compose = this.service.getTicketList(7, str2, str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getTicketList(Co…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<VenueDetailParser> getVenueDetail(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Observable<VenueDetailParser> compose = this.service.getVenueDetail(str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getVenueDetail(v…pose(applyErrorsWithIO())");
        return compose;
    }
}
