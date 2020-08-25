package com.appcan.nativemain;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Process;
import android.os.SystemClock;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.appcan.engine.ui.common.slideback.ActivityHelper;
import com.appcan.router.ACComponentBase;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.Iterator;

public class AppContext extends MultiDexApplication {
    private static AppContext sAppContext;
    private ArrayList<ACComponentBase.ComponentEntity> components = new ArrayList<>();

    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        if (!(StubApp.getOrigApplicationContext(getApplicationContext()).getPackageName() + ":pushservice").equals(getCurrentProcessName())) {
            registerActivityLifecycleCallbacks(ActivityHelper.getInstance());
            initComponentsFromXml();
            long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            invokeOncreate();
            Log.e("AppContext", " invokeOncreate: " + ((SystemClock.elapsedRealtimeNanos() - elapsedRealtimeNanos) / 1000000));
            long elapsedRealtimeNanos2 = SystemClock.elapsedRealtimeNanos();
            invokeOnAllInitialized();
            Log.e("AppContext", " invokeOnAllInitialized: " + ((SystemClock.elapsedRealtimeNanos() - elapsedRealtimeNanos2) / 1000000));
        }
    }

    public void onTerminate() {
        super.onTerminate();
        invokeOnTerminate();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        invokeOnConfigurationChanged(configuration);
    }

    public void onLowMemory() {
        super.onLowMemory();
        invokeOnLowMemory();
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        invokeOnTrimMemory(i);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        invokeAttachBaseContext(context);
        MultiDex.install(context);
    }

    private void initComponentsFromXml() {
        this.components = ACComponentBase.getComponents(getResources().getXml(R$xml.component));
    }

    private void invokeOncreate() {
        if (this.components != null && this.components.size() != 0) {
            Iterator<ACComponentBase.ComponentEntity> it = this.components.iterator();
            while (it.hasNext()) {
                ACComponentBase.ComponentEntity next = it.next();
                if (next != null) {
                    long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                    next.getComponentObj().onCreate(this);
                    Log.e("AppContext", next.getId() + " onCreate: " + ((SystemClock.elapsedRealtimeNanos() - elapsedRealtimeNanos) / 1000000));
                }
            }
        }
    }

    private void invokeOnAllInitialized() {
        if (this.components != null && this.components.size() != 0) {
            Iterator<ACComponentBase.ComponentEntity> it = this.components.iterator();
            while (it.hasNext()) {
                ACComponentBase.ComponentEntity next = it.next();
                if (next != null) {
                    next.getComponentObj().onAllInitialized(this);
                }
            }
        }
    }

    private void invokeOnConfigurationChanged(Configuration configuration) {
        if (this.components != null && this.components.size() != 0) {
            Iterator<ACComponentBase.ComponentEntity> it = this.components.iterator();
            while (it.hasNext()) {
                ACComponentBase.ComponentEntity next = it.next();
                if (next != null) {
                    next.getComponentObj().onConfigurationChanged(configuration);
                }
            }
        }
    }

    private void invokeOnLowMemory() {
        if (this.components != null && this.components.size() != 0) {
            Iterator<ACComponentBase.ComponentEntity> it = this.components.iterator();
            while (it.hasNext()) {
                ACComponentBase.ComponentEntity next = it.next();
                if (next != null) {
                    next.getComponentObj().onLowMemory();
                }
            }
        }
    }

    private void invokeOnTerminate() {
        if (this.components != null && this.components.size() != 0) {
            Iterator<ACComponentBase.ComponentEntity> it = this.components.iterator();
            while (it.hasNext()) {
                ACComponentBase.ComponentEntity next = it.next();
                if (next != null) {
                    next.getComponentObj().onTerminate();
                }
            }
        }
    }

    private void invokeOnTrimMemory(int i) {
        if (this.components != null && this.components.size() != 0) {
            Iterator<ACComponentBase.ComponentEntity> it = this.components.iterator();
            while (it.hasNext()) {
                ACComponentBase.ComponentEntity next = it.next();
                if (next != null) {
                    next.getComponentObj().onTrimMemory(i);
                }
            }
        }
    }

    private void invokeAttachBaseContext(Context context) {
        if (this.components != null && this.components.size() != 0) {
            Iterator<ACComponentBase.ComponentEntity> it = this.components.iterator();
            while (it.hasNext()) {
                ACComponentBase.ComponentEntity next = it.next();
                if (next != null) {
                    next.getComponentObj().attachBaseContext(context);
                }
            }
        }
    }

    private String getCurrentProcessName() {
        int myPid = Process.myPid();
        String str = "";
        for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) StubApp.getOrigApplicationContext(getApplicationContext()).getSystemService("activity")).getRunningAppProcesses()) {
            if (next.pid == myPid) {
                str = next.processName;
            }
        }
        return str;
    }
}
