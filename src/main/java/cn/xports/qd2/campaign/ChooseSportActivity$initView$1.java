package cn.xports.qd2.campaign;

import cn.xports.qd2.adapter.CampItemAdapter;
import cn.xports.qd2.dialog.SelectSportPackage;
import cn.xports.qd2.entity.CampItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/qd2/entity/CampItem;", "kotlin.jvm.PlatformType", "OnClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: ChooseSportActivity.kt */
final class ChooseSportActivity$initView$1 implements CampItemAdapter.OnItemClick {
    final /* synthetic */ ChooseSportActivity this$0;

    ChooseSportActivity$initView$1(ChooseSportActivity chooseSportActivity) {
        this.this$0 = chooseSportActivity;
    }

    public final void OnClick(CampItem campItem) {
        Intrinsics.checkExpressionValueIsNotNull(campItem, "item");
        if (campItem.getPackages().size() == 0) {
            ChooseSportActivity chooseSportActivity = this.this$0;
            String valueOf = String.valueOf(campItem.getId());
            String enrollType = campItem.getEnrollType();
            Intrinsics.checkExpressionValueIsNotNull(enrollType, "item.enrollType");
            chooseSportActivity.check(valueOf, enrollType, "");
            return;
        }
        SelectSportPackage onItemClick = new SelectSportPackage(this.this$0).setOnItemClick(new ChooseSportActivity$initView$1$dialog$1(this, campItem));
        onItemClick.show();
        onItemClick.setPackages(campItem.getPackages());
    }
}
