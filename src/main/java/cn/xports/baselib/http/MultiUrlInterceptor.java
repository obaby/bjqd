package cn.xports.baselib.http;

import cn.xports.baselib.mvp.GlobalStaticConfig;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class MultiUrlInterceptor implements Interceptor {
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String header = request.header("server");
        if (header != null && GlobalStaticConfig.getInstance().getMultiBaseUrl().containsKey(header)) {
            request = request.newBuilder().url(HttpUrl.parse(GlobalStaticConfig.getInstance().getMultiBaseUrl().get(header))).build();
        }
        return chain.proceed(request);
    }
}
