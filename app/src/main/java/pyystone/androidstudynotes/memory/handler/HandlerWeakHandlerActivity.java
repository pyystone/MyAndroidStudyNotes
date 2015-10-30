package pyystone.androidstudynotes.memory.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

import pyystone.androidstudynotes.util.ApplicationUtil;

/**
 * Created by pyysotne on 2015/10/23/0023.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class HandlerWeakHandlerActivity extends HandlerBaseActivity {
    private View mSyncNoticeView ;
    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if(mSyncNoticeView == null) {
                ApplicationUtil.toast(getClass().getName() + "has receive!");
                Log.e("Handler WeakHandler","weakActivity isn't null");
                return true;
            }
            Log.e("Handler WeakHandler","weakActivity is null");
            return false;
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
