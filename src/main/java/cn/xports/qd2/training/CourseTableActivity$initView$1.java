package cn.xports.qd2.training;

import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.qd2.entity.DateWeekday;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/qd2/entity/DateWeekday;", "kotlin.jvm.PlatformType", "viewId", "", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
final class CourseTableActivity$initView$1<T> implements XViewBinder.OnItemClickListener<DateWeekday> {
    final /* synthetic */ CourseTableActivity this$0;

    CourseTableActivity$initView$1(CourseTableActivity courseTableActivity) {
        this.this$0 = courseTableActivity;
    }

    public final void onItemClick(DateWeekday dateWeekday, int i) {
        CourseTableActivity courseTableActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(dateWeekday, "item");
        String date = dateWeekday.getDate();
        Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
        if (date != null) {
            String substring = date.substring(0, 10);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            courseTableActivity.selectDate = substring;
            this.this$0.getTable();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
