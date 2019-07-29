package com.yikangcheng.admin.yikang.activity.fragment.catefragment;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CateAddressRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.LiveRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.LiveShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.ShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.shougang.ShouGangXiaoAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.juhe.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.liveambitus.CateActivity;
import com.yikangcheng.admin.yikang.activity.liveambitus.MovieActivity;
import com.yikangcheng.admin.yikang.activity.liveambitus.city.CityActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：古祥坤 on 2019/7/14 13:39
 * 邮箱：1724959985@qq.com
 */
public class Fragment_Cate_Shou extends BaseFragment {
    @BindView(R.id.live_recycle)
    RecyclerView live_recycle;
    @BindView(R.id.shop_recycler)
    RecyclerView shop_recycler;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.nested)
    NestedScrollView nested;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.city_text)
    TextView city_text;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private LiveRecyclerAdapter liveRecyclerAdapter = new LiveRecyclerAdapter();
    private LiveShopRecyclerAdapter liveShopRecyclerAdapter = new LiveShopRecyclerAdapter();
    private List<Integer> list_path;

    @Override
    protected void initView(View view) {
        live_recycle.setLayoutManager(new GridLayoutManager(getContext(), 5));
        live_recycle.setAdapter(liveRecyclerAdapter);
        setOnClickListener();
        shop_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        shop_recycler.setAdapter(liveShopRecyclerAdapter);

    }

    @SuppressLint("NewApi")
    @Override
    protected void initData() {
        //解决滑动不流畅
        live_recycle.setHasFixedSize(true);
        live_recycle.setNestedScrollingEnabled(false);
        shop_recycler.setHasFixedSize(true);
        shop_recycler.setNestedScrollingEnabled(false);
        /**
         * 设置数据，方式一
         */
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path.add(R.drawable.qizhiyou_banner);
        list_path.add(R.drawable.qizhiyou_banner);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        banner.start();
    }

    /**
     * 点击事件处理
     *
     * @return
     */
    public void setOnClickListener() {
        liveRecyclerAdapter.setOnClickListener(new LiveRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), CateActivity.class));
                        break;
                    case 1:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                    case 2:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), MovieActivity.class));
                        break;
                    case 4:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                    case 5:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                    case 6:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                    case 7:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                    case 8:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                    case 9:
                        ToastUtils.show("功能暂未开放，敬请期待！");
                        break;
                }
            }
        });
        /**
         * 返回页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        /**
         * 跳转到城市页面
         */
        city_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CityActivity.class));
            }
        });
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_cate_shou;
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }
}
