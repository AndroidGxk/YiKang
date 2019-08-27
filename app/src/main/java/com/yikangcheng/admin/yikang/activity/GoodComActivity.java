package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter.GoodCommRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.GoodComBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.LookAssessListPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商品评论列表
 */
public class GoodComActivity extends BaseActivtiy implements ICoreInfe {
    @BindView(R.id.table)
    RelativeLayout table;
    @BindView(R.id.comm_recycle)
    RecyclerView mRecy;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefre;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.img_linear)
    LinearLayout img_linear;
    @BindView(R.id.list_linear)
    LinearLayout list_linear;
    @BindView(R.id.all_check)
    CheckBox mCkeck;
    private LookAssessListPresenter lookAssessListPresenter;
    private List<GoodComBean.CourseAssessListBean> courseAssessListBeans;
    private GoodCommRecyclerAdapter goodCommRecyclerAdapter;
    private int mPage = 1;
    private String detaid;
    private String goodid;
    private String detanum;
    private String goodidnum;

    @Override
    protected void initView() {
        if (getLogUser(this) != null) {
            //设置状态栏颜色
            if (!getLogUser(this).getThemeColors().equals("")) {
                StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
            } else {
                StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
            }
            table.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        }
        Intent intent = getIntent();
        goodid = intent.getStringExtra("goodid");
        detaid = intent.getStringExtra("detaid");
        detanum = detaid.replace("null", "");
        goodidnum = goodid.replace("null", "");
        Log.d("GTT", detanum);
        Log.d("GTT", goodidnum);
        lookAssessListPresenter = new LookAssessListPresenter(this);
        mCkeck.setChecked(true);
        lookAssessListPresenter.request(Integer.parseInt(goodidnum), Integer.parseInt(detanum), mPage);
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        goodCommRecyclerAdapter = new GoodCommRecyclerAdapter(this);
        mRecy.setAdapter(goodCommRecyclerAdapter);
        mRefre.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                goodCommRecyclerAdapter.removeAll();
                if (mCkeck.isChecked()) {
                    lookAssessListPresenter.request(Integer.parseInt(goodidnum), Integer.parseInt(detanum), mPage);
                } else {
                    lookAssessListPresenter.request(Integer.parseInt(goodidnum), 0, mPage);
                }
            }
        });
        mRefre.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                if (mCkeck.isChecked()) {
                    lookAssessListPresenter.request(Integer.parseInt(goodidnum), Integer.parseInt(detanum), mPage);
                } else {
                    lookAssessListPresenter.request(Integer.parseInt(goodidnum), 0, mPage);
                }
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        goodCommRecyclerAdapter.setOnClickListener(new GoodCommRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(ArrayList<String> pathList) {
                Intent intent = new Intent(GoodComActivity.this, PhotoBigActivity.class);
                intent.putStringArrayListExtra("data", pathList);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initEventData() {
        mCkeck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    goodCommRecyclerAdapter.removeAll();
                    mRefre.autoRefresh();
                    lookAssessListPresenter.request(Integer.parseInt(goodidnum), Integer.parseInt(detanum), mPage);
                } else {
                    goodCommRecyclerAdapter.removeAll();
                    mRefre.autoRefresh();
                    lookAssessListPresenter.request(Integer.parseInt(goodidnum), 0, mPage);
                }
            }
        });

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_good_com;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        GoodComBean goodComBean = (GoodComBean) request.getEntity();
        courseAssessListBeans = goodComBean.getCourseAssessList();
        if (courseAssessListBeans != null) {
            goodCommRecyclerAdapter.addAll(courseAssessListBeans);
            img_linear.setVisibility(View.GONE);
            mRefre.setVisibility(View.VISIBLE);
        } else {
            img_linear.setVisibility(View.VISIBLE);
            mRefre.setVisibility(View.GONE);
        }
        mRefre.finishRefresh();
        mRefre.finishLoadmore();
    }

    @Override
    public void fail(ApiException e) {

    }
}
