package com.alibaba.sdk.android.mns.network;

import com.alibaba.sdk.android.common.CancellationHandler;
import com.alibaba.sdk.android.mns.callback.MNSCompletedCallback;
import com.alibaba.sdk.android.mns.callback.MNSProgressCallback;
import com.alibaba.sdk.android.mns.model.MNSRequest;
import okhttp3.OkHttpClient;

public class ExecutionContext<T extends MNSRequest> {
    private CancellationHandler cancellationHandler = new CancellationHandler();
    private OkHttpClient client;
    private MNSCompletedCallback completedCallback;
    private MNSProgressCallback progressCallback;
    private T request;

    public ExecutionContext(OkHttpClient okHttpClient, T t) {
        this.client = okHttpClient;
        this.request = t;
    }

    public T getRequest() {
        return this.request;
    }

    public void setRequest(T t) {
        this.request = t;
    }

    public OkHttpClient getClient() {
        return this.client;
    }

    public void setClient(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    public CancellationHandler getCancellationHandler() {
        return this.cancellationHandler;
    }

    public void setCancellationHandler(CancellationHandler cancellationHandler2) {
        this.cancellationHandler = cancellationHandler2;
    }

    public MNSCompletedCallback getCompletedCallback() {
        return this.completedCallback;
    }

    public void setCompletedCallback(MNSCompletedCallback mNSCompletedCallback) {
        this.completedCallback = mNSCompletedCallback;
    }

    public MNSProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(MNSProgressCallback mNSProgressCallback) {
        this.progressCallback = mNSProgressCallback;
    }
}
