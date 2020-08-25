package com.alibaba.fastjson.support.spring;

import java.io.IOException;
import java.lang.reflect.Type;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

@Deprecated
public class FastJsonpHttpMessageConverter4 extends FastJsonHttpMessageConverter {
    /* access modifiers changed from: protected */
    public boolean supports(Class<?> cls) {
        return super.supports(cls);
    }

    public boolean canRead(Type type, Class<?> cls, MediaType mediaType) {
        return super.canRead(type, cls, mediaType);
    }

    public boolean canWrite(Type type, Class<?> cls, MediaType mediaType) {
        return super.canWrite(type, cls, mediaType);
    }

    public Object read(Type type, Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return super.read(type, cls, httpInputMessage);
    }

    public void write(Object obj, Type type, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        super.write(obj, type, mediaType, httpOutputMessage);
    }

    /* access modifiers changed from: protected */
    public Object readInternal(Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return super.readInternal(cls, httpInputMessage);
    }

    /* access modifiers changed from: protected */
    public void writeInternal(Object obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        super.writeInternal(obj, httpOutputMessage);
    }
}
