package cn.xports.baselib.http;

import io.reactivex.functions.Consumer;

public interface ErrorConsumer<T> extends Consumer<T> {
    void accept(T t) throws Exception;
}
