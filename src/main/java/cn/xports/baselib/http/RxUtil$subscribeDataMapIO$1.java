package cn.xports.baselib.http;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.ToastUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: RxUtil.kt */
final class RxUtil$subscribeDataMapIO$1 extends Lambda implements Function1<DataMap, Unit> {
    public static final RxUtil$subscribeDataMapIO$1 INSTANCE = new RxUtil$subscribeDataMapIO$1();

    RxUtil$subscribeDataMapIO$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        ToastUtil.showMsg(dataMap.getMessage());
    }
}
