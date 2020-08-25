package cn.xports.field;

import cn.xports.base.BaseBusModel;
import cn.xports.baselib.http.RxUtil;
import cn.xports.entity.OrderInfo;
import cn.xports.field.FieldBookContract;
import cn.xports.parser.BookDayParser;
import cn.xports.parser.BookRuleParser;
import cn.xports.parser.FieldParser;
import cn.xports.parser.FieldTypeParser;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.WebViewDetailActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\t\u001a\u00020\bH\u0016J.\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH\u0016J6\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016¨\u0006\u0019"}, d2 = {"Lcn/xports/field/FieldBookModel;", "Lcn/xports/base/BaseBusModel;", "Lcn/xports/field/FieldBookContract$Model;", "()V", "getBookDay", "Lio/reactivex/Observable;", "Lcn/xports/parser/BookDayParser;", "serviceId", "", "venueId", "getBookRule", "Lcn/xports/parser/BookRuleParser;", "getFieldList", "Lcn/xports/parser/FieldParser;", "date", "fieldType", "", "getFieldTypeList", "Lcn/xports/parser/FieldTypeParser;", "getVenuParam", "Lokhttp3/ResponseBody;", "paramCode", "orderField", "Lcn/xports/entity/OrderInfo;", "fieldInfo", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookModel.kt */
public final class FieldBookModel extends BaseBusModel implements FieldBookContract.Model {
    @NotNull
    public Observable<ResponseBody> getVenuParam(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Intrinsics.checkParameterIsNotNull(str2, K.paramCode);
        Observable<ResponseBody> observeOn = this.service.getVenueParams(str, str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkExpressionValueIsNotNull(observeOn, "service.getVenueParams(v…dSchedulers.mainThread())");
        return observeOn;
    }

    @NotNull
    public Observable<FieldParser> getFieldList(@NotNull String str, int i, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str3, K.venueId);
        Observable<FieldParser> compose = this.service.getFieldList(str, i, str2, str3).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getFieldList(dat…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<FieldTypeParser> getFieldTypeList(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str3, K.venueId);
        Observable<FieldTypeParser> compose = this.service.getFieldTypeList(str, str2, str3).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getFieldTypeList…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<BookDayParser> getBookDay(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str2, K.venueId);
        Observable<BookDayParser> compose = this.service.getBookDay(str, str2).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getBookDay(servi…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<BookRuleParser> getBookRule(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Observable<BookRuleParser> compose = this.service.getBookRule(str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getBookRule(venu…pose(applyErrorsWithIO())");
        return compose;
    }

    @NotNull
    public Observable<OrderInfo> orderField(@NotNull String str, int i, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str2, "fieldInfo");
        Intrinsics.checkParameterIsNotNull(str3, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str4, K.venueId);
        Observable<OrderInfo> compose = this.service.orderField(str, i, str2, str3, str4).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.orderField(date,…pose(applyErrorsWithIO())");
        return compose;
    }
}
