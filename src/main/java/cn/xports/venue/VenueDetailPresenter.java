package cn.xports.venue;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import cn.xports.venue.VenueDetailContract;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nJ\u0016\u0010\u000e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\n¨\u0006\u0010"}, d2 = {"Lcn/xports/venue/VenueDetailPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/venue/VenueDetailContract$Model;", "Lcn/xports/venue/VenueDetailContract$View;", "model", "view", "(Lcn/xports/venue/VenueDetailContract$Model;Lcn/xports/venue/VenueDetailContract$View;)V", "callPhone", "", "phone", "", "getFieldSaleList", "venueId", "serviceId", "getTicketSaleList", "getVenueDetail", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueDetailPresenter.kt */
public final class VenueDetailPresenter extends BasePresenter<VenueDetailContract.Model, VenueDetailContract.View> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VenueDetailPresenter(@NotNull VenueDetailContract.Model model, @NotNull VenueDetailContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getTicketSaleList(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        VenueDetailContract.Model model = (VenueDetailContract.Model) getModel();
        if (model != null) {
            model.getTicketSaleList(str, str2).subscribe(new VenueDetailPresenter$getTicketSaleList$$inlined$apply$lambda$1(getRootView(), this, str, str2));
        }
    }

    public final void getFieldSaleList(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        VenueDetailContract.Model model = (VenueDetailContract.Model) getModel();
        if (model != null) {
            model.getFieldSaleList(str, str2).subscribe(new VenueDetailPresenter$getFieldSaleList$$inlined$apply$lambda$1(getRootView(), this, str, str2));
        }
    }

    public final void getVenueDetail(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        VenueDetailContract.Model model = (VenueDetailContract.Model) getModel();
        if (model != null) {
            model.getVenueDetail(str).subscribe(new VenueDetailPresenter$getVenueDetail$$inlined$apply$lambda$1(getRootView(), this, str));
        }
    }

    public final void callPhone(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "phone");
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
        intent.setFlags(268435456);
        IView rootView = getRootView();
        if (rootView != null) {
            ((Activity) rootView).startActivity(intent);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }
}
