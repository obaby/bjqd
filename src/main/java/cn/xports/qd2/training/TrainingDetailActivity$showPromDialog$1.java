package cn.xports.qd2.training;

import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CoursePromInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032,\u0010\u0004\u001a(\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0014\u0012\u000e\b\u0001\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u0005H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", "viewId", "", "objects", "", "", "kotlin.jvm.PlatformType", "onItemClick", "(I[Ljava/lang/Object;)V"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
final class TrainingDetailActivity$showPromDialog$1 implements BaseBottomDialog.OnItemClickListener {
    final /* synthetic */ TrainingDetailActivity this$0;

    TrainingDetailActivity$showPromDialog$1(TrainingDetailActivity trainingDetailActivity) {
        this.this$0 = trainingDetailActivity;
    }

    public final void onItemClick(int i, Object[] objArr) {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvProms);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvProms");
        String str = objArr[1];
        if (str != null) {
            textView.setText(str);
            boolean z = false;
            CoursePromInfo coursePromInfo = objArr[0];
            if (coursePromInfo != null) {
                CoursePromInfo coursePromInfo2 = coursePromInfo;
                List<CoursePromInfo.PromDetail> promDetail = coursePromInfo2.getPromDetail();
                if (promDetail != null) {
                    for (CoursePromInfo.PromDetail promDetail2 : promDetail) {
                        Intrinsics.checkExpressionValueIsNotNull(promDetail2, "it");
                        if (Intrinsics.areEqual(promDetail2.getElementType(), "1")) {
                            TrainingDetailActivity trainingDetailActivity = this.this$0;
                            String elementValue = promDetail2.getElementValue();
                            Intrinsics.checkExpressionValueIsNotNull(elementValue, "it.elementValue");
                            trainingDetailActivity.price = Integer.parseInt(elementValue);
                            this.this$0.courseJson.put("shouldPay", Integer.valueOf(this.this$0.price));
                            this.this$0.selectCourseMap.set("shouldPay", Integer.valueOf(this.this$0.price));
                            this.this$0.changePrice = false;
                        }
                        TrainingDetailActivity trainingDetailActivity2 = this.this$0;
                        String access$getGiftlist$p = trainingDetailActivity2.giftlist;
                        trainingDetailActivity2.giftlist = access$getGiftlist$p + "-" + coursePromInfo2.getPromId() + ":" + promDetail2.getElementType() + ":" + promDetail2.getElementValue() + ":" + promDetail2.getElementNum();
                    }
                }
                if (this.this$0.giftlist.length() > 0) {
                    z = true;
                }
                if (z) {
                    TrainingDetailActivity trainingDetailActivity3 = this.this$0;
                    String access$getGiftlist$p2 = this.this$0.giftlist;
                    if (access$getGiftlist$p2 != null) {
                        String substring = access$getGiftlist$p2.substring(1);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                        trainingDetailActivity3.giftlist = substring;
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type cn.xports.qd2.entity.CoursePromInfo");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }
}
