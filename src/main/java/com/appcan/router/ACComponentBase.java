package com.appcan.router;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

public abstract class ACComponentBase {
    private static final String ATTRIBUTE_CLASSNAME = "className";
    private static final String ATTRIBUTE_MODULE_ID = "moduleId";
    public static final String ERROR_ID = "errorId";
    protected static ArrayList<ComponentEntity> components = new ArrayList<>();

    public void attachBaseContext(Context context) {
    }

    public void onAllInitialized(Context context) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public abstract void onCreate(Application application);

    public void onLowMemory() {
    }

    public void onTerminate() {
    }

    public void onTrimMemory(int i) {
    }

    public static class ComponentEntity {
        ACComponentBase componentObj;
        String id;

        public ComponentEntity(ACComponentBase aCComponentBase, String str) {
            this.componentObj = aCComponentBase;
            this.id = str;
        }

        public ACComponentBase getComponentObj() {
            return this.componentObj;
        }

        public void setComponentObj(ACComponentBase aCComponentBase) {
            this.componentObj = aCComponentBase;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }
    }

    public String getId(Class<? extends ACComponentBase> cls) {
        if (cls == null || components == null || components.size() == 0) {
            return ERROR_ID;
        }
        Iterator<ComponentEntity> it = components.iterator();
        while (it.hasNext()) {
            ComponentEntity next = it.next();
            if (next != null && next.getComponentObj().getClass() == cls) {
                return next.getId();
            }
        }
        return ERROR_ID;
    }

    public static String getId(String str) {
        if (str == null || components == null || components.size() == 0) {
            return ERROR_ID;
        }
        Iterator<ComponentEntity> it = components.iterator();
        while (it.hasNext()) {
            ComponentEntity next = it.next();
            if (next != null && next.getId().startsWith(str)) {
                return next.getId();
            }
        }
        return ERROR_ID;
    }

    public static ArrayList<ComponentEntity> getComponents(XmlResourceParser xmlResourceParser) {
        if (components.size() != 0) {
            return components;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    stringBuffer.append(xmlResourceParser.getName());
                    stringBuffer.append(10);
                    if (xmlResourceParser.getAttributeCount() > 0) {
                        initComponents(xmlResourceParser);
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        xmlResourceParser.close();
        return components;
    }

    private static void initComponents(XmlResourceParser xmlResourceParser) {
        int attributeCount = xmlResourceParser.getAttributeCount();
        String str = null;
        ACComponentBase aCComponentBase = null;
        for (int i = 0; i < attributeCount; i++) {
            if (ATTRIBUTE_CLASSNAME.equals(xmlResourceParser.getAttributeName(i))) {
                try {
                    aCComponentBase = (ACComponentBase) Class.forName(xmlResourceParser.getAttributeValue(i)).newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            } else if (ATTRIBUTE_MODULE_ID.equals(xmlResourceParser.getAttributeName(i))) {
                str = xmlResourceParser.getAttributeValue(i);
            }
        }
        if (!TextUtils.isEmpty(str) && aCComponentBase != null) {
            Iterator<ComponentEntity> it = components.iterator();
            while (it.hasNext()) {
                ComponentEntity next = it.next();
                if (next != null && str.equals(next.getId())) {
                    return;
                }
            }
            components.add(new ComponentEntity(aCComponentBase, str));
        }
    }
}
