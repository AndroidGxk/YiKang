package com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.myaccount.IntroActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.GoswfPresenter;
import com.yikangcheng.admin.yikang.presenter.UpdateUserMapperPresenter;
import com.yikangcheng.admin.yikang.presenter.UserInfoPresenter;
import com.yikangcheng.admin.yikang.util.CropUtils;
import com.yikangcheng.admin.yikang.util.FileUtil;

import java.io.File;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class BasicFragment extends BaseFragment implements ICoreInfe {
    private RelativeLayout mReLayoutFragmentBasicTouxiang;
    private LinearLayout mReLayoutFragmentBasicSynopsis;
    private int width;
    private int height;
    private Dialog mDialog;
    private View mInflate;
    private TextView mTvPhotograph, ok_btn;
    private TextView mTvPhotoAlbu;
    private ImageView mImgFinsh;
    private ImageView user_header;
    private EditText name_text, rela_name_text;
    private RadioGroup sex_group;
    private TextView jianjie;
    //相机相册
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_ALBUM = 2;
    private static final int REQUEST_CODE_CROUP_PHOTO = 3;
    private File file;
    private Uri uri;
    private GoswfPresenter goswfPresenter;
    private String path;
    private RadioButton nv, nan;
    private UserInfoPresenter userInfoPresenter;
    private PromptDialog promptDialog;
    private UpdateUserMapperPresenter updateUserMapperPresenter;
    private EditText youxiang_text;
    private PromptButton cancle;
    private UserDetailBean userCenter;
    int sex = 0;

    @Override
    protected void initView(View view) {

        //创建对象
        promptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        //头像
        mReLayoutFragmentBasicTouxiang = view.findViewById(R.id.reLayout_fragment_basic_touxiang);
        //单选框
        nan = view.findViewById(R.id.nan);
        //单选框
        nv = view.findViewById(R.id.nv);
        //简介
        mReLayoutFragmentBasicSynopsis = view.findViewById(R.id.reLayout_fragment_basic_synopsis);
        //保存信息
        ok_btn = view.findViewById(R.id.ok_btn);
        //相机相册
        file = new File(FileUtil.getCachePath(getActivity()), "user-avatar.jpg");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            //通过FileProvider创建一个content类型的Uri(android 7.0需要这样的方法跨应用访问)
            uri = FileProvider.getUriForFile(BaseApp.getApp(), "com.yikangcheng.admin.yikang", file);
        }
        goswfPresenter = new GoswfPresenter(new ImagePre());
        /**
         * 点击个人简介跳转页面
         */
        mReLayoutFragmentBasicSynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                intent.putExtra("jianjie", jianjie.getText().toString());
                startActivity(intent);
            }
        });

        //查询个人信息
        userInfoPresenter = new UserInfoPresenter(this);
        if (getLogUser(getContext()) != null) {
            userInfoPresenter.request(getLogUser(getContext()).getId());
        }
//        /**
//         * 点击姓名跳转页面
//         */
//        mReLayoutFragmentBasicName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), NameActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        /**
//         * 点击用户昵称跳转页面
//         */
//        mReLayoutFragmentBasic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), NicknameActivity.class);
//                startActivity(intent);
//            }
//        });

        //修改个人信息
        updateUserMapperPresenter = new UpdateUserMapperPresenter(new UpdateMesg());
        //用户名称
        name_text = view.findViewById(R.id.name_text);
        //姓名名称
        rela_name_text = view.findViewById(R.id.rela_name_text);
        //性别单选
        sex_group = view.findViewById(R.id.sex);
        //邮箱
        youxiang_text = view.findViewById(R.id.youxiang_text);
        //简介
        jianjie = view.findViewById(R.id.jianjie);
        //用户头像
        user_header = view.findViewById(R.id.user_avatar);
        mInflate = LayoutInflater.from(getContext()).inflate(R.layout.head_fragment_basic, null, false);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
//        mDialog = new Dialog(getContext(), R.style.BottomDialog);
        cancle = new PromptButton("取消", null);
        mReLayoutFragmentBasicTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
//                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.6));
//                mDialog.setContentView(mInflate, layoutParams);
//                mDialog.getWindow().setGravity(Gravity.BOTTOM);
//                mDialog.setCanceledOnTouchOutside(true);
//                mDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
//                mDialog.show();

                promptDialog.showAlertSheet("", true, cancle,
                        new PromptButton("相册", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                uploadAvatarFromAlbumRequest();
                            }
                        }), new PromptButton("相机", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                        || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    // 申请权限
                                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                                } else {
                                    uploadAvatarFromPhotoRequest();
                                }
                            }
                        }),
                        new PromptButton("更改头像", null));

            }
        });
        /**
         * 拍照id
         */
//        mTvPhotograph = mInflate.findViewById(R.id.tv_Photograph);   /**
        //         * 拍照
        //         */
//        mTvPhotograph.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (PermissionUtil.hasCameraPermission(getActivity())) {
////                    mDialog.dismiss();
//                }
//            }
//        });
//

        /**
         * 相册id
         */
//        mTvPhotoAlbu = mInflate.findViewById(R.id.tv_PhotoAlbu);
//        mTvPhotoAlbu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                mDialog.dismiss();
//            }
//        });
        mImgFinsh = mInflate.findViewById(R.id.img_finsh);
        /**
         * 完成
         */
//        mImgFinsh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                mDialog.dismiss();
//            }
//        });

        /**
         * 保存信息
         */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_str = name_text.getText().toString();
                String rela_name_str = rela_name_text.getText().toString();
                String jianjie_str = jianjie.getText().toString();
                if (nan.isChecked()) {
                    sex = 0;
                } else if (nv.isChecked()) {
                    sex = 1;
                }
                String email = youxiang_text.getText().toString();
                //todo 修改个人信息
                LoginBean logUser = getLogUser(getContext());
                if (logUser != null) {
                    if (path == null || path.equals("")) {
                        path = userCenter.getAvatar();
                    }
                    if (name_str.equals("") || rela_name_str.equals("") || jianjie_str.equals("")||email.equals("")) {
                        Toast.makeText(getContext(), "请完善信息", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    updateUserMapperPresenter.request(logUser.getId(), name_str, path, rela_name_str, email, sex, jianjie_str);
                    promptDialog.showLoading("正在保存");
                }
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
    protected void initData() {
        UserDetailBean userInfo = getUserInfo(getContext());
        name_text.setText(userInfo.getNickName());
        rela_name_text.setText(userInfo.getRealName());
        int gender = userInfo.getGender();
        if (gender == 0) {
            sex_group.check(sex_group.getChildAt(0).getId());
        } else {
            sex_group.check(sex_group.getChildAt(1).getId());
        }
        SharedPreferences intro = getContext().getSharedPreferences("intro", Context.MODE_PRIVATE);
        String introString = intro.getString("intro", "");
        if (!introString.equals("")) {
            jianjie.setText(introString);
        }
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_basic;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences intro = getContext().getSharedPreferences("intro", Context.MODE_PRIVATE);
        String introString = intro.getString("intro", "");
        jianjie.setText(introString);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
                newUri = Uri.parse("file:///" + CropUtils.getPath(getActivity(), data.getData()));
            } else {
                newUri = data.getData();
            }
            if (newUri != null) {
                startPhotoZoom(newUri);
            } else {
                Toast.makeText(getActivity(), "没有得到相册图片", Toast.LENGTH_LONG).show();
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
        final File cover = FileUtil.getSmallBitmap(getActivity(), fileSrc);
        //加载本地图片
        Uri uri = Uri.fromFile(cover);
        //设置图片圆角角度
        Glide.with(getContext()).load(uri)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(user_header);
        goswfPresenter.request(cover);
        promptDialog.showLoading("正在上传");
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
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // 申请权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                }
                break;
            default:
                break;
        }

    }

    /**
     * 查询个人信息
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        UserInfoBean entity = (UserInfoBean) request.getEntity();
        userCenter = (UserDetailBean) entity.getUserCenter();
        rela_name_text.setText(userCenter.getRealName());
        name_text.setText(userCenter.getNickName());
        if (userCenter.getGender() == 0) {
            nan.setChecked(true);
        } else if (userCenter.getGender() == 1) {
            nv.setChecked(true);
        }
        jianjie.setText(userCenter.getUserInfo());
        youxiang_text.setText(userCenter.getEmail());
        //设置图片圆角角度
        Glide.with(getContext()).load("https://static.yikch.com" + userCenter.getAvatar())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(user_header);
    }

    @Override
    public void fail(ApiException e) {

    }

    /**
     * 网络请求
     */
    private class ImagePre implements ICoreInfe {
        @Override
        public void success(Object data) {
            path = (String) data;
            if (path != null && (!path.equals(""))) {
                promptDialog.showSuccess("上传成功");
            } else {
                promptDialog.showSuccess("上传失败");
            }
        }

        @Override
        public void fail(ApiException e) {
            promptDialog.showError("上传失败");
        }
    }

    /**
     * 修改个人信息
     */
    private class UpdateMesg implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                promptDialog.showSuccess("修改成功");
                getActivity().finish();
            } else {
                promptDialog.dismiss();
                Toast.makeText(getContext(), "" + request.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            promptDialog.dismiss();
        }
    }
}