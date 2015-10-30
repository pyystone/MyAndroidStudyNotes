package pyystone.androidstudynotes.memory.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2015/10/23/0023.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class HandlerMainActivity extends Activity implements View.OnClickListener {
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_activity);
        init();
    }
    private void init() {
        findViewById(R.id.gotoHandlerActivity).setOnClickListener(this);
        findViewById(R.id.gotoMainLoopActivity).setOnClickListener(this);
        findViewById(R.id.gotoStaticActivity).setOnClickListener(this);
        findViewById(R.id.gotoWeakActivity).setOnClickListener(this);
        findViewById(R.id.gotoWeakHandlerActivity).setOnClickListener(this);
        count = 0;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        count ++;
        Intent intent;
        if (id == R.id.gotoMainLoopActivity) {
            intent = new Intent(HandlerMainActivity.this,Handler2MainLoopActivity.class);
        } else if (id == R.id.gotoStaticActivity) {
            intent = new Intent(HandlerMainActivity.this,HandlerStaticActivity.class);
        } else if (id == R.id.gotoWeakActivity) {
            intent = new Intent(HandlerMainActivity.this,HandlerWeakActivity.class);
        } else if (id == R.id.gotoHandlerActivity) {
            intent = new Intent(this,HandlerActivity.class);
        } else {
            intent = new Intent(HandlerMainActivity.this,HandlerWeakHandlerActivity.class);
        }
        intent.putExtra("count",count);
        startActivity(intent);
    }
}
