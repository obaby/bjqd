package cn.xports.baselib.rxbind.widget;

import android.view.View;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B+\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewItemClickEvent;", "", "view", "Landroid/widget/AdapterView;", "clickedView", "Landroid/view/View;", "position", "", "id", "", "(Landroid/widget/AdapterView;Landroid/view/View;IJ)V", "getClickedView", "()Landroid/view/View;", "getId", "()J", "getPosition", "()I", "getView", "()Landroid/widget/AdapterView;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterViewItemClickEventObservable.kt */
public final class AdapterViewItemClickEvent {
    @Nullable
    private final View clickedView;
    private final long id;
    private final int position;
    @NotNull
    private final AdapterView<?> view;

    public static /* synthetic */ AdapterViewItemClickEvent copy$default(AdapterViewItemClickEvent adapterViewItemClickEvent, AdapterView<?> adapterView, View view2, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            adapterView = adapterViewItemClickEvent.view;
        }
        if ((i2 & 2) != 0) {
            view2 = adapterViewItemClickEvent.clickedView;
        }
        View view3 = view2;
        if ((i2 & 4) != 0) {
            i = adapterViewItemClickEvent.position;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            j = adapterViewItemClickEvent.id;
        }
        return adapterViewItemClickEvent.copy(adapterView, view3, i3, j);
    }

    @NotNull
    public final AdapterView<?> component1() {
        return this.view;
    }

    @Nullable
    public final View component2() {
        return this.clickedView;
    }

    public final int component3() {
        return this.position;
    }

    public final long component4() {
        return this.id;
    }

    @NotNull
    public final AdapterViewItemClickEvent copy(@NotNull AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        return new AdapterViewItemClickEvent(adapterView, view2, i, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof AdapterViewItemClickEvent) {
                AdapterViewItemClickEvent adapterViewItemClickEvent = (AdapterViewItemClickEvent) obj;
                if (Intrinsics.areEqual(this.view, adapterViewItemClickEvent.view) && Intrinsics.areEqual(this.clickedView, adapterViewItemClickEvent.clickedView)) {
                    if (this.position == adapterViewItemClickEvent.position) {
                        if (this.id == adapterViewItemClickEvent.id) {
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
        AdapterView<?> adapterView = this.view;
        int i = 0;
        int hashCode = (adapterView != null ? adapterView.hashCode() : 0) * 31;
        View view2 = this.clickedView;
        if (view2 != null) {
            i = view2.hashCode();
        }
        long j = this.id;
        return ((((hashCode + i) * 31) + this.position) * 31) + ((int) (j ^ (j >>> 32)));
    }

    @NotNull
    public String toString() {
        return "AdapterViewItemClickEvent(view=" + this.view + ", clickedView=" + this.clickedView + ", position=" + this.position + ", id=" + this.id + ")";
    }

    public AdapterViewItemClickEvent(@NotNull AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        this.view = adapterView;
        this.clickedView = view2;
        this.position = i;
        this.id = j;
    }

    @NotNull
    public final AdapterView<?> getView() {
        return this.view;
    }

    @Nullable
    public final View getClickedView() {
        return this.clickedView;
    }

    public final int getPosition() {
        return this.position;
    }

    public final long getId() {
        return this.id;
    }
}
