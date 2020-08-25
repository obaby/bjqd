package cn.xports.venue;

import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import cn.xports.parser.FieldSaleParser;
import cn.xports.parser.TicketParser;
import cn.xports.parser.VenueDetailParser;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/venue/VenueDetailContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueDetailContract.kt */
public final class VenueDetailContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\f"}, d2 = {"Lcn/xports/venue/VenueDetailContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "getFieldSaleList", "Lio/reactivex/Observable;", "Lcn/xports/parser/FieldSaleParser;", "venueId", "", "serviceId", "getTicketSaleList", "Lcn/xports/parser/TicketParser;", "getVenueDetail", "Lcn/xports/parser/VenueDetailParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: VenueDetailContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<FieldSaleParser> getFieldSaleList(@NotNull String str, @NotNull String str2);

        @NotNull
        Observable<TicketParser> getTicketSaleList(@NotNull String str, @NotNull String str2);

        @NotNull
        Observable<VenueDetailParser> getVenueDetail(@NotNull String str);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcn/xports/venue/VenueDetailContract$View;", "Lcn/xports/baselib/mvp/IView;", "onGetFieldSale", "", "fieldSaleParser", "Lcn/xports/parser/FieldSaleParser;", "onGetTicketScle", "ticketParser", "Lcn/xports/parser/TicketParser;", "onGetVenueDetail", "venueDetailParser", "Lcn/xports/parser/VenueDetailParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: VenueDetailContract.kt */
    public interface View extends IView {
        void onGetFieldSale(@Nullable FieldSaleParser fieldSaleParser);

        void onGetTicketScle(@Nullable TicketParser ticketParser);

        void onGetVenueDetail(@NotNull VenueDetailParser venueDetailParser);
    }
}
