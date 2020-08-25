package cn.xports.qd2.circle.videoPlayer;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.LoadingDialog;
import cn.xports.export.EventHandler;
import com.convenient.qd.lib.adaptScreen.IAdaptScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyBaseActivity extends AppCompatActivity implements IView, IAdaptScreen {
    private String TAG = getClass().getSimpleName();
    protected int loadingCount = 0;
    private LoadingDialog loadingDialog;

    public boolean cancelAdapt() {
        return true;
    }

    public int getScreenSize() {
        return 375;
    }

    public void hideNoData() {
    }

    public boolean isBaseOnWidth() {
        return true;
    }

    public boolean needResetAdapt() {
        return true;
    }

    public void showMsg(@NotNull String str) {
    }

    public void showNoData(@Nullable String str) {
    }

    public void showTipDialog(@NotNull String str) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@android.support.annotation.Nullable Bundle bundle) {
        ActionBar supportActionBar;
        super.onCreate(bundle);
        setStatusBarTransparent();
        if (!(getSupportActionBar() == null || (supportActionBar = getSupportActionBar()) == null)) {
            supportActionBar.hide();
        }
        this.loadingDialog = new LoadingDialog(this);
    }

    public void showLoading() {
        this.loadingCount++;
        if (EventHandler.getInstance().getLoadingListener() != null) {
            EventHandler.getInstance().getLoadingListener().showLoading();
        } else if (this.loadingDialog != null) {
            this.loadingDialog.show();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.loadingCount > 0) {
            this.loadingCount = 1;
            hideLoading();
        }
        super.onStop();
    }

    public void hideLoading() {
        this.loadingCount--;
        if (this.loadingCount <= 0) {
            if (EventHandler.getInstance().getLoadingListener() != null) {
                EventHandler.getInstance().getLoadingListener().hideLoading();
            } else if (this.loadingDialog != null) {
                this.loadingDialog.dismiss();
            }
        }
    }

    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        RxDisposableManager.getInstance().clear(this.TAG);
    }
}
