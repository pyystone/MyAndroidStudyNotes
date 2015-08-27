package pyystone.androidstudynotes;

import android.app.Application;

import pyystone.androidstudynotes.util.ApplicationUtil;

/**
 * Created by pyysotne on 2015/08/19/0019.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        ApplicationUtil.init(getApplicationContext());
    }
}
