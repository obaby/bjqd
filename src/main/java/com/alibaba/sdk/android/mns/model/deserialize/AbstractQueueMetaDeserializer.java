package com.alibaba.sdk.android.mns.model.deserialize;

import anetwork.channel.util.RequestConstant;
import cn.xports.qd2.entity.K;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.QueueMeta;
import java.util.Date;
import org.w3c.dom.Element;

public abstract class AbstractQueueMetaDeserializer<T> extends XMLDeserializer<T> {
    /* access modifiers changed from: protected */
    public QueueMeta parseQueueMeta(Element element) {
        QueueMeta queueMeta = new QueueMeta();
        queueMeta.setQueueName(safeGetElementContent(element, MNSConstants.QUEUE_NAME_TAG, (String) null));
        queueMeta.setDelaySeconds(Long.valueOf(Long.parseLong(safeGetElementContent(element, MNSConstants.DELAY_SECONDS_TAG, K.k0))));
        queueMeta.setMaxMessageSize(Long.valueOf(Long.parseLong(safeGetElementContent(element, MNSConstants.MAX_MESSAGE_SIZE_TAG, K.k0))));
        queueMeta.setMessageRetentionPeriod(Long.valueOf(Long.parseLong(safeGetElementContent(element, MNSConstants.MESSAGE_RETENTION_PERIOD_TAG, K.k0))));
        queueMeta.setVisibilityTimeout(Long.valueOf(Long.parseLong(safeGetElementContent(element, MNSConstants.VISIBILITY_TIMEOUT, K.k0))));
        queueMeta.setCreateTime(new Date(Long.parseLong(safeGetElementContent(element, MNSConstants.CREATE_TIME_TAG, K.k0)) * 1000));
        queueMeta.setLastModifyTime(new Date(Long.parseLong(safeGetElementContent(element, MNSConstants.LASTMODIFYTIME_TAG, K.k0)) * 1000));
        queueMeta.setPollingWaitSeconds(Integer.valueOf(Integer.parseInt(safeGetElementContent(element, MNSConstants.POLLING_WAITSECONDS_TAG, K.k0))));
        queueMeta.setActiveMessages(Long.valueOf(Long.parseLong(safeGetElementContent(element, MNSConstants.ACTIVE_MESSAGES_TAG, K.k0))));
        queueMeta.setInactiveMessages(Long.valueOf(Long.parseLong(safeGetElementContent(element, MNSConstants.INACTIVE_MESSAGES_TAG, K.k0))));
        queueMeta.setDelayMessages(Long.valueOf(Long.parseLong(safeGetElementContent(element, MNSConstants.DELAY_MESSAGES_TAG, K.k0))));
        queueMeta.setQueueURL(safeGetElementContent(element, MNSConstants.QUEUE_URL_TAG, (String) null));
        queueMeta.setLoggingEnabled(Boolean.parseBoolean(safeGetElementContent(element, MNSConstants.LOGGING_ENABLED_TAG, RequestConstant.FALSE)));
        return queueMeta;
    }
}
