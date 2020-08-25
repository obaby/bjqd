package com.alibaba.sdk.android.mns.model.deserialize;

import okhttp3.Response;

public interface Deserializer<T> {
    T deserialize(Response response) throws Exception;
}
