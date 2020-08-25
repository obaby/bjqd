package cn.xports.order;

import com.alipay.sdk.app.PayTask;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "it", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
final class OrderPayActivity$callAlipay$subscription$1<T, R> implements Function<T, R> {
    final /* synthetic */ OrderPayActivity this$0;

    OrderPayActivity$callAlipay$subscription$1(OrderPayActivity orderPayActivity) {
        this.this$0 = orderPayActivity;
    }

    public final String apply(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "it");
        return new PayTask(this.this$0).pay(str, true);
    }
}
