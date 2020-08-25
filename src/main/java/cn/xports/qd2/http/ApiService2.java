package cn.xports.qd2.http;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.bean.Response;
import cn.xports.entity.CardListResult;
import cn.xports.qd2.entity.AddPlayerResult;
import cn.xports.qd2.entity.CampEnrolledResult;
import cn.xports.qd2.entity.CampItemListResult;
import cn.xports.qd2.entity.CampItemSignResult;
import cn.xports.qd2.entity.CampaignDetailResult;
import cn.xports.qd2.entity.CampaignListResult;
import cn.xports.qd2.entity.CardDetailResult;
import cn.xports.qd2.entity.CardProductResult;
import cn.xports.qd2.entity.CourseElementsReslut;
import cn.xports.qd2.entity.CoursePromsResult;
import cn.xports.qd2.entity.ItemEnrollmentDetailResult;
import cn.xports.qd2.entity.PlayerInfoResult;
import cn.xports.qd2.entity.RechargePromotion;
import cn.xports.qd2.entity.RechargeRule;
import cn.xports.qd2.entity.SignElementsResult;
import cn.xports.qd2.entity.StudentListResult;
import cn.xports.qd2.entity.TeamInfoResult;
import cn.xports.qd2.entity.TermAndLessonResult;
import cn.xports.qd2.entity.TrainCourseDetailResult;
import cn.xports.qd2.entity.TrainListResult;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService2 {
    @FormUrlEncoded
    @POST("aisports-api/api/camp/enroll/addPlayer")
    Observable<AddPlayerResult> addPlayerInfo(@Field("enrollId") String str, @Field("campItemId") String str2, @Field("teamId") String str3, @Field("name") String str4, @Field("gender") String str5, @Field("mobileNum") String str6, @Field("psptType") String str7, @Field("psptId") String str8, @Field("elementInfoString") String str9, @Field("state") String str10);

    @FormUrlEncoded
    @POST("aisports-api/api/training/addStudent")
    Observable<ResponseBody> addStudent(@Field("studentInfo") String str, @Field("ecardNo") String str2, @Field("selfTag") String str3, @Field("studentLevels") String str4);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/applyJoinCircle")
    @Headers({"Community:true"})
    Observable<ResponseBody> applyJoinCircle(@Field("circleId") String str, @Field("applyMessage") String str2);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/auditApply")
    @Headers({"Community:true"})
    Observable<ResponseBody> auditJoinCircleApply(@Field("circleJoinList") String str);

    @FormUrlEncoded
    @POST("aisports-api/api/user/bindCard")
    Observable<Response> bindCard(@Field("ecardNo") String str, @Field("verifyCodeType") String str2, @Field("code") String str3);

    @FormUrlEncoded
    @POST("aisports-api/api/camp/enroll/createOrder")
    Observable<ResponseBody> campOrder(@Field("enrollId") String str, @Field("packageId") String str2);

    @FormUrlEncoded
    @POST("aisports-api/api/training/cancelPrivateResv")
    Observable<ResponseBody> cancelCourseResv(@Field("resvId") String str);

    @GET("aisports-api/api/camp/enroll/checkEnrollQualification")
    Observable<Response> checkQualification(@Query("campId") String str, @Query("campItemId") String str2);

    @FormUrlEncoded
    @POST("aisports-api/api/common/checkVerifyCode")
    Observable<ResponseBody> checkVerifyCode(@Field("phoneNum") String str, @Field("verifyCodeType") String str2, @Field("verifyCode") String str3);

    @FormUrlEncoded
    @POST("aisports-api/api/miniApp/lesson/commentSubmit")
    Observable<ResponseBody> commentSubmit(@FieldMap DataMap dataMap);

    @FormUrlEncoded
    @POST("aisports-api/api/training/privateCourseResv")
    Observable<ResponseBody> courseResv(@Field("enrollId") String str, @Field("coachId") String str2, @Field("lessonId") String str3);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/add")
    @Headers({"Community:true"})
    Observable<ResponseBody> createCircle(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("aisports-api/api/recharge/createOrder")
    Observable<ResponseBody> createRechargeOrder(@Field("rechargeMoney") String str, @Field("ecardNo") String str2, @Field("promId") String str3, @Field("saleCampaign") String str4);

    @FormUrlEncoded
    @POST("aisports-api/api/product/specialCardOrder")
    Observable<ResponseBody> createSpCardOrder(@Field("ecardNo") String str, @Field("productId") String str2, @Field("shouldPay") long j, @Field("proms") String str3);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/delete")
    @Headers({"Community:true"})
    Observable<ResponseBody> deleteCircle(@Field("circleId") String str);

    @FormUrlEncoded
    @POST("xports-community-api/priv/comments/delete")
    @Headers({"Community:true"})
    Observable<ResponseBody> deleteComment(@Field("commentId") String str);

    @FormUrlEncoded
    @POST("xports-community-api/priv/posts/delete")
    @Headers({"Community:true"})
    Observable<ResponseBody> deletePost(@Field("postId") String str);

    @FormUrlEncoded
    @POST("aisports-api/api/training/editStudent")
    Observable<ResponseBody> editStudent(@Field("stuId") String str, @Field("stuName") String str2, @Field("gender") String str3, @Field("height") String str4, @Field("phone") String str5, @Field("birthday") String str6);

    @GET("aisports-api/api/po/exchangeCouponByCustId")
    Observable<ResponseBody> exchangeGoods(@Query("objectId") String str, @Query("ecardNo") String str2, @Query("exchangeNum") long j);

    @FormUrlEncoded
    @POST("aisports-api/api/user/feedback")
    Observable<ResponseBody> feedback(@Field("content") String str, @Field("phoneNum") String str2);

    @GET("xports-community-api/boards/getAll")
    @Headers({"Community:true"})
    Observable<ResponseBody> getAllBoards();

    @GET("aisports-api/api/miniApp/lesson/findResvDetailsByResvId")
    Observable<ResponseBody> getAppointDetail(@Query("resvId") String str);

    @GET("aisports-api/api/miniApp/member/findMyAppoints")
    Observable<ResponseBody> getAppoints();

    @GET("xports-community-api/priv/circle/findCircle")
    @Headers({"Community:true"})
    Observable<ResponseBody> getBoardCircle(@Query("boardId") String str, @Query("pageSize") String str2, @Query("pageNum") Integer num);

    @GET("aisports-api/api/camp/enroll/getCampAttr")
    Observable<ResponseBody> getCampAttr(@Query("campId") String str, @Query("attrCode") String str2);

    @GET("aisports-api/api/camp/enroll/getCampaignInfo")
    Observable<CampaignDetailResult> getCampaignDetail(@Query("campId") String str);

    @GET("aisports-api/api/camp/enroll/getCampItemList")
    Observable<CampItemListResult> getCampaignItems(@Query("campId") String str);

    @GET("aisports-api/api/camp/enroll/getCampaignList")
    Observable<CampaignListResult> getCampaignList(@Query("pageNo") int i, @Query("state") String str, @Query("topTag") String str2);

    @GET("aisports-api/api/user/queryEcardDetail")
    Observable<CardDetailResult> getCardDetail(@Query("ecardNo") String str);

    @GET("aisports-api/api/user/getCardList")
    Observable<CardListResult> getCardList();

    @GET("xports-community-api/priv/circle/getCircleInfo")
    @Headers({"Community:true"})
    Observable<ResponseBody> getCircleDetail(@Query("circleId") String str);

    @GET("xports-community-api/posts/getByCircleId")
    @Headers({"Community:true"})
    Observable<ResponseBody> getCircleDetailPosts(@Query("circleId") String str, @Query("pageSize") String str2, @Query("pageNum") Integer num);

    @GET("xports-community-api/priv/circle/getCircleMemberInfo")
    @Headers({"Community:true"})
    Observable<ResponseBody> getCircleMember(@Query("circleId") String str);

    @GET("xports-community-api/priv/circle/personalCenter")
    @Headers({"Community:true"})
    Observable<ResponseBody> getCircleMyCenterInfo();

    @GET("aisports-api/api/training/queryCoachCourse")
    Observable<ResponseBody> getCoachCourse();

    @GET("xports-community-api/comments/getByPostId")
    @Headers({"Community:true"})
    Observable<ResponseBody> getCommentById(@Query("postId") String str, @Query("commentType") String str2, @Query("pageSize") String str3, @Query("pageNum") Integer num);

    @GET("aisports-api/api/miniApp/lesson/findCommentGradeDetail")
    Observable<ResponseBody> getCommentGradeDetail(@Query("venueId") String str);

    @FormUrlEncoded
    @POST("aisports-api/api/coupon/collectCoupon")
    Observable<ResponseBody> getCoupon(@FieldMap DataMap dataMap);

    @GET("aisports-api/api/coupon/camp/getCouponInfo")
    Observable<ResponseBody> getCouponDetail(@Query("couponId") String str, @Query("campId") String str2);

    @GET("aisports-api/api/coupon/camp/queryGroupCouponList")
    Observable<ResponseBody> getCouponList(@Query("venueId") String str, @Query("serviceId") String str2, @Query("couponClass") String str3, @Query("valueType") String str4);

    @GET("aisports-api/api/coupon/camp/queryServiceList")
    Observable<ResponseBody> getCouponServices();

    @GET("aisports-api/api/training/queryCourseEnrollElement")
    Observable<CourseElementsReslut> getCourseEnrollElement(@Query("courseId") String str);

    @GET("aisports-api/api/training/queryCourseProms")
    Observable<CoursePromsResult> getCourseProms(@Query("courseId") String str);

    @GET("aisports-api/api/training/queryPrivateCourseSchedule")
    Observable<ResponseBody> getCourseSchedule(@Query("selectedCoachId") String str, @Query("enrollId") String str2);

    @GET("aisports-api/api/user/getCustInfo")
    Observable<ResponseBody> getCustName(@Query("eCardNo") String str);

    @GET("aisports-api/api/camp/enroll/getEnrollElements")
    Observable<SignElementsResult> getElements(@Query("campId") String str);

    @GET("aisports-api/api/po/queryExchangeCoupons")
    Observable<ResponseBody> getExchangeCoupons(@Query("objectType") String str, @Query("pointsType") String str2, @Query("pageNo") int i, @Query("pageSize") int i2);

    @GET("aisports-api/api/po/queryExchangeDetail")
    Observable<ResponseBody> getExchangeDetail(@Query("exchangeId") String str);

    @GET("aisports-api/api/training/getFilterParam")
    Observable<ResponseBody> getFilterParam();

    @GET("xports-community-api/priv/circle/interactiveNews")
    @Headers({"Community:true"})
    Observable<ResponseBody> getInteractMsgNews(@Query("pageSize") int i, @Query("pageNum") int i2);

    @GET("aisports-api/api/camp/enroll/getItemEnrollmentDetail")
    Observable<ItemEnrollmentDetailResult> getItemDetail(@Query("enrollId") String str);

    @GET("aisports-api/api/camp/enroll/getEnrolledCampaigns")
    Observable<CampEnrolledResult> getMyCamps(@Query("pageNo") int i);

    @GET("aisports-api/api/coupon/getCouponInfo")
    Observable<ResponseBody> getMyCouponDetail(@Query("couponNo") String str);

    @GET("aisports-api/api/coupon/getUserCouponList")
    Observable<ResponseBody> getMyCouponList(@Query("couponState") String str, @Query("pageNo") int i);

    @GET("aisports-api/api/training/getMyCourseDetailsNew")
    Observable<ResponseBody> getMyCourseDetail(@Query("enrollId") String str);

    @GET("aisports-api/api/training/getMyCourseList")
    Observable<ResponseBody> getMyCourseList();

    @GET("xports-community-api/priv/circle/getJoinedCircles")
    @Headers({"Community:true"})
    Observable<ResponseBody> getMyJoinCircleInfo(@Query("pageNum") Integer num, @Query("pageSize") Integer num2);

    @GET("xports-community-api/priv/posts/getByJoinedCircles")
    @Headers({"Community:true"})
    Observable<ResponseBody> getMyJoinCirclePosts(@Query("pageNum") Integer num, @Query("pageSize") Integer num2);

    @GET("xports-community-api/priv/posts/getMyPosts")
    @Headers({"Community:true"})
    Observable<ResponseBody> getMyPosts(@Query("state") String str, @Query("pageNum") Integer num, @Query("pageSize") Integer num2);

    @GET("xports-community-api/priv/circle/personalCenterByAccountId")
    @Headers({"Community:true"})
    Observable<ResponseBody> getOtherCenter(@Query("circleId") String str, @Query("queryAccountId") String str2);

    @GET("aisports-api/api/camp/enroll/getPlayerInfo")
    Observable<PlayerInfoResult> getPlayerInfo(@Query("playerId") String str);

    @GET("aisports-api/api/user/getCardList")
    Observable<ResponseBody> getPointCardList();

    @GET("aisports-api/api/po/queryInAndOutNew")
    Observable<ResponseBody> getPointDetailList(@Query("custId") String str, @Query("pointsType") String str2, @Query("pageNo") int i, @Query("pageSize") int i2);

    @GET("aisports-api/api/po/queryExchangeDesc")
    Observable<ResponseBody> getPointExchangeDesc(@Query("pointsType") String str);

    @GET("aisports-api/api/po/queryExchangeHistByEcardNo")
    Observable<ResponseBody> getPointExchangeHist(@QueryMap DataMap dataMap);

    @GET("xports-community-api/posts/get")
    @Headers({"Community:true"})
    Observable<ResponseBody> getPostDetail(@Query("postId") String str);

    @GET("xports-community-api/posts/getByAccountId")
    @Headers({"Community:true"})
    Observable<ResponseBody> getPostsByAccount(@Query("circleId") String str, @Query("createAccountId") String str2, @Query("pageSize") int i, @Query("pageNum") int i2);

    @GET("aisports-api/api/product/getProductInfo")
    Observable<CardProductResult> getProductDetail(@Query("productId") String str, @Query("serviceId") String str2, @Query("goodsId") String str3);

    @GET("aisports-api/api/recharge/getEcardRechargePromotion")
    Observable<RechargePromotion> getRechargeProm();

    @GET("aisports-api/api/recharge/getEcardRechargeRule")
    Observable<RechargeRule> getRechargeRule();

    @GET("xports-community-api/priv/circle/getBillboards")
    @Headers({"Community:true"})
    Observable<ResponseBody> getRecommendList(@Query("defaultTag") String str, @Query("pageSize") String str2, @Query("pageNum") Integer num);

    @GET("aisports-api/api/camp/enroll/getCampScoreRank")
    Observable<ResponseBody> getScoreRank(@Query("campId") String str, @Query("campItemId") String str2, @Query("pageSize") int i, @Query("pageNum") int i2);

    @GET("aisports-api/api/training/getStudents")
    Observable<StudentListResult> getStudentList(@Query("ecardNo") String str);

    @GET("aisports-api/api/camp/enroll/getTeamInfo")
    Observable<TeamInfoResult> getTeamInfo(@Query("enrollId") String str);

    @GET("aisports-api/api/training/queryTermAndLesson")
    Observable<TermAndLessonResult> getTermLesson(@Query("courseId") String str);

    @GET("aisports-api/api/training/queryTrainingDetail")
    Observable<TrainCourseDetailResult> getTrainDetail(@Query("courseId") String str);

    @GET("aisports-api/api/training/queryTrainings")
    Observable<TrainListResult> getTrainings(@Query("pageNo") int i, @Query("pageSize") int i2, @Query("venueId") String str, @Query("serviceId") String str2, @Query("suitPerson") String str3, @Query("privateTag") String str4);

    @GET("aisports-api/api/miniApp/member/memberCenter")
    Observable<ResponseBody> getUserCouponCountInfo();

    @GET("aisports-api/api/training/queryUserFilterInfo")
    Observable<ResponseBody> getUserFilterInfo();

    @GET("aisports-api/api/user/getUserInfo")
    Observable<ResponseBody> getUserInfo();

    @GET("aisports-api/api/training/queryUserSchedule")
    Observable<ResponseBody> getUserSchedule(@Query("venueId") String str, @Query("lessonDate") String str2);

    @GET("aisports-api/api/venue/venueList")
    Observable<ResponseBody> getVenueList();

    @GET("aisports-api/api/common/getVenueParam")
    Observable<ResponseBody> getVenueParams(@Query("paramKeys") String str);

    @FormUrlEncoded
    @POST("aisports-api/api/user/sendBindCardVerifyCode")
    Observable<ResponseBody> getVerifyCode(@Field("ecardNo") String str, @Field("verifyCodeType") String str2);

    @GET("xports-community-api/priv/circle/getWaitAuditApply")
    @Headers({"Community:true"})
    Observable<ResponseBody> getWaitAuditApplyMsg(@Query("pageSize") int i, @Query("pageNum") int i2);

    @FormUrlEncoded
    @POST("xports-community-api/priv/posts/add")
    @Headers({"Community:true"})
    Observable<ResponseBody> issuePosts(@Field("contentType") String str, @Field("content") String str2, @Field("media") String str3, @Field("state") String str4, @Field("visibleRange") String str5, @Field("circleId") String str6, @Field("thumbMediaUrl") String str7);

    @FormUrlEncoded
    @POST("xports-community-api/priv/like/add")
    @Headers({"Community:true"})
    Observable<ResponseBody> likeAdd(@Field("relatedPostId") String str);

    @FormUrlEncoded
    @POST("xports-community-api/priv/like/delete")
    @Headers({"Community:true"})
    Observable<ResponseBody> likeDelete(@Field("relatedPostId") String str);

    @FormUrlEncoded
    @POST("xports-community-api/priv/comments/add")
    @Headers({"Community:true"})
    Observable<ResponseBody> postComment(@Field("relatedPostId") String str, @Field("content") String str2, @Field("commentType") String str3);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/removeMember")
    @Headers({"Community:true"})
    Observable<ResponseBody> removeMember(@Field("circleId") String str, @Field("accountIdList") String str2);

    @FormUrlEncoded
    @POST("aisports-api/api/camp/enroll/removePlayer")
    Observable<Response> removePlayer(@Field("playerId") String str);

    @FormUrlEncoded
    @POST("aisports-api/api/common/sendVerifyCode")
    Observable<ResponseBody> sendVerifyCode(@Field("phoneNum") String str, @Field("verifyCodeType") String str2, @Field("smsTempletType") String str3);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/setRole")
    @Headers({"Community:true"})
    Observable<ResponseBody> setRole(@Field("circleId") String str, @Field("setAccountId") String str2, @Field("role") String str3);

    @FormUrlEncoded
    @POST("aisports-api/api/camp/enroll/addItemEnrollment")
    Observable<ResponseBody> signUpItem(@Field("campId") String str, @Field("campItemId") String str2, @Field("accountId") String str3, @Field("enrollPlayerList") String str4, @Field("enrollInfoList") String str5, @Field("leaderName") String str6, @Field("phoneNum") String str7, @Field("gender") String str8, @Field("psptType") String str9, @Field("psptId") String str10, @Field("teamName") String str11);

    @FormUrlEncoded
    @POST("aisports-api/api/camp/enroll/addEnrollment")
    Observable<CampItemSignResult> signUpNoItem(@Field("campId") String str, @Field("accountId") String str2, @Field("name") String str3, @Field("phoneNum") String str4, @Field("psptType") String str5, @Field("psptId") String str6, @Field("gender") String str7);

    @FormUrlEncoded
    @POST("aisports-api/api/training/submitPrivateTraining")
    Observable<ResponseBody> submitPrivateTraining(@Field("ecardNo") String str, @Field("courseJson") String str2);

    @FormUrlEncoded
    @POST("aisports-api/api/training/submitTraining")
    Observable<ResponseBody> submitTraining(@FieldMap DataMap dataMap);

    @FormUrlEncoded
    @POST("aisports-api/api/user/unbindCard")
    Observable<Response> unbindCard(@Field("ecardNo") String str);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/update")
    @Headers({"Community:true"})
    Observable<ResponseBody> updateCircle(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("aisports-api/api/camp/enroll/updatePlayer")
    Observable<Response> updatePlayerInfo(@Field("enrollId") String str, @Field("playerId") String str2, @Field("name") String str3, @Field("gender") String str4, @Field("mobileNum") String str5, @Field("psptType") String str6, @Field("psptId") String str7, @Field("elementInfoString") String str8, @Field("state") String str9);

    @FormUrlEncoded
    @POST("xports-community-api/priv/circle/updateLastReadTime")
    @Headers({"Community:true"})
    Observable<ResponseBody> updateRead(@Field("readType") String str, @Field("readObjectId") String str2);

    @POST("aisports-api/api/upload/image")
    @Multipart
    Observable<ResponseBody> uploadImg(@Part List<MultipartBody.Part> list);

    @Multipart
    @POST("xports-community-api/priv/posts/media/upload")
    @Headers({"Community:true"})
    Observable<ResponseBody> uploadPostsFile(@Part MultipartBody.Part part);

    @FormUrlEncoded
    @POST("aisports-api/api/user/userQrcode")
    Observable<ResponseBody> useQrCode(@Field("ecardNo") String str);
}
