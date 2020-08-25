package cn.xports.baselib.rxbind;

import io.reactivex.Observable;
import io.reactivex.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\rB\u0005¢\u0006\u0002\u0010\u0003J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0018\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u000bH\u0014J\u0018\u0010\f\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u000bH$R\u0012\u0010\u0004\u001a\u00028\u0000X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/InitialValueObservable;", "T", "Lio/reactivex/Observable;", "()V", "initialValue", "getInitialValue", "()Ljava/lang/Object;", "skipInitialValue", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "subscribeListener", "Skipped", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InitialValueObservable.kt */
public abstract class InitialValueObservable<T> extends Observable<T> {
    /* access modifiers changed from: protected */
    public abstract T getInitialValue();

    /* access modifiers changed from: protected */
    public abstract void subscribeListener(@NotNull Observer<? super T> observer);

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        subscribeListener(observer);
        observer.onNext(getInitialValue());
    }

    @NotNull
    public final Observable<T> skipInitialValue() {
        return (Observable) new Skipped();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcn/xports/baselib/rxbind/InitialValueObservable$Skipped;", "Lio/reactivex/Observable;", "(Lcn/xports/baselib/rxbind/InitialValueObservable;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InitialValueObservable.kt */
    private final class Skipped extends Observable<T> {
        public Skipped() {
        }

        /* access modifiers changed from: protected */
        public void subscribeActual(@NotNull Observer<? super T> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            InitialValueObservable.this.subscribeListener(observer);
        }
    }
}
