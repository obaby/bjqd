package cn.xports.venue;

import android.view.View;
import android.widget.TextView;
import cn.xports.qdplugin.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
final class VenueDetailActivity$initRecyclerView$1 implements View.OnClickListener {
    final /* synthetic */ VenueDetailActivity this$0;

    VenueDetailActivity$initRecyclerView$1(VenueDetailActivity venueDetailActivity) {
        this.this$0 = venueDetailActivity;
    }

    public final void onClick(View view) {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvOperation);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvOperation");
        if (Intrinsics.areEqual(textView.getText(), "展开更多")) {
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvOperation);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvOperation");
            textView2.setText("收起");
            VenueDetailActivity.access$getTicketAdapter$p(this.this$0).expand();
            return;
        }
        TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvOperation);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvOperation");
        textView3.setText("展开更多");
        VenueDetailActivity.access$getTicketAdapter$p(this.this$0).up();
    }
}
