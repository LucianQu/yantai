package com.jsmy.pingshan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.YuAnDataActivity;
import com.jsmy.pingshan.bean.YuAnZDataBean;
import com.jsmy.pingshan.model.API;
import com.jsmy.pingshan.util.OfficeUtil;
import com.jsmy.pingshan.util.ToastUtil;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class YuAnDataAdapter extends RecyclerView.Adapter<YuAnDataAdapter.YuAnDataHolder> {
    private YuAnDataActivity context;
    private List<YuAnZDataBean.DataBean> list;

    public YuAnDataAdapter(YuAnDataActivity context, List<YuAnZDataBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public YuAnDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_yu_an_data_item, null);
        return new YuAnDataHolder(view);
    }

    @Override
    public void onBindViewHolder(YuAnDataHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvData.setText(list.get(position).getArea());
        final String url = list.get(position).getDocUrl() + list.get(position).getFileSaveName();
        if (!"".equals(url)) {
            File docFile = new File(API.SAVA_DOC_PATH, url.substring(url.lastIndexOf("/") + 1));
            if (!docFile.exists()) {
                holder.tvDowload.setText("预案文件下载");
                holder.tvDowload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        context.showProgressDialog(url);
//                        context.dowLoadFile(url);
                        context.verifyStoragePermissions(url);
                    }
                });
            } else {
                holder.tvDowload.setText("预案文件预览");
                holder.tvDowload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(OfficeUtil.getFileIntent(context, API.SAVA_DOC_PATH + "/" + url.substring(url.lastIndexOf("/") + 1)));
                    }
                });
            }
        } else {
            holder.tvDowload.setText("预案文件缺失");
            holder.tvDowload.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class YuAnDataHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvData;
        private TextView tvDowload;

        public YuAnDataHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvDowload = (TextView) itemView.findViewById(R.id.tv_dowload);
        }
    }
}
