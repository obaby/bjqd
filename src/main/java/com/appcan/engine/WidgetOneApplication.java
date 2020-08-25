package com.appcan.engine;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.stub.StubApp;

public class WidgetOneApplication extends Application {
    private boolean init = false;

    public void onCreate() {
        super.onCreate();
        AcWeEngine.getInstance().initSync(StubApp.getOrigApplicationContext(getApplicationContext()));
        this.init = true;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
