package cn.xports.qd2.campaign;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.ScoreRankAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.widget.EmptyRecyclerView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u0005H\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcn/xports/qd2/campaign/TeamRankActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "campId", "", "campItemId", "rankAdapter", "Lcn/xports/qd2/adapter/ScoreRankAdapter;", "rankList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "getChildTitle", "getLayoutId", "", "getScoreRank", "", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TeamRankActivity.kt */
public final class TeamRankActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private String campId = "";
    private String campItemId = "";
    /* access modifiers changed from: private */
    public final ScoreRankAdapter rankAdapter = new ScoreRankAdapter(this.rankList);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> rankList = new ArrayList<>();

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
        return "";
    }

    public void initData() {
        this.campId = getStringExtra(K.campId);
        this.campItemId = getStringExtra("campItemId");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_team_rank;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        underStatusBar(true, true);
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvRankList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvRankList");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvRankList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvRankList");
        emptyRecyclerView2.setAdapter(this.rankAdapter);
        getScoreRank();
    }

    private final void getScoreRank() {
        Observable<ResponseBody> scoreRank = ApiUtil.getApi2().getScoreRank(this.campId, this.campItemId, 999, 1);
        Intrinsics.checkExpressionValueIsNotNull(scoreRank, "ApiUtil.getApi2().getSco…campId, campItemId,999,1)");
        RxUtil.subscribeDataMapIO$default(scoreRank, this, new TeamRankActivity$getScoreRank$1(this), (Function1) null, false, 12, (Object) null);
    }
}
