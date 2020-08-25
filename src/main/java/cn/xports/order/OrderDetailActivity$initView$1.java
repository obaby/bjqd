package cn.xports.order;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.qdplugin.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderDetailActivity.kt */
final class OrderDetailActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ OrderDetailActivity this$0;

    OrderDetailActivity$initView$1(OrderDetailActivity orderDetailActivity) {
        this.this$0 = orderDetailActivity;
    }

    public final void onClick(View view) {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvOption);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvOption");
        if (Intrinsics.areEqual(textView.getText(), "退票")) {
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvOption);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvOption");
            textView2.setText("取消");
            ((TextView) this.this$0._$_findCachedViewById(R.id.tvOption)).setBackgroundColor(-1);
            RelativeLayout relativeLayout = (RelativeLayout) this.this$0._$_findCachedViewById(R.id.rlPayOption);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlPayOption");
            relativeLayout.setVisibility(0);
            Button button = (Button) this.this$0._$_findCachedViewById(R.id.btnPayOption);
            Intrinsics.checkExpressionValueIsNotNull(button, "btnPayOption");
            button.setText("确认退票");
            for (ItemOrderWrap showSelect : this.this$0.orderItems) {
                showSelect.showSelect(true);
            }
            return;
        }
        ((TextView) this.this$0._$_findCachedViewById(R.id.tvOption)).setBackgroundResource(R.drawable.bg_blue_stroke_round);
        TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvOption);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvOption");
        textView3.setText("退票");
        RelativeLayout relativeLayout2 = (RelativeLayout) this.this$0._$_findCachedViewById(R.id.rlPayOption);
        Intrinsics.checkExpressionValueIsNotNull(relativeLayout2, "rlPayOption");
        relativeLayout2.setVisibility(8);
        for (ItemOrderWrap showSelect2 : this.this$0.orderItems) {
            showSelect2.showSelect(false);
        }
    }
}
