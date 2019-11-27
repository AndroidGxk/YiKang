package com.yikangcheng.admin.yikang.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeekListNewActivity;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiBAdapter;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClassifyPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.List;

import butterknife.BindView;
import me.jessyan.autosize.AutoSizeConfig;

public class Fragment_Fen extends BaseFragment implements ICoreInfe {
    private RecyclerView mRlvFragmentFenleiYou;
    private RecyclerView mRlvFragmentFenleiZuo;
    private FenLeiAdapter mFenLeiAdapter;
    private FenLeiBAdapter mFenLeiBAdapter;
    private ClassifyPresenter classifyPresenter;
    private List<ClassifyListOneBean.ChildSubjectListBeanX> childSubjectList;
    private List<ClassifyListOneBean> entity;
    private int height;
    private TextView text_seek;
    private int width;
    private RelativeLayout rela;
    @BindView(R.id.tabview)
    TextView tabview;

    @Override
    protected void initView(View view) {
        //设置状态栏颜色
//        if (!getLogUser(getContext()).getThemeColors().equals("")) {
//            StatusBarUtil.setStatusBarMode((Activity) getContext(), true, Color.parseColor(getLogUser(getContext()).getThemeColors()));
//        } else {
//            StatusBarUtil.setStatusBarMode((Activity) getContext(), true, R.color.colorToolbar);
//        }
        AutoSizeConfig.getInstance().setCustomFragment(true);
        rela = (RelativeLayout) view.findViewById(R.id.rela);
        rela.setBackgroundColor(Color.parseColor(getLogUser(getContext()).getThemeColors()));
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
//        promptDialog.showLoading("加载中...");
        mRlvFragmentFenleiYou = view.findViewById(R.id.rlv__fragment_fenlei_you);
        text_seek = view.findViewById(R.id.text_seek);
        mRlvFragmentFenleiZuo = view.findViewById(R.id.rlv__fragment_fenlei_zuo);
        classifyPresenter = new ClassifyPresenter(this);
        classifyPresenter.request();
        //左边
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRlvFragmentFenleiZuo.setLayoutManager(linearLayoutManager);
        mFenLeiAdapter = new FenLeiAdapter(getContext(), getLogUser(getContext()).getThemeColors());
        mRlvFragmentFenleiZuo.setAdapter(mFenLeiAdapter);
        //右边
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        mRlvFragmentFenleiYou.setLayoutManager(linearLayoutManager1);
        mFenLeiBAdapter = new FenLeiBAdapter(getContext());
        mRlvFragmentFenleiYou.setAdapter(mFenLeiBAdapter);
        mFenLeiAdapter.setOnclickListener(new FenLeiAdapter.Onclick() {
            @Override
            public void onClick(int position) {
                mFenLeiBAdapter.removeAll();
                mFenLeiBAdapter.addData(entity.get(position).getChildSubjectList());
                mRlvFragmentFenleiYou.scrollToPosition(0);
            }
        });
        mFenLeiBAdapter.setOnClickListener(new FenLeiBAdapter.onClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(getContext(), SeekListNewActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        text_seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SeekActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) tabview.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height =getStatusBarHeight(getContext());// 控件的高强制设成20
        tabview.setLayoutParams(linearParams);
        tabview.setBackgroundColor(Color.parseColor(getLogUser(getContext()).getThemeColors()));
    }
    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_fen;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        entity = (List<ClassifyListOneBean>) request.getEntity();
        mFenLeiAdapter.addAll(entity);
        childSubjectList = entity.get(0).getChildSubjectList();
        mFenLeiBAdapter.addData(childSubjectList);
    }

    @Override
    public void fail(ApiException e) {
    }
}
