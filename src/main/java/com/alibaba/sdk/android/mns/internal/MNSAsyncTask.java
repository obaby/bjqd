package com.alibaba.sdk.android.mns.internal;

import com.alibaba.sdk.android.common.ClientException;
import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.model.MNSResult;
import com.alibaba.sdk.android.mns.network.ExecutionContext;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MNSAsyncTask<T extends MNSResult> {
    private volatile boolean canceled;
    private ExecutionContext context;
    private Future<T> future;

    public void cancel() {
        this.canceled = true;
        if (this.context != null) {
            this.context.getCancellationHandler().cancel();
        }
    }

    public boolean isCompleted() {
        return this.future.isDone();
    }

    public T getResult() throws ClientException, ServiceException {
        try {
            return (MNSResult) this.future.get();
        } catch (InterruptedException e) {
            throw new ClientException(e.getMessage(), e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof ClientException) {
                throw ((ClientException) cause);
            } else if (cause instanceof ServiceException) {
                throw ((ServiceException) cause);
            } else {
                cause.printStackTrace();
                throw new ClientException("Unexpected exception!" + cause.getMessage());
            }
        }
    }

    public static MNSAsyncTask wrapRequestTask(Future future2, ExecutionContext executionContext) {
        MNSAsyncTask mNSAsyncTask = new MNSAsyncTask();
        mNSAsyncTask.future = future2;
        mNSAsyncTask.context = executionContext;
        return mNSAsyncTask;
    }

    public void waitUntilFinished() {
        try {
            this.future.get();
        } catch (Exception unused) {
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }
}
