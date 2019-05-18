package com.yikangcheng.admin.yikang.activity.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.ShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;


public class Fragment_Gou extends BaseFragment implements ShopRecyclerAdapter.TotalPriceListener, ShopRecyclerAdapter.checkBoxTouchListener {
    private RecyclerView shop_recyclertwo, shop_recycler;
    private ShopRecyclerAdapter shopRecyclerAdapter;
    private CheckBox all_check;
    private TextView text_total, num_text, heji, dele_text;
    private TextView tv_toolBar_right;
    private boolean judge = false;//判断编辑或完成

    @Override
    protected void initView(View view) {
        shop_recycler = view.findViewById(R.id.shop_recycler);
        shop_recyclertwo = view.findViewById(R.id.shop_recyclertwo);
        all_check = view.findViewById(R.id.all_check);
        num_text = view.findViewById(R.id.num_text);
        dele_text = view.findViewById(R.id.dele_text);
        tv_toolBar_right = view.findViewById(R.id.tv_toolBar_right);
        heji = view.findViewById(R.id.heji);
        text_total = view.findViewById(R.id.text_total);
    }

    @Override
    protected void initData() {
//        //解决滑动不流畅
        shop_recyclertwo.setHasFixedSize(true);
        shop_recyclertwo.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        // TODO: 2019/5/15 侧拉删除
        //购物车recyclerview
        shop_recycler.setLayoutManager(layoutManager);
        shopRecyclerAdapter = new ShopRecyclerAdapter(getContext());
        shopRecyclerAdapter.setTotalPriceListener(this);//设置总价回调接口
        shopRecyclerAdapter.setCheckBoxTouchListener(this);
        shop_recycler.setAdapter(shopRecyclerAdapter);

        //为你推荐recyclerview
        shop_recyclertwo.setLayoutManager(new GridLayoutManager(getContext(), 2));
        all_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = all_check.isChecked();
                shopRecyclerAdapter.checkAll(checked);
            }
        });
        tv_toolBar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (judge) {
                    heji.setVisibility(View.VISIBLE);
                    text_total.setVisibility(View.VISIBLE);
                    num_text.setVisibility(View.VISIBLE);
                    dele_text.setVisibility(View.GONE);
                    tv_toolBar_right.setText("编辑");
                    judge = false;
                } else {
                    heji.setVisibility(View.GONE);
                    text_total.setVisibility(View.GONE);
                    dele_text.setVisibility(View.VISIBLE);
                    num_text.setVisibility(View.GONE);
                    tv_toolBar_right.setText("完成");
                    judge = true;
                }

            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_gou;
    }

    @Override
    public void totalPrice(double totalPrice, int count) {
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
        String str = myformat.format(totalPrice);
        text_total.setText("¥" + str);
        if (count > 99) {
            num_text.setText("去结算(99+)");
            return;
        }
        num_text.setText("去结算(" + count + ")");
    }

    @Override
    public void checked(boolean isCheck) {
        all_check.setChecked(isCheck);
    }
}
