package pyystone.androidstudynotes.memory.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import pyystone.androidstudynotes.util.ApplicationUtil;

/**
 * Created by pyysotne on 2015/10/23/0023.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class Handler2MainLoopActivity extends HandlerBaseActivity {
    private View mSyncNoticeView ;
    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
        try {
            ApplicationUtil.getInstance().toast(getClass().getName() + "has receive!");
            Log.e("Handler2MainLoop",mSyncNoticeView == null ? "view is null" : "view isn't null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSyncNoticeView = new View(this);
        mHandler.sendEmptyMessageDelayed(1,time);
        finish();
    }
}
