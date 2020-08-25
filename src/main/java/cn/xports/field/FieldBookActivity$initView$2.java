package cn.xports.field;

import android.view.View;
import cn.xports.entity.FieldSegment;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: FieldBookActivity.kt */
final class FieldBookActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ FieldBookActivity this$0;

    FieldBookActivity$initView$2(FieldBookActivity fieldBookActivity) {
        this.this$0 = fieldBookActivity;
    }

    public final void onClick(View view) {
        FieldBookPresenter fieldBookPresenter = (FieldBookPresenter) this.this$0.getPresenter();
        if (fieldBookPresenter != null && fieldBookPresenter.checkRules(this.this$0.fieldSegments, this.this$0.effectiveFieldSegments, this.this$0.defaultEffectiveFieldSegments, this.this$0.rule)) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.this$0.fieldSegments.iterator();
            while (it.hasNext()) {
                FieldSegment fieldSegment = (FieldSegment) it.next();
                Intrinsics.checkExpressionValueIsNotNull(fieldSegment, "segment");
                arrayList.add(fieldSegment.getFieldSegmentId());
            }
            fieldBookPresenter.orderField(this.this$0.selectDate, this.this$0.selectFieldType, arrayList, this.this$0.mServiceId, this.this$0.mVenueId);
        }
    }
}
