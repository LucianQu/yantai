package com.jsmy.pingshan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.PreAdapter;
import com.jsmy.pingshan.bean.PreBean;
import com.jsmy.pingshan.model.NetWork;
import com.jsmy.pingshan.util.ToastUtil;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 市级前置界面
 * 列表里都为县
 */
public class PreActivity extends BaseActivity {


    @BindView(R.id.recyc_reciv)
    RecyclerView recycReciv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    List<PreBean.DataBean.ListBean> list;
    PreAdapter adapter;
    private static String ID_KEY = "ID";
    public  static  int FROM_DINGWEI=1;
    public  static  int FROM_YUAN=2;
    private int from;
    @Override
    protected int getContenView() {
        return R.layout.activity_pre;
    }

    @Override
    protected void initView() {
    from=getIntent().getIntExtra(ID_KEY,FROM_DINGWEI);
    tvTitle.setText(from==FROM_YUAN?"预案管理":"定位统计");
    }

    @Override
    protected void initData() {
        NetWork.getPreList(this);
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("1".equals(code)) {
            if (gson.fromJson(result, PreBean.class).getData().getList().size() > 0) {
                list = gson.fromJson(result, PreBean.class).getData().getList();
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycReciv.setLayoutManager(layoutManager);
                recycReciv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                adapter = new PreAdapter(list, this,from);
                recycReciv.setAdapter(adapter);
            }
        } else {
            ToastUtil.showShort(this, msg);
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    public static void startPreActivity(Context context, int fromac) {
        Intent intent = new Intent(context, PreActivity.class);
        intent.putExtra(ID_KEY, fromac);
        context.startActivity(intent);

    }




    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
