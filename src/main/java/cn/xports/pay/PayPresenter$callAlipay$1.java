package cn.xports.pay;

import android.app.Activity;
import cn.xports.baselib.mvp.IView;
import com.alipay.sdk.app.PayTask;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "it", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
final class PayPresenter$callAlipay$1<T, R> implements Function<T, R> {
    final /* synthetic */ PayPresenter this$0;

    PayPresenter$callAlipay$1(PayPresenter payPresenter) {
        this.this$0 = payPresenter;
    }

    public final String apply(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "it");
        IView rootView = this.this$0.getRootView();
        if (rootView != null) {
            return new PayTask((Activity) rootView).pay(str, true);
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }
}
