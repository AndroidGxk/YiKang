package com.yikangcheng.admin.yikang.activity.fragment.juhe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.superluo.textbannerlibrary.TextBannerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.activity.coupon.CouponActivity;
import com.yikangcheng.admin.yikang.activity.liveambitus.LiveamActivity;
import com.yikangcheng.admin.yikang.activity.liveambitus.MovieActivity;
import com.yikangcheng.admin.yikang.activity.yuangong.StaffhealthActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.ShouBannerBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ShouBannerPresenter;
import com.yikangcheng.admin.yikang.util.UIUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：古祥坤 on 2019/7/10 10:05
 * 邮箱：1724959985@qq.com
 */
public class Fragment_Shou extends BaseFragment implements ICoreInfe {
    @BindView(R.id.shop_imag)
    ImageView shop_imag;
    @BindView(R.id.jiankang)
    ImageView jiankang;
    @BindView(R.id.tv_banner)
    TextBannerView tv_banner;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.qiandao_img)
    ImageView qiandao_img;
    @BindView(R.id.shenghuo_img)
    ImageView shenghuo_img;
    @BindView(R.id.lvyou_img)
    ImageView lvyou_img;
    @BindView(R.id.fanka_img)
    ImageView fanka_img;
    @BindView(R.id.zhineng_img)
    ImageView zhineng_img;
    @BindView(R.id.yousheng_imag)
    ImageView yousheng_imag;
    private List<String> mList;
    private List<String> list_path;
    private ShouBannerPresenter shouBannerPresenter;
    private View dialogview;
    private Dialog dialog;
    private ImageView imageView;
    private TextView tiao;
    private TextView jinru;
    private int count = 0;

    @Override
    protected void initView(View view) {
        onClickListener();
        shouBannerPresenter = new ShouBannerPresenter(this);
        shouBannerPresenter.request();
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("欢迎优胜教育入驻企挚友平台！");
        mList.add("欢迎优胜教育入驻企挚友平台！");
        mList.add("欢迎优胜教育入驻企挚友平台！");
        /**
         * 设置数据，方式一
         */
        tv_banner.setDatas(mList);
        if (count == 0) {
            count++;
            showDialog();
        }
    }


    private void showDialog() {
        //dialog展示优惠券
        dialogview = getLayoutInflater().inflate(R.layout.neigou_dialog, null);
        imageView = dialogview.findViewById(R.id.img);
        tiao = dialogview.findViewById(R.id.tiao);
        jinru = dialogview.findViewById(R.id.jinru);
        Glide.with(getContext()).load(R.drawable.neigou)
                .into(imageView);
        dialog = new Dialog(getContext(), R.style.dialogWindowAnim);
        dialog.setContentView(dialogview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        //设置显示动画
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        //设置显示位置
        dialog.onWindowAttributesChanged(wl);
        //设置点击外围消散
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        jinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/yousheng?type=android");
                intent.putExtra("title", "优胜教育内部购");
                startActivity(intent);
                dialog.dismiss();
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

    /**
     * 点击事件处理
     *
     * @return
     */
    public void onClickListener() {
        shop_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UIUtils.isFastClick()) {
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
            }
        });
        jiankang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!UIUtils.isFastClick()) {
//                    startActivity(new Intent(getContext(), StaffhealthActivity.class));
//                }
                ToastUtils.show("功能暂未开放，敬请期待！");
                return;
            }
        });
        qiandao_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 签到页面
                 */
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/zeroPurchase?type=android");
                intent.putExtra("title", "专场0元购");
                startActivity(intent);
                return;
            }
        });
        shenghuo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getContext(), LiveamActivity.class));
                ToastUtils.show("功能暂未开放，敬请期待！");
                return;
            }
        });
        lvyou_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("功能暂未开放，敬请期待！");
                return;
            }
        });
        fanka_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("功能暂未开放，敬请期待！");
                return;
            }
        });
        zhineng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("功能暂未开放，敬请期待！");
                return;
            }
        });
        yousheng_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //内部购
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/yousheng?type=android");
                intent.putExtra("title", "优胜教育内部购");
                startActivity(intent);
                return;
            }
        });
        /**
         * banner监听
         */
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                switch (position) {

                    case 0:
                        //内部购
                        intent.putExtra("http", "https://www.yikch.com/mobile/appShow/yousheng?type=android");
                        intent.putExtra("title", "优胜教育内部购");
                        startActivity(intent);
                        break;
                    case 1:
                        /**
                         * 签到页面
                         */
                        intent.putExtra("http", "https://www.yikch.com/mobile/appShow/zeroPurchase?type=android");
                        intent.putExtra("title", "专场0元购");
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_banner.startViewAnimator();
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        tv_banner.stopViewAnimator();
        banner.stopAutoPlay();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_ji_shou;
    }

    /**
     * 首页Banner
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<ShouBannerBean> shouBannerBeans = (List<ShouBannerBean>) request.getEntity();
        if (shouBannerBeans != null) {
            list_path = new ArrayList<>();
            for (int i = 0; i < shouBannerBeans.size(); i++) {
                //放图片地址的集合
                list_path.add(Constants.BASETUPIANSHANGCHUANURL + shouBannerBeans.get(i).getImagesUrl());
            }
            //设置图片加载器，图片加载器在下方
            banner.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            banner.setImages(list_path);
            banner.start();
        }
    }

    @Override
    public void fail(ApiException e) {

    }
}
