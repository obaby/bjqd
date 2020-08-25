package cn.xports.qd2.training;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.widget.GradeLabelView;
import cn.xports.widget.RatingBar;
import com.alipay.sdk.packet.d;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SizeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0014J\b\u0010\u0012\u001a\u00020\rH\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcn/xports/qd2/training/CourseCommentActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "labelViews", "Ljava/util/ArrayList;", "Lcn/xports/qd2/widget/GradeLabelView;", "Lkotlin/collections/ArrayList;", "subMap", "Lcn/xports/baselib/bean/DataMap;", "getChildTitle", "", "getCommentGrade", "", "getLayoutId", "", "initData", "initView", "postCommentSubmit", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseCommentActivity.kt */
public final class CourseCommentActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ArrayList<GradeLabelView> labelViews = new ArrayList<>();
    /* access modifiers changed from: private */
    public DataMap subMap = new DataMap();

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
        return "课程评价";
    }

    public void initData() {
        DataMap fromJson = DataMapUtils.fromJson(getStringExtra("subMap"));
        Intrinsics.checkExpressionValueIsNotNull(fromJson, "DataMapUtils.fromJson(getStringExtra(\"subMap\"))");
        this.subMap = fromJson;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_course_comment;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((RatingBar) _$_findCachedViewById(R.id.ratingBarTotal)).showStars(SizeUtils.dp2px(8.0f)).setChangeListener(new CourseCommentActivity$initView$1(this));
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvCourseName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCourseName");
        textView.setText(this.subMap.getString(K.courseName));
        ((TextView) _$_findCachedViewById(R.id.tvSubmitComment)).setOnClickListener(new CourseCommentActivity$initView$2(this));
        getCommentGrade();
    }

    private final void getCommentGrade() {
        ApiUtil.getApi2().getCommentGradeDetail(this.subMap.getString(K.venueId)).compose(RxUtil.applyIO()).subscribe(new CourseCommentActivity$getCommentGrade$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void postCommentSubmit() {
        Integer intValue = this.subMap.getIntValue(K.grade);
        if (intValue != null && intValue.intValue() == 0) {
            showMsg("请完成所有评价");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = this.labelViews.size();
        for (int i = 0; i < size; i++) {
            GradeLabelView gradeLabelView = this.labelViews.get(i);
            Intrinsics.checkExpressionValueIsNotNull(gradeLabelView, "labelViews[i]");
            if (gradeLabelView.getStar() == 0) {
                showMsg("请完成所有评价");
                return;
            }
            GradeLabelView gradeLabelView2 = this.labelViews.get(i);
            Intrinsics.checkExpressionValueIsNotNull(gradeLabelView2, "labelViews[i]");
            arrayList.add(gradeLabelView2.getGradeSubmit());
        }
        this.subMap.put(K.commentDetail, GsonUtils.toJson(arrayList));
        DataMap dataMap = this.subMap;
        EditText editText = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etComment");
        dataMap.put("content", editText.getText());
        this.subMap.put(d.p, "4");
        this.subMap.put("objectType", "lesson_attn");
        ApiUtil.getApi2().commentSubmit(this.subMap).compose(RxUtil.applyIO()).subscribe(new CourseCommentActivity$postCommentSubmit$1(this, this));
    }
}
