package cn.xports.baselib.http;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadFileInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String str = request.headers().get("isDownloadFile");
        if (!(str != null ? Boolean.valueOf(str).booleanValue() : false)) {
            return chain.proceed(chain.request());
        }
        Response proceed = chain.proceed(request);
        return proceed.newBuilder().body(new FileResponseBody(proceed)).build();
    }
}
