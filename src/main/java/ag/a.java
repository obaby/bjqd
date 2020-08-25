package ag;

import a.e;
import com.linewell.licence.R;
import com.linewell.licence.entity.MaterialEntity;
import com.linewell.licence.ui.license.material.select.SelectMaterialAdapter;
import java.util.ArrayList;
import java.util.List;

public class a extends e<MaterialEntity, b> {

    /* renamed from: a  reason: collision with root package name */
    private SelectMaterialAdapter f85a;
    private String q;
    private String r;
    private List<MaterialEntity> s;

    public a(String str, String str2, SelectMaterialAdapter selectMaterialAdapter, ArrayList<MaterialEntity> arrayList) {
        super(R.layout.adapter_select_materiallist);
        this.f85a = selectMaterialAdapter;
        this.q = str2;
        this.r = str;
        this.s = arrayList;
    }

    /* access modifiers changed from: protected */
    public void a(b bVar, MaterialEntity materialEntity) {
        if (this.s != null && this.s.size() > 0) {
            for (MaterialEntity materialEntity2 : this.s) {
                if (materialEntity.materialId.equals(materialEntity2.materialId)) {
                    materialEntity.isCheckEntity = true;
                }
            }
        }
        bVar.a(materialEntity, this.r, this.q, this.f85a);
    }
}
