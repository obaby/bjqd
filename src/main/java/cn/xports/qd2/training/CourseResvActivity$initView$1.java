package cn.xports.qd2.training;

import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.entity.DateWeekday;
import cn.xports.qd2.entity.K;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/qd2/entity/DateWeekday;", "kotlin.jvm.PlatformType", "position", "", "viewId", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CourseResvActivity.kt */
final class CourseResvActivity$initView$1<T> implements XBaseAdapter.OnItemClickListener<DateWeekday> {
    final /* synthetic */ CourseResvActivity this$0;

    CourseResvActivity$initView$1(CourseResvActivity courseResvActivity) {
        this.this$0 = courseResvActivity;
    }

    public final void onItemClick(DateWeekday dateWeekday, int i, int i2) {
        this.this$0.lessons.clear();
        DataMap access$getTotalLesson$p = this.this$0.totalLesson;
        Intrinsics.checkExpressionValueIsNotNull(dateWeekday, "item");
        ArrayList<DataMap> dataList = access$getTotalLesson$p.getDataList(dateWeekday.getDate());
        Intrinsics.checkExpressionValueIsNotNull(dataList, "totalLesson.getDataList(item.date)");
        for (DataMap copy : dataList) {
            this.this$0.lessons.add(copy.copy(this.this$0.selectCourse, K.courseName, K.image, K.enrollId));
        }
        this.this$0.lessonAdapter.notifyDataSetChanged();
    }
}
