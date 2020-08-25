package cn.xports.baselib.http;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ExceptionHandlerFunc<T> implements Function<Throwable, Observable<T>> {
    public Observable<T> apply(Throwable th) throws Exception {
        return Observable.error(ExceptionHandler.handleException(th));
    }
}
