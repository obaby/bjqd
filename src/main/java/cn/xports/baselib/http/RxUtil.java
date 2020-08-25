package cn.xports.baselib.http;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.mvp.IView;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0007\u001a\u001c\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00060\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0007\u001a\u0018\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00060\u0001\"\u0004\b\u0000\u0010\u0006\u001aj\u0010\n\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u000b0\u00102#\b\u0002\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000b0\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u001aj\u0010\u0018\u001a\u00020\u000b\"\b\b\u0000\u0010\u0006*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\f2\u0006\u0010\r\u001a\u00020\u000e2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u0011H\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000b0\u00102#\b\u0002\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000b0\u0010¨\u0006\u001b"}, d2 = {"applyDataMap", "Lio/reactivex/ObservableTransformer;", "Lokhttp3/ResponseBody;", "Lcn/xports/baselib/bean/DataMap;", "applyDataMapIO", "applyErrors", "T", "Lcn/xports/baselib/bean/Response;", "applyErrorsWithIO", "applyIO", "subscribeDataMapIO", "", "Lio/reactivex/Observable;", "iView", "Lcn/xports/baselib/mvp/IView;", "success", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "dataMap", "fail", "error", "showProcess", "", "subscribeProcessIO", "data", "Lcn/xports/baselib/http/ResponseThrowable;", "baselib_release"}, k = 2, mv = {1, 1, 15})
@JvmName(name = "RxUtil")
/* compiled from: RxUtil.kt */
public final class RxUtil {
    @NotNull
    public static final <T extends Response> ObservableTransformer<T, T> applyErrors() {
        return (ObservableTransformer) RxUtil$applyErrors$1.INSTANCE;
    }

    @NotNull
    public static final <T extends Response> ObservableTransformer<T, T> applyErrorsWithIO() {
        return (ObservableTransformer) RxUtil$applyErrorsWithIO$1.INSTANCE;
    }

    @NotNull
    public static final ObservableTransformer<ResponseBody, DataMap> applyDataMapIO() {
        return (ObservableTransformer) RxUtil$applyDataMapIO$1.INSTANCE;
    }

    @NotNull
    public static final ObservableTransformer<ResponseBody, DataMap> applyDataMap() {
        return (ObservableTransformer) RxUtil$applyDataMap$1.INSTANCE;
    }

    @NotNull
    public static final <T> ObservableTransformer<T, T> applyIO() {
        return (ObservableTransformer) RxUtil$applyIO$1.INSTANCE;
    }

    public static /* synthetic */ void subscribeDataMapIO$default(Observable observable, IView iView, Function1 function1, Function1 function12, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = (Function1) RxUtil$subscribeDataMapIO$1.INSTANCE;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        subscribeDataMapIO(observable, iView, function1, function12, z);
    }

    public static final void subscribeDataMapIO(@NotNull Observable<ResponseBody> observable, @NotNull IView iView, @NotNull Function1<? super DataMap, Unit> function1, @NotNull Function1<? super DataMap, Unit> function12, boolean z) {
        Intrinsics.checkParameterIsNotNull(observable, "$this$subscribeDataMapIO");
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        Intrinsics.checkParameterIsNotNull(function1, "success");
        Intrinsics.checkParameterIsNotNull(function12, "fail");
        observable.compose(applyDataMapIO()).subscribe(new RxUtil$subscribeDataMapIO$2(function1, function12, iView, z, iView, z));
    }

    public static /* synthetic */ void subscribeProcessIO$default(Observable observable, IView iView, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = (Function1) RxUtil$subscribeProcessIO$1.INSTANCE;
        }
        subscribeProcessIO(observable, iView, function1, function12);
    }

    public static final <T extends Response> void subscribeProcessIO(@NotNull Observable<T> observable, @NotNull IView iView, @NotNull Function1<? super T, Unit> function1, @NotNull Function1<? super ResponseThrowable, Unit> function12) {
        Intrinsics.checkParameterIsNotNull(observable, "$this$subscribeProcessIO");
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        Intrinsics.checkParameterIsNotNull(function1, "success");
        Intrinsics.checkParameterIsNotNull(function12, "fail");
        observable.compose(applyErrorsWithIO()).subscribe(new RxUtil$subscribeProcessIO$2(function1, function12, iView, iView));
    }
}
