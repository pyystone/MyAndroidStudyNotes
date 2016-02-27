package pyystone.androidstudynotes.ui;

import pyystone.androidstudynotes.BaseActivity;
import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2016/1/9.
 * email: pyystone@163.com
 * QQ: 862429936
 * 这个界面主要用于头像在ScrollView里面的缩放效果
 */
public class UserHeadActivity extends BaseActivity {

    @Override
    protected void initUI() {
//        mFragment = new UserHeadFragment();
//        FragmentManager manager = getFragmentManager();
//        manager.beginTransaction().add(R.id.contentFragment,mFragment).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_head_activity;
    }

}
