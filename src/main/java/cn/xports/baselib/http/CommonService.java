package cn.xports.baselib.http;

import cn.xports.baselib.bean.AppVersionParser;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface CommonService {
    @FormUrlEncoded
    @POST("http://218.244.136.36/aisports-api/app-api/open/addCrashLog")
    Observable<Callback> addCrashLog(@Field("crashInfo") String str, @Field("appId") int i);

    @Streaming
    @GET
    @Headers({"Authorization: false", "isDownloadFile: true"})
    Observable<ResponseBody> downloadApk(@Url String str);

    @GET("/aisports-api/api/public/app/update")
    @Headers({"community:true"})
    Observable<AppVersionParser> getAppVersion(@Query("app") String str);
}
