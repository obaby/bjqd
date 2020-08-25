package cn.xports.baselib.rxbind.widget;

import android.widget.RadioGroup;
import cn.xports.baselib.rxbind.InitialValueObservable;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fH\u0014R\u0014\u0010\u0006\u001a\u00020\u00028TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/RadioGroupCheckedChangeObservable;", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "", "view", "Landroid/widget/RadioGroup;", "(Landroid/widget/RadioGroup;)V", "initialValue", "getInitialValue", "()Ljava/lang/Integer;", "subscribeListener", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: RadioGroupCheckedChangeObservable.kt */
final class RadioGroupCheckedChangeObservable extends InitialValueObservable<Integer> {
    private final RadioGroup view;

    public RadioGroupCheckedChangeObservable(@NotNull RadioGroup radioGroup) {
        Intrinsics.checkParameterIsNotNull(radioGroup, "view");
        this.view = radioGroup;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(@NotNull Observer<? super Integer> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            Disposable listener = new Listener(this.view, observer);
            this.view.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Integer getInitialValue() {
        return Integer.valueOf(this.view.getCheckedRadioButtonId());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0014R\u000e\u0010\t\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcn/xports/baselib/rxbind/widget/RadioGroupCheckedChangeObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/RadioGroup$OnCheckedChangeListener;", "view", "Landroid/widget/RadioGroup;", "observer", "Lio/reactivex/Observer;", "", "(Landroid/widget/RadioGroup;Lio/reactivex/Observer;)V", "lastChecked", "onCheckedChanged", "", "radioGroup", "checkedId", "onDispose", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: RadioGroupCheckedChangeObservable.kt */
    private static final class Listener extends MainThreadDisposable implements RadioGroup.OnCheckedChangeListener {
        private int lastChecked = -1;
        private final Observer<? super Integer> observer;
        private final RadioGroup view;

        public Listener(@NotNull RadioGroup radioGroup, @NotNull Observer<? super Integer> observer2) {
            Intrinsics.checkParameterIsNotNull(radioGroup, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = radioGroup;
            this.observer = observer2;
        }

        public void onCheckedChanged(@NotNull RadioGroup radioGroup, int i) {
            Intrinsics.checkParameterIsNotNull(radioGroup, "radioGroup");
            if (!isDisposed() && i != this.lastChecked) {
                this.lastChecked = i;
                this.observer.onNext(Integer.valueOf(i));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) null);
        }
    }
}
