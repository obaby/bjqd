package cn.xports.qd2.campaign;

import android.widget.RelativeLayout;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.CampaignInfo;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CampaignListResult;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/qd2/campaign/CampaignListActivity$getHot$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CampaignListResult;", "next", "", "p0", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CampaignListActivity.kt */
public final class CampaignListActivity$getHot$1 extends ProcessObserver<CampaignListResult> {
    final /* synthetic */ CampaignListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CampaignListActivity$getHot$1(CampaignListActivity campaignListActivity, IView iView) {
        super(iView);
        this.this$0 = campaignListActivity;
    }

    public void next(@Nullable CampaignListResult campaignListResult) {
        List<CampaignInfo> campaignList;
        ArrayList arrayList = new ArrayList();
        if (!(campaignListResult == null || (campaignList = campaignListResult.getCampaignList()) == null)) {
            for (CampaignInfo campaignInfo : campaignList) {
                Intrinsics.checkExpressionValueIsNotNull(campaignInfo, "it");
                arrayList.add(campaignInfo.getCoverImg());
            }
        }
        if (arrayList.isEmpty()) {
            RelativeLayout relativeLayout = (RelativeLayout) this.this$0._$_findCachedViewById(R.id.rlTop);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlTop");
            relativeLayout.setVisibility(8);
        }
        List list = arrayList;
        this.this$0.createRound(list);
        ((BGABanner) this.this$0._$_findCachedViewById(R.id.bgaBanner)).setData(list, (List<String>) null);
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        RelativeLayout relativeLayout = (RelativeLayout) this.this$0._$_findCachedViewById(R.id.rlTop);
        Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlTop");
        relativeLayout.setVisibility(8);
    }
}
