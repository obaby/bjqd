package cn.xports.baselib.rxbind.widget;

import android.widget.RadioGroup;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "value", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Integer;)V"}, k = 3, mv = {1, 1, 15})
/* compiled from: RadioGroupToggleCheckedConsumer.kt */
final class RxRadioGroup__RadioGroupToggleCheckedConsumerKt$checked$1<T> implements Consumer<Integer> {
    final /* synthetic */ RadioGroup $this_checked;

    RxRadioGroup__RadioGroupToggleCheckedConsumerKt$checked$1(RadioGroup radioGroup) {
        this.$this_checked = radioGroup;
    }

    public final void accept(Integer num) {
        if (num != null && num.intValue() == -1) {
            this.$this_checked.clearCheck();
            return;
        }
        RadioGroup radioGroup = this.$this_checked;
        if (num == null) {
            Intrinsics.throwNpe();
        }
        radioGroup.check(num.intValue());
    }
}
