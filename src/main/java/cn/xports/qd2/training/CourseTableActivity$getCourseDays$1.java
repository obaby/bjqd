package cn.xports.qd2.training;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.DateWeekday;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.TimeUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/CourseTableActivity$getCourseDays$1", "Lcn/xports/baselib/http/ResponseDataMap;", "onSuccess", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
public final class CourseTableActivity$getCourseDays$1 extends ResponseDataMap {
    final /* synthetic */ CourseTableActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CourseTableActivity$getCourseDays$1(CourseTableActivity courseTableActivity, IView iView) {
        super(iView);
        this.this$0 = courseTableActivity;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        Integer intValue = dataMap.getIntValue(K.course_schedule_days, 0);
        Intrinsics.checkExpressionValueIsNotNull(intValue, "days");
        int intValue2 = intValue.intValue();
        for (int i = 0; i < intValue2; i++) {
            DateWeekday date = new DateWeekday().setDate(TimeUtils.getStringByNow((long) i, 86400000));
            if (i == 0) {
                Intrinsics.checkExpressionValueIsNotNull(date, "weekDay");
                date.setSelect(true);
            }
            this.this$0.dayItems.add(date);
        }
        this.this$0.dayAdapter.notifyDataSetChanged();
    }
}
