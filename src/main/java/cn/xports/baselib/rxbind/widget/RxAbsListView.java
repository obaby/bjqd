package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.widget.AbsListView;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/widget/RxAbsListView__AbsListViewScrollEventObservableKt"}, k = 4, mv = {1, 1, 15})
/* compiled from: AbsListViewScrollEventObservable.kt */
public final class RxAbsListView {
    @NotNull
    @CheckResult
    public static final Observable<AbsListViewScrollEvent> scrollEvents(@NotNull AbsListView absListView) {
        return RxAbsListView__AbsListViewScrollEventObservableKt.scrollEvents(absListView);
    }
}
