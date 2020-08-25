package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.charset.Charset;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {
    public static final MediaType APPLICATION_JAVASCRIPT = new MediaType("application", "javascript");
    private Charset charset = Charset.forName("UTF-8");
    @Deprecated
    protected String dateFormat;
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();
    @Deprecated
    protected SerializerFeature[] features = new SerializerFeature[0];
    @Deprecated
    protected SerializeFilter[] filters = new SerializeFilter[0];

    /* access modifiers changed from: protected */
    public boolean supports(Class<?> cls) {
        return true;
    }

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig2) {
        this.fastJsonConfig = fastJsonConfig2;
    }

    public FastJsonHttpMessageConverter() {
        super(MediaType.ALL);
    }

    @Deprecated
    public Charset getCharset() {
        return this.fastJsonConfig.getCharset();
    }

    @Deprecated
    public void setCharset(Charset charset2) {
        this.fastJsonConfig.setCharset(charset2);
    }

    @Deprecated
    public String getDateFormat() {
        return this.fastJsonConfig.getDateFormat();
    }

    @Deprecated
    public void setDateFormat(String str) {
        this.fastJsonConfig.setDateFormat(str);
    }

    @Deprecated
    public SerializerFeature[] getFeatures() {
        return this.fastJsonConfig.getSerializerFeatures();
    }

    @Deprecated
    public void setFeatures(SerializerFeature... serializerFeatureArr) {
        this.fastJsonConfig.setSerializerFeatures(serializerFeatureArr);
    }

    @Deprecated
    public SerializeFilter[] getFilters() {
        return this.fastJsonConfig.getSerializeFilters();
    }

    @Deprecated
    public void setFilters(SerializeFilter... serializeFilterArr) {
        this.fastJsonConfig.setSerializeFilters(serializeFilterArr);
    }

    @Deprecated
    public void addSerializeFilter(SerializeFilter serializeFilter) {
        if (serializeFilter != null) {
            int length = this.fastJsonConfig.getSerializeFilters().length;
            SerializeFilter[] serializeFilterArr = new SerializeFilter[(length + 1)];
            System.arraycopy(this.fastJsonConfig.getSerializeFilters(), 0, serializeFilterArr, 0, length);
            serializeFilterArr[serializeFilterArr.length - 1] = serializeFilter;
            this.fastJsonConfig.setSerializeFilters(serializeFilterArr);
        }
    }

    public boolean canRead(Type type, Class<?> cls, MediaType mediaType) {
        return FastJsonHttpMessageConverter.super.canRead(cls, mediaType);
    }

    public boolean canWrite(Type type, Class<?> cls, MediaType mediaType) {
        return FastJsonHttpMessageConverter.super.canWrite(cls, mediaType);
    }

    public Object read(Type type, Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(type, cls), httpInputMessage);
    }

    public void write(Object obj, Type type, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        FastJsonHttpMessageConverter.super.write(obj, mediaType, httpOutputMessage);
    }

    /* access modifiers changed from: protected */
    public Object readInternal(Class<? extends Object> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(cls, (Class<?>) null), httpInputMessage);
    }

    private Object readType(Type type, HttpInputMessage httpInputMessage) throws IOException {
        try {
            return JSON.parseObject(httpInputMessage.getBody(), this.fastJsonConfig.getCharset(), type, this.fastJsonConfig.getFeatures());
        } catch (JSONException e) {
            throw new HttpMessageNotReadableException("JSON parse error: " + e.getMessage(), e);
        } catch (IOException e2) {
            throw new HttpMessageNotReadableException("I/O error while reading input message", e2);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0048, code lost:
        if ((r2 instanceof com.alibaba.fastjson.JSONPObject) != false) goto L_0x004a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0081 A[Catch:{ JSONException -> 0x009f, all -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008e A[Catch:{ JSONException -> 0x009f, all -> 0x009d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeInternal(java.lang.Object r12, org.springframework.http.HttpOutputMessage r13) throws java.io.IOException, org.springframework.http.converter.HttpMessageNotWritableException {
        /*
            r11 = this;
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream
            r8.<init>()
            org.springframework.http.HttpHeaders r9 = r13.getHeaders()     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r11.fastJsonConfig     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.serializer.SerializeFilter[] r0 = r0.getSerializeFilters()     // Catch:{ JSONException -> 0x009f }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ JSONException -> 0x009f }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ JSONException -> 0x009f }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x009f }
            r0 = 0
            java.lang.Object r12 = r11.strangeCodeForJackson(r12)     // Catch:{ JSONException -> 0x009f }
            boolean r2 = r12 instanceof com.alibaba.fastjson.support.spring.FastJsonContainer     // Catch:{ JSONException -> 0x009f }
            if (r2 == 0) goto L_0x0032
            com.alibaba.fastjson.support.spring.FastJsonContainer r12 = (com.alibaba.fastjson.support.spring.FastJsonContainer) r12     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.support.spring.PropertyPreFilters r2 = r12.getFilters()     // Catch:{ JSONException -> 0x009f }
            java.util.List r2 = r2.getFilters()     // Catch:{ JSONException -> 0x009f }
            r1.addAll(r2)     // Catch:{ JSONException -> 0x009f }
            java.lang.Object r12 = r12.getValue()     // Catch:{ JSONException -> 0x009f }
        L_0x0032:
            r2 = r12
            boolean r12 = r2 instanceof com.alibaba.fastjson.support.spring.MappingFastJsonValue     // Catch:{ JSONException -> 0x009f }
            r3 = 1
            if (r12 == 0) goto L_0x0046
            r12 = r2
            com.alibaba.fastjson.support.spring.MappingFastJsonValue r12 = (com.alibaba.fastjson.support.spring.MappingFastJsonValue) r12     // Catch:{ JSONException -> 0x009f }
            java.lang.String r12 = r12.getJsonpFunction()     // Catch:{ JSONException -> 0x009f }
            boolean r12 = org.springframework.util.StringUtils.isEmpty(r12)     // Catch:{ JSONException -> 0x009f }
            if (r12 != 0) goto L_0x004c
            goto L_0x004a
        L_0x0046:
            boolean r12 = r2 instanceof com.alibaba.fastjson.JSONPObject     // Catch:{ JSONException -> 0x009f }
            if (r12 == 0) goto L_0x004c
        L_0x004a:
            r12 = 1
            goto L_0x004d
        L_0x004c:
            r12 = 0
        L_0x004d:
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r11.fastJsonConfig     // Catch:{ JSONException -> 0x009f }
            java.nio.charset.Charset r3 = r0.getCharset()     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r11.fastJsonConfig     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.serializer.SerializeConfig r4 = r0.getSerializeConfig()     // Catch:{ JSONException -> 0x009f }
            int r0 = r1.size()     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.serializer.SerializeFilter[] r0 = new com.alibaba.fastjson.serializer.SerializeFilter[r0]     // Catch:{ JSONException -> 0x009f }
            java.lang.Object[] r0 = r1.toArray(r0)     // Catch:{ JSONException -> 0x009f }
            r5 = r0
            com.alibaba.fastjson.serializer.SerializeFilter[] r5 = (com.alibaba.fastjson.serializer.SerializeFilter[]) r5     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r11.fastJsonConfig     // Catch:{ JSONException -> 0x009f }
            java.lang.String r6 = r0.getDateFormat()     // Catch:{ JSONException -> 0x009f }
            int r7 = com.alibaba.fastjson.JSON.DEFAULT_GENERATE_FEATURE     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r11.fastJsonConfig     // Catch:{ JSONException -> 0x009f }
            com.alibaba.fastjson.serializer.SerializerFeature[] r10 = r0.getSerializerFeatures()     // Catch:{ JSONException -> 0x009f }
            r0 = r8
            r1 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r10
            int r0 = com.alibaba.fastjson.JSON.writeJSONString(r0, r1, r2, r3, r4, r5, r6, r7)     // Catch:{ JSONException -> 0x009f }
            if (r12 == 0) goto L_0x0086
            org.springframework.http.MediaType r12 = APPLICATION_JAVASCRIPT     // Catch:{ JSONException -> 0x009f }
            r9.setContentType(r12)     // Catch:{ JSONException -> 0x009f }
        L_0x0086:
            com.alibaba.fastjson.support.config.FastJsonConfig r12 = r11.fastJsonConfig     // Catch:{ JSONException -> 0x009f }
            boolean r12 = r12.isWriteContentLength()     // Catch:{ JSONException -> 0x009f }
            if (r12 == 0) goto L_0x0092
            long r0 = (long) r0     // Catch:{ JSONException -> 0x009f }
            r9.setContentLength(r0)     // Catch:{ JSONException -> 0x009f }
        L_0x0092:
            java.io.OutputStream r12 = r13.getBody()     // Catch:{ JSONException -> 0x009f }
            r8.writeTo(r12)     // Catch:{ JSONException -> 0x009f }
            r8.close()
            return
        L_0x009d:
            r12 = move-exception
            goto L_0x00bb
        L_0x009f:
            r12 = move-exception
            org.springframework.http.converter.HttpMessageNotWritableException r13 = new org.springframework.http.converter.HttpMessageNotWritableException     // Catch:{ all -> 0x009d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x009d }
            r0.<init>()     // Catch:{ all -> 0x009d }
            java.lang.String r1 = "Could not write JSON: "
            r0.append(r1)     // Catch:{ all -> 0x009d }
            java.lang.String r1 = r12.getMessage()     // Catch:{ all -> 0x009d }
            r0.append(r1)     // Catch:{ all -> 0x009d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x009d }
            r13.<init>(r0, r12)     // Catch:{ all -> 0x009d }
            throw r13     // Catch:{ all -> 0x009d }
        L_0x00bb:
            r8.close()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter.writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage):void");
    }

    private Object strangeCodeForJackson(Object obj) {
        return (obj == null || !"com.fasterxml.jackson.databind.node.ObjectNode".equals(obj.getClass().getName())) ? obj : obj.toString();
    }

    /* access modifiers changed from: protected */
    public Type getType(Type type, Class<?> cls) {
        return Spring4TypeResolvableHelper.isSupport() ? Spring4TypeResolvableHelper.getType(type, cls) : type;
    }

    private static class Spring4TypeResolvableHelper {
        private static boolean hasClazzResolvableType;

        private Spring4TypeResolvableHelper() {
        }

        static {
            try {
                Class.forName("org.springframework.core.ResolvableType");
                hasClazzResolvableType = true;
            } catch (ClassNotFoundException unused) {
                hasClazzResolvableType = false;
            }
        }

        /* access modifiers changed from: private */
        public static boolean isSupport() {
            return hasClazzResolvableType;
        }

        /* access modifiers changed from: private */
        public static Type getType(Type type, Class<?> cls) {
            if (cls != null) {
                ResolvableType forType = ResolvableType.forType(type);
                if (type instanceof TypeVariable) {
                    ResolvableType resolveVariable = resolveVariable((TypeVariable) type, ResolvableType.forClass(cls));
                    if (resolveVariable != ResolvableType.NONE) {
                        return resolveVariable.resolve();
                    }
                } else if ((type instanceof ParameterizedType) && forType.hasUnresolvableGenerics()) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Class[] clsArr = new Class[parameterizedType.getActualTypeArguments().length];
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for (int i = 0; i < actualTypeArguments.length; i++) {
                        Type type2 = actualTypeArguments[i];
                        if (type2 instanceof TypeVariable) {
                            ResolvableType resolveVariable2 = resolveVariable((TypeVariable) type2, ResolvableType.forClass(cls));
                            if (resolveVariable2 != ResolvableType.NONE) {
                                clsArr[i] = resolveVariable2.resolve();
                            } else {
                                clsArr[i] = ResolvableType.forType(type2).resolve();
                            }
                        } else {
                            clsArr[i] = ResolvableType.forType(type2).resolve();
                        }
                    }
                    return ResolvableType.forClassWithGenerics(forType.getRawClass(), clsArr).getType();
                }
            }
            return type;
        }

        private static ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType resolvableType) {
            if (resolvableType.hasGenerics()) {
                ResolvableType forType = ResolvableType.forType(typeVariable, resolvableType);
                if (forType.resolve() != null) {
                    return forType;
                }
            }
            ResolvableType superType = resolvableType.getSuperType();
            if (superType != ResolvableType.NONE) {
                ResolvableType resolveVariable = resolveVariable(typeVariable, superType);
                if (resolveVariable.resolve() != null) {
                    return resolveVariable;
                }
            }
            for (ResolvableType resolveVariable2 : resolvableType.getInterfaces()) {
                ResolvableType resolveVariable3 = resolveVariable(typeVariable, resolveVariable2);
                if (resolveVariable3.resolve() != null) {
                    return resolveVariable3;
                }
            }
            return ResolvableType.NONE;
        }
    }
}
