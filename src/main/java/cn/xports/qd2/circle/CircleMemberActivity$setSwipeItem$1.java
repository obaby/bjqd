package cn.xports.qd2.circle;

import cn.xports.baselib.bean.DataMap;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "menuBridge", "Lcom/yanzhenjie/recyclerview/swipe/SwipeMenuBridge;", "kotlin.jvm.PlatformType", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CircleMemberActivity.kt */
final class CircleMemberActivity$setSwipeItem$1 implements SwipeMenuItemClickListener {
    final /* synthetic */ CircleMemberActivity this$0;

    CircleMemberActivity$setSwipeItem$1(CircleMemberActivity circleMemberActivity) {
        this.this$0 = circleMemberActivity;
    }

    public final void onItemClick(SwipeMenuBridge swipeMenuBridge) {
        swipeMenuBridge.closeMenu();
        ArrayList access$getMembers$p = this.this$0.members;
        Intrinsics.checkExpressionValueIsNotNull(swipeMenuBridge, "menuBridge");
        Object obj = access$getMembers$p.get(swipeMenuBridge.getAdapterPosition());
        Intrinsics.checkExpressionValueIsNotNull(obj, "members[menuBridge.adapterPosition]");
        DataMap dataMap = (DataMap) obj;
        CircleMemberActivity circleMemberActivity = this.this$0;
        String string = dataMap.getString("accountId");
        Intrinsics.checkExpressionValueIsNotNull(string, "member.getString(\"accountId\")");
        circleMemberActivity.removeMember(CollectionsKt.arrayListOf(new String[]{string}), CollectionsKt.arrayListOf(new DataMap[]{dataMap}));
    }
}
