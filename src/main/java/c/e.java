package c;

import android.text.TextUtils;
import com.linewell.licence.entity.User;
import com.linewell.licence.util.u;

public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final String f449a = "CY_SJD";

    /* renamed from: b  reason: collision with root package name */
    public static final String f450b = "DEFAULT";
    private static e g;

    /* renamed from: c  reason: collision with root package name */
    private boolean f451c = false;
    private b d;
    private String e;
    private d f;
    private String h;
    private String i = "";

    private e(String str) {
    }

    public static e a(String str) {
        if (g == null) {
            synchronized (e.class) {
                if (g == null) {
                    g = new e(str);
                }
            }
        }
        return g;
    }

    public e a(b bVar) {
        this.d = bVar;
        return this;
    }

    public e a(User user, String str, boolean z, String str2) {
        this.i = str2;
        this.h = f450b;
        if (!"".equals(this.h)) {
            if (f449a.equals(this.h)) {
                b(user, str, z);
            } else {
                a(user, z);
            }
        }
        return this;
    }

    public e a(User user, String str, boolean z) {
        this.h = f450b;
        if (!"".equals(this.h)) {
            if (f449a.equals(this.h)) {
                b(user, str, z);
            } else {
                a(user, z);
            }
        }
        return this;
    }

    public e a(boolean z, User user, String str, boolean z2) {
        this.f451c = z;
        this.h = f450b;
        if (!"".equals(this.h)) {
            if (f449a.equals(this.h)) {
                b(user, str, z2);
            } else {
                a(user, z2);
            }
        }
        return this;
    }

    public e b(String str) {
        if (!TextUtils.isEmpty(str) && this.f != null) {
            this.f.b(str);
        }
        return this;
    }

    public void b(User user, String str, boolean z) {
        u.c("isAnewInit------>" + this.f451c);
        this.i = "";
    }

    public void a(User user, boolean z) {
        this.f = a.a(this).a(this.d).a(user, z, this.i);
        this.i = "";
    }

    public String a() {
        return this.h;
    }
}
