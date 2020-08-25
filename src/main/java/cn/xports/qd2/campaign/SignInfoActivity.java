package cn.xports.qd2.campaign;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.MemberAddAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.PictureUtil;
import cn.xports.qd2.widget.ElementView;
import cn.xports.widget.AgreementBar;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0005H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\bH\u0014J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0014J\"\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0005H\u0002J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcn/xports/qd2/campaign/SignInfoActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "campId", "", "campItemId", "editPos", "", "enrollType", "members", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/MemberInfo;", "Lkotlin/collections/ArrayList;", "opType", "packageId", "checkAttr", "", "getAgreement", "getChildTitle", "getElements", "getLayoutId", "getOrder", "enrollId", "initData", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "signUpItem", "state", "uploadImg", "path", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SignInfoActivity.kt */
public final class SignInfoActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String campId = "";
    private String campItemId = "";
    /* access modifiers changed from: private */
    public int editPos;
    /* access modifiers changed from: private */
    public String enrollType = "1";
    /* access modifiers changed from: private */
    public final ArrayList<MemberInfo> members = new ArrayList<>();
    /* access modifiers changed from: private */
    public int opType = 22;
    private String packageId = "";

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
        return "填写报名信息";
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_sign_info;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((RelativeLayout) _$_findCachedViewById(R.id.rlAddMember)).setOnClickListener(new SignInfoActivity$initView$1(this));
        AgreementBar agreementBar = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar, "agreementBar");
        agreementBar.setCheck(true);
        AgreementBar agreementBar2 = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar2, "agreementBar");
        agreementBar2.getTvTip().setTextSize(14.0f);
        AgreementBar agreementBar3 = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar3, "agreementBar");
        agreementBar3.getTvAgreement().setTextSize(14.0f);
        ((TextView) _$_findCachedViewById(R.id.tvSignNew)).setOnClickListener(new SignInfoActivity$initView$2(this));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMemberList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvMemberList");
        recyclerView2.setAdapter(new MemberAddAdapter(this.members).setOnEditMemberListener(new SignInfoActivity$initView$3(this)));
        getElements();
        getAgreement();
    }

    public void initData() {
        this.campId = getStringExtra(K.campId);
        this.campItemId = getStringExtra("campItemId");
        this.enrollType = getStringExtra("enrollType");
        this.packageId = getStringExtra("packageId");
    }

    private final void getElements() {
        ApiUtil.getApi2().getElements(this.campId).compose(RxUtil.applyErrorsWithIO()).subscribe(new SignInfoActivity$getElements$1(this, this));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        Uri data;
        String str;
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 1010) {
                if (intent != null) {
                    ArrayList arrayList = (ArrayList) GsonUtils.fromJson(intent.getStringExtra("members"), new SignInfoActivity$onActivityResult$1$memberList$1().getType());
                    Collection collection = arrayList;
                    if (!(collection == null || collection.isEmpty())) {
                        if (this.opType == 22) {
                            this.members.clear();
                            this.members.addAll(collection);
                        } else {
                            this.members.remove(this.editPos);
                            this.members.add(this.editPos, arrayList.get(0));
                        }
                        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMemberList);
                        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMemberList");
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }
            } else if (i == 1111) {
                String string = SPUtils.getInstance().getString("_path_", "");
                StringBuilder sb = new StringBuilder();
                File externalFilesDir = getExternalFilesDir("");
                if (externalFilesDir == null || (str = externalFilesDir.getPath()) == null) {
                    str = "sdcard";
                }
                sb.append(str);
                sb.append("/");
                sb.append(string);
                uploadImg(sb.toString());
            } else if (i == 2222 && intent != null && (data = intent.getData()) != null) {
                String uriImgPath = PictureUtil.getUriImgPath(this, data);
                Intrinsics.checkExpressionValueIsNotNull(uriImgPath, "PictureUtil.getUriImgPat…s@SignInfoActivity, this)");
                uploadImg(uriImgPath);
            }
        }
    }

    private final void checkAttr() {
        if (ElementView.checkInput((LinearLayout) _$_findCachedViewById(R.id.llSignElements))) {
            ApiUtil.getApi2().getCampAttr(this.campId, "player_need_confirmed").compose(RxUtil.applyIO()).subscribe(new SignInfoActivity$checkAttr$1(this, this));
        }
    }

    /* access modifiers changed from: private */
    public final void signUpItem(String str) {
        MemberInfo createMemberInfo;
        if (ElementView.checkInput((LinearLayout) _$_findCachedViewById(R.id.llSignElements)) && (createMemberInfo = ElementView.createMemberInfo((LinearLayout) _$_findCachedViewById(R.id.llSignElements), (MemberInfo) null)) != null) {
            for (MemberInfo state : this.members) {
                state.setState(str);
            }
            if (Intrinsics.areEqual(this.campItemId, "")) {
                ApiUtil.getApi2().signUpNoItem(this.campId, SPUtil.Companion.getINSTANCE().getStringValue("appId"), createMemberInfo.getName(), createMemberInfo.getMobileNum(), createMemberInfo.getPsptType(), createMemberInfo.getPsptId(), createMemberInfo.getGender()).compose(RxUtil.applyErrorsWithIO()).subscribe(new SignInfoActivity$signUpItem$2(this, this));
                return;
            }
            String json = GsonUtils.toJson(this.members, false);
            String json2 = GsonUtils.toJson(createMemberInfo.getEnrollInfoList());
            LogUtils.e(new Object[]{json});
            LogUtils.e(new Object[]{json2});
            ApiUtil.getApi2().signUpItem(this.campId, this.campItemId, SPUtil.Companion.getINSTANCE().getStringValue("appId"), json, json2, createMemberInfo.getName(), createMemberInfo.getMobileNum(), createMemberInfo.getGender(), createMemberInfo.getPsptType(), createMemberInfo.getPsptId(), createMemberInfo.getTeamName()).compose(RxUtil.applyIO()).subscribe(new SignInfoActivity$signUpItem$3(this, this));
        }
    }

    /* access modifiers changed from: private */
    public final void getOrder(String str) {
        ApiUtil.getApi2().campOrder(str, this.packageId).compose(RxUtil.applyIO()).subscribe(new SignInfoActivity$getOrder$1(this, this));
    }

    private final void getAgreement() {
        ApiUtil.getApi2().getCampaignDetail(this.campId).compose(RxUtil.applyErrorsWithIO()).subscribe(new SignInfoActivity$getAgreement$1(this, this));
    }

    private final void uploadImg(String str) {
        ApiUtil.getApi2().uploadImg(RetrofitUtil.getMultiparts("file", new File(str), (Map<String, String>) null)).compose(RxUtil.applyIO()).subscribe(new SignInfoActivity$uploadImg$1(this, this));
    }
}
