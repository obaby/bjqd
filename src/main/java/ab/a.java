package ab;

import a.e;
import com.linewell.licence.R;
import com.linewell.licence.entity.MaterialEntity;
import com.linewell.licence.ui.license.material.select.SelectMaterialAdapter;

public class a extends e<MaterialEntity, b> {

    /* renamed from: a  reason: collision with root package name */
    private SelectMaterialAdapter f62a;
    private String q;
    private String r;

    public a(String str, String str2, SelectMaterialAdapter selectMaterialAdapter) {
        super(R.layout.adapter_select_materiallist);
        this.f62a = selectMaterialAdapter;
        this.q = str2;
        this.r = str;
    }

    /* access modifiers changed from: protected */
    public void a(b bVar, MaterialEntity materialEntity) {
        bVar.a(materialEntity, this.r, this.q, this.f62a);
    }
}
