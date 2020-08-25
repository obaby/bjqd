package cn.xports.baselib.rxbind.widget;

import android.widget.PopupMenu;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00022\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcn/xports/baselib/rxbind/widget/PopupMenuDismissObservable;", "Lio/reactivex/Observable;", "", "view", "Landroid/widget/PopupMenu;", "(Landroid/widget/PopupMenu;)V", "subscribeActual", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PopupMenuDismissObservable.kt */
final class PopupMenuDismissObservable extends Observable<Unit> {
    private final PopupMenu view;

    public PopupMenuDismissObservable(@NotNull PopupMenu popupMenu) {
        Intrinsics.checkParameterIsNotNull(popupMenu, "view");
        this.view = popupMenu;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super Unit> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            Disposable listener = new Listener(this.view, observer);
            this.view.setOnDismissListener((PopupMenu.OnDismissListener) listener);
            observer.onSubscribe(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0014R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcn/xports/baselib/rxbind/widget/PopupMenuDismissObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/PopupMenu$OnDismissListener;", "view", "Landroid/widget/PopupMenu;", "observer", "Lio/reactivex/Observer;", "", "(Landroid/widget/PopupMenu;Lio/reactivex/Observer;)V", "onDismiss", "popupMenu", "onDispose", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: PopupMenuDismissObservable.kt */
    private static final class Listener extends MainThreadDisposable implements PopupMenu.OnDismissListener {
        private final Observer<? super Unit> observer;
        private final PopupMenu view;

        public Listener(@NotNull PopupMenu popupMenu, @NotNull Observer<? super Unit> observer2) {
            Intrinsics.checkParameterIsNotNull(popupMenu, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = popupMenu;
            this.observer = observer2;
        }

        public void onDismiss(@NotNull PopupMenu popupMenu) {
            Intrinsics.checkParameterIsNotNull(popupMenu, "popupMenu");
            if (!isDisposed()) {
                this.observer.onNext(Unit.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnDismissListener((PopupMenu.OnDismissListener) null);
        }
    }
}
