package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.CompoundButton;
import cn.xports.baselib.rxbind.InitialValueObservable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/widget/RxCompoundButton__CompoundButtonCheckedChangeObservableKt"}, k = 4, mv = {1, 1, 15})
/* compiled from: CompoundButtonCheckedChangeObservable.kt */
public final class RxCompoundButton {
    @NotNull
    @CheckResult
    public static final InitialValueObservable<Boolean> checkedChanges(@NotNull CompoundButton compoundButton) {
        return RxCompoundButton__CompoundButtonCheckedChangeObservableKt.checkedChanges(compoundButton);
    }
}
