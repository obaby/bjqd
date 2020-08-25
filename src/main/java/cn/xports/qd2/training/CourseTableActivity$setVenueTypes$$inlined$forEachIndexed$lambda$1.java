package cn.xports.qd2.training;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/qd2/training/CourseTableActivity$setVenueTypes$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
final class CourseTableActivity$setVenueTypes$$inlined$forEachIndexed$lambda$1 implements View.OnClickListener {
    final /* synthetic */ CheckBox $typeCheckBox;
    final /* synthetic */ DataMap $venue;
    final /* synthetic */ CourseTableActivity this$0;

    CourseTableActivity$setVenueTypes$$inlined$forEachIndexed$lambda$1(CheckBox checkBox, DataMap dataMap, CourseTableActivity courseTableActivity) {
        this.$typeCheckBox = checkBox;
        this.$venue = dataMap;
        this.this$0 = courseTableActivity;
    }

    public final void onClick(View view) {
        this.$typeCheckBox.setEnabled(false);
        CourseTableActivity courseTableActivity = this.this$0;
        String string = this.$venue.getString(K.venueId);
        Intrinsics.checkExpressionValueIsNotNull(string, "venue.getString(K.venueId)");
        courseTableActivity.selectVenueId = string;
        CourseTableActivity courseTableActivity2 = this.this$0;
        String string2 = this.$venue.getString(K.studentIds);
        Intrinsics.checkExpressionValueIsNotNull(string2, "venue.getString(K.studentIds)");
        courseTableActivity2.selectStuIds = string2;
        this.this$0.getTable();
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llVenues);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llVenues");
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!Intrinsics.areEqual(((LinearLayout) this.this$0._$_findCachedViewById(R.id.llVenues)).getChildAt(i), this.$typeCheckBox)) {
                View childAt = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llVenues)).getChildAt(i);
                if (childAt != null) {
                    ((CheckBox) childAt).setChecked(false);
                    View childAt2 = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llVenues)).getChildAt(i);
                    Intrinsics.checkExpressionValueIsNotNull(childAt2, "llVenues.getChildAt(i)");
                    childAt2.setEnabled(true);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
                }
            }
        }
    }
}
