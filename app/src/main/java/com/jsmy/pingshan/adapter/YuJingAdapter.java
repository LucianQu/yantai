package com.jsmy.pingshan.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.bean.YuJingBean;
import com.jsmy.pingshan.view.MyExpandableListView;

import java.util.List;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

/**
 * Created by Administrator on 2017/10/12.
 */

public class YuJingAdapter extends RecyclerAdapter<YuJingBean.DataBean> {
    private MainActivity context;

    public YuJingAdapter(MainActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<YuJingBean.DataBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new YuJingHolder(parent);
    }

    private TextView tvTitle;
    private MyExpandableListView myExpand;
    private YuJingExpandAdapter adapter;
    private List<YuJingBean.DataBean.ListBeanX> list;

    class YuJingHolder extends BaseViewHolder<YuJingBean.DataBean> {
        public YuJingHolder(ViewGroup parent) {
            super(parent, R.layout.fragment_yujing_item);
        }

        @Override
        public void onInitializeView() {
            super.onInitializeView();
            tvTitle = (TextView) findViewById(R.id.tv_title);
            myExpand = (MyExpandableListView) findViewById(R.id.my_expand);
        }

        @Override
        public void setData(YuJingBean.DataBean data) {
            super.setData(data);
            tvTitle.setText(data.getADNM());
            list = data.getList();
            adapter = new YuJingExpandAdapter(context, list,data.getZid());
            myExpand.setGroupIndicator(null);
            myExpand.setAdapter(adapter);
        }

    }

}
