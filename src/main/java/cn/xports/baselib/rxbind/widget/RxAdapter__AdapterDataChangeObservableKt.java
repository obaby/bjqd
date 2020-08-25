package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.Adapter;
import cn.xports.baselib.rxbind.InitialValueObservable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"dataChanges", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "T", "Landroid/widget/Adapter;", "(Landroid/widget/Adapter;)Lcn/xports/baselib/rxbind/InitialValueObservable;", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/widget/RxAdapter")
/* compiled from: AdapterDataChangeObservable.kt */
final /* synthetic */ class RxAdapter__AdapterDataChangeObservableKt {
    @NotNull
    @CheckResult
    public static final <T extends Adapter> InitialValueObservable<T> dataChanges(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "$this$dataChanges");
        return new AdapterDataChangeObservable<>(t);
    }
}
