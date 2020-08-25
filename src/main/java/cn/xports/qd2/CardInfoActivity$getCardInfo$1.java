package cn.xports.qd2;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.CardInfo;
import cn.xports.qd2.entity.CardDetailResult;
import cn.xports.widget.EmptyRecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/CardInfoActivity$getCardInfo$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CardDetailResult;", "next", "", "p0", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardInfoActivity.kt */
public final class CardInfoActivity$getCardInfo$1 extends ProcessObserver<CardDetailResult> {
    final /* synthetic */ CardInfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardInfoActivity$getCardInfo$1(CardInfoActivity cardInfoActivity, IView iView) {
        super(iView);
        this.this$0 = cardInfoActivity;
    }

    public void next(@Nullable CardDetailResult cardDetailResult) {
        if (cardDetailResult != null) {
            this.this$0.depositInfos.addAll(cardDetailResult.getDepositInfo());
            EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) this.this$0._$_findCachedViewById(R.id.rvCardInfoList);
            Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCardInfoList");
            emptyRecyclerView.getAdapter().notifyDataSetChanged();
            CardInfoActivity cardInfoActivity = this.this$0;
            CardInfo cardInfo = cardDetailResult.getCardInfo();
            Intrinsics.checkExpressionValueIsNotNull(cardInfo, "cardInfo");
            String centerName = cardInfo.getCenterName();
            Intrinsics.checkExpressionValueIsNotNull(centerName, "cardInfo.centerName");
            CardInfo cardInfo2 = cardDetailResult.getCardInfo();
            Intrinsics.checkExpressionValueIsNotNull(cardInfo2, "cardInfo");
            String ecardNo = cardInfo2.getEcardNo();
            Intrinsics.checkExpressionValueIsNotNull(ecardNo, "cardInfo.ecardNo");
            cardInfoActivity.setCardView(centerName, ecardNo, (int) cardDetailResult.getEcardBalance());
        }
    }
}
