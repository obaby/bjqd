package cn.xports.order;

import cn.xports.order.OrderPayContract;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "longTime", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
final class OrderPayPresenter$countDownTime$sub$1<T> implements Consumer<Long> {
    final /* synthetic */ Ref.LongRef $seconds;
    final /* synthetic */ OrderPayPresenter this$0;

    OrderPayPresenter$countDownTime$sub$1(OrderPayPresenter orderPayPresenter, Ref.LongRef longRef) {
        this.this$0 = orderPayPresenter;
        this.$seconds = longRef;
    }

    public final void accept(Long l) {
        long j = this.$seconds.element;
        Intrinsics.checkExpressionValueIsNotNull(l, "longTime");
        int longValue = (int) (j - l.longValue());
        int i = longValue / 60;
        int i2 = longValue % 60;
        OrderPayContract.View view = (OrderPayContract.View) this.this$0.getRootView();
        if (view != null) {
            view.showCountDownTime(i, i2);
        }
    }
}
