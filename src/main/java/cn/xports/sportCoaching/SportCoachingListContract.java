package cn.xports.sportCoaching;

import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/sportCoaching/SportCoachingListContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SportCoachingListContract.kt */
public final class SportCoachingListContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"}, d2 = {"Lcn/xports/sportCoaching/SportCoachingListContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "getSportCoachList", "Lio/reactivex/Observable;", "Lcn/xports/sportCoaching/SportCoachingBean;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: SportCoachingListContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<SportCoachingBean> getSportCoachList();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcn/xports/sportCoaching/SportCoachingListContract$View;", "Lcn/xports/baselib/mvp/IView;", "showListData", "", "data", "Lcn/xports/sportCoaching/SportCoachingBean;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: SportCoachingListContract.kt */
    public interface View extends IView {
        void showListData(@NotNull SportCoachingBean sportCoachingBean);
    }
}
