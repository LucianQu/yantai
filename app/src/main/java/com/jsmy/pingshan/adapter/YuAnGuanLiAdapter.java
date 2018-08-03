package com.jsmy.pingshan.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.YuAnDataActivity;
import com.jsmy.pingshan.activity.YuAnGuanLiActivity;
import com.jsmy.pingshan.bean.XZQBean;
import com.jsmy.pingshan.bean.YuAnGuanLiBean;
import com.jsmy.pingshan.bean.YuanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14.
 */

public class YuAnGuanLiAdapter extends BaseExpandableListAdapter {

    private YuAnGuanLiActivity contxet;
    private List<YuanBean.DataBean.ListBean> list;
    private LayoutInflater inflater;

    public YuAnGuanLiAdapter(YuAnGuanLiActivity contxet, List<YuanBean.DataBean.ListBean> list) {
        this.contxet = contxet;
        this.list = list;
        this.inflater = LayoutInflater.from(contxet);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList2().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getList2().get(i1);
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
        private TextView tvTitle;
        private ImageView imgArrow;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder group = null;
        if (view == null) {
            group = new GroupHolder();
            view = inflater.inflate(R.layout.activity_yu_an_guan_li_group, null);
            group.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            group.imgArrow = (ImageView) view.findViewById(R.id.img_arrow);
            view.setTag(group);
        } else {
            group = (GroupHolder) view.getTag();
        }
        group.tvTitle.setText(list.get(i).getZmc());
        group.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contxet, YuAnDataActivity.class);
                intent.putExtra("type", "z");
                intent.putExtra("mc", list.get(i).getZmc());
                intent.putExtra("id", list.get(i).getZid());
                contxet.startActivity(intent);
            }
        });
        return view;
    }

    class ChildHolder {
        private TextView tvName;
        private ImageView imgGl;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder child = null;
        if (view == null) {
            child = new ChildHolder();
            view = inflater.inflate(R.layout.activity_yu_an_guan_li_child, null);
            child.tvName = (TextView) view.findViewById(R.id.tv_name);
            child.imgGl = (ImageView) view.findViewById(R.id.img_gl);
            view.setTag(child);
        } else {
            child = (ChildHolder) view.getTag();
        }
//        child.tvName.setText(list.get(i).getList2().get(i1).getCmc());
//        child.imgGl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(contxet, YuAnDataActivity.class);
//                intent.putExtra("type", "c");
//                intent.putExtra("mc", list.get(i).getList2().get(i1).getCmc());
//                intent.putExtra("id", list.get(i).getList2().get(i1).getCid());
//                contxet.startActivity(intent);
//            }
//        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
