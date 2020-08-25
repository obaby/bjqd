package cn.xports.baselib.rxbind.widget;

import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u0017\u0010\b\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewNothingSelectionEvent;", "Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionEvent;", "view", "Landroid/widget/AdapterView;", "(Landroid/widget/AdapterView;)V", "getView", "()Landroid/widget/AdapterView;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterViewSelectionEvent.kt */
public final class AdapterViewNothingSelectionEvent extends AdapterViewSelectionEvent {
    @NotNull
    private final AdapterView<?> view;

    public static /* synthetic */ AdapterViewNothingSelectionEvent copy$default(AdapterViewNothingSelectionEvent adapterViewNothingSelectionEvent, AdapterView<?> adapterView, int i, Object obj) {
        if ((i & 1) != 0) {
            adapterView = adapterViewNothingSelectionEvent.getView();
        }
        return adapterViewNothingSelectionEvent.copy(adapterView);
    }

    @NotNull
    public final AdapterView<?> component1() {
        return getView();
    }

    @NotNull
    public final AdapterViewNothingSelectionEvent copy(@NotNull AdapterView<?> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        return new AdapterViewNothingSelectionEvent(adapterView);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof AdapterViewNothingSelectionEvent) && Intrinsics.areEqual(getView(), ((AdapterViewNothingSelectionEvent) obj).getView());
        }
        return true;
    }

    public int hashCode() {
        AdapterView<?> view2 = getView();
        if (view2 != null) {
            return view2.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "AdapterViewNothingSelectionEvent(view=" + getView() + ")";
    }

    @NotNull
    public AdapterView<?> getView() {
        return this.view;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdapterViewNothingSelectionEvent(@NotNull AdapterView<?> adapterView) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(adapterView, "view");
        this.view = adapterView;
    }
}
