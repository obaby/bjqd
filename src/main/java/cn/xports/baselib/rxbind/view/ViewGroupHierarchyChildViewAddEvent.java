package cn.xports.baselib.rxbind.view;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewGroupHierarchyChildViewAddEvent;", "Lcn/xports/baselib/rxbind/view/ViewGroupHierarchyChangeEvent;", "view", "Landroid/view/ViewGroup;", "child", "Landroid/view/View;", "(Landroid/view/ViewGroup;Landroid/view/View;)V", "getChild", "()Landroid/view/View;", "getView", "()Landroid/view/ViewGroup;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewGroupHierarchyChangeEvent.kt */
public final class ViewGroupHierarchyChildViewAddEvent extends ViewGroupHierarchyChangeEvent {
    @NotNull
    private final View child;
    @NotNull
    private final ViewGroup view;

    public static /* synthetic */ ViewGroupHierarchyChildViewAddEvent copy$default(ViewGroupHierarchyChildViewAddEvent viewGroupHierarchyChildViewAddEvent, ViewGroup viewGroup, View view2, int i, Object obj) {
        if ((i & 1) != 0) {
            viewGroup = viewGroupHierarchyChildViewAddEvent.getView();
        }
        if ((i & 2) != 0) {
            view2 = viewGroupHierarchyChildViewAddEvent.getChild();
        }
        return viewGroupHierarchyChildViewAddEvent.copy(viewGroup, view2);
    }

    @NotNull
    public final ViewGroup component1() {
        return getView();
    }

    @NotNull
    public final View component2() {
        return getChild();
    }

    @NotNull
    public final ViewGroupHierarchyChildViewAddEvent copy(@NotNull ViewGroup viewGroup, @NotNull View view2) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "view");
        Intrinsics.checkParameterIsNotNull(view2, "child");
        return new ViewGroupHierarchyChildViewAddEvent(viewGroup, view2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewGroupHierarchyChildViewAddEvent)) {
            return false;
        }
        ViewGroupHierarchyChildViewAddEvent viewGroupHierarchyChildViewAddEvent = (ViewGroupHierarchyChildViewAddEvent) obj;
        return Intrinsics.areEqual(getView(), viewGroupHierarchyChildViewAddEvent.getView()) && Intrinsics.areEqual(getChild(), viewGroupHierarchyChildViewAddEvent.getChild());
    }

    public int hashCode() {
        ViewGroup view2 = getView();
        int i = 0;
        int hashCode = (view2 != null ? view2.hashCode() : 0) * 31;
        View child2 = getChild();
        if (child2 != null) {
            i = child2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "ViewGroupHierarchyChildViewAddEvent(view=" + getView() + ", child=" + getChild() + ")";
    }

    @NotNull
    public ViewGroup getView() {
        return this.view;
    }

    @NotNull
    public View getChild() {
        return this.child;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewGroupHierarchyChildViewAddEvent(@NotNull ViewGroup viewGroup, @NotNull View view2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(viewGroup, "view");
        Intrinsics.checkParameterIsNotNull(view2, "child");
        this.view = viewGroup;
        this.child = view2;
    }
}
