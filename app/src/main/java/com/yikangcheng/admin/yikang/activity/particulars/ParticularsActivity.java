package com.yikangcheng.admin.yikang.activity.particulars;

import android.app.Dialog;
import android.graphics.Paint;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;

public class ParticularsActivity extends BaseActivtiy implements CustomAdapt {

    private ArrayList<String> strings = new ArrayList<>();
    private TextView mTvParticularsYuanjia;
    private ImageView img_particulars_kefu;
    private RelativeLayout kefu_rela, rela_can, shu_rela;
    private LinearLayout line;
    private Dialog bottomDialog;
    private View contentView, particulars_parame, selection_good;
    private int width;
    private int height;
    private TagFlowLayout flow1, flow2;

    @Override
    protected void initView() {
        contentView = LayoutInflater.from(this).inflate(R.layout.particulars_safeg_diag, null);
        particulars_parame = LayoutInflater.from(this).inflate(R.layout.particulars_parame_diag, null);
        selection_good = LayoutInflater.from(this).inflate(R.layout.selection_good_item, null);
        mTvParticularsYuanjia = findViewById(R.id.tv_particulars_yuanjia);
        img_particulars_kefu = findViewById(R.id.img_particulars_kefu);
        shu_rela = findViewById(R.id.shu_rela);
        rela_can = findViewById(R.id.rela_can);
        line = findViewById(R.id.line);
        kefu_rela = findViewById(R.id.kefu_rela);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        flow1 = selection_good.findViewById(R.id.flow1);
        flow2 = selection_good.findViewById(R.id.flow2);
        for (int i = 0; i < 3; i++) {
            strings.add("数据" + i);
        }
        //预先设置选中
        TagAdapter adapter = new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) View.inflate(ParticularsActivity.this, R.layout.text_item, null);
                textView.setText(s);
                return textView;
            }
        };
        //颜色选择
        flow1.setAdapter(adapter) ;
        flow1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(ParticularsActivity.this, strings.get(position), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        adapter.setSelectedList(0);
        //内存选择
        flow2.setAdapter(adapter);
        flow2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(ParticularsActivity.this, strings.get(position), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        /**
         * 在TextView文字中间加横线（原价）
         */
        mTvParticularsYuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        bottomDialog = new Dialog(this, R.style.BottomDialog);


    }

    @Override
    protected void initEventData() {
        kefu_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(ParticularsActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(ParticularsActivity.this, info);
            }
        });
        //保障
        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.2));
                bottomDialog.setContentView(contentView, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
            }
        });
        //参数
        rela_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.2));
                bottomDialog.setContentView(particulars_parame, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
            }
        });
        //选择数量
        shu_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.2));
                bottomDialog.setContentView(selection_good, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 720;
    }
}
