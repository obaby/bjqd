package anetwork.channel.interceptor;

import anet.channel.request.Request;
import java.util.concurrent.Future;

/* compiled from: Taobao */
public interface Interceptor {

    /* compiled from: Taobao */
    public interface Chain {
        Callback callback();

        Future proceed(Request request, Callback callback);

        Request request();
    }

    Future intercept(Chain chain);
}
