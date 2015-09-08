package pyystone.androidstudynotes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pyystone.androidstudynotes.BaseActivity;
import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2015/08/21/0021.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class UIActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final int GOTO_GIFUI = 1;

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
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tag = (int) view.getTag();
        Intent intent;
        if (tag == GOTO_GIFUI) {
            intent = new Intent(this, GifUIActivtiy.class);
            startActivity(intent);
        }
    }
}
