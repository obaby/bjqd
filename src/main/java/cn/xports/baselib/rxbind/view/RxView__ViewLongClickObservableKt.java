package cn.xports.baselib.rxbind.view;

import android.support.annotation.CheckResult;
import android.view.View;
import cn.xports.baselib.rxbind.internal.AlwaysTrue;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"longClicks", "Lio/reactivex/Observable;", "", "Landroid/view/View;", "handled", "Lkotlin/Function0;", "", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/view/RxView")
/* compiled from: ViewLongClickObservable.kt */
final /* synthetic */ class RxView__ViewLongClickObservableKt {
    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<Unit> longClicks(@NotNull View view) {
        return longClicks$default(view, (Function0) null, 1, (Object) null);
    }

    public static /* synthetic */ Observable longClicks$default(View view, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = (Function0) AlwaysTrue.INSTANCE;
        }
        return RxView.longClicks(view, function0);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<Unit> longClicks(@NotNull View view, @NotNull Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(view, "$this$longClicks");
        Intrinsics.checkParameterIsNotNull(function0, "handled");
        return (Observable) new ViewLongClickObservable(view, function0);
    }
}
