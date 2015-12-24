package pyystone.androidstudynotes;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import pyystone.androidstudynotes.net.LruBitmapCache;

/**
 * Created by pyysotne on 2015/08/22/0022.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initUI();
    }

    protected abstract void initUI();

    protected abstract int getLayoutId();


}
