package cn.xports.qd2;

import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CardInfo;
import cn.xports.parser.OrderPayParser;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lcn/xports/entity/BalanceInfo;", "Lkotlin/collections/ArrayList;", "it", "Lcn/xports/parser/OrderPayParser;", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: SelectCardPayActivity.kt */
final class SelectCardPayActivity$getCardDetail$1<T, R> implements Function<T, R> {
    final /* synthetic */ CardInfo $cardInfo;
    final /* synthetic */ SelectCardPayActivity this$0;

    SelectCardPayActivity$getCardDetail$1(SelectCardPayActivity selectCardPayActivity, CardInfo cardInfo) {
        this.this$0 = selectCardPayActivity;
        this.$cardInfo = cardInfo;
    }

    @NotNull
    public final ArrayList<BalanceInfo> apply(@NotNull OrderPayParser orderPayParser) {
        Intrinsics.checkParameterIsNotNull(orderPayParser, "it");
        SelectCardPayActivity selectCardPayActivity = this.this$0;
        String ecardNo = this.$cardInfo.getEcardNo();
        Intrinsics.checkExpressionValueIsNotNull(ecardNo, "cardInfo.ecardNo");
        return selectCardPayActivity.createBalances(orderPayParser, ecardNo);
    }
}
