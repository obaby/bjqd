package cn.xports.qd2.circle;

import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.widget.EmptyRecyclerView;
import com.alipay.sdk.packet.d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: PostDetailActivity.kt */
final class PostDetailActivity$getCommentList$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ int $page;
    final /* synthetic */ PostDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PostDetailActivity$getCommentList$1(PostDetailActivity postDetailActivity, int i) {
        super(1);
        this.this$0 = postDetailActivity;
        this.$page = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        this.this$0._$_findCachedViewById(R.id.srfLayout).finishLoadMore();
        if (this.$page == 1) {
            this.this$0.commentList.clear();
        }
        this.this$0.pageNum = this.$page;
        this.this$0.hasNextPage = dataMap.getDataMap(d.k).getBooleanValue("hasNextPage");
        this.this$0.commentList.addAll(dataMap.getDataMap(d.k).getDataList("list"));
        this.this$0.adapter.notifyDataSetChanged();
        if (this.this$0.commentList.size() == 0) {
            this.this$0._$_findCachedViewById(R.id.srfLayout).setEnableLoadMore(false);
            EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) this.this$0._$_findCachedViewById(R.id.rvCommentList);
            Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCommentList");
            emptyRecyclerView.setVisibility(8);
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCommentTip);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvCommentTip");
            textView.setVisibility(0);
            return;
        }
        this.this$0._$_findCachedViewById(R.id.srfLayout).setEnableLoadMore(true);
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) this.this$0._$_findCachedViewById(R.id.rvCommentList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCommentList");
        emptyRecyclerView2.setVisibility(0);
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCommentTip);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCommentTip");
        textView2.setVisibility(8);
    }
}
