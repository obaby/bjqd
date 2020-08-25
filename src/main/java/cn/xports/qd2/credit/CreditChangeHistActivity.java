package cn.xports.qd2.credit;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CreditUseHistBinder;
import cn.xports.qd2.adapter.TextServiceBinder;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\bH\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0007j\b\u0012\u0004\u0012\u00020\u000b`\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcn/xports/qd2/credit/CreditChangeHistActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "adapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "exDates", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "exHist", "Lcn/xports/baselib/bean/DataMap;", "mItems", "Lme/drakeet/multitype/Items;", "mapStr", "queryMap", "getChildTitle", "getHist", "", "getLayoutId", "", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CreditChangeHistActivity.kt */
public final class CreditChangeHistActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final MultiTypeAdapter adapter = new MultiTypeAdapter(this.mItems);
    /* access modifiers changed from: private */
    public final ArrayList<String> exDates = new ArrayList<>();
    private final ArrayList<DataMap> exHist = new ArrayList<>();
    /* access modifiers changed from: private */
    public final Items mItems = new Items();
    private String mapStr = "";
    private DataMap queryMap = new DataMap();

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
        return "积分兑换明细";
    }

    public void initData() {
        this.mapStr = getStringExtra("queryMap");
        DataMap fromJson = DataMapUtils.fromJson(this.mapStr);
        Intrinsics.checkExpressionValueIsNotNull(fromJson, "DataMapUtils.fromJson(mapStr)");
        this.queryMap = fromJson;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_credit_hist;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditHist);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCreditHist");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.adapter.register(String.class, new TextServiceBinder().setFontSize(12).setHeight(SizeUtils.dp2px(32.0f)).setBackgroundColor(ColorUtils.getColor(R.color.gray_f8f)).setTextColor(ColorUtils.getColor(R.color.second_text_color)));
        this.adapter.register(DataMap.class, new CreditUseHistBinder());
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditHist);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCreditHist");
        emptyRecyclerView2.setAdapter(this.adapter);
        getHist();
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditHist);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCreditHist");
        ViewExt.setEmptyView$default(emptyRecyclerView3, "暂无兑换记录", (String) null, (Function0) null, 6, (Object) null);
    }

    private final void getHist() {
        Observable<ResponseBody> pointExchangeHist = ApiUtil.getApi2().getPointExchangeHist(this.queryMap);
        Intrinsics.checkExpressionValueIsNotNull(pointExchangeHist, "ApiUtil.getApi2()\n      …intExchangeHist(queryMap)");
        RxUtil.subscribeDataMapIO$default(pointExchangeHist, this, new CreditChangeHistActivity$getHist$1(this), (Function1) null, false, 12, (Object) null);
    }
}
