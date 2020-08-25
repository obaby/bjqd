package com.boruan.qq.zxgylibrary.service.view;

import com.boruan.qq.zxgylibrary.base.BaseView;
import com.boruan.qq.zxgylibrary.service.model.DownOrderBean;
import com.boruan.qq.zxgylibrary.service.model.PayInfoResponse;
import com.boruan.qq.zxgylibrary.service.model.SettleDefaultAddressBean;
import com.boruan.qq.zxgylibrary.service.model.ShopCarDataBean;
import java.util.List;

public interface OrderView extends BaseView {
    void getDefaultAddressFailed(String str);

    void getDefaultAddressSuccess(SettleDefaultAddressBean.ResultBean resultBean);

    void getOrderDetailFailed(String str);

    void getOrderDetailSuccess(int i);

    void getPayParamsFailed(String str);

    void getPayParamsSuccess(PayInfoResponse.ResultBean resultBean);

    void getShopCarDataFailed(String str);

    void getShopCarDataSuccess(List<ShopCarDataBean.ResultBean> list);

    void userDownOrderFailed(String str);

    void userDownOrderSuccess(DownOrderBean.ResultBean resultBean);

    void userPayNoticeFailed(String str);

    void userPayNoticeSuccess(String str);
}
