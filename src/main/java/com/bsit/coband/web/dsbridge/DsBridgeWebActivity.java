package com.bsit.coband.web.dsbridge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import coband.bsit.com.integral.R;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bsit.coband.net.api.AppModule;
import com.bumptech.glide.Glide;
import com.convenient.qd.core.base.eventbus.Event;
import com.convenient.qd.core.base.eventbus.EventBusUtils;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.bean.MenuTab;
import com.convenient.qd.core.user.UserDBHelper;
import com.convenient.qd.core.utils.CommonUtils;
import com.convenient.qd.core.utils.NetworkUtils;
import com.convenient.qd.core.utils.StringUtil;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import com.stub.StubApp;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.greenrobot.eventbus.Subscribe;
import wendu.dsbridge.DWebView;

@Route(path = "/web/DsBridgeWebActivity")
public class DsBridgeWebActivity extends BaseActivity implements JsApi$OnRightBtnListener {
    public static final String DS_BRIDGE_WEB_URL = "bridge_web_url";
    /* access modifiers changed from: private */
    public String articleId;
    private CountDownTimer countDownTimer;
    /* access modifiers changed from: private */
    public String countTime;
    private JsApi jsApi;
    @BindView(2131299713)
    ImageView mIvFinish;
    private MenuTab mMenuTab;
    @BindView(2131299712)
    TextView mToolbarTitle;
    @BindView(2131298921)
    ProgressBar progressBar;
    @BindView(2131300135)
    TextView tv_finish;
    @BindView(2131300810)
    DWebView webView;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return 2131427445;
    }

    public void onRightBtnClick() {
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bsit.coband.web.dsbridge.DsBridgeWebActivity, java.lang.Object, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void initView() {
        this.jsApi = new JsApi(this);
        this.mIvFinish.setVisibility(0);
        if (getIntent() != null) {
            this.mMenuTab = getIntent().getSerializableExtra("menuTab");
        }
        EventBusUtils.register(this);
        DWebView dWebView = this.webView;
        DWebView.setWebContentsDebuggingEnabled(true);
        this.webView.addJavascriptObject(this.jsApi, (String) null);
        if (this.mMenuTab != null) {
            doGetEducateUrl(StringUtil.getString(this.mMenuTab.getAppId()), StringUtil.getString(this.mMenuTab.getAppStartUrl()));
        }
    }

    /* access modifiers changed from: protected */
    public void initListener() {
        DsBridgeWebActivity.super.initListener();
        this.webView.setWebViewClient(new 1(this));
        this.webView.setWebChromeClient(new 2(this));
        this.jsApi.setOnRightBtnListener(this);
    }

    /* access modifiers changed from: protected */
    public void initImmersionBar() {
        DsBridgeWebActivity.super.initImmersionBar();
        setStatusBar(R.id.base_toorbar);
    }

    @OnClick({2131299709, 2131299713, 2131300135, 2131299712})
    public void onViewClicked(View view) {
        if (view.getId() == 2131299709) {
            if (this.webView.canGoBack()) {
                if (StringUtil.getString(this.webView.getUrl()).startsWith("about:blank")) {
                    finish();
                }
                this.webView.goBack();
                return;
            }
            finish();
        } else if (view.getId() == 2131299713) {
            finish();
        } else if (view.getId() == 2131300135) {
            this.webView.callHandler("nativeRightBtnHander", new Object[0]);
        } else if (view.getId() == 2131299712) {
            this.webView.callHandler("titleBtnHander", new Object[]{this.mToolbarTitle.getText().toString()});
        }
    }

    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            if (StringUtil.getString(this.webView.getUrl()).startsWith("about:blank")) {
                finish();
            }
            this.webView.goBack();
            return;
        }
        DsBridgeWebActivity.super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void upLoadReadTime(int i, String str) {
        AppModule.getInstance().readAddCredit(i, str, new 3(this));
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.bsit.coband.web.dsbridge.DsBridgeWebActivity] */
    private void doGetEducateUrl(String str, String str2) {
        if (!NetworkUtils.isNetAvailable(StubApp.getOrigApplicationContext(getApplicationContext()))) {
            Toast.makeText(this.activity, getString(R.string.error_net), 0).show();
            return;
        }
        LoadingDiaLogUtils.showLoadingDialog(this);
        AppModule.getInstance().getH5UrlCardData(str, str2, new 4(this));
    }

    public void setRightBtnVisitable(String str) {
        if ("1".equals(str)) {
            this.tv_finish.setVisibility(0);
            this.mIvFinish.setVisibility(8);
            return;
        }
        this.tv_finish.setVisibility(8);
        this.mIvFinish.setVisibility(0);
    }

    public void setRightBtnTitle(String str) {
        this.tv_finish.setText(str);
    }

    public void resetRightBtn() {
        this.tv_finish.setVisibility(8);
        this.mIvFinish.setVisibility(0);
    }

    public void closeCurView() {
        finish();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.bsit.coband.web.dsbridge.DsBridgeWebActivity] */
    public void share(String str, String str2) {
        LoadingDiaLogUtils.showLoadingDialog(this);
        AppModule.getInstance().activeShare(str, str2, new 5(this));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.support.v4.app.FragmentActivity, com.bsit.coband.web.dsbridge.DsBridgeWebActivity] */
    public void saveUrlToPhotosAlbum(String str) {
        Glide.with((FragmentActivity) this).asBitmap().load(str).into(new 6(this));
    }

    public void saveBase64ToPhotosAlbum(String str) {
        saveToPhotosAlbum(CommonUtils.stringToBitmap(str));
    }

    public void newsReadStart(String str, String str2) {
        this.countTime = str2;
        this.articleId = str;
        timeCountDown();
    }

    private void timeCountDown() {
        this.countDownTimer = new 7(this, 1000 * Long.valueOf(this.countTime).longValue(), 1000);
        this.countDownTimer.start();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        DsBridgeWebActivity.super.onPause();
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
            this.countDownTimer = null;
        }
    }

    @Subscribe
    public void onMyRefreshEvent(Event event) {
        int code = event.getCode();
        if (code == 1001) {
            this.jsApi.returnLoginToken(UserDBHelper.getInstance().getAccessToken());
        } else if (code == 9001) {
            this.jsApi.returnShiMingToken(UserDBHelper.getInstance().getAccessToken());
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        DsBridgeWebActivity.super.onDestroy();
        EventBusUtils.unregister(this);
    }

    /* access modifiers changed from: private */
    public void saveToPhotosAlbum(Bitmap bitmap) {
        String file = Environment.getExternalStorageDirectory().toString();
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        File file2 = new File(file, format + ".JPEG");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file2));
            sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }
}
