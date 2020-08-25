package com.alibaba.sdk.android.mns.model.serialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.QueueMeta;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class QueueMetaSerializer extends XMLSerializer<QueueMeta> {
    public String serialize(QueueMeta queueMeta, String str) throws Exception {
        Document newDocument = getDocumentBuilder().newDocument();
        Element createElementNS = newDocument.createElementNS(MNSConstants.DEFAULT_XML_NAMESPACE, MNSConstants.QUEUE_TAG);
        newDocument.appendChild(createElementNS);
        Element safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.DELAY_SECONDS_TAG, queueMeta.getDelaySeconds(), (String) null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        Element safeCreateContentElement2 = safeCreateContentElement(newDocument, MNSConstants.VISIBILITY_TIMEOUT, queueMeta.getVisibilityTimeout(), (String) null);
        if (safeCreateContentElement2 != null) {
            createElementNS.appendChild(safeCreateContentElement2);
        }
        Element safeCreateContentElement3 = safeCreateContentElement(newDocument, MNSConstants.MAX_MESSAGE_SIZE_TAG, queueMeta.getMaxMessageSize(), (String) null);
        if (safeCreateContentElement3 != null) {
            createElementNS.appendChild(safeCreateContentElement3);
        }
        Element safeCreateContentElement4 = safeCreateContentElement(newDocument, MNSConstants.MESSAGE_RETENTION_PERIOD_TAG, queueMeta.getMessageRetentionPeriod(), (String) null);
        if (safeCreateContentElement4 != null) {
            createElementNS.appendChild(safeCreateContentElement4);
        }
        Element safeCreateContentElement5 = safeCreateContentElement(newDocument, MNSConstants.POLLING_WAITSECONDS_TAG, queueMeta.getPollingWaitSeconds(), (String) null);
        if (safeCreateContentElement5 != null) {
            createElementNS.appendChild(safeCreateContentElement5);
        }
        Element safeCreateBooleanContentElement = safeCreateBooleanContentElement(newDocument, MNSConstants.LOGGING_ENABLED_TAG, queueMeta.getLoggingEnabled(), (String) null);
        if (safeCreateBooleanContentElement != null) {
            createElementNS.appendChild(safeCreateBooleanContentElement);
        }
        return XmlUtil.xmlNodeToString(newDocument, str);
    }
}
