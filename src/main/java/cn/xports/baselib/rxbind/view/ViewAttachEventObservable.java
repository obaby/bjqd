package cn.xports.baselib.rxbind.view;

import android.view.View;
import anet.channel.strategy.dispatch.DispatchConstants;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewAttachEventObservable;", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/view/ViewAttachEvent;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewAttachEventObservable.kt */
final class ViewAttachEventObservable extends Observable<ViewAttachEvent> {
    private final View view;

    public ViewAttachEventObservable(@NotNull View view2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        this.view = view2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super ViewAttachEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            View.OnAttachStateChangeListener listener = new Listener(this.view, observer);
            observer.onSubscribe((Disposable) listener);
            this.view.addOnAttachStateChangeListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewAttachEventObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/view/View$OnAttachStateChangeListener;", "view", "Landroid/view/View;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/view/ViewAttachEvent;", "(Landroid/view/View;Lio/reactivex/Observer;)V", "onDispose", "", "onViewAttachedToWindow", "v", "onViewDetachedFromWindow", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ViewAttachEventObservable.kt */
    private static final class Listener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        private final Observer<? super ViewAttachEvent> observer;
        private final View view;

        public Listener(@NotNull View view2, @NotNull Observer<? super ViewAttachEvent> observer2) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = view2;
            this.observer = observer2;
        }

        public void onViewAttachedToWindow(@NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view2, DispatchConstants.VERSION);
            if (!isDisposed()) {
                this.observer.onNext(new ViewAttachAttachedEvent(this.view));
            }
        }

        public void onViewDetachedFromWindow(@NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view2, DispatchConstants.VERSION);
            if (!isDisposed()) {
                this.observer.onNext(new ViewAttachDetachedEvent(this.view));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeOnAttachStateChangeListener(this);
        }
    }
}
