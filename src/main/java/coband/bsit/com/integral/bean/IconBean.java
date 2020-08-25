package coband.bsit.com.integral.bean;

import com.convenient.qd.core.bean.MenuTab;
import java.io.Serializable;
import java.util.List;

public class IconBean implements Serializable {
    private List<MenuTab> exchangeList;
    private List<MenuTab> inviteList;

    public List<MenuTab> getInviteList() {
        return this.inviteList;
    }

    public void setInviteList(List<MenuTab> list) {
        this.inviteList = list;
    }

    public List<MenuTab> getExchangeList() {
        return this.exchangeList;
    }

    public void setExchangeList(List<MenuTab> list) {
        this.exchangeList = list;
    }
}
