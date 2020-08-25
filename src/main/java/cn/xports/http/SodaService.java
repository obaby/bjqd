package cn.xports.http;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.bean.Response;
import cn.xports.entity.AgreementResult;
import cn.xports.entity.OrderInfo;
import cn.xports.entity.PayCardResult;
import cn.xports.entity.ProductListResult;
import cn.xports.parser.AliPaySignParser;
import cn.xports.parser.AppParser;
import cn.xports.parser.BookDayParser;
import cn.xports.parser.BookRuleParser;
import cn.xports.parser.FieldParser;
import cn.xports.parser.FieldSaleParser;
import cn.xports.parser.FieldTypeParser;
import cn.xports.parser.HomeBannerBean;
import cn.xports.parser.HomeWeatherBean;
import cn.xports.parser.LoginParser;
import cn.xports.parser.OrderAmountParse;
import cn.xports.parser.OrderInfoParser;
import cn.xports.parser.OrderListParser;
import cn.xports.parser.OrderPayParser;
import cn.xports.parser.ServiceParser;
import cn.xports.parser.ThirdPayAccountParser;
import cn.xports.parser.ThirdPayBodyParser;
import cn.xports.parser.TicketDetailParser;
import cn.xports.parser.TicketParser;
import cn.xports.parser.TradeTicketParser;
import cn.xports.parser.VenueDetailParser;
import cn.xports.parser.VenueListParser;
import cn.xports.parser.WeChatPayParser;
import cn.xports.sportCoaching.SportCoachingBean;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SodaService {
    @GET("aisports-api/api/order/cancelPay")
    Observable<Response> cancelPay(@Query("tradeId") String str);

    @GET("aisports-api/api/order/cancelTickets")
    Observable<Response> cancelTickets(@Query("ticketList") String str);

    @GET("aisports-api/api/common/getAgreement")
    Observable<AgreementResult> getAgreement(@QueryMap DataMap dataMap);

    @FormUrlEncoded
    @POST("aisports-api/app-api/alipaySign")
    Observable<AliPaySignParser> getAliPayOrder(@Field("body") String str, @Field("subject") String str2, @Field("totalFee") String str3, @Field("tradeId") String str4);

    @GET("aisports-api/api/app/appInfo")
    Observable<AppParser> getAppInfo(@Query("bundleId") String str);

    @GET("aisports-api/api/service/fieldBookDays")
    Observable<BookDayParser> getBookDay(@Query("serviceId") String str, @Query("venueId") String str2);

    @GET("aisports-api/api/field/bookingRules")
    Observable<BookRuleParser> getBookRule(@Query("venueId") String str);

    @GET("aisports-api/api/service/centerServices")
    Observable<ServiceParser> getCenterServices();

    @GET("aisports-api/api/field/fieldList")
    Observable<FieldParser> getFieldList(@Query("date") String str, @Query("fieldTypeId") int i, @Query("serviceId") String str2, @Query("venueId") String str3);

    @GET("aisports-api/api/field/fieldSaleList")
    Observable<FieldSaleParser> getFieldSaleList(@Query("serviceId") String str, @Query("venueId") String str2);

    @GET("aisports-api/api/field/fieldTypes")
    Observable<FieldTypeParser> getFieldTypeList(@Query("date") String str, @Query("serviceId") String str2, @Query("venueId") String str3);

    @GET("aisports-api/api/app/open/index/center/ad")
    Observable<HomeBannerBean> getHomeAd();

    @GET("aisports-web/api/weather/heweather/free/{city}")
    Observable<HomeWeatherBean> getHomeWeather(@Path("city") String str);

    @GET("aisports-api/api/order/userOrderAmount")
    Observable<OrderAmountParse> getOrderCount(@Query("orderState") int i);

    @GET("aisports-api/api/order/orderInfo")
    Observable<OrderInfoParser> getOrderInfo(@Query("tradeId") String str);

    @GET("aisports-api/api/pay/getPayCardList")
    Observable<PayCardResult> getPayCardList(@Query("tradeId") String str);

    @GET("aisports-api/api/pay/orderInfo")
    Observable<OrderPayParser> getPayOrderInfo(@Query("tradeId") String str, @Query("ecardNo") String str2);

    @GET("aisports-api/api/product/getProducts")
    Observable<ProductListResult> getProducts(@Query("venueId") String str);

    @GET("aisports-api/api/fitness/queryFitnessGuides")
    Observable<SportCoachingBean> getSportCoachInfo();

    @FormUrlEncoded
    @POST("aisports-api/api/pay/queryThirdAccountId")
    Observable<ThirdPayAccountParser> getThirdAccountId(@Field("tradeId") String str);

    @GET("api/common/getStaticParamName")
    Observable<ThirdPayBodyParser> getThirdPayBody(@Query("attrCode") String str, @Query("attrValue") int i);

    @GET("aisports-api/api/ticket/ticketTypeInfo")
    Observable<TicketDetailParser> getTicketInfo(@Query("ticketTypeId") String str, @Query("timeId") String str2);

    @GET("aisports-api/api/ticket/ticketSaleList")
    Observable<TicketParser> getTicketList(@Query("channelId") int i, @Query("serviceId") String str, @Query("venueId") String str2);

    @GET("aisports-api/api/ticket/todayTickets")
    Observable<TradeTicketParser> getTodayTickets();

    @GET("aisports-api/api/pay/getUseCoupons")
    Observable<ResponseBody> getUseCouponList(@Query("tradeId") String str);

    @GET("aisports-api/api/order/userOrders")
    Observable<OrderListParser> getUserOrders(@Query("pageNo") int i, @Query("orderState") String str);

    @GET("aisports-api/api/venue/venueDetail")
    Observable<VenueDetailParser> getVenueDetail(@Query("venueId") String str);

    @GET("aisports-api/api/venue/venueAndServiceList")
    Observable<VenueListParser> getVenueList(@Query("channelId") int i, @Query("serviceId") String str, @Query("venueBusiness") int i2);

    @GET("aisports-api/api/param/venueParams")
    Observable<ResponseBody> getVenueParams(@Query("venueId") String str, @Query("paramCode") String str2);

    @FormUrlEncoded
    @POST("aisports-api/api/pay/wechatOrder")
    Observable<WeChatPayParser> getWeChatSign(@Field("body") String str, @Field("attach") String str2, @Field("totalFee") int i, @Field("tradeId") String str3);

    @FormUrlEncoded
    @POST("aisports-api/api/user/appLogin")
    Observable<LoginParser> login(@Field("phoneNum") String str, @Field("outerUid") String str2, @Field("nickname") String str3, @Field("avatar") String str4);

    @FormUrlEncoded
    @POST("aisports-api/api/order/fieldOrder")
    Observable<OrderInfo> orderField(@Field("date") String str, @Field("fieldTypeId") int i, @Field("fieldInfo") String str2, @Field("serviceId") String str3, @Field("venueId") String str4);

    @FormUrlEncoded
    @POST("aisports-api/api/order/ticketOrder")
    Observable<OrderInfo> orderTicket(@Field("date") String str, @Field("ticketInfo") String str2, @Field("serviceId") String str3, @Field("venueId") String str4);

    @FormUrlEncoded
    @POST("aisports-api/api/pay/payOrder")
    Observable<ResponseBody> payOrder(@Field("ecardNo") String str, @Field("payGroup") String str2, @Field("tradeId") String str3, @Field("orderDiscount") String str4);

    @FormUrlEncoded
    @POST("aisports-api/api/pay/saveTradeAttach")
    Observable<Response> saveTradeAttach(@Field("payGroup") String str, @Field("cashPledgeInfo") String str2, @Field("tradeId") String str3);
}
