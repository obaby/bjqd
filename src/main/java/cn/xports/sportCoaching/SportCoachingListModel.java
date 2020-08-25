package cn.xports.sportCoaching;

import android.support.v4.app.NotificationCompat;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BaseModel;
import cn.xports.http.SodaService;
import cn.xports.sportCoaching.SportCoachingListContract;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u0019\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcn/xports/sportCoaching/SportCoachingListModel;", "Lcn/xports/baselib/mvp/BaseModel;", "Lcn/xports/sportCoaching/SportCoachingListContract$Model;", "()V", "service", "Lcn/xports/http/SodaService;", "kotlin.jvm.PlatformType", "getService", "()Lcn/xports/http/SodaService;", "getSportCoachList", "Lio/reactivex/Observable;", "Lcn/xports/sportCoaching/SportCoachingBean;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SportCoachingListModel.kt */
public final class SportCoachingListModel extends BaseModel implements SportCoachingListContract.Model {
    private final SodaService service = ((SodaService) RetrofitUtil.getInstance().create(SodaService.class));

    public final SodaService getService() {
        return this.service;
    }

    @NotNull
    public Observable<SportCoachingBean> getSportCoachList() {
        SodaService sodaService = this.service;
        Intrinsics.checkExpressionValueIsNotNull(sodaService, NotificationCompat.CATEGORY_SERVICE);
        Observable<SportCoachingBean> compose = sodaService.getSportCoachInfo().compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.sportCoachInfo.c…pose(applyErrorsWithIO())");
        return compose;
    }
}
