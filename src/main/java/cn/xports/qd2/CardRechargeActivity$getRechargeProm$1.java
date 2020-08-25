package cn.xports.qd2;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.RechargePromotion;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/CardRechargeActivity$getRechargeProm$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/RechargePromotion;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
public final class CardRechargeActivity$getRechargeProm$1 extends ProcessObserver<RechargePromotion> {
    final /* synthetic */ List $priceList;
    final /* synthetic */ CardRechargeActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardRechargeActivity$getRechargeProm$1(CardRechargeActivity cardRechargeActivity, List list, IView iView) {
        super(iView);
        this.this$0 = cardRechargeActivity;
        this.$priceList = list;
    }

    public void next(@Nullable RechargePromotion rechargePromotion) {
        if (rechargePromotion != null) {
            this.this$0.curRechargePromotion = rechargePromotion;
            this.this$0.setPromTip(rechargePromotion);
            this.this$0.getChargeValue(this.$priceList, rechargePromotion);
        }
    }
}
