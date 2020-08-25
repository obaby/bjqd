package cn.xports.qd2.campaign;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.TextView;
import com.blankj.utilcode.util.BarUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "nestedScrollView", "Landroid/support/v4/widget/NestedScrollView;", "scrollX", "", "scrollY", "ox", "oy", "onScrollChange"}, k = 3, mv = {1, 1, 15})
/* compiled from: SportDetailActivity.kt */
final class SportDetailActivity$initView$2 implements NestedScrollView.OnScrollChangeListener {
    final /* synthetic */ SportDetailActivity this$0;

    SportDetailActivity$initView$2(SportDetailActivity sportDetailActivity) {
        this.this$0 = sportDetailActivity;
    }

    public final void onScrollChange(@Nullable NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        if (i2 > i4) {
            View access$getRlTitleBar$p = this.this$0.rlTitleBar;
            Intrinsics.checkExpressionValueIsNotNull(access$getRlTitleBar$p, "rlTitleBar");
            if (i2 > access$getRlTitleBar$p.getHeight() + BarUtils.getStatusBarHeight()) {
                TextView access$getMTvTitle$p = this.this$0.mTvTitle;
                Intrinsics.checkExpressionValueIsNotNull(access$getMTvTitle$p, "mTvTitle");
                access$getMTvTitle$p.setText("");
            }
        } else if (i2 < i4 && i2 < 40) {
            TextView access$getMTvTitle$p2 = this.this$0.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(access$getMTvTitle$p2, "mTvTitle");
            access$getMTvTitle$p2.setText("");
        }
    }
}
