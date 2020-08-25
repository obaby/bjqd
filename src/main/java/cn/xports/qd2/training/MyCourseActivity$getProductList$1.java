package cn.xports.qd2.training;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/qd2/training/MyCourseActivity$getProductList$1", "Lcn/xports/baselib/http/ResponseDataMap;", "onError", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "onSuccess", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCourseActivity.kt */
public final class MyCourseActivity$getProductList$1 extends ResponseDataMap {
    final /* synthetic */ MyCourseActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyCourseActivity$getProductList$1(MyCourseActivity myCourseActivity, IView iView) {
        super(iView);
        this.this$0 = myCourseActivity;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        ArrayList<DataMap> dataList = dataMap.getDataList(K.courseList);
        Intrinsics.checkExpressionValueIsNotNull(dataList, "dataMap.getDataList(K.courseList)");
        for (DataMap dataMap2 : dataList) {
            if (Intrinsics.areEqual(dataMap2.getString(K.privateTag), K.k0)) {
                this.this$0.trainList.add(dataMap2);
            } else {
                this.this$0.privateList.add(dataMap2);
            }
        }
        this.this$0.showList.addAll(this.this$0.trainList);
        this.this$0.adapter.notifyDataSetChanged();
    }

    public void onError(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        super.onError(dataMap);
        this.this$0.adapter.notifyDataSetChanged();
    }
}
