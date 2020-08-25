package cn.xports.baselib.rxbind.view;

import android.view.View;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "value", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 1, 15})
/* compiled from: ViewVisibilityConsumer.kt */
final class RxView__ViewVisibilityConsumerKt$visibility$3<T> implements Consumer<Boolean> {
    final /* synthetic */ View $this_visibility;
    final /* synthetic */ int $visibilityWhenFalse;

    RxView__ViewVisibilityConsumerKt$visibility$3(View view, int i) {
        this.$this_visibility = view;
        this.$visibilityWhenFalse = i;
    }

    public final void accept(Boolean bool) {
        View view = this.$this_visibility;
        Intrinsics.checkExpressionValueIsNotNull(bool, "value");
        view.setVisibility(bool.booleanValue() ? 0 : this.$visibilityWhenFalse);
    }
}
