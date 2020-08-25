package cn.xports.qd2.credit;

import cn.xports.baselib.bean.DataMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: CreditRuleActivity.kt */
final class CreditRuleActivity$getRule$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ CreditRuleActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CreditRuleActivity$getRule$1(CreditRuleActivity creditRuleActivity) {
        super(1);
        this.this$0 = creditRuleActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        this.this$0.rules.addAll(dataMap.getDataList("exchangeDesc"));
        this.this$0.ruleAdapter.notifyDataSetChanged();
    }
}
