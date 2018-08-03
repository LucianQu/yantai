package com.jsmy.pingshan.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsmy.pingshan.R;
import com.jsmy.pingshan.activity.ConversationListActivity;

import org.json.JSONException;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by Administrator on 2017/11/6.
 */

public class PersonFragment extends BaseFragment {
    private ConversationListFragment fragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, null);
        fragment = new ConversationListFragment();
        enterFragment();
        return view;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {

    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    /**
     * 加载会话页面 ConversationListFragment
     */
    private void enterFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.conversationlist,fragment,"person").commit();
        Uri uri = Uri.parse("rong://" + getContext().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                .build();

        fragment.setUri(uri);
//        RongIM.getInstance().startPrivateChat(this, id1, name1);
    }
}
