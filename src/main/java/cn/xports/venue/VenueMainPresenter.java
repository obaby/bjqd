package cn.xports.venue;

import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.venue.VenueMainContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bJ\u0006\u0010\u000e\u001a\u00020\bJ\u0010\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\nJ\u0016\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n¨\u0006\u0014"}, d2 = {"Lcn/xports/venue/VenueMainPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/venue/VenueMainContract$Model;", "Lcn/xports/venue/VenueMainContract$View;", "model", "view", "(Lcn/xports/venue/VenueMainContract$Model;Lcn/xports/venue/VenueMainContract$View;)V", "getAppInfo", "", "phone", "", "outerUid", "getCenterAd", "getOrderNum", "getServiceList", "getVenueList", "serviceId", "getWeather", "cityName", "login", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainPresenter.kt */
public final class VenueMainPresenter extends BasePresenter<VenueMainContract.Model, VenueMainContract.View> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VenueMainPresenter(@NotNull VenueMainContract.Model model, @NotNull VenueMainContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getVenueList(@Nullable String str) {
        VenueMainContract.Model model = (VenueMainContract.Model) getModel();
        if (model != null) {
            model.getVenueList(str).subscribe(new VenueMainPresenter$getVenueList$$inlined$apply$lambda$1(getTAG(), this, str));
        }
    }

    public final void getAppInfo(@Nullable String str, @Nullable String str2) {
        VenueMainContract.Model model = (VenueMainContract.Model) getModel();
        if (model != null) {
            model.getAppInfo().subscribe(new VenueMainPresenter$getAppInfo$$inlined$apply$lambda$1(getRootView(), this, str, str2));
        }
    }

    public final void login(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "phone");
        Intrinsics.checkParameterIsNotNull(str2, "outerUid");
        VenueMainContract.Model model = (VenueMainContract.Model) getModel();
        if (model != null) {
            model.appLogin(str2, str).subscribe(new VenueMainPresenter$login$$inlined$apply$lambda$1(getRootView(), this, str2, str));
        }
    }

    public final void getServiceList() {
        VenueMainContract.Model model = (VenueMainContract.Model) getModel();
        if (model != null) {
            model.getCenterServices().subscribe(new VenueMainPresenter$getServiceList$$inlined$apply$lambda$1(getRootView(), this));
        }
    }

    public final void getOrderNum() {
        VenueMainContract.Model model = (VenueMainContract.Model) getModel();
    }

    public final void getCenterAd() {
        VenueMainContract.Model model = (VenueMainContract.Model) getModel();
        if (model != null) {
            model.getBannerList().subscribe(new VenueMainPresenter$getCenterAd$$inlined$apply$lambda$1(getRootView(), this));
        }
    }

    public final void getWeather(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "cityName");
        VenueMainContract.Model model = (VenueMainContract.Model) getModel();
        if (model != null) {
            model.getWeatherInfo(str).subscribe(new VenueMainPresenter$getWeather$$inlined$apply$lambda$1(getRootView(), this, str));
        }
    }
}
