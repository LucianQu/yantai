package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.bean.NewYuqingBean;

import java.util.List;

public class New_YuqingAdapter extends RecyclerView.Adapter<New_YuqingAdapter.MViewHolder> {

    List<NewYuqingBean.DataBean> list;
    Context context;

    public New_YuqingAdapter( Context context,List<NewYuqingBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MViewHolder(LayoutInflater.from(context).inflate(R.layout.item_yuqing,parent,false));
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_benci.setText(list.get(position).getRainlast());
        holder.tv_niandu.setText(list.get(position).getRainyear());
        holder.tv_qunian.setText(list.get(position).getRainafteryear());
        holder.tv_nianli.setText(list.get(position).getRainallyear());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_benci;
        private TextView tv_niandu;
        private TextView tv_qunian;
        private TextView tv_nianli;

       public MViewHolder(View itemView) {
           super(itemView);
           tv_name=(TextView) itemView.findViewById(R.id.tv_name);
           tv_benci=(TextView) itemView.findViewById(R.id.tv_benci);
           tv_niandu=(TextView) itemView.findViewById(R.id.tv_niandu);
           tv_qunian=(TextView) itemView.findViewById(R.id.tv_qunian);
           tv_nianli=(TextView) itemView.findViewById(R.id.tv_nianli);
       }
   }
}
