package cn.xports.qd2.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import cn.xports.base.BaseBussFragment;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.http.RxUtil;
import cn.xports.entity.Service;
import cn.xports.entity.Venue;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.PersonalTrainBinder;
import cn.xports.qd2.adapter.TrainMapBinder;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.Option;
import cn.xports.qd2.entity.TrainItem;
import cn.xports.qd2.entity.VenueLocationItem;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.qd2.widget.DropDownView;
import cn.xports.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\bH\u0014J\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\bH\u0002J\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00100\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcn/xports/qd2/fragment/TrainingFragment;", "Lcn/xports/base/BaseBussFragment;", "()V", "adapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "items", "Lme/drakeet/multitype/Items;", "pageNo", "", "privateTag", "", "rvTrainList", "Lcn/xports/widget/EmptyRecyclerView;", "serviceId", "serviceList", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/Option;", "Lcn/xports/entity/Service;", "suitPerson", "suitPersonList", "totalPage", "vCourseLocation", "Lcn/xports/qd2/widget/DropDownView;", "vPersonRange", "vSportType", "venueId", "venueList", "Lcn/xports/entity/Venue;", "getFilter", "", "getLayoutId", "getTrains", "page", "initView", "holder", "Lcn/xports/baselib/adapter/XBaseHolder;", "Companion", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingFragment.kt */
public final class TrainingFragment extends BaseBussFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final MultiTypeAdapter adapter = new MultiTypeAdapter();
    /* access modifiers changed from: private */
    public final Items items = new Items();
    /* access modifiers changed from: private */
    public int pageNo = 1;
    private String privateTag = "";
    private EmptyRecyclerView rvTrainList;
    /* access modifiers changed from: private */
    public String serviceId = "";
    /* access modifiers changed from: private */
    public final ArrayList<Option<Service>> serviceList = new ArrayList<>();
    /* access modifiers changed from: private */
    public String suitPerson = "";
    /* access modifiers changed from: private */
    public final ArrayList<Option<String>> suitPersonList = new ArrayList<>();
    /* access modifiers changed from: private */
    public int totalPage = 1;
    /* access modifiers changed from: private */
    public DropDownView vCourseLocation;
    /* access modifiers changed from: private */
    public DropDownView vPersonRange;
    /* access modifiers changed from: private */
    public DropDownView vSportType;
    /* access modifiers changed from: private */
    public String venueId = "";
    /* access modifiers changed from: private */
    public final ArrayList<Option<Venue>> venueList = new ArrayList<>();

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.fragment_train_list;
    }

    /* access modifiers changed from: protected */
    public void initView(@NotNull XBaseHolder xBaseHolder) {
        String str;
        Intrinsics.checkParameterIsNotNull(xBaseHolder, "holder");
        this.rvTrainList = (EmptyRecyclerView) xBaseHolder.getView(R.id.rv_train);
        EmptyRecyclerView emptyRecyclerView = this.rvTrainList;
        if (emptyRecyclerView != null) {
            emptyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        this.adapter.register(TrainItem.class, new PersonalTrainBinder());
        this.adapter.register(VenueLocationItem.class, new TrainMapBinder());
        this.adapter.setItems(this.items);
        EmptyRecyclerView emptyRecyclerView2 = this.rvTrainList;
        if (emptyRecyclerView2 != null) {
            emptyRecyclerView2.setAdapter(this.adapter);
        }
        this.vCourseLocation = (DropDownView) xBaseHolder.getView(R.id.course_location);
        this.vSportType = (DropDownView) xBaseHolder.getView(R.id.sport_type);
        this.vPersonRange = (DropDownView) xBaseHolder.getView(R.id.person_range);
        DropDownView dropDownView = this.vCourseLocation;
        if (dropDownView != null) {
            dropDownView.setTitle("上课地点");
        }
        DropDownView dropDownView2 = this.vSportType;
        if (dropDownView2 != null) {
            dropDownView2.setTitle("运动项目");
        }
        DropDownView dropDownView3 = this.vPersonRange;
        if (dropDownView3 != null) {
            dropDownView3.setTitle("全部年龄");
        }
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            if (arguments == null || (str = arguments.getString(K.privateTag, "")) == null) {
                str = "";
            }
            this.privateTag = str;
        }
        DropDownView dropDownView4 = this.vCourseLocation;
        if (dropDownView4 != null) {
            dropDownView4.setOnItemSelectListener(new TrainingFragment$initView$1(this));
        }
        DropDownView dropDownView5 = this.vSportType;
        if (dropDownView5 != null) {
            dropDownView5.setOnItemSelectListener(new TrainingFragment$initView$2(this));
        }
        DropDownView dropDownView6 = this.vPersonRange;
        if (dropDownView6 != null) {
            dropDownView6.setOnItemSelectListener(new TrainingFragment$initView$3(this));
        }
        getFilter();
        EmptyRecyclerView emptyRecyclerView3 = this.rvTrainList;
        if (emptyRecyclerView3 != null) {
            ViewExt.loadMore(emptyRecyclerView3, new TrainingFragment$initView$4(this), new TrainingFragment$initView$5(this), TrainingFragment$initView$6.INSTANCE);
        }
        EmptyRecyclerView emptyRecyclerView4 = this.rvTrainList;
        if (emptyRecyclerView4 != null) {
            EmptyRecyclerView emptyRecyclerView5 = this.rvTrainList;
            emptyRecyclerView4.setEmptyView(emptyRecyclerView5 != null ? ViewExt.getEmptyView(emptyRecyclerView5, "暂无课程！") : null);
        }
        getTrains(this.pageNo);
    }

    /* access modifiers changed from: private */
    public final void getTrains(int i) {
        ApiUtil.getApi2().getTrainings(i, 999, this.venueId, this.serviceId, this.suitPerson, this.privateTag).compose(RxUtil.applyErrorsWithIO()).subscribe(new TrainingFragment$getTrains$1(this, i, this));
    }

    private final void getFilter() {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        api2.getFilterParam().compose(RxUtil.applyIO()).subscribe(new TrainingFragment$getFilter$1(this, this));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcn/xports/qd2/fragment/TrainingFragment$Companion;", "", "()V", "newInstance", "Lcn/xports/qd2/fragment/TrainingFragment;", "privateTag", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: TrainingFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TrainingFragment newInstance(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, K.privateTag);
            Bundle bundle = new Bundle();
            bundle.putString(K.privateTag, str);
            TrainingFragment trainingFragment = new TrainingFragment();
            trainingFragment.setArguments(bundle);
            return trainingFragment;
        }
    }
}
