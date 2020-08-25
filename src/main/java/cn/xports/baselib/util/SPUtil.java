package cn.xports.baselib.util;

import android.content.SharedPreferences;
import cn.xports.baselib.App;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\u0000J\u0006\u0010\t\u001a\u00020\u0000J\u0006\u0010\n\u001a\u00020\u0000J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0016\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0001R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcn/xports/baselib/util/SPUtil;", "", "()V", "editor", "Landroid/content/SharedPreferences$Editor;", "kotlin.jvm.PlatformType", "mSharedPreferences", "Landroid/content/SharedPreferences;", "apply", "clearAll", "commit", "getBooleanValue", "", "key", "", "getFloatValue", "", "getIntValue", "", "getLongValue", "", "getStringValue", "save", "value", "Companion", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: SPUtil.kt */
public class SPUtil {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final Lazy INSTANCE$delegate = LazyKt.lazy(SPUtil$Companion$INSTANCE$2.INSTANCE);
    private final SharedPreferences.Editor editor;
    private final SharedPreferences mSharedPreferences;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcn/xports/baselib/util/SPUtil$Companion;", "", "()V", "INSTANCE", "Lcn/xports/baselib/util/SPUtil;", "getINSTANCE", "()Lcn/xports/baselib/util/SPUtil;", "INSTANCE$delegate", "Lkotlin/Lazy;", "baselib_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: SPUtil.kt */
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {(KProperty) Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "INSTANCE", "getINSTANCE()Lcn/xports/baselib/util/SPUtil;"))};

        @NotNull
        public final SPUtil getINSTANCE() {
            Lazy access$getINSTANCE$cp = SPUtil.INSTANCE$delegate;
            Companion companion = SPUtil.Companion;
            KProperty kProperty = $$delegatedProperties[0];
            return (SPUtil) access$getINSTANCE$cp.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private SPUtil() {
        this.mSharedPreferences = App.INSTANCE.getSharedPreferences("userInfo", 0);
        this.editor = this.mSharedPreferences.edit();
    }

    public /* synthetic */ SPUtil(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public final SPUtil save(@NotNull String str, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Intrinsics.checkParameterIsNotNull(obj, "value");
        if (obj instanceof Boolean) {
            this.editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            this.editor.putFloat(str, ((Number) obj).floatValue());
        } else if (obj instanceof Long) {
            this.editor.putLong(str, ((Number) obj).longValue());
        } else if (obj instanceof Integer) {
            this.editor.putInt(str, ((Number) obj).intValue());
        } else if (obj instanceof String) {
            this.editor.putString(str, (String) obj);
        }
        return this;
    }

    @NotNull
    public final SPUtil apply() {
        this.editor.apply();
        return this;
    }

    @NotNull
    public final SPUtil clearAll() {
        this.editor.clear().commit();
        return this;
    }

    @NotNull
    public final SPUtil commit() {
        this.editor.commit();
        return this;
    }

    @Nullable
    public final String getStringValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this.mSharedPreferences.getString(str, "");
    }

    public final int getIntValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this.mSharedPreferences.getInt(str, 0);
    }

    public final boolean getBooleanValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this.mSharedPreferences.getBoolean(str, false);
    }

    public final float getFloatValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this.mSharedPreferences.getFloat(str, 0.0f);
    }

    public final long getLongValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this.mSharedPreferences.getLong(str, 0);
    }
}
