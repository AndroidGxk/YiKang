package com.yikangcheng.admin.yikang.activity.seek;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeekListActivity;
import com.yikangcheng.admin.yikang.activity.adapter.SeekHotAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.TagAdapter;
import com.yikangcheng.admin.yikang.activity.edittext_delete.ETextWithDelete;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.SeekHotBean;
import com.yikangcheng.admin.yikang.greendao.SoSuoDb;
import com.yikangcheng.admin.yikang.greendao.Utils;
import com.yikangcheng.admin.yikang.util.FlowTagLayout;
import com.yikangcheng.admin.yikang.util.OnTagClickListener;
import com.yikangcheng.admin.yikang.util.SerachUtils;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

public class SeekActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView mImgActivitySeekDelete;
    private TextView mTvActivitySeekCancel;
    private ETextWithDelete mEditTixtActivitySeekSousuo;
    private RecyclerView mRlvActivitySeekHot;
    private List<SoSuoDb> mSoSuoDbs;
    private FlowTagLayout flowTagLayout;
    private TagAdapter tagAdapter;
    private List<String> searchHistory;//搜索历史
    private int width;

    @Override
    protected void initView() {
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        int height = display.getHeight();
        //删除最近搜索按钮
        mImgActivitySeekDelete = (ImageView) findViewById(R.id.img_activity_seek_delete);
        //取消搜索
        mTvActivitySeekCancel = (TextView) findViewById(R.id.tv_activity_seek_cancel);
        //搜索EditText
        mEditTixtActivitySeekSousuo = (ETextWithDelete) findViewById(R.id.EditTixt_activity_seek_sousuo);
        //最近搜索
        flowTagLayout = (FlowTagLayout) findViewById(R.id.FlowLayout_activity_seek);
        //热门搜索
        mRlvActivitySeekHot = (RecyclerView) findViewById(R.id.rlv_activity_seek_hot);

        //获得搜索历史
        searchHistory = SerachUtils.getInstance().getSearchList();
        tagAdapter = new TagAdapter(this);
        //设置这个模式意思是处理流标签点击事件
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
        flowTagLayout.setAdapter(tagAdapter);
        tagAdapter.onlyAddAll(searchHistory);
        //点击流标签让历史文字出现在EditText上,并执行搜索
        flowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                View view1 = parent.getAdapter().getView(position, null, null);
                String tag = (String) view1.getTag();
                mEditTixtActivitySeekSousuo.setText(tag);
                startActivityToResult();
            }
        });


        /**
         * 热门搜索
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRlvActivitySeekHot.setLayoutManager(linearLayoutManager);
        ArrayList<SeekHotBean> seekHotBeans = new ArrayList<>();
        seekHotBeans.add(new SeekHotBean(null, R.drawable.seekhot_a));
        seekHotBeans.add(new SeekHotBean(null, R.drawable.seekhot_b));
        seekHotBeans.add(new SeekHotBean(null, R.drawable.seekhot_c));
        SeekHotAdapter seekHotAdapter = new SeekHotAdapter(this, seekHotBeans);
        mRlvActivitySeekHot.setAdapter(seekHotAdapter);


        /**
         * 点击取消按钮取消搜索
         */
        seekFinish();


        /**
         * 这是回车键的监听
         */
        mEditTixtActivitySeekSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    String s = mEditTixtActivitySeekSousuo.getText().toString();
                    mSoSuoDbs = new ArrayList<>();
                    mSoSuoDbs.add(new SoSuoDb(null, s));
                    Utils.getInstance().insent(mSoSuoDbs);
                    tagAdapter.onlyAdd(mEditTixtActivitySeekSousuo.getText().toString());
                    tagAdapter.notifyDataSetChanged();
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

    }

    /**
     * 去往搜索结果
     */
    private void startActivityToResult() {
        Intent intent = new Intent(SeekActivity.this, SeekListActivity.class);
        String s = mEditTixtActivitySeekSousuo.getText().toString();
        intent.putExtra("count", s);
        startActivity(intent);
    }

    /**
     * 这是清除历史搜索
     */
    private void deleteAllDb() {
        mImgActivitySeekDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SoSuoDb> select = Utils.getInstance().select();
                for (int i = 0; i < select.size(); i++) {
                    SerachUtils.getInstance().clear();
                    searchHistory.clear();
                    tagAdapter.clear();
                    tagAdapter.notifyDataSetChanged();
                }
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


    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width / 2;
    }
}
