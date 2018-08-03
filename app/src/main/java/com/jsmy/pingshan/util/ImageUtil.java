package com.jsmy.pingshan.util;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.LruCache;

import com.jsmy.pingshan.model.API;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/2.
 */

public class ImageUtil {
    /**
     * 服务器返回url，通过url去获取视频的第一帧
     * Android 原生给我们提供了一个MediaMetadataRetriever类
     * 提供了获取url视频第一帧的方法,返回Bitmap对象
     *
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
            saveBitmap(videoUrl, bitmap);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    /**
     * 获取本地视频的第一帧
     *
     * @param localPath
     * @return
     */
    public static Bitmap getLocalVideoBitmap(String localPath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(localPath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    private static void saveBitmap(String url, Bitmap bitmap) {


        String cachePath = API.SAVA_DOC_PATH;

        File file = new File(cachePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        url = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));

        String imagPath = cachePath + url + ".png";
        try {
            File imgFile = new File(imagPath);
            if (imgFile.exists())
                return;
            FileOutputStream outputStream = new FileOutputStream(imgFile);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);

            byte[] result = output.toByteArray();
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            outputStream.write(result);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
