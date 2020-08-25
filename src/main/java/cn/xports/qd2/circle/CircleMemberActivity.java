package cn.xports.qd2.circle;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CircleMemberAdapter;
import cn.xports.qd2.util.ApiUtil;
import com.blankj.utilcode.util.GsonUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\u0007H\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0014J8\u0010\u0013\u001a\u00020\u00102\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\u000b2\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\nH\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\u0016\u0010\u0019\u001a\u00020\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcn/xports/qd2/circle/CircleMemberActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "adapter", "Lcn/xports/qd2/adapter/CircleMemberAdapter;", "circleId", "", "members", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "getChildTitle", "getLayoutId", "", "getMembers", "", "initData", "initView", "removeMember", "ids", "removed", "setRole", "member", "setSwipeItem", "updateUi", "list", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CircleMemberActivity.kt */
public final class CircleMemberActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final CircleMemberAdapter adapter = new CircleMemberAdapter(this.members);
    private String circleId = "";
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> members = new ArrayList<>();

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
        return "圈子成员";
    }

    public void initData() {
        this.circleId = getStringExtra("circleId");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_circle_member;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mIvRight.setImageResource(R.drawable.ic_right_select);
        getMembers();
        this.mIvRight.setOnClickListener(new CircleMemberActivity$initView$1(this));
        this.mTvRight.setOnClickListener(new CircleMemberActivity$initView$2(this));
        ((CheckBox) _$_findCachedViewById(R.id.checkBox)).setOnCheckedChangeListener(new CircleMemberActivity$initView$3(this));
        this.adapter.setOnItemClickListener(new CircleMemberActivity$initView$4(this));
        setSwipeItem();
        SwipeMenuRecyclerView _$_findCachedViewById = _$_findCachedViewById(R.id.rvMemberList);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "rvMemberList");
        _$_findCachedViewById.setLayoutManager(new LinearLayoutManager(this));
        SwipeMenuRecyclerView _$_findCachedViewById2 = _$_findCachedViewById(R.id.rvMemberList);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "rvMemberList");
        _$_findCachedViewById2.setAdapter(this.adapter);
        ((TextView) _$_findCachedViewById(R.id.tvRemoveMember)).setOnClickListener(new CircleMemberActivity$initView$5(this));
    }

    private final void setSwipeItem() {
        _$_findCachedViewById(R.id.rvMemberList).setSwipeMenuCreator(new CircleMemberActivity$setSwipeItem$menuCreate$1(this));
        _$_findCachedViewById(R.id.rvMemberList).setSwipeMenuItemClickListener(new CircleMemberActivity$setSwipeItem$1(this));
    }

    /* access modifiers changed from: private */
    public final void updateUi(List<? extends DataMap> list) {
        String stringValue = SPUtil.Companion.getINSTANCE().getStringValue("coAccountId");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (DataMap dataMap : list) {
            String string = dataMap.getString("role");
            if (string != null) {
                switch (string.hashCode()) {
                    case 49:
                        if (string.equals("1")) {
                            this.members.add(dataMap);
                            break;
                        }
                        break;
                    case 50:
                        if (string.equals("2")) {
                            arrayList.add(dataMap);
                            break;
                        }
                        break;
                    case 51:
                        if (string.equals("3")) {
                            arrayList2.add(dataMap);
                            break;
                        }
                        break;
                }
            }
            if (Intrinsics.areEqual(stringValue, dataMap.getString("accountId"))) {
                this.adapter.setYourRole(dataMap.getString("role"));
            }
        }
        this.members.addAll(arrayList2);
        Collection collection = arrayList;
        this.members.addAll(collection);
        this.adapter.notifyDataSetChanged();
        if (Intrinsics.areEqual(this.adapter.getYourRole(), "1") && this.members.size() > 1) {
            ImageView imageView = this.mIvRight;
            Intrinsics.checkExpressionValueIsNotNull(imageView, "mIvRight");
            imageView.setVisibility(0);
        } else if (Intrinsics.areEqual(this.adapter.getYourRole(), "3") && (!collection.isEmpty())) {
            ImageView imageView2 = this.mIvRight;
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "mIvRight");
            imageView2.setVisibility(0);
        }
    }

    private final void getMembers() {
        Observable<ResponseBody> circleMember = ApiUtil.getApi2().getCircleMember(this.circleId);
        Intrinsics.checkExpressionValueIsNotNull(circleMember, "ApiUtil.getApi2().getCircleMember(circleId)");
        RxUtil.subscribeDataMapIO$default(circleMember, this, new CircleMemberActivity$getMembers$1(this), (Function1) null, false, 12, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void setRole(DataMap dataMap) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "3";
        if (Intrinsics.areEqual(dataMap.getString("role"), "3")) {
            objectRef.element = "2";
        }
        Observable<ResponseBody> role = ApiUtil.getApi2().setRole(this.circleId, dataMap.getString("accountId"), (String) objectRef.element);
        Intrinsics.checkExpressionValueIsNotNull(role, "ApiUtil.getApi2().setRol…String(\"accountId\"),role)");
        RxUtil.subscribeDataMapIO$default(role, this, new CircleMemberActivity$setRole$1(this, dataMap, objectRef), (Function1) null, false, 12, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void removeMember(ArrayList<String> arrayList, ArrayList<DataMap> arrayList2) {
        if (!arrayList.isEmpty()) {
            Observable<ResponseBody> removeMember = ApiUtil.getApi2().removeMember(this.circleId, GsonUtils.toJson(arrayList));
            Intrinsics.checkExpressionValueIsNotNull(removeMember, "ApiUtil.getApi2().remove…Id,GsonUtils.toJson(ids))");
            RxUtil.subscribeDataMapIO$default(removeMember, this, new CircleMemberActivity$removeMember$1(this, arrayList2), (Function1) null, false, 12, (Object) null);
        }
    }
}
