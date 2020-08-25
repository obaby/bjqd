package cn.xports.baselib.rxbind.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewScrollChangeEvent;", "", "view", "Landroid/view/View;", "scrollX", "", "scrollY", "oldScrollX", "oldScrollY", "(Landroid/view/View;IIII)V", "getOldScrollX", "()I", "getOldScrollY", "getScrollX", "getScrollY", "getView", "()Landroid/view/View;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewLayoutChangeEventObservable.kt */
public final class ViewScrollChangeEvent {
    private final int oldScrollX;
    private final int oldScrollY;
    private final int scrollX;
    private final int scrollY;
    @NotNull
    private final View view;

    public static /* synthetic */ ViewScrollChangeEvent copy$default(ViewScrollChangeEvent viewScrollChangeEvent, View view2, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            view2 = viewScrollChangeEvent.view;
        }
        if ((i5 & 2) != 0) {
            i = viewScrollChangeEvent.scrollX;
        }
        int i6 = i;
        if ((i5 & 4) != 0) {
            i2 = viewScrollChangeEvent.scrollY;
        }
        int i7 = i2;
        if ((i5 & 8) != 0) {
            i3 = viewScrollChangeEvent.oldScrollX;
        }
        int i8 = i3;
        if ((i5 & 16) != 0) {
            i4 = viewScrollChangeEvent.oldScrollY;
        }
        return viewScrollChangeEvent.copy(view2, i6, i7, i8, i4);
    }

    @NotNull
    public final View component1() {
        return this.view;
    }

    public final int component2() {
        return this.scrollX;
    }

    public final int component3() {
        return this.scrollY;
    }

    public final int component4() {
        return this.oldScrollX;
    }

    public final int component5() {
        return this.oldScrollY;
    }

    @NotNull
    public final ViewScrollChangeEvent copy(@NotNull View view2, int i, int i2, int i3, int i4) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        return new ViewScrollChangeEvent(view2, i, i2, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ViewScrollChangeEvent) {
                ViewScrollChangeEvent viewScrollChangeEvent = (ViewScrollChangeEvent) obj;
                if (Intrinsics.areEqual(this.view, viewScrollChangeEvent.view)) {
                    if (this.scrollX == viewScrollChangeEvent.scrollX) {
                        if (this.scrollY == viewScrollChangeEvent.scrollY) {
                            if (this.oldScrollX == viewScrollChangeEvent.oldScrollX) {
                                if (this.oldScrollY == viewScrollChangeEvent.oldScrollY) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        View view2 = this.view;
        return ((((((((view2 != null ? view2.hashCode() : 0) * 31) + this.scrollX) * 31) + this.scrollY) * 31) + this.oldScrollX) * 31) + this.oldScrollY;
    }

    @NotNull
    public String toString() {
        return "ViewScrollChangeEvent(view=" + this.view + ", scrollX=" + this.scrollX + ", scrollY=" + this.scrollY + ", oldScrollX=" + this.oldScrollX + ", oldScrollY=" + this.oldScrollY + ")";
    }

    public ViewScrollChangeEvent(@NotNull View view2, int i, int i2, int i3, int i4) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        this.view = view2;
        this.scrollX = i;
        this.scrollY = i2;
        this.oldScrollX = i3;
        this.oldScrollY = i4;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    public final int getScrollX() {
        return this.scrollX;
    }

    public final int getScrollY() {
        return this.scrollY;
    }

    public final int getOldScrollX() {
        return this.oldScrollX;
    }

    public final int getOldScrollY() {
        return this.oldScrollY;
    }
}
