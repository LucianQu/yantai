package com.jsmy.pingshan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.MainActivity;
import com.jsmy.pingshan.bean.TongXunLuBean;
import com.jsmy.pingshan.util.MesssageUtil;
import com.jsmy.pingshan.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class TongXunLuAdapter extends BaseExpandableListAdapter {
    private MainActivity context;
    private List<TongXunLuBean.DataBean.ListBeanX> list;
    private LayoutInflater inflater;

    public TongXunLuAdapter(MainActivity context, List<TongXunLuBean.DataBean.ListBeanX> list) {
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
        private TextView tvMc;
        private TextView tvRen;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder group = null;
        if (view == null) {
            group = new GroupHolder();
            view = inflater.inflate(R.layout.fragment_tongxunlu_expand_group, null);
            group.tvMc = (TextView) view.findViewById(R.id.tv_mc);
            group.tvRen = (TextView) view.findViewById(R.id.tv_ren);
            view.setTag(group);
        } else {
            group = (GroupHolder) view.getTag();
        }
        group.tvMc.setText(list.get(i).getADNM());
        group.tvRen.setText(list.get(i).getList().size() + 0 + "人");
        return view;
    }

    class ChildHolder {
        private CircleImageView imgHead;
        private TextView tvName;
        private TextView tvPhone;
        private TextView tvZhi;
        private TextView tvDan;
        private ImageView imgPhone;
        private ImageView imgSms;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder child = null;
        if (view == null) {
            child = new ChildHolder();
            view = inflater.inflate(R.layout.fragment_tongxunlu_expand_child, null);
            child.imgHead = (CircleImageView) view.findViewById(R.id.img_head);
            child.tvName = (TextView) view.findViewById(R.id.tv_name);
            child.tvPhone = (TextView) view.findViewById(R.id.tv_phone);
            child.tvZhi = (TextView) view.findViewById(R.id.tv_zhi);
            child.tvDan = (TextView) view.findViewById(R.id.tv_dan);
            child.imgPhone = (ImageView) view.findViewById(R.id.img_phone);
            child.imgSms = (ImageView) view.findViewById(R.id.img_sms);
            view.setTag(child);
        } else {
            child = (ChildHolder) view.getTag();
        }
        child.tvName.setText(list.get(i).getList().get(i1).getName());
        child.tvPhone.setText(list.get(i).getList().get(i1).getSjh());
        child.tvZhi.setText(list.get(i).getList().get(i1).getPost());
        child.tvDan.setText(list.get(i).getList().get(i1).getDeptNm());
        child.imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MesssageUtil.sendSMS(context, list.get(i).getList().get(i1).getSjh());
            }
        });
        child.imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MesssageUtil.call(context, list.get(i).getList().get(i1).getSjh());
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
