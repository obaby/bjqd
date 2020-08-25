package cn.xports.qd2.training;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.base.GlobalDialog;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.baselib.util.RxBus;
import cn.xports.baselib.widget.FakeBoldText;
import cn.xports.entity.CardInfo;
import cn.xports.entity.PairEvent;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.ChooseCourseDialog;
import cn.xports.qd2.dialog.CoursePromDialog;
import cn.xports.qd2.dialog.CourseTipDialog;
import cn.xports.qd2.entity.CoursePromInfo;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.TermAndLessonResult;
import cn.xports.qd2.mvp.TrainingDetailPresenter;
import cn.xports.qd2.mvp.TrainingDetailView;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.widget.AddLessView;
import com.alipay.sdk.util.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010 \u001a\u00020\fH\u0014J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u0006H\u0014J\b\u0010$\u001a\u00020\"H\u0002J\b\u0010%\u001a\u00020\"H\u0002J\u0010\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020\fH\u0002J\b\u0010(\u001a\u00020\u000eH\u0002J\b\u0010)\u001a\u00020\"H\u0016J\b\u0010*\u001a\u00020\"H\u0014J\u0010\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u001fH\u0016J\b\u0010-\u001a\u00020\"H\u0002J\b\u0010.\u001a\u00020\"H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\bj\b\u0012\u0004\u0012\u00020\u001b`\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcn/xports/qd2/training/TrainingDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/qd2/mvp/TrainingDetailPresenter;", "Lcn/xports/qd2/mvp/TrainingDetailView;", "()V", "baseLessonNum", "", "cardList", "Ljava/util/ArrayList;", "Lcn/xports/entity/CardInfo;", "Lkotlin/collections/ArrayList;", "centerVenueName", "", "changePrice", "", "courseId", "courseJson", "Lcn/xports/baselib/bean/DataMap;", "courseSub", "ecardNo", "giftlist", "lat", "lng", "notNeedProm", "price", "privateTag", "proms", "Lcn/xports/qd2/entity/CoursePromInfo;", "selectCourseMap", "selectNum", "termAndLesson", "Lcn/xports/qd2/entity/TermAndLessonResult;", "getChildTitle", "getCourseDetail", "", "getLayoutId", "getProms", "getStudents", "goSignInfo", "students", "hasSelectProm", "initData", "initView", "saveTermLessonResult", "result", "showCourseTip", "showPromDialog", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
public final class TrainingDetailActivity extends BaseBussActivity<TrainingDetailPresenter> implements TrainingDetailView {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public int baseLessonNum = 1;
    /* access modifiers changed from: private */
    public ArrayList<CardInfo> cardList = new ArrayList<>();
    /* access modifiers changed from: private */
    public String centerVenueName = "";
    /* access modifiers changed from: private */
    public boolean changePrice = true;
    private String courseId = "";
    /* access modifiers changed from: private */
    public final DataMap courseJson = new DataMap();
    private String courseSub = "";
    /* access modifiers changed from: private */
    public String ecardNo = "";
    /* access modifiers changed from: private */
    public String giftlist = "";
    /* access modifiers changed from: private */
    public String lat = "";
    /* access modifiers changed from: private */
    public String lng = "";
    /* access modifiers changed from: private */
    public boolean notNeedProm;
    /* access modifiers changed from: private */
    public int price;
    /* access modifiers changed from: private */
    public String privateTag = K.k0;
    /* access modifiers changed from: private */
    public ArrayList<CoursePromInfo> proms = new ArrayList<>();
    /* access modifiers changed from: private */
    public DataMap selectCourseMap = new DataMap();
    private int selectNum = 1;
    /* access modifiers changed from: private */
    public TermAndLessonResult termAndLesson;

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
        return "";
    }

    public void initData() {
        setPresenter(new TrainingDetailPresenter(this));
        this.courseId = getStringExtra("courseId");
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(PairEvent.class).subscribe(new TrainingDetailActivity$initData$1(this)));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        underStatusBar(true, true);
        ((LinearLayout) _$_findCachedViewById(R.id.llLocation)).setOnClickListener(new TrainingDetailActivity$initView$1(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llProms)).setOnClickListener(new TrainingDetailActivity$initView$2(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llCourseProp)).setOnClickListener(new TrainingDetailActivity$initView$3(this));
        ((AddLessView) _$_findCachedViewById(R.id.addLessView)).setMinNum(1);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlSignUp)).setOnClickListener(new TrainingDetailActivity$initView$4(this));
        getCourseDetail();
        getProms();
        TrainingDetailPresenter trainingDetailPresenter = (TrainingDetailPresenter) getPresenter();
        if (trainingDetailPresenter != null) {
            trainingDetailPresenter.getTermLesson(this.courseId);
        }
    }

    public void saveTermLessonResult(@NotNull TermAndLessonResult termAndLessonResult) {
        Intrinsics.checkParameterIsNotNull(termAndLessonResult, j.f740c);
        this.termAndLesson = termAndLessonResult;
        if (ChooseCourseDialog.showClassNum(termAndLessonResult)) {
            TermAndLessonResult.SubjectPrice subjectPrice = termAndLessonResult.getSubjectPriceList().get(0);
            Intrinsics.checkExpressionValueIsNotNull(subjectPrice, "result.subjectPriceList[0]");
            List<TermAndLessonResult.CourseSubject> packageList = subjectPrice.getPackageList();
            Intrinsics.checkExpressionValueIsNotNull(packageList, "result.subjectPriceList[0].packageList");
            if (packageList.size() > 1) {
                CollectionsKt.sortWith(packageList, new TrainingDetailActivity$saveTermLessonResult$$inlined$sortBy$1());
            }
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvPrice);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvPrice");
            StringBuilder sb = new StringBuilder();
            sb.append("¥");
            TermAndLessonResult.SubjectPrice subjectPrice2 = termAndLessonResult.getSubjectPriceList().get(0);
            Intrinsics.checkExpressionValueIsNotNull(subjectPrice2, "result.subjectPriceList[0]");
            TermAndLessonResult.CourseSubject courseSubject = subjectPrice2.getPackageList().get(0);
            Intrinsics.checkExpressionValueIsNotNull(courseSubject, "result.subjectPriceList[0].packageList[0]");
            sb.append(MoneyUtil.cent2Yuan(courseSubject.getPrice()));
            textView.setText(sb.toString());
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvPercent);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvPercent");
            textView2.setText("起");
        }
    }

    private final void getCourseDetail() {
        ApiUtil.getApi2().getTrainDetail(this.courseId).compose(RxUtil.applyErrorsWithIO()).subscribe(new TrainingDetailActivity$getCourseDetail$1(this, this));
    }

    private final void getProms() {
        ApiUtil.getApi2().getCourseProms(this.courseId).compose(RxUtil.applyErrorsWithIO()).subscribe(new TrainingDetailActivity$getProms$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void showCourseTip() {
        StringBuilder sb = new StringBuilder();
        sb.append("已选课程：");
        FakeBoldText fakeBoldText = (FakeBoldText) _$_findCachedViewById(R.id.tvName);
        Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tvName");
        sb.append(fakeBoldText.getText());
        sb.append("，");
        String sb2 = sb.toString();
        if (this.selectCourseMap.containsKey("termName")) {
            sb2 = sb2 + this.selectCourseMap.getString("termName") + "，";
        }
        Integer intValue = this.selectCourseMap.getIntValue("shouldPay", this.price);
        if (!(!Intrinsics.areEqual(this.privateTag, "1"))) {
            intValue = Integer.valueOf(intValue.intValue() * ((AddLessView) _$_findCachedViewById(R.id.addLessView)).getValue());
        }
        String str = sb2 + this.selectCourseMap.getIntValue(K.lessonNum, this.baseLessonNum) + "课时，";
        String str2 = "";
        if (Intrinsics.areEqual(this.privateTag, "1")) {
            str2 = "数量：" + ((AddLessView) _$_findCachedViewById(R.id.addLessView)).getValue() + 65292;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(str2);
        sb3.append("共");
        Intrinsics.checkExpressionValueIsNotNull(intValue, "tempPrice");
        sb3.append(MoneyUtil.simpleYuan(intValue.intValue()));
        sb3.append("元，");
        new CourseTipDialog(this, sb3.toString(), this.centerVenueName).setButtonClick(new TrainingDetailActivity$showCourseTip$1(this));
    }

    /* access modifiers changed from: private */
    public final void showPromDialog() {
        if (this.proms.size() != 0) {
            new CoursePromDialog(this).setList(this.proms).setOnItemClickListener(new TrainingDetailActivity$showPromDialog$1(this)).show();
        }
    }

    /* access modifiers changed from: private */
    public final boolean hasSelectProm() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvProms);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvProms");
        if (!Intrinsics.areEqual(textView.getText(), "未选择优惠活动") || this.notNeedProm) {
            return true;
        }
        new GlobalDialog(this, "您未选择促销优惠，\n是否确认放弃？").setRightText("确认放弃").setLeftText("重新选择").setButtonClick(new TrainingDetailActivity$hasSelectProm$1(this));
        return false;
    }

    /* access modifiers changed from: private */
    public final void goSignInfo(String str) {
        this.courseJson.put("giftlist", this.giftlist);
        if (!this.courseJson.hasKey("shouldPay")) {
            this.courseJson.put("shouldPay", Integer.valueOf(this.price * ((AddLessView) _$_findCachedViewById(R.id.addLessView)).getValue()));
        }
        if (Intrinsics.areEqual(this.privateTag, "1")) {
            this.courseJson.put(K.lessonNum, Integer.valueOf(((AddLessView) _$_findCachedViewById(R.id.addLessView)).getValue()));
            CharSequence charSequence = str;
            for (int i = 0; i < charSequence.length(); i++) {
                charSequence.charAt(i);
            }
        }
        startActivity(new Intent(this, TrainingSignActivity.class).putExtra(K.ecardNo, this.ecardNo).putExtra(K.privateTag, this.privateTag).putExtra("studentList", str).putExtra("courseJson", this.courseJson.toJson()));
    }

    /* access modifiers changed from: private */
    public final void getStudents() {
        ApiUtil.getApi2().getStudentList(this.ecardNo).compose(RxUtil.applyErrorsWithIO()).subscribe(new TrainingDetailActivity$getStudents$1(this, new ArrayList(), this));
    }
}
