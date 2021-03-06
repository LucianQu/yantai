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
import com.jsmy.pingshan.activity.QiangXianBianJiActivity;
import com.jsmy.pingshan.activity.ZhiHuiRenYuanActivity;
import com.jsmy.pingshan.bean.QiangianFramentBean;
import com.jsmy.pingshan.bean.ZhiHuiFramentBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class QiangXianFragmentAdapter extends BaseExpandableListAdapter {

    private DingWeiTongJiActivity context;
    private List<QiangianFramentBean.DataBean.ListBeanX> list;
    private LayoutInflater inflater;

    public QiangXianFragmentAdapter(DingWeiTongJiActivity context, List<QiangianFramentBean.DataBean.ListBeanX> list) {
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
        private TextView tvNum;
        private ImageView imgArrow;

    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder group = null;
        if (view == null){
            group = new GroupHolder();
            view = inflater.inflate(R.layout.fragment_zhi_hui_group,null);
            group.tvName = (TextView) view.findViewById(R.id.tv_name);
            group.tvNum = (TextView) view.findViewById(R.id.tv_num);
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
        group.tvNum.setText(list.get(i).getQxrs());
        return view;
    }

    class ChildHolder{
        private TextView tvName;
        private TextView tvNum;
        private ImageView imgZhihui;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder child = null;
        if (view == null){
            child = new ChildHolder();
            view = inflater.inflate(R.layout.fragment_zhi_hui_child,null);
            child.tvName = (TextView) view.findViewById(R.id.tv_name);
            child.tvNum = (TextView) view.findViewById(R.id.tv_num);
            child.imgZhihui = (ImageView) view.findViewById(R.id.img_zhihui);
            view.setTag(child);
        }else {
            child = (ChildHolder) view.getTag();
        }
        child.tvName.setText(list.get(i).getList().get(i1).getCmc());
        child.tvNum.setText(list.get(i).getList().get(i1).getQxrs());
        child.imgZhihui.setImageResource(R.mipmap.dingweitongji_bianji);
        child.imgZhihui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QiangXianBianJiActivity.class);
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
