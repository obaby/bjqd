package cn.xports.venue;

import android.view.View;
import cn.xports.base.Router;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueMainActivity.kt */
final class VenueMainActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ VenueMainActivity this$0;

    VenueMainActivity$initView$2(VenueMainActivity venueMainActivity) {
        this.this$0 = venueMainActivity;
    }

    public final void onClick(View view) {
        this.this$0.startActivity(Router.getIntent("/myCenter"));
    }
}
