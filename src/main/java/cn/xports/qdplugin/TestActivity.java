package cn.xports.qdplugin;

import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.qdplugin.VenueMainContract;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0014¨\u0006\f"}, d2 = {"Lcn/xports/qdplugin/TestActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/qdplugin/VenueMainPresenter;", "Lcn/xports/qdplugin/VenueMainContract$View;", "()V", "getChildTitle", "", "getLayoutId", "", "initData", "", "initView", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TestActivity.kt */
public final class TestActivity extends BaseBussActivity<VenueMainPresenter> implements VenueMainContract.View {
    private HashMap _$_findViewCache;

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

    /* access modifiers changed from: protected */
    @NotNull
    public String getChildTitle() {
        return "青岛国信体育中心";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_venue_main;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setPresenter(new VenueMainPresenter(new VenueMainModel(), this));
        VenueMainPresenter venueMainPresenter = (VenueMainPresenter) getPresenter();
        if (venueMainPresenter != null) {
            venueMainPresenter.getVenueList();
        }
    }
}
