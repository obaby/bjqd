package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.Adapter;
import android.widget.AdapterView;
import cn.xports.baselib.rxbind.internal.AlwaysTrue;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¨\u0006\t"}, d2 = {"itemLongClicks", "Lio/reactivex/Observable;", "", "T", "Landroid/widget/Adapter;", "Landroid/widget/AdapterView;", "handled", "Lkotlin/Function0;", "", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/widget/RxAdapterView")
/* compiled from: AdapterViewItemLongClickObservable.kt */
final /* synthetic */ class RxAdapterView__AdapterViewItemLongClickObservableKt {
    @CheckResult
    @NotNull
    @JvmOverloads
    public static final <T extends Adapter> Observable<Integer> itemLongClicks(@NotNull AdapterView<T> adapterView) {
        return itemLongClicks$default(adapterView, (Function0) null, 1, (Object) null);
    }

    public static /* synthetic */ Observable itemLongClicks$default(AdapterView adapterView, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = (Function0) AlwaysTrue.INSTANCE;
        }
        return RxAdapterView.itemLongClicks(adapterView, function0);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final <T extends Adapter> Observable<Integer> itemLongClicks(@NotNull AdapterView<T> adapterView, @NotNull Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$this$itemLongClicks");
        Intrinsics.checkParameterIsNotNull(function0, "handled");
        return (Observable) new AdapterViewItemLongClickObservable(adapterView, function0);
    }
}
