package cn.xports.qd2.training;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.AppointDayBinder;
import cn.xports.qd2.adapter.MyAppointBinder;
import cn.xports.qd2.entity.DateWeekday;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0014J\b\u0010\u0017\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcn/xports/qd2/training/MyResvActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "clickData", "Lcn/xports/baselib/bean/DataMap;", "courseList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "dateList", "Lcn/xports/qd2/entity/DateWeekday;", "mItems", "Lme/drakeet/multitype/Items;", "multiTypeAdapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "getAppoints", "", "getChildTitle", "", "getLayoutId", "", "initData", "initView", "refreshList", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyResvActivity.kt */
public final class MyResvActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public DataMap clickData = new DataMap();
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> courseList = new ArrayList<>();
    private final ArrayList<DateWeekday> dateList = new ArrayList<>();
    private final Items mItems = new Items();
    private final MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();

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
        return "我的预约";
    }

    public void initData() {
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(String.class).subscribe(new MyResvActivity$initData$1(this)));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_resv;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ItemViewBinder appointDayBinder = new AppointDayBinder();
        ItemViewBinder myAppointBinder = new MyAppointBinder();
        myAppointBinder.setOnItemClickListener(new MyResvActivity$initView$1(this));
        this.multiTypeAdapter.register(DataMap.class, myAppointBinder);
        this.multiTypeAdapter.register(DateWeekday.class, appointDayBinder);
        this.multiTypeAdapter.setItems(this.mItems);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMyResvList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMyResvList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvMyResvList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvMyResvList");
        recyclerView2.setAdapter(this.multiTypeAdapter);
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvJoinTip);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvJoinTip");
        textView.setText("暂无预约信息");
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvJoin);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvJoin");
        textView2.setVisibility(8);
        getAppoints();
    }

    private final void getAppoints() {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        api2.getAppoints().compose(RxUtil.applyDataMapIO()).subscribe(new MyResvActivity$getAppoints$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void refreshList() {
        this.mItems.clear();
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 0;
        for (Object next : this.courseList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DataMap dataMap = (DataMap) next;
            String string = dataMap.getString(K.lessonDate);
            if (hashMap.get(string) == null) {
                Intrinsics.checkExpressionValueIsNotNull(string, "key");
                hashMap.put(string, new ArrayList());
            }
            ArrayList arrayList2 = (ArrayList) hashMap.get(string);
            if (arrayList2 != null) {
                arrayList2.add(dataMap);
            }
            if (!arrayList.contains(string)) {
                arrayList.add(string);
            }
            i = i2;
        }
        for (String str : arrayList) {
            this.mItems.add(new DateWeekday().setDate(str));
            ArrayList arrayList3 = (ArrayList) hashMap.get(str);
            if (arrayList3 != null) {
                this.mItems.addAll(arrayList3);
            }
        }
        this.multiTypeAdapter.notifyDataSetChanged();
        if (this.mItems.isEmpty()) {
            View _$_findCachedViewById = _$_findCachedViewById(R.id.noDataAndJoin);
            Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "noDataAndJoin");
            _$_findCachedViewById.setVisibility(0);
        }
    }
}
