package cn.xports.qd2.campaign;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.qd2.R;
import cn.xports.qd2.widget.ElementView;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.ColorUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
final class AddMemberActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ AddMemberActivity this$0;

    AddMemberActivity$initView$1(AddMemberActivity addMemberActivity) {
        this.this$0 = addMemberActivity;
    }

    public final void onClick(View view) {
        TextView access$getMTvRight$p = this.this$0.mTvRight;
        Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p, "mTvRight");
        if (Intrinsics.areEqual(access$getMTvRight$p.getText().toString(), "编辑")) {
            TextView access$getMTvRight$p2 = this.this$0.mTvRight;
            Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p2, "mTvRight");
            access$getMTvRight$p2.setText("取消编辑");
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llSignElements");
            for (ElementView enable : SignInfoActivityKt.getElementViews(linearLayout)) {
                enable.setEnable(true);
            }
            CornerTextView cornerTextView = (CornerTextView) this.this$0._$_findCachedViewById(R.id.ctSave);
            Intrinsics.checkExpressionValueIsNotNull(cornerTextView, "ctSave");
            cornerTextView.setEnabled(true);
            ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctSave)).setBgColor(ColorUtils.getColor(R.color.blue_2e6));
            return;
        }
        TextView access$getMTvRight$p3 = this.this$0.mTvRight;
        Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p3, "mTvRight");
        access$getMTvRight$p3.setText("编辑");
        LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llSignElements");
        for (ElementView enable2 : SignInfoActivityKt.getElementViews(linearLayout2)) {
            enable2.setEnable(false);
        }
        CornerTextView cornerTextView2 = (CornerTextView) this.this$0._$_findCachedViewById(R.id.ctSave);
        Intrinsics.checkExpressionValueIsNotNull(cornerTextView2, "ctSave");
        cornerTextView2.setEnabled(false);
        ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctSave)).setBgColor(Color.parseColor("#dedede"));
    }
}
