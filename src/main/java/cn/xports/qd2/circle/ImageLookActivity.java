package cn.xports.qd2.circle;

import android.os.Bundle;
import android.view.View;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.ThreadUtils;
import com.previewlibrary.GPreviewActivity;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcn/xports/qd2/circle/ImageLookActivity;", "Lcom/previewlibrary/GPreviewActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveImg", "setContentLayout", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ImageLookActivity.kt */
public final class ImageLookActivity extends GPreviewActivity {
    private HashMap _$_findViewCache;

    static {
        StubApp.interface11(3812);
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [cn.xports.qd2.circle.ImageLookActivity, android.support.v4.app.FragmentActivity] */
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

    /* access modifiers changed from: protected */
    public native void onCreate(@Nullable Bundle bundle);

    /* access modifiers changed from: private */
    public final void saveImg() {
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("imagePaths");
        Intrinsics.checkExpressionValueIsNotNull(parcelableArrayListExtra, "intent.getParcelableArrayListExtra(\"imagePaths\")");
        ThreadUtils.getIoPool().execute(new ImageLookActivity$saveImg$1(this, parcelableArrayListExtra));
    }

    public int setContentLayout() {
        return R.layout.activity_image_look;
    }
}
