package cn.xports.baselib.rxbind.widget;

import android.view.MenuItem;
import android.widget.PopupMenu;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcn/xports/baselib/rxbind/widget/PopupMenuItemClickObservable;", "Lio/reactivex/Observable;", "Landroid/view/MenuItem;", "view", "Landroid/widget/PopupMenu;", "(Landroid/widget/PopupMenu;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PopupMenuItemClickObservable.kt */
final class PopupMenuItemClickObservable extends Observable<MenuItem> {
    private final PopupMenu view;

    public PopupMenuItemClickObservable(@NotNull PopupMenu popupMenu) {
        Intrinsics.checkParameterIsNotNull(popupMenu, "view");
        this.view = popupMenu;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super MenuItem> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            Disposable listener = new Listener(this.view, observer);
            this.view.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) listener);
            observer.onSubscribe(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/PopupMenuItemClickObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/PopupMenu$OnMenuItemClickListener;", "view", "Landroid/widget/PopupMenu;", "observer", "Lio/reactivex/Observer;", "Landroid/view/MenuItem;", "(Landroid/widget/PopupMenu;Lio/reactivex/Observer;)V", "onDispose", "", "onMenuItemClick", "", "menuItem", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: PopupMenuItemClickObservable.kt */
    private static final class Listener extends MainThreadDisposable implements PopupMenu.OnMenuItemClickListener {
        private final Observer<? super MenuItem> observer;
        private final PopupMenu view;

        public Listener(@NotNull PopupMenu popupMenu, @NotNull Observer<? super MenuItem> observer2) {
            Intrinsics.checkParameterIsNotNull(popupMenu, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = popupMenu;
            this.observer = observer2;
        }

        public boolean onMenuItemClick(@NotNull MenuItem menuItem) {
            Intrinsics.checkParameterIsNotNull(menuItem, "menuItem");
            if (isDisposed()) {
                return false;
            }
            this.observer.onNext(menuItem);
            return true;
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) null);
        }
    }
}
