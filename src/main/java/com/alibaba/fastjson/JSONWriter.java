package com.alibaba.fastjson;

import android.support.v4.view.PointerIconCompat;
import cn.qqtheme.framework.BuildConfig;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JSONWriter implements Closeable, Flushable {
    private JSONStreamContext context;
    private JSONSerializer serializer = new JSONSerializer(this.writer);
    private SerializeWriter writer;

    public JSONWriter(Writer writer2) {
        this.writer = new SerializeWriter(writer2);
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.writer.config(serializerFeature, z);
    }

    public void startObject() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, PointerIconCompat.TYPE_CONTEXT_MENU);
        this.writer.write((int) BuildConfig.VERSION_CODE);
    }

    public void endObject() {
        this.writer.write(125);
        endStructure();
    }

    public void writeKey(String str) {
        writeObject(str);
    }

    public void writeValue(Object obj) {
        writeObject(obj);
    }

    public void writeObject(String str) {
        beforeWrite();
        this.serializer.write(str);
        afterWriter();
    }

    public void writeObject(Object obj) {
        beforeWrite();
        this.serializer.write(obj);
        afterWriter();
    }

    public void startArray() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, PointerIconCompat.TYPE_WAIT);
        this.writer.write(91);
    }

    private void beginStructure() {
        int i = this.context.state;
        switch (this.context.state) {
            case PointerIconCompat.TYPE_CONTEXT_MENU:
            case PointerIconCompat.TYPE_WAIT:
                return;
            case PointerIconCompat.TYPE_HAND:
                this.writer.write(58);
                return;
            case 1005:
                this.writer.write(44);
                return;
            default:
                throw new JSONException("illegal state : " + i);
        }
    }

    public void endArray() {
        this.writer.write(93);
        endStructure();
    }

    private void endStructure() {
        int i;
        this.context = this.context.parent;
        if (this.context != null) {
            switch (this.context.state) {
                case PointerIconCompat.TYPE_CONTEXT_MENU:
                    i = PointerIconCompat.TYPE_HAND;
                    break;
                case PointerIconCompat.TYPE_HAND:
                    i = PointerIconCompat.TYPE_HELP;
                    break;
                case PointerIconCompat.TYPE_WAIT:
                    i = 1005;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i != -1) {
                this.context.state = i;
            }
        }
    }

    private void beforeWrite() {
        if (this.context != null) {
            switch (this.context.state) {
                case PointerIconCompat.TYPE_HAND:
                    this.writer.write(58);
                    return;
                case PointerIconCompat.TYPE_HELP:
                    this.writer.write(44);
                    return;
                case 1005:
                    this.writer.write(44);
                    return;
                default:
                    return;
            }
        }
    }

    private void afterWriter() {
        int i;
        if (this.context != null) {
            switch (this.context.state) {
                case PointerIconCompat.TYPE_CONTEXT_MENU:
                case PointerIconCompat.TYPE_HELP:
                    i = PointerIconCompat.TYPE_HAND;
                    break;
                case PointerIconCompat.TYPE_HAND:
                    i = PointerIconCompat.TYPE_HELP;
                    break;
                case PointerIconCompat.TYPE_WAIT:
                    i = 1005;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i != -1) {
                this.context.state = i;
            }
        }
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void close() throws IOException {
        this.writer.close();
    }

    @Deprecated
    public void writeStartObject() {
        startObject();
    }

    @Deprecated
    public void writeEndObject() {
        endObject();
    }

    @Deprecated
    public void writeStartArray() {
        startArray();
    }

    @Deprecated
    public void writeEndArray() {
        endArray();
    }
}
