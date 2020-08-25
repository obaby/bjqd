package cn.xports.baselib.http;

import android.text.TextUtils;
import anet.channel.request.Request;
import cn.xports.baselib.mvp.GlobalStaticConfig;
import cn.xports.baselib.util.SPUtil;
import cn.xports.baselib.util.SignUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

public class SignInterceptor implements Interceptor {
    HttpParamExtent httpParamExtent;

    public SignInterceptor(HttpParamExtent httpParamExtent2) {
        this.httpParamExtent = httpParamExtent2;
    }

    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        HttpUrl url = request.url();
        String str = request.headers().get("Community");
        int i = 0;
        boolean booleanValue = str != null ? Boolean.valueOf(str).booleanValue() : false;
        String encodedPath = url.encodedPath();
        if (method.equals(Request.Method.GET)) {
            HashMap hashMap = new HashMap();
            ArrayList<String> arrayList = new ArrayList<>();
            while (i < url.querySize()) {
                if (TextUtils.isEmpty(url.queryParameterValue(i))) {
                    arrayList.add(url.queryParameterName(i));
                } else {
                    hashMap.put(url.queryParameterName(i), url.queryParameterValue(i));
                }
                i++;
            }
            hashMap.putAll(getPublicParams(booleanValue));
            SignUtil.wrapData(hashMap, encodedPath, booleanValue);
            HttpUrl.Builder newBuilder = request.url().newBuilder();
            for (String removeAllQueryParameters : arrayList) {
                newBuilder.removeAllQueryParameters(removeAllQueryParameters);
            }
            Set queryParameterNames = url.queryParameterNames();
            for (String str2 : hashMap.keySet()) {
                if (!queryParameterNames.contains(str2)) {
                    newBuilder.setQueryParameter(str2, (String) hashMap.get(str2));
                }
            }
            request = request.newBuilder().url(newBuilder.build()).build();
        } else if (method.equals(Request.Method.POST)) {
            if (request.body() instanceof FormBody) {
                FormBody body = request.body();
                HashMap hashMap2 = new HashMap();
                while (i < body.size()) {
                    hashMap2.put(body.name(i), body.value(i));
                    i++;
                }
                hashMap2.putAll(getPublicParams(booleanValue));
                SignUtil.wrapData(hashMap2, encodedPath, booleanValue);
                FormBody.Builder builder = new FormBody.Builder();
                for (String str3 : hashMap2.keySet()) {
                    builder.add(str3, (String) hashMap2.get(str3));
                }
                request = request.newBuilder().post(builder.build()).build();
            } else if (request.body() instanceof MultipartBody) {
                HashMap hashMap3 = new HashMap();
                List parts = request.body().parts();
                MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
                for (int size = parts.size() - 1; size >= 0; size--) {
                    RequestBody body2 = ((MultipartBody.Part) parts.get(size)).body();
                    if (body2.contentType() == null) {
                        String str4 = ((MultipartBody.Part) parts.get(size)).headers().get("Content-Disposition");
                        if (str4 != null) {
                            String replace = str4.replace("form-data; name=", "").replace("\"", "");
                            Buffer buffer = new Buffer();
                            body2.writeTo(buffer);
                            hashMap3.put(replace, buffer.readUtf8());
                        }
                    } else {
                        type.addPart((MultipartBody.Part) parts.get(size));
                    }
                }
                hashMap3.putAll(getPublicParams(booleanValue));
                SignUtil.wrapData(hashMap3, encodedPath, booleanValue);
                for (String str5 : hashMap3.keySet()) {
                    type.addPart(MultipartBody.Part.createFormData(str5, (String) hashMap3.get(str5)));
                }
                request = request.newBuilder().post(type.build()).build();
            }
        }
        return chain.proceed(request);
    }

    private Map<String, String> getPublicParams(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("apiKey", z ? GlobalStaticConfig.getInstance().getcApiKey() : GlobalStaticConfig.getInstance().getApiKey());
        hashMap.put("timestamp", Long.toString(new Date().getTime()));
        if (this.httpParamExtent != null) {
            hashMap.putAll(this.httpParamExtent.getPublicParams());
        } else {
            String stringValue = SPUtil.Companion.getINSTANCE().getStringValue("global_keys");
            if (!TextUtils.isEmpty(stringValue)) {
                for (String str : stringValue.split(",")) {
                    hashMap.put(str, SPUtil.Companion.getINSTANCE().getStringValue(str));
                }
            }
        }
        return hashMap;
    }
}
