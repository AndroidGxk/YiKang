package com.yikangcheng.admin.yikang.util;

import android.os.Environment;
import android.os.StatFs;

/**
 * 爱生活，爱代码
 * 创建于：2019/1/22 14:46
 * 作 者：T
 * 微信：704003376
 */
public class SDCardUtil {

    //判断SDCard剩余空间大小是否大于等于20M  如果SDCARD剩余空间大于20M那么就让SDCARD作为缓存目录 反之则不让其作为缓存目录
    public static boolean isSCardAvailable(long maxCacheSize) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // /mnt/sdcard/
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (getSDCardAvilable(rootPath) >= maxCacheSize)
                return true;
            else
                return false;
        } else
            return false;
    }


    //获取sdcard剩余空间大小  单位字节
    private static long getSDCardAvilable(String sdcardPath) {
        StatFs statFs = new StatFs(sdcardPath);
        int blocks = statFs.getAvailableBlocks();
        int blockSize = statFs.getBlockSize();
        long size = blocks * blockSize;
        return size;
    }
}
