package cn.xports.field;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.entity.OrderInfo;
import cn.xports.field.FieldBookContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/field/FieldBookPresenter$orderField$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/OrderInfo;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookPresenter.kt */
public final class FieldBookPresenter$orderField$$inlined$apply$lambda$1 extends ProcessObserver<OrderInfo> {
    final /* synthetic */ String $date$inlined;
    final /* synthetic */ String $fieldInfo$inlined;
    final /* synthetic */ int $fieldType$inlined;
    final /* synthetic */ String $serviceId$inlined;
    final /* synthetic */ String $venueId$inlined;
    final /* synthetic */ FieldBookPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FieldBookPresenter$orderField$$inlined$apply$lambda$1(String str, FieldBookPresenter fieldBookPresenter, String str2, int i, String str3, String str4, String str5) {
        super(str);
        this.this$0 = fieldBookPresenter;
        this.$date$inlined = str2;
        this.$fieldType$inlined = i;
        this.$fieldInfo$inlined = str3;
        this.$serviceId$inlined = str4;
        this.$venueId$inlined = str5;
    }

    public void next(@Nullable OrderInfo orderInfo) {
        FieldBookContract.View view;
        if (orderInfo != null && (view = (FieldBookContract.View) this.this$0.getRootView()) != null) {
            String tradeId = orderInfo.getTradeId();
            Intrinsics.checkExpressionValueIsNotNull(tradeId, "tradeId");
            view.onGetTradeId(tradeId);
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        FieldBookContract.View view = (FieldBookContract.View) this.this$0.getRootView();
        if (view != null) {
            if (responseThrowable == null) {
                Intrinsics.throwNpe();
            }
            String message = responseThrowable.getMessage();
            if (message == null) {
                message = "";
            }
            view.showMsg(message);
        }
    }
}
