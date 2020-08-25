package cn.xports.qd2.entity;

import android.support.annotation.NonNull;
import com.blankj.utilcode.util.GsonUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewElementData implements Serializable {
    private long campId;
    private String createTime;
    private String elementId;
    private String elementType;
    private String inputType;
    private String name;
    private String options;
    private String order;
    private String required;
    private String state;
    private String updateTime;
    private String value;

    public String getValue() {
        return this.value;
    }

    public ViewElementData setValue(String str) {
        this.value = str;
        return this;
    }

    public String getElementId() {
        return this.elementId;
    }

    public ViewElementData setElementId(String str) {
        this.elementId = str;
        return this;
    }

    public long getCampId() {
        return this.campId;
    }

    public ViewElementData setCampId(long j) {
        this.campId = j;
        return this;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public ViewElementData setCreateTime(String str) {
        this.createTime = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ViewElementData setName(String str) {
        this.name = str;
        return this;
    }

    public String getOptions() {
        return this.options;
    }

    public ViewElementData setOptions(String str) {
        this.options = str;
        return this;
    }

    public String getInputType() {
        return this.inputType;
    }

    public ViewElementData setInputType(String str) {
        this.inputType = str;
        return this;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public ViewElementData setUpdateTime(String str) {
        this.updateTime = str;
        return this;
    }

    public String getState() {
        return this.state;
    }

    public ViewElementData setState(String str) {
        this.state = str;
        return this;
    }

    public String getElementType() {
        return this.elementType;
    }

    public ViewElementData setElementType(String str) {
        this.elementType = str;
        return this;
    }

    public String getRequired() {
        return this.required;
    }

    public ViewElementData setRequired(String str) {
        this.required = str;
        return this;
    }

    public String getOrder() {
        return this.order;
    }

    public ViewElementData setOrder(String str) {
        this.order = str;
        return this;
    }

    public String toPostJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("elementId", this.elementId);
            jSONObject.put("value", this.value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static class Option {
        private boolean isSelect;
        private String name;
        private String value;

        public boolean isSelect() {
            return this.isSelect;
        }

        public Option setSelect(boolean z) {
            this.isSelect = z;
            return this;
        }

        public String getName() {
            return this.name;
        }

        public Option setName(String str) {
            this.name = str;
            return this;
        }

        public String getValue() {
            return this.value;
        }

        public Option setValue(String str) {
            this.value = str;
            return this;
        }

        @NonNull
        public String toString() {
            if (this.name == null) {
                return "";
            }
            return this.name;
        }

        public String toJson() {
            return GsonUtils.toJson(new Option().setName(this.name).setValue(this.value));
        }
    }
}
