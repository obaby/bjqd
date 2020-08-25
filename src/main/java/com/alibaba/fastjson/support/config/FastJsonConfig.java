package com.alibaba.fastjson.support.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.nio.charset.Charset;
import java.util.Map;

public class FastJsonConfig {
    private Charset charset = Charset.forName("UTF-8");
    private Map<Class<?>, SerializeFilter> classSerializeFilters;
    private String dateFormat;
    private Feature[] features = new Feature[0];
    private ParserConfig parserConfig = new ParserConfig();
    private SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
    private SerializeFilter[] serializeFilters = new SerializeFilter[0];
    private SerializerFeature[] serializerFeatures = {SerializerFeature.BrowserSecure};
    protected boolean writeContentLength = true;

    public SerializeConfig getSerializeConfig() {
        return this.serializeConfig;
    }

    public void setSerializeConfig(SerializeConfig serializeConfig2) {
        this.serializeConfig = serializeConfig2;
    }

    public ParserConfig getParserConfig() {
        return this.parserConfig;
    }

    public void setParserConfig(ParserConfig parserConfig2) {
        this.parserConfig = parserConfig2;
    }

    public SerializerFeature[] getSerializerFeatures() {
        return this.serializerFeatures;
    }

    public void setSerializerFeatures(SerializerFeature... serializerFeatureArr) {
        this.serializerFeatures = serializerFeatureArr;
    }

    public SerializeFilter[] getSerializeFilters() {
        return this.serializeFilters;
    }

    public void setSerializeFilters(SerializeFilter... serializeFilterArr) {
        this.serializeFilters = serializeFilterArr;
    }

    public Feature[] getFeatures() {
        return this.features;
    }

    public void setFeatures(Feature... featureArr) {
        this.features = featureArr;
    }

    public Map<Class<?>, SerializeFilter> getClassSerializeFilters() {
        return this.classSerializeFilters;
    }

    public void setClassSerializeFilters(Map<Class<?>, SerializeFilter> map) {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                this.serializeConfig.addFilter((Class) next.getKey(), (SerializeFilter) next.getValue());
            }
            this.classSerializeFilters = map;
        }
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormat = str;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset2) {
        this.charset = charset2;
    }

    public boolean isWriteContentLength() {
        return this.writeContentLength;
    }

    public void setWriteContentLength(boolean z) {
        this.writeContentLength = z;
    }
}
