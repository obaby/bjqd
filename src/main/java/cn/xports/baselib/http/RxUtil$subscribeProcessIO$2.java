package cn.xports.baselib.http;

import cn.xports.baselib.mvp.IView;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0015\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/baselib/http/RxUtil$subscribeProcessIO$2", "Lcn/xports/baselib/http/ProcessObserver;", "next", "", "value", "(Lcn/xports/baselib/bean/Response;)V", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: RxUtil.kt */
public final class RxUtil$subscribeProcessIO$2 extends ProcessObserver<T> {
    final /* synthetic */ Function1 $fail;
    final /* synthetic */ IView $iView;
    final /* synthetic */ Function1 $success;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxUtil$subscribeProcessIO$2(Function1 function1, Function1 function12, IView iView, IView iView2) {
        super(iView2);
        this.$success = function1;
        this.$fail = function12;
        this.$iView = iView;
    }

    public void next(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "value");
        this.$success.invoke(t);
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        if (responseThrowable != null) {
            this.$fail.invoke(responseThrowable);
        }
    }
}
