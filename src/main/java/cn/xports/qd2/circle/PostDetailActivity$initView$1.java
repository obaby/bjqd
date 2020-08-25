package cn.xports.qd2.circle;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/scwang/smartrefresh/layout/api/RefreshLayout;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$initView$1 extends Lambda implements Function1<RefreshLayout, Unit> {
    final /* synthetic */ PostDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PostDetailActivity$initView$1(PostDetailActivity postDetailActivity) {
        super(1);
        this.this$0 = postDetailActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RefreshLayout) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull RefreshLayout refreshLayout) {
        Intrinsics.checkParameterIsNotNull(refreshLayout, "it");
        this.this$0.getPostDetail();
        this.this$0.getCommentList(1);
    }
}
