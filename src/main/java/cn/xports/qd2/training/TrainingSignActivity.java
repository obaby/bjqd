package cn.xports.qd2.training;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.StudentInfo;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.widget.FollowChangeView;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.GsonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\b\u0010\u0013\u001a\u00020\u0005H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\nH\u0014J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0014J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcn/xports/qd2/training/TrainingSignActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "courseJson", "", "curStudent", "Lcn/xports/qd2/entity/StudentInfo;", "ecardNo", "infoPos", "", "opType", "privateTag", "studentList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "fillInfo", "", "student", "getChildTitle", "getCourseEnrollElement", "getLayoutId", "goAddStudent", "initData", "initView", "submitPrivateTrain", "submitTrain", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingSignActivity.kt */
public final class TrainingSignActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String courseJson = "{}";
    /* access modifiers changed from: private */
    public StudentInfo curStudent;
    private String ecardNo = "";
    /* access modifiers changed from: private */
    public int infoPos;
    /* access modifiers changed from: private */
    public String opType = AddTrainingInfoActivity.ADD;
    /* access modifiers changed from: private */
    public String privateTag = K.k0;
    /* access modifiers changed from: private */
    public ArrayList<StudentInfo> studentList = new ArrayList<>();

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
        return "培训报名";
    }

    public void initData() {
        String stringExtra = getStringExtra("studentList");
        if (!TextUtils.isEmpty(stringExtra)) {
            Collection collection = (ArrayList) GsonUtils.fromJson(stringExtra, new TrainingSignActivity$initData$s$1().getType());
            if (!(collection == null || collection.isEmpty())) {
                this.studentList.addAll(collection);
            }
        }
        this.ecardNo = getStringExtra(K.ecardNo);
        this.privateTag = getStringExtra(K.privateTag);
        this.courseJson = getStringExtra("courseJson");
        if (this.studentList.isEmpty()) {
            this.opType = AddTrainingInfoActivity.ADD;
            startActivity(new Intent(this, AddTrainingInfoActivity.class).putExtra(K.privateTag, this.privateTag).putExtra(K.ecardNo, this.ecardNo).putExtra("opType", AddTrainingInfoActivity.ADD).putExtra("back", "training_sign_finish"));
        }
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(StudentInfo.class).subscribe(new TrainingSignActivity$initData$1(this)));
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(String.class).subscribe(new TrainingSignActivity$initData$2(this)));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_training_sign;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        if (Intrinsics.areEqual(this.privateTag, "1")) {
            TextView textView = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(textView, "mTvTitle");
            textView.setText("私教报名");
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llTopTip);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llTopTip");
            linearLayout.setVisibility(0);
            CornerTextView cornerTextView = (CornerTextView) _$_findCachedViewById(R.id.ctChange);
            Intrinsics.checkExpressionValueIsNotNull(cornerTextView, "ctChange");
            cornerTextView.setVisibility(8);
        }
        ((CornerTextView) _$_findCachedViewById(R.id.ctChange)).setOnClickListener(new TrainingSignActivity$initView$1(this));
        ((ImageView) _$_findCachedViewById(R.id.ivEdit)).setOnClickListener(new TrainingSignActivity$initView$2(this));
        if (this.studentList.size() > 0) {
            StudentInfo studentInfo = this.studentList.get(0);
            Intrinsics.checkExpressionValueIsNotNull(studentInfo, "studentList[0]");
            fillInfo(studentInfo);
        }
        ((TextView) _$_findCachedViewById(R.id.tvSignNew)).setOnClickListener(new TrainingSignActivity$initView$3(this));
    }

    /* access modifiers changed from: private */
    public final void fillInfo(StudentInfo studentInfo) {
        this.curStudent = studentInfo;
        ((EditText) _$_findCachedViewById(R.id.etStudentName)).setText(studentInfo.getStuName());
        ((EditText) _$_findCachedViewById(R.id.etGender)).setText(Intrinsics.areEqual(studentInfo.getGender(), K.k0) ? "男" : "女");
        String height = studentInfo.getHeight();
        if (height.length() > 3) {
            Intrinsics.checkExpressionValueIsNotNull(height, "h");
            if (height != null) {
                height = height.substring(0, 3);
                Intrinsics.checkExpressionValueIsNotNull(height, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        ((EditText) _$_findCachedViewById(R.id.etHeight)).setText(height);
        ((EditText) _$_findCachedViewById(R.id.etPhone)).setText(studentInfo.getPhone());
    }

    /* access modifiers changed from: private */
    public final void goAddStudent(String str) {
        this.opType = str;
        startActivity(new Intent(this, AddTrainingInfoActivity.class).putExtra(K.privateTag, this.privateTag).putExtra(K.ecardNo, this.ecardNo).putExtra("opType", str).putExtra("editStudent", GsonUtils.toJson(this.curStudent, false)));
    }

    private final void getCourseEnrollElement() {
        DataMap dataMap = (DataMap) GsonUtils.fromJson(this.courseJson, DataMap.class);
        ApiUtil.getApi2().getCourseEnrollElement(dataMap != null ? dataMap.getString("courseId") : null).compose(RxUtil.applyErrorsWithIO()).subscribe(new TrainingSignActivity$getCourseEnrollElement$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void submitTrain() {
        DataMap dataMap = new DataMap();
        dataMap.set(K.ecardNo, this.ecardNo).set("courseJson", this.courseJson);
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llAddMember);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llAddMember");
        if (linearLayout.getVisibility() == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            FollowChangeView followChangeView = (FollowChangeView) _$_findCachedViewById(R.id.followChangeView);
            Intrinsics.checkExpressionValueIsNotNull(followChangeView, "followChangeView");
            sb.append(followChangeView.getSubmitJson());
            sb.append("]");
            dataMap.set("elementInfo", sb.toString());
        }
        ApiUtil.getApi2().submitTraining(dataMap).compose(RxUtil.applyIO()).subscribe(new TrainingSignActivity$submitTrain$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void submitPrivateTrain() {
        ApiUtil.getApi2().submitPrivateTraining(this.ecardNo, this.courseJson).compose(RxUtil.applyIO()).subscribe(new TrainingSignActivity$submitPrivateTrain$1(this, this));
    }
}
