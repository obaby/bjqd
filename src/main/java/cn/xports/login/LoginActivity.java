package cn.xports.login;

import android.app.Activity;
import android.net.Uri;
import cn.xports.base.Router;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.mvp.BaseActivity;
import cn.xports.baselib.util.BarUtils;
import cn.xports.baselib.util.LoadingDialog;
import cn.xports.baselib.util.SPUtil;
import cn.xports.export.EventHandler;
import cn.xports.login.LoginPresenter;
import java.util.HashMap;

public class LoginActivity extends BaseActivity implements LoginPresenter.LoginCallback {
    protected int loadingCount = 0;
    private LoadingDialog loadingDialog;

    public void initData() {
        BarUtils.setStatusBarLightMode((Activity) this, true);
        BarUtils.setStatusBarColor((Activity) this, 0);
        this.loadingDialog = new LoadingDialog(this);
        new LoginPresenter(this, this).login(SPUtil.Companion.getINSTANCE().getStringValue("phone"), SPUtil.Companion.getINSTANCE().getStringValue("outUid"));
    }

    public void finish() {
        RxDisposableManager.getInstance().clear(getTAG());
        super.finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

    public void onSuccess() {
        String stringExtra = getStringExtra("path");
        HashMap hashMap = new HashMap();
        if (stringExtra.equals("")) {
            Uri data = getIntent().getData();
            for (String next : data.getQueryParameterNames()) {
                if (!"path".equals(next) && data.getQueryParameter(next) != null) {
                    hashMap.put(next, data.getQueryParameter(next));
                }
            }
        }
        startActivity(Router.getIntent(stringExtra, hashMap));
        finish();
    }

    public void onError() {
        finish();
    }
}
