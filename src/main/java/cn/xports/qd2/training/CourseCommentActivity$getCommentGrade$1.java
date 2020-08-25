package cn.xports.qd2.training;

import android.widget.LinearLayout;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseMapWrap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.widget.GradeLabelView;
import com.blankj.utilcode.util.SizeUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/CourseCommentActivity$getCommentGrade$1", "Lcn/xports/baselib/http/ResponseMapWrap;", "onSuccess", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseCommentActivity.kt */
public final class CourseCommentActivity$getCommentGrade$1 extends ResponseMapWrap {
    final /* synthetic */ CourseCommentActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CourseCommentActivity$getCommentGrade$1(CourseCommentActivity courseCommentActivity, IView iView) {
        super(iView);
        this.this$0 = courseCommentActivity;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        ArrayList<DataMap> dataList = dataMap.getDataList(K.commentGradeDetail);
        Intrinsics.checkExpressionValueIsNotNull(dataList, "mapList");
        for (DataMap gradeLabelView : dataList) {
            GradeLabelView gradeLabelView2 = new GradeLabelView(this.this$0, gradeLabelView);
            gradeLabelView2.setPadding(0, SizeUtils.dp2px(12.0f), 0, SizeUtils.dp2px(12.0f));
            this.this$0.labelViews.add(gradeLabelView2);
            ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llGradeList)).addView(gradeLabelView2);
        }
    }
}
