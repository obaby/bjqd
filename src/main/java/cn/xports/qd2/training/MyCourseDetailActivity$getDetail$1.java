package cn.xports.qd2.training;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseMapWrap;
import cn.xports.baselib.mvp.IView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/MyCourseDetailActivity$getDetail$1", "Lcn/xports/baselib/http/ResponseMapWrap;", "onSuccess", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCourseDetailActivity.kt */
public final class MyCourseDetailActivity$getDetail$1 extends ResponseMapWrap {
    final /* synthetic */ MyCourseDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyCourseDetailActivity$getDetail$1(MyCourseDetailActivity myCourseDetailActivity, IView iView) {
        super(iView);
        this.this$0 = myCourseDetailActivity;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        this.this$0.initCourse(dataMap);
    }
}
