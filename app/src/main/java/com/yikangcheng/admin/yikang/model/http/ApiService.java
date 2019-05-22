package com.yikangcheng.admin.yikang.model.http;

import com.yikangcheng.admin.yikang.bean.AdvertisingBean;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.LikeBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;

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


}
