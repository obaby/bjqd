package cn.xports.qd2.circle;

import android.widget.CompoundButton;
import cn.xports.baselib.bean.DataMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 15})
/* compiled from: CircleMemberActivity.kt */
final class CircleMemberActivity$initView$3 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ CircleMemberActivity this$0;

    CircleMemberActivity$initView$3(CircleMemberActivity circleMemberActivity) {
        this.this$0 = circleMemberActivity;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        for (DataMap dataMap : this.this$0.members) {
            dataMap.set("checked", Boolean.valueOf(z));
        }
        this.this$0.adapter.notifyDataSetChanged();
    }
}
