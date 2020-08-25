package cn.xports.baselib.rxbind.widget;

import android.view.KeyEvent;
import android.widget.TextView;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fH\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewEditorActionEventObservable;", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/widget/TextViewEditorActionEvent;", "view", "Landroid/widget/TextView;", "handled", "Lkotlin/Function1;", "", "(Landroid/widget/TextView;Lkotlin/jvm/functions/Function1;)V", "subscribeActual", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TextViewEditorActionEventObservable.kt */
final class TextViewEditorActionEventObservable extends Observable<TextViewEditorActionEvent> {
    private final Function1<TextViewEditorActionEvent, Boolean> handled;
    private final TextView view;

    public TextViewEditorActionEventObservable(@NotNull TextView textView, @NotNull Function1<? super TextViewEditorActionEvent, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(textView, "view");
        Intrinsics.checkParameterIsNotNull(function1, "handled");
        this.view = textView;
        this.handled = function1;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(@NotNull Observer<? super TextViewEditorActionEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (Preconditions.checkMainThread(observer)) {
            TextView.OnEditorActionListener listener = new Listener(this.view, observer, this.handled);
            observer.onSubscribe((Disposable) listener);
            this.view.setOnEditorActionListener(listener);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0014J\"\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewEditorActionEventObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/widget/TextView$OnEditorActionListener;", "view", "Landroid/widget/TextView;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/widget/TextViewEditorActionEvent;", "handled", "Lkotlin/Function1;", "", "(Landroid/widget/TextView;Lio/reactivex/Observer;Lkotlin/jvm/functions/Function1;)V", "onDispose", "", "onEditorAction", "textView", "actionId", "", "keyEvent", "Landroid/view/KeyEvent;", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TextViewEditorActionEventObservable.kt */
    private static final class Listener extends MainThreadDisposable implements TextView.OnEditorActionListener {
        private final Function1<TextViewEditorActionEvent, Boolean> handled;
        private final Observer<? super TextViewEditorActionEvent> observer;
        private final TextView view;

        public Listener(@NotNull TextView textView, @NotNull Observer<? super TextViewEditorActionEvent> observer2, @NotNull Function1<? super TextViewEditorActionEvent, Boolean> function1) {
            Intrinsics.checkParameterIsNotNull(textView, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            Intrinsics.checkParameterIsNotNull(function1, "handled");
            this.view = textView;
            this.observer = observer2;
            this.handled = function1;
        }

        public boolean onEditorAction(@NotNull TextView textView, int i, @Nullable KeyEvent keyEvent) {
            Intrinsics.checkParameterIsNotNull(textView, "textView");
            TextViewEditorActionEvent textViewEditorActionEvent = new TextViewEditorActionEvent(this.view, i, keyEvent);
            try {
                if (isDisposed() || !((Boolean) this.handled.invoke(textViewEditorActionEvent)).booleanValue()) {
                    return false;
                }
                this.observer.onNext(textViewEditorActionEvent);
                return true;
            } catch (Exception e) {
                this.observer.onError(e);
                dispose();
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnEditorActionListener((TextView.OnEditorActionListener) null);
        }
    }
}
