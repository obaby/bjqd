package cn.xports.baselib.rxbind.widget;

import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcn/xports/baselib/rxbind/widget/AdapterViewSelectionEvent;", "", "()V", "view", "Landroid/widget/AdapterView;", "getView", "()Landroid/widget/AdapterView;", "Lcn/xports/baselib/rxbind/widget/AdapterViewItemSelectionEvent;", "Lcn/xports/baselib/rxbind/widget/AdapterViewNothingSelectionEvent;", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AdapterViewSelectionEvent.kt */
public abstract class AdapterViewSelectionEvent {
    @NotNull
    public abstract AdapterView<?> getView();

    private AdapterViewSelectionEvent() {
    }

    public /* synthetic */ AdapterViewSelectionEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
