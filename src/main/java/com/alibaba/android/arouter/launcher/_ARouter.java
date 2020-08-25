package com.alibaba.android.arouter.launcher;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;
import com.alibaba.android.arouter.core.InstrumentationHook;
import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.exception.InitException;
import com.alibaba.android.arouter.exception.NoRouteFoundException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.service.PathReplaceService;
import com.alibaba.android.arouter.facade.service.PretreatmentService;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.thread.DefaultPoolExecutor;
import com.alibaba.android.arouter.utils.DefaultLogger;
import com.alibaba.android.arouter.utils.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

final class _ARouter {
    private static volatile boolean autoInject = false;
    private static volatile boolean debuggable = false;
    private static volatile ThreadPoolExecutor executor = DefaultPoolExecutor.getInstance();
    private static volatile boolean hasInit = false;
    private static volatile _ARouter instance = null;
    private static InterceptorService interceptorService;
    static ILogger logger = new DefaultLogger("ARouter::");
    /* access modifiers changed from: private */
    public static Context mContext;
    private static Handler mHandler;
    private static volatile boolean monitorMode = false;

    private _ARouter() {
    }

    protected static synchronized boolean init(Application application) {
        synchronized (_ARouter.class) {
            mContext = application;
            LogisticsCenter.init(mContext, executor);
            logger.info("ARouter::", "ARouter init success!");
            hasInit = true;
            mHandler = new Handler(Looper.getMainLooper());
        }
        return true;
    }

    static synchronized void destroy() {
        synchronized (_ARouter.class) {
            if (debuggable()) {
                hasInit = false;
                LogisticsCenter.suspend();
                logger.info("ARouter::", "ARouter destroy success!");
            } else {
                logger.error("ARouter::", "Destroy can be used in debug mode only!");
            }
        }
    }

    protected static _ARouter getInstance() {
        if (hasInit) {
            if (instance == null) {
                synchronized (_ARouter.class) {
                    if (instance == null) {
                        instance = new _ARouter();
                    }
                }
            }
            return instance;
        }
        throw new InitException("ARouterCore::Init::Invoke init(context) first!");
    }

    static synchronized void openDebug() {
        synchronized (_ARouter.class) {
            debuggable = true;
            logger.info("ARouter::", "ARouter openDebug");
        }
    }

    static synchronized void openLog() {
        synchronized (_ARouter.class) {
            logger.showLog(true);
            logger.info("ARouter::", "ARouter openLog");
        }
    }

    @Deprecated
    static synchronized void enableAutoInject() {
        synchronized (_ARouter.class) {
            autoInject = true;
        }
    }

    @Deprecated
    static boolean canAutoInject() {
        return autoInject;
    }

    @Deprecated
    static void attachBaseContext() {
        Log.i("ARouter::", "ARouter start attachBaseContext");
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke((Object) null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mInstrumentation");
            declaredField.setAccessible(true);
            declaredField.set(invoke, new InstrumentationHook());
            Log.i("ARouter::", "ARouter hook instrumentation success!");
        } catch (Exception e) {
            Log.e("ARouter::", "ARouter hook instrumentation failed! [" + e.getMessage() + "]");
        }
    }

    static synchronized void printStackTrace() {
        synchronized (_ARouter.class) {
            logger.showStackTrace(true);
            logger.info("ARouter::", "ARouter printStackTrace");
        }
    }

    static synchronized void setExecutor(ThreadPoolExecutor threadPoolExecutor) {
        synchronized (_ARouter.class) {
            executor = threadPoolExecutor;
        }
    }

    static synchronized void monitorMode() {
        synchronized (_ARouter.class) {
            monitorMode = true;
            logger.info("ARouter::", "ARouter monitorMode on");
        }
    }

    static boolean isMonitorMode() {
        return monitorMode;
    }

    static boolean debuggable() {
        return debuggable;
    }

    static void setLogger(ILogger iLogger) {
        if (iLogger != null) {
            logger = iLogger;
        }
    }

    static void inject(Object obj) {
        AutowiredService autowiredService = (AutowiredService) ARouter.getInstance().build("/arouter/service/autowired").navigation();
        if (autowiredService != null) {
            autowiredService.autowire(obj);
        }
    }

    /* access modifiers changed from: protected */
    public Postcard build(String str) {
        if (!TextUtils.isEmpty(str)) {
            PathReplaceService pathReplaceService = (PathReplaceService) ARouter.getInstance().navigation(PathReplaceService.class);
            if (pathReplaceService != null) {
                str = pathReplaceService.forString(str);
            }
            return build(str, extractGroup(str));
        }
        throw new HandlerException("ARouter::Parameter is invalid!");
    }

    /* access modifiers changed from: protected */
    public Postcard build(Uri uri) {
        if (uri == null || TextUtils.isEmpty(uri.toString())) {
            throw new HandlerException("ARouter::Parameter invalid!");
        }
        PathReplaceService pathReplaceService = (PathReplaceService) ARouter.getInstance().navigation(PathReplaceService.class);
        if (pathReplaceService != null) {
            uri = pathReplaceService.forUri(uri);
        }
        return new Postcard(uri.getPath(), extractGroup(uri.getPath()), uri, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public Postcard build(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new HandlerException("ARouter::Parameter is invalid!");
        }
        PathReplaceService pathReplaceService = (PathReplaceService) ARouter.getInstance().navigation(PathReplaceService.class);
        if (pathReplaceService != null) {
            str = pathReplaceService.forString(str);
        }
        return new Postcard(str, str2);
    }

    private String extractGroup(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("/")) {
            throw new HandlerException("ARouter::Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!");
        }
        try {
            String substring = str.substring(1, str.indexOf("/", 1));
            if (!TextUtils.isEmpty(substring)) {
                return substring;
            }
            throw new HandlerException("ARouter::Extract the default group failed! There's nothing between 2 '/'!");
        } catch (Exception e) {
            ILogger iLogger = logger;
            iLogger.warning("ARouter::", "Failed to extract default group! " + e.getMessage());
            return null;
        }
    }

    static void afterInit() {
        interceptorService = (InterceptorService) ARouter.getInstance().build("/arouter/service/interceptor").navigation();
    }

    /* access modifiers changed from: protected */
    public <T> T navigation(Class<? extends T> cls) {
        try {
            Postcard buildProvider = LogisticsCenter.buildProvider(cls.getName());
            if (buildProvider == null) {
                buildProvider = LogisticsCenter.buildProvider(cls.getSimpleName());
            }
            if (buildProvider == null) {
                return null;
            }
            LogisticsCenter.completion(buildProvider);
            return buildProvider.getProvider();
        } catch (NoRouteFoundException e) {
            logger.warning("ARouter::", e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Object navigation(Context context, final Postcard postcard, int i, NavigationCallback navigationCallback) {
        PretreatmentService pretreatmentService = (PretreatmentService) ARouter.getInstance().navigation(PretreatmentService.class);
        if (pretreatmentService != null && !pretreatmentService.onPretreatment(context, postcard)) {
            return null;
        }
        try {
            LogisticsCenter.completion(postcard);
            if (navigationCallback != null) {
                navigationCallback.onFound(postcard);
            }
            if (postcard.isGreenChannel()) {
                return _navigation(context, postcard, i, navigationCallback);
            }
            final Context context2 = context;
            final int i2 = i;
            final NavigationCallback navigationCallback2 = navigationCallback;
            final Postcard postcard2 = postcard;
            interceptorService.doInterceptions(postcard, new InterceptorCallback() {
                public void onContinue(Postcard postcard) {
                    Object unused = _ARouter.this._navigation(context2, postcard, i2, navigationCallback2);
                }

                public void onInterrupt(Throwable th) {
                    if (navigationCallback2 != null) {
                        navigationCallback2.onInterrupt(postcard2);
                    }
                    ILogger iLogger = _ARouter.logger;
                    iLogger.info("ARouter::", "Navigation failed, termination by interceptor : " + th.getMessage());
                }
            });
            return null;
        } catch (NoRouteFoundException e) {
            logger.warning("ARouter::", e.getMessage());
            if (debuggable()) {
                runInMainThread(new Runnable() {
                    public void run() {
                        Context access$000 = _ARouter.mContext;
                        Toast.makeText(access$000, "There's no route matched!\n Path = [" + postcard.getPath() + "]\n Group = [" + postcard.getGroup() + "]", 1).show();
                    }
                });
            }
            if (navigationCallback != null) {
                navigationCallback.onLost(postcard);
            } else {
                DegradeService degradeService = (DegradeService) ARouter.getInstance().navigation(DegradeService.class);
                if (degradeService != null) {
                    degradeService.onLost(context, postcard);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public Object _navigation(Context context, Postcard postcard, int i, NavigationCallback navigationCallback) {
        if (context == null) {
            context = mContext;
        }
        final Context context2 = context;
        switch (postcard.getType()) {
            case ACTIVITY:
                final Intent intent = new Intent(context2, postcard.getDestination());
                intent.putExtras(postcard.getExtras());
                int flags = postcard.getFlags();
                if (-1 != flags) {
                    intent.setFlags(flags);
                } else if (!(context2 instanceof Activity)) {
                    intent.setFlags(268435456);
                }
                String action = postcard.getAction();
                if (!TextUtils.isEmpty(action)) {
                    intent.setAction(action);
                }
                final int i2 = i;
                final Postcard postcard2 = postcard;
                final NavigationCallback navigationCallback2 = navigationCallback;
                runInMainThread(new Runnable() {
                    public void run() {
                        _ARouter.this.startActivity(i2, context2, intent, postcard2, navigationCallback2);
                    }
                });
                return null;
            case PROVIDER:
                return postcard.getProvider();
            case BOARDCAST:
            case CONTENT_PROVIDER:
            case FRAGMENT:
                try {
                    Object newInstance = postcard.getDestination().getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (newInstance instanceof Fragment) {
                        ((Fragment) newInstance).setArguments(postcard.getExtras());
                    } else if (newInstance instanceof android.support.v4.app.Fragment) {
                        ((android.support.v4.app.Fragment) newInstance).setArguments(postcard.getExtras());
                    }
                    return newInstance;
                } catch (Exception e) {
                    ILogger iLogger = logger;
                    iLogger.error("ARouter::", "Fetch fragment instance error, " + TextUtils.formatStackTrace(e.getStackTrace()));
                    break;
                }
        }
        return null;
    }

    private void runInMainThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public void startActivity(int i, Context context, Intent intent, Postcard postcard, NavigationCallback navigationCallback) {
        if (i < 0) {
            ActivityCompat.startActivity(context, intent, postcard.getOptionsBundle());
        } else if (context instanceof Activity) {
            ActivityCompat.startActivityForResult((Activity) context, intent, i, postcard.getOptionsBundle());
        } else {
            logger.warning("ARouter::", "Must use [navigation(activity, ...)] to support [startActivityForResult]");
        }
        if (!(-1 == postcard.getEnterAnim() || -1 == postcard.getExitAnim() || !(context instanceof Activity))) {
            ((Activity) context).overridePendingTransition(postcard.getEnterAnim(), postcard.getExitAnim());
        }
        if (navigationCallback != null) {
            navigationCallback.onArrival(postcard);
        }
    }
}
