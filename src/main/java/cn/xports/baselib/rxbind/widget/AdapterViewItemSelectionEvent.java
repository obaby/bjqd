package cn.xports.baselib.rxbind.widget;

import android.view.View;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B+\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewItemSelectionEvent;", "Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionEvent;", "view", "Landroid/widget/AdapterView;", "selectedView", "Landroid/view/View;", "position", "", "id", "", "(Landroid/widget/AdapterView;Landroid/view/View;IJ)V", "getId", "()J", "getPosition", "()I", "getSelectedView", "()Landroid/view/View;", "getView", "()Landroid/widget/AdapterView;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterViewSelectionEvent.kt */
public final class AdapterViewItemSelectionEvent extends AdapterViewSelectionEvent {
    private final long id;
    private final int position;
    @Nullable
    private final View selectedView;
    @NotNull
    private final AdapterView<?> view;

    public static /* synthetic */ AdapterViewItemSelectionEvent copy$default(AdapterViewItemSelectionEvent adapterViewItemSelectionEvent, AdapterView<?> adapterView, View view2, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            adapterView = adapterViewItemSelectionEvent.getView();
        }
        if ((i2 & 2) != 0) {
            view2 = adapterViewItemSelectionEvent.selectedView;
        }
        View view3 = view2;
        if ((i2 & 4) != 0) {
            i = adapterViewItemSelectionEvent.position;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            j = adapterViewItemSelectionEvent.id;
        }
        return adapterViewItemSelectionEvent.copy(adapterView, view3, i3, j);
    }

    @NotNull
    public final AdapterView<?> component1() {
        return getView();
    }

    @Nullable
    public final View component2() {
        return this.selectedView;
    }

    public final int component3() {
        return this.position;
    }

    public final long component4() {
        return this.id;
    }

    @NotNull
    public final AdapterViewItemSelectionEvent copy(@NotNull AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        return new AdapterViewItemSelectionEvent(adapterView, view2, i, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof AdapterViewItemSelectionEvent) {
                AdapterViewItemSelectionEvent adapterViewItemSelectionEvent = (AdapterViewItemSelectionEvent) obj;
                if (Intrinsics.areEqual(getView(), adapterViewItemSelectionEvent.getView()) && Intrinsics.areEqual(this.selectedView, adapterViewItemSelectionEvent.selectedView)) {
                    if (this.position == adapterViewItemSelectionEvent.position) {
                        if (this.id == adapterViewItemSelectionEvent.id) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        AdapterView<?> view2 = getView();
        int i = 0;
        int hashCode = (view2 != null ? view2.hashCode() : 0) * 31;
        View view3 = this.selectedView;
        if (view3 != null) {
            i = view3.hashCode();
        }
        long j = this.id;
        return ((((hashCode + i) * 31) + this.position) * 31) + ((int) (j ^ (j >>> 32)));
    }

    @NotNull
    public String toString() {
        return "AdapterViewItemSelectionEvent(view=" + getView() + ", selectedView=" + this.selectedView + ", position=" + this.position + ", id=" + this.id + ")";
    }

    @NotNull
    public AdapterView<?> getView() {
        return this.view;
    }

    @Nullable
    public final View getSelectedView() {
        return this.selectedView;
    }

    public final int getPosition() {
        return this.position;
    }

    public final long getId() {
        return this.id;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdapterViewItemSelectionEvent(@NotNull AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        this.view = adapterView;
        this.selectedView = view2;
        this.position = i;
        this.id = j;
    }
}
