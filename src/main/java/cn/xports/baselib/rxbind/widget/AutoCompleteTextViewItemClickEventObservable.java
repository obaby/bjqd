package cn.xports.baselib.rxbind.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AutoCompleteTextViewItemClickEventObservable;", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/widget/AdapterViewItemClickEvent;", "view", "Landroid/widget/AutoCompleteTextView;", "(Landroid/widget/AutoCompleteTextView;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AutoCompleteTextViewItemClickEventObservable.kt */
final class AutoCompleteTextViewItemClickEventObservable extends Observable<AdapterViewItemClickEvent> {
    private final AutoCompleteTextView view;

    public AutoCompleteTextViewItemClickEventObservable(@NotNull AutoCompleteTextView autoCompleteTextView) {
        Intrinsics.checkParameterIsNotNull(autoCompleteTextView, "view");
        this.view = autoCompleteTextView;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super AdapterViewItemClickEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            AdapterView.OnItemClickListener listener = new Listener(this.view, observer);
            observer.onSubscribe((Disposable) listener);
            this.view.setOnItemClickListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J.\u0010\u000b\u001a\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AutoCompleteTextViewItemClickEventObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/AdapterView$OnItemClickListener;", "view", "Landroid/widget/AutoCompleteTextView;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/widget/AdapterViewItemClickEvent;", "(Landroid/widget/AutoCompleteTextView;Lio/reactivex/Observer;)V", "onDispose", "", "onItemClick", "parent", "Landroid/widget/AdapterView;", "Landroid/view/View;", "position", "", "id", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AutoCompleteTextViewItemClickEventObservable.kt */
    private static final class Listener extends MainThreadDisposable implements AdapterView.OnItemClickListener {
        private final Observer<? super AdapterViewItemClickEvent> observer;
        private final AutoCompleteTextView view;

        public Listener(@NotNull AutoCompleteTextView autoCompleteTextView, @NotNull Observer<? super AdapterViewItemClickEvent> observer2) {
            Intrinsics.checkParameterIsNotNull(autoCompleteTextView, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = autoCompleteTextView;
            this.observer = observer2;
        }

        public void onItemClick(@NotNull AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
            Intrinsics.checkParameterIsNotNull(adapterView, "parent");
            if (!isDisposed()) {
                this.observer.onNext(new AdapterViewItemClickEvent(adapterView, view2, i, j));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnItemClickListener((AdapterView.OnItemClickListener) null);
        }
    }
}
