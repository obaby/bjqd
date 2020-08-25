package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.util.ViewExt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initPostContent$2 implements View.OnClickListener {
    final /* synthetic */ DataMap $post;
    final /* synthetic */ PostDetailActivity this$0;

    PostDetailActivity$initPostContent$2(PostDetailActivity postDetailActivity, DataMap dataMap) {
        this.this$0 = postDetailActivity;
        this.$post = dataMap;
    }

    public final void onClick(View view) {
        PostDetailActivity postDetailActivity = this.this$0;
        Bundle bundle = new Bundle();
        String string = this.$post.getString("circleId");
        Intrinsics.checkExpressionValueIsNotNull(string, "post.getString(\"circleId\")");
        postDetailActivity.startActivity(new Intent(postDetailActivity, CircleDetailActivity.class).putExtras(ViewExt.set(bundle, CircleDetailActivity.CIRCLE_ID, string)));
    }
}
