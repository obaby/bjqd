package cn.xports.qd2;

import cn.xports.entity.BalanceInfo;
import cn.xports.parser.OrderPayParser;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a>\u0012\u0004\u0012\u00020\u0002\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005`\u00062(\u0010\u0007\u001a$\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t \n*\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\u000b0\bH\n¢\u0006\u0002\b\f"}, d2 = {"<anonymous>", "Ljava/util/HashMap;", "", "Ljava/util/ArrayList;", "Lcn/xports/entity/BalanceInfo;", "Lkotlin/collections/ArrayList;", "Lkotlin/collections/HashMap;", "cardDetails", "", "Lcn/xports/parser/OrderPayParser;", "kotlin.jvm.PlatformType", "", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: SelectCardPayActivity.kt */
final class SelectCardPayActivity$getAllDetails$2<T, R> implements Function<T, R> {
    final /* synthetic */ HashMap $balanceCards;
    final /* synthetic */ SelectCardPayActivity this$0;

    SelectCardPayActivity$getAllDetails$2(SelectCardPayActivity selectCardPayActivity, HashMap hashMap) {
        this.this$0 = selectCardPayActivity;
        this.$balanceCards = hashMap;
    }

    @NotNull
    public final HashMap<String, ArrayList<BalanceInfo>> apply(@NotNull List<OrderPayParser> list) {
        String ecardNo;
        Intrinsics.checkParameterIsNotNull(list, "cardDetails");
        for (OrderPayParser orderPayParser : list) {
            Intrinsics.checkExpressionValueIsNotNull(orderPayParser, "it");
            BalanceInfo balanceInfo = orderPayParser.getBalanceInfo();
            if (!(balanceInfo == null || (ecardNo = balanceInfo.getEcardNo()) == null)) {
                this.$balanceCards.put(ecardNo, this.this$0.createBalances(orderPayParser, ecardNo));
            }
        }
        return this.$balanceCards;
    }
}
