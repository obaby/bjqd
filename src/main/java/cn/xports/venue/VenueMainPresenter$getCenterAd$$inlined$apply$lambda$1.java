package cn.xports.venue;

import android.util.Log;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.parser.HomeBannerBean;
import cn.xports.venue.VenueMainContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/venue/VenueMainPresenter$getCenterAd$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/HomeBannerBean;", "next", "", "value", "onError", "e", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainPresenter.kt */
public final class VenueMainPresenter$getCenterAd$$inlined$apply$lambda$1 extends ProcessObserver<HomeBannerBean> {
    final /* synthetic */ VenueMainPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VenueMainPresenter$getCenterAd$$inlined$apply$lambda$1(IView iView, VenueMainPresenter venueMainPresenter) {
        super(iView);
        this.this$0 = venueMainPresenter;
    }

    public void next(@Nullable HomeBannerBean homeBannerBean) {
        List<HomeBannerBean.AdList> list;
        VenueMainContract.View view;
        StringBuilder sb = new StringBuilder();
        sb.append("广告：");
        sb.append(homeBannerBean != null ? homeBannerBean.toString() : null);
        Log.e(">>>", sb.toString());
        if (homeBannerBean != null && (list = homeBannerBean.adList) != null && (view = (VenueMainContract.View) this.this$0.getRootView()) != null) {
            view.showBanner(list);
        }
    }

    public void onError(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "e");
        super.onError(th);
        Log.e(">>>", "广告onError：" + th.getMessage());
    }
}
