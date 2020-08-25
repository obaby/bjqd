package cn.xports.order;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.CardInfo;
import cn.xports.entity.PayCardResult;
import cn.xports.order.OrderPayContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/order/OrderPayPresenter$getPayCards$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/PayCardResult;", "next", "", "value", "onError", "e", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
public final class OrderPayPresenter$getPayCards$$inlined$apply$lambda$1 extends ProcessObserver<PayCardResult> {
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ OrderPayPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OrderPayPresenter$getPayCards$$inlined$apply$lambda$1(IView iView, OrderPayPresenter orderPayPresenter, String str) {
        super(iView);
        this.this$0 = orderPayPresenter;
        this.$tradeId$inlined = str;
    }

    public void next(@Nullable PayCardResult payCardResult) {
        String str;
        String str2 = "";
        if (payCardResult != null) {
            OrderPayContract.View view = (OrderPayContract.View) this.this$0.getRootView();
            if (view != null) {
                view.onCardList(payCardResult);
            }
            if (!Intrinsics.areEqual("1", payCardResult.getChooseCard())) {
                CardInfo primaryCard = payCardResult.getPrimaryCard();
                if (primaryCard == null || (str = primaryCard.getEcardNo()) == null) {
                    str = "";
                }
                str2 = str;
            }
        }
        this.this$0.getPayInfo(this.$tradeId$inlined, str2);
    }

    public void onError(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "e");
        super.onError(th);
        this.this$0.getPayInfo(this.$tradeId$inlined, "");
    }
}
