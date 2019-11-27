package com.yikangcheng.admin.yikang.activity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallAdapter;
import com.fm.openinstall.listener.AppWakeUpAdapter;
import com.fm.openinstall.model.AppData;
import com.hjq.toast.ToastUtils;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.callback.ConfigText;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.DialogParams;
import com.mylhyl.circledialog.params.TextParams;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_New_Wo;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Wo;
import com.yikangcheng.admin.yikang.activity.fragment.juhe.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.fragment.juhe.Fragment_Xiao;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AppUpdateBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CheckupdatePresenter;
import com.yikangcheng.admin.yikang.updater.Updater;
import com.yikangcheng.admin.yikang.updater.UpdaterConfig;

import butterknife.BindView;

public class ApplySeleActivity extends BaseActivtiy {

    private CheckupdatePresenter checkupdatePresenter;
    private Fragment currentFragment = new Fragment();
    private Fragment_Shou fragment_shou = new Fragment_Shou();
    private Fragment_Xiao fragment_xiao = new Fragment_Xiao();
    private Fragment_Wo fragment_wo = new Fragment_Wo();
    @BindView(R.id.one)
    RadioButton one;
    @BindView(R.id.two)
    RadioButton two;
    @BindView(R.id.three)
    RadioButton three;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    private SharedPreferences sharedPreferences;
    @BindView(R.id.tabview)
    TextView tabview;

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("wo", MODE_PRIVATE);
        closeSwipeBack();
        onClickListener();
        one.setChecked(true);
    }


    //查询当前App版本号
    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = ApplySeleActivity.this.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(ApplySeleActivity.this.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    @Override
    protected void initEventData() {
        checkupdatePresenter = new CheckupdatePresenter(new ApplySeleActivity.UpdateApp());
        checkupdatePresenter.request("android");

        RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) tabview.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height =getStatusBarHeight(ApplySeleActivity.this);// 控件的高强制设成20
        tabview.setLayoutParams(linearParams);
        tabview.setBackgroundColor(Color.parseColor("#FFFFFF"));
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
    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fragment, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    /**
     * 处理点击事件
     *
     * @return
     */
    private void onClickListener() {
        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switchFragment(fragment_shou).commit();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("wo", "no");
                    editor.commit();
                    one.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextT));
                    two.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextF));
                    three.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextF));

                }
            }
        });
        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switchFragment(fragment_xiao).commit();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("wo", "no");
                    editor.commit();
                    one.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextF));
                    two.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextT));
                    three.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextF));
                }
            }
        });
        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switchFragment(fragment_wo).commit();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("wo", "yes");
                    editor.commit();
                    one.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextF));
                    two.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextF));
                    three.setTextColor(ApplySeleActivity.this.getResources().getColor(R.color.colorTextT));
                }
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_apply_sele;
    }

    @Override
    protected void createPresenter() {

    }

    @SuppressLint("MissingSuperCall")
    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        //super.onSaveInstanceState(outState);   //将这一行注释掉，阻止activity保存fragment的状态
    }


    /**
     * @authorszx 双击返回键退出应用
     */
    long lastPressed;
    long prePressed;

    @Override
    public void onBackPressed() {
        //获得系统第二次点击的时间
        lastPressed = System.currentTimeMillis();
        if (lastPressed - prePressed > 2000) {
            //把第一次点击获得的时间赋值给第二次
            prePressed = lastPressed;
            //弹出吐司
            ToastUtils.show("再点一次退出应用");
        } else {
            //结束页面（销毁页面）
            finish();
            System.exit(0);
        }
    }

    /**
     * 更新APP
     */
    private class UpdateApp implements ICoreInfe {
        private String versionName;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            final AppUpdateBean entity = (AppUpdateBean) request.getEntity();
            try {
                versionName = getVersionName();
                if (versionName != null && entity.getVersionNo() != null) {
                    if (!versionName.equals(entity.getVersionNo())) {
                        new CircleDialog.Builder()
                                .setMaxHeight(0.8f)
                                .setCanceledOnTouchOutside(false)
                                .setCancelable(false)
                                .configDialog(new ConfigDialog() {
                                    @Override
                                    public void onConfig(DialogParams params) {
                                    }
                                })
                                .setTitle("发现新版本")
                                .setText(entity.getDepict())
                                .configText(new ConfigText() {
                                    @Override
                                    public void onConfig(TextParams params) {
                                        params.gravity = Gravity.LEFT | Gravity.TOP;
                                    }
                                })
                                .setNegative("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String versionNo = entity.getVersionNo();
                                        String versionNostr = versionNo.substring(0, 1);
                                        String versionNamestr = versionName.substring(0, 1);
                                        if (versionNamestr.equals(versionNamestr)) {
                                            String versionNostr2 = versionNo.substring(2, 3);
                                            String versionNamestr2 = versionName.substring(2, 3);
                                            if (versionNostr2.equals(versionNamestr2)) {
                                                String versionNostr3 = versionNo.substring(versionNo.length() - 1, versionNo.length());
                                                String versionNamestr3 = versionName.substring(versionName.length() - 1, versionName.length());
                                                if (versionNamestr3.equals(versionNostr3)) {
                                                    return;
                                                } else {
                                                    return;
                                                }
                                            } else {
                                                finish();
                                            }
                                            return;
                                        } else {
                                            finish();
                                        }
                                    }
                                })
                                .setPositive("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ToastUtils.show("正在更新");
                                        UpdaterConfig config = new UpdaterConfig.Builder(ApplySeleActivity.this)
                                                .setTitle(getResources().getString(R.string.app_name))
                                                .setDescription(entity.getDepict())
                                                .setFileUrl(entity.getDownloadUrl())
                                                .setCanMediaScanner(true)
                                                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                                                        | DownloadManager.Request.NETWORK_WIFI)
                                                .build();
                                        Updater.get().showLog(true).download(config);
                                        finish();
                                    }
                                })
                                .configPositive(new ConfigButton() {
                                    @Override
                                    public void onConfig(ButtonParams params) {
                                        params.backgroundColorPress = Color.RED;
                                    }
                                })
                                .show(getSupportFragmentManager());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
