package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.RadioGroup;
import cn.xports.baselib.rxbind.InitialValueObservable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"checkedChanges", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "", "Landroid/widget/RadioGroup;", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/widget/RxRadioGroup")
/* compiled from: RadioGroupCheckedChangeObservable.kt */
final /* synthetic */ class RxRadioGroup__RadioGroupCheckedChangeObservableKt {
    @NotNull
    @CheckResult
    public static final InitialValueObservable<Integer> checkedChanges(@NotNull RadioGroup radioGroup) {
        Intrinsics.checkParameterIsNotNull(radioGroup, "$this$checkedChanges");
        return new RadioGroupCheckedChangeObservable(radioGroup);
    }
}
