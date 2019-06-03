package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AllFragment;
import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.All_A_Bean;
import com.yikangcheng.admin.yikang.bean.All_B_Bean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AllPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class All_A_Adapter extends RecyclerView.Adapter {

    private Context mContent;
    public ArrayList<ALLBean.OrderBean> mList;
    private All_B_Adapter mAll_b_adapter;
    private OnClickListener mListener;
    private OnClickListenerDelete mListenerDelete;


    public All_A_Adapter(Context context, ArrayList<ALLBean.OrderBean> orderBeans) {
        this.mContent = context;
        this.mList = orderBeans;
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fragment_all_a, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
//        mUserId =mList.get(position).getUserId();

        holder1.mBianhao.setText("订单编号:" + mList.get(position).getOrderNo());
        holder1.mData.setText(mList.get(position).getCreateTime());

        holder1.mPrice.setText("合计：¥" + mList.get(position).getRealPrice());
        if (mList.get(position).getOrderState().equals("INIT")) {
            holder1.mZhuangtai.setText("未支付");
        }

        if (mList.get(position).getOrderState().equals("SUCCESS")) {
            holder1.mZhuangtai.setText("支付成功");
        }
        if (mList.get(position).getOrderState().equals("REFUND")) {
            holder1.mZhuangtai.setText("退款");
        }
        if (mList.get(position).getOrderState().equals("CANCEL")) {
            holder1.mZhuangtai.setText("取消订单");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContent, LinearLayoutManager.VERTICAL, false);
        holder1.mRlv.setLayoutManager(linearLayoutManager);
        mAll_b_adapter = new All_B_Adapter(mContent, mList.get(position).getOrderDetailsList());
        holder1.mRlv.setAdapter(mAll_b_adapter);


        mAll_b_adapter.setOnClickListener(new All_B_Adapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId) {
                String orderState = mList.get(position).getOrderState();
                mListener.OnClickListener(v, orderId, orderState,position);
            }
        });

        holder1.mShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerDelete.OnClickListener(v, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void allData(List<ALLBean.OrderBean> order) {
        mList.addAll(order);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mBianhao;
        public LinearLayout mShanchu;
        private TextView mData;
        private TextView mPrice;
        private RecyclerView mRlv;
        private TextView mZhuangtai;

        public ViewHolder(View itemView) {
            super(itemView);
            mShanchu = itemView.findViewById(R.id.img_fragment_all_shanchu);
            mBianhao = itemView.findViewById(R.id.tv_fragment_all_bianhao);
            mData = itemView.findViewById(R.id.tv_fragment_all_data);
            mPrice = itemView.findViewById(R.id.tv_fragment_all_price);
            mRlv = itemView.findViewById(R.id.rlv_fragment_all_item);
            mZhuangtai = itemView.findViewById(R.id.tv_fragment_all_zhuangtai);
        }
    }

    public interface OnClickListener {
        void OnClickListener(View v, int orderId, String orderState, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }


    public interface OnClickListenerDelete {
        void OnClickListener(View v, int position);
    }

    public void setOnClickListenerDelete(OnClickListenerDelete listener) {
        this.mListenerDelete = listener;
    }
}
