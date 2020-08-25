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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\rH\u0014R\u001c\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00020\u00028TX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewTextChangesObservable;", "Lcn/xports/baselib/rxbind/InitialValueObservable;", "", "view", "Landroid/widget/TextView;", "(Landroid/widget/TextView;)V", "initialValue", "kotlin.jvm.PlatformType", "getInitialValue", "()Ljava/lang/CharSequence;", "subscribeListener", "", "observer", "Lio/reactivex/Observer;", "Listener", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TextViewTextChangesObservable.kt */
final class TextViewTextChangesObservable extends InitialValueObservable<CharSequence> {
    private final TextView view;

    public TextViewTextChangesObservable(@NotNull TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "view");
        this.view = textView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(@NotNull Observer<? super CharSequence> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        TextWatcher listener = new Listener(this.view, observer);
        observer.onSubscribe((Disposable) listener);
        this.view.addTextChangedListener(listener);
    }

    /* access modifiers changed from: protected */
    public CharSequence getInitialValue() {
        return this.view.getText();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J(\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0014J(\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewTextChangesObservable$Listener;", "Lio/reactivex/android/MainThreadDisposable;", "Landroid/text/TextWatcher;", "view", "Landroid/widget/TextView;", "observer", "Lio/reactivex/Observer;", "", "(Landroid/widget/TextView;Lio/reactivex/Observer;)V", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "start", "", "count", "after", "onDispose", "onTextChanged", "before", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TextViewTextChangesObservable.kt */
    private static final class Listener extends MainThreadDisposable implements TextWatcher {
        private final Observer<? super CharSequence> observer;
        private final TextView view;

        public void afterTextChanged(@NotNull Editable editable) {
            Intrinsics.checkParameterIsNotNull(editable, "s");
        }

        public void beforeTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(charSequence, "s");
        }

        public Listener(@NotNull TextView textView, @NotNull Observer<? super CharSequence> observer2) {
            Intrinsics.checkParameterIsNotNull(textView, "view");
            Intrinsics.checkParameterIsNotNull(observer2, "observer");
            this.view = textView;
            this.observer = observer2;
        }

        public void onTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(charSequence, "s");
            if (!isDisposed()) {
                this.observer.onNext(charSequence);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeTextChangedListener(this);
        }
    }
}
