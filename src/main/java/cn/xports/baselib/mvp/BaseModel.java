package cn.xports.baselib.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Lcn/xports/baselib/mvp/BaseModel;", "Lcn/xports/baselib/mvp/IModel;", "Landroid/arch/lifecycle/LifecycleObserver;", "()V", "onDestroy", "", "owner", "Landroid/arch/lifecycle/LifecycleOwner;", "onDestroy$baselib_release", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BaseModel.kt */
public class BaseModel implements IModel, LifecycleObserver {
    public void onDestroy() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy$baselib_release(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        lifecycleOwner.getLifecycle().removeObserver(this);
    }
}
