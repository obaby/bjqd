package cn.xports.baselib.rxbind.widget;

import android.database.DataSetObserver;
import android.widget.Adapter;
import cn.xports.baselib.rxbind.InitialValueObservable;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u000eB\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\rH\u0014R\u0010\u0010\u0004\u001a\u00028\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00028\u00008TX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterDataChangeObservable;", "T", "Landroid/widget/Adapter;", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "adapter", "(Landroid/widget/Adapter;)V", "Landroid/widget/Adapter;", "initialValue", "getInitialValue", "()Landroid/widget/Adapter;", "subscribeListener", "", "observer", "Lio/reactivex/Observer;", "ObserverDisposable", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterDataChangeObservable.kt */
final class AdapterDataChangeObservable<T extends Adapter> extends InitialValueObservable<T> {
    private final T adapter;

    public AdapterDataChangeObservable(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "adapter");
        this.adapter = t;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(@NotNull Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            Disposable observerDisposable = new ObserverDisposable(getInitialValue(), observer);
            getInitialValue().registerDataSetObserver(observerDisposable.dataSetObserver);
            observer.onSubscribe(observerDisposable);
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public T getInitialValue() {
        return this.adapter;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0014R\u0010\u0010\u0004\u001a\u00028\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u00020\n8\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterDataChangeObservable$ObserverDisposable;", "T", "Landroid/widget/Adapter;", "Lio/reactivex/android/MainThreadDisposable;", "adapter", "observer", "Lio/reactivex/Observer;", "(Landroid/widget/Adapter;Lio/reactivex/Observer;)V", "Landroid/widget/Adapter;", "dataSetObserver", "Landroid/database/DataSetObserver;", "onDispose", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AdapterDataChangeObservable.kt */
    private static final class ObserverDisposable<T extends Adapter> extends MainThreadDisposable {
        /* access modifiers changed from: private */
        public final T adapter;
        @NotNull
        @JvmField
        public final DataSetObserver dataSetObserver;

        public ObserverDisposable(@NotNull T t, @NotNull Observer<? super T> observer) {
            Intrinsics.checkParameterIsNotNull(t, "adapter");
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            this.adapter = t;
            this.dataSetObserver = new AdapterDataChangeObservable$ObserverDisposable$dataSetObserver$1(this, observer);
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.adapter.unregisterDataSetObserver(this.dataSetObserver);
        }
    }
}
