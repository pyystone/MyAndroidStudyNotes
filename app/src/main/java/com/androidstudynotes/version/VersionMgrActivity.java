package com.androidstudynotes.version;

import android.view.View;

import com.androidstudynotes.BaseActivity;
import com.androidstudynotes.R;
import com.androidstudynotes.util.ApplicationUtil;
import com.androidstudynotes.util.ChannelUtil;

/**
 * Created by pyysotne on 2015/09/08/0008.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class VersionMgrActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initUI() {
        findViewById(R.id.marketCode).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.version_mgr_activity;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.marketCode) {
            doMarketCode();
        }
    }

    /**
     * 方法地址：
     * https://github.com/GavinCT/AndroidMultiChannelBuildTool
     *
     */
    private void doMarketCode() {
        ApplicationUtil.getInstance().toast("marketCode:" + ChannelUtil.getChannel(this));

    }
}
