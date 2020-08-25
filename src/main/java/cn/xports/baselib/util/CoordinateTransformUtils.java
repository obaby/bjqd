package cn.xports.baselib.util;

public class CoordinateTransformUtils {
    private static final double FLATTENING = 0.006693421622965943d;
    private static final double PI = 3.141592653589793d;
    private static final double SEMI_MAJOR = 6378245.0d;
    private static final double X_PI = 52.35987755982988d;

    public static boolean outOfChina(double d, double d2) {
        return d < 72.004d || d > 137.8347d || d2 < 0.8293d || d2 > 55.8271d;
    }

    public static Point wgs84ToGcj02(double d, double d2) {
        if (outOfChina(d, d2)) {
            return new Point(d, d2);
        }
        double[] offset = offset(d, d2);
        return new Point(d + offset[0], d2 + offset[1]);
    }

    public static Point gcj02ToWgs84(double d, double d2) {
        if (outOfChina(d, d2)) {
            return new Point(d, d2);
        }
        double[] offset = offset(d, d2);
        return new Point(d - offset[0], d2 - offset[1]);
    }

    public static Point gcj02ToWgs84Exactly(double d, double d2) {
        double d3;
        double d4;
        double d5 = d;
        double d6 = d2;
        if (outOfChina(d, d2)) {
            return new Point(d5, d6);
        }
        double d7 = d6 - 0.01d;
        double d8 = d5 - 0.01d;
        double d9 = d6 + 0.01d;
        double d10 = 0.01d + d5;
        double d11 = 0.0d;
        do {
            d3 = (d7 + d9) / 2.0d;
            d4 = (d8 + d10) / 2.0d;
            Point wgs84ToGcj02 = wgs84ToGcj02(d4, d3);
            double lng = wgs84ToGcj02.getLng() - d5;
            double lat = wgs84ToGcj02.getLat() - d6;
            if (Math.abs(lat) < 1.0E-9d && Math.abs(lng) < 1.0E-9d) {
                break;
            }
            if (lat > 0.0d) {
                d9 = d3;
            } else {
                d7 = d3;
            }
            if (lng > 0.0d) {
                d10 = d4;
            } else {
                d8 = d4;
            }
            d11 += 1.0d;
        } while (d11 <= 10000.0d);
        return new Point(d4, d3);
    }

    public static Point gcj02ToBd09(double d, double d2) {
        double sqrt = Math.sqrt((d * d) + (d2 * d2)) + (Math.sin(d2 * X_PI) * 2.0E-5d);
        double atan2 = Math.atan2(d2, d) + (Math.cos(d * X_PI) * 3.0E-6d);
        return new Point((Math.cos(atan2) * sqrt) + 0.0065d, (sqrt * Math.sin(atan2)) + 0.006d);
    }

    public static Point bd09ToGcj02(double d, double d2) {
        double d3 = d - 0.0065d;
        double d4 = d2 - 0.006d;
        double sqrt = Math.sqrt((d3 * d3) + (d4 * d4)) - (Math.sin(d4 * X_PI) * 2.0E-5d);
        double atan2 = Math.atan2(d4, d3) - (Math.cos(d3 * X_PI) * 3.0E-6d);
        return new Point(Math.cos(atan2) * sqrt, sqrt * Math.sin(atan2));
    }

    public static Point wgs84ToBd09(double d, double d2) {
        Point wgs84ToGcj02 = wgs84ToGcj02(d, d2);
        return gcj02ToBd09(wgs84ToGcj02.getLng(), wgs84ToGcj02.getLat());
    }

    public static Point bd09ToWgs84(double d, double d2) {
        Point bd09ToGcj02 = bd09ToGcj02(d, d2);
        return gcj02ToWgs84(bd09ToGcj02.getLng(), bd09ToGcj02.getLat());
    }

    private static double transformLng(double d, double d2) {
        double d3 = d * 0.1d;
        return d + 300.0d + (d2 * 2.0d) + (d3 * d) + (d3 * d2) + (Math.sqrt(Math.abs(d)) * 0.1d) + ((((Math.sin((6.0d * d) * PI) * 20.0d) + (Math.sin((d * 2.0d) * PI) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(d * PI) * 20.0d) + (Math.sin((d / 3.0d) * PI) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d / 12.0d) * PI) * 150.0d) + (Math.sin((d / 30.0d) * PI) * 300.0d)) * 2.0d) / 3.0d);
    }

    private static double transformLat(double d, double d2) {
        double d3 = d * 2.0d;
        double sqrt = -100.0d + d3 + (d2 * 3.0d) + (d2 * 0.2d * d2) + (0.1d * d * d2) + (Math.sqrt(Math.abs(d)) * 0.2d) + ((((Math.sin((d * 6.0d) * PI) * 20.0d) + (Math.sin(d3 * PI) * 20.0d)) * 2.0d) / 3.0d);
        double d4 = d2 * PI;
        return sqrt + ((((Math.sin(d4) * 20.0d) + (Math.sin((d2 / 3.0d) * PI) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * PI) * 160.0d) + (Math.sin(d4 / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    public static double[] offset(double d, double d2) {
        double d3 = d - 105.0d;
        double d4 = d2 - 35.0d;
        double transformLng = transformLng(d3, d4);
        double transformLat = transformLat(d3, d4);
        double d5 = (d2 / 180.0d) * PI;
        double sin = Math.sin(d5);
        double d6 = 1.0d - ((FLATTENING * sin) * sin);
        double sqrt = Math.sqrt(d6);
        return new double[]{(transformLng * 180.0d) / (((SEMI_MAJOR / sqrt) * Math.cos(d5)) * PI), (transformLat * 180.0d) / ((6335552.717000426d / (d6 * sqrt)) * PI)};
    }

    public static class Point {
        private double lat;
        private double lng;

        public Point(double d, double d2) {
            this.lng = d;
            this.lat = d2;
        }

        public double getLng() {
            return this.lng;
        }

        public Point setLng(double d) {
            this.lng = d;
            return this;
        }

        public double getLat() {
            return this.lat;
        }

        public Point setLat(double d) {
            this.lat = d;
            return this;
        }
    }
}
