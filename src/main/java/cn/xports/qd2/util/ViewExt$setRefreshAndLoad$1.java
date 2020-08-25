package cn.xports.qd2.util;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/scwang/smartrefresh/layout/api/RefreshLayout;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: ViewExt.kt */
final class ViewExt$setRefreshAndLoad$1 extends Lambda implements Function1<RefreshLayout, Unit> {
    public static final ViewExt$setRefreshAndLoad$1 INSTANCE = new ViewExt$setRefreshAndLoad$1();

    ViewExt$setRefreshAndLoad$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RefreshLayout) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull RefreshLayout refreshLayout) {
        Intrinsics.checkParameterIsNotNull(refreshLayout, "it");
    }
}
