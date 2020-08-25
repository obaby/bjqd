package cn.xports.qd2.fragment;

import cn.xports.qd2.entity.Option;
import cn.xports.qd2.widget.DropDownView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u000b\u0012\u0002\b\u0003\u0018\u00010\u0003¨\u0006\u00010\u0003¨\u0006\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/qd2/entity/Option;", "", "kotlin.jvm.PlatformType", "position", "", "OnItemSelect"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingFragment.kt */
final class TrainingFragment$initView$1 implements DropDownView.OnItemSelectListener {
    final /* synthetic */ TrainingFragment this$0;

    TrainingFragment$initView$1(TrainingFragment trainingFragment) {
        this.this$0 = trainingFragment;
    }

    public final void OnItemSelect(Option<Object> option, int i) {
        TrainingFragment trainingFragment = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(option, "item");
        String value = option.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "item.value");
        trainingFragment.venueId = value;
        trainingFragment.getTrains(trainingFragment.pageNo);
    }
}
