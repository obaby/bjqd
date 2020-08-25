package cn.xports.baselib.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0006"}, d2 = {"getVersionCode", "", "context", "Landroid/content/Context;", "getVersionName", "", "baselib_release"}, k = 2, mv = {1, 1, 15})
@JvmName(name = "AppUtil")
/* compiled from: AppUtil.kt */
public final class AppUtil {
    @NotNull
    public static final String getVersionName(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        Intrinsics.checkExpressionValueIsNotNull(str, "context.packageManager.g…ackageName,0).versionName");
        return str;
    }

    public static final int getVersionCode(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    }
}
