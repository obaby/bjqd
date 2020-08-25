package cn.xports.baselib;

import android.app.Application;
import android.util.Log;
import java.io.Serializable;

public class App implements Serializable {
    public static final Application INSTANCE;

    static {
        Application application;
        Application application2;
        try {
            application = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke((Object) null, new Object[0]);
            if (application != null) {
                INSTANCE = application;
                return;
            }
            try {
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
            } catch (Exception e) {
                e = e;
                try {
                    Log.e("App", "Failed to getInstance current application from AppGlobals." + e.getMessage());
                    application2 = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke((Object) null, new Object[0]);
                } catch (Exception unused) {
                    Log.e("App", "Failed to getInstance current application from ActivityThread." + e.getMessage());
                    application2 = application;
                } catch (Throwable th) {
                    th = th;
                    INSTANCE = application;
                    throw th;
                }
                INSTANCE = application2;
            }
        } catch (Exception e2) {
            e = e2;
            application = null;
            Log.e("App", "Failed to getInstance current application from AppGlobals." + e.getMessage());
            application2 = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke((Object) null, new Object[0]);
            INSTANCE = application2;
        } catch (Throwable th2) {
            application = null;
            th = th2;
            INSTANCE = application;
            throw th;
        }
    }

    public static Application getInstance() {
        return INSTANCE;
    }
}
