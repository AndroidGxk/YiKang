package com.yikangcheng.admin.yikang.activity.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseFragment;

public class Fragment_Wo extends BaseFragment {


    private TextView mTvFragmentWoDingdan;
    private ImageView mImgFragmentWoMingxi;
    private ImageView mImgFragmentWoZhongxin;
    private ImageView mImgFragmentWoYizhifu;
    private ImageView mImgFragmentWoYituikuan;
    private TextView mTvFragmentWoXiaoxiA;
    private RecyclerView mRlvFragmentWo;
    private TextView mTvFragmentWoXiaoxiB;
    private ImageView mImgFragmentWoTouxiang;
    private ImageView mImgFragmentWoHongyuanquanB;
    private ImageView mImgFragmentWoHongyuanquanA;
    private ImageView mImgFragmentWoLingdang;
    private ImageView mImgFragmentWoDaifukuan;
    private ImageView mImgFragmentWoYiquxiao;
    private ImageView mImgFragmentWoDizi;
    private ImageView mImgFragmentWoZhanghao;
    private TextView mTvFragmentWoName;

    @Override
    protected void initView(View view) {

        mTvFragmentWoDingdan = view.findViewById(R.id.tv_fragment_wo_dingdan);
        mImgFragmentWoMingxi = view.findViewById(R.id.img_fragment_wo_mingxi);
        mImgFragmentWoZhongxin = view.findViewById(R.id.img__fragment_wo_zhongxin);
        mImgFragmentWoYizhifu = view.findViewById(R.id.img_fragment_wo_yizhifu);
        mImgFragmentWoYituikuan = view.findViewById(R.id.img_fragment_wo_yituikuan);
        mTvFragmentWoXiaoxiA = view.findViewById(R.id.tv_fragment_wo_xiaoxi_a);
        mRlvFragmentWo = view.findViewById(R.id.rlv__fragment_wo);
        mTvFragmentWoXiaoxiB = view.findViewById(R.id.tv_fragment_wo_xiaoxi_b);
        mImgFragmentWoTouxiang = view.findViewById(R.id.img_fragment_wo_touxiang);
        mImgFragmentWoHongyuanquanB = view.findViewById(R.id.img_fragment_wo_hongyuanquan_b);
        mImgFragmentWoHongyuanquanA = view.findViewById(R.id.img__fragment_wo_hongyuanquan_a);
        mImgFragmentWoLingdang = view.findViewById(R.id.img_fragment_wo_lingdang);
        mImgFragmentWoDaifukuan = view.findViewById(R.id.img_fragment_wo_daifukuan);
        mImgFragmentWoYiquxiao = view.findViewById(R.id.img__fragment_wo_yiquxiao);
        mImgFragmentWoDizi = view.findViewById(R.id.img_fragment_wo_dizi);
        mImgFragmentWoZhanghao = view.findViewById(R.id.img__fragment_wo_zhanghao);
        mTvFragmentWoName = view.findViewById(R.id.tv__fragment_wo_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_wo;
    }
}
