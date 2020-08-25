package com.alibaba.sdk.android.mns.model.serialize;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class BaseXMLSerializer<T> {
    protected static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static ThreadLocal<DocumentBuilder> sps = new ThreadLocal<>();

    /* access modifiers changed from: protected */
    public DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = sps.get();
        if (documentBuilder != null) {
            return documentBuilder;
        }
        DocumentBuilder newDocumentBuilder = factory.newDocumentBuilder();
        sps.set(newDocumentBuilder);
        return newDocumentBuilder;
    }
}
