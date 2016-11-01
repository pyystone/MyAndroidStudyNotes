package com.androidstudynotes.codedesign.moduledesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidstudynotes.R;
import com.androidstudynotes.ui.ListItemData;

/**
 * Created by pyysotne on 2015/12/25.
 * email: pyystone@163.com
 * QQ: 862429936
 * 开闭原则
 */
public class ModuleDesignListAdapter extends BaseAdapter {
    public interface ViewController {
        public View getViewByPosition(Object data,View view);
    }
    private BaseDataModel mModel;
    private Context mContext;
    private ViewController mViewController;
    public ModuleDesignListAdapter(BaseDataModel model,Context context) {
        mModel = model;
        mContext = context;
    }
    public void setViewController(ViewController controller) {
        mViewController = controller;
    }
    @Override
    public int getCount() {
        return mModel.getChildCount();
    }

    @Override
    public Object getItem(int position) {
        return mModel.getChildByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mViewController != null) {
            return mViewController.getViewByPosition(getItem(position),convertView);
        }
        return getViewByPosition(position, convertView);
    }

    public View getViewByPosition(int position,View view) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.base_list_item, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.tv1);
        ListItemData item = (ListItemData) getItem(position);
        tv.setText(item.mTitle);
        view.setTag(item.mTagId);
        return view;
    }

}
