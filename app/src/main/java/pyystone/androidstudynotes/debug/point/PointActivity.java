package pyystone.androidstudynotes.debug.point;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pyystone.androidstudynotes.R;

/**
 * 断点调试类
 * btn1 : 异常断点 BreakPoints -> Java Exception BreakPoints -> NullPointerException
 * btn2 : 对象断点 Field WatchPoint
 *
 */
public class PointActivity extends AppCompatActivity implements View.OnClickListener {

    private testClass mAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        init();
    }

    private void init() {
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
    }

    private void initView() {
    }

    private void initData() {
        mAndroid = new testClass();
        mAndroid.songsong = 1;
        mAndroid.mingming = 2;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1) {
            ((TextView)findViewById(R.id.action0)).setText(null);
        } else if (id == R.id.btn2) {
            mAndroid.songsong = 3;
        } else if (id == R.id.btn3) {
            mAndroid.mingming = 4;
        } else if (id == R.id.btn4) {

        }
    }

    class testClass {
        public int songsong;
        public int mingming;
    }
}
