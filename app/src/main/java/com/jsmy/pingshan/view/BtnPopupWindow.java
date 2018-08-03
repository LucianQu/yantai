package com.jsmy.pingshan.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jsmy.pingshan.R;

import java.io.File;

import vn.tungdx.mediapicker.MediaOptions;
import vn.tungdx.mediapicker.activities.MediaPickerActivity;


/**
 * Created by jsmy001 on 2016/6/25 0025.
 * 打赏的泡泡窗口
 */
public class BtnPopupWindow extends PopupWindow {

    Activity activity;
    View view;

    Button bt_send;
    String tarpid;
    String dtid;
    int curent_index = -1;
    LinearLayout[] linearLayouts;

    private static final int TAKE_VIDEO = 1003;
    private static final int TAKE_IMG = 1001;
    private static final int REQUEST_VIDEO = 1004;
    private static final int REQUEST_IMG = 1002;

    private TextView txtCamera;
    private TextView txtGallery;
    private TextView txtCancel;

    public BtnPopupWindow(final Activity activity, final int num) {
        super(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.activity = activity;
        view = activity.getLayoutInflater().inflate(R.layout.activity_gallery_and_camera, null);
        txtCamera = view.findViewById(R.id.txt_camera);
        txtGallery = view.findViewById(R.id.txt_gallery);
        txtCancel = view.findViewById(R.id.txt_cancel);
        if (1 == num) {
            txtCamera.setText("拍照");
            txtGallery.setText("相册");
        } else {
            txtCamera.setText("拍摄视频");
            txtGallery.setText("选择视频");
        }
        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == num) {
                    takePictureForCamera();
                } else {
                    video();
                }

                dismiss();
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        txtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == num) {
                    takePictureFroList();
                } else {
                    openVideoList();
                }

                dismiss();
            }
        });
        initViews();
        super.setContentView(view);
    }


    private void initViews() {
        //外部点击关闭
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable());

        //设置背景半透明
        setWindowBgAlpha(activity, 0.8f);

        //设置隐藏时还原透明度
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowBgAlpha(activity, 1f);
            }
        });

    }

    public void video() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        activity.startActivityForResult(intent, TAKE_VIDEO);
    }

    private void openVideoList() {
        MediaOptions.Builder builder = new MediaOptions.Builder();
        MediaOptions options = builder.selectVideo().canSelectMultiVideo(true).build();
        MediaPickerActivity.open(activity, REQUEST_VIDEO, options);
    }

    private void takePictureForCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "shanhong")));
        activity.startActivityForResult(intent, TAKE_IMG);
    }

    private void takePictureFroList() {
        MediaOptions.Builder builder = new MediaOptions.Builder();
        MediaOptions options = builder.selectPhoto().canSelectMultiPhoto(true).build();
        MediaPickerActivity.open(activity, REQUEST_IMG, options);
    }

    public static void setWindowBgAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }

}
