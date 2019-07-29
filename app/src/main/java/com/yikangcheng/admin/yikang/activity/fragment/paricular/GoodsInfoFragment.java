package com.yikangcheng.admin.yikang.activity.fragment.paricular;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.Comadapter;
import com.yikangcheng.admin.yikang.activity.adapter.GoodPartiRecdapter;
import com.yikangcheng.admin.yikang.activity.particulars.GoodParticularsActivity;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;
import com.yikangcheng.admin.yikang.util.UIUtils;
import com.yikangcheng.admin.yikang.util.widget.SlideDetailsLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * item页ViewPager里的商品Fragment
 */
public class GoodsInfoFragment extends Fragment implements View.OnClickListener, SlideDetailsLayout.OnSlideDetailsListener, ICoreInfe {
    private SlideDetailsLayout sv_switch;
    private ScrollView sv_goods_info;
    private FloatingActionButton fab_up_slide;
    private TextView tv_goods_detail, tv_goods_config, text, text_price;
    private View v_tab_cursor;
    public FrameLayout fl_content;
    private RelativeLayout ll_current_goods;
    public LinearLayout ll_activity, ll_comment, ll_recommend, ll_pull_up;
    public TextView tv_current_goods, tv_comment_count, tv_good_comment, comment_btn;
    /**
     * 当前商品详情数据页的索引分别是图文详情、规格参数
     */
    private int nowIndex;
    private float fromX;
    public GoodsConfigFragment goodsConfigFragment;
    public GoodsInfoWebFragment goodsInfoWebFragment;
    public GoodsCommentFragment goodsCommentFragment;
    private Fragment nowFragment;
    private List<TextView> tabTextList;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    public GoodParticularsActivity activity;
    private LayoutInflater inflater;
    private RecommendPresenter recommendPresenter;
    private Banner banner;
    private LinearLayout lin_sum;
    private RecyclerView recom_recycler, img_count_recycler;
    private GoodPartiRecdapter goodPartiRecdapter;
    private Comadapter comadapter;
    private List<String> list_path;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (GoodParticularsActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View rootView = inflater.inflate(R.layout.fragment_goods_info, null);
        initView(rootView);
        initListener();
        initData();
        return rootView;
    }

    private void initListener() {
        fab_up_slide.setOnClickListener(this);
        ll_current_goods.setOnClickListener(this);
        ll_activity.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        ll_pull_up.setOnClickListener(this);
        tv_goods_detail.setOnClickListener(this);
        tv_goods_config.setOnClickListener(this);
        sv_switch.setOnSlideDetailsListener(this);
        comment_btn.setOnClickListener(this);
    }

    private void initView(View rootView) {
        fab_up_slide = rootView.findViewById(R.id.fab_up_slide);
        sv_switch = rootView.findViewById(R.id.sv_switch);
        sv_goods_info = rootView.findViewById(R.id.sv_goods_info);
        v_tab_cursor = rootView.findViewById(R.id.v_tab_cursor);
        fl_content = rootView.findViewById(R.id.fl_content);
        ll_current_goods = rootView.findViewById(R.id.ll_current_goods);
        ll_activity = rootView.findViewById(R.id.ll_activity);
        ll_comment = rootView.findViewById(R.id.ll_comment);
        ll_recommend = rootView.findViewById(R.id.ll_recommend);
        ll_pull_up = rootView.findViewById(R.id.ll_pull_up);
        tv_goods_detail = rootView.findViewById(R.id.tv_goods_detail);
        tv_goods_config = rootView.findViewById(R.id.tv_goods_config);
        tv_current_goods = rootView.findViewById(R.id.tv_current_goods);
        tv_comment_count = rootView.findViewById(R.id.tv_comment_count);
        tv_good_comment = rootView.findViewById(R.id.tv_good_comment);
        //查看评论
        comment_btn = rootView.findViewById(R.id.comment_btn);
        lin_sum = rootView.findViewById(R.id.lin_sum);
        //banner轮播图
        banner = rootView.findViewById(R.id.banner);
        tv_good_comment = rootView.findViewById(R.id.tv_good_comment);
        recom_recycler = rootView.findViewById(R.id.recom_recycler);
        img_count_recycler = rootView.findViewById(R.id.img_count_recycler);
        text = rootView.findViewById(R.id.text);
        text_price = rootView.findViewById(R.id.text_price);
        TextPaint tp = text.getPaint();
        tp.setFakeBoldText(true);
        text_price.setText(UIUtils.changTVsize("30.60"));
        setDetailData();
        fab_up_slide.hide();
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
    }

    @SuppressLint("NewApi")
    private void initData() {
        fragmentList = new ArrayList<>();
        tabTextList = new ArrayList<>();
        tabTextList.add(tv_goods_detail);
        tabTextList.add(tv_goods_config);
        recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.request(92, 1, 1);
        recom_recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        goodPartiRecdapter = new GoodPartiRecdapter();
        recom_recycler.setAdapter(goodPartiRecdapter);
        //评论
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        img_count_recycler.setLayoutManager(linearLayoutManager);
        comadapter = new Comadapter();
        img_count_recycler.setAdapter(comadapter);
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setBannerAnimation(Transformer.ScaleInOut);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        banner.start();
        //解决滑动不流畅
        recom_recycler.setHasFixedSize(true);
        recom_recycler.setNestedScrollingEnabled(false);
        img_count_recycler.setHasFixedSize(true);
        img_count_recycler.setNestedScrollingEnabled(false);
    }

    /**
     * 加载完商品详情执行
     */
    public void setDetailData() {
        goodsConfigFragment = new GoodsConfigFragment();
        goodsInfoWebFragment = new GoodsInfoWebFragment();
        goodsCommentFragment = new GoodsCommentFragment();
        fragmentList.add(goodsConfigFragment);
        fragmentList.add(goodsInfoWebFragment);
        fragmentList.add(goodsCommentFragment);
        nowFragment = goodsInfoWebFragment;
        fragmentManager = getChildFragmentManager();
        //默认显示商品详情tab
        fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_pull_up:
                //上拉查看图文详情
                sv_switch.smoothOpen(true);
                break;

            case R.id.fab_up_slide:
                //点击滑动到顶部
                sv_goods_info.smoothScrollTo(0, 0);
                sv_switch.smoothClose(true);
                break;
            case R.id.tv_goods_detail:
                //商品详情tab
                nowIndex = 0;
                scrollCursor();
                switchFragment(nowFragment, goodsInfoWebFragment);
                nowFragment = goodsInfoWebFragment;
                tv_goods_config.setBackgroundResource(R.drawable.parti_false);
                tv_goods_config.setTextColor(getActivity().getResources().getColor(R.color.clolrBAai));
                tv_goods_detail.setTextColor(getActivity().getResources().getColor(R.color.colorTab));
                tv_goods_detail.setBackgroundResource(R.drawable.parti_true);
                break;
            case R.id.tv_goods_config:
                //规格参数tab
                nowIndex = 1;
                scrollCursor();
                switchFragment(nowFragment, goodsConfigFragment);
                nowFragment = goodsConfigFragment;
                tv_goods_config.setBackgroundResource(R.drawable.parti_true);
                tv_goods_detail.setTextColor(getActivity().getResources().getColor(R.color.clolrBAai));
                tv_goods_config.setTextColor(getActivity().getResources().getColor(R.color.colorTab));
                tv_goods_detail.setBackgroundResource(R.drawable.parti_false);
                break;

            case R.id.comment_btn:

                break;
            default:
                break;
        }
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    @Override
    public void onStatucChanged(SlideDetailsLayout.Status status) {
        if (status == SlideDetailsLayout.Status.OPEN) {
            //当前为图文详情页
            fab_up_slide.show();
            activity.vp_content.setNoScroll(true);
            activity.tv_title.setVisibility(View.VISIBLE);
            activity.tv_title.setText("图文详情");
            activity.psts_tabs.setVisibility(View.GONE);
        } else {
            //当前为商品详情页
            fab_up_slide.hide();
            activity.vp_content.setNoScroll(false);
            activity.tv_title.setVisibility(View.GONE);
            activity.psts_tabs.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 滑动游标
     */
    private void scrollCursor() {
        TranslateAnimation anim = new TranslateAnimation(fromX, nowIndex * v_tab_cursor.getWidth(), 0, 0);
        anim.setFillAfter(true);//设置动画结束时停在动画结束的位置
        anim.setDuration(50);
        //保存动画结束时游标的位置,作为下次滑动的起点
        fromX = nowIndex * v_tab_cursor.getWidth();
        v_tab_cursor.startAnimation(anim);
        //设置Tab切换颜色
        for (int i = 0; i < tabTextList.size(); i++) {
            tabTextList.get(i).setTextColor(i == nowIndex ? getResources().getColor(R.color.colorTab) : getResources().getColor(R.color.colorText));
        }
    }

    /**
     * 切换Fragment
     * <p>(hide、show、add)
     *
     * @param fromFragment
     * @param toFragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (nowFragment != toFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {    // 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    /**
     * 为你推荐
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<RecommendBean> entity = (List<RecommendBean>) request.getEntity();
    }

    @Override
    public void fail(ApiException e) {

    }
}
