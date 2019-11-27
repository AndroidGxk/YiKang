package com.yikangcheng.admin.yikang.activity.fragment.juhe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.superluo.textbannerlibrary.TextBannerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.activity.WelcomeActivity;
import com.yikangcheng.admin.yikang.activity.adapter.WelfareCourseAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.juhe.adapter.SectionRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.giftactivity.MyGiftActivity;
import com.yikangcheng.admin.yikang.activity.particulars.ToParticularsActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.SectionImageBean;
import com.yikangcheng.admin.yikang.bean.ShouBannerBean;
import com.yikangcheng.admin.yikang.bean.WelfareCourseBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.SectionImagePresenter;
import com.yikangcheng.admin.yikang.presenter.ShouBannerPresenter;
import com.yikangcheng.admin.yikang.presenter.ShouTankuangPresenter;
import com.yikangcheng.admin.yikang.presenter.WelfareCoursePresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
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
    @BindView(R.id.tv_banner)
    TextBannerView tv_banner;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recy)
    RecyclerView recy;
    private List<String> mList;
    private List<String> list_path;
    private ShouBannerPresenter shouBannerPresenter;
    private View dialogview;
    private Dialog dialog;
    private ImageView imageView;
    private ImageView tiao;
    private ImageView jinru;
    private int count = 0;
    private List<ShouBannerBean> shouBannerBeans;
    private SectionRecyclerAdapter sectionRecyclerAdapter;
    private List<SectionImageBean> imageBeans;
    private ShouTankuangPresenter shouTankuangPresenter;
    private List<ShouBannerBean> bannerBean;
    private WelfareCoursePresenter welfareCoursePresenter;
    private RecyclerView recy1;
    private WelfareCourseAdapter welfareCourseAdapter;
    private TextView name;
    private TextView count1;
    private WelfareCourseBean welfareCourseBeans;

    @Override
    protected void initView(View view) {
        onClickListener();
        shouBannerPresenter = new ShouBannerPresenter(this);
        shouTankuangPresenter = new ShouTankuangPresenter(new ShouTanKuang());
        shouTankuangPresenter.request(getLogUser(BaseApp.getApp()).getId());
        shouBannerPresenter.request();
        recy.setLayoutManager(new GridLayoutManager(getContext(), 2));
        sectionRecyclerAdapter = new SectionRecyclerAdapter(getContext());
        recy.setAdapter(sectionRecyclerAdapter);
        SectionImagePresenter sectionImagePresenter = new SectionImagePresenter(new SectionImage());
        if (getLogUser(getContext()) != null) {
            sectionImagePresenter.request(getLogUser(getContext()).getEnterId());
        }
        /**
         * 福利礼品接口
         */
        welfareCourseAdapter = new WelfareCourseAdapter(getContext());
        welfareCoursePresenter = new WelfareCoursePresenter(new WelfareCourse());
        welfareCoursePresenter.request(getLogUser(getContext()).getId(), 1);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("福利内购活动火爆进行中！");
        mList.add("福利内购活动火爆进行中！");
        mList.add("福利内购活动火爆进行中！");
        /**
         * 设置数据，方式一
         */
        tv_banner.setDatas(mList);

        sectionRecyclerAdapter.setOnClickListener(new SectionRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        /**
                         * 签到页面
                         */
                        if (imageBeans.get(position).getLinkAddress() == null || (imageBeans.get(position).getLinkAddress().equals("") || imageBeans.get(position).getLinkAddress().equals("/"))) {
                            ToastUtils.show("功能暂未开放，敬请期待！");
                        } else {
                            Intent intent = new Intent(getContext(), H5SecActivity.class);
                            intent.putExtra("http", imageBeans.get(position).getLinkAddress() + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                            intent.putExtra("title", imageBeans.get(position).getImagesTitle());
                            startActivity(intent);
                        }
                        break;
                    case 1:
                        if (imageBeans.get(position).getLinkAddress() == null || (imageBeans.get(position).getLinkAddress().equals("") || imageBeans.get(position).getLinkAddress().equals("/"))) {
                            ToastUtils.show("功能暂未开放，敬请期待！");
                        } else {
                            Intent intent = new Intent(getContext(), H5SecActivity.class);
                            intent.putExtra("http", imageBeans.get(position).getLinkAddress() + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                            intent.putExtra("title", imageBeans.get(position).getImagesTitle());
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if (!UIUtils.isFastClick()) {
                            startActivity(new Intent(getContext(), MainActivity.class));
                        }
                        break;
                    case 3:
                        if (imageBeans.get(position).getLinkAddress() == null || (imageBeans.get(position).getLinkAddress().equals("") || imageBeans.get(position).getLinkAddress().equals("/"))) {
                            ToastUtils.show("功能暂未开放，敬请期待！");
                        } else {
                            Intent intent = new Intent(getContext(), H5SecActivity.class);
                            intent.putExtra("http", imageBeans.get(position).getLinkAddress() + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                            intent.putExtra("title", imageBeans.get(position).getImagesTitle());
                            startActivity(intent);
                        }
                        break;
                    case 4:
                        if (imageBeans.get(position).getLinkAddress() == null || (imageBeans.get(position).getLinkAddress().equals("") || imageBeans.get(position).getLinkAddress().equals("/"))) {
                            ToastUtils.show("功能暂未开放，敬请期待！");
                        } else {
                            Intent intent = new Intent(getContext(), H5SecActivity.class);
                            intent.putExtra("http", imageBeans.get(position).getLinkAddress() + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                            intent.putExtra("title", imageBeans.get(position).getImagesTitle());
                            startActivity(intent);
                        }
                        break;
                    case 5:
                        if (imageBeans.get(position).getLinkAddress() == null || (imageBeans.get(position).getLinkAddress().equals("") || imageBeans.get(position).getLinkAddress().equals("/"))) {
                            ToastUtils.show("功能暂未开放，敬请期待！");
                        } else {
                            Intent intent = new Intent(getContext(), H5SecActivity.class);
                            intent.putExtra("http", imageBeans.get(position).getLinkAddress() + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                            intent.putExtra("title", imageBeans.get(position).getImagesTitle());
                            startActivity(intent);
                        }
                        break;
                    case 6:
                        if (imageBeans.get(position).getLinkAddress() == null || (imageBeans.get(position).getLinkAddress().equals("") || imageBeans.get(position).getLinkAddress().equals("/"))) {
                            ToastUtils.show("功能暂未开放，敬请期待！");
                        } else {
                            Intent intent = new Intent(getContext(), H5SecActivity.class);
                            intent.putExtra("http", imageBeans.get(position).getLinkAddress() + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                            intent.putExtra("title", imageBeans.get(position).getImagesTitle());
                            startActivity(intent);
                        }
                        break;
                    case 7:
                        if (imageBeans.get(position).getLinkAddress() == null || (imageBeans.get(position).getLinkAddress().equals("") || imageBeans.get(position).getLinkAddress().equals("/"))) {
                            ToastUtils.show("功能暂未开放，敬请期待！");
                        } else {
                            Intent intent = new Intent(getContext(), H5SecActivity.class);
                            intent.putExtra("http", imageBeans.get(position).getLinkAddress() + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                            intent.putExtra("title", imageBeans.get(position).getImagesTitle());
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }


    private void showDialog() {
        //dialog展示优惠券
        dialogview = getLayoutInflater().inflate(R.layout.neigou_dialog, null);
        imageView = dialogview.findViewById(R.id.img);
        Glide.with(getContext()).load(Constants.BASETUPIANSHANGCHUANURL + bannerBean.get(0).getImagesUrl())
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                String url = bannerBean.get(0).getLinkAddress().replace("type=ios", "type=android");
                intent.putExtra("http", url + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                intent.putExtra("title", bannerBean.get(0).getTitle());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        tiao = dialogview.findViewById(R.id.quxiao);
        jinru = dialogview.findViewById(R.id.qukankan);
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
                if (welfareCourseBeans != null) {
                    if (welfareCourseBeans.getWelfareDetailsList() != null) {
                        showLiPinDialog();
                    }
                }
            }
        });
        jinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                String url = bannerBean.get(0).getLinkAddress().replace("type=ios", "type=android");
                intent.putExtra("http", url + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                intent.putExtra("title", bannerBean.get(0).getTitle());
                startActivity(intent);
                dialog.dismiss();
                if (welfareCourseBeans != null) {
                    if (welfareCourseBeans.getWelfareDetailsList() != null) {
                        showLiPinDialog();
                    }
                }
            }
        });
    }


    /**
     * 福利礼品弹框
     */
    private void showLiPinDialog() {
        //dialog展示优惠券
        dialogview = getLayoutInflater().inflate(R.layout.pop_liwu_shengri, null);
        ImageView diss_img = dialogview.findViewById(R.id.diss_img);
        TextView go_list = dialogview.findViewById(R.id.go_list);
        name = dialogview.findViewById(R.id.name);
        count1 = dialogview.findViewById(R.id.count);
        if (welfareCourseBeans != null) {
            if (welfareCourseBeans.getWelfareDetailsList() != null) {
                if (welfareCourseBeans.getWelfareDetailsList().get(0).getType() == 0) {
                    name.setText("节日礼物");
                    count1.setText("祝你节日快乐,愿你年年有今日,岁岁有今朝,企业的关怀无微不至,收下礼品吧~");
                } else {
                    name.setText("生日礼物");
                    count1.setText("祝你生日快乐,愿你年年有今日,岁岁有今朝,企业的关怀无微不至,收下礼品吧~");
                }
            }
        }

        recy1 = dialogview.findViewById(R.id.recy);
        recy1.setLayoutManager(new LinearLayoutManager(getContext()));
        recy1.setAdapter(welfareCourseAdapter);
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
        diss_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        go_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyGiftActivity.class));
                dialog.dismiss();
            }
        });
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }
    }

    /**
     * 点击事件处理
     *
     * @return
     */
    public void onClickListener() {
        /**
         * banner监听
         */
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                //内部购
                if (shouBannerBeans.get(position).getLinkAddress().equals("") || shouBannerBeans.get(position).getLinkAddress().equals("/")) {
                    return;
                } else {
                    String url = shouBannerBeans.get(position).getLinkAddress().replace("type=ios", "type=android");
                    intent.putExtra("http", url + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                    intent.putExtra("title", shouBannerBeans.get(position).getTitle());
                    startActivity(intent);
                }
            }
        });
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
        shouBannerBeans = (List<ShouBannerBean>) request.getEntity();
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

    /**
     * 八大板块
     */
    private class SectionImage implements ICoreInfe {


        @Override
        public void success(Object data) {
            Request request = (Request) data;
            imageBeans = (List<SectionImageBean>) request.getEntity();
            if (imageBeans != null) {
                sectionRecyclerAdapter.addAll(imageBeans);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class ShouTanKuang implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            bannerBean = (List<ShouBannerBean>) request.getEntity();
            if (count == 0) {
                count++;
                showDialog();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 礼品展示
     */
    private class WelfareCourse implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            welfareCourseBeans = (WelfareCourseBean) request.getEntity();
            welfareCourseAdapter.addAll(welfareCourseBeans.getWelfareDetailsList());

        }

        @Override
        public void fail(ApiException e) {

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        tv_banner.startViewAnimator();
        banner.startAutoPlay();
    }
}
