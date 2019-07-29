package com.yikangcheng.admin.yikang.activity.liveambitus;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.UIUtils;

import butterknife.BindView;

public class BuyTicketActivity extends BaseActivtiy {
    @BindView(R.id.gaussian_img)
    ImageView gaussian_img;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.look_bg)
    ImageView look_bg;
    @BindView(R.id.ping_bg)
    ImageView ping_bg;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        setOnClickListener();
    }

    @Override
    protected void initEventData() {
        // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
        Glide.with(BuyTicketActivity.this).load(R.drawable.qizhiyou_movie_zhizhu).
                apply(RequestOptions.bitmapTransform(new UIUtils.BlurTransformation(200, 1))).
                into(gaussian_img);
        // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
        Glide.with(BuyTicketActivity.this).load(R.drawable.qizhiyou_movie_zhizhu).
                apply(RequestOptions.bitmapTransform(new UIUtils.BlurTransformation(200, 2))).
                into(look_bg);
        // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
        Glide.with(BuyTicketActivity.this).load(R.drawable.qizhiyou_movie_zhizhu).
                apply(RequestOptions.bitmapTransform(new UIUtils.BlurTransformation(200, 2))).
                into(ping_bg);
    }

    /**
     * 点击事件处理
     *
     * @return
     */
    public void setOnClickListener() {
        /**
         * 退出页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_buy_ticket;
    }

    @Override
    protected void createPresenter() {

    }


}
