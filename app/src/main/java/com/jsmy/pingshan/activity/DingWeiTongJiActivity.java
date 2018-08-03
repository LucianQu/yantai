package com.jsmy.pingshan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.fragment.KuCunFragment;
import com.jsmy.pingshan.fragment.KuCunFragment2;
import com.jsmy.pingshan.fragment.QiangXianFragment;
import com.jsmy.pingshan.fragment.QiangXianFragment2;
import com.jsmy.pingshan.fragment.ZhiHuiFragment;
import com.jsmy.pingshan.fragment.ZhiHuiFragment2;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DingWeiTongJiActivity extends BaseActivity {

    @BindView(R.id.tv_zhihui)
    TextView tvZhihui;
    @BindView(R.id.img_zhihui)
    ImageView imgZhihui;
    @BindView(R.id.tv_qiangxian)
    TextView tvQiangxian;
    @BindView(R.id.img_qiangxian)
    ImageView imgQiangxian;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.img_kucun)
    ImageView imgKucun;
    @BindView(R.id.fragment_view)
    FrameLayout fragmentView;

    private ZhiHuiFragment2 zhiHuiFragment;
    private QiangXianFragment2 qiangXianFragment;
    private KuCunFragment2 kuCunFragment;
    private FragmentManager fragmentManager;
    private int fragment;

    private static  String ID_KEY="xid_key" ;
    private  int xid=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_ding_wei_tong_ji;
    }

    @Override
    protected void initView() {
        chekcTopBtn(1);
    }

    @Override
    protected void initData() {
        if(-1!=getIntent().getIntExtra(ID_KEY,-1)){//如果是市级前置界面进来会传入xid

        }else {//乡镇级 没有传xid

        }
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.rela_zhihui, R.id.rela_qiangxian, R.id.rela_kucun, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rela_zhihui:
                chekcTopBtn(1);
                break;
            case R.id.rela_qiangxian:
                chekcTopBtn(2);
                break;
            case R.id.rela_kucun:
                chekcTopBtn(3);
                break;
        }
    }

    private void chekcTopBtn(int num) {
        switch (num) {
            case 1:
                tvZhihui.setTextColor(Color.parseColor("#379AFF"));
                imgZhihui.setVisibility(View.VISIBLE);
                tvQiangxian.setTextColor(Color.parseColor("#333333"));
                imgQiangxian.setVisibility(View.GONE);
                tvKucun.setTextColor(Color.parseColor("#333333"));
                imgKucun.setVisibility(View.GONE);
                break;
            case 2:
                tvZhihui.setTextColor(Color.parseColor("#333333"));
                imgZhihui.setVisibility(View.GONE);
                tvQiangxian.setTextColor(Color.parseColor("#379AFF"));
                imgQiangxian.setVisibility(View.VISIBLE);
                tvKucun.setTextColor(Color.parseColor("#333333"));
                imgKucun.setVisibility(View.GONE);
                break;
            case 3:
                tvZhihui.setTextColor(Color.parseColor("#333333"));
                imgZhihui.setVisibility(View.GONE);
                tvQiangxian.setTextColor(Color.parseColor("#333333"));
                imgQiangxian.setVisibility(View.GONE);
                tvKucun.setTextColor(Color.parseColor("#379AFF"));
                imgKucun.setVisibility(View.VISIBLE);
                break;
        }
        fragment = num;
        showFragment(num);
    }

    private void showFragment(int num) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        switch (num) {
            case 1:
//                if (zhiHuiFragment == null) {
//                    zhiHuiFragment = new ZhiHuiFragment();
//                    fragmentManager.beginTransaction().add(R.id.fragment_view, zhiHuiFragment, num + "D").commit();
//                } else {
//                    hideFragment();
//                    fragmentManager.beginTransaction().show(zhiHuiFragment).commit();
//                }
                if (zhiHuiFragment == null) {
                    zhiHuiFragment = new ZhiHuiFragment2();
                }
                removeFragment();
                fragmentManager.beginTransaction().add(R.id.fragment_view, zhiHuiFragment, num + "D").commit();
                break;
            case 2:
//                if (qiangXianFragment == null) {
//                    qiangXianFragment = new QiangXianFragment();
//                    fragmentManager.beginTransaction().add(R.id.fragment_view, qiangXianFragment, num + "D").commit();
//                } else {
//                    hideFragment();
//                    fragmentManager.beginTransaction().show(qiangXianFragment).commit();
//                }
                if (qiangXianFragment == null) {
                    qiangXianFragment = new QiangXianFragment2();
                }
                removeFragment();
                fragmentManager.beginTransaction().add(R.id.fragment_view, qiangXianFragment, num + "D").commit();
                break;
            case 3:
//                if (kuCunFragment == null) {
//                    kuCunFragment = new KuCunFragment();
//                    fragmentManager.beginTransaction().add(R.id.fragment_view, kuCunFragment, num + "D").commit();
//                } else {
//                    hideFragment();
//                    fragmentManager.beginTransaction().show(kuCunFragment).commit();
//                }
                if (kuCunFragment == null) {
                    kuCunFragment = new KuCunFragment2();
                }
                removeFragment();
                fragmentManager.beginTransaction().add(R.id.fragment_view, kuCunFragment, num + "D").commit();
                break;
        }
    }

    private void hideFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment.getTag().contains("D")) {
                    getSupportFragmentManager().beginTransaction().hide(fragment).commit();
                }

            }
        }
    }

    private void removeFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (null != fragment && null != fragment.getTag() && fragment.getTag().contains("D")) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        switch (fragment) {
            case 1:
                if (zhiHuiFragment != null)
                    zhiHuiFragment.getData();
                break;
            case 2:
                if (qiangXianFragment != null)
                    qiangXianFragment.getData();
                break;
            case 3:
                if (kuCunFragment != null)
                    kuCunFragment.getData();
                break;
        }
    }
    public static void startDingweiActivity(Context context, int xid) {
        Intent intent = new Intent(context, DingWeiTongJiActivity.class);
        intent.putExtra(ID_KEY, xid);
        context.startActivity(intent);

    }
}
