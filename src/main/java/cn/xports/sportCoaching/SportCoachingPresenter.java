package cn.xports.sportCoaching;

import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.sportCoaching.SportCoachingListContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcn/xports/sportCoaching/SportCoachingPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/sportCoaching/SportCoachingListContract$Model;", "Lcn/xports/sportCoaching/SportCoachingListContract$View;", "model", "view", "(Lcn/xports/sportCoaching/SportCoachingListContract$Model;Lcn/xports/sportCoaching/SportCoachingListContract$View;)V", "getDataList", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SportCoachingPresenter.kt */
public final class SportCoachingPresenter extends BasePresenter<SportCoachingListContract.Model, SportCoachingListContract.View> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SportCoachingPresenter(@NotNull SportCoachingListContract.Model model, @NotNull SportCoachingListContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getDataList() {
        SportCoachingListContract.Model model = (SportCoachingListContract.Model) getModel();
        if (model != null) {
            model.getSportCoachList().subscribe(new SportCoachingPresenter$getDataList$$inlined$apply$lambda$1(getRootView(), this));
        }
    }
}
