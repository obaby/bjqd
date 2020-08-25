package com.alibaba.sdk.android.oss.internal;

import java.io.IOException;
import okhttp3.Response;

public interface ResponseParser<T> {
    T parse(Response response) throws IOException;
}
