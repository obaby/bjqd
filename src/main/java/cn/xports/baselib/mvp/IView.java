package cn.xports.baselib.mvp;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcn/xports/baselib/mvp/IView;", "", "hideLoading", "", "hideNoData", "showLoading", "showMsg", "msg", "", "showNoData", "showTipDialog", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: IView.kt */
public interface IView {
    void hideLoading();

    void hideNoData();

    void showLoading();

    void showMsg(@NotNull String str);

    void showNoData(@Nullable String str);

    void showTipDialog(@NotNull String str);
}
