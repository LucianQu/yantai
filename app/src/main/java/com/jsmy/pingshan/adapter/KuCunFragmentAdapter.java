package com.jsmy.pingshan.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.DingWeiTongJiActivity;
import com.jsmy.pingshan.activity.KuCunBianJiActivity;
import com.jsmy.pingshan.activity.QiangXianBianJiActivity;
import com.jsmy.pingshan.bean.KuCunFramentBean;
import com.jsmy.pingshan.bean.QiangianFramentBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class KuCunFragmentAdapter extends BaseExpandableListAdapter {

    private DingWeiTongJiActivity context;
    private List<KuCunFramentBean.DataBean.ListBeanX> list;
    private LayoutInflater inflater;

    public KuCunFragmentAdapter(DingWeiTongJiActivity context, List<KuCunFramentBean.DataBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    class GroupHolder{
        private TextView tvName;
        private TextView tvYi;
        private TextView tvXie;
        private TextView tvTong;
        private TextView tvChuang;
        private ImageView imgArrow;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder group = null;
        if (view == null){
            group = new GroupHolder();
            view = inflater.inflate(R.layout.fragment_ku_cun_group,null);
            group.tvName = (TextView) view.findViewById(R.id.tv_name);
            group.tvYi = (TextView) view.findViewById(R.id.tv_yi);
            group.tvXie = (TextView) view.findViewById(R.id.tv_xie);
            group.tvTong = (TextView) view.findViewById(R.id.tv_tong);
            group.tvChuang = (TextView) view.findViewById(R.id.tv_chuang);
            group.imgArrow = (ImageView) view.findViewById(R.id.img_arrow);
            view.setTag(group);
        }else {
            group = (GroupHolder) view.getTag();
        }
        if (b) {
            group.imgArrow.setImageResource(R.mipmap.yujingxiangying_shangjiantou);
        } else {
            group.imgArrow.setImageResource(R.mipmap.yujingxiangying_xiajiantou);
        }
        group.tvName.setText(list.get(i).getZmc());
        group.tvYi.setText(list.get(i).getYy());
        group.tvXie.setText(list.get(i).getYx());
        group.tvTong.setText(list.get(i).getSdt());
        group.tvChuang.setText(list.get(i).getTc());
        return view;
    }

    class ChildHolder{
        private TextView tvName;
        private TextView tvYi;
        private TextView tvXie;
        private TextView tvTong;
        private TextView tvChuang;
        private ImageView imgArrow;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder child = null;
        if (view == null){
            child = new ChildHolder();
            view = inflater.inflate(R.layout.fragment_ku_cun_child,null);
            child.tvName = (TextView) view.findViewById(R.id.tv_name);
            child.tvYi = (TextView) view.findViewById(R.id.tv_yi);
            child.tvXie = (TextView) view.findViewById(R.id.tv_xie);
            child.tvTong = (TextView) view.findViewById(R.id.tv_tong);
            child.tvChuang = (TextView) view.findViewById(R.id.tv_chuang);
            child.imgArrow = (ImageView) view.findViewById(R.id.img_arrow);
            view.setTag(child);
        }else {
            child = (ChildHolder) view.getTag();
        }
        child.tvName.setText(list.get(i).getList().get(i1).getCmc());
        child.tvYi.setText(list.get(i).getList().get(i1).getYy());
        child.tvXie.setText(list.get(i).getList().get(i1).getYx());
        child.tvTong.setText(list.get(i).getList().get(i1).getSdt());
        child.tvChuang.setText(list.get(i).getList().get(i1).getTc());
        child.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KuCunBianJiActivity.class);
                intent.putExtra("cid",list.get(i).getList().get(i1).getCid());
                intent.putExtra("zmc",list.get(i).getZmc());
                intent.putExtra("cmc",list.get(i).getList().get(i1).getCmc());
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

}
