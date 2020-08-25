package cn.xports.baselib.http;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u0001H\u0002H\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u0001H\u0002H\u00020\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "T", "kotlin.jvm.PlatformType", "upstream", "apply"}, k = 3, mv = {1, 1, 15})
/* compiled from: RxUtil.kt */
final class RxUtil$applyIO$1<Upstream, Downstream> implements ObservableTransformer<T, T> {
    public static final RxUtil$applyIO$1 INSTANCE = new RxUtil$applyIO$1();

    RxUtil$applyIO$1() {
    }

    public final Observable<T> apply(@NotNull Observable<T> observable) {
        Intrinsics.checkParameterIsNotNull(observable, "upstream");
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
