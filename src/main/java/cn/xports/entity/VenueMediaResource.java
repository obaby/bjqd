package cn.xports.entity;

import java.io.Serializable;

public class VenueMediaResource implements Serializable {
    private Long centerId;
    private Long createStaffId;
    private String createTime;
    private Long id;
    private int mediaOrder;
    private String mediaType;
    private String path;
    private String state;
    private String updateTime;
    private String useType;
    private Long venueId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public Long getCenterId() {
        return this.centerId;
    }

    public void setCenterId(Long l) {
        this.centerId = l;
    }

    public Long getVenueId() {
        return this.venueId;
    }

    public void setVenueId(Long l) {
        this.venueId = l;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(String str) {
        this.mediaType = str;
    }

    public String getUseType() {
        return this.useType;
    }

    public void setUseType(String str) {
        this.useType = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public int getMediaOrder() {
        return this.mediaOrder;
    }

    public void setMediaOrder(int i) {
        this.mediaOrder = i;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public Long getCreateStaffId() {
        return this.createStaffId;
    }

    public void setCreateStaffId(Long l) {
        this.createStaffId = l;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }
}
