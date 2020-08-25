package cn.xports.baselib.http;

import cn.xports.baselib.mvp.GlobalStaticConfig;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static volatile RetrofitUtil instance;
    private static HttpParamExtent mHttpParamExtent;
    private OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private RetrofitUtil() {
        AnonymousClass1 r0 = new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Response proceed = chain.proceed(chain.request());
                if (proceed.code() == 200) {
                    return proceed;
                }
                throw new ServerException(proceed.code());
            }
        };
        this.okHttpClient = new OkHttpClient.Builder().addInterceptor(r0).addInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                String str = request.headers().get("Authorization");
                if (str != null) {
                    Boolean.valueOf(str).booleanValue();
                }
                if (request.url().toString().contains("xports-community-api")) {
                    request = request.newBuilder().url(HttpUrl.parse(request.url().toString().replace("http://qdgx.xports.cn", "https://webssl.xports.cn"))).build();
                }
                return chain.proceed(request);
            }
        }).addInterceptor(new SignInterceptor(mHttpParamExtent)).addNetworkInterceptor(new DownloadFileInterceptor()).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();
        if (GlobalStaticConfig.getInstance().isDebug()) {
            this.okHttpClient = this.okHttpClient.newBuilder().addInterceptor(new LoggerInterceptor("", GlobalStaticConfig.getInstance().isDebug())).build();
        }
        this.retrofit = new Retrofit.Builder().baseUrl(GlobalStaticConfig.getInstance().getBaseUrl()).client(this.okHttpClient).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public static RetrofitUtil getInstance() {
        RetrofitUtil retrofitUtil = instance;
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                retrofitUtil = instance;
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil();
                    instance = retrofitUtil;
                }
            }
        }
        return retrofitUtil;
    }

    public <T> T create(Class<T> cls) {
        return this.retrofit.create(cls);
    }

    public OkHttpClient getClient() {
        return this.okHttpClient;
    }

    public static void addHttpParamExtent(HttpParamExtent httpParamExtent) {
        mHttpParamExtent = httpParamExtent;
    }

    public static List<MultipartBody.Part> getMultiparts(String str, File file, Map<String, String> map) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        String str2 = "image/png";
        if (file != null && file.getAbsolutePath().endsWith(".jpg")) {
            str2 = "image/jpeg";
        }
        type.addFormDataPart(str, file.getName(), RequestBody.create(MediaType.parse(str2), file));
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                type.addFormDataPart((String) next.getKey(), (String) next.getValue());
            }
        }
        return type.build().parts();
    }
}
