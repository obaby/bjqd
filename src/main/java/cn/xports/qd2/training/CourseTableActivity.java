package cn.xports.qd2.training;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.TableCourseBinder;
import cn.xports.qd2.adapter.TableTimeBinder;
import cn.xports.qd2.adapter.TableWeekDayBinder;
import cn.xports.qd2.entity.DateWeekday;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import com.blankj.utilcode.util.TimeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0006H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0014J \u0010 \u001a\u00020\u00192\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0005j\b\u0012\u0004\u0012\u00020\u0014`\u0007H\u0002J\u0016\u0010!\u001a\u00020\u00192\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060#H\u0002J\b\u0010$\u001a\u00020\u0019H\u0002J\u0016\u0010%\u001a\u00020\u00192\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00140#H\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0005j\b\u0012\u0004\u0012\u00020\u000f`\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0005j\b\u0012\u0004\u0012\u00020\u0014`\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcn/xports/qd2/training/CourseTableActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "colors", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "courseAdapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "courseItems", "Lme/drakeet/multitype/Items;", "dayAdapter", "dayItems", "dayList", "Lcn/xports/qd2/entity/DateWeekday;", "selectDate", "selectStuIds", "selectVenueId", "stuColorMap", "Lcn/xports/baselib/bean/DataMap;", "stuNameMap", "tableCourses", "getChildTitle", "getCourseDays", "", "getFilterInfo", "getLayoutId", "", "getTable", "initData", "initView", "refreshTableList", "setStuColors", "stuIds", "", "setStuNames", "setVenueTypes", "venueList", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseTableActivity.kt */
public final class CourseTableActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private final ArrayList<String> colors = CollectionsKt.arrayListOf(new String[]{"#fd477a", "#f5cb3a", "#34da92", "#fda54c", "#5e5cfc"});
    /* access modifiers changed from: private */
    public final MultiTypeAdapter courseAdapter = new MultiTypeAdapter();
    /* access modifiers changed from: private */
    public final Items courseItems = new Items();
    /* access modifiers changed from: private */
    public final MultiTypeAdapter dayAdapter = new MultiTypeAdapter();
    /* access modifiers changed from: private */
    public final Items dayItems = new Items();
    private final ArrayList<DateWeekday> dayList = new ArrayList<>();
    /* access modifiers changed from: private */
    public String selectDate = "";
    /* access modifiers changed from: private */
    public String selectStuIds = "";
    /* access modifiers changed from: private */
    public String selectVenueId = "";
    private final DataMap stuColorMap = new DataMap();
    /* access modifiers changed from: private */
    public final DataMap stuNameMap = new DataMap();
    /* access modifiers changed from: private */
    public ArrayList<DataMap> tableCourses = new ArrayList<>();

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
        return "课表查询";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_course_table;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ItemViewBinder tableWeekDayBinder = new TableWeekDayBinder();
        tableWeekDayBinder.setOnItemClickListener(new CourseTableActivity$initView$1(this));
        this.dayAdapter.register(DateWeekday.class, tableWeekDayBinder);
        this.dayAdapter.setItems(this.dayItems);
        Context context = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, 0, false);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvDateList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvDateList");
        recyclerView.setLayoutManager(linearLayoutManager);
        ((RecyclerView) _$_findCachedViewById(R.id.rvDateList)).setOnScrollListener(new CourseTableActivity$initView$2(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvDateList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvDateList");
        recyclerView2.setAdapter(this.dayAdapter);
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvMonth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvMonth");
        StringBuilder sb = new StringBuilder();
        String nowString = TimeUtils.getNowString();
        Intrinsics.checkExpressionValueIsNotNull(nowString, "TimeUtils.getNowString()");
        if (nowString != null) {
            String substring = nowString.substring(5, 7);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(Integer.parseInt(substring));
            sb.append(10);
            sb.append("月");
            textView.setText(sb.toString());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            gridLayoutManager.setSpanSizeLookup(new CourseTableActivity$initView$3(this));
            EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseTable);
            Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCourseTable");
            emptyRecyclerView.setLayoutManager(gridLayoutManager);
            EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseTable);
            Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCourseTable");
            emptyRecyclerView2.setAdapter(this.courseAdapter);
            this.courseAdapter.setItems(this.courseItems);
            this.courseAdapter.register(String.class, new TableTimeBinder());
            this.courseAdapter.register(DataMap.class, new TableCourseBinder());
            getFilterInfo();
            getCourseDays();
            EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseTable);
            Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCourseTable");
            ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvCourseTable)).setEmptyView(ViewExt.getEmptyView(emptyRecyclerView3, "暂无课程信息"));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final void getCourseDays() {
        ApiUtil.getApi2().getVenueParams(K.course_schedule_days).compose(RxUtil.applyDataMapIO()).subscribe(new CourseTableActivity$getCourseDays$1(this, this));
    }

    private final void getFilterInfo() {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        api2.getUserFilterInfo().compose(RxUtil.applyDataMapIO()).subscribe(new CourseTableActivity$getFilterInfo$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void getTable() {
        if (Intrinsics.areEqual(this.selectVenueId, "")) {
            this.courseAdapter.notifyDataSetChanged();
        } else {
            ApiUtil.getApi2().getUserSchedule(this.selectVenueId, this.selectDate).compose(RxUtil.applyDataMapIO()).subscribe(new CourseTableActivity$getTable$1(this, this));
        }
    }

    private final void setStuColors(List<String> list) {
        this.stuColorMap.clear();
        int i = 0;
        for (Object next : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) next;
            if (i < 5) {
                this.stuColorMap.put(str, this.colors.get(i));
            } else {
                this.stuColorMap.put(str, this.colors.get(4));
            }
            i = i2;
        }
    }

    /* access modifiers changed from: private */
    public final void refreshTableList(ArrayList<DataMap> arrayList) {
        this.courseItems.clear();
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (DataMap dataMap : arrayList) {
            String str = dataMap.getString(K.startTime) + "-" + dataMap.getString(K.endTime);
            if (hashMap.get(str) == null) {
                hashMap.put(str, new ArrayList());
            }
            if (!arrayList2.contains(str)) {
                arrayList2.add(str);
            }
            ArrayList arrayList3 = (ArrayList) hashMap.get(str);
            if (arrayList3 != null) {
                arrayList3.add(dataMap);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        List list = CollectionsKt.toList(StringsKt.split$default(this.selectStuIds, new String[]{","}, false, 0, 6, (Object) null));
        for (String str2 : arrayList2) {
            this.courseItems.add(str2);
            ArrayList<DataMap> arrayList5 = (ArrayList) hashMap.get(str2);
            if (arrayList5 != null) {
                Intrinsics.checkExpressionValueIsNotNull(arrayList5, "this");
                for (DataMap dataMap2 : arrayList5) {
                    String string = dataMap2.getString(K.studentIds);
                    Intrinsics.checkExpressionValueIsNotNull(string, "course.getString(K.studentIds)");
                    for (String str3 : StringsKt.split$default(string, new String[]{","}, false, 0, 6, (Object) null)) {
                        if (list.contains(str3) && !arrayList4.contains(str3)) {
                            arrayList4.add(str3);
                        }
                    }
                    dataMap2.set("stuColorMap", this.stuColorMap);
                    this.courseItems.add(dataMap2);
                }
            }
        }
        setStuColors(arrayList4);
        this.courseAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public final void setVenueTypes(List<? extends DataMap> list) {
        ((LinearLayout) _$_findCachedViewById(R.id.llVenues)).removeAllViews();
        int i = 0;
        for (Object next : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DataMap dataMap = (DataMap) next;
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_venue_type, (LinearLayout) _$_findCachedViewById(R.id.llVenues), false);
            if (inflate != null) {
                CheckBox checkBox = (CheckBox) inflate;
                checkBox.setText(dataMap.getString(K.venueName));
                checkBox.setOnClickListener(new CourseTableActivity$setVenueTypes$$inlined$forEachIndexed$lambda$1(checkBox, dataMap, this));
                if (i == 0) {
                    checkBox.setChecked(true);
                    checkBox.setEnabled(false);
                    String string = dataMap.getString(K.venueId);
                    Intrinsics.checkExpressionValueIsNotNull(string, "venue.getString(K.venueId)");
                    this.selectVenueId = string;
                    String string2 = dataMap.getString(K.studentIds);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "venue.getString(K.studentIds)");
                    this.selectStuIds = string2;
                }
                ViewGroup.LayoutParams layoutParams = checkBox.getLayoutParams();
                if (layoutParams != null) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.setMargins(0, 0, DensityUtil.dp2px(10.0f), 0);
                    checkBox.setLayoutParams(layoutParams2);
                    ((LinearLayout) _$_findCachedViewById(R.id.llVenues)).addView(checkBox);
                    i = i2;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setStuNames() {
        ((LinearLayout) _$_findCachedViewById(R.id.llStudents)).removeAllViews();
        Set<String> keySet = this.stuColorMap.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "stuColorMap.keys");
        for (String str : keySet) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_student_type, (LinearLayout) _$_findCachedViewById(R.id.llStudents), false);
            CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.cb_student_name);
            inflate.findViewById(R.id.v_round).setBackgroundColor(Color.parseColor(this.stuColorMap.getString(str)));
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "typeCheckBox");
            checkBox.setText(this.stuNameMap.getString(str));
            checkBox.setOnClickListener(new CourseTableActivity$setStuNames$$inlined$forEach$lambda$1(checkBox, str, inflate, this));
            Intrinsics.checkExpressionValueIsNotNull(inflate, "layout");
            ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
            if (layoutParams != null) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.setMargins(0, 0, DensityUtil.dp2px(10.0f), 0);
                inflate.setLayoutParams(layoutParams2);
                ((LinearLayout) _$_findCachedViewById(R.id.llStudents)).addView(inflate);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
        }
    }
}
