package cn.xports.qd2.entity;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class CampItemListResult extends Response implements Serializable {
    private List<CampItem> campItemList;
    private List<CampPackage> packageList;

    public List<CampItem> getCampItemList() {
        return this.campItemList;
    }

    public void setCampItemList(List<CampItem> list) {
        this.campItemList = list;
    }

    public List<CampPackage> getPackageList() {
        return this.packageList;
    }

    public void setPackageList(List<CampPackage> list) {
        this.packageList = list;
    }

    public static class CampPackage implements Serializable {
        private long campId;
        private long campItemId;
        private String campItems;
        private String description;
        private String id;
        private boolean isChecked;
        private String name;
        private int price;
        private String state;

        public long getCampItemId() {
            return this.campItemId;
        }

        public CampPackage setCampItemId(long j) {
            this.campItemId = j;
            return this;
        }

        public boolean isChecked() {
            return this.isChecked;
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public long getCampId() {
            return this.campId;
        }

        public void setCampId(long j) {
            this.campId = j;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public int getPrice() {
            return this.price;
        }

        public void setPrice(int i) {
            this.price = i;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getCampItems() {
            return this.campItems;
        }

        public void setCampItems(String str) {
            this.campItems = str;
        }
    }
}
