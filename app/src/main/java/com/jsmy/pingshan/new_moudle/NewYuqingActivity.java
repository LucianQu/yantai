package com.jsmy.pingshan.new_moudle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.BaseActivity;
import com.jsmy.pingshan.adapter.New_YuqingAdapter;
import com.jsmy.pingshan.adapter.PreAdapter;
import com.jsmy.pingshan.bean.NewYuqingBean;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewYuqingActivity extends BaseActivity {

    List<NewYuqingBean.DataBean> list;
    @BindView(R.id.recyc_reciv)
    RecyclerView recycReciv;
    New_YuqingAdapter adapter;
    private String toady;//今天
    private Date dst;//默认开始时间
    private Date det;//默认结束时间
    private String dst_time=" 08:00";
    private String det_time=" 18:00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_new_yuqing;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        NetWork.getYuqingList("", "2018-07-10-08:00", "2018-07-12-08:00", this);
    }
    void inintDate(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
        toady=df.format(day);

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("1".equals(code)) {
            if (gson.fromJson(result, NewYuqingBean.class).getData().size() > 0) {
                list = gson.fromJson(result, NewYuqingBean.class).getData();
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycReciv.setLayoutManager(layoutManager);
                recycReciv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                adapter = new New_YuqingAdapter( this,list);
                recycReciv.setAdapter(adapter);
            }
        } else {
            ToastUtil.showShort(this, msg);
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NewYuqingActivity.class);
        context.startActivity(intent);

    }

    @OnClick({R.id.bt_back, R.id.bt_cezhan, R.id.bt_time_select, R.id.tv_1dady, R.id.tv_3day, R.id.tv_7day, R.id.tv_30day})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                break;
            case R.id.bt_cezhan:
                break;
            case R.id.bt_time_select:
                showPopwindow();
                break;
            case R.id.tv_1dady:
                break;
            case R.id.tv_3day:
                break;
            case R.id.tv_7day:
                break;
            case R.id.tv_30day:
                break;
        }
    }
    //获取当前系统前一天日期
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
    //获取当前系统前一天日期
    public static Date getTodayDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        date = calendar.getTime();
        return date;
    }
    /*******************************************************/

    PopupWindow popupWindow;
    View popView;
    public  void showPopwindow(){
        popView = LayoutInflater.from(this).inflate(R.layout.popwindow_time,null,false);
        ((TextView) popView.findViewById(R.id.tv_sday)).setText("");
        ((TextView) popView.findViewById(R.id.tv_stime)).setText("");
        ((TextView) popView.findViewById(R.id.tv_eday)).setText("");
        ((TextView) popView.findViewById(R.id.tv_etime)).setText("");
        ((TextView) popView.findViewById(R.id.tv_stime)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ((TextView) popView.findViewById(R.id.tv_sday)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ((TextView) popView.findViewById(R.id.tv_etime)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ((TextView) popView.findViewById(R.id.tv_eday)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ((TextView) popView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        ((TextView) popView.findViewById(R.id.tv_query)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        popupWindow=new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        backgroundAlpha(0.7f);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER,0,0);


    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }
    void time(){
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013,0,1);
        endDate.set(2020,11,31);

        TimePickerView pvTime = new TimePickerBuilder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date,View v) {//选中事件回调
//                tvTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setCancelText("Cancel")//取消按钮文字
                .setSubmitText("Sure")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("Title")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();
    }
}
