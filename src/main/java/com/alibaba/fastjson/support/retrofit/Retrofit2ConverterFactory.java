package com.alibaba.fastjson.support.retrofit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class Retrofit2ConverterFactory extends Converter.Factory {
    /* access modifiers changed from: private */
    public static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];
    /* access modifiers changed from: private */
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    /* access modifiers changed from: private */
    public int featureValues = JSON.DEFAULT_PARSER_FEATURE;
    /* access modifiers changed from: private */
    public Feature[] features;
    /* access modifiers changed from: private */
    public ParserConfig parserConfig = ParserConfig.getGlobalInstance();
    /* access modifiers changed from: private */
    public SerializeConfig serializeConfig;
    /* access modifiers changed from: private */
    public SerializerFeature[] serializerFeatures;

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new ResponseBodyConverter(type);
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new RequestBodyConverter();
    }

    public ParserConfig getParserConfig() {
        return this.parserConfig;
    }

    public Retrofit2ConverterFactory setParserConfig(ParserConfig parserConfig2) {
        this.parserConfig = parserConfig2;
        return this;
    }

    public int getParserFeatureValues() {
        return this.featureValues;
    }

    public Retrofit2ConverterFactory setParserFeatureValues(int i) {
        this.featureValues = i;
        return this;
    }

    public Feature[] getParserFeatures() {
        return this.features;
    }

    public Retrofit2ConverterFactory setParserFeatures(Feature[] featureArr) {
        this.features = featureArr;
        return this;
    }

    public SerializeConfig getSerializeConfig() {
        return this.serializeConfig;
    }

    public Retrofit2ConverterFactory setSerializeConfig(SerializeConfig serializeConfig2) {
        this.serializeConfig = serializeConfig2;
        return this;
    }

    public SerializerFeature[] getSerializerFeatures() {
        return this.serializerFeatures;
    }

    public Retrofit2ConverterFactory setSerializerFeatures(SerializerFeature[] serializerFeatureArr) {
        this.serializerFeatures = serializerFeatureArr;
        return this;
    }

    final class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private Type type;

        ResponseBodyConverter(Type type2) {
            this.type = type2;
        }

        public T convert(ResponseBody responseBody) throws IOException {
            Feature[] featureArr;
            try {
                String string = responseBody.string();
                Type type2 = this.type;
                ParserConfig access$000 = Retrofit2ConverterFactory.this.parserConfig;
                int access$100 = Retrofit2ConverterFactory.this.featureValues;
                if (Retrofit2ConverterFactory.this.features != null) {
                    featureArr = Retrofit2ConverterFactory.this.features;
                } else {
                    featureArr = Retrofit2ConverterFactory.EMPTY_SERIALIZER_FEATURES;
                }
                return JSON.parseObject(string, type2, access$000, access$100, featureArr);
            } finally {
                responseBody.close();
            }
        }
    }

    final class RequestBodyConverter<T> implements Converter<T, RequestBody> {
        RequestBodyConverter() {
        }

        public RequestBody convert(T t) throws IOException {
            SerializeConfig serializeConfig;
            SerializerFeature[] serializerFeatureArr;
            if (Retrofit2ConverterFactory.this.serializeConfig == null) {
                serializeConfig = SerializeConfig.globalInstance;
            } else {
                serializeConfig = Retrofit2ConverterFactory.this.serializeConfig;
            }
            if (Retrofit2ConverterFactory.this.serializerFeatures == null) {
                serializerFeatureArr = SerializerFeature.EMPTY;
            } else {
                serializerFeatureArr = Retrofit2ConverterFactory.this.serializerFeatures;
            }
            return RequestBody.create(Retrofit2ConverterFactory.MEDIA_TYPE, JSON.toJSONBytes((Object) t, serializeConfig, serializerFeatureArr));
        }
    }
}
