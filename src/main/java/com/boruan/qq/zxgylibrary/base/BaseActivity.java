package com.boruan.qq.zxgylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.boruan.qq.zxgylibrary.constants.AllActivitiesHolder;

public abstract class BaseActivity extends AppCompatActivity {
    public WebView commonWeb;
    private Context mContext;
    Unbinder unbinder;

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract void init(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AllActivitiesHolder.addAct(this);
        setContentView(getLayoutId());
        this.mContext = this;
        this.unbinder = ButterKnife.bind((Activity) this);
        setRequestedOrientation(1);
        initWeb();
        init(bundle);
    }

    public void initWeb() {
        this.commonWeb = new WebView(this.mContext);
        this.commonWeb.getSettings().setJavaScriptEnabled(true);
        this.commonWeb.getSettings().setSupportZoom(true);
        this.commonWeb.getSettings().setBuiltInZoomControls(true);
        this.commonWeb.getSettings().setDisplayZoomControls(false);
        this.commonWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.commonWeb.getSettings().setLoadWithOverviewMode(true);
        this.commonWeb.getSettings().setUseWideViewPort(true);
        this.commonWeb.getSettings().setTextZoom(100);
        this.commonWeb.setVerticalScrollBarEnabled(false);
        this.commonWeb.getSettings().setCacheMode(2);
        this.commonWeb.setScrollBarStyle(0);
        this.commonWeb.getSettings().setDefaultTextEncodingName("UTF-8");
        if (Build.VERSION.SDK_INT >= 21) {
            WebSettings settings = this.commonWeb.getSettings();
            this.commonWeb.getSettings();
            settings.setMixedContentMode(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.unbinder.unbind();
        AllActivitiesHolder.removeAct(this);
    }

    public Context getContext() {
        return this.mContext;
    }
}
