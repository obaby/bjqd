package cn.xports.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class ProductListResult extends Response implements Serializable {
    private String shopId;
    private List<ValidProduct> validProducts;
    private List<ValidService> validServices;

    public List<ValidService> getValidServices() {
        return this.validServices;
    }

    public void setValidServices(List<ValidService> list) {
        this.validServices = list;
    }

    public List<ValidProduct> getValidProducts() {
        return this.validProducts;
    }

    public void setValidProducts(List<ValidProduct> list) {
        this.validProducts = list;
    }

    public String getShopId() {
        return this.shopId;
    }

    public void setShopId(String str) {
        this.shopId = str;
    }
}
