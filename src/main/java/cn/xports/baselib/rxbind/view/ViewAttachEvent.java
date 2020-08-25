package cn.xports.baselib.rxbind.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcn/xports/baselib/rxbind/view/ViewAttachEvent;", "", "()V", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "Lcn/xports/baselib/rxbind/view/ViewAttachAttachedEvent;", "Lcn/xports/baselib/rxbind/view/ViewAttachDetachedEvent;", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewAttachEvent.kt */
public abstract class ViewAttachEvent {
    @NotNull
    public abstract View getView();

    private ViewAttachEvent() {
    }

    public /* synthetic */ ViewAttachEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
