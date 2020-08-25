package cn.xports.qd2.circle;

import android.widget.EditText;
import android.widget.LinearLayout;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.KeyboardUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "onSoftInputChanged"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initView$7 implements KeyboardUtils.OnSoftInputChangedListener {
    final /* synthetic */ PostDetailActivity this$0;

    PostDetailActivity$initView$7(PostDetailActivity postDetailActivity) {
        this.this$0 = postDetailActivity;
    }

    public final void onSoftInputChanged(int i) {
        if (KeyboardUtils.isSoftInputVisible(this.this$0)) {
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llCommentInput);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llCommentInput");
            linearLayout.setVisibility(0);
            ((EditText) this.this$0._$_findCachedViewById(R.id.etComment)).requestFocus();
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llCommentInput);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llCommentInput");
        linearLayout2.setVisibility(8);
    }
}
