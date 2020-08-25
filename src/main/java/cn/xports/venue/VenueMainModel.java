package cn.xports.venue;

import android.support.v4.app.NotificationCompat;
import cn.xports.base.Constant;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BaseModel;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.baselib.util.SPUtil;
import cn.xports.http.SodaService;
import cn.xports.parser.AppParser;
import cn.xports.parser.HomeBannerBean;
import cn.xports.parser.HomeWeatherBean;
import cn.xports.parser.LoginParser;
import cn.xports.parser.ServiceParser;
import cn.xports.parser.VenueListParser;
import cn.xports.qd2.entity.K;
import cn.xports.venue.VenueMainContract;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nH\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\nH\u0016J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\rH\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\n2\u0006\u0010\u001a\u001a\u00020\rH\u0016R\u0019\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lcn/xports/venue/VenueMainModel;", "Lcn/xports/baselib/mvp/BaseModel;", "Lcn/xports/venue/VenueMainContract$Model;", "()V", "service", "Lcn/xports/http/SodaService;", "kotlin.jvm.PlatformType", "getService", "()Lcn/xports/http/SodaService;", "appLogin", "Lio/reactivex/Observable;", "Lcn/xports/parser/LoginParser;", "outerUid", "", "phoneNum", "getAppInfo", "Lcn/xports/parser/AppParser;", "getBannerList", "Lcn/xports/parser/HomeBannerBean;", "getCenterServices", "Lcn/xports/parser/ServiceParser;", "getVenueList", "Lcn/xports/parser/VenueListParser;", "serviceId", "getWeatherInfo", "Lcn/xports/parser/HomeWeatherBean;", "cityName", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainModel.kt */
public final class VenueMainModel extends BaseModel implements VenueMainContract.Model {
    private final SodaService service = ((SodaService) RetrofitUtil.getInstance().create(SodaService.class));

    @NotNull
    public Observable<LoginParser> appLogin(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "outerUid");
        Intrinsics.checkParameterIsNotNull(str2, K.phoneNum);
        DataMap fromJson = DataMapUtils.fromJson(SPUtil.Companion.getINSTANCE().getStringValue("qdUserInfo"));
        if (fromJson == null) {
            fromJson = new DataMap();
        }
        Observable<LoginParser> compose = this.service.login(str2, str, fromJson.getString("nickName", SPUtil.Companion.getINSTANCE().getStringValue("phone")), fromJson.getString("photo")).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.login(phoneNum, …pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<AppParser> getAppInfo() {
        Observable<AppParser> compose = this.service.getAppInfo(Constant.APPLICATION_ID).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getAppInfo(Const…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<ServiceParser> getCenterServices() {
        SodaService sodaService = this.service;
        Intrinsics.checkExpressionValueIsNotNull(sodaService, NotificationCompat.CATEGORY_SERVICE);
        Observable<ServiceParser> compose = sodaService.getCenterServices().compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.centerServices.c…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<HomeBannerBean> getBannerList() {
        SodaService sodaService = this.service;
        Intrinsics.checkExpressionValueIsNotNull(sodaService, NotificationCompat.CATEGORY_SERVICE);
        Observable<HomeBannerBean> compose = sodaService.getHomeAd().compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.homeAd.compose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<HomeWeatherBean> getWeatherInfo(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "cityName");
        Observable<HomeWeatherBean> compose = this.service.getHomeWeather(str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getHomeWeather(c…pose(applyErrorsWithIO())");
        return compose;
    }

    public final SodaService getService() {
        return this.service;
    }

    @NotNull
    public Observable<VenueListParser> getVenueList(@Nullable String str) {
        Observable<VenueListParser> compose = this.service.getVenueList(7, str, 1).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getVenueList(Con…pose(applyErrorsWithIO())");
        return compose;
    }
}
