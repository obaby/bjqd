package cn.xports.qd2.campaign;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.CampItem;
import cn.xports.qd2.entity.CampItemListResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/ChooseSportActivity$getCampItems$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CampItemListResult;", "next", "", "p0", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ChooseSportActivity.kt */
public final class ChooseSportActivity$getCampItems$1 extends ProcessObserver<CampItemListResult> {
    final /* synthetic */ ChooseSportActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChooseSportActivity$getCampItems$1(ChooseSportActivity chooseSportActivity, IView iView) {
        super(iView);
        this.this$0 = chooseSportActivity;
    }

    public void next(@Nullable CampItemListResult campItemListResult) {
        this.this$0.campItems.clear();
        if (campItemListResult != null) {
            List<CampItemListResult.CampPackage> packageList = campItemListResult.getPackageList();
            if (packageList != null) {
                for (CampItemListResult.CampPackage campPackage : packageList) {
                    List<CampItem> campItemList = campItemListResult.getCampItemList();
                    if (campItemList != null) {
                        for (CampItem campItem : campItemList) {
                            Intrinsics.checkExpressionValueIsNotNull(campItem, "it");
                            String valueOf = String.valueOf(campItem.getId());
                            Intrinsics.checkExpressionValueIsNotNull(campPackage, "p");
                            if (Intrinsics.areEqual(valueOf, campPackage.getCampItems())) {
                                campItem.addPackage(campPackage);
                            }
                        }
                    }
                }
            }
            this.this$0.campItems.addAll(campItemListResult.getCampItemList());
        }
        this.this$0.adapter.notifyDataSetChanged();
    }
}
