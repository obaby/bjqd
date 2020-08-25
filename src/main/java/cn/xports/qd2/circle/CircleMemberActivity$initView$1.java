package cn.xports.qd2.circle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CircleMemberActivity.kt */
final class CircleMemberActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ CircleMemberActivity this$0;

    CircleMemberActivity$initView$1(CircleMemberActivity circleMemberActivity) {
        this.this$0 = circleMemberActivity;
    }

    public final void onClick(View view) {
        ImageView access$getMIvRight$p = this.this$0.mIvRight;
        Intrinsics.checkExpressionValueIsNotNull(access$getMIvRight$p, "mIvRight");
        access$getMIvRight$p.setVisibility(8);
        TextView access$getMTvRight$p = this.this$0.mTvRight;
        Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p, "mTvRight");
        access$getMTvRight$p.setText("取消");
        TextView access$getMTvRight$p2 = this.this$0.mTvRight;
        Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p2, "mTvRight");
        access$getMTvRight$p2.setTextSize(17.0f);
        TextView access$getMTvRight$p3 = this.this$0.mTvRight;
        Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p3, "mTvRight");
        access$getMTvRight$p3.setVisibility(0);
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSelectAll);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llSelectAll");
        linearLayout.setVisibility(0);
        this.this$0.adapter.setCanSelect(true).notifyDataSetChanged();
    }
}
