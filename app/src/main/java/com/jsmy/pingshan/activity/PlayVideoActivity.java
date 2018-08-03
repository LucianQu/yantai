package com.jsmy.pingshan.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.UtilsTools;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import org.json.JSONException;

import butterknife.BindView;

public class PlayVideoActivity extends BaseActivity implements UniversalVideoView.VideoViewCallback {

    private static final String TAG = "PlayVideoActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    @BindView(R.id.videoView)
    UniversalVideoView videoView;
    @BindView(R.id.media_controller)
    UniversalMediaController mediaController;
    @BindView(R.id.video_layout)
    FrameLayout videoLayout;
    private String url;
    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_play_video;
    }

    @Override
    protected void initView() {
        videoView.setMediaController(mediaController);
        url = getIntent().getStringExtra("url");
        setVideoAreaSize();
    }

    @Override
    protected void initData() {
        if (mSeekPosition > 0) {
            videoView.seekTo(mSeekPosition);
        }
        videoView.start();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = videoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            videoLayout.setLayoutParams(layoutParams);
            //设置全屏时,无关的View消失,以便为视频控件和控制器控件留出最大化的位置
//            mBottomLayout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = videoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            videoLayout.setLayoutParams(layoutParams);
//            mBottomLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        // 视频暂停
        if (videoView != null && videoView.isPlaying()) {
            mSeekPosition = videoView.getCurrentPosition();
            MyLog.showLog(TAG, "onPause mSeekPosition=" + mSeekPosition);
            videoView.pause();
        }
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        // 视频开始播放或恢复播放
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        // 视频开始缓冲
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        // 视频结束缓冲
    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            videoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        videoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = videoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = videoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                videoLayout.setLayoutParams(videoLayoutParams);
                videoView.setVideoPath(url);
                videoView.requestFocus();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MyLog.showLog(TAG, "onSaveInstanceState Position=" + videoView.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
        MyLog.showLog(TAG, "onRestoreInstanceState Position=" + mSeekPosition);
    }
}
