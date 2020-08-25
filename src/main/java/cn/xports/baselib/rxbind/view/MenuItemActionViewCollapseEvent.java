package cn.xports.baselib.rxbind.view;

import android.view.MenuItem;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcn/xports/baselib/rxbind/view/MenuItemActionViewCollapseEvent;", "Lcn/xports/baselib/rxbind/view/MenuItemActionViewEvent;", "menuItem", "Landroid/view/MenuItem;", "(Landroid/view/MenuItem;)V", "getMenuItem", "()Landroid/view/MenuItem;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: MenuItemActionViewEvent.kt */
public final class MenuItemActionViewCollapseEvent extends MenuItemActionViewEvent {
    @NotNull
    private final MenuItem menuItem;

    public static /* synthetic */ MenuItemActionViewCollapseEvent copy$default(MenuItemActionViewCollapseEvent menuItemActionViewCollapseEvent, MenuItem menuItem2, int i, Object obj) {
        if ((i & 1) != 0) {
            menuItem2 = menuItemActionViewCollapseEvent.getMenuItem();
        }
        return menuItemActionViewCollapseEvent.copy(menuItem2);
    }

    @NotNull
    public final MenuItem component1() {
        return getMenuItem();
    }

    @NotNull
    public final MenuItemActionViewCollapseEvent copy(@NotNull MenuItem menuItem2) {
        Intrinsics.checkParameterIsNotNull(menuItem2, "menuItem");
        return new MenuItemActionViewCollapseEvent(menuItem2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof MenuItemActionViewCollapseEvent) && Intrinsics.areEqual(getMenuItem(), ((MenuItemActionViewCollapseEvent) obj).getMenuItem());
        }
        return true;
    }

    public int hashCode() {
        MenuItem menuItem2 = getMenuItem();
        if (menuItem2 != null) {
            return menuItem2.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "MenuItemActionViewCollapseEvent(menuItem=" + getMenuItem() + ")";
    }

    @NotNull
    public MenuItem getMenuItem() {
        return this.menuItem;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MenuItemActionViewCollapseEvent(@NotNull MenuItem menuItem2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(menuItem2, "menuItem");
        this.menuItem = menuItem2;
    }
}
