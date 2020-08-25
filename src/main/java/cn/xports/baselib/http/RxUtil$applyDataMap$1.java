package cn.xports.baselib.http;

import cn.xports.baselib.bean.DataMap;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00050\u00050\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcn/xports/baselib/bean/DataMap;", "kotlin.jvm.PlatformType", "upstream", "Lokhttp3/ResponseBody;", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: RxUtil.kt */
final class RxUtil$applyDataMap$1<Upstream, Downstream> implements ObservableTransformer<ResponseBody, DataMap> {
    public static final RxUtil$applyDataMap$1 INSTANCE = new RxUtil$applyDataMap$1();

    RxUtil$applyDataMap$1() {
    }

    public final Observable<DataMap> apply(@NotNull Observable<ResponseBody> observable) {
        Intrinsics.checkParameterIsNotNull(observable, "upstream");
        return observable.map(AnonymousClass1.INSTANCE).onErrorResumeNext(new ExceptionHandlerFunc());
    }
}
