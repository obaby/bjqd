package cn.xports.baselib.rxbind.view;

import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@RequiresApi(16)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00022\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewTreeObserverDrawObservable;", "Lio/reactivex/Observable;", "", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "subscribeActual", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewTreeObserverDrawObservable.kt */
final class ViewTreeObserverDrawObservable extends Observable<Unit> {
    private final View view;

    public ViewTreeObserverDrawObservable(@NotNull View view2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        this.view = view2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super Unit> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            ViewTreeObserver.OnDrawListener listener = new Listener(this.view, observer);
            observer.onSubscribe((Disposable) listener);
            this.view.getViewTreeObserver().addOnDrawListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0007H\u0014J\b\u0010\n\u001a\u00020\u0007H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewTreeObserverDrawObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/view/ViewTreeObserver$OnDrawListener;", "view", "Landroid/view/View;", "observer", "Lio/reactivex/Observer;", "", "(Landroid/view/View;Lio/reactivex/Observer;)V", "onDispose", "onDraw", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ViewTreeObserverDrawObservable.kt */
    private static final class Listener extends MainThreadDisposable implements ViewTreeObserver.OnDrawListener {
        private final Observer<? super Unit> observer;
        private final View view;

        public Listener(@NotNull View view2, @NotNull Observer<? super Unit> observer2) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = view2;
            this.observer = observer2;
        }

        public void onDraw() {
            if (!isDisposed()) {
                this.observer.onNext(Unit.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.getViewTreeObserver().removeOnDrawListener(this);
        }
    }
}
