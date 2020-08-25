package cn.xports.qd2.circle.videoPlayer.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {
    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        public Video createFromParcel(Parcel parcel) {
            return new Video(parcel);
        }

        public Video[] newArray(int i) {
            return new Video[i];
        }
    };
    private int img;
    private String name;
    private String url;

    public int describeContents() {
        return 0;
    }

    public Video(String str, String str2, int i) {
        this.url = str;
        this.name = str2;
        this.img = i;
    }

    public String toString() {
        return "Video{url='" + this.url + '\'' + ", name='" + this.name + '\'' + ", img=" + this.img + '}';
    }

    public Video() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getImg() {
        return this.img;
    }

    public void setImg(int i) {
        this.img = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.name);
        parcel.writeInt(this.img);
    }

    protected Video(Parcel parcel) {
        this.url = parcel.readString();
        this.name = parcel.readString();
        this.img = parcel.readInt();
    }
}
