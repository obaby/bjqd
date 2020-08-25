package cn.xports.entity;

import java.io.Serializable;
import java.util.List;

public class Venue implements Serializable {
    private List<AdditionalService> additionalService;
    private String address;
    private String businessHours;
    private String centerId;
    private String cityCode;
    private String description;
    private String ecardCustId;
    private String freeVenueTag;
    private String iconName;
    private String latitude;
    private String longitude;
    private String path;
    private String phone;
    private String pinyin;
    private String serviceId;
    private String serviceName;
    private String venueId;
    private String venueImage;
    private List<String> venueImages;
    private String venueName;

    public String getCenterId() {
        return this.centerId;
    }

    public Venue setCenterId(String str) {
        this.centerId = str;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public Venue setAddress(String str) {
        this.address = str;
        return this;
    }

    public String getIconName() {
        return this.iconName;
    }

    public Venue setIconName(String str) {
        this.iconName = str;
        return this;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public Venue setCityCode(String str) {
        this.cityCode = str;
        return this;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public Venue setLatitude(String str) {
        this.latitude = str;
        return this;
    }

    public String getBusinessHours() {
        return this.businessHours;
    }

    public Venue setBusinessHours(String str) {
        this.businessHours = str;
        return this;
    }

    public String getVenueImage() {
        return this.venueImage;
    }

    public Venue setVenueImage(String str) {
        this.venueImage = str;
        return this;
    }

    public List<String> getVenueImages() {
        return this.venueImages;
    }

    public Venue setVenueImages(List<String> list) {
        this.venueImages = list;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public Venue setDescription(String str) {
        this.description = str;
        return this;
    }

    public String getVenueName() {
        return this.venueName;
    }

    public Venue setVenueName(String str) {
        this.venueName = str;
        return this;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public Venue setPinyin(String str) {
        this.pinyin = str;
        return this;
    }

    public String getPhone() {
        return this.phone;
    }

    public Venue setPhone(String str) {
        this.phone = str;
        return this;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public Venue setVenueId(String str) {
        this.venueId = str;
        return this;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public Venue setServiceId(String str) {
        this.serviceId = str;
        return this;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public Venue setServiceName(String str) {
        this.serviceName = str;
        return this;
    }

    public String getEcardCustId() {
        return this.ecardCustId;
    }

    public Venue setEcardCustId(String str) {
        this.ecardCustId = str;
        return this;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public Venue setLongitude(String str) {
        this.longitude = str;
        return this;
    }

    public String getFreeVenueTag() {
        return this.freeVenueTag;
    }

    public Venue setFreeVenueTag(String str) {
        this.freeVenueTag = str;
        return this;
    }

    public List<AdditionalService> getAdditionalService() {
        return this.additionalService;
    }

    public Venue setAdditionalService(List<AdditionalService> list) {
        this.additionalService = list;
        return this;
    }
}
