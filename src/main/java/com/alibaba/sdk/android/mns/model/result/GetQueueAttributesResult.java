package com.alibaba.sdk.android.mns.model.result;

import com.alibaba.sdk.android.mns.model.MNSResult;
import com.alibaba.sdk.android.mns.model.QueueMeta;

public class GetQueueAttributesResult extends MNSResult {
    private QueueMeta queueMeta;

    public void setQueueMeta(QueueMeta queueMeta2) {
        this.queueMeta = queueMeta2;
    }

    public QueueMeta getQueueMeta() {
        return this.queueMeta;
    }
}
