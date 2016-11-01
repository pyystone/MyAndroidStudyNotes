package com.androidstudynotes.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.androidstudynotes.BaseActivity;
import com.androidstudynotes.R;
import com.androidstudynotes.ui.activity.ParticleActivity;

/**
 * Created by pyysotne on 2015/08/21/0021.
 * email: pyystone@163.com
 * QQ: 862429936
 * GifUI 动态图加载
 * UserHead 用于头像在ScrollView 里面的缩放动效
 */
public class UIActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final int GOTO_GIFUI = 1;
    private static final int GOTO_USER_HEAD = 2;
    private static final int GOTO_POPUP_MESSAGE = 3;
    private static final int GOTO_PARTICLEACTIVITY = 4;

    protected void initUI() {
        ListView listView = (ListView) findViewById(R.id.listview);
        BaseListAdapter adapter = new BaseListAdapter(getListViewData(),this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ui_activity;
    }

    public List<ListItemData> getListViewData() {
        List<ListItemData> data = new ArrayList<>();
        ListItemData item = new ListItemData();
        item.mTitle = "GifImageView";
        item.mTagId = GOTO_GIFUI;
        data.add(item);
        ListItemData item2 = new ListItemData();
        item2.mTitle = "UserHead";
        item2.mTagId = GOTO_USER_HEAD;
        data.add(item2);
        ListItemData item3 = new ListItemData();
        item3.mTitle = "PopupMessage";
        item3.mTagId = GOTO_POPUP_MESSAGE;
        data.add(item3);
        ListItemData item4 = new ListItemData();
        item4.mTitle = "ParticleActivity";
        item4.mTagId = GOTO_PARTICLEACTIVITY;
        data.add(item4);
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tag = (int) view.getTag();
        Intent intent;
        if (tag == GOTO_GIFUI) {
            intent = new Intent(this, GifUIActivtiy.class);
            startActivity(intent);
        } else if (tag == GOTO_USER_HEAD) {
            intent = new Intent(this, UserHeadActivity.class);
            startActivity(intent);
        } else if (tag == GOTO_POPUP_MESSAGE) {
            intent = new Intent(this,PopUpMessageActivity.class);
            startActivity(intent);
        } else if (tag == GOTO_PARTICLEACTIVITY) {
            intent = new Intent(this,ParticleActivity.class);
            startActivity(intent);
        }
    }
}
