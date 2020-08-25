package cn.xports.baselib.rxbind.view;

import android.view.View;
import anet.channel.strategy.dispatch.DispatchConstants;
import cn.xports.baselib.rxbind.InitialValueObservable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fH\u0014R\u0014\u0010\u0006\u001a\u00020\u00028TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewFocusChangeObservable;", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "initialValue", "getInitialValue", "()Ljava/lang/Boolean;", "subscribeListener", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewFocusChangeObservable.kt */
final class ViewFocusChangeObservable extends InitialValueObservable<Boolean> {
    private final View view;

    public ViewFocusChangeObservable(@NotNull View view2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        this.view = view2;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Boolean getInitialValue() {
        return Boolean.valueOf(this.view.hasFocus());
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(@NotNull Observer<? super Boolean> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        View.OnFocusChangeListener listener = new Listener(this.view, observer);
        observer.onSubscribe((Disposable) listener);
        this.view.setOnFocusChangeListener(listener);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0007H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewFocusChangeObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/view/View$OnFocusChangeListener;", "view", "Landroid/view/View;", "observer", "Lio/reactivex/Observer;", "", "(Landroid/view/View;Lio/reactivex/Observer;)V", "onDispose", "", "onFocusChange", "v", "hasFocus", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ViewFocusChangeObservable.kt */
    private static final class Listener extends MainThreadDisposable implements View.OnFocusChangeListener {
        private final Observer<? super Boolean> observer;
        private final View view;

        public Listener(@NotNull View view2, @NotNull Observer<? super Boolean> observer2) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = view2;
            this.observer = observer2;
        }

        public void onFocusChange(@NotNull View view2, boolean z) {
            Intrinsics.checkParameterIsNotNull(view2, DispatchConstants.VERSION);
            if (!isDisposed()) {
                this.observer.onNext(Boolean.valueOf(z));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnFocusChangeListener((View.OnFocusChangeListener) null);
        }
    }
}
