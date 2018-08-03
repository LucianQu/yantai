package com.jsmy.pingshan.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.MyApplication;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.PersonBean;
import com.jsmy.pingshan.fragment.GongZuoTaiFragment;
import com.jsmy.pingshan.fragment.GongZuoTaiFragment2;
import com.jsmy.pingshan.fragment.PreFragment;
import com.jsmy.pingshan.fragment.SettingFragment;
import com.jsmy.pingshan.fragment.TongXunLuFragment;
import com.jsmy.pingshan.fragment.YuJingFragment;
import com.jsmy.pingshan.fragment.YuJingFragment2;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.new_moudle.NewYuqingActivity;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.SharePrefUtil;
import com.jsmy.pingshan.util.ToastUtil;
import com.test.demo.DemoActivity;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity {

    @BindView(R.id.img_yujing)
    ImageView imgYujing;
    @BindView(R.id.tv_yujing)
    TextView tvYujing;
    @BindView(R.id.img_work)
    ImageView imgWork;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.img_txl)
    ImageView imgTxl;
    @BindView(R.id.tv_txl)
    TextView tvTxl;
    @BindView(R.id.img_setting)
    ImageView imgSetting;
    @BindView(R.id.tv_setting)
    TextView tvSetting;

    private YuJingFragment yuJingFragment;
    private GongZuoTaiFragment2 gongZuoTaiFragment;
    private TongXunLuFragment tongXunLuFragment;
    private PreFragment preFragment;
    private SettingFragment settingFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        showFragment(1);
        changeBottomView(1);
    }

    @Override
    protected void initData() {
        NetWork.getUserList(SharePrefUtil.getString(this, Constant.SHARE_USERID, MyApplication.getMyApplication().bean.getId()), this);
        NetWork.getVideoList(this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_USER_LIST:
                if ("1".equals(code)) {
                    MyApplication.getMyApplication().listUser = gson.fromJson(result, PersonBean.class).getData().getList();
                } else {
//                    ToastUtil.showShort(this, msg);
                }
                break;
            case API.GET_VIDEO_LIST:
                if ("1".equals(code)) {
                    MyApplication.getMyApplication().video = result;
                } else {
//                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    public void turnToActivity(String number) {
        switch (number) {
            case "1":
                //雨晴
//                this.startActivity(new Intent(this, YuQingActivity.class));
                NewYuqingActivity.startActivity(this);
                break;
            case "2":
                this.startActivity(new Intent(this, ShuiQingActivity.class));
                break;
            case "3":
                this.startActivity(new Intent(this, YiDongXunChaActivity.class));
                break;
            case "4":
//                this.startActivity(new Intent(this, DingWeiTongJiActivity.class));
                new_startDingwei();
                break;
            case "5":
                this.startActivity(new Intent(this, QiXiangXinXiActivity.class));
                break;
            case "6":
//                this.startActivity(new Intent(this, YuAnGuanLiActivity.class));
                startYuAn();
                break;
            case "7":
                this.startActivity(new Intent(this, ConversationListActivity.class));
                break;
            case "8":
                if (MyApplication.getMyApplication().video != null && !"".equals(MyApplication.getMyApplication().video)) {
                    Intent intent = new Intent(this, DemoActivity.class);
                    intent.putExtra("video", MyApplication.getMyApplication().video);
                    this.startActivity(intent);
                } else {
                    NetWork.getVideoList(this);
                    ToastUtil.showShort(this, "视频列表为空，请检查网络状态后重试！");
                }
                break;
            case "9":
                this.startActivity(new Intent(this, ShiPinHuiShangActivity.class));
                break;
            case "10":
                this.startActivity(new Intent(this, XunChaMingLingActivity.class));
                break;
        }
    }
    public void new_startDingwei(){
        if(MyApplication.isCity==1){
            PreActivity.startPreActivity(this,PreActivity.FROM_DINGWEI);
        }else {
            this.startActivity(new Intent(this,DingWeiTongJiActivity.class));
        }
    }
    public void startYuAn(){
        if(MyApplication.isCity==1){
            PreActivity.startPreActivity(this,PreActivity.FROM_YUAN);
        }else {
            this.startActivity(new Intent(this,YuAnGuanLiActivity.class));
        }
    }

    public Drawable getGZTdrawable(String number) {
        Drawable drawable = null;
        switch (number) {
            case "1":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_yuqing);
                break;
            case "2":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_shuiqing);
            break;
            case "3":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_yidongxuncha);
            break;
            case "4":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_dingweitongji);
            break;
            case "5":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_qixiangxinxi);
            break;
            case "6":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_yuanguanli);
            break;
            case "7":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_yidongbangong);
            break;
            case "8":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_shipinjiankong);
            break;
            case "9":
                drawable = getResources().getDrawable(R.mipmap.gongzuotai_shipinhuishang);
            break;
            case "10":
                drawable = getResources().getDrawable(R.mipmap.tongxunlu1);
            break;
            case "11":
                drawable=getResources().getDrawable(R.mipmap.shangqing);
                break;
        }
        return drawable;
    }

    private void showFragment(int num) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        switch (num) {
            case 1:
                if (yuJingFragment == null) {
                    yuJingFragment = new YuJingFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, yuJingFragment, num + "M").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(yuJingFragment).commit();
                }
                break;
            case 2:
                if (gongZuoTaiFragment == null) {
                    gongZuoTaiFragment = new GongZuoTaiFragment2();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, gongZuoTaiFragment, num + "M").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(gongZuoTaiFragment).commit();
                }
                break;
            case 3:
//                if (tongXunLuFragment == null) {
//                    tongXunLuFragment = new TongXunLuFragment();
//                    fragmentManager.beginTransaction().add(R.id.fragment_view, tongXunLuFragment, num + "M").commit();
//                } else {
//                    hideFragment();
//                    fragmentManager.beginTransaction().show(tongXunLuFragment).commit();
//                }
                if (preFragment == null) {
                    preFragment = new PreFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, preFragment, num + "M").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(preFragment).commit();
                }
                break;
            case 4:
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_view, settingFragment, num + "M").commit();
                } else {
                    hideFragment();
                    fragmentManager.beginTransaction().show(settingFragment).commit();
                }
                break;
            default:
                break;
        }
    }

    private void hideFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment.getTag().contains("M")) {
                    getSupportFragmentManager().beginTransaction().hide(fragment).commit();
                }
            }
        }
    }

    @OnClick({R.id.linear_yujing, R.id.linear_work, R.id.linear_txl, R.id.linear_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_yujing:
                changeBottomView(1);
                break;
            case R.id.linear_work:
                changeBottomView(2);
                break;
            case R.id.linear_txl:
                changeBottomView(3);
                break;
            case R.id.linear_setting:
                changeBottomView(4);
                break;
            default:
                break;
        }
    }

    public void changeBottomView(int num) {
        imgYujing.setImageResource(R.mipmap.yujingxiangying_yujingxiangying_moren);
        tvYujing.setTextColor(Color.parseColor("#333333"));
        imgWork.setImageResource(R.mipmap.caidanlan_gongzuotai_moren);
        tvWork.setTextColor(Color.parseColor("#333333"));
        imgTxl.setImageResource(R.mipmap.yonghuming);
        tvTxl.setTextColor(Color.parseColor("#333333"));
        imgSetting.setImageResource(R.mipmap.shezhi1);
        tvSetting.setTextColor(Color.parseColor("#333333"));
        switch (num) {
            case 1:
                imgYujing.setImageResource(R.mipmap.yujingxiangying_yujingxiangying_xuanzhong);
                tvYujing.setTextColor(Color.parseColor("#379AFF"));
                break;
            case 2:
                imgWork.setImageResource(R.mipmap.caidanlan_gongzuotai_xuanzhong);
                tvWork.setTextColor(Color.parseColor("#379AFF"));
                break;
            case 3:
                imgTxl.setImageResource(R.mipmap.dingweitongji_zhihuirenyuan);
                tvTxl.setTextColor(Color.parseColor("#379AFF"));
                break;
            case 4:
                imgSetting.setImageResource(R.mipmap.shezhi2);
                tvSetting.setTextColor(Color.parseColor("#379AFF"));
                break;
            default:
                break;
        }
        showFragment(num);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivityPermissionsDispatcher.baiduMapNeedPermissionsWithPermissionCheck(MainActivity.this);
    }

    //百度地图权限
    //需要的权限
    @NeedsPermission({Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void baiduMapNeedPermissions() {

    }

    //告知用户为什么需要权限
    @OnShowRationale({Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void permissionsDatail(PermissionRequest request) {
        showRationaleDialog("使用地图功能必须打开相关权限", request);
    }

    //用户拒绝
    @OnPermissionDenied({Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void userDenied() {
        AskForPermission();
    }

    //用户选择不再询问
    @OnNeverAskAgain({Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void neverAskAgain() {
        AskForPermission();
    }

    /**
     * 告知用户具体需要权限的原因
     *
     * @param messageResId
     * @param request
     */
    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();//请求权限
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    /**
     * 被拒绝并且不再提醒,提示用户去设置界面重新打开权限
     */
    private void AskForPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("当前应用缺少定位相关权限,请去设置界面打开\n打开之后按两次返回键可回到该应用哦");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}
