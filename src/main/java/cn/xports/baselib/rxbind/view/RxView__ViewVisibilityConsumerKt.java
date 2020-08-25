package cn.xports.baselib.rxbind.view;

import android.support.annotation.CheckResult;
import android.view.View;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u001e\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"visibility", "Lio/reactivex/functions/Consumer;", "", "Landroid/view/View;", "visibilityWhenFalse", "", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/view/RxView")
/* compiled from: ViewVisibilityConsumer.kt */
final /* synthetic */ class RxView__ViewVisibilityConsumerKt {
    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Consumer<? super Boolean> visibility(@NotNull View view) {
        return visibility$default(view, 0, 1, (Object) null);
    }

    public static /* synthetic */ Consumer visibility$default(View view, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8;
        }
        return RxView.visibility(view, i);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Consumer<? super Boolean> visibility(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "$this$visibility");
        boolean z = false;
        if (i != 0) {
            if (i == 4 || i == 8) {
                z = true;
            }
            if (z) {
                return (Consumer) new RxView__ViewVisibilityConsumerKt$visibility$3<>(view, i);
            }
            throw new IllegalArgumentException("Must set visibility to INVISIBLE or GONE when false.".toString());
        }
        throw new IllegalArgumentException("Setting visibility to VISIBLE when false would have no effect.".toString());
    }
}
