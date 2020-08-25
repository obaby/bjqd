package cn.xports.baselib.rxbind.widget;

import android.widget.CompoundButton;
import cn.xports.baselib.rxbind.InitialValueObservable;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fH\u0014R\u0014\u0010\u0006\u001a\u00020\u00028TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/CompoundButtonCheckedChangeObservable;", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "", "view", "Landroid/widget/CompoundButton;", "(Landroid/widget/CompoundButton;)V", "initialValue", "getInitialValue", "()Ljava/lang/Boolean;", "subscribeListener", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CompoundButtonCheckedChangeObservable.kt */
final class CompoundButtonCheckedChangeObservable extends InitialValueObservable<Boolean> {
    private final CompoundButton view;

    public CompoundButtonCheckedChangeObservable(@NotNull CompoundButton compoundButton) {
        Intrinsics.checkParameterIsNotNull(compoundButton, "view");
        this.view = compoundButton;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(@NotNull Observer<? super Boolean> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            CompoundButton.OnCheckedChangeListener listener = new Listener(this.view, observer);
            observer.onSubscribe((Disposable) listener);
            this.view.setOnCheckedChangeListener(listener);
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Boolean getInitialValue() {
        return Boolean.valueOf(this.view.isChecked());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0007H\u0016J\b\u0010\r\u001a\u00020\nH\u0014R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/CompoundButtonCheckedChangeObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "view", "Landroid/widget/CompoundButton;", "observer", "Lio/reactivex/Observer;", "", "(Landroid/widget/CompoundButton;Lio/reactivex/Observer;)V", "onCheckedChanged", "", "compoundButton", "isChecked", "onDispose", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: CompoundButtonCheckedChangeObservable.kt */
    private static final class Listener extends MainThreadDisposable implements CompoundButton.OnCheckedChangeListener {
        private final Observer<? super Boolean> observer;
        private final CompoundButton view;

        public Listener(@NotNull CompoundButton compoundButton, @NotNull Observer<? super Boolean> observer2) {
            Intrinsics.checkParameterIsNotNull(compoundButton, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = compoundButton;
            this.observer = observer2;
        }

        public void onCheckedChanged(@NotNull CompoundButton compoundButton, boolean z) {
            Intrinsics.checkParameterIsNotNull(compoundButton, "compoundButton");
            if (!isDisposed()) {
                this.observer.onNext(Boolean.valueOf(z));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
    }
}
