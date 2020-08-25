package cn.xports.baselib.rxbind.view;

import android.support.annotation.CheckResult;
import android.support.annotation.RequiresApi;
import android.view.View;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"draws", "Lio/reactivex/Observable;", "", "Landroid/view/View;", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/view/RxView")
/* compiled from: ViewTreeObserverDrawObservable.kt */
final /* synthetic */ class RxView__ViewTreeObserverDrawObservableKt {
    @RequiresApi(16)
    @CheckResult
    @NotNull
    public static final Observable<Unit> draws(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$draws");
        return (Observable) new ViewTreeObserverDrawObservable(view);
    }
}
