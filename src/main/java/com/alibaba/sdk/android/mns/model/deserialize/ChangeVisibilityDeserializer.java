package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.Message;
import java.io.StringReader;
import java.util.Date;
import okhttp3.Response;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class ChangeVisibilityDeserializer extends XMLDeserializer<Message> {
    public Message deserialize(Response response) throws Exception {
        Message message = new Message();
        try {
            Element documentElement = getDocumentBuilder().parse(new InputSource(new StringReader(response.body().string()))).getDocumentElement();
            if (documentElement != null && documentElement.getNodeName().equals(MNSConstants.CHANGE_VISIBILITY_TAG)) {
                String safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.RECEIPT_HANDLE_TAG, (String) null);
                if (safeGetElementContent != null) {
                    message.setReceiptHandle(safeGetElementContent);
                }
                String safeGetElementContent2 = safeGetElementContent(documentElement, MNSConstants.NEXT_VISIBLE_TIME_TAG, (String) null);
                if (safeGetElementContent2 != null) {
                    message.setNextVisibleTime(new Date(Long.parseLong(safeGetElementContent2)));
                }
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
