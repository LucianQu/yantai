package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.QiXiangXinXiActivity;
import com.jsmy.pingshan.bean.ForecastBean;
import com.jsmy.pingshan.bean.ForecastInfo;
import com.jsmy.pingshan.util.Constant;
import com.jsmy.pingshan.util.MyLog;

import java.util.List;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/11/4.
 */

public class TianQiFragmentAdapter extends RecyclerView.Adapter<TianQiFragmentAdapter.TianQiFragmentHolder> {
    private QiXiangXinXiActivity context;
    private List<ForecastInfo> list;
    private LayoutInflater inflater;

    public TianQiFragmentAdapter(QiXiangXinXiActivity context, List<ForecastInfo> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TianQiFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_tianqi_item, null);
        TianQiFragmentHolder holder = new TianQiFragmentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TianQiFragmentHolder holder, int position) {
        MyLog.showLog("TTT", list.get(position).getWind() + " - " + list.get(position).getSkycon());
        holder.tvTitle.setText(list.get(position).getDate());
        holder.tvTemp.setText(list.get(position).getTemper());
        holder.tvFeng.setText(list.get(position).getWind());
        switch (list.get(position).getSkycon()) {
            case Constant.CLEAR_DAY:
                holder.tvSkycon.setText("晴天");
                holder.imgTianqi.setImageResource(R.mipmap.qing1);
                break;
            case Constant.CLEAR_NIGHT:
                holder.tvSkycon.setText("晴夜");
                holder.imgTianqi.setImageResource(R.mipmap.ye1);
                break;
            case Constant.PARTLY_CLOUDY_DAY:
                holder.tvSkycon.setText("多云");
                holder.imgTianqi.setImageResource(R.mipmap.yun2);
                break;
            case Constant.PARTLY_CLOUDY_NIGHT:
                holder.tvSkycon.setText("多云");
                holder.imgTianqi.setImageResource(R.mipmap.yun2);
                break;
            case Constant.CLOUDY:
                holder.tvSkycon.setText("阴");
                holder.imgTianqi.setImageResource(R.mipmap.qing2);
                break;
            case Constant.RAIN:
                holder.tvSkycon.setText("降雨");
                holder.imgTianqi.setImageResource(R.mipmap.yu3);
                break;
            case Constant.SNOW:
                holder.tvSkycon.setText("降雪");
                holder.imgTianqi.setImageResource(R.mipmap.xue3);
                break;
            case Constant.WIND:
                holder.tvSkycon.setText("刮风");
                holder.imgTianqi.setImageResource(R.mipmap.feng1);
                break;
            case Constant.FOG:
                holder.tvSkycon.setText("降雾");
                holder.imgTianqi.setImageResource(R.mipmap.feng1);
                break;
            case Constant.HAZE:
                holder.tvSkycon.setText("雾霾");
                holder.imgTianqi.setImageResource(R.mipmap.feng1);
                break;
            case Constant.SLEET:
                holder.tvSkycon.setText("冻雨");
                holder.imgTianqi.setImageResource(R.mipmap.yu3);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class TianQiFragmentHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView imgTianqi;
        private TextView tvTemp;
        private TextView tvSkycon;
        private TextView tvFeng;

        public TianQiFragmentHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            imgTianqi = (ImageView) itemView.findViewById(R.id.img_tianqi);
            tvTemp = (TextView) itemView.findViewById(R.id.tv_temp);
            tvSkycon = (TextView) itemView.findViewById(R.id.tv_skycon);
            tvFeng = (TextView) itemView.findViewById(R.id.tv_feng);
        }
    }
}
