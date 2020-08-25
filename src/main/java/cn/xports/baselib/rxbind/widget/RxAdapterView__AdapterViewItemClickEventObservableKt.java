package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.Adapter;
import android.widget.AdapterView;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0007¨\u0006\u0006"}, d2 = {"itemClickEvents", "Lio/reactivex/Observable;", "Lcn/xports/baselib/rxbind/widget/AdapterViewItemClickEvent;", "T", "Landroid/widget/Adapter;", "Landroid/widget/AdapterView;", "baselib_release"}, k = 5, mv = {1, 1, 15}, xs = "cn/xports/baselib/rxbind/widget/RxAdapterView")
/* compiled from: AdapterViewItemClickEventObservable.kt */
final /* synthetic */ class RxAdapterView__AdapterViewItemClickEventObservableKt {
    @NotNull
    @CheckResult
    public static final <T extends Adapter> Observable<AdapterViewItemClickEvent> itemClickEvents(@NotNull AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$this$itemClickEvents");
        return (Observable) new AdapterViewItemClickEventObservable(adapterView);
    }
}
