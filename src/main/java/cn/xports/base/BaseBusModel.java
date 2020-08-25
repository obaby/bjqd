package cn.xports.base;

import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.mvp.BaseModel;
import cn.xports.http.SodaService;

public class BaseBusModel extends BaseModel {
    protected SodaService service = ((SodaService) RetrofitUtil.getInstance().create(SodaService.class));
}
