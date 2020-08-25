package cn.xports.baselib.http;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggerInterceptor implements Interceptor {
    public static final String TAG = "xports";
    private boolean showResponse;
    private String tag;

    public LoggerInterceptor(String str, boolean z) {
        str = TextUtils.isEmpty(str) ? "xports" : str;
        this.showResponse = z;
        this.tag = str;
    }

    public LoggerInterceptor(String str) {
        this(str, false);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        return logForResponse(chain.proceed(request));
    }

    private Response logForResponse(Response response) {
        ResponseBody body;
        MediaType contentType;
        try {
            Log.e(this.tag, "\n========response'log=======");
            Response build = response.newBuilder().build();
            String str = this.tag;
            Log.e(str, "url : " + build.request().url());
            String str2 = this.tag;
            Log.e(str2, "code : " + build.code());
            String str3 = this.tag;
            Log.e(str3, "protocol : " + build.protocol());
            if (!TextUtils.isEmpty(build.message())) {
                String str4 = this.tag;
                Log.e(str4, "message : " + build.message());
            }
            if (!(!this.showResponse || (body = build.body()) == null || (contentType = body.contentType()) == null)) {
                String str5 = this.tag;
                Log.e(str5, "responseBody's contentType : " + contentType.toString());
                if (isText(contentType)) {
                    String string = body.string();
                    String str6 = this.tag;
                    Log.e(str6, "responseBody's content : \n" + formatJson(string));
                    return response.newBuilder().body(ResponseBody.create(contentType, string)).build();
                }
                Log.e(this.tag, "responseBody's content :  maybe [file part] , too large too print , ignored!");
            }
            Log.e(this.tag, "========response'log=======end");
        } catch (Exception unused) {
        }
        return response;
    }

    private void logForRequest(Request request) {
        MediaType contentType;
        try {
            String httpUrl = request.url().toString();
            Headers headers = request.headers();
            Log.e(this.tag, "\n========request'log=======");
            String str = this.tag;
            Log.e(str, "method : " + request.method());
            String str2 = this.tag;
            Log.e(str2, "url : " + httpUrl);
            if (headers != null && headers.size() > 0) {
                String str3 = this.tag;
                Log.e(str3, "headers : " + headers.toString());
            }
            RequestBody body = request.body();
            if (!(body == null || (contentType = body.contentType()) == null)) {
                String str4 = this.tag;
                Log.e(str4, "requestBody's contentType : " + contentType.toString());
                if (isText(contentType)) {
                    String str5 = this.tag;
                    Log.e(str5, "requestBody's content : " + bodyToString(request));
                } else {
                    Log.e(this.tag, "requestBody's content :  maybe [file part] , too large too print , ignored!");
                }
            }
            Log.e(this.tag, "========request'log=======end");
        } catch (Exception unused) {
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() == null) {
            return false;
        }
        if (mediaType.subtype().equals("json") || mediaType.subtype().equals("xml") || mediaType.subtype().equals("html") || mediaType.subtype().equals("x-www-form-urlencoded") || mediaType.subtype().equals("webviewhtml")) {
            return true;
        }
        return false;
    }

    private String bodyToString(Request request) {
        try {
            Request build = request.newBuilder().build();
            Buffer buffer = new Buffer();
            build.body().writeTo(buffer);
            return Uri.decode(buffer.readUtf8());
        } catch (IOException unused) {
            return "something error when show requestBody.";
        }
    }

    public static String formatJson(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char c2 = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != ',') {
                if (charAt != '[') {
                    if (charAt != ']') {
                        if (charAt != '{') {
                            if (charAt != '}') {
                                sb.append(charAt);
                            }
                        }
                    }
                    sb.append(10);
                    i2--;
                    addIndentBlank(sb, i2);
                    sb.append(charAt);
                }
                sb.append(charAt);
                sb.append(10);
                i2++;
                addIndentBlank(sb, i2);
            } else {
                sb.append(charAt);
                if (c2 != '\\') {
                    sb.append(10);
                    addIndentBlank(sb, i2);
                }
            }
            i++;
            c2 = charAt;
        }
        return sb.toString();
    }

    private static void addIndentBlank(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(9);
        }
    }
}
