package cn.xports.pay;

import cn.xports.entity.PayResult;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcn/xports/entity/PayResult;", "it", "", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
final class PayPresenter$callAlipay$2<T, R> implements Function<T, R> {
    public static final PayPresenter$callAlipay$2 INSTANCE = new PayPresenter$callAlipay$2();

    PayPresenter$callAlipay$2() {
    }

    @NotNull
    public final PayResult apply(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "it");
        return new PayResult(str);
    }
}
