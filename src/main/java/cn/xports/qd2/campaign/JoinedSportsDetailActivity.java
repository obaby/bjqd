package cn.xports.qd2.campaign;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.MemberRoundAdapter;
import cn.xports.qd2.entity.ItemEnrollmentDetailResult;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.util.ApiUtil;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0007H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\fH\u0014J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcn/xports/qd2/campaign/JoinedSportsDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "adapter", "Lcn/xports/qd2/adapter/MemberRoundAdapter;", "campId", "", "campScore", "Lcn/xports/qd2/entity/ItemEnrollmentDetailResult$CampScore;", "enrollId", "exBgColor", "", "exState", "members", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/MemberInfo;", "Lkotlin/collections/ArrayList;", "readOnly", "", "getChildTitle", "getDetail", "", "getLayoutId", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: JoinedSportsDetailActivity.kt */
public final class JoinedSportsDetailActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public MemberRoundAdapter adapter;
    /* access modifiers changed from: private */
    public String campId = "";
    /* access modifiers changed from: private */
    public ItemEnrollmentDetailResult.CampScore campScore;
    private String enrollId = "";
    /* access modifiers changed from: private */
    public int exBgColor;
    /* access modifiers changed from: private */
    public String exState = "";
    /* access modifiers changed from: private */
    public final ArrayList<MemberInfo> members = new ArrayList<>();
    /* access modifiers changed from: private */
    public boolean readOnly;

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
        return "报名详情";
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_joined_sports_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMemberList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.adapter = new MemberRoundAdapter(this.members);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvMemberList");
        recyclerView2.setAdapter(this.adapter);
        ((RecyclerView) _$_findCachedViewById(R.id.rvMemberList)).setOnTouchListener(new JoinedSportsDetailActivity$initView$1(this));
        getDetail();
        ((RelativeLayout) _$_findCachedViewById(R.id.rlScoreInfo)).setOnClickListener(new JoinedSportsDetailActivity$initView$2(this));
        ((TextView) _$_findCachedViewById(R.id.tvNoScoreTip)).setOnClickListener(JoinedSportsDetailActivity$initView$3.INSTANCE);
    }

    public void initData() {
        this.enrollId = getStringExtra(K.enrollId);
        this.campId = getStringExtra(K.campId);
        this.readOnly = getIntent().getBooleanExtra("readTag", false);
        this.exState = getStringExtra("ex_state");
        this.exBgColor = getIntent().getIntExtra("bg_state_color", 0);
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(String.class).subscribe(new JoinedSportsDetailActivity$initData$1(this)));
    }

    /* access modifiers changed from: private */
    public final void getDetail() {
        IView iView = this;
        ApiUtil.getApi2().getItemDetail(this.enrollId).compose(RxUtil.applyErrorsWithIO()).subscribe(new JoinedSportsDetailActivity$getDetail$1(this, iView));
        ApiUtil.getApi2().getTeamInfo(this.enrollId).compose(RxUtil.applyErrorsWithIO()).subscribe(new JoinedSportsDetailActivity$getDetail$2(this, iView));
    }
}
