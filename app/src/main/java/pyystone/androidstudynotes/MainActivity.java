package pyystone.androidstudynotes;

import android.content.Intent;
import android.graphics.Point;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pyystone.androidstudynotes.debug.point.PointActivity;
import pyystone.androidstudynotes.memory.handler.HandlerMainActivity;
import pyystone.androidstudynotes.ui.UIActivity;
import pyystone.androidstudynotes.version.VersionMgrActivity;

// 这是主Activity

public class MainActivity extends BaseActivity implements View.OnClickListener {


    protected void initUI() {
        findViewById(R.id.UI).setOnClickListener(this);
        findViewById(R.id.thread).setOnClickListener(this);
        findViewById(R.id.version).setOnClickListener(this);
        findViewById(R.id.handler).setOnClickListener(this);
        findViewById(R.id.pointDebug).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        if (id == R.id.UI) {
            intent.setClass(this, UIActivity.class);
            startActivity(intent);
        } else if (id == R.id.thread) {

        } else if (id == R.id.version) {
            intent.setClass(this, VersionMgrActivity.class);
            startActivity(intent);
        } else if (id == R.id.handler) {
            intent.setClass(this, HandlerMainActivity.class);
            startActivity(intent);
        } else if (id == R.id.pointDebug) {
            intent.setClass(this, PointActivity.class);
            startActivity(intent);
        }

    }
}
