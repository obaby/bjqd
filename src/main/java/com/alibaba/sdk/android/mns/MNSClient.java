package com.alibaba.sdk.android.mns;

import android.content.Context;
import anet.channel.util.HttpConstant;
import com.alibaba.sdk.android.common.ClientConfiguration;
import com.alibaba.sdk.android.common.ClientException;
import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.common.auth.CredentialProvider;
import com.alibaba.sdk.android.mns.callback.MNSCompletedCallback;
import com.alibaba.sdk.android.mns.internal.MNSAsyncTask;
import com.alibaba.sdk.android.mns.internal.MNSInternalRequestOperation;
import com.alibaba.sdk.android.mns.model.request.ChangeMessageVisibilityRequest;
import com.alibaba.sdk.android.mns.model.request.CreateQueueRequest;
import com.alibaba.sdk.android.mns.model.request.DeleteMessageRequest;
import com.alibaba.sdk.android.mns.model.request.DeleteQueueRequest;
import com.alibaba.sdk.android.mns.model.request.GetQueueAttributesRequest;
import com.alibaba.sdk.android.mns.model.request.ListQueueRequest;
import com.alibaba.sdk.android.mns.model.request.PeekMessageRequest;
import com.alibaba.sdk.android.mns.model.request.ReceiveMessageRequest;
import com.alibaba.sdk.android.mns.model.request.SendMessageRequest;
import com.alibaba.sdk.android.mns.model.request.SetQueueAttributesRequest;
import com.alibaba.sdk.android.mns.model.result.ChangeMessageVisibilityResult;
import com.alibaba.sdk.android.mns.model.result.CreateQueueResult;
import com.alibaba.sdk.android.mns.model.result.DeleteMessageResult;
import com.alibaba.sdk.android.mns.model.result.DeleteQueueResult;
import com.alibaba.sdk.android.mns.model.result.GetQueueAttributesResult;
import com.alibaba.sdk.android.mns.model.result.ListQueueResult;
import com.alibaba.sdk.android.mns.model.result.PeekMessageResult;
import com.alibaba.sdk.android.mns.model.result.ReceiveMessageResult;
import com.alibaba.sdk.android.mns.model.result.SendMessageResult;
import com.alibaba.sdk.android.mns.model.result.SetQueueAttributesResult;
import java.net.URI;
import java.net.URISyntaxException;

public class MNSClient implements MNS {
    private ClientConfiguration conf;
    private CredentialProvider credentialProvider;
    private URI endpointURI;
    private MNSInternalRequestOperation internalRequestOperation;

    public MNSClient(Context context, String str, CredentialProvider credentialProvider2) {
        this(context, str, credentialProvider2, (ClientConfiguration) null);
    }

    public MNSClient(Context context, String str, CredentialProvider credentialProvider2, ClientConfiguration clientConfiguration) {
        try {
            String trim = str.trim();
            if (!trim.startsWith(HttpConstant.HTTP)) {
                trim = "http://" + trim;
            }
            this.endpointURI = new URI(trim);
            if (credentialProvider2 != null) {
                this.credentialProvider = credentialProvider2;
                this.conf = clientConfiguration == null ? ClientConfiguration.getDefaultConf() : clientConfiguration;
                this.internalRequestOperation = new MNSInternalRequestOperation(context, this.endpointURI, credentialProvider2, this.conf);
                return;
            }
            throw new IllegalArgumentException("CredentialProvider can't be null.");
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Endpoint must be a string like 'http://mns.cn-****.aliyuncs.com'!");
        }
    }

    public MNSAsyncTask<CreateQueueResult> asyncCreateQueue(CreateQueueRequest createQueueRequest, MNSCompletedCallback<CreateQueueRequest, CreateQueueResult> mNSCompletedCallback) {
        return this.internalRequestOperation.createQueue(createQueueRequest, mNSCompletedCallback);
    }

    public CreateQueueResult createQueue(CreateQueueRequest createQueueRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.createQueue(createQueueRequest, (MNSCompletedCallback<CreateQueueRequest, CreateQueueResult>) null).getResult();
    }

    public MNSAsyncTask<DeleteQueueResult> asyncDeleteQueue(DeleteQueueRequest deleteQueueRequest, MNSCompletedCallback<DeleteQueueRequest, DeleteQueueResult> mNSCompletedCallback) {
        return this.internalRequestOperation.deleteQueue(deleteQueueRequest, mNSCompletedCallback);
    }

    public DeleteQueueResult deleteQueue(DeleteQueueRequest deleteQueueRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.deleteQueue(deleteQueueRequest, (MNSCompletedCallback<DeleteQueueRequest, DeleteQueueResult>) null).getResult();
    }

    public MNSAsyncTask<SetQueueAttributesResult> asyncSetQueueAttributes(SetQueueAttributesRequest setQueueAttributesRequest, MNSCompletedCallback<SetQueueAttributesRequest, SetQueueAttributesResult> mNSCompletedCallback) {
        return this.internalRequestOperation.setQueueAttr(setQueueAttributesRequest, mNSCompletedCallback);
    }

    public SetQueueAttributesResult setQueueAttributes(SetQueueAttributesRequest setQueueAttributesRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.setQueueAttr(setQueueAttributesRequest, (MNSCompletedCallback<SetQueueAttributesRequest, SetQueueAttributesResult>) null).getResult();
    }

    public MNSAsyncTask<GetQueueAttributesResult> asyncGetQueueAttributes(GetQueueAttributesRequest getQueueAttributesRequest, MNSCompletedCallback<GetQueueAttributesRequest, GetQueueAttributesResult> mNSCompletedCallback) {
        return this.internalRequestOperation.getQueueAttr(getQueueAttributesRequest, mNSCompletedCallback);
    }

    public GetQueueAttributesResult getQueueAttributes(GetQueueAttributesRequest getQueueAttributesRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.getQueueAttr(getQueueAttributesRequest, (MNSCompletedCallback<GetQueueAttributesRequest, GetQueueAttributesResult>) null).getResult();
    }

    public MNSAsyncTask<ListQueueResult> asyncListQueue(ListQueueRequest listQueueRequest, MNSCompletedCallback<ListQueueRequest, ListQueueResult> mNSCompletedCallback) {
        return this.internalRequestOperation.listQueue(listQueueRequest, mNSCompletedCallback);
    }

    public ListQueueResult listQueue(ListQueueRequest listQueueRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.listQueue(listQueueRequest, (MNSCompletedCallback<ListQueueRequest, ListQueueResult>) null).getResult();
    }

    public MNSAsyncTask<SendMessageResult> asyncSendMessage(SendMessageRequest sendMessageRequest, MNSCompletedCallback<SendMessageRequest, SendMessageResult> mNSCompletedCallback) {
        return this.internalRequestOperation.sendMessage(sendMessageRequest, mNSCompletedCallback);
    }

    public SendMessageResult sendMessage(SendMessageRequest sendMessageRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.sendMessage(sendMessageRequest, (MNSCompletedCallback<SendMessageRequest, SendMessageResult>) null).getResult();
    }

    public MNSAsyncTask<ReceiveMessageResult> asyncReceiveMessage(ReceiveMessageRequest receiveMessageRequest, MNSCompletedCallback<ReceiveMessageRequest, ReceiveMessageResult> mNSCompletedCallback) {
        return this.internalRequestOperation.receiveMessage(receiveMessageRequest, mNSCompletedCallback);
    }

    public ReceiveMessageResult receiveMessage(ReceiveMessageRequest receiveMessageRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.receiveMessage(receiveMessageRequest, (MNSCompletedCallback<ReceiveMessageRequest, ReceiveMessageResult>) null).getResult();
    }

    public MNSAsyncTask<DeleteMessageResult> asyncDeleteMessage(DeleteMessageRequest deleteMessageRequest, MNSCompletedCallback<DeleteMessageRequest, DeleteMessageResult> mNSCompletedCallback) {
        return this.internalRequestOperation.deleteMessage(deleteMessageRequest, mNSCompletedCallback);
    }

    public DeleteMessageResult deleteMessage(DeleteMessageRequest deleteMessageRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.deleteMessage(deleteMessageRequest, (MNSCompletedCallback<DeleteMessageRequest, DeleteMessageResult>) null).getResult();
    }

    public MNSAsyncTask<PeekMessageResult> asyncPeekMessage(PeekMessageRequest peekMessageRequest, MNSCompletedCallback<PeekMessageRequest, PeekMessageResult> mNSCompletedCallback) {
        return this.internalRequestOperation.peekMessage(peekMessageRequest, mNSCompletedCallback);
    }

    public PeekMessageResult peekMessage(PeekMessageRequest peekMessageRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.peekMessage(peekMessageRequest, (MNSCompletedCallback<PeekMessageRequest, PeekMessageResult>) null).getResult();
    }

    public MNSAsyncTask<ChangeMessageVisibilityResult> asyncChangeMessageVisibility(ChangeMessageVisibilityRequest changeMessageVisibilityRequest, MNSCompletedCallback<ChangeMessageVisibilityRequest, ChangeMessageVisibilityResult> mNSCompletedCallback) {
        return this.internalRequestOperation.changeMessageVisibility(changeMessageVisibilityRequest, mNSCompletedCallback);
    }

    public ChangeMessageVisibilityResult changeMessageVisibility(ChangeMessageVisibilityRequest changeMessageVisibilityRequest) throws ClientException, ServiceException {
        return this.internalRequestOperation.changeMessageVisibility(changeMessageVisibilityRequest, (MNSCompletedCallback<ChangeMessageVisibilityRequest, ChangeMessageVisibilityResult>) null).getResult();
    }
}
