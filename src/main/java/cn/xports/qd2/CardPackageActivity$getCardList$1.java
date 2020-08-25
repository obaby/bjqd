package cn.xports.qd2;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.CardListResult;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/qd2/CardPackageActivity$getCardList$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/CardListResult;", "next", "", "p0", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardPackageActivity.kt */
public final class CardPackageActivity$getCardList$1 extends ProcessObserver<CardListResult> {
    final /* synthetic */ CardPackageActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardPackageActivity$getCardList$1(CardPackageActivity cardPackageActivity, IView iView) {
        super(iView);
        this.this$0 = cardPackageActivity;
    }

    public void next(@Nullable CardListResult cardListResult) {
        List<CardListResult.CardCenterInfo> cardList;
        if (!(cardListResult == null || (cardList = cardListResult.getCardList()) == null || cardList.size() <= 0)) {
            CardListResult.CardCenterInfo cardCenterInfo = cardList.get(0);
            Intrinsics.checkExpressionValueIsNotNull(cardCenterInfo, "get(0)");
            if (cardCenterInfo.getCardInfo() != null) {
                ArrayList access$getCardList$p = this.this$0.cardList;
                CardListResult.CardCenterInfo cardCenterInfo2 = cardList.get(0);
                Intrinsics.checkExpressionValueIsNotNull(cardCenterInfo2, "get(0)");
                access$getCardList$p.addAll(cardCenterInfo2.getCardInfo());
            }
        }
        this.this$0.cardAdapter.notifyDataSetChanged();
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        String str;
        super.onError(responseThrowable);
        CardPackageActivity cardPackageActivity = this.this$0;
        if (responseThrowable == null || (str = responseThrowable.getMessage()) == null) {
            str = "位置错误";
        }
        cardPackageActivity.showMsg(str);
    }
}
