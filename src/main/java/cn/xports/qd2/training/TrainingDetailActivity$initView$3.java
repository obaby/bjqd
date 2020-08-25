package cn.xports.qd2.training;

import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.ChooseCourseDialog;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.TermAndLessonResult;
import com.alipay.sdk.util.h;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
final class TrainingDetailActivity$initView$3 implements View.OnClickListener {
    final /* synthetic */ TrainingDetailActivity this$0;

    TrainingDetailActivity$initView$3(TrainingDetailActivity trainingDetailActivity) {
        this.this$0 = trainingDetailActivity;
    }

    public final void onClick(View view) {
        new ChooseCourseDialog(this.this$0).setShouldPay(this.this$0.price, this.this$0.changePrice).setData(this.this$0.termAndLesson).setOnItemClickListener(new BaseBottomDialog.OnItemClickListener(this) {
            final /* synthetic */ TrainingDetailActivity$initView$3 this$0;

            {
                this.this$0 = r1;
            }

            public final void onItemClick(int i, Object[] objArr) {
                TrainingDetailActivity trainingDetailActivity = this.this$0.this$0;
                DataMap dataMap = objArr[0];
                if (dataMap != null) {
                    trainingDetailActivity.selectCourseMap = dataMap;
                    String str = objArr[1];
                    if (str != null) {
                        String str2 = str;
                        if (this.this$0.this$0.selectCourseMap.size() > 0) {
                            this.this$0.this$0.courseJson.copy(this.this$0.this$0.selectCourseMap, "termId", K.startDate);
                            if (this.this$0.this$0.selectCourseMap.hasKey("shouldPay")) {
                                this.this$0.this$0.courseJson.set("shouldPay", this.this$0.this$0.selectCourseMap.getIntValue("shouldPay"));
                            }
                            if (this.this$0.this$0.selectCourseMap.isNotEmpty("subject")) {
                                Object obj = this.this$0.this$0.selectCourseMap.get("subject");
                                if (obj != null) {
                                    this.this$0.this$0.courseJson.put("subject", CollectionsKt.arrayListOf(new TermAndLessonResult.CourseSubject[]{(TermAndLessonResult.CourseSubject) obj}));
                                    this.this$0.this$0.courseJson.put("subjectDiscount", 100);
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type cn.xports.qd2.entity.TermAndLessonResult.CourseSubject");
                                }
                            }
                        }
                        String str3 = "已选：";
                        for (String str4 : StringsKt.split$default(str2, new String[]{h.f735b}, false, 0, 6, (Object) null)) {
                            if (!Intrinsics.areEqual(str4, "")) {
                                str3 = str3 + '\"' + str4 + '\"';
                            }
                        }
                        TextView textView = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.tvCourseProp);
                        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCourseProp");
                        textView.setText(str3);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                throw new TypeCastException("null cannot be cast to non-null type cn.xports.baselib.bean.DataMap");
            }
        }).show();
    }
}
