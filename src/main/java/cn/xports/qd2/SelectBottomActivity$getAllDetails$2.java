package cn.xports.qd2;

import cn.xports.entity.BalanceInfo;
import cn.xports.entity.DepositInfo;
import cn.xports.qd2.entity.CardDetailResult;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a>\u0012\u0004\u0012\u00020\u0002\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00050\u0001j\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005`\u00062(\u0010\u0007\u001a$\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t \n*\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\u000b0\bH\n¢\u0006\u0002\b\f"}, d2 = {"<anonymous>", "Ljava/util/HashMap;", "", "Ljava/util/ArrayList;", "Lcn/xports/entity/BalanceInfo;", "Lkotlin/collections/ArrayList;", "Lkotlin/collections/HashMap;", "cardDetails", "", "Lcn/xports/qd2/entity/CardDetailResult;", "kotlin.jvm.PlatformType", "", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: SelectBottomActivity.kt */
final class SelectBottomActivity$getAllDetails$2<T, R> implements Function<T, R> {
    final /* synthetic */ HashMap $balanceCards;

    SelectBottomActivity$getAllDetails$2(HashMap hashMap) {
        this.$balanceCards = hashMap;
    }

    @NotNull
    public final HashMap<String, ArrayList<BalanceInfo>> apply(@NotNull List<CardDetailResult> list) {
        Intrinsics.checkParameterIsNotNull(list, "cardDetails");
        for (CardDetailResult cardDetailResult : list) {
            ArrayList arrayList = new ArrayList();
            BalanceInfo name = new BalanceInfo().setName("一卡通账户");
            Intrinsics.checkExpressionValueIsNotNull(cardDetailResult, "it");
            arrayList.add(name.setBalance((int) cardDetailResult.getEcardBalance()));
            List<DepositInfo> depositInfo = cardDetailResult.getDepositInfo();
            if (depositInfo != null) {
                for (DepositInfo depositInfo2 : depositInfo) {
                    BalanceInfo balanceInfo = new BalanceInfo();
                    Intrinsics.checkExpressionValueIsNotNull(depositInfo2, "deposit");
                    arrayList.add(balanceInfo.setName(depositInfo2.getDepositName()).setBalance(depositInfo2.getBalance()).setLimitType(depositInfo2.getLimitType()));
                }
            }
            this.$balanceCards.put(cardDetailResult.getEcardNo(), arrayList);
        }
        return this.$balanceCards;
    }
}
