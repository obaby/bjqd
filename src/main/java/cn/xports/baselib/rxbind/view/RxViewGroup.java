package cn.xports.baselib.rxbind.view;

import android.support.annotation.CheckResult;
import android.view.ViewGroup;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/view/RxViewGroup__ViewGroupHierarchyChangeEventObservableKt"}, k = 4, mv = {1, 1, 15})
/* compiled from: ViewGroupHierarchyChangeEventObservable.kt */
public final class RxViewGroup {
    @NotNull
    @CheckResult
    public static final Observable<ViewGroupHierarchyChangeEvent> changeEvents(@NotNull ViewGroup viewGroup) {
        return RxViewGroup__ViewGroupHierarchyChangeEventObservableKt.changeEvents(viewGroup);
    }
}
