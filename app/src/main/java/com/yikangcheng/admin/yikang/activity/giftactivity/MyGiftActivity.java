package com.yikangcheng.admin.yikang.activity.giftactivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.giftactivity.adapter.MyGiftOneAdapter;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;

import butterknife.BindView;

public class MyGiftActivity extends BaseActivtiy {

    private AllAddressBean.ListUserAddressBean userAddressBean;

    /**
     * 回传值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            Bundle bundle = data.getExtras();
            userAddressBean = (AllAddressBean.ListUserAddressBean) bundle.getSerializable("result");
            if (userAddressBean != null) {
                myGiftOneAdapter.addAddress(userAddressBean);
            }
        }
    }

    @BindView(R.id.recy)
    RecyclerView recy;
    private MyGiftOneAdapter myGiftOneAdapter;
    private LinearLayoutManager linearLayoutManager;
    private View dialogview;
    private Dialog dialog;
    @BindView(R.id.back_img)
    ImageView back_img;

    @Override
    protected void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        myGiftOneAdapter = new MyGiftOneAdapter(this);
    }

    @Override
    protected void initEventData() {
        recy.setLayoutManager(linearLayoutManager);
        recy.setAdapter(myGiftOneAdapter);

        /**
         *显示领取弹框
         */
        myGiftOneAdapter.setOnClickListener(new MyGiftOneAdapter.onClickListener() {
            @Override
            public void onClick() {
                showDialog();
            }
        });

        /**
         * 选择地址
         */
        myGiftOneAdapter.setOnClickAddressListener(new MyGiftOneAdapter.onClickAddressListener() {
            @Override
            public void onClick() {
                Intent addIntent = new Intent(MyGiftActivity.this, AiteActivity.class);
                addIntent.putExtra("address", "true");
                startActivityForResult(addIntent, 1000);
            }
        });

        /**
         * 退出页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showDialog() {
        //dialog展示优惠券
        dialogview = getLayoutInflater().inflate(R.layout.lipin_lingqu, null);
        ImageView dismissImg = dialogview.findViewById(R.id.dismissImg);
        dialog = new Dialog(MyGiftActivity.this, R.style.dialogWindowAnim);
        dialog.setContentView(dialogview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        //设置显示动画
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        //设置显示位置
        dialog.onWindowAttributesChanged(wl);
        //设置点击外围消散
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dismissImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_my_gift;
    }

    @Override
    protected void createPresenter() {

    }
}
