package cn.xports.baselib.rxbind.widget;

import android.view.View;
import android.widget.AdapterView;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB%\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fH\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewItemLongClickEventObservable;", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/widget/AdapterViewItemLongClickEvent;", "view", "Landroid/widget/AdapterView;", "handled", "Lkotlin/Function1;", "", "(Landroid/widget/AdapterView;Lkotlin/jvm/functions/Function1;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterViewItemLongClickEventObservable.kt */
final class AdapterViewItemLongClickEventObservable extends Observable<AdapterViewItemLongClickEvent> {
    private final Function1<AdapterViewItemLongClickEvent, Boolean> handled;
    private final AdapterView<?> view;

    public AdapterViewItemLongClickEventObservable(@NotNull AdapterView<?> adapterView, @NotNull Function1<? super AdapterViewItemLongClickEvent, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        Intrinsics.checkParameterIsNotNull(function1, "handled");
        this.view = adapterView;
        this.handled = function1;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super AdapterViewItemLongClickEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            AdapterView.OnItemLongClickListener listener = new Listener(this.view, observer, this.handled);
            observer.onSubscribe((Disposable) listener);
            this.view.setOnItemLongClickListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0014J.\u0010\u000e\u001a\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewItemLongClickEventObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/AdapterView$OnItemLongClickListener;", "view", "Landroid/widget/AdapterView;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/widget/AdapterViewItemLongClickEvent;", "handled", "Lkotlin/Function1;", "", "(Landroid/widget/AdapterView;Lio/reactivex/Observer;Lkotlin/jvm/functions/Function1;)V", "onDispose", "", "onItemLongClick", "parent", "Landroid/view/View;", "position", "", "id", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AdapterViewItemLongClickEventObservable.kt */
    private static final class Listener extends MainThreadDisposable implements AdapterView.OnItemLongClickListener {
        private final Function1<AdapterViewItemLongClickEvent, Boolean> handled;
        private final Observer<? super AdapterViewItemLongClickEvent> observer;
        private final AdapterView<?> view;

        public Listener(@NotNull AdapterView<?> adapterView, @NotNull Observer<? super AdapterViewItemLongClickEvent> observer2, @NotNull Function1<? super AdapterViewItemLongClickEvent, Boolean> function1) {
            Intrinsics.checkParameterIsNotNull(adapterView, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            Intrinsics.checkParameterIsNotNull(function1, "handled");
            this.view = adapterView;
            this.observer = observer2;
            this.handled = function1;
        }

        public boolean onItemLongClick(@NotNull AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
            Intrinsics.checkParameterIsNotNull(adapterView, "parent");
            if (isDisposed()) {
                return false;
            }
            AdapterViewItemLongClickEvent adapterViewItemLongClickEvent = new AdapterViewItemLongClickEvent(adapterView, view2, i, j);
            try {
                if (!((Boolean) this.handled.invoke(adapterViewItemLongClickEvent)).booleanValue()) {
                    return false;
                }
                this.observer.onNext(adapterViewItemLongClickEvent);
                return true;
            } catch (Exception e) {
                this.observer.onError(e);
                dispose();
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) null);
        }
    }
}
