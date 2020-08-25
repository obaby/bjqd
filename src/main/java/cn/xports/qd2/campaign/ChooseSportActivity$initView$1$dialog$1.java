package cn.xports.qd2.campaign;

import cn.xports.qd2.adapter.CampPackageAdapter;
import cn.xports.qd2.entity.CampItem;
import cn.xports.qd2.entity.CampItemListResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/qd2/entity/CampItemListResult$CampPackage;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: ChooseSportActivity.kt */
final class ChooseSportActivity$initView$1$dialog$1 implements CampPackageAdapter.OnItemClick {
    final /* synthetic */ CampItem $item;
    final /* synthetic */ ChooseSportActivity$initView$1 this$0;

    ChooseSportActivity$initView$1$dialog$1(ChooseSportActivity$initView$1 chooseSportActivity$initView$1, CampItem campItem) {
        this.this$0 = chooseSportActivity$initView$1;
        this.$item = campItem;
    }

    public final void onClick(CampItemListResult.CampPackage campPackage) {
        ChooseSportActivity chooseSportActivity = this.this$0.this$0;
        CampItem campItem = this.$item;
        Intrinsics.checkExpressionValueIsNotNull(campItem, "item");
        String valueOf = String.valueOf(campItem.getId());
        CampItem campItem2 = this.$item;
        Intrinsics.checkExpressionValueIsNotNull(campItem2, "item");
        String enrollType = campItem2.getEnrollType();
        Intrinsics.checkExpressionValueIsNotNull(enrollType, "item.enrollType");
        Intrinsics.checkExpressionValueIsNotNull(campPackage, "it");
        String id = campPackage.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
        chooseSportActivity.check(valueOf, enrollType, id);
    }
}
