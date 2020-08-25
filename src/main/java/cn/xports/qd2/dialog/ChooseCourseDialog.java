package cn.xports.qd2.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.adapter.XListAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.FlowTagAdapter;
import cn.xports.qd2.entity.BeginDate;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.Option;
import cn.xports.qd2.entity.TermAndLessonResult;
import cn.xports.qd2.widget.FlowTagLayout;
import com.alipay.sdk.util.h;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class ChooseCourseDialog extends BaseBottomDialog {
    private static String NO = "0";
    private static String OK = "1";
    private FlowTagAdapter adapterClassDate;
    private FlowTagAdapter adapterClassNum;
    private FlowTagAdapter adapterPlaces;
    private FlowTagAdapter adapterStartDate;
    private FlowTagAdapter adapterTerms;
    /* access modifiers changed from: private */
    public boolean canChangePrice = true;
    /* access modifiers changed from: private */
    public int coursePrice = 0;
    /* access modifiers changed from: private */
    public TermAndLessonResult data;
    private FlowTagLayout flowClassDate;
    private FlowTagLayout flowClassNum;
    private FlowTagLayout flowPlaces;
    private GridView flowStartDate;
    private FlowTagLayout flowTerms;
    /* access modifiers changed from: private */
    public TermAndLessonResult.CourseSubject selectSubject;
    /* access modifiers changed from: private */
    public TermAndLessonResult.TcTermLesson selectTcTerm;
    /* access modifiers changed from: private */
    public BeginDate selectedDate;
    private TextView tvConfirm;
    /* access modifiers changed from: private */
    public TextView tvShouldPay;
    private View vClassDate;
    private View vClassNum;
    private View vPlaces;
    private View vStartDate;
    private View vTerms;

    public ChooseCourseDialog(@NonNull Context context) {
        super(context);
    }

    public static boolean showClassNum(TermAndLessonResult termAndLessonResult) {
        if (termAndLessonResult != null && OK.equals(termAndLessonResult.getSubjectTag()) && CollectionUtils.isNotEmpty(termAndLessonResult.getSubjectPriceList()) && CollectionUtils.isNotEmpty(termAndLessonResult.getSubjectPriceList().get(0).getPackageList())) {
            return true;
        }
        return false;
    }

    public static boolean showChooseDay(TermAndLessonResult termAndLessonResult) {
        return termAndLessonResult != null && termAndLessonResult.getChooseDays() > 0;
    }

    public static boolean showTerms(TermAndLessonResult termAndLessonResult) {
        return termAndLessonResult != null && CollectionUtils.isNotEmpty(termAndLessonResult.getTcTermList()) && OK.equals(termAndLessonResult.getClassTag());
    }

    public ChooseCourseDialog setShouldPay(int i, boolean z) {
        this.coursePrice = i;
        this.canChangePrice = z;
        TextView textView = this.tvShouldPay;
        textView.setText("¥" + MoneyUtil.cent2Yuan(i));
        return this;
    }

    public ChooseCourseDialog setData(TermAndLessonResult termAndLessonResult) {
        this.data = termAndLessonResult;
        if (!CollectionUtils.isNotEmpty(termAndLessonResult.getTcTermList()) || !OK.equals(termAndLessonResult.getClassTag())) {
            this.vTerms.setVisibility(8);
        } else {
            this.vTerms.setVisibility(0);
            final ArrayList arrayList = new ArrayList();
            CollectionUtils.forAllDo(termAndLessonResult.getTcTermList(), new CollectionUtils.Closure<TermAndLessonResult.TcTermLesson>() {
                public void execute(int i, TermAndLessonResult.TcTermLesson tcTermLesson) {
                    arrayList.add(new Option().setData(tcTermLesson).setName(tcTermLesson.getTermName()));
                }
            });
            this.adapterTerms = new FlowTagAdapter(arrayList);
            this.flowTerms.setAdapter(this.adapterTerms);
            this.adapterTerms.notifyDataSetChanged();
            this.adapterTerms.setOnItemClickListener(new XListAdapter.OnItemClickListener<Option<TermAndLessonResult.TcTermLesson>>() {
                public void onItemClick(Option<TermAndLessonResult.TcTermLesson> option, int i, int i2) {
                    if (option.isSelect()) {
                        TermAndLessonResult.TcTermLesson unused = ChooseCourseDialog.this.selectTcTerm = option.getData();
                    } else {
                        TermAndLessonResult.TcTermLesson unused2 = ChooseCourseDialog.this.selectTcTerm = null;
                    }
                }
            });
        }
        if (termAndLessonResult.getChooseDays() > 0) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < termAndLessonResult.getChooseDays(); i++) {
                BeginDate beginDate = new BeginDate(i);
                arrayList2.add(new Option().setData(beginDate).setName(beginDate.getDate()));
            }
            this.adapterStartDate = new FlowTagAdapter(arrayList2);
            this.flowStartDate.setAdapter(this.adapterStartDate);
            this.flowStartDate.setSelector(new ColorDrawable(0));
            this.adapterStartDate.notifyDataSetChanged();
            this.adapterStartDate.setOnItemClickListener(new XListAdapter.OnItemClickListener<Option<BeginDate>>() {
                public void onItemClick(Option<BeginDate> option, int i, int i2) {
                    if (option.isSelect()) {
                        BeginDate unused = ChooseCourseDialog.this.selectedDate = option.getData();
                    } else {
                        BeginDate unused2 = ChooseCourseDialog.this.selectedDate = null;
                    }
                }
            });
            this.vStartDate.setVisibility(0);
        } else {
            this.vStartDate.setVisibility(8);
        }
        if (!OK.equals(termAndLessonResult.getSubjectTag()) || !CollectionUtils.isNotEmpty(termAndLessonResult.getSubjectPriceList()) || !CollectionUtils.isNotEmpty(termAndLessonResult.getSubjectPriceList().get(0).getPackageList())) {
            this.vClassNum.setVisibility(8);
        } else {
            final ArrayList arrayList3 = new ArrayList();
            CollectionUtils.forAllDo(termAndLessonResult.getSubjectPriceList().get(0).getPackageList(), new CollectionUtils.Closure<TermAndLessonResult.CourseSubject>() {
                public void execute(int i, TermAndLessonResult.CourseSubject courseSubject) {
                    List list = arrayList3;
                    Option data = new Option().setData(courseSubject);
                    list.add(data.setName(courseSubject.getLessonNum() + "课时/" + MoneyUtil.cent2Yuan(courseSubject.getPrice()) + "元"));
                }
            });
            this.adapterClassNum = new FlowTagAdapter(arrayList3);
            this.flowClassNum.setAdapter(this.adapterClassNum);
            this.adapterClassNum.notifyDataSetChanged();
            this.adapterClassNum.setOnItemClickListener(new XListAdapter.OnItemClickListener<Option<TermAndLessonResult.CourseSubject>>() {
                public void onItemClick(Option<TermAndLessonResult.CourseSubject> option, int i, int i2) {
                    if (option.isSelect()) {
                        TermAndLessonResult.CourseSubject unused = ChooseCourseDialog.this.selectSubject = option.getData();
                        int access$300 = ChooseCourseDialog.this.coursePrice;
                        if (ChooseCourseDialog.this.canChangePrice) {
                            access$300 = ChooseCourseDialog.this.selectSubject.getPrice();
                        }
                        TextView access$500 = ChooseCourseDialog.this.tvShouldPay;
                        access$500.setText("¥" + MoneyUtil.cent2Yuan(access$300));
                        return;
                    }
                    TextView access$5002 = ChooseCourseDialog.this.tvShouldPay;
                    access$5002.setText("¥" + MoneyUtil.cent2Yuan(0));
                    TermAndLessonResult.CourseSubject unused2 = ChooseCourseDialog.this.selectSubject = null;
                }
            });
            this.vClassNum.setVisibility(0);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_choose_course);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        this.flowTerms = (FlowTagLayout) findViewById(R.id.flowTerms);
        this.flowPlaces = (FlowTagLayout) findViewById(R.id.flowPlaces);
        this.flowStartDate = (GridView) findViewById(R.id.flowStartDate);
        this.flowClassDate = (FlowTagLayout) findViewById(R.id.flowClassDate);
        this.flowClassNum = (FlowTagLayout) findViewById(R.id.flowClassNum);
        this.vTerms = findViewById(R.id.rl_terms);
        this.vPlaces = findViewById(R.id.rl_places);
        this.vStartDate = findViewById(R.id.rl_start_date);
        this.vClassDate = findViewById(R.id.rl_class_date);
        this.vClassNum = findViewById(R.id.rl_class_num);
        this.tvConfirm = (TextView) findViewById(R.id.tvSubmit);
        this.tvShouldPay = (TextView) findViewById(R.id.tvTotalMoney);
        this.tvConfirm.setText("确认");
        this.tvConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DataMap dataMap = new DataMap();
                String str = "";
                if (ChooseCourseDialog.showTerms(ChooseCourseDialog.this.data)) {
                    if (ChooseCourseDialog.this.selectTcTerm == null) {
                        ToastUtils.showShort("请选择课程期次");
                        return;
                    }
                    dataMap.put("termId", ChooseCourseDialog.this.selectTcTerm.getTermId());
                    dataMap.put("termName", ChooseCourseDialog.this.selectTcTerm.getTermName());
                    str = str + ChooseCourseDialog.this.selectTcTerm.getTermName() + h.f735b;
                }
                if (ChooseCourseDialog.showClassNum(ChooseCourseDialog.this.data)) {
                    if (ChooseCourseDialog.this.selectSubject == null) {
                        ToastUtils.showShort("请选择课时数量");
                        return;
                    }
                    str = str + ChooseCourseDialog.this.selectSubject.getLessonNum() + "课时/" + MoneyUtil.cent2Yuan(ChooseCourseDialog.this.selectSubject.getPrice()) + "元;";
                    dataMap.put("shouldPay", Integer.valueOf(ChooseCourseDialog.this.canChangePrice ? ChooseCourseDialog.this.selectSubject.getPrice() : ChooseCourseDialog.this.coursePrice));
                    dataMap.put("subject", ChooseCourseDialog.this.selectSubject);
                    dataMap.put(K.lessonNum, Integer.valueOf(ChooseCourseDialog.this.selectSubject.getLessonNum()));
                }
                if (ChooseCourseDialog.showChooseDay(ChooseCourseDialog.this.data)) {
                    if (ChooseCourseDialog.this.selectedDate == null) {
                        ToastUtils.showShort("请选择开始时间");
                        return;
                    }
                    dataMap.put(K.startDate, ChooseCourseDialog.this.selectedDate.getFormatDate());
                    str = str + ChooseCourseDialog.this.selectedDate.getDate();
                }
                ChooseCourseDialog.this.performClick(0, dataMap, str);
                ChooseCourseDialog.this.dismiss();
            }
        });
        findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseCourseDialog.this.dismiss();
            }
        });
    }
}
