package cn.xports.venue;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
final class VenueDetailActivity$initRecyclerView$2 implements Runnable {
    final /* synthetic */ VenueDetailActivity this$0;

    VenueDetailActivity$initRecyclerView$2(VenueDetailActivity venueDetailActivity) {
        this.this$0 = venueDetailActivity;
    }

    public final void run() {
        ((RecyclerView) this.this$0._$_findCachedViewById(R.id.rvServiceList)).addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.State state) {
                Intrinsics.checkParameterIsNotNull(rect, "outRect");
                Intrinsics.checkParameterIsNotNull(view, "view");
                Intrinsics.checkParameterIsNotNull(recyclerView, "parent");
                Intrinsics.checkParameterIsNotNull(state, K.state);
                int dp2px = DensityUtil.dp2px(11.0f);
                rect.bottom = DensityUtil.dp2px(11.0f);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view) % 4;
                rect.right = dp2px;
            }
        });
    }
}
