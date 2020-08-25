package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.Message;
import java.io.StringReader;
import java.util.Date;
import okhttp3.Response;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class MessageDeserializer extends XMLDeserializer<Message> {
    public Message deserialize(Response response) throws Exception {
        Message message = new Message();
        try {
            Element documentElement = getDocumentBuilder().parse(new InputSource(new StringReader(response.body().string()))).getDocumentElement();
            if (documentElement != null && documentElement.getNodeName().equals("Message")) {
                String safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.MESSAGE_ID_TAG, (String) null);
                if (safeGetElementContent != null) {
                    message.setMessageId(safeGetElementContent);
                }
                String safeGetElementContent2 = safeGetElementContent(documentElement, MNSConstants.MESSAGE_BODY_MD5_TAG, (String) null);
                if (safeGetElementContent2 != null) {
                    message.setMessageBodyMd5(safeGetElementContent2);
                }
                String safeGetElementContent3 = safeGetElementContent(documentElement, MNSConstants.RECEIPT_HANDLE_TAG, (String) null);
                if (safeGetElementContent3 != null) {
                    message.setReceiptHandle(safeGetElementContent3);
                }
                String safeGetElementContent4 = safeGetElementContent(documentElement, MNSConstants.MESSAGE_BODY_TAG, (String) null);
                if (safeGetElementContent4 != null) {
                    message.setMessageBody(safeGetElementContent4);
                }
                String safeGetElementContent5 = safeGetElementContent(documentElement, MNSConstants.ENQUEUE_TIME_TAG, (String) null);
                if (safeGetElementContent5 != null) {
                    message.setEnqueueTime(new Date(Long.parseLong(safeGetElementContent5)));
                }
                String safeGetElementContent6 = safeGetElementContent(documentElement, MNSConstants.NEXT_VISIBLE_TIME_TAG, (String) null);
                if (safeGetElementContent6 != null) {
                    message.setNextVisibleTime(new Date(Long.parseLong(safeGetElementContent6)));
                }
                String safeGetElementContent7 = safeGetElementContent(documentElement, MNSConstants.FIRST_DEQUEUE_TIME_TAG, (String) null);
                if (safeGetElementContent7 != null) {
                    message.setFirstDequeueTime(new Date(Long.parseLong(safeGetElementContent7)));
                }
                String safeGetElementContent8 = safeGetElementContent(documentElement, MNSConstants.DEQUEUE_COUNT_TAG, (String) null);
                if (safeGetElementContent8 != null) {
                    message.setDequeueCount(Integer.parseInt(safeGetElementContent8));
                }
                String safeGetElementContent9 = safeGetElementContent(documentElement, MNSConstants.PRIORITY_TAG, (String) null);
                if (safeGetElementContent9 != null) {
                    message.setPriority(Integer.parseInt(safeGetElementContent9));
                }
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
