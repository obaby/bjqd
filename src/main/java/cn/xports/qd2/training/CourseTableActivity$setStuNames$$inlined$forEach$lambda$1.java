package cn.xports.qd2.training;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/qd2/training/CourseTableActivity$setStuNames$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
final class CourseTableActivity$setStuNames$$inlined$forEach$lambda$1 implements View.OnClickListener {
    final /* synthetic */ String $key;
    final /* synthetic */ View $layout;
    final /* synthetic */ CheckBox $typeCheckBox;
    final /* synthetic */ CourseTableActivity this$0;

    CourseTableActivity$setStuNames$$inlined$forEach$lambda$1(CheckBox checkBox, String str, View view, CourseTableActivity courseTableActivity) {
        this.$typeCheckBox = checkBox;
        this.$key = str;
        this.$layout = view;
        this.this$0 = courseTableActivity;
    }

    public final void onClick(View view) {
        CheckBox checkBox = this.$typeCheckBox;
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "typeCheckBox");
        if (checkBox.isChecked()) {
            ArrayList arrayList = new ArrayList();
            for (DataMap dataMap : this.this$0.tableCourses) {
                String string = dataMap.getString(K.studentIds);
                Intrinsics.checkExpressionValueIsNotNull(string, "course.getString(K.studentIds)");
                String str = this.$key;
                Intrinsics.checkExpressionValueIsNotNull(str, "key");
                if (StringsKt.contains$default(string, str, false, 2, (Object) null)) {
                    arrayList.add(dataMap);
                }
            }
            this.this$0.refreshTableList(arrayList);
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llStudents);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llStudents");
            int childCount = linearLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (!Intrinsics.areEqual(((LinearLayout) this.this$0._$_findCachedViewById(R.id.llStudents)).getChildAt(i), this.$layout)) {
                    CheckBox checkBox2 = (CheckBox) ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llStudents)).getChildAt(i).findViewById(R.id.cb_student_name);
                    Intrinsics.checkExpressionValueIsNotNull(checkBox2, "checkBox");
                    checkBox2.setChecked(false);
                }
            }
            return;
        }
        this.this$0.refreshTableList(this.this$0.tableCourses);
    }
}
