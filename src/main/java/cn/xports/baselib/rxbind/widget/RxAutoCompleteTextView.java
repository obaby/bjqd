package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.AutoCompleteTextView;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/widget/RxAutoCompleteTextView__AutoCompleteTextViewItemClickEventObservableKt"}, k = 4, mv = {1, 1, 15})
/* compiled from: AutoCompleteTextViewItemClickEventObservable.kt */
public final class RxAutoCompleteTextView {
    @NotNull
    @CheckResult
    public static final Observable<AdapterViewItemClickEvent> itemClickEvents(@NotNull AutoCompleteTextView autoCompleteTextView) {
        return RxAutoCompleteTextView__AutoCompleteTextViewItemClickEventObservableKt.itemClickEvents(autoCompleteTextView);
    }
}
