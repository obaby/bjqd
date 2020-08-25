package cn.xports.qd2.campaign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ViewExt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: SportDetailActivity.kt */
final class SportDetailActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ SportDetailActivity this$0;

    SportDetailActivity$initView$1(SportDetailActivity sportDetailActivity) {
        this.this$0 = sportDetailActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.hasFinish) {
            SportDetailActivity sportDetailActivity = this.this$0;
            sportDetailActivity.startActivity(new Intent(sportDetailActivity, CampaignRankActivity.class).putExtras(ViewExt.set(new Bundle(), "campItemJson", this.this$0.campItemJson)));
            return;
        }
        Intent intent = new Intent(this.this$0, ChooseSportActivity.class);
        intent.putExtra(K.campId, this.this$0.campId);
        this.this$0.startActivity(intent);
    }
}
