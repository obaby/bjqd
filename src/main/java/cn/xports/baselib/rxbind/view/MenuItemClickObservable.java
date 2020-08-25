package cn.xports.baselib.rxbind.view;

import android.view.MenuItem;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\u00022\u000e\u0010\n\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u000bH\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcn/xports/baselib/rxbind/view/MenuItemClickObservable;", "Lio/reactivex/Observable;", "", "menuItem", "Landroid/view/MenuItem;", "handled", "Lkotlin/Function1;", "", "(Landroid/view/MenuItem;Lkotlin/jvm/functions/Function1;)V", "subscribeActual", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: MenuItemClickObservable.kt */
final class MenuItemClickObservable extends Observable<Unit> {
    private final Function1<MenuItem, Boolean> handled;
    private final MenuItem menuItem;

    public MenuItemClickObservable(@NotNull MenuItem menuItem2, @NotNull Function1<? super MenuItem, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(menuItem2, "menuItem");
        Intrinsics.checkParameterIsNotNull(function1, "handled");
        this.menuItem = menuItem2;
        this.handled = function1;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super Unit> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            MenuItem.OnMenuItemClickListener listener = new Listener(this.menuItem, this.handled, observer);
            observer.onSubscribe((Disposable) listener);
            this.menuItem.setOnMenuItemClickListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\nH\u0014J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0004H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcn/xports/baselib/rxbind/view/MenuItemClickObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/view/MenuItem$OnMenuItemClickListener;", "menuItem", "Landroid/view/MenuItem;", "handled", "Lkotlin/Function1;", "", "observer", "Lio/reactivex/Observer;", "", "(Landroid/view/MenuItem;Lkotlin/jvm/functions/Function1;Lio/reactivex/Observer;)V", "onDispose", "onMenuItemClick", "item", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: MenuItemClickObservable.kt */
    private static final class Listener extends MainThreadDisposable implements MenuItem.OnMenuItemClickListener {
        private final Function1<MenuItem, Boolean> handled;
        private final MenuItem menuItem;
        private final Observer<? super Unit> observer;

        public Listener(@NotNull MenuItem menuItem2, @NotNull Function1<? super MenuItem, Boolean> function1, @NotNull Observer<? super Unit> observer2) {
            Intrinsics.checkParameterIsNotNull(menuItem2, "menuItem");
            Intrinsics.checkParameterIsNotNull(function1, "handled");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.menuItem = menuItem2;
            this.handled = function1;
            this.observer = observer2;
        }

        public boolean onMenuItemClick(@NotNull MenuItem menuItem2) {
            Intrinsics.checkParameterIsNotNull(menuItem2, "item");
            if (isDisposed()) {
                return false;
            }
            try {
                if (!((Boolean) this.handled.invoke(this.menuItem)).booleanValue()) {
                    return false;
                }
                this.observer.onNext(Unit.INSTANCE);
                return true;
            } catch (Exception e) {
                this.observer.onError(e);
                dispose();
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.menuItem.setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener) null);
        }
    }
}
