package cn.xports.qd2.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CourseResvAdapter;
import cn.xports.qd2.adapter.CourseWeekdayAdapter;
import cn.xports.qd2.entity.DateWeekday;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.mvp.CourseResvPresenter;
import cn.xports.qd2.mvp.CourseResvView;
import cn.xports.qd2.util.ViewExt;
import cn.xports.sportCoaching.WebViewDetailActivity;
import cn.xports.widget.EmptyRecyclerView;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.TimeUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\rH\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0014J\b\u0010\u001c\u001a\u00020\rH\u0014J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0014J\u0012\u0010\u001f\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010#\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0016\u0010$\u001a\u00020\u00182\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070&H\u0002J\u0016\u0010'\u001a\u00020\u00182\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH\u0016J\u001e\u0010)\u001a\u00020\u00182\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f2\u0006\u0010+\u001a\u00020\u0007H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000fj\b\u0012\u0004\u0012\u00020\u0007`\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u000fj\b\u0012\u0004\u0012\u00020\u0013`\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u000fj\b\u0012\u0004\u0012\u00020\u0016`\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcn/xports/qd2/training/CourseResvActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/qd2/mvp/CourseResvPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "Lcn/xports/qd2/mvp/CourseResvView;", "()V", "coachCourses", "Lcn/xports/baselib/bean/DataMap;", "dayAdapter", "Lcn/xports/qd2/adapter/CourseWeekdayAdapter;", "lessonAdapter", "Lcn/xports/qd2/adapter/CourseResvAdapter;", "lessonPos", "", "lessons", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "selectCourse", "titles", "", "totalLesson", "weekdayList", "Lcn/xports/qd2/entity/DateWeekday;", "courseResv", "", "resvData", "error", "getChildTitle", "getLayoutId", "initData", "initView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "setUpTypes", "courseList", "", "showCoachs", "coachs", "showSchedule", "weekdays", "lessonList", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseResvActivity.kt */
public final class CourseResvActivity extends BaseBussActivity<CourseResvPresenter> implements TabLayout.OnTabSelectedListener, CourseResvView {
    private HashMap _$_findViewCache;
    private final DataMap coachCourses = new DataMap();
    private final CourseWeekdayAdapter dayAdapter = new CourseWeekdayAdapter(this.weekdayList);
    /* access modifiers changed from: private */
    public final CourseResvAdapter lessonAdapter = new CourseResvAdapter(this.lessons);
    /* access modifiers changed from: private */
    public int lessonPos;
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> lessons = new ArrayList<>();
    /* access modifiers changed from: private */
    public DataMap selectCourse = new DataMap();
    private final ArrayList<String> titles = new ArrayList<>();
    /* access modifiers changed from: private */
    public DataMap totalLesson = new DataMap();
    private final ArrayList<DateWeekday> weekdayList = new ArrayList<>();

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
        return "课程预约";
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void courseResv(@NotNull DataMap dataMap, int i) {
        String str;
        int i2;
        String str2;
        DataMap dataMap2 = dataMap;
        Intrinsics.checkParameterIsNotNull(dataMap2, "resvData");
        String message = dataMap.getMessage();
        if (i == 0) {
            i2 = 1;
            this.lessons.get(this.lessonPos).copy(dataMap2, K.reserveId);
            this.lessonAdapter.notifyDataSetChanged();
            str = "/myResv";
            StringBuilder sb = new StringBuilder();
            String string = dataMap2.getString("lessonTime");
            Intrinsics.checkExpressionValueIsNotNull(string, "resvData.getString(\"lessonTime\")");
            sb.append(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default((String) StringsKt.split$default(string, new String[]{"-"}, false, 0, 6, (Object) null).get(0), "年", "-", false, 4, (Object) null), "月", "-", false, 4, (Object) null), "日", "", false, 4, (Object) null));
            sb.append(":00");
            String sb2 = sb.toString();
            long timeSpanByNow = TimeUtils.getTimeSpanByNow(sb2, 86400000);
            str2 = "距离开课还有" + timeSpanByNow + "天" + (TimeUtils.getTimeSpanByNow(sb2, 3600000) - (((long) 24) * timeSpanByNow)) + "时" + (TimeUtils.getTimeSpanByNow(sb2, 60000) - (TimeUtils.getTimeSpanByNow(sb2, 3600000) * ((long) 60))) + "分";
        } else {
            str2 = message;
            str = "";
            i2 = 0;
        }
        startActivity(new Intent(this, CourseResvStateActivity.class).putExtra(K.state, String.valueOf(i2)).putExtra(WebViewDetailActivity.TITLE, i2 == 0 ? "预约失败" : "预约成功").putExtra("content", i2 == 0 ? "预约失败" : "恭喜，预约成功！").putExtra("tip", str2).putExtra("firstOptionName", "继续预约").putExtra("secondOptionName", i2 == 0 ? "关闭" : "查看我的预约").putExtra("secondOptionUrl", str));
    }

    public void showSchedule(@NotNull ArrayList<DateWeekday> arrayList, @NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(arrayList, "weekdays");
        Intrinsics.checkParameterIsNotNull(dataMap, K.lessonList);
        this.totalLesson = dataMap;
        String str = "";
        Collection collection = arrayList;
        if (!collection.isEmpty()) {
            DateWeekday dateWeekday = arrayList.get(0);
            Intrinsics.checkExpressionValueIsNotNull(dateWeekday, "weekdays[0]");
            dateWeekday.setSelect(true);
            DateWeekday dateWeekday2 = arrayList.get(0);
            Intrinsics.checkExpressionValueIsNotNull(dateWeekday2, "weekdays[0]");
            str = dateWeekday2.getDate();
            Intrinsics.checkExpressionValueIsNotNull(str, "weekdays[0].date");
        }
        this.weekdayList.clear();
        this.weekdayList.addAll(collection);
        this.dayAdapter.notifyDataSetChanged();
        this.lessons.clear();
        ArrayList<DataMap> dataList = dataMap.getDataList(str);
        Intrinsics.checkExpressionValueIsNotNull(dataList, "lessonList.getDataList(d)");
        for (DataMap copy : dataList) {
            this.lessons.add(copy.copy(this.selectCourse, K.courseName, K.image, K.enrollId));
        }
        this.lessonAdapter.notifyDataSetChanged();
    }

    public void showCoachs(@NotNull ArrayList<DataMap> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "coachs");
        for (DataMap dataMap : arrayList) {
            String string = dataMap.getString(K.coachName);
            this.titles.add(string);
            this.coachCourses.put(string, dataMap.getDataList(K.courseList));
        }
        if (arrayList.size() > 1) {
            TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
            Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
            tabLayout.setVisibility(0);
            TabLayout tabLayout2 = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
            Intrinsics.checkExpressionValueIsNotNull(tabLayout2, "tabLayout");
            ViewExt.setTextColorSize(ViewExt.setUpTabLayout(ViewExt.setOnTabListener(tabLayout2, this), this.titles, R.layout.item_tab_text, -1), R.color.white, 17.0f, R.color.white, 14.0f).setTabMode(0);
            if (arrayList.size() > 3) {
                TabLayout tabLayout3 = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
                Intrinsics.checkExpressionValueIsNotNull(tabLayout3, "tabLayout");
                tabLayout3.setTabMode(0);
            }
        } else if (arrayList.size() == 1) {
            ArrayList<DataMap> dataList = this.coachCourses.getDataList(this.titles.get(0));
            Intrinsics.checkExpressionValueIsNotNull(dataList, "coachCourses.getDataList(titles[0])");
            setUpTypes(dataList);
        } else {
            this.lessonAdapter.notifyDataSetChanged();
        }
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            ArrayList<DataMap> dataList = this.coachCourses.getDataList(this.titles.get(tab.getPosition()));
            Intrinsics.checkExpressionValueIsNotNull(dataList, "coachCourses.getDataList(titles[position])");
            setUpTypes(dataList);
        }
    }

    public void initData() {
        setPresenter(new CourseResvPresenter(this));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_course_appoint;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        useBlackTitle(false);
        Activity activity = this;
        BarUtils.setStatusBarLightMode(activity, false);
        BarUtils.setStatusBarColor(activity, ColorUtils.getColor(R.color.blue_2e6));
        this.rlTitleBar.setBackgroundColor(ColorUtils.getColor(R.color.blue_2e6));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvDays);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvDays");
        Context context = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvDays);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvDays");
        recyclerView2.setAdapter(this.dayAdapter);
        this.dayAdapter.setOnItemClickListener(new CourseResvActivity$initView$1(this));
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCourseList");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCourseList");
        emptyRecyclerView2.setAdapter(this.lessonAdapter);
        this.lessonAdapter.setOnItemClickListener(new CourseResvActivity$initView$2(this));
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCourseList");
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseList)).setEmptyView(ViewExt.getEmptyView(emptyRecyclerView3, "暂无课程信息！\n「有心不怕迟，立即购课吧！」", "立即购课", new CourseResvActivity$initView$empty$1(this)));
        CourseResvPresenter courseResvPresenter = (CourseResvPresenter) getPresenter();
        if (courseResvPresenter != null) {
            courseResvPresenter.getCoachCourse();
        }
    }

    private final void setUpTypes(List<? extends DataMap> list) {
        ((LinearLayout) _$_findCachedViewById(R.id.llCourseTypes)).removeAllViews();
        int i = 0;
        for (Object next : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DataMap dataMap = (DataMap) next;
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_course_type, (LinearLayout) _$_findCachedViewById(R.id.llCourseTypes), false);
            if (inflate != null) {
                CheckBox checkBox = (CheckBox) inflate;
                checkBox.setText(dataMap.getString(K.courseName));
                checkBox.setOnClickListener(new CourseResvActivity$setUpTypes$$inlined$forEachIndexed$lambda$1(dataMap, checkBox, this));
                if (i == 0) {
                    this.selectCourse = dataMap;
                    checkBox.setChecked(true);
                    checkBox.setEnabled(false);
                    CourseResvPresenter courseResvPresenter = (CourseResvPresenter) getPresenter();
                    if (courseResvPresenter != null) {
                        courseResvPresenter.getCourseSchedule(dataMap.getString(K.coachId), dataMap.getString(K.enrollId));
                    }
                }
                ViewGroup.LayoutParams layoutParams = checkBox.getLayoutParams();
                if (layoutParams != null) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.setMargins(0, 0, DensityUtil.dp2px(10.0f), 0);
                    checkBox.setLayoutParams(layoutParams2);
                    ((LinearLayout) _$_findCachedViewById(R.id.llCourseTypes)).addView(checkBox);
                    i = i2;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
            }
        }
    }
}
