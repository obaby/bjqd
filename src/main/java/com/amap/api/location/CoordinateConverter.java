package com.amap.api.location;

import android.content.Context;
import com.loc.di;
import com.loc.dj;
import com.loc.dq;

public class CoordinateConverter {

    /* renamed from: a  reason: collision with root package name */
    DPoint f821a = null;

    /* renamed from: b  reason: collision with root package name */
    private Context f822b;

    /* renamed from: c  reason: collision with root package name */
    private CoordType f823c = null;
    private DPoint d = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter(Context context) {
        this.f822b = context;
    }

    public static float calculateLineDistance(DPoint dPoint, DPoint dPoint2) {
        try {
            return dq.a(dPoint, dPoint2);
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    public static boolean isAMapDataAvailable(double d2, double d3) {
        return di.a(d2, d3);
    }

    public synchronized DPoint convert() throws Exception {
        DPoint a2;
        if (this.f823c == null) {
            throw new IllegalArgumentException("转换坐标类型不能为空");
        } else if (this.d == null) {
            throw new IllegalArgumentException("转换坐标源不能为空");
        } else if (this.d.getLongitude() > 180.0d || this.d.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("请传入合理经度");
        } else if (this.d.getLatitude() > 90.0d || this.d.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("请传入合理纬度");
        } else if (!di.a(this.d.getLatitude(), this.d.getLongitude())) {
            return this.d;
        } else {
            switch (this.f823c) {
                case BAIDU:
                    a2 = dj.a(this.d);
                    break;
                case MAPBAR:
                    a2 = dj.b(this.f822b, this.d);
                    break;
                case MAPABC:
                case SOSOMAP:
                case ALIYUN:
                case GOOGLE:
                    a2 = this.d;
                    break;
                case GPS:
                    a2 = dj.a(this.f822b, this.d);
                    break;
            }
            this.f821a = a2;
            return this.f821a;
        }
    }

    public synchronized CoordinateConverter coord(DPoint dPoint) throws Exception {
        if (dPoint == null) {
            throw new IllegalArgumentException("传入经纬度对象为空");
        } else if (dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("请传入合理经度");
        } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("请传入合理纬度");
        } else {
            this.d = dPoint;
        }
        return this;
    }

    public synchronized CoordinateConverter from(CoordType coordType) {
        this.f823c = coordType;
        return this;
    }
}
