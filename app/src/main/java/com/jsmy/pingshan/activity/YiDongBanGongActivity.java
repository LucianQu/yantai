package com.jsmy.pingshan.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.fragment.DuanXinQunFaFragment;
import com.jsmy.pingshan.fragment.HuiHuaGouTongFragment;
import com.jsmy.pingshan.fragment.YuYinDianHuaFragment;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YiDongBanGongActivity extends BaseActivity {

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

    private HuiHuaGouTongFragment huiHuaGouTongFragment;
    private DuanXinQunFaFragment duanXinQunFaFragment;
    private YuYinDianHuaFragment yuYinDianHuaFragment;
    private FragmentManager fragmentManager;

    private List<String> list;
    private String[] items;
    private boolean initChoiceSets[];
    public List<String> choices;
    private String [] yourChoices;
    private String personName = "name";

    private int fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_yi_dong_ban_gong;
    }

    @Override
    protected void initView() {
        chekcTopBtn(1);
        list = new ArrayList<>();
        list.add("张一一");
        list.add("张一二");
        list.add("张一三");
        list.add("张一四");
        list.add("张一五");
        list.add("张一六");
        list.add("张一七");
        list.add("张一八");
        list.add("张一九");
        list.add("张一十");
        list.add("张二一");
        list.add("张二二");
        list.add("张二三");
        list.add("张二四");
        list.add("张二五");
        list.add("张二六");
        list.add("张二七");
        list.add("张二八");
        list.add("张二九");
        list.add("张二十");
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

    @OnClick({R.id.rela_zhihui, R.id.rela_qiangxian, R.id.rela_kucun, R.id.img_back, R.id.tv_check})
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
            case R.id.tv_check:
                ToastUtil.showShort(this, "未连接网络数据......");
                changeInput();
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
        showFragment(num);
    }

    private void showFragment(int num) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        switch (num) {
            case 1:
                if (huiHuaGouTongFragment == null) {
                    huiHuaGouTongFragment = new HuiHuaGouTongFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, huiHuaGouTongFragment, num + "Y").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(huiHuaGouTongFragment).commit();
                }
                break;
            case 2:
                if (duanXinQunFaFragment == null) {
                    duanXinQunFaFragment = new DuanXinQunFaFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, duanXinQunFaFragment, num + "Y").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(duanXinQunFaFragment).commit();
                }
                break;
            case 3:
                if (yuYinDianHuaFragment == null) {
                    yuYinDianHuaFragment = new YuYinDianHuaFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, yuYinDianHuaFragment, num + "Y").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(yuYinDianHuaFragment).commit();
                }
                break;
        }
        fragment = num;
    }

    private void hideFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment.getTag().contains("Y")) {
                    getSupportFragmentManager().beginTransaction().hide(fragment).commit();
                }

            }
        }
    }

    private void changeInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        switch (fragment) {
            case 1:
                imm.hideSoftInputFromWindow(huiHuaGouTongFragment.editNr.getWindowToken(), 0);
                break;
            case 2:
                imm.hideSoftInputFromWindow(duanXinQunFaFragment.editNr.getWindowToken(), 0);
                break;
            case 3:
                imm.hideSoftInputFromWindow(yuYinDianHuaFragment.editNr.getWindowToken(), 0);
                break;
        }

    }

    public void showMultiChoiceDialog(final int num) {
        //设置每个选项的名称
        items = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i] = list.get(i);
        }
        // 设置默认选中的选项，全为false默认均未选中
        initChoiceSets = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            initChoiceSets[i] = false;
        }
        //选择/取消 改变值
        yourChoices = new String[list.size()];
        for (int i = 0; i < items.length; i++) {
            yourChoices[i] = personName;
        }
        //最终确定选择的值
        choices = new ArrayList<>();
        AlertDialog.Builder multiChoiceDialog = new AlertDialog.Builder(YiDongBanGongActivity.this);
        multiChoiceDialog.setTitle("请选择要通信的人");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
//                    yourChoices.add(which, list.get(which));
                    yourChoices[which] = list.get(which);
                } else {
//                    yourChoices.add(which, personName);
                    yourChoices[which] = personName;
                }
            }

        });
        multiChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < yourChoices.length; i++) {
                    if (!yourChoices[i].equals(personName)) {
                        choices.add(yourChoices[i]);
                    }
                }
                switch (num) {
                    case 1:
                        huiHuaGouTongFragment.pushData();
                        break;
                    case 2:
                        duanXinQunFaFragment.pushData();
                        break;
                    case 3:
                        yuYinDianHuaFragment.pushData();
                        break;
                }
//                ToastUtil.showShort(YiDongBanGongActivity.this, "你选中了" + str);
            }
        });
        multiChoiceDialog.show();
    }

}
