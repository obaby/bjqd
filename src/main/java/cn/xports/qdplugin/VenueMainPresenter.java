package cn.xports.qdplugin;

import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.qdplugin.VenueMainContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcn/xports/qdplugin/VenueMainPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/qdplugin/VenueMainContract$Model;", "Lcn/xports/qdplugin/VenueMainContract$View;", "model", "view", "(Lcn/xports/qdplugin/VenueMainContract$Model;Lcn/xports/qdplugin/VenueMainContract$View;)V", "getVenueList", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainPresenter.kt */
public final class VenueMainPresenter extends BasePresenter<VenueMainContract.Model, VenueMainContract.View> {
    public final void getVenueList() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VenueMainPresenter(@NotNull VenueMainContract.Model model, @NotNull VenueMainContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }
}
