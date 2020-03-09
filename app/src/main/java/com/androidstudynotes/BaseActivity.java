package com.androidstudynotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pyysotne on 2015/08/22/0022.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initUI();
    }

    protected abstract void initUI();

    protected abstract int getLayoutId();


}
