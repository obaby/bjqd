package com.alibaba.sdk.android.mns.internal;

import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.common.MNSHeaders;
import com.alibaba.sdk.android.mns.model.deserialize.ChangeVisibilityDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.ErrorMessageListDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.MessageDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.QueueArrayDeserializer;
import com.alibaba.sdk.android.mns.model.deserialize.QueueMetaDeserializer;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Response;

public class ResponseParsers {
    public static Map<String, String> parseResponseHeader(Response response) {
        HashMap hashMap = new HashMap();
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            hashMap.put(headers.name(i), headers.value(i));
        }
        return hashMap;
    }

    public static void safeCloseResponse(Response response) {
        try {
            response.body().close();
        } catch (Exception unused) {
        }
    }

    public static final class CreateQueueResponseParser implements ResponseParser<CreateQueueResult> {
        public CreateQueueResult parse(Response response) throws IOException {
            try {
                CreateQueueResult createQueueResult = new CreateQueueResult();
                createQueueResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                createQueueResult.setStatusCode(response.code());
                createQueueResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                createQueueResult.setQueueLocation(createQueueResult.getResponseHeader().get("Location"));
                ResponseParsers.safeCloseResponse(response);
                return createQueueResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class DeleteQueueResponseParser implements ResponseParser<DeleteQueueResult> {
        public DeleteQueueResult parse(Response response) throws IOException {
            try {
                DeleteQueueResult deleteQueueResult = new DeleteQueueResult();
                deleteQueueResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                deleteQueueResult.setStatusCode(response.code());
                deleteQueueResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                ResponseParsers.safeCloseResponse(response);
                return deleteQueueResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class SetQueueAttributesResponseParser implements ResponseParser<SetQueueAttributesResult> {
        public SetQueueAttributesResult parse(Response response) throws IOException {
            try {
                SetQueueAttributesResult setQueueAttributesResult = new SetQueueAttributesResult();
                setQueueAttributesResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                setQueueAttributesResult.setStatusCode(response.code());
                setQueueAttributesResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                ResponseParsers.safeCloseResponse(response);
                return setQueueAttributesResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class GetQueueAttributesResponseParser implements ResponseParser<GetQueueAttributesResult> {
        public GetQueueAttributesResult parse(Response response) throws IOException {
            try {
                GetQueueAttributesResult getQueueAttributesResult = new GetQueueAttributesResult();
                getQueueAttributesResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                getQueueAttributesResult.setStatusCode(response.code());
                getQueueAttributesResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                getQueueAttributesResult.setQueueMeta(new QueueMetaDeserializer().deserialize(response));
                ResponseParsers.safeCloseResponse(response);
                return getQueueAttributesResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class ListQueueResponseParser implements ResponseParser<ListQueueResult> {
        public ListQueueResult parse(Response response) throws IOException {
            try {
                ListQueueResult listQueueResult = new ListQueueResult();
                listQueueResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                listQueueResult.setStatusCode(response.code());
                listQueueResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                listQueueResult.setQueueLists(new QueueArrayDeserializer().deserialize(response));
                ResponseParsers.safeCloseResponse(response);
                return listQueueResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class SendMessageResponseParser implements ResponseParser<SendMessageResult> {
        public SendMessageResult parse(Response response) throws IOException {
            try {
                SendMessageResult sendMessageResult = new SendMessageResult();
                sendMessageResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                sendMessageResult.setStatusCode(response.code());
                sendMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                sendMessageResult.setMessageResponse(new MessageDeserializer().deserialize(response));
                ResponseParsers.safeCloseResponse(response);
                return sendMessageResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class ReceiveMessageParser implements ResponseParser<ReceiveMessageResult> {
        public ReceiveMessageResult parse(Response response) throws IOException {
            try {
                ReceiveMessageResult receiveMessageResult = new ReceiveMessageResult();
                receiveMessageResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                receiveMessageResult.setStatusCode(response.code());
                receiveMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                receiveMessageResult.setMessage(new MessageDeserializer().deserialize(response));
                ResponseParsers.safeCloseResponse(response);
                return receiveMessageResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class DeleteMessageParser implements ResponseParser<DeleteMessageResult> {
        public DeleteMessageResult parse(Response response) throws IOException {
            try {
                DeleteMessageResult deleteMessageResult = new DeleteMessageResult();
                deleteMessageResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                deleteMessageResult.setStatusCode(response.code());
                deleteMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                ResponseParsers.safeCloseResponse(response);
                return deleteMessageResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class ChangeMessageVisibilityParser implements ResponseParser<ChangeMessageVisibilityResult> {
        public ChangeMessageVisibilityResult parse(Response response) throws IOException {
            try {
                ChangeMessageVisibilityResult changeMessageVisibilityResult = new ChangeMessageVisibilityResult();
                changeMessageVisibilityResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                changeMessageVisibilityResult.setStatusCode(response.code());
                changeMessageVisibilityResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                changeMessageVisibilityResult.setChangeVisibleResponse(new ChangeVisibilityDeserializer().deserialize(response));
                ResponseParsers.safeCloseResponse(response);
                return changeMessageVisibilityResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static final class PeekMessageParser implements ResponseParser<PeekMessageResult> {
        public PeekMessageResult parse(Response response) throws IOException {
            try {
                PeekMessageResult peekMessageResult = new PeekMessageResult();
                peekMessageResult.setRequestId(response.header(MNSHeaders.MNS_HEADER_REQUEST_ID));
                peekMessageResult.setStatusCode(response.code());
                peekMessageResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                peekMessageResult.setMessage(new MessageDeserializer().deserialize(response));
                ResponseParsers.safeCloseResponse(response);
                return peekMessageResult;
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(response);
                throw th;
            }
        }
    }

    public static ServiceException parseResponseErrorXML(Response response) throws IOException {
        try {
            ServiceException deserialize = new ErrorMessageListDeserializer().deserialize(response);
            safeCloseResponse(response);
            return deserialize;
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        } catch (Throwable th) {
            safeCloseResponse(response);
            throw th;
        }
    }
}
