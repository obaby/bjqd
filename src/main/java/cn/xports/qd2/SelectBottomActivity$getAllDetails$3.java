package cn.xports.qd2;

import cn.xports.baselib.http.SingleProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CardInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002:\u00126\u00124\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002j\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0007`\u00060\u0001JF\u0010\b\u001a\u00020\t2<\u0010\n\u001a8\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0002j \u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0007\u0018\u0001`\u0006H\u0016¨\u0006\u000b"}, d2 = {"cn/xports/qd2/SelectBottomActivity$getAllDetails$3", "Lcn/xports/baselib/http/SingleProcessObserver;", "Ljava/util/HashMap;", "", "Ljava/util/ArrayList;", "Lcn/xports/entity/BalanceInfo;", "Lkotlin/collections/HashMap;", "Lkotlin/collections/ArrayList;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SelectBottomActivity.kt */
public final class SelectBottomActivity$getAllDetails$3 extends SingleProcessObserver<HashMap<String, ArrayList<BalanceInfo>>> {
    final /* synthetic */ HashMap $mapCards;
    final /* synthetic */ SelectBottomActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SelectBottomActivity$getAllDetails$3(SelectBottomActivity selectBottomActivity, HashMap hashMap, IView iView) {
        super(iView);
        this.this$0 = selectBottomActivity;
        this.$mapCards = hashMap;
    }

    public void next(@Nullable HashMap<String, ArrayList<BalanceInfo>> hashMap) {
        if (hashMap != null) {
            Set<String> keySet = hashMap.keySet();
            Intrinsics.checkExpressionValueIsNotNull(keySet, "keys");
            for (String str : keySet) {
                ArrayList arrayList = hashMap.get(str);
                CardInfo cardInfo = (CardInfo) this.$mapCards.get(str);
                if (cardInfo != null) {
                    cardInfo.setBalanceInfoList(arrayList);
                    cardInfo.setExpand(true);
                }
                if (arrayList != null) {
                    this.this$0.mItems.addAll(this.this$0.mItems.indexOf(cardInfo) + 1, arrayList);
                }
            }
            this.this$0.multiTypeAdapter.notifyDataSetChanged();
        }
    }
}
