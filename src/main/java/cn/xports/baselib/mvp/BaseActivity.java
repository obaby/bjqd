package cn.xports.baselib.mvp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.mvp.IPresenter;
import com.stub.StubApp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pub.devrel.easypermissions.EasyPermissions;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u001f\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u001f\"\u00020\f¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u0019H\u0016J\b\u0010\"\u001a\u00020\u0019H\u0016J\b\u0010#\u001a\u00020\u0019H&J\b\u0010$\u001a\u00020\u0019H\u0016J\u0012\u0010%\u001a\u00020\u00192\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\b\u0010(\u001a\u00020\u0019H\u0014J\u001e\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0+H\u0016J\u001e\u0010,\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0+H\u0016J-\u0010-\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\b2\u000e\u0010.\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u001f2\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0002\u00101J\u001f\u00102\u001a\u00020\u00192\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u001f\"\u00020\f¢\u0006\u0002\u00103J\b\u00104\u001a\u00020\u0019H\u0016J\u0010\u00105\u001a\u00020\u00192\u0006\u00106\u001a\u00020\fH\u0016J\u0012\u00107\u001a\u00020\u00192\b\u00106\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u00108\u001a\u00020\u00192\u0006\u00106\u001a\u00020\fH\u0016R\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u00069"}, d2 = {"Lcn/xports/baselib/mvp/BaseActivity;", "P", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/v7/app/AppCompatActivity;", "Lcn/xports/baselib/mvp/IView;", "Lpub/devrel/easypermissions/EasyPermissions$PermissionCallbacks;", "()V", "PERMISSION_CODE", "", "getPERMISSION_CODE", "()I", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "presenter", "getPresenter", "()Lcn/xports/baselib/mvp/IPresenter;", "setPresenter", "(Lcn/xports/baselib/mvp/IPresenter;)V", "Lcn/xports/baselib/mvp/IPresenter;", "finish", "", "getStringExtra", "key", "hasPermission", "", "perms", "", "([Ljava/lang/String;)Z", "hideLoading", "hideNoData", "initData", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPermissionsDenied", "requestCode", "", "onPermissionsGranted", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestPermission", "([Ljava/lang/String;)V", "showLoading", "showMsg", "msg", "showNoData", "showTipDialog", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BaseActivity.kt */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView, EasyPermissions.PermissionCallbacks {
    private final int PERMISSION_CODE = 102;
    private String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    @Nullable
    private P presenter;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public void hideLoading() {
    }

    public void hideNoData() {
    }

    public abstract void initData();

    public void onPermissionsDenied(int i, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "perms");
    }

    public void onPermissionsGranted(int i, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "perms");
    }

    public void showLoading() {
    }

    public void showNoData(@Nullable String str) {
    }

    public void showTipDialog(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "msg");
    }

    /* access modifiers changed from: protected */
    public final int getPERMISSION_CODE() {
        return this.PERMISSION_CODE;
    }

    @Nullable
    public final P getPresenter() {
        return this.presenter;
    }

    public final void setPresenter(@Nullable P p) {
        this.presenter = p;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        this.TAG = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initData();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        P p = this.presenter;
        if (p != null) {
            p.onDestroy();
        }
        this.presenter = (IPresenter) null;
    }

    public void finish() {
        RxDisposableManager.getInstance().clear(this.TAG);
        P p = this.presenter;
        if (p != null) {
            p.onDestroy();
        }
        this.presenter = (IPresenter) null;
        super.finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void showMsg(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "msg");
        Toast.makeText(this, str, 0).show();
    }

    public void onRequestPermissionsResult(int i, @NotNull String[] strArr, @NotNull int[] iArr) {
        StubApp.interface22(i, strArr, iArr);
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, new Object[]{this});
    }

    public final void requestPermission(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "perms");
        EasyPermissions.requestPermissions(this, "请求所需权限", this.PERMISSION_CODE, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final boolean hasPermission(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "perms");
        return EasyPermissions.hasPermissions(this, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @NotNull
    public final String getStringExtra(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        String stringExtra = getIntent().getStringExtra(str);
        if (stringExtra == null) {
            Intent intent = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
            Uri data = intent.getData();
            stringExtra = data != null ? data.getQueryParameter(str) : null;
        }
        return stringExtra != null ? stringExtra : "";
    }
}
