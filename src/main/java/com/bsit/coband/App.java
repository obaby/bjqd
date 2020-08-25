package com.bsit.coband;

import com.convenient.qd.core.constant.Constant;
import com.convenient.qd.module.gov.GOVApplication;

public class App extends GOVApplication {
    public void onCreate() {
        App.super.onCreate();
        Constant.DEBUG = false;
        Constant.SERVER_URL = "https://bjqdapi.aqdpay.com/";
        Constant.Drug_BaseUrl = "http://bjweb.bjqd.aqdpay.com/drug/#/";
        Constant.H5_SERVER_URL = "https://bjweb.aqdpay.com/";
        Constant.APPCLICATION_CODE = "";
    }
}
