package ae;

import a.e;
import ae.c;
import com.linewell.licence.R;
import com.linewell.licence.entity.FootprintEntity;

public class a extends e<FootprintEntity, b> {

    /* renamed from: a  reason: collision with root package name */
    private c.a f75a;
    private String q;

    public a(c.a aVar) {
        super(R.layout.msg_time_main_item);
        this.f75a = aVar;
    }

    /* access modifiers changed from: protected */
    public void a(b bVar, FootprintEntity footprintEntity) {
        if (bVar != null && footprintEntity != null) {
            bVar.a(footprintEntity, this.q, this.f75a, l().size());
        }
    }

    public void a(String str) {
        this.q = str;
    }
}
