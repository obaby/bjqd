package com.alipay.tscenter.biz.rpc.report.general;

import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

public interface a {
    @OperationType("alipay.security.device.data.report")
    DataReportResult a();
}
