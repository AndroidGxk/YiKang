package com.yikangcheng.admin.yikang.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter.GoodCommRecyclerAdapter;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AssessAddPresenter;
import com.yikangcheng.admin.yikang.presenter.GoswfPresenter;
import com.yikangcheng.admin.yikang.util.CropUtils;
import com.yikangcheng.admin.yikang.util.FileUtil;
import com.yikangcheng.admin.yikang.util.StarBar;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 商品评论晒单页面
 */
public class GoodComBaskActivity extends BaseActivtiy implements ICoreInfe {

    @BindView(R.id.start)
    StarBar start;
    @BindView(R.id.img1)
    LinearLayout mImag1;
    @BindView(R.id.img2)
    LinearLayout mImag2;
    @BindView(R.id.img3)
    LinearLayout mImag3;
    @BindView(R.id.imag1)
    ImageView mImg1;
    @BindView(R.id.imag2)
    ImageView mImg2;
    @BindView(R.id.imag3)
    ImageView mImg3;
    @BindView(R.id.add_comm)
    TextView mAddComm;
    @BindView(R.id.comm_edit)
    TextView mCommEdit;
    @BindView(R.id.table)
    RelativeLayout table;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.good_img)
    ImageView good_img;
    int onclickPosition = 0;
    int num = 0;
    //相机相册
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_ALBUM = 2;
    private static final int REQUEST_CODE_CROUP_PHOTO = 3;
    private PromptDialog promptDialog;
    private Uri uri;
    private File file;
    private PromptButton cancle;
    private File file3;
    private File file2;
    private File file1;
    private GoswfPresenter goswfPresenter;
    private List<String> stringList;
    private AssessAddPresenter assessAddPresenter;
    private Intent intent;
    private int id;
    private String logo;

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_good_com_bask;
    }

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        mAddComm.setTextColor(Color.parseColor(getLogUser(this).getThemeColors()));
        GradientDrawable mAddCommDra = (GradientDrawable) mAddComm.getBackground();
        mAddCommDra.setStroke(2, Color.parseColor(getLogUser(this).getThemeColors()));
        table.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        stringList = new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        logo = intent.getStringExtra("logo");
        start.setStarMark(1);
        good_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        requestOptions.fallback(R.drawable.inco_log);
        Glide.with(this).load(Constants.BASETUPIANSHANGCHUANURL + logo)
                .apply(requestOptions)
                .into(good_img);
        /**
         * 上传图片接口
         */
        goswfPresenter = new GoswfPresenter(this);
        /**
         * 添加评论接口
         */
        assessAddPresenter = new AssessAddPresenter(new AddComm());
        //创建对象
        promptDialog = new PromptDialog(GoodComBaskActivity.this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        start.setIntegerMark(true);
        start.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {

            }
        });
    }

    @Override
    protected void initEventData() {
        /**
         * 点击事件
         */
        onClickLister();
        //相机相册
        file = new File(FileUtil.getCachePath(GoodComBaskActivity.this), "user-avatar.jpg");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            //通过FileProvider创建一个content类型的Uri(android 7.0需要这样的方法跨应用访问)
            uri = FileProvider.getUriForFile(BaseApp.getApp(), "com.yikangcheng.admin.yikang", file);
        }

    }

    /**
     * 处理点击事件
     *
     * @return
     */
    public void onClickLister() {
        cancle = new PromptButton("取消", null);
        //第一个图片选择
        mImag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickPosition = 1;
                promptDialog.showAlertSheet("", true, cancle,
                        new PromptButton("相册", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                uploadAvatarFromAlbumRequest();
                            }
                        }), new PromptButton("相机", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                if (ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                        || ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    // 申请权限
                                    ActivityCompat.requestPermissions(GoodComBaskActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                                } else {
                                    uploadAvatarFromPhotoRequest();
                                }
                            }
                        }));
            }
        });
        //第二个图片选择
        mImag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onclickPosition = 2;
                promptDialog.showAlertSheet("", true, cancle,
                        new PromptButton("相册", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                uploadAvatarFromAlbumRequest();
                            }
                        }), new PromptButton("相机", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                if (ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                        || ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    // 申请权限
                                    ActivityCompat.requestPermissions(GoodComBaskActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                                } else {
                                    uploadAvatarFromPhotoRequest();
                                }
                            }
                        }));
            }
        });
        //第三个图片选择
        mImag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickPosition = 3;
                promptDialog.showAlertSheet("", true, cancle,
                        new PromptButton("相册", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                uploadAvatarFromAlbumRequest();
                            }
                        }), new PromptButton("相机", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                if (ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                        || ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    // 申请权限
                                    ActivityCompat.requestPermissions(GoodComBaskActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                                } else {
                                    uploadAvatarFromPhotoRequest();
                                }
                            }
                        }));
            }
        });
        /**
         * 提交评论
         */
        mAddComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //评论内容
                String count = mCommEdit.getText().toString();
                if (count.equals("")) {
                    ToastUtils.show("您还没有填写评价内容");
                    return;
                }
                if ((int) start.getStarMark() == 0) {
                    num = 1;
                } else {
                    num = (int) start.getStarMark();
                }
                switch (stringList.size()) {
                    case 0:
                        assessAddPresenter.request(id, num, getLogUser(BaseApp.getApp()).getId(), count, "", "", "");
                        break;
                    case 1:
                        assessAddPresenter.request(id, num, getLogUser(BaseApp.getApp()).getId(), count, stringList.get(0), "", "");
                        break;
                    case 2:
                        assessAddPresenter.request(id, num, getLogUser(BaseApp.getApp()).getId(), count, stringList.get(0), stringList.get(1), "");
                        break;
                    case 3:
                        assessAddPresenter.request(id, num, getLogUser(BaseApp.getApp()).getId(), count, stringList.get(0), stringList.get(1), stringList.get(2));
                        break;
                }
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

    /**
     * photo
     */
    private void uploadAvatarFromPhotoRequest() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }


    /**
     * album
     */
    private void uploadAvatarFromAlbumRequest() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_ALBUM);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == REQUEST_CODE_ALBUM && data != null) {
            Uri newUri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                newUri = Uri.parse("file:///" + CropUtils.getPath(GoodComBaskActivity.this, data.getData()));
            } else {
                newUri = data.getData();
            }
            if (newUri != null) {
                startPhotoZoom(newUri);
            } else {
                ToastUtils.show("没有得到相册图片");
            }
        } else if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            startPhotoZoom(uri);
        } else if (requestCode == REQUEST_CODE_CROUP_PHOTO) {
            uploadAvatarFromPhoto();
        }
    }

    private void uploadAvatarFromPhoto() {
        compressAndUploadAvatar(file.getPath());

    }

    private void compressAndUploadAvatar(String fileSrc) {
        final File cover = FileUtil.getSmallBitmap(GoodComBaskActivity.this, fileSrc);
        //加载本地图片
        //设置图片圆角角度
        switch (onclickPosition) {
            case 1:
                mImag2.setVisibility(View.VISIBLE);
                Uri uri = Uri.fromFile(cover);
                file1 = cover;
                goswfPresenter.request(file1);
                mImag1.setVisibility(View.GONE);
                mImg1.setVisibility(View.VISIBLE);
                Glide.with(GoodComBaskActivity.this).load(uri)
                        .into(mImg1);
                break;
            case 2:
                mImag3.setVisibility(View.VISIBLE);
                Uri uri1 = Uri.fromFile(cover);
                file2 = cover;
                goswfPresenter.request(file2);
                mImag2.setVisibility(View.GONE);
                mImg2.setVisibility(View.VISIBLE);
                Glide.with(GoodComBaskActivity.this).load(uri1)
                        .into(mImg2);
                break;
            case 3:
                Uri uri2 = Uri.fromFile(cover);
                file3 = cover;
                goswfPresenter.request(file3);
                mImag3.setVisibility(View.GONE);
                mImg3.setVisibility(View.VISIBLE);
                Glide.with(GoodComBaskActivity.this).load(uri2)
                        .into(mImg3);
                break;
        }
//        goswfPresenter.request(cover);
//        promptDialog.showLoading("正在上传");
    }
    //todo 相机相册问题

    /**
     * 裁剪拍照裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop", "true");// crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1);// 这两项为裁剪框的比例.
        intent.putExtra("aspectY", 1);// x:y=1:1
//        intent.putExtra("outputX", 400);//图片输出大小
//        intent.putExtra("outputY", 400);
        intent.putExtra("output", Uri.fromFile(file));
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        startActivityForResult(intent, REQUEST_CODE_CROUP_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //这里已经获取到了摄像头的权限，想干嘛干嘛了可以
                    uploadAvatarFromPhotoRequest();
                } else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以
                    // 。
                    if (ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(GoodComBaskActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // 申请权限
                        ActivityCompat.requestPermissions(GoodComBaskActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void createPresenter() {

    }

    /**
     * 上传图片接口
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        stringList.add(Constants.BASETUPIANSHANGCHUANURL + (String) data);
    }

    @Override
    public void fail(ApiException e) {

    }

    /**
     * 添加评论接口
     */
    private class AddComm implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                finish();
            }
            ToastUtils.show(request.getMessage());
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
