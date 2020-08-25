package cn.xports.qd2.training;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.StudentInfo;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.stub.StubApp;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\rH\u0002J\b\u0010\u0013\u001a\u00020\u0005H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u000fH\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcn/xports/qd2/training/AddTrainingInfoActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "custId", "", "ecardNo", "gender", "instId", "opType", "privateTag", "selfTag", "studentInfo", "Lcn/xports/qd2/entity/StudentInfo;", "addStudent", "", "editStudent", "fillInfo", "student", "getChildTitle", "getLayoutId", "", "initData", "initView", "Companion", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddTrainingInfoActivity.kt */
public final class AddTrainingInfoActivity extends BaseBussActivity<IPresenter> {
    @NotNull
    public static final String ADD = "222222";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    public static final String EDIT = "111111";
    private HashMap _$_findViewCache;
    private String custId = "";
    private String ecardNo = "";
    /* access modifiers changed from: private */
    public String gender = "";
    private String instId = "";
    /* access modifiers changed from: private */
    public String opType = ADD;
    private String privateTag = K.k0;
    private String selfTag = "";
    private StudentInfo studentInfo;

    static {
        StubApp.interface11(4160);
    }

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
        return "添加培训人";
    }

    public void initData() {
        String string = SPUtils.getInstance().getString(K.custId);
        Intrinsics.checkExpressionValueIsNotNull(string, "SPUtils.getInstance().getString(\"custId\")");
        this.custId = string;
        String string2 = SPUtils.getInstance().getString("instId");
        Intrinsics.checkExpressionValueIsNotNull(string2, "SPUtils.getInstance().getString(\"instId\")");
        this.instId = string2;
        String stringExtra = getStringExtra("editStudent");
        if (stringExtra.length() > 0) {
            this.studentInfo = (StudentInfo) GsonUtils.fromJson(stringExtra, StudentInfo.class);
        }
        this.opType = getStringExtra("opType");
        this.ecardNo = getStringExtra(K.ecardNo);
        this.privateTag = getStringExtra(K.privateTag);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_add_training_info;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.etStudentName);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etStudentName");
        ViewExt.filterBlank(editText);
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etStudentName);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etStudentName");
        ViewExt.filterSpecialChar(editText2);
        EditText editText3 = (EditText) _$_findCachedViewById(R.id.etHeight);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "etHeight");
        ViewExt.filterBlank(editText3);
        EditText editText4 = (EditText) _$_findCachedViewById(R.id.etHeight);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "etHeight");
        ViewExt.setMaxLength$default(editText4, 3, (String) null, 2, (Object) null);
        EditText editText5 = (EditText) _$_findCachedViewById(R.id.etPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText5, "etPhone");
        ViewExt.filterBlank(editText5);
        this.mIvBack.setOnClickListener(new AddTrainingInfoActivity$initView$1(this));
        if (Intrinsics.areEqual(this.opType, EDIT)) {
            TextView textView = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(textView, "mTvTitle");
            textView.setText("编辑培训人");
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvAddStudent);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvAddStudent");
            textView2.setText("保存");
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvRedTip);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvRedTip");
            textView3.setVisibility(0);
            StudentInfo studentInfo2 = this.studentInfo;
            if (studentInfo2 != null) {
                fillInfo(studentInfo2);
            }
            if (Intrinsics.areEqual(this.privateTag, "1")) {
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llTopTip);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llTopTip");
                linearLayout.setVisibility(0);
            }
        }
        ((EditText) _$_findCachedViewById(R.id.etHeight)).addTextChangedListener(new AddTrainingInfoActivity$initView$3());
        ((TextView) _$_findCachedViewById(R.id.tvAddStudent)).setOnClickListener(new AddTrainingInfoActivity$initView$4(this));
        ((TextView) _$_findCachedViewById(R.id.tvGender)).setOnClickListener(new AddTrainingInfoActivity$initView$5(this));
    }

    /* access modifiers changed from: private */
    public final void addStudent() {
        StudentInfo studentInfo2 = new StudentInfo();
        studentInfo2.setCustId(this.custId);
        EditText editText = (EditText) _$_findCachedViewById(R.id.etHeight);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etHeight");
        Editable text = editText.getText();
        String str = null;
        studentInfo2.setHeight(text != null ? text.toString() : null);
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etPhone");
        Editable text2 = editText2.getText();
        studentInfo2.setPhone(text2 != null ? text2.toString() : null);
        studentInfo2.setGender(this.gender);
        studentInfo2.setInstId(this.instId);
        EditText editText3 = (EditText) _$_findCachedViewById(R.id.etStudentName);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "etStudentName");
        Editable text3 = editText3.getText();
        if (text3 != null) {
            str = text3.toString();
        }
        studentInfo2.setStuName(str);
        if (Intrinsics.areEqual(this.privateTag, "1")) {
            this.selfTag = "1";
        }
        ApiUtil.getApi2().addStudent(GsonUtils.toJson(studentInfo2), this.ecardNo, this.selfTag, "").compose(RxUtil.applyIO()).subscribe(new AddTrainingInfoActivity$addStudent$1(this, studentInfo2, this));
    }

    /* access modifiers changed from: private */
    public final void editStudent() {
        String obj;
        StudentInfo studentInfo2 = new StudentInfo();
        StudentInfo studentInfo3 = this.studentInfo;
        String str = null;
        studentInfo2.setCustId(studentInfo3 != null ? studentInfo3.getCustId() : null);
        StudentInfo studentInfo4 = this.studentInfo;
        studentInfo2.setInstId(studentInfo4 != null ? studentInfo4.getInstId() : null);
        StudentInfo studentInfo5 = this.studentInfo;
        studentInfo2.setStuId(studentInfo5 != null ? studentInfo5.getStuId() : null);
        EditText editText = (EditText) _$_findCachedViewById(R.id.etHeight);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etHeight");
        Editable text = editText.getText();
        studentInfo2.setHeight((text == null || (obj = text.toString()) == null) ? null : StringsKt.replace$default(obj, "cm", "", false, 4, (Object) null));
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etPhone");
        Editable text2 = editText2.getText();
        studentInfo2.setPhone(text2 != null ? text2.toString() : null);
        studentInfo2.setGender(this.gender);
        EditText editText3 = (EditText) _$_findCachedViewById(R.id.etStudentName);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "etStudentName");
        Editable text3 = editText3.getText();
        if (text3 != null) {
            str = text3.toString();
        }
        studentInfo2.setStuName(str);
        ApiUtil.getApi2().editStudent(studentInfo2.getStuId(), studentInfo2.getStuName(), studentInfo2.getGender(), studentInfo2.getHeight(), studentInfo2.getPhone(), "").compose(RxUtil.applyIO()).subscribe(new AddTrainingInfoActivity$editStudent$1(this, studentInfo2, this));
    }

    private final void fillInfo(StudentInfo studentInfo2) {
        ((EditText) _$_findCachedViewById(R.id.etStudentName)).setText(studentInfo2.getStuName());
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvGender);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvGender");
        textView.setText(Intrinsics.areEqual(studentInfo2.getGender(), K.k0) ? "男" : "女");
        String height = studentInfo2.getHeight();
        Intrinsics.checkExpressionValueIsNotNull(height, "student.height");
        String replace$default = StringsKt.replace$default(height, "cm", "", false, 4, (Object) null);
        if (replace$default.length() > 3) {
            if (replace$default != null) {
                replace$default = replace$default.substring(0, 3);
                Intrinsics.checkExpressionValueIsNotNull(replace$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        ((EditText) _$_findCachedViewById(R.id.etHeight)).setText(replace$default);
        ((EditText) _$_findCachedViewById(R.id.etPhone)).setText(studentInfo2.getPhone());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcn/xports/qd2/training/AddTrainingInfoActivity$Companion;", "", "()V", "ADD", "", "EDIT", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AddTrainingInfoActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
