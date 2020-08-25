package cn.xports.qd2.circle.videoPlayer.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class VideoInfo implements Parcelable {
    public static final Parcelable.Creator<VideoInfo> CREATOR = new Parcelable.Creator<VideoInfo>() {
        public VideoInfo createFromParcel(Parcel parcel) {
            return new VideoInfo(parcel);
        }

        public VideoInfo[] newArray(int i) {
            return new VideoInfo[i];
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private Bitmap f453b;
    private String filePath;
    private int img;
    private String mimeType;
    private String size;
    private String time;
    private String title;

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "VideoInfo{filePath='" + this.filePath + '\'' + ", mimeType='" + this.mimeType + '\'' + ", b=" + this.f453b + ", title='" + this.title + '\'' + ", time='" + this.time + '\'' + ", size='" + this.size + '\'' + '}';
    }

    public VideoInfo() {
    }

    public VideoInfo(String str, String str2, int i) {
        this.filePath = str;
        this.title = str2;
        this.img = i;
    }

    public VideoInfo(String str, String str2, String str3) {
        this.filePath = str;
        this.title = str2;
        this.time = str3;
    }

    public VideoInfo(String str, String str2, Bitmap bitmap, String str3, String str4, String str5) {
        this.filePath = str;
        this.mimeType = str2;
        this.f453b = bitmap;
        this.title = str3;
        this.time = str4;
        this.size = str5;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public Bitmap getB() {
        return this.f453b;
    }

    public void setB(Bitmap bitmap) {
        this.f453b = bitmap;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public int getImg() {
        return this.img;
    }

    public void setImg(int i) {
        this.img = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.filePath);
        parcel.writeString(this.mimeType);
        parcel.writeParcelable(this.f453b, i);
        parcel.writeString(this.title);
        parcel.writeString(this.time);
        parcel.writeString(this.size);
        parcel.writeInt(this.img);
    }

    protected VideoInfo(Parcel parcel) {
        this.filePath = parcel.readString();
        this.mimeType = parcel.readString();
        this.f453b = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.title = parcel.readString();
        this.time = parcel.readString();
        this.size = parcel.readString();
        this.img = parcel.readInt();
    }
}
