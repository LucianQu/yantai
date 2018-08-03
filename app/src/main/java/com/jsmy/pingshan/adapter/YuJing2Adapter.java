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
import com.jsmy.pingshan.bean.YuJing2Bean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */

public class YuJing2Adapter extends BaseExpandableListAdapter {

    private MainActivity context;
    private List<YuJing2Bean.DataBean> list;
    private LayoutInflater inflater;

    public YuJing2Adapter(MainActivity context, List<YuJing2Bean.DataBean> list) {
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

    class GroupHolder {
        TextView tvName;
        ImageView imgArrow;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder group = null;
        if (view == null) {
            group = new GroupHolder();
            view = inflater.inflate(R.layout.fragment_yujing_expand_father2, null);
            group.tvName = (TextView) view.findViewById(R.id.tv_name);
            group.imgArrow = (ImageView) view.findViewById(R.id.img_arrow);
            view.setTag(group);
        } else {
            group = (GroupHolder) view.getTag();
        }
        group.tvName.setText(list.get(i).getADNM());
        if (b) {
            group.imgArrow.setImageResource(R.mipmap.yujingxiangying_shangjiantou);
        } else {
            group.imgArrow.setImageResource(R.mipmap.yujingxiangying_xiajiantou);
        }
        return view;
    }

    class ChildHolder {
        TextView tvName;
        TextView tvDate;
        TextView tvDate2;
        ImageView imgInfo;
        ImageView imgZhu;
        ImageView imgArrow;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder child = null;
        if (view == null) {
            child = new ChildHolder();
            view = inflater.inflate(R.layout.fragment_yujing_expand_father, null);
            child.tvName = (TextView) view.findViewById(R.id.tv_name);
            child.tvDate = (TextView) view.findViewById(R.id.tv_date);
            child.tvDate2 = (TextView) view.findViewById(R.id.tv_date2);
            child.imgInfo = (ImageView) view.findViewById(R.id.img_info);
            child.imgZhu = (ImageView) view.findViewById(R.id.img_zhu);
            child.imgArrow = (ImageView) view.findViewById(R.id.img_arrow);
            view.setTag(child);
        } else {
            child = (ChildHolder) view.getTag();
        }
        child.tvName.setText(list.get(i).getList().get(i1).getJcz());
        child.tvDate.setText(list.get(i).getList().get(i1).getLatestLevl());
        child.tvDate2.setText(list.get(i).getList().get(i1).getLatestDt().substring(5));
        child.imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, XiangYingFankuiActivity.class);
                intent.putExtra("cid", list.get(i).getList().get(i1).getId());
                context.startActivity(intent);
            }
        });
        child.imgZhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZaiQingTongJiActivity.class);
                intent.putExtra("cid", list.get(i).getList().get(i1).getId());
                context.startActivity(intent);
            }
        });
        child.imgArrow.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
