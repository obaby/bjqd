package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    ResourcesFlusher() {
    }

    static boolean flush(@NonNull Resources resources) {
        if (Build.VERSION.SDK_INT >= 24) {
            return flushNougats(resources);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return flushMarshmallows(resources);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return flushLollipops(resources);
        }
        return false;
    }

    @RequiresApi(21)
    private static boolean flushLollipops(@NonNull Resources resources) {
        Map map;
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField == null) {
            return false;
        }
        try {
            map = (Map) sDrawableCacheField.get(resources);
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", e2);
            map = null;
        }
        if (map == null) {
            return false;
        }
        map.clear();
        return true;
    }

    @RequiresApi(23)
    private static boolean flushMarshmallows(@NonNull Resources resources) {
        Object obj;
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField != null) {
            try {
                obj = sDrawableCacheField.get(resources);
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", e2);
            }
            if (obj == null && obj != null && flushThemedResourcesCache(obj)) {
                return true;
            }
            return false;
        }
        obj = null;
        if (obj == null) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0076 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    @android.support.annotation.RequiresApi(24)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean flushNougats(@android.support.annotation.NonNull android.content.res.Resources r6) {
        /*
            boolean r0 = sResourcesImplFieldFetched
            r1 = 1
            if (r0 != 0) goto L_0x001f
            java.lang.Class<android.content.res.Resources> r0 = android.content.res.Resources.class
            java.lang.String r2 = "mResourcesImpl"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0015 }
            sResourcesImplField = r0     // Catch:{ NoSuchFieldException -> 0x0015 }
            java.lang.reflect.Field r0 = sResourcesImplField     // Catch:{ NoSuchFieldException -> 0x0015 }
            r0.setAccessible(r1)     // Catch:{ NoSuchFieldException -> 0x0015 }
            goto L_0x001d
        L_0x0015:
            r0 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve Resources#mResourcesImpl field"
            android.util.Log.e(r2, r3, r0)
        L_0x001d:
            sResourcesImplFieldFetched = r1
        L_0x001f:
            java.lang.reflect.Field r0 = sResourcesImplField
            r2 = 0
            if (r0 != 0) goto L_0x0025
            return r2
        L_0x0025:
            r0 = 0
            java.lang.reflect.Field r3 = sResourcesImplField     // Catch:{ IllegalAccessException -> 0x002d }
            java.lang.Object r6 = r3.get(r6)     // Catch:{ IllegalAccessException -> 0x002d }
            goto L_0x0036
        L_0x002d:
            r6 = move-exception
            java.lang.String r3 = "ResourcesFlusher"
            java.lang.String r4 = "Could not retrieve value from Resources#mResourcesImpl"
            android.util.Log.e(r3, r4, r6)
            r6 = r0
        L_0x0036:
            if (r6 != 0) goto L_0x0039
            return r2
        L_0x0039:
            boolean r3 = sDrawableCacheFieldFetched
            if (r3 != 0) goto L_0x0059
            java.lang.Class r3 = r6.getClass()     // Catch:{ NoSuchFieldException -> 0x004f }
            java.lang.String r4 = "mDrawableCache"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch:{ NoSuchFieldException -> 0x004f }
            sDrawableCacheField = r3     // Catch:{ NoSuchFieldException -> 0x004f }
            java.lang.reflect.Field r3 = sDrawableCacheField     // Catch:{ NoSuchFieldException -> 0x004f }
            r3.setAccessible(r1)     // Catch:{ NoSuchFieldException -> 0x004f }
            goto L_0x0057
        L_0x004f:
            r3 = move-exception
            java.lang.String r4 = "ResourcesFlusher"
            java.lang.String r5 = "Could not retrieve ResourcesImpl#mDrawableCache field"
            android.util.Log.e(r4, r5, r3)
        L_0x0057:
            sDrawableCacheFieldFetched = r1
        L_0x0059:
            java.lang.reflect.Field r3 = sDrawableCacheField
            if (r3 == 0) goto L_0x006c
            java.lang.reflect.Field r3 = sDrawableCacheField     // Catch:{ IllegalAccessException -> 0x0064 }
            java.lang.Object r6 = r3.get(r6)     // Catch:{ IllegalAccessException -> 0x0064 }
            goto L_0x006d
        L_0x0064:
            r6 = move-exception
            java.lang.String r3 = "ResourcesFlusher"
            java.lang.String r4 = "Could not retrieve value from ResourcesImpl#mDrawableCache"
            android.util.Log.e(r3, r4, r6)
        L_0x006c:
            r6 = r0
        L_0x006d:
            if (r6 == 0) goto L_0x0076
            boolean r6 = flushThemedResourcesCache(r6)
            if (r6 == 0) goto L_0x0076
            goto L_0x0077
        L_0x0076:
            r1 = 0
        L_0x0077:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.ResourcesFlusher.flushNougats(android.content.res.Resources):boolean");
    }

    @RequiresApi(16)
    private static boolean flushThemedResourcesCache(@NonNull Object obj) {
        LongSparseArray longSparseArray;
        if (!sThemedResourceCacheClazzFetched) {
            try {
                sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "Could not find ThemedResourceCache class", e);
            }
            sThemedResourceCacheClazzFetched = true;
        }
        if (sThemedResourceCacheClazz == null) {
            return false;
        }
        if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
            try {
                sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
                sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e(TAG, "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e2);
            }
            sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
        }
        if (sThemedResourceCache_mUnthemedEntriesField == null) {
            return false;
        }
        try {
            longSparseArray = (LongSparseArray) sThemedResourceCache_mUnthemedEntriesField.get(obj);
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e3);
            longSparseArray = null;
        }
        if (longSparseArray == null) {
            return false;
        }
        longSparseArray.clear();
        return true;
    }
}
