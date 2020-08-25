package cn.xports.venue;

import cn.xports.qdplugin.R;
import cn.xports.widget.PointIndexView;
import cn.xports.widget.pagelayout.PagerGridLayoutManager;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b¸\u0006\u0000"}, d2 = {"cn/xports/venue/VenueDetailActivity$onGetFieldSale$1$2", "Lcn/xports/widget/pagelayout/PagerGridLayoutManager$PageListener;", "onPageSelect", "", "pageIndex", "", "onPageSizeChanged", "pageSize", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
public final class VenueDetailActivity$onGetFieldSale$$inlined$apply$lambda$1 implements PagerGridLayoutManager.PageListener {
    final /* synthetic */ VenueDetailActivity this$0;

    public void onPageSizeChanged(int i) {
    }

    VenueDetailActivity$onGetFieldSale$$inlined$apply$lambda$1(VenueDetailActivity venueDetailActivity) {
        this.this$0 = venueDetailActivity;
    }

    public void onPageSelect(int i) {
        ((PointIndexView) this.this$0._$_findCachedViewById(R.id.pointIndex)).setIndex(i);
    }
}
