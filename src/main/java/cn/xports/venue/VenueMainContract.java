package cn.xports.venue;

import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.Service;
import cn.xports.parser.AppParser;
import cn.xports.parser.HomeBannerBean;
import cn.xports.parser.HomeWeatherBean;
import cn.xports.parser.LoginParser;
import cn.xports.parser.ServiceParser;
import cn.xports.parser.VenueListParser;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/venue/VenueMainContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainContract.kt */
public final class VenueMainContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H&J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0003H&J\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006H&J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\u0006\u0010\u0013\u001a\u00020\u0006H&¨\u0006\u0014"}, d2 = {"Lcn/xports/venue/VenueMainContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "appLogin", "Lio/reactivex/Observable;", "Lcn/xports/parser/LoginParser;", "outerUid", "", "phoneNum", "getAppInfo", "Lcn/xports/parser/AppParser;", "getBannerList", "Lcn/xports/parser/HomeBannerBean;", "getCenterServices", "Lcn/xports/parser/ServiceParser;", "getVenueList", "Lcn/xports/parser/VenueListParser;", "serviceId", "getWeatherInfo", "Lcn/xports/parser/HomeWeatherBean;", "cityName", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: VenueMainContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<LoginParser> appLogin(@NotNull String str, @NotNull String str2);

        @NotNull
        Observable<AppParser> getAppInfo();

        @NotNull
        Observable<HomeBannerBean> getBannerList();

        @NotNull
        Observable<ServiceParser> getCenterServices();

        @NotNull
        Observable<VenueListParser> getVenueList(@Nullable String str);

        @NotNull
        Observable<HomeWeatherBean> getWeatherInfo(@NotNull String str);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006R\u00020\u00070\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\nH&¨\u0006\u0010"}, d2 = {"Lcn/xports/venue/VenueMainContract$View;", "Lcn/xports/baselib/mvp/IView;", "showBanner", "", "bannerAd", "", "Lcn/xports/parser/HomeBannerBean$AdList;", "Lcn/xports/parser/HomeBannerBean;", "showCityLocation", "cityName", "", "showServiceList", "serviceList", "Lcn/xports/entity/Service;", "showWeather", "temperature", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: VenueMainContract.kt */
    public interface View extends IView {
        void showBanner(@NotNull List<? extends HomeBannerBean.AdList> list);

        void showCityLocation(@NotNull String str);

        void showServiceList(@NotNull List<? extends Service> list);

        void showWeather(@NotNull String str);
    }
}
