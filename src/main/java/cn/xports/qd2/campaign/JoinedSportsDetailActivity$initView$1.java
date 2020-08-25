package cn.xports.qd2.campaign;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch"}, k = 3, mv = {1, 1, 15})
/* compiled from: JoinedSportsDetailActivity.kt */
final class JoinedSportsDetailActivity$initView$1 implements View.OnTouchListener {
    final /* synthetic */ JoinedSportsDetailActivity this$0;

    JoinedSportsDetailActivity$initView$1(JoinedSportsDetailActivity joinedSportsDetailActivity) {
        this.this$0 = joinedSportsDetailActivity;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkExpressionValueIsNotNull(motionEvent, "event");
        if (motionEvent.getAction() != 1) {
            return false;
        }
        ((RelativeLayout) this.this$0._$_findCachedViewById(R.id.rlAddMember)).performClick();
        return false;
    }
}
