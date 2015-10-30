package pyystone.androidstudynotes.memory.handler;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by pyysotne on 2015/10/23/0023.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class HandlerBaseActivity extends Activity {
    protected int time = 1000*60;
    protected int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count = getIntent().getIntExtra("count",0);
    }
}
