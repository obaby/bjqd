package cn.xports.qd2.training;

import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/qd2/training/CourseTableActivity$getTable$1", "Lcn/xports/baselib/http/ResponseDataMap;", "onError", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "onSuccess", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
public final class CourseTableActivity$getTable$1 extends ResponseDataMap {
    final /* synthetic */ CourseTableActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CourseTableActivity$getTable$1(CourseTableActivity courseTableActivity, IView iView) {
        super(iView);
        this.this$0 = courseTableActivity;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        CourseTableActivity courseTableActivity = this.this$0;
        ArrayList<DataMap> dataList = dataMap.getDataList(K.schedule);
        Intrinsics.checkExpressionValueIsNotNull(dataList, "dataMap.getDataList(K.schedule)");
        courseTableActivity.tableCourses = dataList;
        this.this$0.refreshTableList(this.this$0.tableCourses);
        this.this$0.setStuNames();
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llStudents);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llStudents");
        if (linearLayout.getChildCount() < 2) {
            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) this.this$0._$_findCachedViewById(R.id.scrollStudents);
            Intrinsics.checkExpressionValueIsNotNull(horizontalScrollView, "scrollStudents");
            horizontalScrollView.setVisibility(8);
            return;
        }
        HorizontalScrollView horizontalScrollView2 = (HorizontalScrollView) this.this$0._$_findCachedViewById(R.id.scrollStudents);
        Intrinsics.checkExpressionValueIsNotNull(horizontalScrollView2, "scrollStudents");
        horizontalScrollView2.setVisibility(0);
    }

    public void onError(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        this.this$0.courseAdapter.notifyDataSetChanged();
    }
}
