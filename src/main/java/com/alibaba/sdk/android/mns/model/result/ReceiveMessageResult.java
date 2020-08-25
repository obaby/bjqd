package com.alibaba.sdk.android.mns.model.result;

import com.alibaba.sdk.android.mns.model.MNSResult;
import com.alibaba.sdk.android.mns.model.Message;

public class ReceiveMessageResult extends MNSResult {
    private Message message;

    public void setMessage(Message message2) {
        this.message = message2;
    }

    public Message getMessage() {
        return this.message;
    }
}
