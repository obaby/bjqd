package cn.xports.qd2.circle;

import cn.xports.baselib.rxbind.widget.TextViewTextChangeEvent;
import cn.xports.qd2.R;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.ColorUtils;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEvent;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initView$9<T> implements Consumer<TextViewTextChangeEvent> {
    final /* synthetic */ PostDetailActivity this$0;

    PostDetailActivity$initView$9(PostDetailActivity postDetailActivity) {
        this.this$0 = postDetailActivity;
    }

    public final void accept(TextViewTextChangeEvent textViewTextChangeEvent) {
        if (StringsKt.trim(textViewTextChangeEvent.getText()).length() == 0) {
            CornerTextView cornerTextView = (CornerTextView) this.this$0._$_findCachedViewById(R.id.ctPost);
            Intrinsics.checkExpressionValueIsNotNull(cornerTextView, "ctPost");
            cornerTextView.setEnabled(false);
            ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctPost)).setBgColor(ColorUtils.getColor(R.color.gray_e0e));
            ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctPost)).setTextColor(ColorUtils.getColor(R.color.gray_737));
            return;
        }
        CornerTextView cornerTextView2 = (CornerTextView) this.this$0._$_findCachedViewById(R.id.ctPost);
        Intrinsics.checkExpressionValueIsNotNull(cornerTextView2, "ctPost");
        cornerTextView2.setEnabled(true);
        ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctPost)).setBgColor(ColorUtils.getColor(R.color.blue_2e6));
        ((CornerTextView) this.this$0._$_findCachedViewById(R.id.ctPost)).setTextColor(-1);
    }
}
