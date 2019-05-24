package com.yikangcheng.admin.yikang.model.http;

import com.yikangcheng.admin.yikang.bean.AdvertisingBean;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.LikeBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.RegisterBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.SeckillBean;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}
