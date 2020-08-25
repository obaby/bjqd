package cn.xports.baselib.http;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u0001H\u0002H\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00042\u0014\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u0001H\u0002H\u00020\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "T", "kotlin.jvm.PlatformType", "Lcn/xports/baselib/bean/Response;", "upstream", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: RxUtil.kt */
final class RxUtil$applyErrors$1<Upstream, Downstream> implements ObservableTransformer<T, T> {
    public static final RxUtil$applyErrors$1 INSTANCE = new RxUtil$applyErrors$1();

    RxUtil$applyErrors$1() {
    }

    public final Observable<T> apply(@NotNull Observable<T> observable) {
        Intrinsics.checkParameterIsNotNull(observable, "upstream");
        return observable.map(new ApiResponseFunc()).onErrorResumeNext(new ExceptionHandlerFunc());
    }
}
