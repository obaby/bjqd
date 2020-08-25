package cn.xports.baselib.rxbind.widget;

import android.view.View;
import android.widget.AdapterView;
import cn.xports.baselib.rxbind.InitialValueObservable;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fH\u0014R\u0014\u0010\u0006\u001a\u00020\u00028TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionObservable;", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionEvent;", "view", "Landroid/widget/AdapterView;", "(Landroid/widget/AdapterView;)V", "initialValue", "getInitialValue", "()Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionEvent;", "subscribeListener", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterViewSelectionObservable.kt */
final class AdapterViewSelectionObservable extends InitialValueObservable<AdapterViewSelectionEvent> {
    private final AdapterView<?> view;

    public AdapterViewSelectionObservable(@NotNull AdapterView<?> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        this.view = adapterView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(@NotNull Observer<? super AdapterViewSelectionEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            Disposable listener = new Listener(this.view, observer);
            this.view.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public AdapterViewSelectionEvent getInitialValue() {
        int selectedItemPosition = this.view.getSelectedItemPosition();
        if (selectedItemPosition == -1) {
            return new AdapterViewNothingSelectionEvent(this.view);
        }
        return new AdapterViewItemSelectionEvent(this.view, this.view.getSelectedView(), selectedItemPosition, this.view.getSelectedItemId());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J.\u0010\u000b\u001a\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0014\u0010\u0012\u001a\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "view", "Landroid/widget/AdapterView;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionEvent;", "(Landroid/widget/AdapterView;Lio/reactivex/Observer;)V", "onDispose", "", "onItemSelected", "parent", "Landroid/view/View;", "position", "", "id", "", "onNothingSelected", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AdapterViewSelectionObservable.kt */
    private static final class Listener extends MainThreadDisposable implements AdapterView.OnItemSelectedListener {
        private final Observer<? super AdapterViewSelectionEvent> observer;
        private final AdapterView<?> view;

        public Listener(@NotNull AdapterView<?> adapterView, @NotNull Observer<? super AdapterViewSelectionEvent> observer2) {
            Intrinsics.checkParameterIsNotNull(adapterView, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = adapterView;
            this.observer = observer2;
        }

        public void onItemSelected(@NotNull AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
            Intrinsics.checkParameterIsNotNull(adapterView, "parent");
            if (!isDisposed()) {
                this.observer.onNext(new AdapterViewItemSelectionEvent(adapterView, view2, i, j));
            }
        }

        public void onNothingSelected(@NotNull AdapterView<?> adapterView) {
            Intrinsics.checkParameterIsNotNull(adapterView, "parent");
            if (!isDisposed()) {
                this.observer.onNext(new AdapterViewNothingSelectionEvent(adapterView));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
        }
    }
}
