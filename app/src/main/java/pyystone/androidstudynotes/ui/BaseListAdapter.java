package pyystone.androidstudynotes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2015/08/21/0021.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class BaseListAdapter extends BaseAdapter {
    private List<ListItemData> mData;
    private Context mContext;
    public BaseListAdapter(List<ListItemData> datas,Context context) {
        mContext = context;
        mData = datas;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        if (mData == null) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 该ListView 里面有多少种需要重建的view
    @Override
    public int getViewTypeCount() {
        //return mData.size();
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.base_list_item,null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv1);
        ListItemData item = (ListItemData) getItem(position);
        tv.setText(item.mTitle);
        convertView.setTag(item.mTagId);
        return convertView;
    }

}
