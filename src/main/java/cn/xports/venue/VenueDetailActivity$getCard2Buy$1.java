package cn.xports.venue;

import android.widget.LinearLayout;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.ProductListResult;
import cn.xports.entity.ValidProduct;
import cn.xports.qdplugin.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/venue/VenueDetailActivity$getCard2Buy$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/ProductListResult;", "next", "", "value", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
public final class VenueDetailActivity$getCard2Buy$1 extends ProcessObserver<ProductListResult> {
    final /* synthetic */ VenueDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VenueDetailActivity$getCard2Buy$1(VenueDetailActivity venueDetailActivity, IView iView) {
        super(iView);
        this.this$0 = venueDetailActivity;
    }

    public void next(@Nullable ProductListResult productListResult) {
        if (productListResult != null) {
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llCard2Buy);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llCard2Buy");
            linearLayout.setVisibility(0);
            List<ValidProduct> validProducts = productListResult.getValidProducts();
            Intrinsics.checkExpressionValueIsNotNull(validProducts, "validProducts");
            for (ValidProduct validProduct : validProducts) {
                Intrinsics.checkExpressionValueIsNotNull(validProduct, "product");
                String limitTag = validProduct.getLimitTag();
                Intrinsics.checkExpressionValueIsNotNull(limitTag, "product.limitTag");
                if (limitTag.length() > 0) {
                    if (this.this$0.productMap.get(validProduct.getLimitTag()) == null) {
                        this.this$0.limitTags.add(validProduct.getLimitTag());
                        String limitTag2 = validProduct.getLimitTag();
                        Intrinsics.checkExpressionValueIsNotNull(limitTag2, "product.limitTag");
                        this.this$0.productMap.put(limitTag2, new ArrayList());
                    }
                    ArrayList arrayList = (ArrayList) this.this$0.productMap.get(validProduct.getLimitTag());
                    if (arrayList != null) {
                        arrayList.add(validProduct);
                    }
                }
            }
            this.this$0.setUpTypes(CollectionsKt.sorted(this.this$0.limitTags));
            this.this$0.updateCardTypeList((String) CollectionsKt.sorted(this.this$0.limitTags).get(0));
        }
    }
}
