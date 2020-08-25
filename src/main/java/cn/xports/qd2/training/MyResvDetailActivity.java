package cn.xports.qd2.training;

import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.widget.FakeBoldText;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.blankj.utilcode.util.TimeUtils;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0005H\u0014J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcn/xports/qd2/training/MyResvDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "reserveId", "", "resvId", "cancelResv", "", "getChildTitle", "getLayoutId", "", "getResvDetail", "initData", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyResvDetailActivity.kt */
public final class MyResvDetailActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private String reserveId = "";
    private String resvId = "";

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
        return "预约详情";
    }

    public void initData() {
        this.resvId = getStringExtra(K.resvId);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_resv_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tvCancelResv)).setOnClickListener(new MyResvDetailActivity$initView$1(this));
        getResvDetail();
    }

    private final void getResvDetail() {
        ApiUtil.getApi2().getAppointDetail(this.resvId).compose(RxUtil.applyIO()).subscribe(new MyResvDetailActivity$getResvDetail$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void initData(DataMap dataMap) {
        DataMap dataMap2 = dataMap.getDataMap(K.resvDetail);
        String str = dataMap2.getString(K.courseName) + "-" + dataMap2.getString(K.lessonName);
        if (str != null) {
            if (StringsKt.endsWith$default(StringsKt.trim(str).toString(), "-", false, 2, (Object) null)) {
                str = dataMap2.getString(K.courseName);
                Intrinsics.checkExpressionValueIsNotNull(str, "detail.getString(K.courseName)");
            }
            FakeBoldText fakeBoldText = (FakeBoldText) _$_findCachedViewById(R.id.tvCourseName);
            Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tvCourseName");
            fakeBoldText.setText(str);
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvCoachName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvCoachName");
            textView.setText(dataMap2.getString(K.coachName) + "(教练)");
            String string = dataMap2.getString(K.lessonDate);
            Intrinsics.checkExpressionValueIsNotNull(string, "detail.getString(K.lessonDate)");
            String replace$default = StringsKt.replace$default(string, "-", "/", false, 4, (Object) null);
            String sb = new StringBuilder(dataMap2.getString(K.lessonStart, "0000")).insert(2, ":").toString();
            Intrinsics.checkExpressionValueIsNotNull(sb, "StringBuilder(detail.get….insert(2,\":\").toString()");
            String sb2 = new StringBuilder(dataMap2.getString(K.lessonEnd, "0000")).insert(2, ":").toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(detail.get….insert(2,\":\").toString()");
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvLessonDate);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvLessonDate");
            textView2.setText(replace$default + " " + sb + "-" + sb2 + " " + TimeUtils.getChineseWeek(replace$default, new SimpleDateFormat("yyyy/MM/dd")));
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvLessonPlace);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvLessonPlace");
            textView3.setText(dataMap2.getString(K.placeName));
            String string2 = dataMap2.getString(K.reserveId);
            Intrinsics.checkExpressionValueIsNotNull(string2, "detail.getString(K.reserveId)");
            this.reserveId = string2;
            if ((this.reserveId.length() > 0) && !getIntent().getBooleanExtra("hasFinish", false)) {
                TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvCancelResv);
                Intrinsics.checkExpressionValueIsNotNull(textView4, "tvCancelResv");
                textView4.setVisibility(0);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    /* access modifiers changed from: private */
    public final void cancelResv() {
        ApiUtil.getApi2().cancelCourseResv(this.reserveId).compose(RxUtil.applyIO()).subscribe(new MyResvDetailActivity$cancelResv$1(this, this));
    }
}
