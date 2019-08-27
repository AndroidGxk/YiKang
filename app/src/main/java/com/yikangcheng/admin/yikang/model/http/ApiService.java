package com.yikangcheng.admin.yikang.model.http;

import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.AdvertBean;
import com.yikangcheng.admin.yikang.bean.AdvertisingBean;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.AppUpdateBean;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.CouponusableBean;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
import com.yikangcheng.admin.yikang.bean.DalogTimeBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.DiscountBean;
import com.yikangcheng.admin.yikang.bean.GoodComBean;
import com.yikangcheng.admin.yikang.bean.HaveSignBean;
import com.yikangcheng.admin.yikang.bean.LikeBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.RegisterBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.SeckillBean;
import com.yikangcheng.admin.yikang.bean.SectionImageBean;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.bean.ShouBannerBean;
import com.yikangcheng.admin.yikang.bean.UserCommDaiListBean;
import com.yikangcheng.admin.yikang.bean.UserCommListBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;
import com.yikangcheng.admin.yikang.bean.WaitSignBean;
import com.yikangcheng.admin.yikang.bean.WaliDealBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 分类列表
     *
     * @return
     */
    @POST("classification/all")
    Observable<Request<List<ClassifyListOneBean>>> classify();

    /**
     * 购物车列表
     *
     * @param userId
     * @return
     */
    @POST("shopCart/list")
    Observable<Request<List<ShopCarBean>>> listCar(@Query("userId") int userId);

    /**
     * 猜你喜欢
     */
    @POST("index/similarCommodityPage")
    Observable<Request<List<LikeBean>>> list(@Query("userId") int userId, @Query("page.currentPage") int currentPage);

    /**
     * 为你推荐
     */
    @POST("api/everyDayCommodityList")
    Observable<Request<RecommendBean>> Recommend(@Query("num") int num, @Query("subjectId") int subjectId);


    /**
     * 我的页面-----广告图
     */
    @POST("userImage")
    Observable<Request<AdvertisingBean>> Advertising();


    /**
     * 获取短信验证Key
     *
     * @return
     */
    @POST("getMobileKey")
    Observable<Request> getMobileKey(@Query("mobile") String mobile, @Query("mobileType") String mobileType);

    /**
     * 获取短信验证码
     */
    @POST("sendMobileMessage")
    Observable<Request> sendMobileMessage(@Query("mobile") String mobile, @Query("sendType") String sendType, @Query("sign") String sign, @Query("mobileType") String mobileType);

    /**
     * 注册
     */
    @POST("register")
    Observable<Request<RegisterBean>> register(@Query("mobile") String mobile, @Query("userPassword") String userPassword, @Query("confirmPwd") String confirmPwd,
                                               @Query("mobileCheckCode") String mobileCheckCode, @Query("invitationCode") String invitationCode, @Query("longitude") String longitude, @Query("dimensionality") String dimensionality);

    /**
     * 登录
     */
    @POST("login")
    Observable<Request<LoginBean>> login(@Query("mobile") String mobile, @Query("userPassword") String userPassword);

    /**
     * 首页 秒杀 倒计时
     */
    @POST("seckill")
    Observable<Request<SeckillBean>> Seckill();

    /**
     * 个人信息资料
     */
    @POST("userDetails")
    Observable<Request<UserInfoBean<UserDetailBean>>> userDetails(@Query("userId") int userId);

    /**
     * 修改密码
     */
    @POST("retrievePwd")
    Observable<Request> retrievePwd(@Query("mobile") String mobile, @Query("retrieveType") String retrieveType,
                                    @Query("mobileCheckCode") String mobileCheckCode,
                                    @Query("userPassword") String userPassword, @Query("confirmPwd") String confirmPwd);

    /**
     * 修改个人信息
     */
    @POST("updateUserMessage")
    Observable<Request> updateUserMapper(@Query("userId") int userId, @Query("nickName") String nickName, @Query("avatar") String avatar,
                                         @Query("realName") String realName, @Query("email") String email, @Query("gender") int gender,
                                         @Query("userInfo") String userInfo);

    /**
     * 查看全部订单
     */
    @POST("userOrderType")
    Observable<Request<ALLBean>> All(@Query("userId") int userId, @Query("pageIndex") int pageIndex, @Query("orderState") String orderState);

    /**
     * 待支付
     */
    @POST("userOrderType")
    Observable<Request<ObligationBean>> Obligation(@Query("userId") int userId, @Query("pageIndex") int pageIndex, @Query("orderState") String orderState);

    /**
     * 已支付
     */
    @POST("userOrderType")
    Observable<Request<PaidBean>> Paid(@Query("userId") int userId, @Query("pageIndex") int pageIndex, @Query("orderState") String orderState);


    /**
     * 已取消
     */
    @POST("userOrderType")
    Observable<Request<CloseBean>> Close(@Query("userId") int userId, @Query("pageIndex") int pageIndex, @Query("orderState") String orderState);

    /**
     * 未签收
     */
    @POST("payOkWaitSign")
    Observable<Request<WaitSignBean>> payOkWaitSign(@Query("userId") int userId, @Query("pageIndex") int pageIndex, @Query("orderState") String orderState);

    /**
     * 已签收
     */
    @POST("orderAto")
    Observable<Request<HaveSignBean>> orderAto(@Query("userId") int userId, @Query("pageIndex") int pageIndex, @Query("orderState") String orderState);

    /**
     * 分类商品子类接口
     */
    @POST("classification/commodityList")
    Observable<Request<ClassifyCommodityListBean>> commodityList(@Query("classificationId") int classificationId, @Query("order") int order,
                                                                 @Query("condition") String condition, @Query("page") int page, @Query("currentPage") int currentPage);

    /**
     * 获取全部省市区
     */
    @POST("listArea")
    Observable<Request<List<ProvinceBean>>> listArea(@Query("parentId") int parentId);

    /**
     * 添加个人地址
     */
    @POST("insertAddress")
    Observable<Request> insertAddress(@Query("userId") int userId, @Query("receiver") String receiver, @Query("mobile") String mobile,
                                      @Query("address") String address, @Query("isFirst") int isFirst, @Query("provinceId") int provinceId,
                                      @Query("cityId") int cityId, @Query("townId") int townId);

    /**
     * 查询用户全部收货地址
     */
    @POST("listUserAddress")
    Observable<Request<AllAddressBean>> listUserAddress(@Query("userId") int userId);

    /**
     * 查询用户失效优惠券
     */
    @POST("mobile/listCouponCode")
    Observable<Request<DiscountBean>> DiscountCouponUnused(@Query("userId") int userId, @Query("status") int status);

    /**
     * 查询用户可用优惠券
     */
    @POST("mobile/listCouponCode")
    Observable<Request<DiscountBean>> DiscountCouponUnused(@Query("userId") int userId);

    /**
     * 修改用户地址
     */
    @POST("updateAddress")
    Observable<Request> updateAddress(@Query("id") int id, @Query("userId") int userId, @Query("receiver") String receiver,
                                      @Query("mobile") String mobile, @Query("address") String address, @Query("isFirst") int isFirst,
                                      @Query("provinceId") int provinceId, @Query("cityId") int cityId, @Query("townId") int townId);

    /**
     * 订单详情
     */
    @POST("userOrderDetails")
    Observable<Request<WaliDealBean>> CloseTheDealBean(@Query("orderId") int orderId);

    /**
     * 删除订单
     */
    @POST("deleteOrder")
    Observable<Request<DeleteOrderBean>> DeleteOrder(@Query("orderId") int orderId);

    /**
     * 删除用户地址
     */
    @POST("deleteAddress")
    Observable<Request> deleteAddress(@Query("id") int id);

    /**
     * 删除购物车商品
     */
    @POST("shopCart/delete")
    Observable<Request> deleteShopCar(@Query("userId") int userId, @Query("cartIds") String cartIds);

    /**
     * 添加购物车商品
     */
    @POST("shopCart/add")
    Observable<Request> addShopCar(@Query("userId") int userId, @Query("dataIds") String dataIds, @Query("dataType") String dataType,
                                   @Query("buyNum") String buyNum, @Query("price") String price);

    /**
     * 创建订单
     */
    @POST("order/buy")
    Observable<Request<CreatOrderBean>> orderbuy(@Query("userId") int userId, @Query("commodityId") int commodityId, @Query("addressId") int addressId,
                                                 @Query("buyNum") int buyNum, @Query("invoiceType") int invoiceType, @Query("invoiceFrom") int invoiceFrom,
                                                 @Query("invoiceName") String invoiceName, @Query("invoiceNo") String invoiceNo, @Query("invoiceContent") String invoiceContent,
                                                 @Query("invoiceEmail") String invoiceEmail, @Query("payType") String payType, @Query("orderForm") String orderForm, @Query("ipAddr") String ipAddr, @Query("couponCodeId") String couponCodeId);

    /**
     * 创建多个商品订单
     */
    @POST("order/create")
    Observable<Request<CreatOrderBean>> createOrder(@Query("userId") int userId, @Query("cartIds") String cartIds, @Query("addressId") int addressId,
                                                    @Query("invoiceType") int invoiceType, @Query("invoiceFrom") int invoiceFrom,
                                                    @Query("invoiceName") String invoiceName, @Query("invoiceNo") String invoiceNo, @Query("invoiceContent") String invoiceContent,
                                                    @Query("invoiceEmail") String invoiceEmail, @Query("payType") String payType, @Query("orderForm") String orderForm, @Query("dataType") String dataType, @Query("ipAddr") String ipAddr, @Query("couponCodeId") String couponCodeId);

    /**
     * 上传头像
     */
    @Multipart
    @POST("goswf")
    Observable<ResponseBody> goswf(@Part List<MultipartBody.Part> part);

    /**
     * 重新支付
     */
    @POST("order/repayUpdateOrder")
    Observable<Request<PayBean>> repayUpdateOrder(@Query("orderId") int orderId, @Query("payType") String payType, @Query("ipAddr") String ipAddr);

    /**
     * 修改购物车商品数量
     */
    @POST("shopCart/updateBuyNum")
    Observable<Request> updateBuyNum(@Query("userId") int userId, @Query("cartId") int cartId, @Query("buyNum") int buyNum);

    /**
     * 查询售后状态
     */
    @POST("afterSale/afterSaleStatus")
    Observable<Request> afterSaleStatus(@Query("userId") int userId, @Query("detailsId") int detailsId);

    /**
     * 确认收货
     */
    @POST("signOrder")
    Observable<Request> signOrder(@Query("userId") int userId, @Query("orderDetailsId") int orderDetailsId);

    /**
     * APP检查更新接口
     */
    @POST("check/update")
    Observable<Request<AppUpdateBean>> update(@Query("type") String kType);

    /**
     * 查看待付款数据个数
     */
    @POST("initOrderCount")
    Observable<Request> initOrderCount(@Query("userId") int userId);

    /**
     * 查询订单可使用优惠券
     */
    @POST("get/coupon/usable")
    Observable<Request<List<CouponusableBean>>> couponusable(@Query("userId") int userId, @Query("limitamount") double limitamount);

    /**
     * 取消订单接口
     */
    @POST("cancelOrder")
    Observable<Request> cancelOrder(@Query("userId") int userId, @Query("orderId") int orderId);

    /**
     * 启动页广告接口
     */
    @POST("startUp/banner")
    Observable<Request<List<AdvertBean>>> startUp(@Query("userId") int userId);

    @POST("startUp/banner")
    Observable<Request<List<AdvertBean>>> startUp();

    /**
     * 首页弹框
     */
    @POST("startUp/index")
    Observable<Request<List<ShouBannerBean>>> startUpindex(@Query("userId") int userId);

    /**
     * 首页Banner
     */
    @POST("index/banner")
    Observable<Request<List<ShouBannerBean>>> index();

    /**
     * 首页赠送优惠券接口
     */
    @POST("getRegisterCouponInfo")
    Observable<Request<DalogTimeBean>> getRegisterCouponInfo();

    /**
     * 千企千面聚合首页板块接口
     */
    @POST("querysectionImages")
    Observable<Request<List<SectionImageBean>>> querysectionImages(@Query("enterId") int enterId);

    /**
     * 用户添加评论接口
     */
    @POST("assess/add")
    Observable<Request> assessadd(@Query("orderDetailsId") int orderDetailsId, @Query("starClass") int starClass, @Query("userId") int userId, @Query("content") String content, @Query("image1") String image1, @Query("image2") String image2, @Query("image3") String image3);

    /**
     * 我的已评论列表
     */
    @POST("center/assess/list")
    Observable<Request<UserCommListBean>> assesslist(@Query("userId") int userId, @Query("page.currentPage") int page);

    /**
     * 我的待评论列表
     */
    @POST("center/waitAssess/list")
    Observable<Request<UserCommDaiListBean>> waitAssesslist(@Query("userId") int userId, @Query("page.currentPage") int page);

    /**
     * 查看商品评价列表
     */
    @POST("assess/list")
    Observable<Request<GoodComBean>> lookcommlist(@Query("commodityId") int commodityId, @Query("detailedId") int detailedId, @Query("page.currentPage") int page);
}
