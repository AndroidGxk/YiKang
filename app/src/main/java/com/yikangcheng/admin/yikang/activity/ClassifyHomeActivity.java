package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiAAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiBAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClassifyPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

public class ClassifyHomeActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {
    private RecyclerView mRlvFragmentFenleiYou;
    private RecyclerView mRlvFragmentFenleiZuo;
    private FenLeiAAdapter mFenLeiAdapter;
    private FenLeiBAdapter mFenLeiBAdapter;
    private ClassifyPresenter classifyPresenter;
    private List<ClassifyListOneBean.ChildSubjectListBeanX> childSubjectList;
    private List<ClassifyListOneBean> entity;
    private int height;
    private ImageView back_img;
    private String id;
    private int recI;
    private int width;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        mRlvFragmentFenleiYou = (RecyclerView) findViewById(R.id.rlv__fragment_fenlei_you);
        back_img = (ImageView) findViewById(R.id.back_img);
        mRlvFragmentFenleiZuo = (RecyclerView) findViewById(R.id.rlv__fragment_fenlei_zuo);
        classifyPresenter = new ClassifyPresenter(this);
        classifyPresenter.request();
        //左边
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRlvFragmentFenleiZuo.setLayoutManager(linearLayoutManager);
        mFenLeiAdapter = new FenLeiAAdapter(this);
        mRlvFragmentFenleiZuo.setAdapter(mFenLeiAdapter);
        //右边
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        mRlvFragmentFenleiYou.setLayoutManager(linearLayoutManager1);
        mFenLeiBAdapter = new FenLeiBAdapter(this);
        mRlvFragmentFenleiYou.setAdapter(mFenLeiBAdapter);
        mFenLeiAdapter.setOnclickListener(new FenLeiAAdapter.Onclick() {
            @Override
            public void onClick(int position) {
                mFenLeiBAdapter.removeAll();
                mFenLeiBAdapter.addData(entity.get(position).getChildSubjectList());
            }
        });
        mFenLeiBAdapter.setOnClickListener(new FenLeiBAdapter.onClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(ClassifyHomeActivity.this, SeekListActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_classify_home;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        entity = (List<ClassifyListOneBean>) request.getEntity();
        mFenLeiAdapter.addAll(entity);
        for (int i = 0; i < entity.size(); i++) {
            if (entity.get(i).getSubjectId() == Integer.parseInt(id)) {
                childSubjectList = entity.get(i).getChildSubjectList();
                recI = Integer.parseInt(id);
            }
        }
        mFenLeiAdapter.setId(recI);
        mFenLeiBAdapter.addData(childSubjectList);
    }

    @Override
    public void fail(ApiException e) {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width / 2;
    }
}
