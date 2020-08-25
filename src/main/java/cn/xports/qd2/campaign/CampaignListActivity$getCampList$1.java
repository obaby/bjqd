package cn.xports.qd2.campaign;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.CampaignInfo;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CampaignListResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/CampaignListActivity$getCampList$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CampaignListResult;", "next", "", "p0", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CampaignListActivity.kt */
public final class CampaignListActivity$getCampList$1 extends ProcessObserver<CampaignListResult> {
    final /* synthetic */ int $pageNo;
    final /* synthetic */ CampaignListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CampaignListActivity$getCampList$1(CampaignListActivity campaignListActivity, int i, IView iView) {
        super(iView);
        this.this$0 = campaignListActivity;
        this.$pageNo = i;
    }

    public void next(@Nullable CampaignListResult campaignListResult) {
        List<CampaignInfo> campaignList;
        this.this$0.totalPage = campaignListResult != null ? campaignListResult.getTotalPage() : 1;
        this.this$0.curPage = this.$pageNo;
        if (!(campaignListResult == null || (campaignList = campaignListResult.getCampaignList()) == null)) {
            this.this$0.campaignList.addAll(campaignList);
        }
        this.this$0.campaignsAdpater.notifyDataSetChanged();
        if (this.this$0.campaignList.isEmpty()) {
            RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvSportList);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvSportList");
            recyclerView.setVisibility(8);
            View _$_findCachedViewById = this.this$0._$_findCachedViewById(R.id.noData);
            Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "noData");
            _$_findCachedViewById.setVisibility(0);
            return;
        }
        RecyclerView recyclerView2 = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvSportList");
        recyclerView2.setVisibility(0);
        View _$_findCachedViewById2 = this.this$0._$_findCachedViewById(R.id.noData);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "noData");
        _$_findCachedViewById2.setVisibility(8);
    }
}
