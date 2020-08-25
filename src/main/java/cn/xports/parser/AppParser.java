package cn.xports.parser;

import cn.xports.baselib.bean.Response;
import cn.xports.entity.App;
import java.io.Serializable;

public class AppParser extends Response implements Serializable {
    private App app;

    public App getApp() {
        return this.app;
    }

    public void setApp(App app2) {
        this.app = app2;
    }
}
