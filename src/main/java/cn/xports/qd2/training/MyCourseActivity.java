package cn.xports.qd2.training;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.MyCourseAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\rH\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0014J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\r0\bj\b\u0012\u0004\u0012\u00020\r`\nX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcn/xports/qd2/training/MyCourseActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "()V", "adapter", "Lcn/xports/qd2/adapter/MyCourseAdapter;", "privateList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "showList", "titles", "", "trainList", "venueId", "getChildTitle", "getLayoutId", "", "getProductList", "", "initData", "initView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCourseActivity.kt */
public final class MyCourseActivity extends BaseBussActivity<IPresenter> implements TabLayout.OnTabSelectedListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final MyCourseAdapter adapter = new MyCourseAdapter(this.showList);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> privateList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> showList = new ArrayList<>();
    private final ArrayList<String> titles = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> trainList = new ArrayList<>();
    private String venueId = "";

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
        return "我的课程";
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            this.showList.clear();
            if (tab.getPosition() == 0) {
                this.showList.addAll(this.trainList);
            } else {
                this.showList.addAll(this.privateList);
            }
            this.adapter.notifyDataSetChanged();
        }
    }

    public void initData() {
        String stringExtra = getIntent().getStringExtra(K.venueId);
        if (stringExtra != null) {
            this.venueId = stringExtra;
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_course;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.titles.add("培训课程");
        this.titles.add("私教课程");
        TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
        ViewExt.setTextColorSize(ViewExt.setUpTabLayout$default(ViewExt.setOnTabListener(tabLayout, this), this.titles, R.layout.item_tab_text, 0, 4, (Object) null), R.color.blue_2e6, 17.0f, R.color.gray_888, 14.0f);
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCourseList");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCourseList");
        emptyRecyclerView2.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new MyCourseActivity$initView$1(this));
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCourseList");
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList)).setEmptyView(ViewExt.getEmptyView(emptyRecyclerView3, "暂无课程信息！\n「有心不怕迟，立即约课吧！」", "立即约课", new MyCourseActivity$initView$empty$1(this)));
        getProductList();
    }

    private final void getProductList() {
        ApiUtil.getApi2().getMyCourseList().compose(RxUtil.applyDataMapIO()).subscribe(new MyCourseActivity$getProductList$1(this, this));
    }
}
