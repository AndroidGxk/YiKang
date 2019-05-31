package com.yikangcheng.admin.yikang.activity.seek;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeekListActivity;
import com.yikangcheng.admin.yikang.activity.adapter.SeekHotAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.SeekHotBean;
import com.yikangcheng.admin.yikang.greendao.SoSuoDb;
import com.yikangcheng.admin.yikang.greendao.Utils;
import com.yikangcheng.admin.yikang.util.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class SeekActivity extends BaseActivtiy {


    private ImageView mImgActivitySeekDelete;
    private TextView mTvActivitySeekCancel;
    private EditText mEditTixtActivitySeekSousuo;
    private RecyclerView mRlvActivitySeekRecently;
    private RecyclerView mRlvActivitySeekHot;
    private FlowLayout mFlowLayout_activity_seek;
    private List<SoSuoDb> mSoSuoDbs;

    @Override
    protected void initView() {

        //删除最近搜索按钮
        mImgActivitySeekDelete = (ImageView) findViewById(R.id.img_activity_seek_delete);
        //取消搜索
        mTvActivitySeekCancel = (TextView) findViewById(R.id.tv_activity_seek_cancel);
        //搜索EditText
        mEditTixtActivitySeekSousuo = (EditText) findViewById(R.id.EditTixt_activity_seek_sousuo);
        //最近搜索
        mFlowLayout_activity_seek = (FlowLayout) findViewById(R.id.FlowLayout_activity_seek);
        //热门搜索
        mRlvActivitySeekHot = (RecyclerView) findViewById(R.id.rlv_activity_seek_hot);


        /**
         * 热门搜索
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRlvActivitySeekHot.setLayoutManager(linearLayoutManager);
        ArrayList<SeekHotBean> seekHotBeans = new ArrayList<>();
        seekHotBeans.add(new SeekHotBean(null,R.drawable.seekhot_a));
        seekHotBeans.add(new SeekHotBean(null,R.drawable.seekhot_b));
        seekHotBeans.add(new SeekHotBean(null,R.drawable.seekhot_c));
        SeekHotAdapter seekHotAdapter = new SeekHotAdapter(this,seekHotBeans);
        mRlvActivitySeekHot.setAdapter(seekHotAdapter);


        /**
         * 点击取消按钮取消搜索
         */
        seekFinish();

        /**
         * 这是点击回车键进行搜索
         */
        mEditTixtActivitySeekSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }

        });

        /**
         * 这是回车键的监听
         */
        mEditTixtActivitySeekSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    Toast.makeText(SeekActivity.this, "你点击了回车", Toast.LENGTH_SHORT).show();
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    String s = mEditTixtActivitySeekSousuo.getText().toString();
                    mSoSuoDbs = new ArrayList<>();
                    mSoSuoDbs.add(new SoSuoDb(null, s));
                    Utils.getInstance().insent(mSoSuoDbs);
                    mFlowLayout_activity_seek.addTextView(s);
                    Intent intent = new Intent(SeekActivity.this, SeekListActivity.class);
                    intent.putExtra("count", s);
                    startActivity(intent);
                }
                return false;
            }
        });
        /**
         * 这是清除历史搜索
         */
        deleteAllDb();
        List<SoSuoDb> select = Utils.getInstance().select();
        for (int i = 0; i < select.size(); i++) {
            SoSuoDb soSuoDb = select.get(i);
            mFlowLayout_activity_seek.addTextView(soSuoDb.getSeekTitle());
        }
    }

    /**
     * 这是清除历史搜索
     */
    private void deleteAllDb() {
        mImgActivitySeekDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().deleAAll();
                mFlowLayout_activity_seek.removeAllViews();
            }
        });
    }

    /**
     * 点击取消按钮取消搜索
     */
    private void seekFinish() {
        mTvActivitySeekCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_seek;
    }

    @Override
    protected void createPresenter() {

    }


}
