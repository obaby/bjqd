package com.appcan.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.appcan.engine.plugin.ThirdPluginMgr;
import com.appcan.engine.ui.browser.webview.ACBrowserActivity;
import com.stub.StubApp;

public class AcWeEngine {
    private static AcWeEngine sInstance;
    private String appId = null;
    private boolean isRunner = false;
    private Context mContext;
    private ThirdPluginMgr mThirdPluginMgr;
    private XmlResourceParser plugins;

    private AcWeEngine() {
    }

    public static AcWeEngine getInstance() {
        if (sInstance == null) {
            sInstance = new AcWeEngine();
        }
        return sInstance;
    }

    public boolean initSync(Context context) {
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        return true;
    }

    public boolean isRunner() {
        return this.isRunner;
    }

    public void setRunner(boolean z) {
        this.isRunner = z;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public XmlResourceParser getPlugins() {
        return this.plugins;
    }

    public void setPlugins(XmlResourceParser xmlResourceParser) {
        this.plugins = xmlResourceParser;
    }

    public void startWidget(Activity activity) {
        startWidget(activity, "");
    }

    public void startWidget(Activity activity, String str) {
        String str2;
        if (!this.isRunner || TextUtils.isEmpty(this.appId)) {
            StringBuilder sb = new StringBuilder();
            sb.append("file:///android_asset/widget/index.html#");
            if (TextUtils.isEmpty(str)) {
                str = "/";
            }
            sb.append(str);
            str2 = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("file:///");
            sb2.append(Environment.getExternalStorageDirectory());
            sb2.append("/ac/runner/wgt/");
            sb2.append(this.appId);
            sb2.append("/index.html#");
            if (TextUtils.isEmpty(str)) {
                str = "/";
            }
            sb2.append(str);
            str2 = sb2.toString();
        }
        Bundle bundle = new Bundle();
        bundle.putString("url_key", str2);
        Intent intent = new Intent(activity, ACBrowserActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void startCustomWidget(Activity activity, Bundle bundle) {
        Intent intent = new Intent(activity, ACBrowserActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public final ThirdPluginMgr getThirdPlugins() {
        if (this.mThirdPluginMgr == null) {
            initPlugin();
        }
        return this.mThirdPluginMgr;
    }

    private final void initPlugin() {
        if (this.mThirdPluginMgr == null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mThirdPluginMgr = new ThirdPluginMgr(this.mContext);
            if (this.plugins == null) {
                this.plugins = this.mContext.getResources().getXml(this.mContext.getResources().getIdentifier("plugin", "xml", this.mContext.getPackageName()));
            }
            this.mThirdPluginMgr.initClass(this.plugins, (ClassLoader) null);
            Log.i("DL", "plugins loading total costs " + (System.currentTimeMillis() - currentTimeMillis));
        }
    }
}
