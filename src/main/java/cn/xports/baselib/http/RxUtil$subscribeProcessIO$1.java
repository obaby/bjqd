package cn.xports.baselib.http;

import cn.xports.baselib.util.ToastUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "T", "Lcn/xports/baselib/bean/Response;", "it", "Lcn/xports/baselib/http/ResponseThrowable;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: RxUtil.kt */
final class RxUtil$subscribeProcessIO$1 extends Lambda implements Function1<ResponseThrowable, Unit> {
    public static final RxUtil$subscribeProcessIO$1 INSTANCE = new RxUtil$subscribeProcessIO$1();

    RxUtil$subscribeProcessIO$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ResponseThrowable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull ResponseThrowable responseThrowable) {
        Intrinsics.checkParameterIsNotNull(responseThrowable, "it");
        ToastUtil.showMsg(responseThrowable.getMessage());
    }
}
