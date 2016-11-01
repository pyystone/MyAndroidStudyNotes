package com.androidstudynotes.codedesign.moduledesign;

import java.util.ArrayList;

import com.androidstudynotes.ui.ListItemData;

/**
 * Created by pyysotne on 2015/12/25.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class ModuleDesignListModel extends BaseDataModel {
    private ArrayList<ListItemData> mListItemDatas;

    @Override
    protected void initChildData() {
        mListItemDatas = new ArrayList<>();
        int count = 20;
        while(count-- != 0) {
            ListItemData item = new ListItemData();
            item.mTitle = "ModuleDesignListModel item" + count;
            item.mTagId = count;
            mListItemDatas.add(item);
        }
    }

    @Override
    protected int getChildCount() {
        if (mListItemDatas == null) {
            return 0;
        }
        return mListItemDatas.size();
    }

    @Override
    protected Object getChildByPosition(int position) {
        if (mListItemDatas == null) {
            return null;
        }
        return mListItemDatas.get(position);
    }

    @Override
    protected int getChildType() {
        return 1;
    }
}
