package cn.xports.baselib.bean;

import android.text.TextUtils;
import cn.xports.baselib.util.DataMapUtils;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataMap extends HashMap<String, Object> implements Serializable {
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    public DataMap() {
    }

    public DataMap(Map<? extends String, ?> map) {
        super(map);
    }

    private static void remove(Object obj, Iterator it) {
        if (obj == null) {
            it.remove();
        } else if (obj instanceof String) {
            if (TextUtils.isEmpty((String) obj)) {
                it.remove();
            }
        } else if (obj instanceof Collection) {
            if (((Collection) obj).isEmpty()) {
                it.remove();
            }
        } else if (obj instanceof Map) {
            if (((Map) obj).isEmpty()) {
                it.remove();
            }
        } else if ((obj instanceof Object[]) && ((Object[]) obj).length <= 0) {
            it.remove();
        }
    }

    private static void removeNullValue(Map map) {
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            remove(map.get(it.next()), it);
        }
    }

    public DataMap set(String str, Object obj) {
        put(str, obj);
        return this;
    }

    public DataMap copy(Map map, String... strArr) {
        for (String str : strArr) {
            if (map.get(str) != null) {
                put(str, map.get(str));
            }
        }
        return this;
    }

    public DataMap getDataMap(String str) {
        DataMap dataMap = new DataMap();
        if (isNotEmpty(str)) {
            dataMap.putAll(DataMapUtils.getMap(this, str));
        }
        return dataMap;
    }

    public HashMap<String, Object> getHashMap(String str) {
        if (DataMapUtils.getMap(this, str) != null) {
            return (HashMap) DataMapUtils.getMap(this, str);
        }
        return new HashMap<>();
    }

    public ArrayList<HashMap<String, Object>> getMapList(String str) {
        if (isNotEmpty(str)) {
            return (ArrayList) get(str);
        }
        return new ArrayList<>();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e A[LOOP:0: B:18:0x003e->B:20:0x0044, LOOP_START, PHI: r2 
  PHI: (r2v1 int) = (r2v0 int), (r2v2 int) binds: [B:17:0x003c, B:20:0x0044] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<cn.xports.baselib.bean.DataMap> getDataList(java.lang.String r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = r4.isNotEmpty(r5)
            if (r1 == 0) goto L_0x0055
            java.lang.Object r5 = r4.get(r5)
            boolean r1 = r5 instanceof java.util.ArrayList
            r2 = 0
            if (r1 == 0) goto L_0x0026
            r1 = r5
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r3 = r1.size()
            if (r3 <= 0) goto L_0x0026
            java.lang.Object r3 = r1.get(r2)
            boolean r3 = r3 instanceof cn.xports.baselib.bean.DataMap
            if (r3 == 0) goto L_0x003a
            return r1
        L_0x0026:
            boolean r1 = r5 instanceof java.lang.String
            if (r1 == 0) goto L_0x003a
            java.lang.String r5 = (java.lang.String) r5
            java.util.List r5 = cn.xports.baselib.util.DataMapUtils.fromJsonArray(r5)
            if (r5 != 0) goto L_0x0037
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
        L_0x0037:
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            return r5
        L_0x003a:
            java.util.List r5 = (java.util.List) r5
            if (r5 == 0) goto L_0x0055
        L_0x003e:
            int r1 = r5.size()
            if (r2 >= r1) goto L_0x0055
            cn.xports.baselib.bean.DataMap r1 = new cn.xports.baselib.bean.DataMap
            java.lang.Object r3 = r5.get(r2)
            java.util.Map r3 = (java.util.Map) r3
            r1.<init>(r3)
            r0.add(r1)
            int r2 = r2 + 1
            goto L_0x003e
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.baselib.bean.DataMap.getDataList(java.lang.String):java.util.ArrayList");
    }

    public boolean hasKey(String str) {
        return containsKey(str);
    }

    public boolean isNotEmpty(String str) {
        return hasKey(str) && get(str) != null && !"".equals(get(str));
    }

    public Date getDate(String str) {
        return (Date) get(str);
    }

    public Integer getIntValue(String str) {
        return Integer.valueOf(DataMapUtils.getIntValue(this, str));
    }

    public Integer getIntValue(String str, int i) {
        return Integer.valueOf(DataMapUtils.getIntValue(this, str, i));
    }

    public Integer getInteger(String str) {
        return DataMapUtils.getInteger(this, str);
    }

    public Integer getInteger(String str, Integer num) {
        return DataMapUtils.getInteger(this, str, num);
    }

    public Long getLongValue(String str) {
        return Long.valueOf(DataMapUtils.getLongValue(this, str));
    }

    public Long getLongValue(String str, Long l) {
        return Long.valueOf(DataMapUtils.getLongValue(this, str, l.longValue()));
    }

    public Long getLong(String str) {
        return DataMapUtils.getLong(this, str);
    }

    public Long getLong(String str, Long l) {
        return DataMapUtils.getLong(this, str, l);
    }

    public String getString(String str) {
        return DataMapUtils.getString(this, str, "");
    }

    public String getString(String str, String str2) {
        return DataMapUtils.getString(this, str, str2);
    }

    public Double getDouble(String str) {
        return DataMapUtils.getDouble(this, str);
    }

    public Double getDouble(String str, Double d) {
        return DataMapUtils.getDouble(this, str, d);
    }

    public double getDoubleValue(String str) {
        return DataMapUtils.getDoubleValue(this, str);
    }

    public double getDoubleValue(String str, double d) {
        return DataMapUtils.getDoubleValue(this, str, d);
    }

    public boolean getBooleanValue(String str) {
        return DataMapUtils.getBooleanValue(this, str);
    }

    public boolean getBooleanValue(String str, boolean z) {
        return DataMapUtils.getBooleanValue(this, str, z);
    }

    public Boolean getBoolean(String str) {
        return DataMapUtils.getBoolean(this, str);
    }

    public Boolean getBoolean(String str, Boolean bool) {
        return DataMapUtils.getBoolean(this, str, bool);
    }

    public String toJson() {
        removeNullValue(this);
        return new Gson().toJson(this);
    }

    public String getMessage() {
        return getString("message", "");
    }

    public int getError() {
        return getIntValue("error", 101001).intValue();
    }

    public boolean isOK(String str) {
        String string = getString(str);
        return "1".equals(string) || "OK".equals(string) || "ok".equals(string);
    }
}
