package com.yikangcheng.admin.yikang.activity.liveambitus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CateAddressMeiRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.CateAddressMeiRecyclerAdapterTwo;
import com.yikangcheng.admin.yikang.activity.adapter.CateAddressRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.CateAddressRecyclerAdapterTwo;
import com.yikangcheng.admin.yikang.activity.adapter.CateAddressRecyclerZhinengAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.CateImageAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.CateShopRecycleAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.TasteRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.liveambitus.city.CityActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.SwitchButton;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CateActivity extends BaseActivtiy implements SwitchButton.OnCheckedChangeListener {
    @BindView(R.id.shop_img_recycler)
    RecyclerView shop_img_recycler;
    @BindView(R.id.shop_recycler)
    RecyclerView shop_recycler;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.rela)
    LinearLayout rela;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.line2)
    LinearLayout line2;
    @BindView(R.id.gone_linear)
    LinearLayout gone_linear;
    @BindView(R.id.nested)
    NestedScrollView nested;
    @BindView(R.id.v_all_line)
    LinearLayout v_all_line;
    @BindView(R.id.v_meishi_line)
    LinearLayout v_meishi_line;
    @BindView(R.id.pop_all1)
    LinearLayout pop_all1;
    @BindView(R.id.pop_meishi)
    LinearLayout pop_meishi;
    @BindView(R.id.pop_zhineng)
    LinearLayout pop_zhineng;
    @BindView(R.id.pop_shai)
    LinearLayout pop_shai;
    @BindView(R.id.v_zhineng_line)
    LinearLayout v_zhineng_line;
    @BindView(R.id.v_shai_line)
    LinearLayout v_shai_line;
    @BindView(R.id.v_all_text)
    TextView v_all_text;
    @BindView(R.id.v_meishi_text)
    TextView v_meishi_text;
    @BindView(R.id.v_zhineng_text)
    TextView v_zhineng_text;
    @BindView(R.id.v_shai_text)
    TextView v_shai_text;
    @BindView(R.id.pop_all_text)
    TextView pop_all_text;
    @BindView(R.id.pop_meishi_text)
    TextView pop_meishi_text;
    @BindView(R.id.pop_zhineng_text)
    TextView pop_zhineng_text;
    @BindView(R.id.pop_shai_text)
    TextView pop_shai_text;
    @BindView(R.id.city_text)
    TextView city_text;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.recycler2)
    RecyclerView recycler2;
    private List<Integer> list_path;
    private PopupWindow popMenu;
    private PopupWindow popMenuz;
    private PopupWindow popMenum;
    private PopupWindow popMenus;
    private View contentView;
    private View contentZhiView;
    private View contentMeiView;
    private View contentShaiView;
    private CateAddressRecyclerAdapter cateAddressRecyclerAdapter;
    private CateAddressMeiRecyclerAdapter cateAddressMeiRecyclerAdapter;
    private CateAddressRecyclerAdapterTwo cateAddressRecyclerAdapterTwo;
    private CateAddressMeiRecyclerAdapterTwo cateAddressMeiRecyclerAdapterTwo;
    private CateAddressRecyclerZhinengAdapter cateAddressRecyclerZhinengAdapter;
    private RecyclerView recycler_two;
    private RecyclerView recycler_twom;
    private RecyclerView recyclerView, recyclerViewm;
    private TagFlowLayout folw1, folw2;
    private TextView wan, chong;
    private List<String> stringList = new ArrayList<>();
    private List<String> footList = new ArrayList<>();
    //保存用户筛选的文字
    private List<String> seleText = new ArrayList<>();
    private SwitchButton switch_mian, swith_jie, swith_xin;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        cateAddressRecyclerZhinengAdapter = new CateAddressRecyclerZhinengAdapter(CateActivity.this);
        cateAddressRecyclerAdapter = new CateAddressRecyclerAdapter(CateActivity.this);
        cateAddressMeiRecyclerAdapter = new CateAddressMeiRecyclerAdapter(CateActivity.this);
        cateAddressRecyclerAdapterTwo = new CateAddressRecyclerAdapterTwo(CateActivity.this);
        cateAddressMeiRecyclerAdapterTwo = new CateAddressMeiRecyclerAdapterTwo(CateActivity.this);
        contentMeiView = View.inflate(CateActivity.this, R.layout.popwin_supplier_list,
                null);
        contentView = View.inflate(CateActivity.this, R.layout.popwin_supplier_list,
                null);
        contentZhiView = View.inflate(CateActivity.this, R.layout.popwin_supplier_list_zhineng,
                null);
        contentShaiView = View.inflate(CateActivity.this, R.layout.popwin_supplier_list_shai,
                null);
        popMenu = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                750);
        popMenum = new PopupWindow(contentMeiView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                750);
        popMenuz = new PopupWindow(contentZhiView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                750);
        popMenus = new PopupWindow(contentShaiView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        initPopMenu();
        initPopMenuZ();
        initPopMenuM();
        initPopMenuS();
        setOnClickListener();
    }

    @SuppressLint("NewApi")
    @Override
    protected void initEventData() {
        //解决滑动不流畅
        shop_img_recycler.setHasFixedSize(true);
        shop_img_recycler.setNestedScrollingEnabled(false);
        shop_recycler.setHasFixedSize(true);
        shop_recycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CateActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        shop_img_recycler.setLayoutManager(linearLayoutManager);
        shop_img_recycler.setAdapter(new CateImageAdapter());
        shop_recycler.setLayoutManager(new LinearLayoutManager(CateActivity.this));
        shop_recycler.setAdapter(new CateShopRecycleAdapter());
        /**
         * 设置数据，方式一
         */
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path.add(R.drawable.qizhiyou_banner);
        list_path.add(R.drawable.qizhiyou_banner);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        banner.start();
        nested.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1 >= line.getBottom() - getHeight()) {
                    gone_linear.setVisibility(View.VISIBLE);
                    line.setVisibility(View.INVISIBLE);
                    recycler2.setVisibility(View.VISIBLE);
                } else {
                    gone_linear.setVisibility(View.INVISIBLE);
                    line.setVisibility(View.VISIBLE);
                    recycler2.setVisibility(View.INVISIBLE);
                }
            }
        });
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    footList.add("下午茶");
                    break;
                case 1:
                    footList.add("无辣不欢");
                    break;
                case 2:
                    footList.add("特色菜");
                    break;
                case 3:
                    footList.add("清淡口味");
                    break;
            }
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(layoutManager);
        TasteRecyclerAdapter tasteRecyclerAdapter = new TasteRecyclerAdapter(CateActivity.this);
        tasteRecyclerAdapter.addAll(footList);
        recycler.setAdapter(tasteRecyclerAdapter);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler2.setLayoutManager(layoutManager1);
        recycler2.setAdapter(tasteRecyclerAdapter);
    }

    /**
     * 测量高度
     */
    private int getHeight() {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        line.measure(w, h);
        int height = line.getMeasuredHeight();
        return height;
    }

    /**
     * 点击事件处理
     */
    public void setOnClickListener() {
        /**
         * 退出页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * 全部商区
         */
        v_all_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_all_text.setCompoundDrawables(null, null, drawable, null);
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenu.showAsDropDown(v_all_line);
            }
        });
        /**
         * 吸顶全部商区
         */
        pop_all1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                pop_all_text.setCompoundDrawables(null, null, drawable, null);
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenu.showAsDropDown(pop_all1);
            }
        });
        /**
         * 美食
         */
        v_meishi_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_meishi_text.setCompoundDrawables(null, null, drawable, null);
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenum.showAsDropDown(v_meishi_line);
            }
        });
        /**
         * 吸顶美食
         */
        pop_meishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                pop_meishi_text.setCompoundDrawables(null, null, drawable, null);
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenum.showAsDropDown(pop_meishi);
            }
        });
        /**
         * 智能排序
         */
        v_zhineng_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_zhineng_text.setCompoundDrawables(null, null, drawable, null);
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenuz.showAsDropDown(v_zhineng_line);
            }
        });
        /**
         * 吸顶智能排序
         */
        pop_zhineng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                pop_zhineng_text.setCompoundDrawables(null, null, drawable, null);
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenuz.showAsDropDown(pop_zhineng);
            }
        });
        /**
         * 筛选
         */
        v_shai_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_shai_text.setCompoundDrawables(null, null, drawable, null);
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenus.showAsDropDown(v_shai_line);
            }
        });
        /**
         * 吸顶筛选
         */
        pop_shai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.cate_jianshang);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                pop_shai_text.setCompoundDrawables(null, null, drawable, null);
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllT));
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                popMenus.showAsDropDown(pop_shai);
            }
        });
        /**
         * 弹框销毁
         */
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.movie_xia);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_all_text.setCompoundDrawables(null, null, drawable, null);
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                // 这一步必须要做,否则不会显示.
                pop_all_text.setCompoundDrawables(null, null, drawable, null);
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
            }
        });
        popMenum.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.movie_xia);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_meishi_text.setCompoundDrawables(null, null, drawable, null);
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                // 这一步必须要做,否则不会显示.
                pop_meishi_text.setCompoundDrawables(null, null, drawable, null);
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
            }
        });
        popMenuz.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.movie_xia);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_zhineng_text.setCompoundDrawables(null, null, drawable, null);
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                // 这一步必须要做,否则不会显示.
                pop_zhineng_text.setCompoundDrawables(null, null, drawable, null);
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
            }
        });
        popMenus.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.movie_xia);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                v_shai_text.setCompoundDrawables(null, null, drawable, null);
                v_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                v_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                // 这一步必须要做,否则不会显示.
                pop_shai_text.setCompoundDrawables(null, null, drawable, null);
                pop_all_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_meishi_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_zhineng_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
                pop_shai_text.setTextColor(CateActivity.this.getResources().getColor(R.color.ColorTextAllF));
            }
        });
        /**
         * 跳转城市
         */
        city_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CateActivity.this, CityActivity.class));
            }
        });
    }

    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        switch (button.getId()) {
            case R.id.swith_xin:
                break;
            case R.id.swith_jie:
                break;
            case R.id.switch_mian:
                break;
        }
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    /**
     * 弹框
     */
    private void initPopMenu() {
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        recyclerView = contentView.findViewById(R.id.recycler_one);
        recycler_two = contentView.findViewById(R.id.recycler_two);
        recyclerView.setLayoutManager(new LinearLayoutManager(CateActivity.this));
        recyclerView.setAdapter(cateAddressRecyclerAdapter);
        //测试使用
        stringList.clear();
        for (int i = 0; i < 5; i++) {
            stringList.add("预订" + i);
        }
        cateAddressRecyclerAdapter.addAll(stringList);
        recycler_two.setLayoutManager(new LinearLayoutManager(CateActivity.this));
        recycler_two.setAdapter(cateAddressRecyclerAdapterTwo);
        for (int i = 5; i < 10; i++) {
            stringList.add("预订" + i);
        }
        cateAddressRecyclerAdapterTwo.addAll(stringList);
        cateAddressRecyclerAdapterTwo.setOnclickListener(new CateAddressRecyclerAdapterTwo.Onclick() {
            @Override
            public void onClick(int position, String info) {
                v_all_text.setText(info);
                pop_all_text.setText(info);
                popMenu.dismiss();
            }
        });
        cateAddressRecyclerAdapter.setOnclickListener(new CateAddressRecyclerAdapter.Onclick() {
            @Override
            public void onClick(int position) {
                recycler_two.smoothScrollToPosition(0);
                cateAddressRecyclerAdapterTwo.setmPosition(0);
            }
        });
    }

    /**
     * 智能排序弹框
     */
    private void initPopMenuZ() {
        popMenuz.setOutsideTouchable(true);
        popMenuz.setBackgroundDrawable(new BitmapDrawable());
        popMenuz.setFocusable(true);
        popMenuz.setAnimationStyle(R.style.popwin_anim_style);
        recyclerView = contentZhiView.findViewById(R.id.recycler_one);
        recyclerView.setLayoutManager(new LinearLayoutManager(CateActivity.this));
        recyclerView.setAdapter(cateAddressRecyclerZhinengAdapter);
        for (int i = 5; i < 10; i++) {
            stringList.add("预订" + i);
        }
        cateAddressRecyclerZhinengAdapter.addAll(stringList);
        cateAddressRecyclerZhinengAdapter.setOnclickListener(new CateAddressRecyclerZhinengAdapter.Onclick() {
            @Override
            public void onClick(int position, String info) {
                pop_zhineng_text.setText(info);
                v_zhineng_text.setText(info);
                popMenuz.dismiss();
            }
        });
    }

    /**
     * 美食弹框
     */
    private void initPopMenuM() {
        popMenum.setOutsideTouchable(true);
        popMenum.setBackgroundDrawable(new BitmapDrawable());
        popMenum.setFocusable(true);
        popMenum.setAnimationStyle(R.style.popwin_anim_style);
        recyclerViewm = contentMeiView.findViewById(R.id.recycler_one);
        recycler_twom = contentMeiView.findViewById(R.id.recycler_two);
        recyclerViewm.setLayoutManager(new LinearLayoutManager(CateActivity.this));
        recyclerViewm.setAdapter(cateAddressMeiRecyclerAdapter);
        for (int i = 5; i < 10; i++) {
            stringList.add("预订" + i);
        }
        cateAddressMeiRecyclerAdapter.addAll(stringList);
        recycler_twom.setLayoutManager(new LinearLayoutManager(CateActivity.this));
        recycler_twom.setAdapter(cateAddressMeiRecyclerAdapterTwo);
        cateAddressMeiRecyclerAdapterTwo.addAll(stringList);
        cateAddressMeiRecyclerAdapterTwo.setOnclickListener(new CateAddressMeiRecyclerAdapterTwo.Onclick() {
            @Override
            public void onClick(int position, String info) {
                pop_meishi_text.setText(info);
                v_meishi_text.setText(info);
                popMenum.dismiss();
            }
        });
        cateAddressMeiRecyclerAdapter.setOnclickListener(new CateAddressMeiRecyclerAdapter.Onclick() {
            @Override
            public void onClick(int position) {
                recycler_twom.smoothScrollToPosition(0);
                cateAddressMeiRecyclerAdapterTwo.setmPosition(0);
            }
        });
    }

    /**
     * 筛选弹框
     */
    private void initPopMenuS() {
        popMenus.setOutsideTouchable(true);
        popMenus.setBackgroundDrawable(new BitmapDrawable());
        popMenus.setFocusable(true);
        popMenus.setAnimationStyle(R.style.popwin_anim_style);
        folw1 = contentShaiView.findViewById(R.id.folw1);
        folw2 = contentShaiView.findViewById(R.id.folw2);
        wan = contentShaiView.findViewById(R.id.wan);
        chong = contentShaiView.findViewById(R.id.chong);
        switch_mian = contentShaiView.findViewById(R.id.switch_mian);
        swith_jie = contentShaiView.findViewById(R.id.swith_jie);
        swith_xin = contentShaiView.findViewById(R.id.swith_xin);
        //测试使用
        stringList.clear();
        for (int i = 0; i < 5; i++) {
            stringList.add("预订");
        }
        folw1.setAdapter(new TagAdapter<String>(stringList) {
            @SuppressLint("ResourceType")
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(CateActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.pop_text_item, null);
                tv.setText(s);
                return tv;
            }
        });
        folw1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                View view1 = folw1.getChildAt(position);
                TagView textView = (TagView) view1;
                boolean isSelect = textView.isSelected();
                view1.setSelected(!isSelect);
                return false;
            }
        });
        folw2.setAdapter(new TagAdapter<String>(stringList) {
            @SuppressLint("ResourceType")
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(CateActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.pop_text_item, null);
                tv.setText(s);
                return tv;
            }
        });
        folw2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                View view1 = folw2.getChildAt(position);
                TagView textView = (TagView) view1;
                boolean isSelect = textView.isSelected();
                view1.setSelected(!isSelect);
                return false;
            }
        });
        /**
         * 完成
         */
        wan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popMenus.dismiss();
                seleText.clear();
                /**
                 * 循环拿下标
                 */
                for (Integer integer : folw1.getSelectedList()) {
                    String text = stringList.get(integer);
                    seleText.add(text);
                }
                for (Integer integer : folw2.getSelectedList()) {
                    String text = stringList.get(integer);
                    seleText.add(text);
                }
            }
        });
        /**
         * 重置
         */
        chong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folw1.onChanged();
                folw2.onChanged();
                switch_mian.setChecked(false);
                swith_jie.setChecked(false);
                swith_xin.setChecked(false);
            }
        });
        switch_mian.setOnCheckedChangeListener(this);
        swith_jie.setOnCheckedChangeListener(this);
        swith_xin.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_cate;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }
}
