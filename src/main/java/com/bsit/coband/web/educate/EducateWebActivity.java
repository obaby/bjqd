package com.bsit.coband.web.educate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import coband.bsit.com.integral.R;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bsit.coband.net.api.AppModule;
import com.convenient.qd.core.base.eventbus.Event;
import com.convenient.qd.core.base.eventbus.EventBusUtils;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.bean.MenuTab;
import com.convenient.qd.core.utils.ActivityCollector;
import com.convenient.qd.core.utils.CommonUtils;
import com.convenient.qd.core.utils.NetworkUtils;
import com.convenient.qd.core.utils.ShareUtil;
import com.convenient.qd.core.utils.StringUtil;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import com.convenient.qd.core.widget.WebViewWrapper;
import com.stub.StubApp;
import java.io.File;
import java.util.Calendar;
import java.util.Locale;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

@Route(path = "/web/EducateWebActivity")
public class EducateWebActivity extends BaseActivity {
    private int REQUEST_CODE = 1234;
    private Uri imageUri;
    @BindView(2131296477)
    Toolbar mBaseToorbar;
    @BindView(2131299713)
    ImageView mIvFinish;
    /* access modifiers changed from: private */
    public MenuTab mMenuTab;
    @BindView(2131299242)
    RelativeLayout mRltRefresh;
    @BindView(2131299712)
    TextView mToolbarTitle;
    /* access modifiers changed from: private */
    public ValueCallback<Uri[]> mUploadCallbackAboveL;
    /* access modifiers changed from: private */
    public ValueCallback<Uri> mUploadCallbackBelow;
    @BindView(2131300787)
    View mView_top;
    @BindView(2131300810)
    WebViewWrapper webViewWrapper;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return 2131427843;
    }

    /* access modifiers changed from: protected */
    public void initImmersionBar() {
        EducateWebActivity.super.initImmersionBar();
        setStatusBar(R.id.base_toorbar);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.bsit.coband.web.educate.EducateWebActivity, java.lang.Object, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void initView() {
        ActivityCollector.addOneActivity(this);
        this.mIvFinish.setVisibility(0);
        EventBusUtils.register(this);
        if (getIntent() != null) {
            this.mMenuTab = getIntent().getSerializableExtra("menuTab");
            if (this.mMenuTab != null) {
                this.mToolbarTitle.setText(StringUtil.getString(this.mMenuTab.getAppName()));
                if (this.mMenuTab.getAddToorbar() != 1) {
                    this.mBaseToorbar.setVisibility(8);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mView_top.getLayoutParams();
                    layoutParams.height = CommonUtils.getStatusBarHeight(this);
                    this.mView_top.setLayoutParams(layoutParams);
                }
            }
        }
        1 r0 = new 1(this);
        String path = StubApp.getOrigApplicationContext(getApplicationContext()).getDir("database", 0).getPath();
        WebSettings settings = this.webViewWrapper.getWebView().getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath(path);
        settings.setMediaPlaybackRequiresUserGesture(false);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        this.webViewWrapper.getWebView().setWebViewClient(r0);
        this.webViewWrapper.getWebView().setWebChromeClient(new 2(this));
        settings.setCacheMode(2);
        if (this.mMenuTab != null) {
            doGetEducateUrl(StringUtil.getString(this.mMenuTab.getAppId()), StringUtil.getString(this.mMenuTab.getAppStartUrl()));
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.bsit.coband.web.educate.EducateWebActivity] */
    /* access modifiers changed from: private */
    public void doGetEducateUrl(String str, String str2) {
        if (!NetworkUtils.isNetAvailable(StubApp.getOrigApplicationContext(getApplicationContext()))) {
            this.mRltRefresh.setVisibility(0);
            Toast.makeText(this.activity, getString(R.string.error_net), 0).show();
            return;
        }
        LoadingDiaLogUtils.showLoadingDialog(this);
        AppModule.getInstance().getH5UrlCardData(str, str2, new 3(this));
    }

    @OnClick({2131299709, 2131299713, 2131299242})
    public void onViewClicked(View view) {
        if (view.getId() == 2131299709) {
            if (this.webViewWrapper.getWebView().canGoBack()) {
                if (StringUtil.getString(this.webViewWrapper.getWebView().getUrl()).startsWith("about:blank")) {
                    finish();
                }
                this.webViewWrapper.getWebView().goBack();
                return;
            }
            finish();
        } else if (view.getId() == 2131299713) {
            finish();
        } else if (view.getId() == 2131299242 && this.mMenuTab != null) {
            this.mRltRefresh.setVisibility(8);
            doGetEducateUrl(StringUtil.getString(this.mMenuTab.getAppId()), StringUtil.getString(this.mMenuTab.getAppStartUrl()));
        }
    }

    public void onBackPressed() {
        if (this.webViewWrapper.getWebView().canGoBack()) {
            if (StringUtil.getString(this.webViewWrapper.getWebView().getUrl()).startsWith("about:blank")) {
                finish();
            }
            this.webViewWrapper.getWebView().goBack();
            return;
        }
        EducateWebActivity.super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        clearCookies(StubApp.getOrigApplicationContext(getApplicationContext()));
        if (this.webViewWrapper.getWebView() != null) {
            this.webViewWrapper.getWebView().destroy();
        }
        EventBusUtils.unregister(this);
        EducateWebActivity.super.onDestroy();
    }

    public static void clearCookies(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.removeAllCookie();
        instance.removeSessionCookie();
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.bsit.coband.web.educate.EducateWebActivity, com.convenient.qd.core.base.mvp.BaseActivity] */
    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        EducateWebActivity.super.onActivityResult(i, i2, intent);
        if (i == this.REQUEST_CODE) {
            if (this.mUploadCallbackBelow != null) {
                chooseBelow(i2, intent);
            } else if (this.mUploadCallbackAboveL != null) {
                chooseAbove(i2, intent);
            } else {
                Toast.makeText(this, "发生错误", 0).show();
            }
        }
        if (ShareUtil.getInstance().socialHelper != null) {
            ShareUtil.getInstance().socialHelper.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: private */
    public void takePhoto() {
        this.imageUri = Uri.fromFile(new File((Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_PICTURES + File.separator) + ("IMG_" + DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg")));
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", this.imageUri);
        Intent createChooser = Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "Image Chooser");
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", new Parcelable[]{intent});
        startActivityForResult(createChooser, this.REQUEST_CODE);
    }

    private void chooseBelow(int i, Intent intent) {
        Log.e("WangJ", "返回调用方法--chooseBelow");
        if (-1 == i) {
            updatePhotos();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null) {
                    Log.e("WangJ", "系统返回URI：" + data.toString());
                    this.mUploadCallbackBelow.onReceiveValue(data);
                } else {
                    this.mUploadCallbackBelow.onReceiveValue((Object) null);
                }
            } else {
                Log.e("WangJ", "自定义结果：" + this.imageUri.toString());
                this.mUploadCallbackBelow.onReceiveValue(this.imageUri);
            }
        } else {
            this.mUploadCallbackBelow.onReceiveValue((Object) null);
        }
        this.mUploadCallbackBelow = null;
    }

    private void chooseAbove(int i, Intent intent) {
        Log.e("WangJ", "返回调用方法--chooseAbove");
        if (-1 == i) {
            updatePhotos();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null) {
                    Uri[] uriArr = {data};
                    for (Uri uri : uriArr) {
                        Log.e("WangJ", "系统返回URI：" + uri.toString());
                    }
                    this.mUploadCallbackAboveL.onReceiveValue(uriArr);
                } else {
                    this.mUploadCallbackAboveL.onReceiveValue((Object) null);
                }
            } else {
                Log.e("WangJ", "自定义结果：" + this.imageUri.toString());
                this.mUploadCallbackAboveL.onReceiveValue(new Uri[]{this.imageUri});
            }
        } else {
            this.mUploadCallbackAboveL.onReceiveValue((Object) null);
        }
        this.mUploadCallbackAboveL = null;
    }

    private void updatePhotos() {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(this.imageUri);
        sendBroadcast(intent);
    }

    @Subscribe
    public void onMyRefreshEvent(@NotNull Event event) {
        int code = event.getCode();
        if (code == 1000 || code == 7001) {
            this.webViewWrapper.getWebView().reload();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.bsit.coband.web.educate.EducateWebActivity] */
    /* access modifiers changed from: private */
    public void activeShare(String str, String str2) {
        LoadingDiaLogUtils.showLoadingDialog(this);
        AppModule.getInstance().activeShare(str, str2, new 4(this));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        EducateWebActivity.super.onResume();
        if (this.webViewWrapper.getWebView() != null) {
            this.webViewWrapper.getWebView().onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        EducateWebActivity.super.onPause();
        if (this.webViewWrapper.getWebView() != null) {
            this.webViewWrapper.getWebView().onPause();
        }
    }
}
