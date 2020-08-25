package anet.channel.monitor;

/* compiled from: Taobao */
class e {

    /* renamed from: a  reason: collision with root package name */
    private long f205a = 0;

    /* renamed from: b  reason: collision with root package name */
    private double f206b;

    /* renamed from: c  reason: collision with root package name */
    private double f207c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i = 0.0d;
    private double j = 0.0d;
    private double k = 0.0d;

    e() {
    }

    public double a(double d2, double d3) {
        double d4 = d2 / d3;
        if (d4 >= 8.0d) {
            if (this.f205a == 0) {
                this.i = d4;
                this.h = this.i;
                this.d = this.h * 0.1d;
                this.f207c = this.h * 0.02d;
                this.e = this.h * 0.1d * this.h;
            } else if (this.f205a == 1) {
                this.j = d4;
                this.h = this.j;
            } else {
                double d5 = d4 - this.j;
                this.i = this.j;
                this.j = d4;
                this.f206b = d4 / 0.95d;
                this.g = this.f206b - (this.h * 0.95d);
                char c2 = 0;
                double sqrt = Math.sqrt(this.d);
                if (this.g >= 4.0d * sqrt) {
                    this.g = (this.g * 0.75d) + (sqrt * 2.0d);
                    c2 = 1;
                } else if (this.g <= -4.0d * sqrt) {
                    this.g = (sqrt * -1.0d) + (this.g * 0.75d);
                    c2 = 2;
                }
                this.d = Math.min(Math.max(Math.abs((this.d * 1.05d) - ((this.g * 0.0025d) * this.g)), this.d * 0.8d), this.d * 1.25d);
                this.f = this.e / ((0.9025d * this.e) + this.d);
                this.h = this.h + (1.0526315789473684d * d5) + (this.f * this.g);
                if (c2 == 1) {
                    this.h = Math.min(this.h, this.f206b);
                } else if (c2 == 2) {
                    this.h = Math.max(this.h, this.f206b);
                }
                this.e = (1.0d - (0.95d * this.f)) * (this.e + this.f207c);
            }
            if (this.h < 0.0d) {
                this.k = this.j * 0.7d;
                this.h = this.k;
            } else {
                this.k = this.h;
            }
            return this.k;
        } else if (this.f205a != 0) {
            return this.k;
        } else {
            this.k = d4;
            return this.k;
        }
    }

    public void a() {
        this.f205a = 0;
        this.k = 0.0d;
    }
}
