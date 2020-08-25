package com.appcan.engine.ui.common.slideback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import java.util.Iterator;
import java.util.Stack;

public class ActivityHelper implements Application.ActivityLifecycleCallbacks {
    private static ActivityHelper sActivityHelper;
    private Stack<Activity> mActivityStack;

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public static ActivityHelper getInstance() {
        if (sActivityHelper == null) {
            sActivityHelper = new ActivityHelper();
        }
        return sActivityHelper;
    }

    private ActivityHelper() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.mActivityStack == null) {
            this.mActivityStack = new Stack<>();
        }
        this.mActivityStack.add(activity);
    }

    public void onActivityDestroyed(Activity activity) {
        this.mActivityStack.remove(activity);
    }

    public Activity getPreActivity() {
        int size;
        if (this.mActivityStack != null && (size = this.mActivityStack.size()) >= 2) {
            return (Activity) this.mActivityStack.elementAt(size - 2);
        }
        return null;
    }

    public void finishAllActivity() {
        if (this.mActivityStack != null) {
            Iterator it = this.mActivityStack.iterator();
            while (it.hasNext()) {
                ((Activity) it.next()).finish();
            }
        }
    }

    public void printAllActivity() {
        if (this.mActivityStack != null) {
            for (int i = 0; i < this.mActivityStack.size(); i++) {
                Log.e("TAG", "位置" + i + ": " + this.mActivityStack.get(i));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void postRemoveActivity(Activity activity) {
        if (this.mActivityStack != null) {
            this.mActivityStack.remove(activity);
        }
    }
}
