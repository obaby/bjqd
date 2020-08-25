package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.mns.model.serialize.BaseXMLSerializer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XMLDeserializer<T> extends BaseXMLSerializer<T> implements Deserializer<T> {
    public String safeGetElementContent(Element element, String str, String str2) {
        Node item;
        NodeList elementsByTagName = element.getElementsByTagName(str);
        if (elementsByTagName == null || (item = elementsByTagName.item(0)) == null) {
            return str2;
        }
        return item.getTextContent();
    }
}
