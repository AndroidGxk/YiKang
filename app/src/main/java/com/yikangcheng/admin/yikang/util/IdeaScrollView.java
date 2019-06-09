package com.yikangcheng.admin.yikang.util;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.yikangcheng.admin.yikang.R;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class IdeaScrollView extends NestedScrollView  implements View.OnClickListener {

    private ImageView goTopBtn;

    public IdeaScrollView(@NonNull Context context) {
        super(context);
    }

    public IdeaScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IdeaScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setScrollListener(ImageView goTopBtn)
    {
        this.goTopBtn = goTopBtn;
        this.goTopBtn.setOnClickListener(this);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t >= 500)
        {
            goTopBtn.setVisibility(View.VISIBLE);
        }
        else
        {
            goTopBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBut)
        {
            this.smoothScrollTo(0, 0);
        }
    }
}
