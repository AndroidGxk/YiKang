package com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu;

import android.Manifest;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.sobot.chat.application.MyApplication;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.myaccount.IntroActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.NameActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.NicknameActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.PhoneActivity;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.BasicBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BasicFragment extends BaseFragment {

    private RelativeLayout mReLayoutFragmentBasicName;
    private RelativeLayout mReLayoutFragmentBasicSave;
    private RelativeLayout mReLayoutFragmentBasicTouxiang;
    private RelativeLayout mReLayoutFragmentBasicPhone;
    private RelativeLayout mReLayoutFragmentBasic;
    private RelativeLayout mReLayoutFragmentBasicSex;
    private RelativeLayout mReLayoutFragmentBasicSynopsis;
    private int width;
    private int height;
    private Dialog mDialog;
    private View mInflate;
    private TextView mTvPhotograph;
    private TextView mTvPhotoAlbu;
    private ImageView mImgFinsh, user_header;
    private TextView name_text, rela_name_text;
    private RadioGroup sex_group;
    private TextView phone_text, jianjie;
    private ImageView mUserHeader;
    //相机相册

    @Override
    protected void initView(View view) {
        //用户姓名
        mReLayoutFragmentBasicName = view.findViewById(R.id.reLayout_fragment_basic_name);
        //保存
        mReLayoutFragmentBasicSave = view.findViewById(R.id.reLayout_fragment_basic_save);
        //头像
        mReLayoutFragmentBasicTouxiang = view.findViewById(R.id.reLayout_fragment_basic_touxiang);
        //手机号
        mReLayoutFragmentBasicPhone = view.findViewById(R.id.reLayout_fragment_basic_phone);
        //用户昵称
        mReLayoutFragmentBasic = view.findViewById(R.id.reLayout_fragment_basic_niceng);
        //性别
        mReLayoutFragmentBasicSex = view.findViewById(R.id.reLayout_fragment_basic_sex);
        //简介
        mReLayoutFragmentBasicSynopsis = view.findViewById(R.id.reLayout_fragment_basic_synopsis);



        /**
         * 点击个人简介跳转页面
         */
        mReLayoutFragmentBasicSynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent);

            }
        });

        /**
         * 点击手机号跳转页面
         */
        mReLayoutFragmentBasicPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhoneActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 点击姓名跳转页面
         */
        mReLayoutFragmentBasicName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NameActivity.class);
                startActivity(intent);
            }
        });


        /**
         * 点击用户昵称跳转页面
         */
        mReLayoutFragmentBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NicknameActivity.class);
                startActivity(intent);
            }
        });
        //用户名称
        name_text = view.findViewById(R.id.name_text);
        //姓名名称
        rela_name_text = view.findViewById(R.id.rela_name_text);
        //性别单选
        sex_group = view.findViewById(R.id.sex);
        //手机号
        phone_text = view.findViewById(R.id.phone_text);
        //简介
        jianjie = view.findViewById(R.id.jianjie);
        //用户头像
        user_header = view.findViewById(R.id.user_header);
        mInflate = LayoutInflater.from(getContext()).inflate(R.layout.head_fragment_basic, null, false);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        mDialog = new Dialog(getContext(), R.style.BottomDialog);

        mReLayoutFragmentBasicTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.6));
                mDialog.setContentView(mInflate, layoutParams);
                mDialog.getWindow().setGravity(Gravity.BOTTOM);
                mDialog.setCanceledOnTouchOutside(true);
                mDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                mDialog.show();
            }
        });
        /**
         * 拍照id
         */
        mTvPhotograph = mInflate.findViewById(R.id.tv_Photograph);

        /**
         * 相册id
         */
        mTvPhotoAlbu = mInflate.findViewById(R.id.tv_PhotoAlbu);
        mImgFinsh = mInflate.findViewById(R.id.img_finsh);
        /**
         * 完成
         */
        mImgFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        /**
         *  拍照  读取相册监听
         */
        initPhotoListener();
    }

    private void initPhotoListener() {
        /**
         * 打开相机
         */
        mTvPhotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });

        /**
         * 打开相册
         */
        mTvPhotoAlbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();

            }
        });
    }

    @Override
    protected void initData() {
        UserDetailBean userInfo = getUserInfo(getContext());
        name_text.setText(userInfo.getNickName());
        rela_name_text.setText(userInfo.getRealName());
        phone_text.setText(userInfo.getMobile() + "");
        int gender = userInfo.getGender();
        if (gender == 0) {
            sex_group.check(sex_group.getChildAt(0).getId());
        } else {
            sex_group.check(sex_group.getChildAt(1).getId());
        }
        Glide.with(getContext()).load("https://static.yikch.com" + userInfo.getAvatar()).into(user_header);
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

    }

    //===================================================================
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    private File mOutputImage;
    private static final int CAMERA_CODE = 1;
    private static final int ALBUM_CODE = 2;

    private void openAlbum() {
        //开启系统相册
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            executeOpenAlbum();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 300);
        }
    }

    private void executeOpenAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_CODE);
    }
    //开启相机拍照上传

    private void open() {
        //处理权限
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            take_photo();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    private File mFile;

    public void take_photo() {

        String status = Environment.getExternalStorageState();

        if (status.equals(Environment.MEDIA_MOUNTED)) {

            //创建File对象，用于存储拍照后的图片
            mOutputImage = new File(getActivity().getExternalCacheDir(), "output_image.jpg");

            try {

                if (mOutputImage.exists()) {

                    mOutputImage.delete();

                }

                mOutputImage.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

            if (Build.VERSION.SDK_INT >= 24) {

                imageUri = FileProvider.getUriForFile(getActivity(), "com.llk.study.activity.PhotoActivity", mOutputImage);

            } else {

                imageUri = Uri.fromFile(mOutputImage);

            }

            //启动相机程序

            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            startActivityForResult(intent, TAKE_PHOTO);

        } else {

            Toast.makeText(MyApplication.getInstance(), "没有储存卡", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                try {

                    Bitmap bitmap1 = getRealCompressedBitmap(mOutputImage.getPath().toString(), 150, 150);
                    //转为file上传处理照相之后的结果并上传
                    File file = getFile(bitmap1);
                    //设置图片圆角角度
                    RoundedCorners roundedCorners= new RoundedCorners(6);
                    //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
                    RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
                    Glide.with(this).load(bitmap1).apply(options).into(user_header);//==========================================1111111
                    okUpload(file);////1
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == ALBUM_CODE) {
            //相册
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                //处理uri,7.0以后的fileProvider 把URI 以content provider 方式
                // 对外提供的解析方法
                File file = getFileFromUri(imageUri, getActivity());
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream
                            (imageUri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //处理照相之后的结果并上传
//                mFile
//                mIvUploadHeader.setImageBitmap(bitmap);
                if (file.exists()) {

                    //设置图片圆角角度
                    RoundedCorners roundedCorners= new RoundedCorners(6);
                    //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
                    RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
                    Glide.with(this).load(file).apply(options).into(user_header);//  ================2222222222

                    okUpload(file);////2
                }
            }
        }
    }

    //在这里抽取了一个方法   可以封装到自己的工具类中...
    public File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 图片压缩
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap getRealCompressedBitmap(String pathName, int reqWidth, int reqHeight) {
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        int width = options.outWidth / 2;
        int height = options.outHeight / 2;
        int inSampleSize = 1;

        while (width / inSampleSize >= reqWidth && height / inSampleSize >= reqHeight) {
            inSampleSize = inSampleSize * 2;
        }

        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(pathName, options);
        //showBitmapInfos(pathName);
        return bitmap;
    }


    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     * 通过内容解析中查询uri中的文件路径
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        //内容解析者,查询系统资源
        ContentResolver contentResolver = context.getContentResolver();
        //
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //文件的路径
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {

                file = new File(filePath);
            }
        }
        return file;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //有权限
                take_photo();
            }
        } else if (requestCode == 300) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //有权限
                executeOpenAlbum();
            }
        }
    }

    private void okUpload(File file) {
        RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("avatar", file.getName(), body)//头像地址
                .addFormDataPart("userId", "11")
                .build();
        Request request = new Request.Builder()
                .url(Constants.BASEURL + "/api/updateUserMapper")
                .addHeader("Content-Type", "multipart/form-data")
                .post(multipartBody)
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast("上传失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
//                final HeaderImageFileBean bean = gson.fromJson(string, HeaderImageFileBean.class);
                BasicBean basicBean = gson.fromJson(string, BasicBean.class);
//                if (bean != null) {
//                    if (bean.getCode() == 0) {
//                        showToast("上传成功");
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                setHeader(bean.getData().getHeadImagePath());
//                            }
//                        });
//                    }
//                }
            }
        });
    }

    /**
     * 接口请求回来数据  用这个image
     * @param headerImage
     */
    private void setHeader(String headerImage) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop();
        //设置头像glide

        Glide.with(this).load(headerImage).apply(options).into(user_header);
    }

    public void showToast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}