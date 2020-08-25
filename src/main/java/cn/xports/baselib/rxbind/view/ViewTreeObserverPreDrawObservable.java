package cn.xports.baselib.rxbind.view;

import android.view.View;
import android.view.ViewTreeObserver;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\u00022\u000e\u0010\n\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u000bH\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewTreeObserverPreDrawObservable;", "Lio/reactivex/Observable;", "", "view", "Landroid/view/View;", "proceedDrawingPass", "Lkotlin/Function0;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function0;)V", "subscribeActual", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewTreeObserverPreDrawObservable.kt */
final class ViewTreeObserverPreDrawObservable extends Observable<Unit> {
    private final Function0<Boolean> proceedDrawingPass;
    private final View view;

    public ViewTreeObserverPreDrawObservable(@NotNull View view2, @NotNull Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(function0, "proceedDrawingPass");
        this.view = view2;
        this.proceedDrawingPass = function0;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super Unit> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            ViewTreeObserver.OnPreDrawListener listener = new Listener(this.view, this.proceedDrawingPass, observer);
            observer.onSubscribe((Disposable) listener);
            this.view.getViewTreeObserver().addOnPreDrawListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\nH\u0014J\b\u0010\r\u001a\u00020\u0007H\u0016R\u0016\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewTreeObserverPreDrawObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "view", "Landroid/view/View;", "proceedDrawingPass", "Lkotlin/Function0;", "", "observer", "Lio/reactivex/Observer;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function0;Lio/reactivex/Observer;)V", "onDispose", "onPreDraw", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ViewTreeObserverPreDrawObservable.kt */
    private static final class Listener extends MainThreadDisposable implements ViewTreeObserver.OnPreDrawListener {
        private final Observer<? super Unit> observer;
        private final Function0<Boolean> proceedDrawingPass;
        private final View view;

        public Listener(@NotNull View view2, @NotNull Function0<Boolean> function0, @NotNull Observer<? super Unit> observer2) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(function0, "proceedDrawingPass");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = view2;
            this.proceedDrawingPass = function0;
            this.observer = observer2;
        }

        public boolean onPreDraw() {
            if (isDisposed()) {
                return true;
            }
            this.observer.onNext(Unit.INSTANCE);
            try {
                return ((Boolean) this.proceedDrawingPass.invoke()).booleanValue();
            } catch (Exception e) {
                this.observer.onError(e);
                dispose();
                return true;
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
    }
}
