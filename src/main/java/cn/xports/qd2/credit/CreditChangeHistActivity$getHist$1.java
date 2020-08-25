package cn.xports.qd2.credit;

import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.WebViewDetailActivity;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: CreditChangeHistActivity.kt */
final class CreditChangeHistActivity$getHist$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ CreditChangeHistActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CreditChangeHistActivity$getHist$1(CreditChangeHistActivity creditChangeHistActivity) {
        super(1);
        this.this$0 = creditChangeHistActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        ArrayList<DataMap> dataList = dataMap.getDataMap("exchangeList").getDataList("list");
        Intrinsics.checkExpressionValueIsNotNull(dataList, "exchangeHistList");
        for (DataMap dataMap2 : dataList) {
            String string = dataMap2.getString(K.createTime);
            if (string.length() >= 10) {
                Intrinsics.checkExpressionValueIsNotNull(string, WebViewDetailActivity.DATE);
                int lastIndexOf$default = StringsKt.lastIndexOf$default(string, "-", 0, false, 6, (Object) null);
                if (string != null) {
                    String substring = string.substring(0, lastIndexOf$default);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    string = StringsKt.replace$default(substring, "-", "年", false, 4, (Object) null) + "月";
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            if (!this.this$0.exDates.contains(string)) {
                this.this$0.exDates.add(string);
                this.this$0.mItems.add(string);
            }
            this.this$0.mItems.add(dataMap2);
            this.this$0.adapter.notifyDataSetChanged();
        }
    }
}
