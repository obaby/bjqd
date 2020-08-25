package cn.xports.baselib.mvp;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"Lcn/xports/baselib/mvp/IPresenter;", "", "onDestroy", "", "onStart", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: IPresenter.kt */
public interface IPresenter {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    /* compiled from: IPresenter.kt */
    public static final class DefaultImpls {
        public static void onDestroy(IPresenter iPresenter) {
        }

        public static void onStart(IPresenter iPresenter) {
        }
    }

    void onDestroy();

    void onStart();
}
