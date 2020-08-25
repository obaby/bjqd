package cn.xports.baselib.rxbind.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003Jc\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0005HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewLayoutChangeEvent;", "", "view", "Landroid/view/View;", "left", "", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "(Landroid/view/View;IIIIIIII)V", "getBottom", "()I", "getLeft", "getOldBottom", "getOldLeft", "getOldRight", "getOldTop", "getRight", "getTop", "getView", "()Landroid/view/View;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewLayoutChangeEventObservable.kt */
public final class ViewLayoutChangeEvent {
    private final int bottom;
    private final int left;
    private final int oldBottom;
    private final int oldLeft;
    private final int oldRight;
    private final int oldTop;
    private final int right;
    private final int top;
    @NotNull
    private final View view;

    public static /* synthetic */ ViewLayoutChangeEvent copy$default(ViewLayoutChangeEvent viewLayoutChangeEvent, View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Object obj) {
        ViewLayoutChangeEvent viewLayoutChangeEvent2 = viewLayoutChangeEvent;
        int i10 = i9;
        return viewLayoutChangeEvent.copy((i10 & 1) != 0 ? viewLayoutChangeEvent2.view : view2, (i10 & 2) != 0 ? viewLayoutChangeEvent2.left : i, (i10 & 4) != 0 ? viewLayoutChangeEvent2.top : i2, (i10 & 8) != 0 ? viewLayoutChangeEvent2.right : i3, (i10 & 16) != 0 ? viewLayoutChangeEvent2.bottom : i4, (i10 & 32) != 0 ? viewLayoutChangeEvent2.oldLeft : i5, (i10 & 64) != 0 ? viewLayoutChangeEvent2.oldTop : i6, (i10 & 128) != 0 ? viewLayoutChangeEvent2.oldRight : i7, (i10 & 256) != 0 ? viewLayoutChangeEvent2.oldBottom : i8);
    }

    @NotNull
    public final View component1() {
        return this.view;
    }

    public final int component2() {
        return this.left;
    }

    public final int component3() {
        return this.top;
    }

    public final int component4() {
        return this.right;
    }

    public final int component5() {
        return this.bottom;
    }

    public final int component6() {
        return this.oldLeft;
    }

    public final int component7() {
        return this.oldTop;
    }

    public final int component8() {
        return this.oldRight;
    }

    public final int component9() {
        return this.oldBottom;
    }

    @NotNull
    public final ViewLayoutChangeEvent copy(@NotNull View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        return new ViewLayoutChangeEvent(view2, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ViewLayoutChangeEvent) {
                ViewLayoutChangeEvent viewLayoutChangeEvent = (ViewLayoutChangeEvent) obj;
                if (Intrinsics.areEqual(this.view, viewLayoutChangeEvent.view)) {
                    if (this.left == viewLayoutChangeEvent.left) {
                        if (this.top == viewLayoutChangeEvent.top) {
                            if (this.right == viewLayoutChangeEvent.right) {
                                if (this.bottom == viewLayoutChangeEvent.bottom) {
                                    if (this.oldLeft == viewLayoutChangeEvent.oldLeft) {
                                        if (this.oldTop == viewLayoutChangeEvent.oldTop) {
                                            if (this.oldRight == viewLayoutChangeEvent.oldRight) {
                                                if (this.oldBottom == viewLayoutChangeEvent.oldBottom) {
                                                    return true;
                                                }
                                            }
                                        }
                                    }
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
        return ((((((((((((((((view2 != null ? view2.hashCode() : 0) * 31) + this.left) * 31) + this.top) * 31) + this.right) * 31) + this.bottom) * 31) + this.oldLeft) * 31) + this.oldTop) * 31) + this.oldRight) * 31) + this.oldBottom;
    }

    @NotNull
    public String toString() {
        return "ViewLayoutChangeEvent(view=" + this.view + ", left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", oldLeft=" + this.oldLeft + ", oldTop=" + this.oldTop + ", oldRight=" + this.oldRight + ", oldBottom=" + this.oldBottom + ")";
    }

    public ViewLayoutChangeEvent(@NotNull View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        this.view = view2;
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        this.oldLeft = i5;
        this.oldTop = i6;
        this.oldRight = i7;
        this.oldBottom = i8;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final int getOldLeft() {
        return this.oldLeft;
    }

    public final int getOldTop() {
        return this.oldTop;
    }

    public final int getOldRight() {
        return this.oldRight;
    }

    public final int getOldBottom() {
        return this.oldBottom;
    }
}
