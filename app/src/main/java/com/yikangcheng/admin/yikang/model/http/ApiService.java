package com.yikangcheng.admin.yikang.model.http;

import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.AdvertisingBean;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.DiscountBean;
import com.yikangcheng.admin.yikang.bean.DiscountCouponBean;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.LikeBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.RegisterBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.SeckillBean;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
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
    @POST("index/similarCommodityPage")
    Observable<Request<List<RecommendBean>>> Recommend(@Query("userId") int userId);

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
                                               @Query("mobileCheckCode") String mobileCheckCode, @Query("invitationCode") String invitationCode);

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
    @POST("updateUserMapper")
    Observable<Request> updateUserMapper(@Query("userId") String userId, @Query("nickName") String nickName, @Query("avatar") String avatar,
                                         @Query("realName") String realName, @Query("email") String email, @Query("gender") String gender,
                                         @Query("userInfo") String userInfo, @Query("mobile") String mobile);


    /**
     * 查看全部订单
     */
    @POST("userOrderType")
    Observable<Request<ALLBean>> All(@Query("userId") int userId, @Query("pageIndex") int pageIndex);

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
     * 已支付
     */
    @POST("userOrderType")
    Observable<Request<CloseBean>> Close(@Query("userId") int userId, @Query("pageIndex") int pageIndex, @Query("orderState") String orderState);


    /**
     * 分类子类接口
     */
    @POST("classification/commodityList")
    Observable<Request<ClassifyCommodityListBean>> commodityList(@Query("classificationId") int classificationId, @Query("order") int order,
                                                                 @Query("condition") int condition, @Query("currentPage") int currentPage);

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
     * 查询用户可用优惠券
     */

    @POST("DiscountCouponUnused")
    Observable<Request<DiscountBean>> DiscountCouponUnused(@Query("userId") int userId);

    /**
     * 用户选择优惠券
     */
    @POST("DiscountCoupon")
    Observable<Request<DiscountCouponBean>> DiscountCoupon(@Query("userId") int userId, @Query("status") int status);

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
    Observable<Request<CloseTheDealBean>> CloseTheDealBean(@Query("orderId") int orderId);

    /**
     * 删除订单
     */
    @POST("deleteOrder")
    Observable<Request<DeleteOrderBean>> DeleteOrder(@Query("orderId") int orderId);
}
