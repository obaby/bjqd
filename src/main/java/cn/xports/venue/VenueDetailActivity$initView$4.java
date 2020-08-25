package cn.xports.venue;

import android.support.design.widget.AppBarLayout;
import android.widget.TextView;
import cn.xports.qdplugin.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "appBarLayout", "Landroid/support/design/widget/AppBarLayout;", "kotlin.jvm.PlatformType", "i", "", "onOffsetChanged"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
final class VenueDetailActivity$initView$4 implements AppBarLayout.OnOffsetChangedListener {
    final /* synthetic */ VenueDetailActivity this$0;

    VenueDetailActivity$initView$4(VenueDetailActivity venueDetailActivity) {
        this.this$0 = venueDetailActivity;
    }

    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.toolbar_title);
            Intrinsics.checkExpressionValueIsNotNull(textView, "toolbar_title");
            textView.setVisibility(8);
            return;
        }
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "toolbar_title");
        textView2.setVisibility(0);
    }
}
