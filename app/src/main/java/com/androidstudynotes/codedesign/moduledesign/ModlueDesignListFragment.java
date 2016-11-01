package com.androidstudynotes.codedesign.moduledesign;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidstudynotes.R;

/**
 * Created by pyysotne on 2015/12/25.
 * email: pyystone@163.com
 * QQ: 862429936
 * 列表组件
 */
public class ModlueDesignListFragment extends Fragment {
    private View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.list_fragment,null);
        initUI();
        return mRootView;
    }

    private void initUI() {
        ((ListView)mRootView.findViewById(R.id.listview))
                .setAdapter(new ModuleDesignListAdapter(new ModuleDesignListModel(),getActivity()));
    }

}
