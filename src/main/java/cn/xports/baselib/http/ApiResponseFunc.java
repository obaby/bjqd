package cn.xports.baselib.http;

import cn.xports.baselib.bean.Response;
import io.reactivex.functions.Function;

public class ApiResponseFunc<T> implements Function<Response, T> {
    public T apply(Response response) throws Exception {
        if (response.getError() == 0) {
            return response;
        }
        throw new BusinessException(response.getError(), response.getMessage());
    }
}
