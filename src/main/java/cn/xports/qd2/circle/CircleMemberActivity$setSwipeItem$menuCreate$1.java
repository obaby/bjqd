package cn.xports.qd2.circle;

import cn.xports.qd2.R;
import com.blankj.utilcode.util.SizeUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/yanzhenjie/recyclerview/swipe/SwipeMenu;", "kotlin.jvm.PlatformType", "rightMenu", "<anonymous parameter 2>", "", "onCreateMenu"}, k = 3, mv = {1, 1, 15})
/* compiled from: CircleMemberActivity.kt */
final class CircleMemberActivity$setSwipeItem$menuCreate$1 implements SwipeMenuCreator {
    final /* synthetic */ CircleMemberActivity this$0;

    CircleMemberActivity$setSwipeItem$menuCreate$1(CircleMemberActivity circleMemberActivity) {
        this.this$0 = circleMemberActivity;
    }

    public final void onCreateMenu(SwipeMenu swipeMenu, SwipeMenu swipeMenu2, int i) {
        SwipeMenuItem height = new SwipeMenuItem(this.this$0).setBackground(R.drawable.sel_red).setText("移除").setTextColor(-1).setTextSize(16).setWidth(SizeUtils.dp2px(70.0f)).setHeight(-1);
        if (swipeMenu2 != null) {
            swipeMenu2.addMenuItem(height);
        }
    }
}
