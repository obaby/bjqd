package cn.xports.qd2.campaign;

import android.support.v7.widget.CardView;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: CampaignRankActivity.kt */
final class CampaignRankActivity$getScoreRank$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ CampaignRankActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CampaignRankActivity$getScoreRank$1(CampaignRankActivity campaignRankActivity) {
        super(1);
        this.this$0 = campaignRankActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        DataMap dataMap2 = dataMap.getDataMap("campScore");
        if (!dataMap2.isEmpty()) {
            CardView cardView = (CardView) this.this$0._$_findCachedViewById(R.id.cardMyTeam);
            Intrinsics.checkExpressionValueIsNotNull(cardView, "cardMyTeam");
            cardView.setVisibility(0);
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvTeamName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvTeamName");
            textView.setText(dataMap2.getString("teamName"));
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvScore);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvScore");
            textView2.setText(dataMap2.getString("score"));
            TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvRank);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvRank");
            textView3.setText(dataMap2.getString("rank"));
        }
        this.this$0.rankList.clear();
        this.this$0.rankList.addAll(dataMap.getDataMap("campScoreRank").getDataList("list"));
        this.this$0.rankAdapter.notifyDataSetChanged();
    }
}
