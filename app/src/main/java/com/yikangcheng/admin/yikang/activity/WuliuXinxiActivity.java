package com.yikangcheng.admin.yikang.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.WuliuAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.List;

public class WuliuXinxiActivity extends BaseActivtiy {

    private SwipeMenuListView messge_list;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        messge_list = findViewById(R.id.messge_list);
    }

    @Override
    protected void initEventData() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void create(SwipeMenu menu) {
                WindowManager m = WuliuXinxiActivity.this.getWindowManager();
                Display d = m.getDefaultDisplay();
                SwipeMenuItem reader = new SwipeMenuItem(getApplicationContext());
                //设置item的背景
                reader.setBackground(new ColorDrawable(0x00FFE0E0E0));
                //设置item的宽度
                int width = (int) (250);
                reader.setWidth(width);
                //设置item的标题
                reader.setTitle("标记已读");
                reader.setIcon(R.drawable.biaoweiyidu);
                // 定义item的高度
                reader.setTitleColor(Color.WHITE);
                //设置item标题字体的大小
                reader.setTitleSize(15);
                //添加到菜单中
                menu.addMenuItem(reader);
                //创建一个开放的item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                //设置item的背景
                openItem.setBackground(R.drawable.shanchu_item_shape);
                //设置item的宽度
                openItem.setWidth(width);
                //设置item的标题
                openItem.setTitle("删除");
                openItem.setIcon(R.drawable.shanchu);
                openItem.setTitleColor(Color.WHITE);
                //设置item标题字体的大小
                openItem.setTitleSize(15);
                //添加到菜单中
                menu.addMenuItem(openItem);

            }
        };
        //设置creator
        messge_list.setMenuCreator(creator);
        messge_list.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        break;
                }
                return false;
            }
        });
        //设置滑动的方向
        messge_list.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);//左
        messge_list.setMenuCreator(creator);
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