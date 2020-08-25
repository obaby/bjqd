package cn.xports.qd2.circle;

import android.view.View;
import android.widget.TextView;
import cn.xports.base.GlobalDialog;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.KeyboardUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initView$8 implements View.OnClickListener {
    final /* synthetic */ PostDetailActivity this$0;

    PostDetailActivity$initView$8(PostDetailActivity postDetailActivity) {
        this.this$0 = postDetailActivity;
    }

    public final void onClick(View view) {
        if (!Intrinsics.areEqual(this.this$0.joinState, "2")) {
            new GlobalDialog(this.this$0, "您还没有加入圈子，加入圈子后才可以对帖子进行评价，去申请加入吧").setCancelVisible(false);
        } else if (!this.this$0.canComment) {
            StringBuilder sb = new StringBuilder();
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvName");
            sb.append(textView.getText());
            sb.append("已退出圈子或圈子已解散，无法进行评论！");
            new GlobalDialog(this.this$0, sb.toString()).setCancelVisible(false);
        } else {
            KeyboardUtils.showSoftInput(this.this$0);
        }
    }
}
