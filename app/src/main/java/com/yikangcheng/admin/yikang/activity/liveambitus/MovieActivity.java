package com.yikangcheng.admin.yikang.activity.liveambitus;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.movieadapter.MovieRecyclerAdapterOne;
import com.yikangcheng.admin.yikang.activity.adapter.movieadapter.MovieRecyclerAdapterTwo;
import com.yikangcheng.admin.yikang.activity.h5activity.H5MessActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;
import cn.jiguang.g.m;

/**
 * 选电影选电影院页面
 */
public class MovieActivity extends BaseActivtiy {
    @BindView(R.id.movie_recycle)
    RecyclerView movie_recycle;
    @BindView(R.id.movie_recycle2)
    RecyclerView movie_recycle2;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.mess_btn)
    ImageView mess_btn;
    private MovieRecyclerAdapterOne movieRecyclerAdapterOne;
    private MovieRecyclerAdapterTwo movieRecyclerAdapterTwo;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        movieRecyclerAdapterOne = new MovieRecyclerAdapterOne();
        movieRecyclerAdapterTwo = new MovieRecyclerAdapterTwo();
        getOnClickListener();
    }

    @Override
    protected void initEventData() {
        movie_recycle.setLayoutManager(new LinearLayoutManager(this));
        movie_recycle.setAdapter(movieRecyclerAdapterOne);
        //解决滑动不流畅
        movie_recycle.setHasFixedSize(true);
        movie_recycle.setNestedScrollingEnabled(false);
        movie_recycle2.setLayoutManager(new GridLayoutManager(this, 2));
        movie_recycle2.setAdapter(movieRecyclerAdapterTwo);
        //解决滑动不流畅
        movie_recycle2.setHasFixedSize(true);
        movie_recycle2.setNestedScrollingEnabled(false);
    }

    /**
     * 点击事件处理
     *
     * @return
     */
    public void getOnClickListener() {
        /**
         * 跳转到电影院页面
         */
        movieRecyclerAdapterOne.setOnClickListener(new MovieRecyclerAdapterOne.onClickListener() {
            @Override
            public void onClick() {

            }
        });
        /**
         * 跳转到电影页面
         */
        movieRecyclerAdapterTwo.setOnClickListener(new MovieRecyclerAdapterTwo.onClickListener() {
            @Override
            public void onClick() {
                startActivity(new Intent(MovieActivity.this, BuyTicketActivity.class));
            }
        });
        /**
         * 退出页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         * 消息页面
         */
        mess_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieActivity.this, H5MessActivity.class));
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    protected void createPresenter() {

    }
}
