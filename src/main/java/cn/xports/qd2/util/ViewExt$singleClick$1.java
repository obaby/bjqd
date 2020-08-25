package cn.xports.qd2.util;

import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "kotlin.jvm.PlatformType", "accept", "(Lkotlin/Unit;)V"}, k = 3, mv = {1, 1, 15})
/* compiled from: ViewExt.kt */
final class ViewExt$singleClick$1<T> implements Consumer<Unit> {
    final /* synthetic */ Function0 $click;

    ViewExt$singleClick$1(Function0 function0) {
        this.$click = function0;
    }

    public final void accept(Unit unit) {
        this.$click.invoke();
    }
}
