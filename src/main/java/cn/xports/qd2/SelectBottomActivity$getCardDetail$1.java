package cn.xports.qd2;

import cn.xports.entity.BalanceInfo;
import cn.xports.entity.DepositInfo;
import cn.xports.qd2.entity.CardDetailResult;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lcn/xports/entity/BalanceInfo;", "Lkotlin/collections/ArrayList;", "it", "Lcn/xports/qd2/entity/CardDetailResult;", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: SelectBottomActivity.kt */
final class SelectBottomActivity$getCardDetail$1<T, R> implements Function<T, R> {
    public static final SelectBottomActivity$getCardDetail$1 INSTANCE = new SelectBottomActivity$getCardDetail$1();

    SelectBottomActivity$getCardDetail$1() {
    }

    @NotNull
    public final ArrayList<BalanceInfo> apply(@NotNull CardDetailResult cardDetailResult) {
        Intrinsics.checkParameterIsNotNull(cardDetailResult, "it");
        ArrayList<BalanceInfo> arrayList = new ArrayList<>();
        arrayList.add(new BalanceInfo().setName("一卡通账户").setBalance((int) cardDetailResult.getEcardBalance()));
        List<DepositInfo> depositInfo = cardDetailResult.getDepositInfo();
        if (depositInfo != null) {
            for (DepositInfo depositInfo2 : depositInfo) {
                BalanceInfo balanceInfo = new BalanceInfo();
                Intrinsics.checkExpressionValueIsNotNull(depositInfo2, "deposit");
                arrayList.add(balanceInfo.setName(depositInfo2.getDepositName()).setBalance(depositInfo2.getBalance()).setLimitType(depositInfo2.getLimitType()));
            }
        }
        return arrayList;
    }
}
