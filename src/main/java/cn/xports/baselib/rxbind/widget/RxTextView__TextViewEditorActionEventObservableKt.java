package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.TextView;
import cn.xports.baselib.rxbind.internal.AlwaysTrue;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¨\u0006\u0007"}, d2 = {"editorActionEvents", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/widget/TextViewEditorActionEvent;", "Landroid/widget/TextView;", "handled", "Lkotlin/Function1;", "", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/widget/RxTextView")
/* compiled from: TextViewEditorActionEventObservable.kt */
final /* synthetic */ class RxTextView__TextViewEditorActionEventObservableKt {
    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<TextViewEditorActionEvent> editorActionEvents(@NotNull TextView textView) {
        return editorActionEvents$default(textView, (Function1) null, 1, (Object) null);
    }

    public static /* synthetic */ Observable editorActionEvents$default(TextView textView, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) AlwaysTrue.INSTANCE;
        }
        return RxTextView.editorActionEvents(textView, function1);
    }

    @CheckResult
    @NotNull
    @JvmOverloads
    public static final Observable<TextViewEditorActionEvent> editorActionEvents(@NotNull TextView textView, @NotNull Function1<? super TextViewEditorActionEvent, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(textView, "$this$editorActionEvents");
        Intrinsics.checkParameterIsNotNull(function1, "handled");
        return (Observable) new TextViewEditorActionEventObservable(textView, function1);
    }
}
