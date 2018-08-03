package com.jsmy.pingshan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.adapter.FragmentAdapter;
import com.jsmy.pingshan.fragment.PersonFragment;
import com.jsmy.pingshan.fragment.PersonFragment2;
import com.jsmy.pingshan.fragment.PersonFragment3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class ConversationListActivity extends FragmentActivity {
    @BindView(R.id.my_tab)
    TabLayout myTab;
    @BindView(R.id.my_viewpager)
    ViewPager myViewpager;
    private FragmentAdapter adapter;
    private List<Fragment> fragmentsList;//fragment容器
    private List<String> titleList;//标签容器

    private PersonFragment personFragment;
    private PersonFragment2 personFragment2;
    private PersonFragment3 personFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);
        ButterKnife.bind(this);
//        enterFragment();
        initView();
    }

    private void initView() {
        fragmentsList = new ArrayList<>();
        titleList = new ArrayList<>();

        personFragment = new PersonFragment();
        personFragment2 = new PersonFragment2();
        personFragment3 = new PersonFragment3();

        fragmentsList.add(personFragment);
        fragmentsList.add(personFragment2);
        fragmentsList.add(personFragment3);

        titleList.add("会话");
        titleList.add("联系人");
        titleList.add("讨论组");

        myTab.setTabMode(TabLayout.MODE_FIXED);

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentsList, titleList);
        myViewpager.setAdapter(adapter);
        myViewpager.setOffscreenPageLimit(3);
        myTab.setupWithViewPager(myViewpager);
        myTab.setTabsFromPagerAdapter(adapter);
    }

    public void startPrivateChat(String userId, String title) {
        RongIM.getInstance().startPrivateChat(this, userId, title);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (personFragment3 != null) {
                personFragment3.getDiscussList();
            }
        }
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
