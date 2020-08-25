package cn.xports.baselib.rxbind.view;

import android.view.View;
import android.view.ViewGroup;
import cn.xports.baselib.rxbind.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewGroupHierarchyChangeEventObservable;", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/view/ViewGroupHierarchyChangeEvent;", "viewGroup", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewGroupHierarchyChangeEventObservable.kt */
final class ViewGroupHierarchyChangeEventObservable extends Observable<ViewGroupHierarchyChangeEvent> {
    private final ViewGroup viewGroup;

    public ViewGroupHierarchyChangeEventObservable(@NotNull ViewGroup viewGroup2) {
        Intrinsics.checkParameterIsNotNull(viewGroup2, "viewGroup");
        this.viewGroup = viewGroup2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super ViewGroupHierarchyChangeEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            ViewGroup.OnHierarchyChangeListener listener = new Listener(this.viewGroup, observer);
            observer.onSubscribe((Disposable) listener);
            this.viewGroup.setOnHierarchyChangeListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0014R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewGroupHierarchyChangeEventObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/view/ViewGroup$OnHierarchyChangeListener;", "viewGroup", "Landroid/view/ViewGroup;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/view/ViewGroupHierarchyChangeEvent;", "(Landroid/view/ViewGroup;Lio/reactivex/Observer;)V", "onChildViewAdded", "", "parent", "Landroid/view/View;", "child", "onChildViewRemoved", "onDispose", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: ViewGroupHierarchyChangeEventObservable.kt */
    private static final class Listener extends MainThreadDisposable implements ViewGroup.OnHierarchyChangeListener {
        private final Observer<? super ViewGroupHierarchyChangeEvent> observer;
        private final ViewGroup viewGroup;

        public Listener(@NotNull ViewGroup viewGroup2, @NotNull Observer<? super ViewGroupHierarchyChangeEvent> observer2) {
            Intrinsics.checkParameterIsNotNull(viewGroup2, "viewGroup");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.viewGroup = viewGroup2;
            this.observer = observer2;
        }

        public void onChildViewAdded(@NotNull View view, @NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view, "parent");
            Intrinsics.checkParameterIsNotNull(view2, "child");
            if (!isDisposed()) {
                this.observer.onNext(new ViewGroupHierarchyChildViewAddEvent(this.viewGroup, view2));
            }
        }

        public void onChildViewRemoved(@NotNull View view, @NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view, "parent");
            Intrinsics.checkParameterIsNotNull(view2, "child");
            if (!isDisposed()) {
                this.observer.onNext(new ViewGroupHierarchyChildViewRemoveEvent(this.viewGroup, view2));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.viewGroup.setOnHierarchyChangeListener((ViewGroup.OnHierarchyChangeListener) null);
        }
    }
}
