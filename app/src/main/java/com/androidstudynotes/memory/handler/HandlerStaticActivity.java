package com.androidstudynotes.memory.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.androidstudynotes.util.ApplicationUtil;

/**
 * Created by pyysotne on 2015/10/23/0023.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class HandlerStaticActivity extends HandlerBaseActivity {
    private View mSyncNoticeView ;
    private Handler mHandler = new MyHandler(this);

    static class MyHandler extends Handler {
        Activity mActivity;
        MyHandler(Activity activity) {
            mActivity = activity;
        }
        @Override
        public void handleMessage(Message msg) {
            // do something
            try {
                ApplicationUtil.getInstance().toast(mActivity.getClass().getName() + "has receive!");
                Log.e("HandlerStatic", ((HandlerStaticActivity)mActivity).mSyncNoticeView == null ? "view is null" : "view isn't null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSyncNoticeView = new View(this);
        mHandler.sendEmptyMessageDelayed(1,time);
        finish();
    }
}
