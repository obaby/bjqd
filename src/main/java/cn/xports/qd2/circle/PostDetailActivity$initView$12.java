package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.CommentDeleteDialog;
import cn.xports.qd2.util.ViewExt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/baselib/bean/DataMap;", "kotlin.jvm.PlatformType", "position", "", "viewId", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initView$12<T> implements XBaseAdapter.OnItemClickListener<DataMap> {
    final /* synthetic */ PostDetailActivity this$0;

    PostDetailActivity$initView$12(PostDetailActivity postDetailActivity) {
        this.this$0 = postDetailActivity;
    }

    public final void onItemClick(final DataMap dataMap, int i, int i2) {
        if (i2 == 1) {
            PostDetailActivity postDetailActivity = this.this$0;
            Bundle bundle = ViewExt.set(new Bundle(), "circleId", this.this$0.circleId);
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCircleName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvCircleName");
            CharSequence text = textView.getText();
            Intrinsics.checkExpressionValueIsNotNull(text, "tvCircleName.text");
            Bundle bundle2 = ViewExt.set(bundle, "circleName", text);
            String string = dataMap.getString("accountId");
            Intrinsics.checkExpressionValueIsNotNull(string, "item.getString(\"accountId\")");
            postDetailActivity.startActivity(new Intent(postDetailActivity, CircleMyCenterActivity.class).putExtras(ViewExt.set(bundle2, "accountId", string)));
        } else if (Intrinsics.areEqual(dataMap.getString("accountId"), SPUtil.Companion.getINSTANCE().getStringValue("coAccountId"))) {
            new CommentDeleteDialog(this.this$0).setOnItemClickListener(new BaseBottomDialog.OnItemClickListener(this) {
                final /* synthetic */ PostDetailActivity$initView$12 this$0;

                {
                    this.this$0 = r1;
                }

                public final void onItemClick(int i, Object[] objArr) {
                    PostDetailActivity postDetailActivity = this.this$0.this$0;
                    String string = dataMap.getString("commentId");
                    Intrinsics.checkExpressionValueIsNotNull(string, "item.getString(\"commentId\")");
                    postDetailActivity.deleteComment(string);
                }
            }).show();
        }
    }
}
