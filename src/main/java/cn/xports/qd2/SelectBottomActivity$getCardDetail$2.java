package cn.xports.qd2;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CardInfo;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00040\u0001J$\u0010\u0005\u001a\u00020\u00062\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0004H\u0016¨\u0006\b"}, d2 = {"cn/xports/qd2/SelectBottomActivity$getCardDetail$2", "Lcn/xports/baselib/http/ProcessObserver;", "Ljava/util/ArrayList;", "Lcn/xports/entity/BalanceInfo;", "Lkotlin/collections/ArrayList;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SelectBottomActivity.kt */
public final class SelectBottomActivity$getCardDetail$2 extends ProcessObserver<ArrayList<BalanceInfo>> {
    final /* synthetic */ CardInfo $cardInfo;
    final /* synthetic */ SelectBottomActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SelectBottomActivity$getCardDetail$2(SelectBottomActivity selectBottomActivity, CardInfo cardInfo, IView iView) {
        super(iView);
        this.this$0 = selectBottomActivity;
        this.$cardInfo = cardInfo;
    }

    public void next(@Nullable ArrayList<BalanceInfo> arrayList) {
        if (arrayList != null) {
            Collection collection = arrayList;
            if (!collection.isEmpty()) {
                this.this$0.mItems.addAll(this.this$0.mItems.indexOf(this.$cardInfo) + 1, collection);
                this.this$0.multiTypeAdapter.notifyDataSetChanged();
                this.$cardInfo.setBalanceInfoList(arrayList);
            }
        }
    }
}
