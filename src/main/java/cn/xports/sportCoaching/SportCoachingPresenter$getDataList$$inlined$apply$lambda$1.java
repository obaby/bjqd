package cn.xports.sportCoaching;

import android.util.Log;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.sportCoaching.SportCoachingListContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/sportCoaching/SportCoachingPresenter$getDataList$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/sportCoaching/SportCoachingBean;", "next", "", "p0", "onError", "e", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SportCoachingPresenter.kt */
public final class SportCoachingPresenter$getDataList$$inlined$apply$lambda$1 extends ProcessObserver<SportCoachingBean> {
    final /* synthetic */ SportCoachingPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SportCoachingPresenter$getDataList$$inlined$apply$lambda$1(IView iView, SportCoachingPresenter sportCoachingPresenter) {
        super(iView);
        this.this$0 = sportCoachingPresenter;
    }

    public void next(@Nullable SportCoachingBean sportCoachingBean) {
        SportCoachingListContract.View view;
        if (sportCoachingBean != null && (view = (SportCoachingListContract.View) this.this$0.getRootView()) != null) {
            view.showListData(sportCoachingBean);
        }
    }

    public void onError(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "e");
        super.onError(th);
        Log.e(">>> getDataList ", th.getMessage());
    }
}
