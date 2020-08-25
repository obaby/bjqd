package cn.xports.field;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.field.FieldBookContract;
import cn.xports.parser.FieldParser;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n¸\u0006\u0000"}, d2 = {"cn/xports/field/FieldBookPresenter$getFieldList$1$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/FieldParser;", "next", "", "value", "onComplete", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookPresenter.kt */
public final class FieldBookPresenter$getFieldList$$inlined$apply$lambda$1 extends ProcessObserver<FieldParser> {
    final /* synthetic */ String $date$inlined;
    final /* synthetic */ int $fieldType$inlined;
    final /* synthetic */ String $serviceId$inlined;
    final /* synthetic */ String $venueId$inlined;
    final /* synthetic */ FieldBookPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FieldBookPresenter$getFieldList$$inlined$apply$lambda$1(String str, FieldBookPresenter fieldBookPresenter, String str2, int i, String str3, String str4) {
        super(str);
        this.this$0 = fieldBookPresenter;
        this.$date$inlined = str2;
        this.$fieldType$inlined = i;
        this.$serviceId$inlined = str3;
        this.$venueId$inlined = str4;
    }

    public void next(@Nullable FieldParser fieldParser) {
        FieldBookContract.View view = (FieldBookContract.View) this.this$0.getRootView();
        if (view != null) {
            view.showTopTip(fieldParser != null ? fieldParser.getServiceBookingManual() : null);
        }
        if (fieldParser != null) {
            this.this$0.drawFields(fieldParser);
            if (fieldParser != null) {
                return;
            }
        }
        FieldBookPresenter$getFieldList$$inlined$apply$lambda$1 fieldBookPresenter$getFieldList$$inlined$apply$lambda$1 = this;
        FieldBookContract.View view2 = (FieldBookContract.View) this.this$0.getRootView();
        if (view2 != null) {
            view2.showFieldView(false);
            Unit unit = Unit.INSTANCE;
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        FieldBookContract.View view = (FieldBookContract.View) this.this$0.getRootView();
        if (view != null) {
            view.hideLoading();
        }
        FieldBookContract.View view2 = (FieldBookContract.View) this.this$0.getRootView();
        if (view2 != null) {
            view2.showFieldView(false);
        }
    }

    public void onComplete() {
        super.onComplete();
        FieldBookContract.View view = (FieldBookContract.View) this.this$0.getRootView();
        if (view != null) {
            view.hideLoading();
        }
    }
}
