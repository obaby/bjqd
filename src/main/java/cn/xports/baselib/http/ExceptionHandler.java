package cn.xports.baselib.http;

import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.PointerIconCompat;
import com.google.gson.JsonParseException;
import org.json.JSONException;

public class ExceptionHandler {
    public static ResponseThrowable handleException(Throwable th) {
        if (th instanceof BusinessException) {
            BusinessException businessException = (BusinessException) th;
            return new ResponseThrowable(th, businessException.errorCode, businessException.message).setDataMap(businessException.getDataMap());
        } else if (th instanceof ServerException) {
            ServerException serverException = (ServerException) th;
            return new ResponseThrowable(th, serverException.statusCode, serverException.message);
        } else if ((th instanceof JSONException) || (th instanceof NumberFormatException) || (th instanceof JsonParseException)) {
            return new ResponseThrowable(th, PointerIconCompat.TYPE_CONTEXT_MENU, "解析错误", th.toString());
        } else {
            return new ResponseThrowable(th, 1000, EnvironmentCompat.MEDIA_UNKNOWN, th.toString());
        }
    }
}
