package cn.xports.qd2.campaign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.xports.qd2.entity.ItemEnrollmentDetailResult;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ViewExt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: JoinedSportsDetailActivity.kt */
final class JoinedSportsDetailActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ JoinedSportsDetailActivity this$0;

    JoinedSportsDetailActivity$initView$2(JoinedSportsDetailActivity joinedSportsDetailActivity) {
        this.this$0 = joinedSportsDetailActivity;
    }

    public final void onClick(View view) {
        ItemEnrollmentDetailResult.CampScore access$getCampScore$p = this.this$0.campScore;
        if (access$getCampScore$p != null) {
            JoinedSportsDetailActivity joinedSportsDetailActivity = this.this$0;
            Bundle bundle = new Bundle();
            String campId = access$getCampScore$p.getCampId();
            Intrinsics.checkExpressionValueIsNotNull(campId, K.campId);
            Bundle bundle2 = ViewExt.set(bundle, K.campId, campId);
            String campItemId = access$getCampScore$p.getCampItemId();
            Intrinsics.checkExpressionValueIsNotNull(campItemId, "campItemId");
            joinedSportsDetailActivity.startActivity(new Intent(joinedSportsDetailActivity, TeamRankActivity.class).putExtras(ViewExt.set(bundle2, "campItemId", campItemId)));
        }
    }
}
