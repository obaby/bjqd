package cn.xports.qd2.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"cn/xports/qd2/util/ViewExt$loadMore$1", "Landroid/support/v7/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "newState", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewExt.kt */
public final class ViewExt$loadMore$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ Function0 $curPage;
    final /* synthetic */ Function0 $method;
    final /* synthetic */ Function0 $totalPage;

    ViewExt$loadMore$1(Function0 function0, Function0 function02, Function0 function03) {
        this.$curPage = function0;
        this.$totalPage = function02;
        this.$method = function03;
    }

    public void onScrollStateChanged(@Nullable RecyclerView recyclerView, int i) {
        if (i == 0 && recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager != null) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.getItemCount() - 1 && ((Number) this.$curPage.invoke()).intValue() < ((Number) this.$totalPage.invoke()).intValue()) {
                    this.$method.invoke();
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.LinearLayoutManager");
        }
    }
}
