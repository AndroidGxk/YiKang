package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClickPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;

public class BankActivity extends BaseActivtiy implements ICoreInfe {
    @BindView(R.id.pufa)
    ImageView pufa;
    @BindView(R.id.zhongxin)
    ImageView zhongxin;
    @BindView(R.id.minsheng)
    ImageView minsheng;
    @BindView(R.id.guangzhou)
    ImageView guangzhou;
    @BindView(R.id.rela)
    RelativeLayout rela;
    @BindView(R.id.back_img)
    ImageView back_img;
    private Intent intent;
    @BindView(R.id.title)
    TextView title;
    private ClickPresenter clickPresenter;

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        rela.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        clickPresenter = new ClickPresenter(this);
//        title.setTextColor(Color.parseColor());
    }

    @Override
    protected void initEventData() {
        intent = new Intent(BankActivity.this, BankWebActivity.class);
        zhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("url", "https://m.zm123.com/ZXCredit/Index?pid=CS0482&sid=SJZMJFX41");
                intent.putExtra("title", "");
                intent.putExtra("title1", "中信银行");
                clickPresenter.request(getLogUser(BaseApp.getApp()).getId(),"ECITIC");
                startActivity(intent);
            }
        });
        minsheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("url", "https://creditcard.cmbc.com.cn/wsv2/?enstr=vhwW9k5WokCXNx35RGZHtlDIyvPGGkDXF8OvPSUY8xHtH0IrAj791hO3mblao%2ftXKo1JL9PdBbxL6AcE4XWJW4KkaaooMeELB8LgJxpXXdi7UlhmmQvxeZPVpo9MV5RjxAPkHv%2fFrm2HAxNOdDVTiGPI7mECCYFrw0dbV%2fkhCqxy2XtVaUL0j3v17KXkwYOSX2Ya94MNq0hzJtzsit8pQhl00ABjQFhgfXyVtIVavxGf5qlBD%2bLk0TdEQ8IPUiLA6Qx%2bv7LYML2cogAdYvwbjo9zKizzr0o8T%2ba1xXhRJrZ4GsSjPrZ%2fdg6LLJw%2flJnVp2y%2bL5bF%2fYKTVLhFebYeCg%3d%3d");
                intent.putExtra("title", "民生银行");
                intent.putExtra("title1", "");
                clickPresenter.request(getLogUser(BaseApp.getApp()).getId(),"CMBC");
                startActivity(intent);
            }
        });
        guangzhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("url", "https://i.creditcard.gzcb.com.cn/web/index.html?channel=r360&id=WAPgz104");
                intent.putExtra("title", "广州银行");
                intent.putExtra("title1", "");
                clickPresenter.request(getLogUser(BaseApp.getApp()).getId(),"GCB");
                startActivity(intent);
            }
        });
        pufa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("url", "https://ecentre.spdbccc.com.cn/creditcard/indexActivity.htm?data=ZF2855106&itemcode=0000018219");
                intent.putExtra("title", "浦发银行");
                intent.putExtra("title1", "");
                clickPresenter.request(getLogUser(BaseApp.getApp()).getId(),"SPDB");
                startActivity(intent);
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_bank;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void fail(ApiException e) {

    }
}
