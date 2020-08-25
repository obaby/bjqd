package cn.xports.qd2.campaign;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.entity.ViewElementData;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.PictureUtil;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0014J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\b\u0010\u0016\u001a\u00020\u0007H\u0014J\u0016\u0010\u0017\u001a\u00020\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0016J\b\u0010\u001c\u001a\u00020\u0011H\u0014J\"\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcn/xports/qd2/campaign/AddMemberActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "campId", "", "maxCount", "", "memberList", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/MemberInfo;", "Lkotlin/collections/ArrayList;", "opType", "playerId", "update", "", "addPlayer", "", "member", "finish", "getChildTitle", "getElements", "getLayoutId", "getPlayerInfo", "viewElementDatas", "", "Lcn/xports/qd2/entity/ViewElementData;", "initData", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "updatePlayer", "uploadImg", "path", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
public final class AddMemberActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private String campId = "";
    /* access modifiers changed from: private */
    public int maxCount;
    /* access modifiers changed from: private */
    public ArrayList<MemberInfo> memberList;
    /* access modifiers changed from: private */
    public int opType = 22;
    private String playerId = "";
    /* access modifiers changed from: private */
    public boolean update;

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
        return "添加队员";
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_add_member;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        getElements();
        if (this.opType == 11) {
            this.mTvTitle.setText("队员管理");
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvTopTip);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvTopTip");
            textView.setVisibility(0);
            TextView textView2 = this.mTvRight;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "mTvRight");
            textView2.setVisibility(0);
        }
        if (this.opType == 33) {
            this.mTvTitle.setText("队员信息");
        }
        if (this.opType == 22 && !this.update) {
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvTopTip);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvTopTip");
            textView3.setVisibility(8);
        }
        if (this.opType != 22) {
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvSaveContinue);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "tvSaveContinue");
            textView4.setVisibility(8);
            CornerTextView cornerTextView = (CornerTextView) _$_findCachedViewById(R.id.ctSave);
            Intrinsics.checkExpressionValueIsNotNull(cornerTextView, "ctSave");
            cornerTextView.setEnabled(false);
            ((CornerTextView) _$_findCachedViewById(R.id.ctSave)).setBgColor(Color.parseColor("#dedede"));
        }
        this.mTvRight.setText("编辑");
        this.mTvRight.setOnClickListener(new AddMemberActivity$initView$1(this));
        ((CornerTextView) _$_findCachedViewById(R.id.ctSave)).setOnClickListener(new AddMemberActivity$initView$2(this));
        ((TextView) _$_findCachedViewById(R.id.tvSaveContinue)).setOnClickListener(new AddMemberActivity$initView$3(this));
    }

    private final void getElements() {
        ApiUtil.getApi2().getElements(this.campId).compose(RxUtil.applyErrorsWithIO()).subscribe(new AddMemberActivity$getElements$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void getPlayerInfo(List<? extends ViewElementData> list) {
        String string = SPUtils.getInstance().getString("playerId");
        Intrinsics.checkExpressionValueIsNotNull(string, "SPUtils.getInstance().getString(\"playerId\")");
        this.playerId = string;
        ApiUtil.getApi2().getPlayerInfo(this.playerId).compose(RxUtil.applyErrorsWithIO()).subscribe(new AddMemberActivity$getPlayerInfo$1(this, list, this));
    }

    /* access modifiers changed from: private */
    public final void addPlayer(MemberInfo memberInfo, boolean z) {
        if (this.update) {
            ApiUtil.getApi2().addPlayerInfo(SPUtils.getInstance().getString(K.enrollId), SPUtils.getInstance().getString("campItemId"), SPUtils.getInstance().getString("teamId"), memberInfo.getName(), memberInfo.getGender(), memberInfo.getMobileNum(), memberInfo.getPsptType(), memberInfo.getPsptId(), GsonUtils.toJson(memberInfo.getEnrollInfoList()), "1").compose(RxUtil.applyErrorsWithIO()).subscribe(new AddMemberActivity$addPlayer$1(this, memberInfo, z, this));
        }
    }

    /* access modifiers changed from: private */
    public final void updatePlayer(MemberInfo memberInfo) {
        if (this.update) {
            ApiUtil.getApi2().updatePlayerInfo(SPUtils.getInstance().getString(K.enrollId), SPUtils.getInstance().getString("playerId"), memberInfo.getName(), memberInfo.getGender(), memberInfo.getMobileNum(), memberInfo.getPsptType(), memberInfo.getPsptId(), GsonUtils.toJson(memberInfo.getEnrollInfoList()), "1").compose(RxUtil.applyErrorsWithIO()).subscribe(new AddMemberActivity$updatePlayer$1(this, memberInfo, this));
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        Uri data;
        String str;
        if (i2 == -1) {
            if (i == 1111) {
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
                Intrinsics.checkExpressionValueIsNotNull(uriImgPath, "PictureUtil.getUriImgPat…@AddMemberActivity, this)");
                uploadImg(uriImgPath);
            }
        }
    }

    private final void uploadImg(String str) {
        ApiUtil.getApi2().uploadImg(RetrofitUtil.getMultiparts("file", new File(str), (Map<String, String>) null)).compose(RxUtil.applyIO()).subscribe(new AddMemberActivity$uploadImg$1(this, this));
    }

    public void finish() {
        if (this.opType != 33) {
            Intent intent = new Intent();
            intent.putExtra("members", GsonUtils.toJson(this.memberList));
            setResult(-1, intent);
        }
        super.finish();
    }

    public void initData() {
        String stringExtra = getStringExtra("members");
        this.campId = getStringExtra(K.campId);
        this.maxCount = getIntent().getIntExtra("maxCount", 0);
        this.memberList = (ArrayList) GsonUtils.fromJson(stringExtra, new AddMemberActivity$initData$1().getType());
        this.opType = getIntent().getIntExtra("opType", 22);
        this.update = getIntent().getBooleanExtra("update", false);
    }
}
