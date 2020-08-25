package cn.xports.qd2;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.GoodsAttr;
import cn.xports.entity.ProductListResult;
import cn.xports.entity.ValidProduct;
import cn.xports.entity.ValidService;
import cn.xports.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/Card2BuyActivity$getProductList$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/ProductListResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: Card2BuyActivity.kt */
public final class Card2BuyActivity$getProductList$1 extends ProcessObserver<ProductListResult> {
    final /* synthetic */ Card2BuyActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Card2BuyActivity$getProductList$1(Card2BuyActivity card2BuyActivity, IView iView) {
        super(iView);
        this.this$0 = card2BuyActivity;
    }

    public void next(@Nullable ProductListResult productListResult) {
        if (productListResult != null) {
            HashMap hashMap = new HashMap();
            List<ValidService> validServices = productListResult.getValidServices();
            Intrinsics.checkExpressionValueIsNotNull(validServices, "validServices");
            for (ValidService validService : validServices) {
                Intrinsics.checkExpressionValueIsNotNull(validService, "it");
                String value = validService.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value, "it.value");
                String valueName = validService.getValueName();
                Intrinsics.checkExpressionValueIsNotNull(valueName, "it.valueName");
                hashMap.put(value, valueName);
            }
            ArrayList arrayList = new ArrayList(productListResult.getValidProducts());
            this.this$0.productMap.put("全部", arrayList);
            this.this$0.titles.add("全部");
            List<ValidProduct> validProducts = productListResult.getValidProducts();
            Intrinsics.checkExpressionValueIsNotNull(validProducts, "validProducts");
            for (ValidProduct validProduct : validProducts) {
                Intrinsics.checkExpressionValueIsNotNull(validProduct, "product");
                List<GoodsAttr> goodsAttrList = validProduct.getGoodsAttrList();
                Intrinsics.checkExpressionValueIsNotNull(goodsAttrList, "product.goodsAttrList");
                for (GoodsAttr goodsAttr : goodsAttrList) {
                    Intrinsics.checkExpressionValueIsNotNull(goodsAttr, "it");
                    String str = (String) hashMap.get(goodsAttr.getValue());
                    if (str != null) {
                        if (this.this$0.productMap.get(str) == null) {
                            this.this$0.titles.add(str);
                            this.this$0.productMap.put(str, new ArrayList());
                        }
                        ArrayList arrayList2 = (ArrayList) this.this$0.productMap.get(str);
                        if (arrayList2 != null) {
                            arrayList2.add(validProduct);
                        }
                    }
                }
            }
            this.this$0.setUpTabLayout(this.this$0.titles);
            this.this$0.productList.clear();
            this.this$0.productList.addAll(arrayList);
            EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) this.this$0._$_findCachedViewById(R.id.rvDepositList);
            Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvDepositList");
            emptyRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
