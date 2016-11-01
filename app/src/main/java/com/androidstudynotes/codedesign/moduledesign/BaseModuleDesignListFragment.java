package com.androidstudynotes.codedesign.moduledesign;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.androidstudynotes.R;
import com.androidstudynotes.ui.BaseListAdapter;
import com.androidstudynotes.ui.ListItemData;
import com.androidstudynotes.util.ApplicationUtil;

/**
 * Created by pyysotne on 2015/12/25.
 * email: pyystone@163.com
 * QQ: 862429936
 * 不做组件设计的Fragment设计
 */
public class BaseModuleDesignListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
            return mRootView;
        }
        mRootView = inflater.inflate(R.layout.list_fragment,null);
        initUI();
        return mRootView;

    }
    private void initUI() {
        ListView listView = (ListView) mRootView.findViewById(R.id.listview);
        listView.setAdapter(new BaseListAdapter(getListViewData(),getActivity()));
        listView.setOnItemClickListener(this);
    }

    public List<ListItemData> getListViewData() {
        List<ListItemData> data = new ArrayList<>();
        int count = 20;
        while(count-- != 0) {
            ListItemData item = new ListItemData();
            item.mTitle = "BaseModuleDesignListFragment item" + count;
            item.mTagId = count;
            data.add(item);
        }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ApplicationUtil.getInstance().toast(((TextView)view.findViewById(R.id.tv1)).getText().toString());
    }
}
