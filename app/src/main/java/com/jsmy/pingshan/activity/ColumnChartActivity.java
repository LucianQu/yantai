package com.jsmy.pingshan.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.ColumnChartBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.DateUtil;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class ColumnChartActivity extends BaseActivity {

    @BindView(R.id.tv_zhong)
    TextView tvZhong;
    @BindView(R.id.chart)
    ColumnChartView chart;
    private ColumnChartData data;

    private String rq;
    private Date date;
    private String stationId;
    private SimpleDateFormat format;
    public final static String[] week = new String[]{"9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
            "23", "24", "1", "2", "3", "4", "5", "6", "7", "8"};

    private List<Float> list = new ArrayList<>();

    private List<ColumnChartBean.DataBean.ListBean> listBeen;

    private float maxY = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_column_chart;
    }

    @Override
    protected void initView() {
        chart = (ColumnChartView) findViewById(R.id.chart);
        chart.setZoomEnabled(false);//禁止手势缩放
        date = new Date();
        format = new SimpleDateFormat("yyyy-MM-dd");
        rq = format.format(date);
        stationId = getIntent().getStringExtra("stationId");
        tvZhong.setText(rq);
    }

    @Override
    protected void initData() {

        NetWork.getLSYQlist(rq, stationId, this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_YQLS_LIST:
                if ("Y".equals(code)) {
                    listBeen = gson.fromJson(result, ColumnChartBean.class).getData().getList();
                    pushData();
                    setFirstChart();
                } else {
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_qian, R.id.tv_hou, R.id.tv_zhong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_qian:
                getFrontDate();
                break;
            case R.id.tv_hou:
                getNextDate();
                break;
            case R.id.tv_zhong:
                getDate();
                break;
        }
    }

    private void getFrontDate() {
        date = DateUtil.getFrontDate(date);
        rq = format.format(date);
        tvZhong.setText(rq);
        NetWork.getLSYQlist(rq, stationId, this);
    }

    private void getNextDate() {
        date = DateUtil.getNextDate(date);
        rq = format.format(date);
        tvZhong.setText(rq);
        NetWork.getLSYQlist(rq, stationId, this);
    }

    private void getDate() {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(rq.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(rq.substring(5, 7)) - 1);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(rq.substring(8)));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                date = c.getTime();
                rq = DateFormat.format("yyyy-MM-dd", c).toString();
                tvZhong.setText(rq);
                NetWork.getLSYQlist(rq, stationId, ColumnChartActivity.this);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void pushData() {
        ColumnChartBean.DataBean.ListBean bean = listBeen.get(0);
        list.clear();
        //9
        if ("".equals(bean.getH9())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH9()));
        }
        //10
        if ("".equals(bean.getH10())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH10()));
        }
        //11
        if ("".equals(bean.getH11())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH11()));
        }
        //12
        if ("".equals(bean.getH12())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH12()));
        }
        //13
        if ("".equals(bean.getH13())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH13()));
        }
        //14
        if ("".equals(bean.getH14())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH14()));
        }
        //15
        if ("".equals(bean.getH15())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH15()));
        }
        //16
        if ("".equals(bean.getH16())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH16()));
        }
        //17
        if ("".equals(bean.getH17())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH17()));
        }
        //18
        if ("".equals(bean.getH18())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH18()));
        }
        //19
        if ("".equals(bean.getH19())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH19()));
        }
        //20
        if ("".equals(bean.getH20())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH20()));
        }
        //21
        if ("".equals(bean.getH21())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH21()));
        }
        //22
        if ("".equals(bean.getH22())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH22()));
        }
        //23
        if ("".equals(bean.getH23())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH23()));
        }
        //24
        if ("".equals(bean.getH0())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH0()));
        }
        //1
        if ("".equals(bean.getH1())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH1()));
        }
        //2
        if ("".equals(bean.getH2())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH2()));
        }
        //3
        if ("".equals(bean.getH3())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH3()));
        }
        //4
        if ("".equals(bean.getH4())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH4()));
        }
        //5
        if ("".equals(bean.getH5())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH5()));
        }
        //6
        if ("".equals(bean.getH6())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH6()));
        }
        //7
        if ("".equals(bean.getH7())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH7()));
        }
        //8
        if ("".equals(bean.getH8())) {
            list.add(0f);
        } else {
            list.add(Float.parseFloat(bean.getH8()));
        }

        for (int i = 0; i < list.size(); i++) {
            if (maxY < list.get(i)) {
                maxY = list.get(i);
            }
        }


    }

    private void setFirstChart() {
        // 使用的 7列，每列1个subcolumn。
        int numSubcolumns = 1;
        int numColumns = week.length;
        //定义一个圆柱对象集合
        List<Column> columns = new ArrayList<Column>();
        //子列数据集合
        List<SubcolumnValue> values;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        //遍历列数numColumns
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            //遍历每一列的每一个子列
            for (int j = 0; j < numSubcolumns; ++j) {
                //为每一柱图添加颜色和数值
                float f = list.get(i).floatValue();
                MyLog.showLog("column", "-- " + list.get(i).floatValue() + " --" + f);
                values.add(new SubcolumnValue(f, Color.parseColor("#33B5E5")));
            }
            //创建Column对象
            Column column = new Column(values);
            //这一步是能让圆柱标注数据显示带小数的重要一步 让我找了好久问题
            //作者回答https://github.com/lecho/hellocharts-android/issues/185
            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(1);
            column.setFormatter(chartValueFormatter);
            //是否有数据标注
            column.setHasLabels(true);
            //是否是点击圆柱才显示数据标注
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            //给x轴坐标设置描述
            axisValues.add(new AxisValue(i).setLabel(week[i]));
        }
        //创建一个带有之前圆柱对象column集合的ColumnChartData
        data = new ColumnChartData(columns);

        //定义x轴y轴相应参数
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
//        axisY.setName("出场率(%)");//轴名称
        axisY.hasLines();//是否显示网格线
//        axisY.setTextColor(R.color.new_black);//颜色

        axisX.setName("8时到8时的逐小时雨量");
        axisX.hasLines();
//        axisX.setTextColor(R.color.new_black);
        axisX.setValues(axisValues);
        //把X轴Y轴数据设置到ColumnChartData 对象中
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //给表填充数据，显示出来
        chart.setColumnChartData(data);
        Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = maxY + 10;
        chart.setMaximumViewport(v);
        v.left = -1;
        v.right = 11;
        chart.setCurrentViewport(v);
    }

}
