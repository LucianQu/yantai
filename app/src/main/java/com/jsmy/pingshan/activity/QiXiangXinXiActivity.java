package com.jsmy.pingshan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.fragment.LeiDaFragment;
import com.jsmy.pingshan.fragment.TianQiFragment;
import com.jsmy.pingshan.fragment.WeiXingFragment;
import com.jsmy.pingshan.model.API;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QiXiangXinXiActivity extends BaseActivity {

    @BindView(R.id.tv_tianqi)
    TextView tvTianqi;
    @BindView(R.id.tv_weixing)
    TextView tvWeixing;
    @BindView(R.id.tv_leida)
    TextView tvLeida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_qi_xiang_xin_xi;
    }

    @Override
    protected void initView() {
        changeBtn(1);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_tianqi, R.id.tv_weixing, R.id.tv_leida})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_tianqi:
                changeBtn(1);
                break;
            case R.id.tv_weixing:
                changeBtn(2);
                break;
            case R.id.tv_leida:
                changeBtn(3);
                break;
        }
    }

    private void changeBtn(int num) {
        tvTianqi.setTextColor(Color.parseColor("#333333"));
        tvTianqi.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvWeixing.setTextColor(Color.parseColor("#333333"));
        tvWeixing.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvLeida.setTextColor(Color.parseColor("#333333"));
        tvLeida.setBackgroundColor(Color.parseColor("#FFFFFF"));
        switch (num) {
            case 1:
                tvTianqi.setTextColor(Color.parseColor("#FFFFFF"));
                tvTianqi.setBackgroundColor(Color.parseColor("#379AFF"));
//                mWebview.loadUrl(API.TIANQI_URL);
                break;
            case 2:
                tvWeixing.setTextColor(Color.parseColor("#FFFFFF"));
                tvWeixing.setBackgroundColor(Color.parseColor("#379AFF"));
//                mWebview.loadUrl(API.WEIXING_URL);
                break;
            case 3:
                tvLeida.setTextColor(Color.parseColor("#FFFFFF"));
                tvLeida.setBackgroundColor(Color.parseColor("#379AFF"));
//                mWebview.loadUrl(API.LEIDA_URL);
                break;
        }
        showFragment(num);
    }


    private TianQiFragment tianQiFragment;
    private WeiXingFragment weiXingFragment;
    private LeiDaFragment leiDaFragment;
    private FragmentManager fragmentManager;

    private void showFragment(int num) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        switch (num) {
            case 1:
                if (tianQiFragment == null) {
                    tianQiFragment = new TianQiFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, tianQiFragment, num + "T").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(tianQiFragment).commit();
                }
                break;
            case 2:
                if (weiXingFragment == null) {
                    weiXingFragment = new WeiXingFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, weiXingFragment, num + "T").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(weiXingFragment).commit();
                }
                break;
            case 3:
                if (leiDaFragment == null) {
                    leiDaFragment = new LeiDaFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, leiDaFragment, num + "T").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(leiDaFragment).commit();
                }
                break;
        }
    }

    private void hideFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment.getTag().contains("T")) {
                    getSupportFragmentManager().beginTransaction().hide(fragment).commit();
                }

            }
        }
    }

}
