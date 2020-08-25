package cn.xports.baselib.rxbind.view;

import android.view.View;
import anet.channel.strategy.dispatch.DispatchConstants;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00022\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\nH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewAttachesObservable;", "Lio/reactivex/Observable;", "", "view", "Landroid/view/View;", "callOnAttach", "", "(Landroid/view/View;Z)V", "subscribeActual", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewAttachesObservable.kt */
final class ViewAttachesObservable extends Observable<Unit> {
    private final boolean callOnAttach;
    private final View view;

    public ViewAttachesObservable(@NotNull View view2, boolean z) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        this.view = view2;
        this.callOnAttach = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super Unit> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            View.OnAttachStateChangeListener listener = new Listener(this.view, this.callOnAttach, observer);
            observer.onSubscribe((Disposable) listener);
            this.view.addOnAttachStateChangeListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0000\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\tH\u0014J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0006\b\u0000\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewAttachesObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/view/View$OnAttachStateChangeListener;", "view", "Landroid/view/View;", "callOnAttach", "", "observer", "Lio/reactivex/Observer;", "", "(Landroid/view/View;ZLio/reactivex/Observer;)V", "onDispose", "onViewAttachedToWindow", "v", "onViewDetachedFromWindow", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ViewAttachesObservable.kt */
    private static final class Listener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        private final boolean callOnAttach;
        private final Observer<? super Unit> observer;
        private final View view;

        public Listener(@NotNull View view2, boolean z, @NotNull Observer<? super Unit> observer2) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = view2;
            this.callOnAttach = z;
            this.observer = observer2;
        }

        public void onViewAttachedToWindow(@NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view2, DispatchConstants.VERSION);
            if (this.callOnAttach && !isDisposed()) {
                this.observer.onNext(Unit.INSTANCE);
            }
        }

        public void onViewDetachedFromWindow(@NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view2, DispatchConstants.VERSION);
            if (!this.callOnAttach && !isDisposed()) {
                this.observer.onNext(Unit.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeOnAttachStateChangeListener(this);
        }
    }
}
