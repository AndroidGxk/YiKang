package com.yikangcheng.admin.yikang.util;
 
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

/**
 * Created by aoc on 2017/4/11.
 */
 
public class PriceUpDownView extends LinearLayout implements View.OnClickListener {
 
    private TextView priceView;
    private boolean isUp;
    private Callback callback;
 
    public PriceUpDownView(Context context) {
        this(context, null);
    }
 
    public PriceUpDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    public PriceUpDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
 
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.price_up_down, this);
        priceView = (TextView) findViewById(R.id.price_id);
        priceView.setOnClickListener(this);
    }
 
    @Override
    public void onClick(View v) {
 
        switch (v.getId()) {
            case R.id.price_id:
                setDrawable();
                callback.getStatus(isUp);
                break;
        }
    }
 
    private void setDrawable() {
        Drawable drawable = null;
        if (isUp) {
            isUp = false;
            drawable = getResources().getDrawable(R.drawable.xia_price);
        }
        else {
            isUp = true;
            drawable = getResources().getDrawable(R.drawable.shang_price);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        priceView.setCompoundDrawables(null, null, drawable, null);
        priceView.setCompoundDrawablePadding(8);
        priceView.setTextColor(Color.parseColor("#e02b4d"));
    }
 
    public  interface Callback {
        void getStatus(boolean isUp);
    }
 
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
