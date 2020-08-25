package cn.xports.qd2.circle;

import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$deleteLike$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ PostDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PostDetailActivity$deleteLike$1(PostDetailActivity postDetailActivity) {
        super(1);
        this.this$0 = postDetailActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        RxBus.get().post("DELETE_LIKE");
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCircleCare);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCircleCare");
        if (Intrinsics.areEqual(textView.getText(), "1")) {
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCircleCare);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCircleCare");
            textView2.setText("");
        } else {
            TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCircleCare);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvCircleCare");
            TextView textView4 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCircleCare);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "tvCircleCare");
            textView3.setText(String.valueOf(Integer.parseInt(textView4.getText().toString()) - 1));
        }
        this.this$0.showLike(false);
    }
}
