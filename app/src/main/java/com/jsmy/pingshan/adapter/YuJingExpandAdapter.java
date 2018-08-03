package com.jsmy.pingshan.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.activity.XiangYingFankuiActivity;
import com.jsmy.pingshan.activity.ZaiQingTongJiActivity;
import com.jsmy.pingshan.bean.YuJingBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class YuJingExpandAdapter extends BaseExpandableListAdapter {

    private MainActivity context;
    private List<YuJingBean.DataBean.ListBeanX> list;
    private LayoutInflater inflater;
    private String zid;
    public YuJingExpandAdapter(MainActivity context, List<YuJingBean.DataBean.ListBeanX> list,String zid) {
        this.context = context;
        this.list = list;
        this.zid = zid;
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

    class GroupHolder {
        TextView tvName;
        TextView tvDate;
        ImageView imgInfo;
        ImageView imgZhu;
        ImageView imgArrow;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder group = null;
        if (view == null) {
            group = new GroupHolder();
            view = inflater.inflate(R.layout.fragment_yujing_expand_father, null);
            group.tvName = (TextView) view.findViewById(R.id.tv_name);
            group.tvDate = (TextView) view.findViewById(R.id.tv_date);
            group.imgInfo = (ImageView) view.findViewById(R.id.img_info);
            group.imgZhu = (ImageView) view.findViewById(R.id.img_zhu);
            group.imgArrow = (ImageView) view.findViewById(R.id.img_arrow);
            view.setTag(group);
        } else {
            group = (GroupHolder) view.getTag();
        }
        group.tvName.setText(list.get(i).getCmc());
        group.tvDate.setText(list.get(i).getLatestLevl() + list.get(i).getDt().substring(11));
        group.imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, XiangYingFankuiActivity.class);
                intent.putExtra("zid", zid);
                context.startActivity(intent);
            }
        });
        group.imgZhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZaiQingTongJiActivity.class);
                intent.putExtra("zid", zid);
                context.startActivity(intent);
            }
        });
        if (b) {
            group.imgArrow.setImageResource(R.mipmap.yujingxiangying_shangjiantou);
        } else {
            group.imgArrow.setImageResource(R.mipmap.yujingxiangying_xiajiantou);
        }
        return view;
    }

    class ChildHolder {
        TextView tvName;
        TextView tvWater;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder child = null;
        if (view == null) {
            child = new ChildHolder();
            view = inflater.inflate(R.layout.fragment_yujing_expand_chrid, null);
            child.tvName = (TextView) view.findViewById(R.id.tv_name);
            child.tvWater = (TextView) view.findViewById(R.id.tv_water);
            view.setTag(child);
        } else {
            child = (ChildHolder) view.getTag();
        }
        child.tvName.setText(list.get(i).getList().get(i1).getSTNM());
        child.tvWater.setText(list.get(i).getList().get(i1).getLatestyl());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

}
