package cn.xports.baselib.rxbind.widget;

import android.widget.AbsListView;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AbsListViewScrollEventObservable;", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/widget/AbsListViewScrollEvent;", "view", "Landroid/widget/AbsListView;", "(Landroid/widget/AbsListView;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AbsListViewScrollEventObservable.kt */
final class AbsListViewScrollEventObservable extends Observable<AbsListViewScrollEvent> {
    private final AbsListView view;

    public AbsListViewScrollEventObservable(@NotNull AbsListView absListView) {
        Intrinsics.checkParameterIsNotNull(absListView, "view");
        this.view = absListView;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super AbsListViewScrollEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            AbsListView.OnScrollListener listener = new Listener(this.view, observer);
            observer.onSubscribe((Disposable) listener);
            this.view.setOnScrollListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0014J(\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nH\u0016J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\nH\u0016R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AbsListViewScrollEventObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/AbsListView$OnScrollListener;", "view", "Landroid/widget/AbsListView;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/widget/AbsListViewScrollEvent;", "(Landroid/widget/AbsListView;Lio/reactivex/Observer;)V", "currentScrollState", "", "onDispose", "", "onScroll", "absListView", "firstVisibleItem", "visibleItemCount", "totalItemCount", "onScrollStateChanged", "scrollState", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AbsListViewScrollEventObservable.kt */
    private static final class Listener extends MainThreadDisposable implements AbsListView.OnScrollListener {
        private int currentScrollState;
        private final Observer<? super AbsListViewScrollEvent> observer;
        private final AbsListView view;

        public Listener(@NotNull AbsListView absListView, @NotNull Observer<? super AbsListViewScrollEvent> observer2) {
            Intrinsics.checkParameterIsNotNull(absListView, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = absListView;
            this.observer = observer2;
        }

        public void onScrollStateChanged(@NotNull AbsListView absListView, int i) {
            Intrinsics.checkParameterIsNotNull(absListView, "absListView");
            this.currentScrollState = i;
            if (!isDisposed()) {
                this.observer.onNext(new AbsListViewScrollEvent(this.view, i, this.view.getFirstVisiblePosition(), this.view.getChildCount(), this.view.getCount()));
            }
        }

        public void onScroll(@NotNull AbsListView absListView, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(absListView, "absListView");
            if (!isDisposed()) {
                this.observer.onNext(new AbsListViewScrollEvent(this.view, this.currentScrollState, i, i2, i3));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnScrollListener((AbsListView.OnScrollListener) null);
        }
    }
}
