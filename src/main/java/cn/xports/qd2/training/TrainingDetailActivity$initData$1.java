package cn.xports.qd2.training;

import cn.xports.entity.CardInfo;
import cn.xports.entity.PairEvent;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.SPUtils;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "t", "Lcn/xports/entity/PairEvent;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
final class TrainingDetailActivity$initData$1<T> implements Consumer<PairEvent> {
    final /* synthetic */ TrainingDetailActivity this$0;

    TrainingDetailActivity$initData$1(TrainingDetailActivity trainingDetailActivity) {
        this.this$0 = trainingDetailActivity;
    }

    public final void accept(PairEvent pairEvent) {
        if (pairEvent != null && Intrinsics.areEqual(pairEvent.getKey1(), this.this$0.getTAG())) {
            Object key2 = pairEvent.getKey2();
            if (key2 != null) {
                CardInfo cardInfo = (CardInfo) key2;
                TrainingDetailActivity trainingDetailActivity = this.this$0;
                String ecardNo = cardInfo.getEcardNo();
                Intrinsics.checkExpressionValueIsNotNull(ecardNo, "card.ecardNo");
                trainingDetailActivity.ecardNo = ecardNo;
                SPUtils.getInstance().put(K.custId, cardInfo.getVenueCustId());
                this.this$0.showCourseTip();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type cn.xports.entity.CardInfo");
        }
    }
}
