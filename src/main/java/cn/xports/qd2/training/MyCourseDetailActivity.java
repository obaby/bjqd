package cn.xports.qd2.training;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CourseAttendAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SpanUtils;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000bH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcn/xports/qd2/training/MyCourseDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "adapter", "Lcn/xports/qd2/adapter/CourseAttendAdapter;", "attendList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "enrollId", "", "subMap", "getChildTitle", "getDetail", "", "getLayoutId", "", "initCourse", "dataMap", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCourseDetailActivity.kt */
public final class MyCourseDetailActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private final CourseAttendAdapter adapter = new CourseAttendAdapter(this.attendList);
    private final ArrayList<DataMap> attendList = new ArrayList<>();
    private String enrollId = "";
    /* access modifiers changed from: private */
    public final DataMap subMap = new DataMap();

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
        return "课程详情";
    }

    public void initData() {
        this.enrollId = getStringExtra(K.enrollId);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_course_use_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvHistory);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvHistory");
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvHistory)).setEmptyView(ViewExt.getEmptyView(emptyRecyclerView, "暂无扣次信息"));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvHistory);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvHistory");
        emptyRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvHistory);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvHistory");
        emptyRecyclerView3.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new MyCourseDetailActivity$initView$1(this));
        getDetail();
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(String.class).subscribe(new MyCourseDetailActivity$initView$2(this)));
        ((TextView) _$_findCachedViewById(R.id.tvOrderCourse)).setOnClickListener(new MyCourseDetailActivity$initView$3(this));
        ((TextView) _$_findCachedViewById(R.id.tvContinueCourse)).setOnClickListener(new MyCourseDetailActivity$initView$4(this));
    }

    /* access modifiers changed from: private */
    public final void getDetail() {
        ApiUtil.getApi2().getMyCourseDetail(this.enrollId).compose(RxUtil.applyIO()).subscribe(new MyCourseDetailActivity$getDetail$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void initCourse(DataMap dataMap) {
        DataMap dataMap2 = dataMap.getDataMap(K.courseDetails);
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvStuName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvStuName");
        textView.setText(dataMap2.getString(K.stuName));
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvCourseName);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCourseName");
        textView2.setText(dataMap2.getString(K.courseName));
        this.subMap.set(K.courseName, dataMap2.getString(K.courseName));
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvClass);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvClass");
        textView3.setText("未分班");
        SpanUtils foregroundColor = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvUseDetail)).append("余").append(dataMap2.getString(K.remainNum)).setForegroundColor(ColorUtils.getColor(R.color.red_fd4));
        foregroundColor.append("节/总" + dataMap2.getString(K.lessonNum) + "节").create();
        String string = dataMap2.getString(K.startDate);
        int i = 0;
        if (string.length() > 10) {
            Intrinsics.checkExpressionValueIsNotNull(string, "start");
            if (string != null) {
                string = string.substring(0, 10);
                Intrinsics.checkExpressionValueIsNotNull(string, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        String str = string;
        Intrinsics.checkExpressionValueIsNotNull(str, "start");
        String replace$default = StringsKt.replace$default(str, "-", "/", false, 4, (Object) null);
        String string2 = dataMap2.getString(K.endDate, "");
        if (string2.length() > 10) {
            Intrinsics.checkExpressionValueIsNotNull(string2, "end");
            if (string2 != null) {
                string2 = string2.substring(0, 10);
                Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        String str2 = string2;
        Intrinsics.checkExpressionValueIsNotNull(str2, "end");
        String replace$default2 = StringsKt.replace$default(str2, "-", "/", false, 4, (Object) null);
        TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvEffectDate);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "tvEffectDate");
        textView4.setText(replace$default + " - " + replace$default2);
        this.attendList.clear();
        this.attendList.addAll(dataMap.getDataList(K.attendInfo));
        this.adapter.notifyDataSetChanged();
        this.subMap.set(K.venueId, dataMap2.getString(K.venueId));
        TextView textView5 = (TextView) _$_findCachedViewById(R.id.tvOrderCourse);
        Intrinsics.checkExpressionValueIsNotNull(textView5, "tvOrderCourse");
        if (!Intrinsics.areEqual(dataMap2.getString(K.privateTag), "1")) {
            i = 8;
        }
        textView5.setVisibility(i);
    }
}
