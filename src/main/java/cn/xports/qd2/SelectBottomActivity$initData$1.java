package cn.xports.qd2;

import cn.xports.baselib.util.RxBus;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CardInfo;
import cn.xports.entity.PairEvent;
import cn.xports.qd2.adapter.CardSelectBinder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.drakeet.multitype.Items;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/qd2/SelectBottomActivity$initData$1", "Lcn/xports/qd2/adapter/CardSelectBinder$OnItemSelectClick;", "onClick", "", "cardInfo", "Lcn/xports/entity/CardInfo;", "onSelect", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SelectBottomActivity.kt */
public final class SelectBottomActivity$initData$1 implements CardSelectBinder.OnItemSelectClick {
    final /* synthetic */ SelectBottomActivity this$0;

    SelectBottomActivity$initData$1(SelectBottomActivity selectBottomActivity) {
        this.this$0 = selectBottomActivity;
    }

    public void onClick(@Nullable CardInfo cardInfo) {
        if (cardInfo == null) {
            return;
        }
        if (cardInfo.isExpand()) {
            cardInfo.setExpand(false);
            if (cardInfo.getBalanceInfoList() != null) {
                Items access$getMItems$p = this.this$0.mItems;
                List<BalanceInfo> balanceInfoList = cardInfo.getBalanceInfoList();
                Intrinsics.checkExpressionValueIsNotNull(balanceInfoList, "cardInfo.balanceInfoList");
                access$getMItems$p.removeAll(balanceInfoList);
            }
            this.this$0.multiTypeAdapter.notifyDataSetChanged();
            return;
        }
        cardInfo.setExpand(true);
        if (cardInfo.getBalanceInfoList() != null) {
            this.this$0.mItems.addAll(this.this$0.mItems.indexOf(cardInfo) + 1, cardInfo.getBalanceInfoList());
            this.this$0.multiTypeAdapter.notifyDataSetChanged();
            return;
        }
        this.this$0.getCardDetail(cardInfo);
    }

    public void onSelect(@Nullable CardInfo cardInfo) {
        this.this$0.card = cardInfo;
        if (this.this$0.card != null) {
            RxBus.get().post(new PairEvent(this.this$0.sourceName, this.this$0.card));
        }
        this.this$0.finish();
    }
}
