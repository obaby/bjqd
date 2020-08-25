package cn.xports.qd2.circle;

import cn.xports.baselib.mvp.IView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcn/xports/qd2/circle/CircleLikeView;", "Lcn/xports/baselib/mvp/IView;", "optionFail", "", "msg", "", "optionSuccess", "type", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CircleLikeView.kt */
public interface CircleLikeView extends IView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    /* compiled from: CircleLikeView.kt */
    public static final class DefaultImpls {
        public static void optionFail(CircleLikeView circleLikeView, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "msg");
        }
    }

    void optionFail(@NotNull String str);

    void optionSuccess(int i);
}
