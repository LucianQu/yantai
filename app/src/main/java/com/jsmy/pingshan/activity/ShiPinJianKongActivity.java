package com.jsmy.pingshan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jsmy.pingshan.R;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class ShiPinJianKongActivity extends BaseActivity {

    @BindView(R.id.my_spinner)
    Spinner mySpinner;
    private String[] states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_shi_pin_jian_kong;
    }

    @Override
    protected void initView() {
        states = getResources().getStringArray(R.array.shipinzhan);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_shipin, states);
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown_shipin);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
