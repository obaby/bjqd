package cn.xports.qd2.circle;

import android.view.View;
import cn.xports.baselib.bean.DataMap;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CircleMemberActivity.kt */
final class CircleMemberActivity$initView$5 implements View.OnClickListener {
    final /* synthetic */ CircleMemberActivity this$0;

    CircleMemberActivity$initView$5(CircleMemberActivity circleMemberActivity) {
        this.this$0 = circleMemberActivity;
    }

    public final void onClick(View view) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (DataMap dataMap : this.this$0.members) {
            if (dataMap.getBooleanValue("checked")) {
                arrayList.add(dataMap.getString("accountId"));
                arrayList2.add(dataMap);
            }
        }
        this.this$0.removeMember(arrayList, arrayList2);
    }
}
