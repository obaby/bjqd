package com.alibaba.sdk.android.mns.model.serialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MessageSerializer extends XMLSerializer<Message> {
    public String serialize(Message message, String str) throws Exception {
        Document newDocument = getDocumentBuilder().newDocument();
        Element createElementNS = newDocument.createElementNS(MNSConstants.DEFAULT_XML_NAMESPACE, "Message");
        newDocument.appendChild(createElementNS);
        Element safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.MESSAGE_BODY_TAG, message.getMessageBody(), (String) null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        Element safeCreateContentElement2 = safeCreateContentElement(newDocument, MNSConstants.DELAY_SECONDS_TAG, message.getDelaySeconds(), (String) null);
        if (safeCreateContentElement2 != null) {
            createElementNS.appendChild(safeCreateContentElement2);
        }
        Element safeCreateContentElement3 = safeCreateContentElement(newDocument, MNSConstants.PRIORITY_TAG, message.getPriority(), (String) null);
        if (safeCreateContentElement3 != null) {
            createElementNS.appendChild(safeCreateContentElement3);
        }
        return XmlUtil.xmlNodeToString(newDocument, str);
    }
}
