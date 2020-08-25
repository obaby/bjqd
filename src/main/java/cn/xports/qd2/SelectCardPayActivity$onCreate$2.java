package cn.xports.qd2;

import android.view.View;
import cn.xports.baselib.util.RxBus;
import cn.xports.entity.PairEvent;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: SelectCardPayActivity.kt */
final class SelectCardPayActivity$onCreate$2 implements View.OnClickListener {
    final /* synthetic */ SelectCardPayActivity this$0;

    SelectCardPayActivity$onCreate$2(SelectCardPayActivity selectCardPayActivity) {
        this.this$0 = selectCardPayActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.curBalanceInfo == null) {
            this.this$0.showMsg("请选择支付一卡通");
            return;
        }
        RxBus.get().post(new PairEvent(this.this$0.sourceName, this.this$0.curBalanceInfo));
        this.this$0.finish();
    }
}
