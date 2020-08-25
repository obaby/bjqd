package ac;

import a.e;
import com.linewell.licence.R;
import com.linewell.licence.entity.LincenseEntity;

public class d extends e<LincenseEntity, c> {
    public d() {
        super(R.layout.qrcode_license_print_list_item);
    }

    /* access modifiers changed from: protected */
    public void a(c cVar, LincenseEntity lincenseEntity) {
        if (lincenseEntity != null) {
            cVar.a(lincenseEntity);
        }
    }
}
