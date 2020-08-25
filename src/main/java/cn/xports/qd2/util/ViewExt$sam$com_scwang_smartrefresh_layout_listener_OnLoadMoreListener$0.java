package cn.xports.qd2.util;

import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
/* compiled from: ViewExt.kt */
final class ViewExt$sam$com_scwang_smartrefresh_layout_listener_OnLoadMoreListener$0 implements OnLoadMoreListener {
    private final /* synthetic */ Function1 function;

    ViewExt$sam$com_scwang_smartrefresh_layout_listener_OnLoadMoreListener$0(Function1 function1) {
        this.function = function1;
    }

    public final /* synthetic */ void onLoadMore(@NotNull @NonNull RefreshLayout refreshLayout) {
        Intrinsics.checkParameterIsNotNull(refreshLayout, "p0");
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(refreshLayout), "invoke(...)");
    }
}
