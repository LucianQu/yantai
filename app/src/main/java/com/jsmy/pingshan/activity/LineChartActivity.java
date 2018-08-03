package com.jsmy.pingshan.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.LineChartBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.DateUtil;
import com.jsmy.pingshan.util.MyLog;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import lecho.lib.hellocharts.formatter.LineChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleLineChartValueFormatter;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class LineChartActivity extends BaseActivity {

    @BindView(R.id.tv_zhong)
    TextView tvZhong;
    @BindView(R.id.chart)
    LineChartView lineChart;

    private String rq;
    private Date date;
    private String stationId;
    private SimpleDateFormat format;

    private List<LineChartBean.DataBean.ListBean> list;


    private int totalDays;//总共有多少天的数据显示
    private float minY = 0;//Y轴坐标最小值
    private float maxY = 0;//Y轴坐标最大值
    private float visiable = 0;


    String[] labelsX;//X轴的标注
    float[] valuesY;//图表的数据点

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();


    private boolean hasAxesY = true; //是否需要Y坐标
    private String axesYName = "水位（m）";//Y坐标名称
    private String axesXName = "时间";


    private boolean hasLines = true;//是否要折线连接
    private boolean hasPoints = true;//数据点是否要标注
    private ValueShape shape = ValueShape.CIRCLE;//数据标注点形状,这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
    private boolean isFilled = false;//是否需要填充和X轴之间的空间
    private boolean isCubic = false;//曲线是否平滑，即是曲线还是折线
    private boolean hasLabels = true;//数据点是否显示数据值
    private boolean hasLabelForSelected = true;//点击数据坐标提示数据（设置了这个hasLabels(true);就无效）
    private boolean hasTiltedLabels = false;  //X坐标轴字体是斜的显示还是直的，true是斜的显示
    private String lineColor = "#FF0000";//折现颜色(#FF0000红色)
    private int textColor = Color.WHITE;//设置字体颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_line_chart;
    }

    @Override
    protected void initView() {
        date = new Date();
        format = new SimpleDateFormat("yyyy-MM-dd");
        rq = format.format(date);
        stationId = getIntent().getStringExtra("stationId");
        tvZhong.setText(rq);
    }

    @Override
    protected void initData() {
        NetWork.getLSSQlist(rq, stationId, this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_LSSQ_LIST:
                if ("Y".equals(code)) {
                    list = gson.fromJson(result, LineChartBean.class).getData().getList();
                    pushData();
                    getAxisXYLables();
                    getAxisPoints();
                    initLineChart();
                    if (visiable > 0) {
                        lineChart.setVisibility(View.VISIBLE);
                    } else {
                        lineChart.setVisibility(View.GONE);
                    }
                } else {
                    lineChart.setVisibility(View.GONE);
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.tv_qian, R.id.tv_zhong, R.id.tv_hou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_qian:
                getFrontDate();
                break;
            case R.id.tv_zhong:
                getDate();
                break;
            case R.id.tv_hou:
                getNextDate();
                break;
        }
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
                NetWork.getLSSQlist(rq, stationId, LineChartActivity.this);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void getFrontDate() {
        date = DateUtil.getFrontDate(date);
        rq = format.format(date);
        tvZhong.setText(rq);
        NetWork.getLSSQlist(rq, stationId, this);
    }

    private void getNextDate() {
        date = DateUtil.getNextDate(date);
        rq = format.format(date);
        tvZhong.setText(rq);
        NetWork.getLSSQlist(rq, stationId, this);
    }

    private void pushData() {
        totalDays = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (Float.parseFloat(list.get(i).getLevl()) > maxY) {
                maxY = Float.parseFloat(list.get(i).getLevl()) + 10;
            }
        }
        labelsX = new String[totalDays];
        for (int i = 0; i < list.size(); i++) {
            labelsX[i] = list.get(i).getLocalTm().substring(11, 16);
        }
        valuesY = new float[totalDays];
        for (int i = 0; i < list.size(); i++) {
            valuesY[i] = Float.parseFloat(list.get(i).getLevl());
            visiable = visiable + Float.parseFloat(list.get(i).getLevl());
        }

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        SimpleDateFormat format = new SimpleDateFormat("M-d");
//
//        labelsX[totalDays - 1] = "今天";
//        for (int i = 1; i < totalDays; i++) {
//            cal.add(Calendar.DATE, -1);
//            String str = format.format(cal.getTime());
//            MyLog.showLog("initData: ", str);
//            labelsX[totalDays - i - 1] = str;
//        }
//
//        for (int j = 0; j < totalDays; j++) {
//            Random random = new Random();
//            valuesY[j] = random.nextInt(10) + 5;
//        }

    }

    private void getAxisXYLables() {
        mAxisXValues.clear();
        for (int i = 0; i < labelsX.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(labelsX[i]));
        }
        mAxisYValues.clear();
        for (float i = minY; i <= maxY; i += 10) {
            mAxisYValues.add(new AxisValue(i).setLabel(i + ""));
        }
    }

    private void getAxisPoints() {
        mPointValues.clear();
        for (int i = 0; i < valuesY.length; i++) {
            mPointValues.add(new PointValue(i, valuesY[i]));
        }
        if (mPointValues.size() == 1) {
            PointValue pointValue = new PointValue(1f, valuesY[0]);
            mPointValues.add(pointValue);
            hasLines = false;
        } else {
            hasLines = true;
        }
    }

    private void initLineChart() {
        List<Line> lines = new ArrayList<Line>();
        Line line = new Line(mPointValues);
        LineChartValueFormatter chartValueFormatter = new SimpleLineChartValueFormatter(2);
        line.setFormatter(chartValueFormatter);
        line.setColor(Color.parseColor("#33B5E5"));  //折线的颜色
        line.setShape(shape);//折线图上每个数据点的形状
        line.setCubic(isCubic);//曲线是否平滑，即是曲线还是折线
        line.setFilled(isFilled);//是否填充曲线的面积
        line.setHasLabels(hasLabels);//曲线的数据坐标是否加上备注
//        line.setHasLabelsOnlyForSelected(hasLabelForSelected);//点击数据坐标提示数据
        line.setHasLines(hasLines);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(hasPoints);//是否显示圆点
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴X
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(hasTiltedLabels);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
//        axisX.setTextColor(textColor);  //设置字体颜色
        axisX.setHasLines(true);
        axisX.setTextSize(10);
        axisX.setName(axesXName);
        if (hasLines)
            axisX.setMaxLabelChars(6);
        axisX.setValues(mAxisXValues);
        data.setAxisXBottom(axisX);//x 轴在底部

        //坐标轴Y
        if (hasAxesY) {
            Axis axisY = new Axis();
            axisY.setHasLines(true);
            axisY.setTextSize(10);
            axisY.setName(axesYName);
            axisY.setHasTiltedLabels(true);
            axisY.setValues(mAxisYValues);
            data.setAxisYLeft(axisY);
        }

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        if (hasLines) {
            lineChart.setMaxZoom((float) (mAxisXValues.size() / 4));//最大方法比例
        } else {
            lineChart.setMaxZoom(7);//最大方法比例
        }
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setZoomEnabled(false);
        lineChart.setLineChartData(data);

        lineChart.setVisibility(View.VISIBLE);

        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.bottom = minY;
        v.top = maxY;
        //固定Y轴的范围,如果没有这个,Y轴的范围会根据数据的最大值和最小值决定,这不是我想要的
        lineChart.setMaximumViewport(v);

        //这2个属性的设置一定要在lineChart.setMaximumViewport(v);这个方法之后,不然显示的坐标数据是不能左右滑动查看更多数据的
        v.left = 0;
        if (hasLines) {
            v.right = 4;
        } else {
            v.right = list.size() / 10;
        }
        lineChart.setCurrentViewport(v);
    }

}
