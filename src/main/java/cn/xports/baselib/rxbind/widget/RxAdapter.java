package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.Adapter;
import cn.xports.baselib.rxbind.InitialValueObservable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/widget/RxAdapter__AdapterDataChangeObservableKt"}, k = 4, mv = {1, 1, 15})
/* compiled from: AdapterDataChangeObservable.kt */
public final class RxAdapter {
    @NotNull
    @CheckResult
    public static final <T extends Adapter> InitialValueObservable<T> dataChanges(@NotNull T t) {
        return RxAdapter__AdapterDataChangeObservableKt.dataChanges(t);
    }
}
