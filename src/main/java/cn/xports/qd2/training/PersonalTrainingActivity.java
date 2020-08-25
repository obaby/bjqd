package cn.xports.qd2.training;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.RelativeLayout;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.fragment.TrainingFragment;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.NoScrollViewPager;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\fH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0014J\u0012\u0010\u0014\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0012\u0010\u0018\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0006j\b\u0012\u0004\u0012\u00020\f`\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcn/xports/qd2/training/PersonalTrainingActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "()V", "fragments", "Ljava/util/ArrayList;", "Lcn/xports/qd2/fragment/TrainingFragment;", "Lkotlin/collections/ArrayList;", "getFragments", "()Ljava/util/ArrayList;", "titles", "", "getTitles", "getChildTitle", "getLayoutId", "", "initData", "", "initView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: PersonalTrainingActivity.kt */
public final class PersonalTrainingActivity extends BaseBussActivity<IPresenter> implements TabLayout.OnTabSelectedListener {
    private HashMap _$_findViewCache;
    @NotNull
    private final ArrayList<TrainingFragment> fragments = new ArrayList<>();
    @NotNull
    private final ArrayList<String> titles = CollectionsKt.arrayListOf(new String[]{"私教", "培训"});

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
        return "私教培训";
    }

    public void initData() {
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            ((NoScrollViewPager) _$_findCachedViewById(R.id.viewPager)).setCurrentItem(tab.getPosition());
        }
    }

    @NotNull
    public final ArrayList<TrainingFragment> getFragments() {
        return this.fragments;
    }

    @NotNull
    public final ArrayList<String> getTitles() {
        return this.titles;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_personal_training;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.fragments.add(TrainingFragment.Companion.newInstance("1"));
        this.fragments.add(TrainingFragment.Companion.newInstance(K.k0));
        NoScrollViewPager noScrollViewPager = (NoScrollViewPager) _$_findCachedViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(noScrollViewPager, "viewPager");
        ViewExt.showFragments(noScrollViewPager, this.fragments, false);
        TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
        ViewExt.setTextColorSize(ViewExt.setUpTabLayout$default(ViewExt.setOnTabListener(tabLayout, this), this.titles, R.layout.item_tab_text, 0, 4, (Object) null), R.color.blue_2e6, 17.0f, R.color.black_343, 14.0f);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlTrainAppointment)).setOnClickListener(new PersonalTrainingActivity$initView$1(this));
        ((RelativeLayout) _$_findCachedViewById(R.id.rlTimetable)).setOnClickListener(new PersonalTrainingActivity$initView$2(this));
    }
}
