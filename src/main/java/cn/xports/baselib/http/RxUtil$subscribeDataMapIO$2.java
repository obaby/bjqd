package cn.xports.baselib.http;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.mvp.IView;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/baselib/http/RxUtil$subscribeDataMapIO$2", "Lcn/xports/baselib/http/ResponseDataMap;", "onError", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "onSuccess", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: RxUtil.kt */
public final class RxUtil$subscribeDataMapIO$2 extends ResponseDataMap {
    final /* synthetic */ Function1 $fail;
    final /* synthetic */ IView $iView;
    final /* synthetic */ boolean $showProcess;
    final /* synthetic */ Function1 $success;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxUtil$subscribeDataMapIO$2(Function1 function1, Function1 function12, IView iView, boolean z, IView iView2, boolean z2) {
        super(iView2, z2);
        this.$success = function1;
        this.$fail = function12;
        this.$iView = iView;
        this.$showProcess = z;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        this.$success.invoke(dataMap);
    }

    public void onError(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        this.$fail.invoke(dataMap);
    }
}
