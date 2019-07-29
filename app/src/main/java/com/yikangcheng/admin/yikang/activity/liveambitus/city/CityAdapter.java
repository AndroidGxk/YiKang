package com.yikangcheng.admin.yikang.activity.liveambitus.city;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YH on 2016/10/19.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private Context mContext;
    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;
    private List<City> mList = new ArrayList<>();
    private onItemClickListener listener;


    public CityAdapter(Context mContext, List<City> cityLists) {
        this.mContext = mContext;
        this.mList = cityLists;
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, final int position) {
        City city = mList.get(position);
        holder.tvCityName.setText(city.getCityName());

        holder.rlContentWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(position);
            }
        });

        if(position==0){
            holder.tvStickyHeader.setVisibility(View.VISIBLE);
            holder.tvStickyHeader.setText(city.getCityPinyin());
            holder.tvStickyHeader.setTag(FIRST_STICKY_VIEW);
        }else {
            if(!TextUtils.equals(city.getFirstPinYin(),mList.get(position-1).getFirstPinYin())){
                holder.tvStickyHeader.setVisibility(View.VISIBLE);
                holder.tvStickyHeader.setText(city.getFirstPinYin());
                holder.itemView.setTag(HAS_STICKY_VIEW);
            }else {
                holder.tvStickyHeader.setVisibility(View.GONE);
                holder.itemView.setTag(NONE_STICKY_VIEW);
            }
        }
        holder.itemView.setContentDescription(city.getFirstPinYin());
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }
    public class CityViewHolder extends RecyclerView.ViewHolder {

        TextView tvStickyHeader, tvCityName;
        RelativeLayout rlContentWrapper;
        public CityViewHolder(View itemView) {
            super(itemView);
            tvStickyHeader = (TextView) itemView.findViewById(R.id.tv_sticky_header_view);
            rlContentWrapper = (RelativeLayout) itemView.findViewById(R.id.rl_content_wrapper);
            tvCityName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
    public interface onItemClickListener{
        void itemClick(int position);
    }
}
