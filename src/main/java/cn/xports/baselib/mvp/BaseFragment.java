package cn.xports.baselib.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.mvp.IPresenter;
import io.reactivex.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView {
    protected String TAG = getClass().getName();

    public void hideLoading() {
    }

    public void hideNoData() {
    }

    public void showLoading() {
    }

    public void showNoData(String str) {
    }

    public void showTipDialog(@NotNull String str) {
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onDetach() {
        RxDisposableManager.getInstance().clear(this.TAG);
        super.onDetach();
    }

    /* access modifiers changed from: protected */
    public void add(Disposable disposable) {
        RxDisposableManager.getInstance().add(this.TAG, disposable);
    }

    public void showMsg(String str) {
        Toast.makeText(getContext(), str, 0).show();
    }
}
