package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class SerializeFilterable {
    protected List<AfterFilter> afterFilters = null;
    protected List<BeforeFilter> beforeFilters = null;
    protected List<ContextValueFilter> contextValueFilters = null;
    protected List<LabelFilter> labelFilters = null;
    protected List<NameFilter> nameFilters = null;
    protected List<PropertyFilter> propertyFilters = null;
    protected List<PropertyPreFilter> propertyPreFilters = null;
    protected List<ValueFilter> valueFilters = null;
    protected boolean writeDirect = true;

    public List<BeforeFilter> getBeforeFilters() {
        if (this.beforeFilters == null) {
            this.beforeFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.beforeFilters;
    }

    public List<AfterFilter> getAfterFilters() {
        if (this.afterFilters == null) {
            this.afterFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.afterFilters;
    }

    public List<NameFilter> getNameFilters() {
        if (this.nameFilters == null) {
            this.nameFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.nameFilters;
    }

    public List<PropertyPreFilter> getPropertyPreFilters() {
        if (this.propertyPreFilters == null) {
            this.propertyPreFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.propertyPreFilters;
    }

    public List<LabelFilter> getLabelFilters() {
        if (this.labelFilters == null) {
            this.labelFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.labelFilters;
    }

    public List<PropertyFilter> getPropertyFilters() {
        if (this.propertyFilters == null) {
            this.propertyFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.propertyFilters;
    }

    public List<ContextValueFilter> getContextValueFilters() {
        if (this.contextValueFilters == null) {
            this.contextValueFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.contextValueFilters;
    }

    public List<ValueFilter> getValueFilters() {
        if (this.valueFilters == null) {
            this.valueFilters = new ArrayList();
            this.writeDirect = false;
        }
        return this.valueFilters;
    }

    public void addFilter(SerializeFilter serializeFilter) {
        if (serializeFilter != null) {
            if (serializeFilter instanceof PropertyPreFilter) {
                getPropertyPreFilters().add((PropertyPreFilter) serializeFilter);
            }
            if (serializeFilter instanceof NameFilter) {
                getNameFilters().add((NameFilter) serializeFilter);
            }
            if (serializeFilter instanceof ValueFilter) {
                getValueFilters().add((ValueFilter) serializeFilter);
            }
            if (serializeFilter instanceof ContextValueFilter) {
                getContextValueFilters().add((ContextValueFilter) serializeFilter);
            }
            if (serializeFilter instanceof PropertyFilter) {
                getPropertyFilters().add((PropertyFilter) serializeFilter);
            }
            if (serializeFilter instanceof BeforeFilter) {
                getBeforeFilters().add((BeforeFilter) serializeFilter);
            }
            if (serializeFilter instanceof AfterFilter) {
                getAfterFilters().add((AfterFilter) serializeFilter);
            }
            if (serializeFilter instanceof LabelFilter) {
                getLabelFilters().add((LabelFilter) serializeFilter);
            }
        }
    }

    public boolean applyName(JSONSerializer jSONSerializer, Object obj, String str) {
        if (jSONSerializer.propertyPreFilters != null) {
            for (PropertyPreFilter apply : jSONSerializer.propertyPreFilters) {
                if (!apply.apply(jSONSerializer, obj, str)) {
                    return false;
                }
            }
        }
        if (this.propertyPreFilters == null) {
            return true;
        }
        for (PropertyPreFilter apply2 : this.propertyPreFilters) {
            if (!apply2.apply(jSONSerializer, obj, str)) {
                return false;
            }
        }
        return true;
    }

    public boolean apply(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        if (jSONSerializer.propertyFilters != null) {
            for (PropertyFilter apply : jSONSerializer.propertyFilters) {
                if (!apply.apply(obj, str, obj2)) {
                    return false;
                }
            }
        }
        if (this.propertyFilters == null) {
            return true;
        }
        for (PropertyFilter apply2 : this.propertyFilters) {
            if (!apply2.apply(obj, str, obj2)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public String processKey(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        if (jSONSerializer.nameFilters != null) {
            for (NameFilter process : jSONSerializer.nameFilters) {
                str = process.process(obj, str, obj2);
            }
        }
        if (this.nameFilters != null) {
            for (NameFilter process2 : this.nameFilters) {
                str = process2.process(obj, str, obj2);
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public Object processValue(JSONSerializer jSONSerializer, BeanContext beanContext, Object obj, String str, Object obj2) {
        boolean z;
        if (obj2 != null) {
            if ((jSONSerializer.out.writeNonStringValueAsString || !(beanContext == null || (beanContext.getFeatures() & SerializerFeature.WriteNonStringValueAsString.mask) == 0)) && (((z = obj2 instanceof Number)) || (obj2 instanceof Boolean))) {
                String str2 = null;
                if (z && beanContext != null) {
                    str2 = beanContext.getFormat();
                }
                if (str2 != null) {
                    obj2 = new DecimalFormat(str2).format(obj2);
                } else {
                    obj2 = obj2.toString();
                }
            } else if (beanContext != null && beanContext.isJsonDirect()) {
                obj2 = JSON.parse((String) obj2);
            }
        }
        if (jSONSerializer.valueFilters != null) {
            for (ValueFilter process : jSONSerializer.valueFilters) {
                obj2 = process.process(obj, str, obj2);
            }
        }
        List<ValueFilter> list = this.valueFilters;
        if (list != null) {
            for (ValueFilter process2 : list) {
                obj2 = process2.process(obj, str, obj2);
            }
        }
        if (jSONSerializer.contextValueFilters != null) {
            for (ContextValueFilter process3 : jSONSerializer.contextValueFilters) {
                obj2 = process3.process(beanContext, obj, str, obj2);
            }
        }
        if (this.contextValueFilters != null) {
            for (ContextValueFilter process4 : this.contextValueFilters) {
                obj2 = process4.process(beanContext, obj, str, obj2);
            }
        }
        return obj2;
    }

    /* access modifiers changed from: protected */
    public boolean writeDirect(JSONSerializer jSONSerializer) {
        return jSONSerializer.out.writeDirect && this.writeDirect && jSONSerializer.writeDirect;
    }
}
