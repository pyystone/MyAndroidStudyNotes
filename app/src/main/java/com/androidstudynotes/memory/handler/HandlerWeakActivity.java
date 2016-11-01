package com.androidstudynotes.memory.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

import com.androidstudynotes.util.ApplicationUtil;

/**
 * Created by pyysotne on 2015/10/23/0023.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class HandlerWeakActivity extends HandlerBaseActivity {
    private View mSyncNoticeView ;
    private Handler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler{
        private final WeakReference<Activity> mActivity;
        public MyHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if(mActivity.get() == null) {
                ApplicationUtil.getInstance().toast(mActivity.getClass().getName() + "has receive!");
                Log.e("Handler rWeakActivity","weakActivity isn't null");
                return;
            }
            Log.e("Handler WeakActivity","weakActivity is null");
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
