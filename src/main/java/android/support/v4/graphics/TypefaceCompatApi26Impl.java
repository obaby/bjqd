package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName(FONT_FAMILY_CLASS);
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method4 = cls.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class});
            method3 = cls.getMethod(ADD_FONT_FROM_BUFFER_METHOD, new Class[]{ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE});
            method2 = cls.getMethod(FREEZE_METHOD, new Class[0]);
            method = cls.getMethod(ABORT_CREATION_METHOD, new Class[0]);
            method5 = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{Array.newInstance(cls, 1).getClass(), Integer.TYPE, Integer.TYPE});
            method5.setAccessible(true);
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            cls = null;
            method5 = null;
            method4 = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        sFontFamilyCtor = constructor;
        sFontFamily = cls;
        sAddFontFromAssetManager = method4;
        sAddFontFromBuffer = method3;
        sFreeze = method2;
        sAbortCreation = method;
        sCreateFromFamiliesWithDefault = method5;
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
    }

    private static Object newFamily() {
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromAssetManager(Context context, Object obj, String str, int i, int i2, int i3) {
        try {
            return ((Boolean) sAddFontFromAssetManager.invoke(obj, new Object[]{context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), null})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromBuffer(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            return ((Boolean) sAddFontFromBuffer.invoke(obj, new Object[]{byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Integer.valueOf(i3)})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object obj) {
        try {
            Object newInstance = Array.newInstance(sFontFamily, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke((Object) null, new Object[]{newInstance, -1, -1});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean freeze(Object obj) {
        try {
            return ((Boolean) sFreeze.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void abortCreation(Object obj) {
        try {
            sAbortCreation.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, fontFamilyFilesResourceEntry, resources, i);
        }
        Object newFamily = newFamily();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.getEntries()) {
            if (!addFontFromAssetManager(context, newFamily, fontFileResourceEntry.getFileName(), 0, fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic() ? 1 : 0)) {
                abortCreation(newFamily);
                return null;
            }
        }
        if (!freeze(newFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(newFamily);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        r8 = r12;
        r12 = r11;
        r11 = r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r10, @android.support.annotation.Nullable android.os.CancellationSignal r11, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r12, int r13) {
        /*
            r9 = this;
            int r0 = r12.length
            r1 = 1
            r2 = 0
            if (r0 >= r1) goto L_0x0006
            return r2
        L_0x0006:
            boolean r0 = isFontFamilyPrivateAPIAvailable()
            if (r0 != 0) goto L_0x0064
            android.support.v4.provider.FontsContractCompat$FontInfo r12 = r9.findBestInfo(r12, r13)
            android.content.ContentResolver r10 = r10.getContentResolver()
            android.net.Uri r13 = r12.getUri()     // Catch:{ IOException -> 0x0063 }
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r10 = r10.openFileDescriptor(r13, r0, r11)     // Catch:{ IOException -> 0x0063 }
            if (r10 != 0) goto L_0x0026
            if (r10 == 0) goto L_0x0025
            r10.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0025:
            return r2
        L_0x0026:
            android.graphics.Typeface$Builder r11 = new android.graphics.Typeface$Builder     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            java.io.FileDescriptor r13 = r10.getFileDescriptor()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            r11.<init>(r13)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            int r13 = r12.getWeight()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            android.graphics.Typeface$Builder r11 = r11.setWeight(r13)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            boolean r12 = r12.isItalic()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            android.graphics.Typeface$Builder r11 = r11.setItalic(r12)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            android.graphics.Typeface r11 = r11.build()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            if (r10 == 0) goto L_0x0048
            r10.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0048:
            return r11
        L_0x0049:
            r11 = move-exception
            r12 = r2
            goto L_0x0052
        L_0x004c:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x004e }
        L_0x004e:
            r12 = move-exception
            r8 = r12
            r12 = r11
            r11 = r8
        L_0x0052:
            if (r10 == 0) goto L_0x0062
            if (r12 == 0) goto L_0x005f
            r10.close()     // Catch:{ Throwable -> 0x005a }
            goto L_0x0062
        L_0x005a:
            r10 = move-exception
            r12.addSuppressed(r10)     // Catch:{ IOException -> 0x0063 }
            goto L_0x0062
        L_0x005f:
            r10.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0062:
            throw r11     // Catch:{ IOException -> 0x0063 }
        L_0x0063:
            return r2
        L_0x0064:
            java.util.Map r10 = android.support.v4.provider.FontsContractCompat.prepareFontData(r10, r12, r11)
            java.lang.Object r11 = newFamily()
            int r0 = r12.length
            r3 = 0
            r4 = 0
        L_0x006f:
            if (r3 >= r0) goto L_0x009a
            r5 = r12[r3]
            android.net.Uri r6 = r5.getUri()
            java.lang.Object r6 = r10.get(r6)
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            if (r6 != 0) goto L_0x0080
            goto L_0x0097
        L_0x0080:
            int r4 = r5.getTtcIndex()
            int r7 = r5.getWeight()
            boolean r5 = r5.isItalic()
            boolean r4 = addFontFromBuffer(r11, r6, r4, r7, r5)
            if (r4 != 0) goto L_0x0096
            abortCreation(r11)
            return r2
        L_0x0096:
            r4 = 1
        L_0x0097:
            int r3 = r3 + 1
            goto L_0x006f
        L_0x009a:
            if (r4 != 0) goto L_0x00a0
            abortCreation(r11)
            return r2
        L_0x00a0:
            boolean r10 = freeze(r11)
            if (r10 != 0) goto L_0x00a7
            return r2
        L_0x00a7:
            android.graphics.Typeface r10 = createFromFamiliesWithDefault(r11)
            android.graphics.Typeface r10 = android.graphics.Typeface.create(r10, r13)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, i, str, i2);
        }
        Object newFamily = newFamily();
        if (!addFontFromAssetManager(context, newFamily, str, 0, -1, -1)) {
            abortCreation(newFamily);
            return null;
        } else if (!freeze(newFamily)) {
            return null;
        } else {
            return createFromFamiliesWithDefault(newFamily);
        }
    }
}
