package cn.xports.qd2.campaign;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.GlobalStaticConfig;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.RxBus;
import cn.xports.baselib.widget.FakeBoldText;
import cn.xports.export.EventHandler;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.TeamInfoAdapter;
import cn.xports.qd2.dialog.ShareDialog;
import cn.xports.qd2.entity.CampItem;
import cn.xports.qd2.entity.CampTeam;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.entity.TeamInfoResult;
import cn.xports.qd2.shareCampaign.PopuSelectShare;
import cn.xports.qd2.util.ApiUtil;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.arvin.socialhelper.entities.ShareEntity;
import net.arvin.socialhelper.entities.WXShareEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0014J\b\u0010\u0018\u001a\u00020\tH\u0014J\b\u0010\u0019\u001a\u00020\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0014J\"\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0016H\u0014J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0005H\u0002J\u0010\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\tH\u0002J\b\u0010%\u001a\u00020\u0016H\u0002J\b\u0010&\u001a\u00020\u0016H\u0002J\b\u0010'\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000fj\b\u0012\u0004\u0012\u00020\u0007`\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcn/xports/qd2/campaign/TeamInfoActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "campId", "", "curMember", "Lcn/xports/qd2/entity/MemberInfo;", "editPos", "", "editable", "", "isShare", "maxCount", "memberList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "opType", "teamEditable", "teamInfo", "Lcn/xports/qd2/entity/TeamInfoResult;", "addNewMember", "", "getChildTitle", "getLayoutId", "initData", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onResume", "removePlayer", "playerId", "showInvite", "index", "showShareDialog", "switchAddTip", "updateTeamNum", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TeamInfoActivity.kt */
public final class TeamInfoActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String campId = "";
    /* access modifiers changed from: private */
    public MemberInfo curMember;
    /* access modifiers changed from: private */
    public int editPos = -1;
    /* access modifiers changed from: private */
    public boolean editable;
    private boolean isShare;
    /* access modifiers changed from: private */
    public int maxCount;
    /* access modifiers changed from: private */
    public ArrayList<MemberInfo> memberList = new ArrayList<>();
    /* access modifiers changed from: private */
    public int opType;
    /* access modifiers changed from: private */
    public boolean teamEditable;
    private TeamInfoResult teamInfo;

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
        return "团队成员";
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_team_info;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        TeamInfoResult teamInfoResult = this.teamInfo;
        if (teamInfoResult != null) {
            TextView textView = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(textView, "mTvTitle");
            CampTeam publicCampTeam = teamInfoResult.getPublicCampTeam();
            textView.setText(publicCampTeam != null ? publicCampTeam.getName() : null);
            CampItem publicCampItem = teamInfoResult.getPublicCampItem();
            if (publicCampItem != null) {
                String maxPersonNum = publicCampItem.getMaxPersonNum();
                Intrinsics.checkExpressionValueIsNotNull(maxPersonNum, "it.maxPersonNum");
                this.maxCount = Integer.parseInt(maxPersonNum);
                FakeBoldText fakeBoldText = (FakeBoldText) _$_findCachedViewById(R.id.tv_nums);
                Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tv_nums");
                fakeBoldText.setText("(" + teamInfoResult.getPlayerNum() + "/" + publicCampItem.getMaxPersonNum() + ")");
                if (!this.teamEditable || teamInfoResult.getPlayerNum() >= this.maxCount) {
                    RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.rlAddNew);
                    Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlAddNew");
                    relativeLayout.setVisibility(8);
                } else {
                    RelativeLayout relativeLayout2 = (RelativeLayout) _$_findCachedViewById(R.id.rlAddNew);
                    Intrinsics.checkExpressionValueIsNotNull(relativeLayout2, "rlAddNew");
                    relativeLayout2.setVisibility(0);
                }
            }
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMemberList");
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            TeamInfoAdapter teamInfoAdapter = new TeamInfoAdapter(this.memberList);
            teamInfoAdapter.setEditable(this.teamEditable);
            RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvMemberList");
            recyclerView2.setAdapter(teamInfoAdapter);
            teamInfoAdapter.setListener(new TeamInfoActivity$initView$$inlined$apply$lambda$1(this));
            ((RelativeLayout) _$_findCachedViewById(R.id.rlAddNew)).setOnClickListener(new TeamInfoActivity$initView$$inlined$apply$lambda$2(this));
            SPUtils instance = SPUtils.getInstance();
            CampTeam publicCampTeam2 = teamInfoResult.getPublicCampTeam();
            Intrinsics.checkExpressionValueIsNotNull(publicCampTeam2, "publicCampTeam");
            instance.put("teamId", publicCampTeam2.getId());
            SPUtils instance2 = SPUtils.getInstance();
            CampTeam publicCampTeam3 = teamInfoResult.getPublicCampTeam();
            Intrinsics.checkExpressionValueIsNotNull(publicCampTeam3, "publicCampTeam");
            instance2.put(K.enrollId, publicCampTeam3.getEnrollId());
            SPUtils instance3 = SPUtils.getInstance();
            CampItem publicCampItem2 = teamInfoResult.getPublicCampItem();
            Intrinsics.checkExpressionValueIsNotNull(publicCampItem2, "publicCampItem");
            instance3.put("campItemId", String.valueOf(publicCampItem2.getId()));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvJoinTip);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvJoinTip");
        textView2.setText("暂无成员，请添加！");
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvJoin);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvJoin");
        textView3.setText("添加成员");
        ((TextView) _$_findCachedViewById(R.id.tvJoin)).setOnClickListener(new TeamInfoActivity$initView$2(this));
        switchAddTip();
    }

    /* access modifiers changed from: private */
    public final void addNewMember() {
        PopuSelectShare popuSelectShare = new PopuSelectShare();
        popuSelectShare.init(this, this.topSpace);
        popuSelectShare.setOnSelectLisenter(new TeamInfoActivity$addNewMember$1(this));
    }

    /* access modifiers changed from: private */
    public final void showShareDialog() {
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show();
        shareDialog.setExchangeClickListener(new TeamInfoActivity$showShareDialog$1(this));
    }

    /* access modifiers changed from: private */
    public final void showInvite(int i) {
        String str = Intrinsics.areEqual("product", "product") ? "gh_1a6a3bf49e5d" : "gh_a7f1816ea62c";
        StringBuilder sb = new StringBuilder();
        GlobalStaticConfig instance = GlobalStaticConfig.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "GlobalStaticConfig.getInstance()");
        sb.append(instance.getBaseUrl());
        sb.append("aisports-weixin/");
        sb.append("activity/shareCampaign?wechatOriginId=");
        sb.append(str);
        sb.append("&enrollId=");
        sb.append(SPUtils.getInstance().getString(K.enrollId));
        ShareEntity createWebPageInfo = WXShareEntity.createWebPageInfo(i == 1, sb.toString(), R.drawable.ic_share_icon, "您的朋友正在邀请您一起参加比赛，速度围观~", "火热报名中，点击立即加入队伍…");
        this.isShare = true;
        EventHandler instance2 = EventHandler.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance2, "EventHandler.getInstance()");
        instance2.getSocialHelper().shareWX(this, createWebPageInfo, new TeamInfoActivity$showInvite$1());
    }

    public void initData() {
        this.teamInfo = (TeamInfoResult) GsonUtils.fromJson(getStringExtra("teamInfo"), TeamInfoResult.class);
        TeamInfoResult teamInfoResult = this.teamInfo;
        if (teamInfoResult != null) {
            List<MemberInfo> normalPlayerList = teamInfoResult.getNormalPlayerList();
            if (normalPlayerList != null) {
                this.memberList.addAll(normalPlayerList);
            }
            List<MemberInfo> confirmPlayerList = teamInfoResult.getConfirmPlayerList();
            if (confirmPlayerList != null) {
                this.memberList.addAll(confirmPlayerList);
            }
            CampItem publicCampItem = teamInfoResult.getPublicCampItem();
            if (publicCampItem != null) {
                this.editable = Intrinsics.areEqual("2", publicCampItem.getEnrollType()) && TimeUtils.getTimeSpanByNow(publicCampItem.getEnrollEndDate(), 60000) > 0;
            }
        }
        this.teamEditable = !getIntent().getBooleanExtra("readOnly", true);
        this.campId = getStringExtra(K.campId);
    }

    /* access modifiers changed from: private */
    public final void removePlayer(String str) {
        ApiUtil.getApi2().removePlayer(str).compose(RxUtil.applyErrorsWithIO()).subscribe(new TeamInfoActivity$removePlayer$1(this, this));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i2 == -1 && i == 1010 && intent != null) {
            RxBus.get().post("update_success");
            ArrayList arrayList = (ArrayList) GsonUtils.fromJson(intent.getStringExtra("members"), new TeamInfoActivity$onActivityResult$1$members$1().getType());
            NestedScrollView nestedScrollView = (NestedScrollView) _$_findCachedViewById(R.id.scrollView);
            Intrinsics.checkExpressionValueIsNotNull(nestedScrollView, "scrollView");
            nestedScrollView.setVisibility(0);
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.vNoData);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "vNoData");
            linearLayout.setVisibility(8);
            if (this.opType == 22) {
                this.memberList.clear();
                LogUtils.e(new Object[]{"members " + this.memberList.size()});
                this.memberList.addAll(arrayList);
                updateTeamNum();
            } else if (arrayList.size() > 0) {
                this.memberList.remove(this.editPos);
                this.memberList.add(this.editPos, arrayList.get(0));
            }
            LogUtils.e(new Object[]{"人员数memberList" + this.memberList.size()});
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMemberList");
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public final void updateTeamNum() {
        FakeBoldText fakeBoldText = (FakeBoldText) _$_findCachedViewById(R.id.tv_nums);
        Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tv_nums");
        fakeBoldText.setText("(" + this.memberList.size() + "/" + this.maxCount + ")");
        if (this.memberList.size() < this.maxCount) {
            RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.rlAddNew);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlAddNew");
            relativeLayout.setVisibility(0);
        } else {
            RelativeLayout relativeLayout2 = (RelativeLayout) _$_findCachedViewById(R.id.rlAddNew);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout2, "rlAddNew");
            relativeLayout2.setVisibility(8);
        }
        switchAddTip();
    }

    private final void switchAddTip() {
        if (this.memberList.size() == 0) {
            NestedScrollView nestedScrollView = (NestedScrollView) _$_findCachedViewById(R.id.scrollView);
            Intrinsics.checkExpressionValueIsNotNull(nestedScrollView, "scrollView");
            nestedScrollView.setVisibility(8);
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.vNoData);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "vNoData");
            linearLayout.setVisibility(0);
            return;
        }
        NestedScrollView nestedScrollView2 = (NestedScrollView) _$_findCachedViewById(R.id.scrollView);
        Intrinsics.checkExpressionValueIsNotNull(nestedScrollView2, "scrollView");
        nestedScrollView2.setVisibility(0);
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.vNoData);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "vNoData");
        linearLayout2.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.isShare) {
            showMsg("操作成功");
            this.isShare = false;
        }
    }
}
