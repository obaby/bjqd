package cn.xports.baselib.rxbind.widget;

import android.support.annotation.CheckResult;
import android.view.MenuItem;
import android.widget.PopupMenu;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"cn/xports/baselib/rxbind/widget/RxPopupMenu__PopupMenuDismissObservableKt", "cn/xports/baselib/rxbind/widget/RxPopupMenu__PopupMenuItemClickObservableKt"}, k = 4, mv = {1, 1, 15})
public final class RxPopupMenu {
    @NotNull
    @CheckResult
    public static final Observable<Unit> dismisses(@NotNull PopupMenu popupMenu) {
        return RxPopupMenu__PopupMenuDismissObservableKt.dismisses(popupMenu);
    }

    @NotNull
    @CheckResult
    public static final Observable<MenuItem> itemClicks(@NotNull PopupMenu popupMenu) {
        return RxPopupMenu__PopupMenuItemClickObservableKt.itemClicks(popupMenu);
    }
}
