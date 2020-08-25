package cn.xports.qd2.training;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.mvp.CourseResvPresenter;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/qd2/training/CourseResvActivity$setUpTypes$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: CourseResvActivity.kt */
final class CourseResvActivity$setUpTypes$$inlined$forEachIndexed$lambda$1 implements View.OnClickListener {
    final /* synthetic */ DataMap $course;
    final /* synthetic */ CheckBox $typeCheckBox;
    final /* synthetic */ CourseResvActivity this$0;

    CourseResvActivity$setUpTypes$$inlined$forEachIndexed$lambda$1(DataMap dataMap, CheckBox checkBox, CourseResvActivity courseResvActivity) {
        this.$course = dataMap;
        this.$typeCheckBox = checkBox;
        this.this$0 = courseResvActivity;
    }

    public final void onClick(View view) {
        this.this$0.selectCourse = this.$course;
        this.$typeCheckBox.setEnabled(false);
        CourseResvPresenter courseResvPresenter = (CourseResvPresenter) this.this$0.getPresenter();
        if (courseResvPresenter != null) {
            courseResvPresenter.getCourseSchedule(this.$course.getString(K.coachId), this.$course.getString(K.enrollId));
        }
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llCourseTypes);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llCourseTypes");
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!Intrinsics.areEqual(((LinearLayout) this.this$0._$_findCachedViewById(R.id.llCourseTypes)).getChildAt(i), this.$typeCheckBox)) {
                View childAt = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llCourseTypes)).getChildAt(i);
                if (childAt != null) {
                    ((CheckBox) childAt).setChecked(false);
                    View childAt2 = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llCourseTypes)).getChildAt(i);
                    Intrinsics.checkExpressionValueIsNotNull(childAt2, "llCourseTypes.getChildAt(i)");
                    childAt2.setEnabled(true);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
                }
            }
        }
    }
}
