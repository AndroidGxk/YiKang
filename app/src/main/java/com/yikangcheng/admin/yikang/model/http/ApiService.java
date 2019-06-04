package com.yikangcheng.admin.yikang.model.http;

import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.AdvertisingBean;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.DiscountBean;
import com.yikangcheng.admin.yikang.bean.DiscountCouponBean;
import com.yikangcheng.admin.yikang.bean.LikeBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.NewOrderBean;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.RegisterBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.SeckillBean;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;

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
    @POST("index/similarCommodityPage")
    Observable<Request<List<RecommendBean>>> Recommend(@Query("userId") int userId, @Query("page") int page,@Query("currentPage")int currentPage);

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
    @POST("updateUserMessage")
    Observable<Request> updateUserMapper(@Query("userId") int userId, @Query("nickName") String nickName, @Query("avatar") String avatar,
                                         @Query("realName") String realName, @Query("email") String email, @Query("gender") int gender,
                                         @Query("userInfo") String userInfo);


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
                                                 @Query("invoiceEmail") String invoiceEmail, @Query("payType") String payType, @Query("orderForm") String orderForm,@Query("ipAddr")String ipAddr);

    /**
     * 创建多个商品订单
     */
    @POST("order/create")
    Observable<Request<CreatOrderBean>> createOrder(@Query("userId") int userId, @Query("cartIds") String cartIds, @Query("addressId") int addressId,
                                                    @Query("invoiceType") int invoiceType, @Query("invoiceFrom") int invoiceFrom,
                                                    @Query("invoiceName") String invoiceName, @Query("invoiceNo") String invoiceNo, @Query("invoiceContent") String invoiceContent,
                                                    @Query("invoiceEmail") String invoiceEmail, @Query("payType") String payType, @Query("orderForm") String orderForm, @Query("dataType") String dataType);


    /**
     * 上传头像
     */
    @Multipart
    @POST("goswf")
    Observable<ResponseBody> goswf(@Part List<MultipartBody.Part> part);

    /**
     * 重新下单
     */
    @POST("order/repayUpdateOrder")
    Observable<Request<PayBean>> repayUpdateOrder(@Query("orderId") int orderId, @Query("payType") String payType);

    /**
     * 修改购物车商品数量
     */
    @POST("shopCart/updateBuyNum")
    Observable<Request> updateBuyNum(@Query("userId") int userId, @Query("cartId") int cartId, @Query("buyNum") int buyNum);
}
