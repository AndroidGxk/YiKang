package com.yikangcheng.admin.yikang.activity.adapter.particular;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;


import com.bigkoo.convenientbanner.holder.Holder;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.RecommendBean;

import java.util.List;

/**
 * item页底部的推荐商品适配器
 */
public class ItemRecommendAdapter implements Holder<List<RecommendBean>> {
    private View rootview;
    private GridView gv_recommend_goods;

    @Override
    public View createView(final Context context) {
        rootview = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.good_pricu_item_recommend, null);
        gv_recommend_goods = (GridView) rootview.findViewById(R.id.gv_recommend_goods);
        return rootview;
    }

    @Override
    public void UpdateUI(final Context context, int position, final List<RecommendBean> data) {
        gv_recommend_goods.setAdapter(new ItemRecommendGoodsAdapter(context, data));
    }
}
