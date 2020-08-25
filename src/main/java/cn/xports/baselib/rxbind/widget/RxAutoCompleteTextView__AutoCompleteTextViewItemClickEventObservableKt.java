package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.AutoCompleteTextView;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"itemClickEvents", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/widget/AdapterViewItemClickEvent;", "Landroid/widget/AutoCompleteTextView;", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/widget/RxAutoCompleteTextView")
/* compiled from: AutoCompleteTextViewItemClickEventObservable.kt */
final /* synthetic */ class RxAutoCompleteTextView__AutoCompleteTextViewItemClickEventObservableKt {
    @NotNull
    @CheckResult
    public static final Observable<AdapterViewItemClickEvent> itemClickEvents(@NotNull AutoCompleteTextView autoCompleteTextView) {
        Intrinsics.checkParameterIsNotNull(autoCompleteTextView, "$this$itemClickEvents");
        return (Observable) new AutoCompleteTextViewItemClickEventObservable(autoCompleteTextView);
    }
}
