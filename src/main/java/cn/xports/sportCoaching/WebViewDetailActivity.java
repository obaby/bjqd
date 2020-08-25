package cn.xports.sportCoaching;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qdplugin.R;

public class WebViewDetailActivity extends BaseBussActivity<IPresenter> {
    public static final String CONTENT = "content";
    public static final String CONTENT_TYPE = "content_type";
    public static final String COUNT = "count";
    public static final String DATE = "date";
    public static final String TITLE = "title";
    /* access modifiers changed from: private */
    public ProgressWebView webView;

    /* access modifiers changed from: protected */
    public String getChildTitle() {
        return "";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_web_view_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(TITLE);
        int intExtra = intent.getIntExtra(CONTENT_TYPE, 0);
        String stringExtra2 = intent.getStringExtra("content");
        String stringExtra3 = intent.getStringExtra(DATE);
        String stringExtra4 = intent.getStringExtra(COUNT);
        TextView textView = (TextView) findViewById(R.id.tv_web_see_count);
        this.webView = (ProgressWebView) findViewById(R.id.wv_content);
        this.webView.setScrollContainer(false);
        this.webView.setScrollbarFadingEnabled(false);
        this.webView.setScrollBarStyle(33554432);
        ((TextView) findViewById(R.id.tv_web_title)).setText(stringExtra);
        ((TextView) findViewById(R.id.tv_web_date)).setText(stringExtra3);
        if (TextUtils.isEmpty(stringExtra4)) {
            textView.setVisibility(8);
        }
        loadweb(stringExtra2, intExtra);
    }

    private void loadweb(String str, int i) {
        this.webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                WebViewDetailActivity.this.webView.loadUrl(str);
                return true;
            }
        });
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(1);
        if (i != 0) {
            this.webView.loadDataWithBaseURL((String) null, "<html><header></header>" + str + "</html>", "text/html", "utf-8", (String) null);
        } else if (!TextUtils.isEmpty(str)) {
            this.webView.loadUrl(str);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        if (this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        finish();
        return true;
    }
}
