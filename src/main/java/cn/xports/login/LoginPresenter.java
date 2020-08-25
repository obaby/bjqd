package cn.xports.login;

import cn.xports.base.Constant;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.baselib.util.SPUtil;
import cn.xports.entity.App;
import cn.xports.http.SodaService;
import cn.xports.parser.AppParser;
import cn.xports.parser.LoginParser;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class LoginPresenter {
    /* access modifiers changed from: private */
    public LoginCallback callback;
    private IView rootView;

    public interface LoginCallback {
        void onError();

        void onSuccess();
    }

    public LoginPresenter(IView iView) {
        this(iView, (LoginCallback) null);
    }

    public LoginPresenter(IView iView, LoginCallback loginCallback) {
        this.callback = loginCallback;
        this.rootView = iView;
        SPUtil.Companion.getINSTANCE().save("channelId", "7").save("global_keys", "centerId,channelId,appId,netUserId,accessToken,accountId,coAppId").apply();
    }

    public void login(final String str, final String str2) {
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getAppInfo(Constant.APPLICATION_ID).flatMap(new Function<AppParser, ObservableSource<LoginParser>>() {
            public ObservableSource<LoginParser> apply(AppParser appParser) throws Exception {
                String str;
                String str2;
                if (appParser == null || appParser.getApp() == null) {
                    throw new ResponseThrowable((Throwable) new NullPointerException("获取app信息失败"), -10101, "获取app信息失败");
                }
                App app = appParser.getApp();
                SPUtil.Companion.getINSTANCE().save("centerId", app.getCenterId()).save("appId", app.getAppId()).save("ossUrl", app.getOssUrl()).apply();
                Constant.PAYMODES = app.getPayModes();
                DataMap fromJson = DataMapUtils.fromJson(SPUtil.Companion.getINSTANCE().getStringValue("qdUserInfo"));
                if (fromJson == null) {
                    str = "";
                } else {
                    str = fromJson.getString("nickName");
                }
                if (fromJson == null) {
                    str2 = "";
                } else {
                    str2 = fromJson.getString("photo");
                }
                return ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).login(str, str2, str, str2);
            }
        }).compose(RxUtil.applyErrorsWithIO()).subscribe(new ProcessObserver<LoginParser>(this.rootView) {
            public void next(LoginParser loginParser) {
                if (loginParser != null) {
                    SPUtil.Companion.getINSTANCE().save("netUserId", loginParser.getNetUserId()).save("accessToken", loginParser.getAccessToken()).save("coAppId", loginParser.getCoAppId()).save("coAccountId", loginParser.getCoAccountId()).apply();
                    if (LoginPresenter.this.callback != null) {
                        LoginPresenter.this.callback.onSuccess();
                    }
                } else if (LoginPresenter.this.callback != null) {
                    LoginPresenter.this.callback.onError();
                }
            }

            public void onError(ResponseThrowable responseThrowable) {
                super.onError(responseThrowable);
                if (LoginPresenter.this.callback != null) {
                    LoginPresenter.this.callback.onError();
                }
            }
        });
    }

    public void getAppInfo(final String str, final String str2) {
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getAppInfo(Constant.APPLICATION_ID).compose(RxUtil.applyErrorsWithIO()).subscribe(new ProcessObserver<AppParser>(this.rootView) {
            public void next(AppParser appParser) {
                if (appParser != null && appParser.getApp() != null) {
                    App app = appParser.getApp();
                    SPUtil.Companion.getINSTANCE().save("centerId", app.getCenterId()).save("appId", app.getAppId()).save("ossUrl", app.getOssUrl()).apply();
                    Constant.PAYMODES = app.getPayModes();
                    LoginPresenter.this.loginAction(str, str2);
                } else if (LoginPresenter.this.callback != null) {
                    LoginPresenter.this.callback.onError();
                }
            }

            public void onError(ResponseThrowable responseThrowable) {
                super.onError(responseThrowable);
                if (LoginPresenter.this.callback != null) {
                    LoginPresenter.this.callback.onError();
                }
            }
        });
    }

    public void loginAction(String str, String str2) {
        String str3;
        String str4;
        DataMap fromJson = DataMapUtils.fromJson(SPUtil.Companion.getINSTANCE().getStringValue("qdUserInfo"));
        if (fromJson == null) {
            str3 = "";
        } else {
            str3 = fromJson.getString("nickName");
        }
        if (fromJson == null) {
            str4 = "";
        } else {
            str4 = fromJson.getString("photo");
        }
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).login(str, str2, str3, str4).compose(RxUtil.applyErrorsWithIO()).subscribe(new ProcessObserver<LoginParser>(this.rootView) {
            public void next(LoginParser loginParser) {
                if (loginParser != null) {
                    SPUtil.Companion.getINSTANCE().save("centerId", loginParser.getNetUserId()).apply();
                    if (LoginPresenter.this.callback != null) {
                        LoginPresenter.this.callback.onSuccess();
                    }
                } else if (LoginPresenter.this.callback != null) {
                    LoginPresenter.this.callback.onError();
                }
            }

            public void onError(ResponseThrowable responseThrowable) {
                super.onError(responseThrowable);
                if (LoginPresenter.this.callback != null) {
                    LoginPresenter.this.callback.onError();
                }
            }
        });
    }

    public void setCallback(LoginCallback loginCallback) {
        this.callback = loginCallback;
    }
}
