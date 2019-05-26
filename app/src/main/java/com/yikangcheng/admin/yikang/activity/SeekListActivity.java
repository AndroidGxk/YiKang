package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.activity.adapter.ClassCommodAdapter;
import com.yikangcheng.admin.yikang.presenter.CommodityPresenter;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 搜索列表页面
 */
public class SeekListActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe, View.OnClickListener {

    private EditText edit_seek_sousuo;
    private CommodityPresenter commodityPresenter;
    private XRecyclerView xrecycler;
    private ClassCommodAdapter classCommodAdapter;
    private TextView zonghe, xiaoliang, price_text;
    private RelativeLayout price;
    private ImageView qiehuan;
    private boolean zclick = true, xclick, pclick, qclick = true, pimgclick = true;
    private int state;
    private int id;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String count = intent.getStringExtra("count");
        id = intent.getIntExtra("id", 000);
        xrecycler = findViewById(R.id.xrecycler);
        //搜素框
        edit_seek_sousuo = findViewById(R.id.EditTixt_activity_seek_sousuo);
        zonghe = findViewById(R.id.zonghe);
        price_text = findViewById(R.id.price_text);
        zonghe.setOnClickListener(this);
        xiaoliang = findViewById(R.id.xiaoliang);
        xiaoliang.setOnClickListener(this);
        price = findViewById(R.id.price);
        price.setOnClickListener(this);
        qiehuan = findViewById(R.id.qiehuan);
        qiehuan.setOnClickListener(this);
        edit_seek_sousuo.setText(count);
        commodityPresenter = new CommodityPresenter(this);
        commodityPresenter.request(id, 1, 1, 1);
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        classCommodAdapter = new ClassCommodAdapter(this);
        xrecycler.setAdapter(classCommodAdapter);
    }


    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_seek_list;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 720;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ClassifyCommodityListBean classifyCommodityListBean = (ClassifyCommodityListBean) request.getEntity();
        List<ClassifyCommodityListBean.CommodityListBean> commodityList = classifyCommodityListBean.getCommodityList();
        classCommodAdapter.removeAll();
        classCommodAdapter.addAll(commodityList);
    }

    @Override
    public void fail(ApiException e) {

    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zonghe:
                if (!zclick) {
                    commodityPresenter.request(id, 1, 1, 1);
                    zonghe.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorTab));
                    xiaoliang.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    price_text.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zclick = true;
                    xclick = false;
                    pclick = false;
                    Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_image);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    price_text.setCompoundDrawables(null, null, drawable, null);
                    pimgclick = true;
                }
                break;
            case R.id.xiaoliang:
                if (!xclick) {
                    commodityPresenter.request(id, 5, 1, 1);
                    xiaoliang.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorTab));
                    zonghe.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    price_text.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zclick = false;
                    xclick = true;
                    pclick = false;
                    Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_image);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    price_text.setCompoundDrawables(null, null, drawable, null);
                    pimgclick = true;
                }
                break;
            case R.id.price:
                if (!pclick) {
                    price_text.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorTab));
                    xiaoliang.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zonghe.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zclick = false;
                    xclick = false;
                    if (pimgclick) {
                        commodityPresenter.request(id, 3, 1, 1);
                        Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_top);
                        // 这一步必须要做,否则不会显示.
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_text.setCompoundDrawables(null, null, drawable, null);
                        pimgclick = false;
                    } else {
                        commodityPresenter.request(id, 4, 1, 1);
                        Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_button);
                        // 这一步必须要做,否则不会显示.
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_text.setCompoundDrawables(null, null, drawable, null);
                        pimgclick = true;
                    }
                }
                break;
            case R.id.qiehuan:
                if (!qclick) {
                    qiehuan.setImageResource(R.drawable.shuqiehuan);
                    xrecycler.setLayoutManager(new LinearLayoutManager(this));
                    classCommodAdapter.setState(1);
                    qclick = true;
                } else {
                    qiehuan.setImageResource(R.drawable.qiehuan);
                    xrecycler.setLayoutManager(new GridLayoutManager(this, 2));
                    classCommodAdapter.setState(2);
                    qclick = false;
                }
                break;
        }
    }
}
