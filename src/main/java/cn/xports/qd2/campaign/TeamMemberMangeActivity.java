package cn.xports.qd2.campaign;

import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0014¨\u0006\u000b"}, d2 = {"Lcn/xports/qd2/campaign/TeamMemberMangeActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "getChildTitle", "", "getLayoutId", "", "initData", "", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TeamMemberMangeActivity.kt */
public final class TeamMemberMangeActivity extends BaseBussActivity<IPresenter> {
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
        return "队员管理";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public void initView() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_team_member_mange;
    }
}
