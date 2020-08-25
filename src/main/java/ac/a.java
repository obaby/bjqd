package ac;

import a.e;
import ac.b;
import com.linewell.licence.R;
import com.linewell.licence.entity.LincenseEntity;

public class a extends e<LincenseEntity, b> {

    /* renamed from: a  reason: collision with root package name */
    private b.a f64a;

    public a(b.a aVar) {
        super(R.layout.license_print_list_item);
        this.f64a = aVar;
    }

    /* access modifiers changed from: protected */
    public void a(b bVar, LincenseEntity lincenseEntity) {
        if (bVar != null && lincenseEntity != null) {
            bVar.a(lincenseEntity, this.f64a);
        }
    }
}
