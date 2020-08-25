package cn.xports.qd2.circle;

import android.view.View;
import android.widget.TextView;
import cn.xports.qd2.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initView$5 implements View.OnClickListener {
    final /* synthetic */ PostDetailActivity this$0;

    PostDetailActivity$initView$5(PostDetailActivity postDetailActivity) {
        this.this$0 = postDetailActivity;
    }

    public final void onClick(View view) {
        ((TextView) this.this$0._$_findCachedViewById(R.id.tvCircleCare)).performClick();
    }
}
