package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.mns.model.QueueMeta;
import java.io.StringReader;
import okhttp3.Response;
import org.xml.sax.InputSource;

public class QueueMetaDeserializer extends AbstractQueueMetaDeserializer<QueueMeta> {
    public QueueMeta deserialize(Response response) throws Exception {
        try {
            return parseQueueMeta(getDocumentBuilder().parse(new InputSource(new StringReader(response.body().string()))).getDocumentElement());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
