package coband.bsit.com.integral.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.constant.Constant;
import com.convenient.qd.core.widget.WebViewWrapper;

public class PointRulesActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout back;
    TextView title;
    WebViewWrapper webViewWrapper;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, PointRulesActivity.class));
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return R.layout.integral_activity_point_rules_layout;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setStatusBar(R.id.title_header);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (RelativeLayout) findViewById(R.id.back);
        this.webViewWrapper = findViewById(R.id.webViewWrapper);
        this.title.setText("信豆规则");
    }

    /* access modifiers changed from: protected */
    public void initListener() {
        PointRulesActivity.super.initListener();
        this.back.setOnClickListener(this);
        WebSettings settings = this.webViewWrapper.getWebView().getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        this.webViewWrapper.getWebView().setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                callback.invoke(str, true, true);
                super.onGeolocationPermissionsShowPrompt(str, callback);
            }

            public void onProgressChanged(WebView webView, int i) {
                if (i >= 100) {
                    PointRulesActivity.this.webViewWrapper.getProgressBar().setVisibility(8);
                } else {
                    if (PointRulesActivity.this.webViewWrapper.getProgressBar().getVisibility() == 8) {
                        PointRulesActivity.this.webViewWrapper.getProgressBar().setVisibility(0);
                    }
                    PointRulesActivity.this.webViewWrapper.getProgressBar().setProgress(i);
                }
                super.onProgressChanged(webView, i);
            }
        });
        WebViewWrapper webViewWrapper2 = this.webViewWrapper;
        webViewWrapper2.loadUrl(Constant.H5_SERVER_URL + "appweb/pointsRule");
    }

    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            finish();
        }
    }

    public void onBackPressed() {
        if (this.webViewWrapper.getWebView().canGoBack()) {
            this.webViewWrapper.getWebView().goBack();
        } else {
            PointRulesActivity.super.onBackPressed();
        }
    }
}
