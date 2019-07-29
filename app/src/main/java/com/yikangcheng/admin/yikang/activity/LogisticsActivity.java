package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.LogisticsAdapter;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.WuLiuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流页面
 */
public class LogisticsActivity extends BaseActivtiy implements View.OnClickListener {

    private LogisticsAdapter wuliuAdapter;
    private RecyclerView recyclerView;
    private ImageView copay_img;
    private TextView danhao_text;
    private List<WuLiuBean> strings;

    @Override
    protected void initView() {
        closeSwipeBack();
        recyclerView = (RecyclerView) findViewById(R.id.wuliu_recycler);
        copay_img = (ImageView) findViewById(R.id.copay_img);
        danhao_text = (TextView) findViewById(R.id.danhao_text);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wuliuAdapter = new LogisticsAdapter();
        strings = new ArrayList<>();

    }

    @Override
    protected void initEventData() {
        for (int i = 0; i < 5; i++) {
            WuLiuBean wuLiuBean = new WuLiuBean();
            switch (i) {
                case 0:
                    wuLiuBean.setTime("2016-6-28 15:13:02");
                    wuLiuBean.setContext("快件在【相城中转仓】装车,正发往【无锡分拨中心】已签收,签收人是【王漾】,签收网点是【忻州原平】");
                    strings.add(wuLiuBean);
                    break;
                case 1:
                    wuLiuBean.setTime("2016-6-28 15:13:02");
                    wuLiuBean.setContext("快件在【相城中转仓】装车,正发往【无锡分拨中心】");
                    strings.add(wuLiuBean);
                    break;
                case 2:
                    wuLiuBean.setTime("2016-6-28 15:13:02");
                    wuLiuBean.setContext("快件在【北京鸿运良乡站】的【010058.269】正在派件");
                    strings.add(wuLiuBean);
                    break;
                case 3:
                    wuLiuBean.setTime("2016-6-28 15:13:02");
                    wuLiuBean.setContext("快件在【潍坊市中转部】装车,正发往【潍坊奎文代派】");
                    strings.add(wuLiuBean);
                    break;
                case 4:
                    wuLiuBean.setTime("2016-6-28 15:13:02");
                    wuLiuBean.setContext("快件在【潍坊奎文代派】的【010058269】正在派件");
                    strings.add(wuLiuBean);
                    break;
            }
        }
        recyclerView.setAdapter(wuliuAdapter);
        wuliuAdapter.addAll(strings);
        copay_img.setOnClickListener(this);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.copay_img:
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), danhao_text);
                copyButtonLibrary.init();
                break;
        }
    }
}
