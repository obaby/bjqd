package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

public class ShortcutManagerCompat {
    @VisibleForTesting
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    @VisibleForTesting
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    private ShortcutManagerCompat() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRequestPinShortcutSupported(@android.support.annotation.NonNull android.content.Context r3) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L_0x0013
            java.lang.Class<android.content.pm.ShortcutManager> r0 = android.content.pm.ShortcutManager.class
            java.lang.Object r3 = r3.getSystemService(r0)
            android.content.pm.ShortcutManager r3 = (android.content.pm.ShortcutManager) r3
            boolean r3 = r3.isRequestPinShortcutSupported()
            return r3
        L_0x0013:
            java.lang.String r0 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            int r0 = android.support.v4.content.ContextCompat.checkSelfPermission(r3, r0)
            r1 = 0
            if (r0 == 0) goto L_0x001d
            return r1
        L_0x001d:
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "com.android.launcher.action.INSTALL_SHORTCUT"
            r0.<init>(r2)
            java.util.List r3 = r3.queryBroadcastReceivers(r0, r1)
            java.util.Iterator r3 = r3.iterator()
        L_0x0030:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0050
            java.lang.Object r0 = r3.next()
            android.content.pm.ResolveInfo r0 = (android.content.pm.ResolveInfo) r0
            android.content.pm.ActivityInfo r0 = r0.activityInfo
            java.lang.String r0 = r0.permission
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x004e
            java.lang.String r2 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0030
        L_0x004e:
            r3 = 1
            return r3
        L_0x0050:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.pm.ShortcutManagerCompat.isRequestPinShortcutSupported(android.content.Context):boolean");
    }

    public static boolean requestPinShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat, @Nullable final IntentSender intentSender) {
        if (Build.VERSION.SDK_INT >= 26) {
            return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcutInfoCompat.toShortcutInfo(), intentSender);
        }
        if (!isRequestPinShortcutSupported(context)) {
            return false;
        }
        Intent addToIntent = shortcutInfoCompat.addToIntent(new Intent(ACTION_INSTALL_SHORTCUT));
        if (intentSender == null) {
            context.sendBroadcast(addToIntent);
            return true;
        }
        context.sendOrderedBroadcast(addToIntent, (String) null, new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                try {
                    intentSender.sendIntent(context, 0, (Intent) null, (IntentSender.OnFinished) null, (Handler) null);
                } catch (IntentSender.SendIntentException unused) {
                }
            }
        }, (Handler) null, -1, (String) null, (Bundle) null);
        return true;
    }

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        Intent createShortcutResultIntent = Build.VERSION.SDK_INT >= 26 ? ((ShortcutManager) context.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcutInfoCompat.toShortcutInfo()) : null;
        if (createShortcutResultIntent == null) {
            createShortcutResultIntent = new Intent();
        }
        return shortcutInfoCompat.addToIntent(createShortcutResultIntent);
    }
}
