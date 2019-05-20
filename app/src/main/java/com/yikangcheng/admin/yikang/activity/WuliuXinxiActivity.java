package com.yikangcheng.admin.yikang.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.WuliuAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

public class WuliuXinxiActivity extends BaseActivtiy {

    private SwipeMenuListView messge_list;

    @Override
    protected void initView() {
        messge_list = findViewById(R.id.messge_list);
    }

    @Override
    protected void initEventData() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void create(SwipeMenu menu) {
                //一个置顶的item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                //定义item的颜色
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // 定义item的标题
                openItem.setTitle("置顶");
                // 定义item标题的字体大小
                openItem.setTitleSize(18);
                // 定义item标题的字体的颜色
                openItem.setTitleColor(Color.WHITE);
                // 添加到菜单中
                menu.addMenuItem(openItem); // 一个删除的item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // 设置一个图标
                deleteItem.setIcon(R.drawable.shanchu);
                // 添加到菜单
                menu.addMenuItem(deleteItem);
            }
        };
        messge_list.setMenuCreator(creator);
        //设置滑动的方向
        messge_list.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);//左
        messge_list.setAdapter(new WuliuAdapter());
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_wuliu_xinxi;
    }

    @Override
    protected void createPresenter() {

    }
}