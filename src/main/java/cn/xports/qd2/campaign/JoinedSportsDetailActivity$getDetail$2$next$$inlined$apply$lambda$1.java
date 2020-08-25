package cn.xports.qd2.campaign;

import android.content.Intent;
import android.view.View;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.TeamInfoResult;
import com.blankj.utilcode.util.GsonUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/qd2/campaign/JoinedSportsDetailActivity$getDetail$2$next$1$4"}, k = 3, mv = {1, 1, 15})
/* compiled from: JoinedSportsDetailActivity.kt */
final class JoinedSportsDetailActivity$getDetail$2$next$$inlined$apply$lambda$1 implements View.OnClickListener {
    final /* synthetic */ TeamInfoResult $this_apply;
    final /* synthetic */ JoinedSportsDetailActivity$getDetail$2 this$0;

    JoinedSportsDetailActivity$getDetail$2$next$$inlined$apply$lambda$1(TeamInfoResult teamInfoResult, JoinedSportsDetailActivity$getDetail$2 joinedSportsDetailActivity$getDetail$2) {
        this.$this_apply = teamInfoResult;
        this.this$0 = joinedSportsDetailActivity$getDetail$2;
    }

    public final void onClick(View view) {
        String access$getExState$p = this.this$0.this$0.exState;
        if (access$getExState$p == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        } else if (!Intrinsics.areEqual(StringsKt.trim(access$getExState$p).toString(), "支付超时")) {
            String access$getExState$p2 = this.this$0.this$0.exState;
            if (access$getExState$p2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            } else if (!Intrinsics.areEqual(StringsKt.trim(access$getExState$p2).toString(), "已取消")) {
                this.this$0.this$0.startActivity(new Intent(this.this$0.this$0, TeamInfoActivity.class).putExtra("teamInfo", GsonUtils.toJson(this.$this_apply)).putExtra("readOnly", this.this$0.this$0.readOnly).putExtra(K.campId, this.this$0.this$0.campId));
            }
        }
    }
}
