package cn.xports.baselib.rxbind.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import cn.xports.baselib.rxbind.InitialValueObservable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fH\u0014R\u0014\u0010\u0006\u001a\u00020\u00028TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEventObservable;", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEvent;", "view", "Landroid/widget/TextView;", "(Landroid/widget/TextView;)V", "initialValue", "getInitialValue", "()Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEvent;", "subscribeListener", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TextViewTextChangeEventObservable.kt */
final class TextViewTextChangeEventObservable extends InitialValueObservable<TextViewTextChangeEvent> {
    private final TextView view;

    public TextViewTextChangeEventObservable(@NotNull TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "view");
        this.view = textView;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public TextViewTextChangeEvent getInitialValue() {
        TextView textView = this.view;
        CharSequence text = this.view.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "view.text");
        return new TextViewTextChangeEvent(textView, text, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(@NotNull Observer<? super TextViewTextChangeEvent> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        TextWatcher listener = new Listener(this.view, observer);
        observer.onSubscribe((Disposable) listener);
        this.view.addTextChangedListener(listener);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J(\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\nH\u0014J(\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEventObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/text/TextWatcher;", "view", "Landroid/widget/TextView;", "observer", "Lio/reactivex/Observer;", "Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEvent;", "(Landroid/widget/TextView;Lio/reactivex/Observer;)V", "afterTextChanged", "", "editable", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onDispose", "onTextChanged", "before", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TextViewTextChangeEventObservable.kt */
    private static final class Listener extends MainThreadDisposable implements TextWatcher {
        private final Observer<? super TextViewTextChangeEvent> observer;
        private final TextView view;

        public void afterTextChanged(@NotNull Editable editable) {
            Intrinsics.checkParameterIsNotNull(editable, "editable");
        }

        public void beforeTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(charSequence, "s");
        }

        public Listener(@NotNull TextView textView, @NotNull Observer<? super TextViewTextChangeEvent> observer2) {
            Intrinsics.checkParameterIsNotNull(textView, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = textView;
            this.observer = observer2;
        }

        public void onTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(charSequence, "s");
            if (!isDisposed()) {
                this.observer.onNext(new TextViewTextChangeEvent(this.view, charSequence, i, i2, i3));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeTextChangedListener(this);
        }
    }
}
