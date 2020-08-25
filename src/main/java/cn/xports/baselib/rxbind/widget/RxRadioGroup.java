package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.RadioGroup;
import cn.xports.baselib.rxbind.InitialValueObservable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/widget/RxRadioGroup__RadioGroupCheckedChangeObservableKt", "cn/xports/baselib/rxbind/widget/RxRadioGroup__RadioGroupToggleCheckedConsumerKt"}, k = 4, mv = {1, 1, 15})
public final class RxRadioGroup {
    @NotNull
    @CheckResult
    public static final Consumer<? super Integer> checked(@NotNull RadioGroup radioGroup) {
        return RxRadioGroup__RadioGroupToggleCheckedConsumerKt.checked(radioGroup);
    }

    @NotNull
    @CheckResult
    public static final InitialValueObservable<Integer> checkedChanges(@NotNull RadioGroup radioGroup) {
        return RxRadioGroup__RadioGroupCheckedChangeObservableKt.checkedChanges(radioGroup);
    }
}
