package coband.bsit.com.integral.body;

import com.convenient.qd.core.base.AppInfo;
import java.io.Serializable;

public class CreditListBody implements Serializable {
    private AppInfo appInfo = new AppInfo();
    private int listType;
    private int page;

    public int getPage() {
        return this.page;
    }

    public void setPage(int i) {
        this.page = i;
    }

    public int getListType() {
        return this.listType;
    }

    public void setListType(int i) {
        this.listType = i;
    }
}
