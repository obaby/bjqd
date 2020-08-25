package c;

import android.content.ContextWrapper;
import com.google.gson.Gson;
import com.linewell.licence.Dzzz;
import com.linewell.licence.entity.AESCertificate;
import com.linewell.licence.entity.User;
import com.linewell.licence.ui.d;
import com.linewell.licence.util.u;

public class a extends ContextWrapper implements d {

    /* renamed from: a  reason: collision with root package name */
    private static a f446a;

    /* renamed from: b  reason: collision with root package name */
    private static e f447b;

    /* renamed from: c  reason: collision with root package name */
    private b f448c;
    private String d;
    private User e;
    private boolean f;
    private String g = "";

    public static a a(e eVar) {
        if (f446a == null) {
            synchronized (a.class) {
                if (f446a == null) {
                    f447b = eVar;
                    f446a = new a();
                }
            }
        }
        return f446a;
    }

    public a() {
        super(Dzzz.getmApplication());
    }

    public a a(User user, boolean z, String str) {
        this.e = user;
        this.g = str;
        if (user == null) {
            return this;
        }
        this.f = z;
        if (this.f448c != null) {
            this.f448c.a(f447b);
            u.c("新设备-----------0》" + z + "," + user.isRealName.equals("1") + ",111");
            this.f448c.a(this, f447b);
        }
        return this;
    }

    /* renamed from: a */
    public a b(String str) {
        this.d = str;
        a();
        return this;
    }

    public a a() {
        String str;
        String str2 = "";
        AESCertificate aESCertificate = new AESCertificate();
        aESCertificate.setUserId(this.e.userId);
        String str3 = this.e.securityPasswd.trim() + "|" + this.d + "|" + this.e.userId;
        u.c("result-------------->" + str3);
        try {
            str2 = aj.a.a().a(str3, this.e.userId.substring(0, 16), this.e.userId.substring(16, 32));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!this.g.equals("nojson")) {
            Gson gson = new Gson();
            aESCertificate.setSignatureValue(str2.replaceAll("\\s", ""));
            str = gson.toJson(aESCertificate);
        } else {
            str = str2.replaceAll("\\s", "");
        }
        u.c(this.g + "signValue--ddd------》" + str);
        this.g = "";
        if (this.f448c != null) {
            d.a().setAESsignResult(this.e.userId, str);
            this.f448c.a(str);
        }
        return this;
    }

    public a a(b bVar) {
        this.f448c = bVar;
        return this;
    }
}
