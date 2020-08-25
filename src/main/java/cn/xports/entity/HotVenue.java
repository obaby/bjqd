package cn.xports.entity;

import java.io.Serializable;
import java.util.List;

public class HotVenue implements Serializable {
    private String additionalService;
    private String address;
    private String businessHours;
    private Long centerId;
    private String cityCode;
    private Double currentLatitude = null;
    private Double currentLongitude = null;
    private long ecardCustId;
    private String iconName;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String pinyin;
    private Long venueId;
    private List<VenueMediaResource> venueMediaResourceList;
    private String venueName;

    public String getHotVenueImage() {
        return "StringUtil.getVenueImage(venueMediaResourceList)";
    }

    public String getAdditionalService() {
        return this.additionalService;
    }

    public void setAdditionalService(String str) {
        this.additionalService = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getBusinessHours() {
        return this.businessHours;
    }

    public void setBusinessHours(String str) {
        this.businessHours = str;
    }

    public Long getCenterId() {
        return this.centerId;
    }

    public void setCenterId(Long l) {
        this.centerId = l;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public long getEcardCustId() {
        return this.ecardCustId;
    }

    public void setEcardCustId(long j) {
        this.ecardCustId = j;
    }

    public String getIconName() {
        return this.iconName;
    }

    public void setIconName(String str) {
        this.iconName = str;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }

    public Long getVenueId() {
        return this.venueId;
    }

    public void setVenueId(Long l) {
        this.venueId = l;
    }

    public String getVenueName() {
        return this.venueName;
    }

    public void setVenueName(String str) {
        this.venueName = str;
    }

    public Double getCurrentLatitude() {
        return this.currentLatitude;
    }

    public void setCurrentLatitude(double d) {
        this.currentLatitude = Double.valueOf(d);
    }

    public Double getCurrentLongitude() {
        return this.currentLongitude;
    }

    public void setCurrentLongitude(double d) {
        this.currentLongitude = Double.valueOf(d);
    }

    public List<VenueMediaResource> getVenueMediaResourceList() {
        return this.venueMediaResourceList;
    }

    public void setVenueMediaResourceList(List<VenueMediaResource> list) {
        this.venueMediaResourceList = list;
    }
}
