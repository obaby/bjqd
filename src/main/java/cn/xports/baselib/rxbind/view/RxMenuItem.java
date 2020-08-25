package cn.xports.baselib.rxbind.view;

import android.support.annotation.CheckResult;
import android.view.MenuItem;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/view/RxMenuItem__MenuItemActionViewEventObservableKt", "cn/xports/baselib/rxbind/view/RxMenuItem__MenuItemClickObservableKt"}, k = 4, mv = {1, 1, 15})
public final class RxMenuItem {
    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<MenuItemActionViewEvent> actionViewEvents(@NotNull MenuItem menuItem) {
        return RxMenuItem__MenuItemActionViewEventObservableKt.actionViewEvents$default(menuItem, (Function1) null, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<MenuItemActionViewEvent> actionViewEvents(@NotNull MenuItem menuItem, @NotNull Function1<? super MenuItemActionViewEvent, Boolean> function1) {
        return RxMenuItem__MenuItemActionViewEventObservableKt.actionViewEvents(menuItem, function1);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<Unit> clicks(@NotNull MenuItem menuItem) {
        return RxMenuItem__MenuItemClickObservableKt.clicks$default(menuItem, (Function1) null, 1, (Object) null);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<Unit> clicks(@NotNull MenuItem menuItem, @NotNull Function1<? super MenuItem, Boolean> function1) {
        return RxMenuItem__MenuItemClickObservableKt.clicks(menuItem, function1);
    }
}
