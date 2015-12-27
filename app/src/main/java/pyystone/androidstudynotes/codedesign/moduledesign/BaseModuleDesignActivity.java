package pyystone.androidstudynotes.codedesign.moduledesign;


import android.view.View;

import pyystone.androidstudynotes.BaseActivity;
import pyystone.androidstudynotes.R;
import pyystone.androidstudynotes.util.ApplicationUtil;

/**
 * Created by pyysotne on 2015/12/24.
 * email: pyystone@163.com
 * QQ: 862429936
 * 不做组件设计的Fragment设计
 */
public class BaseModuleDesignActivity extends BaseActivity {
    private BaseModuleDesignListFragment mListFragment;
    private ModlueDesignListFragment mListFragment2;
    private BottomFragment mBottomFragment;
    @Override
    protected void initUI() {
        mListFragment = new BaseModuleDesignListFragment();
        mListFragment2 = new ModlueDesignListFragment();
        mBottomFragment = new BottomFragment("初始设计","组件设计");
        mBottomFragment.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationUtil.toast("gotoList1");
                getFragmentManager().beginTransaction().replace(R.id.listFragment,mListFragment).commit();
            }
        });
        mBottomFragment.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationUtil.toast("gotoList2");
                getFragmentManager().beginTransaction().replace(R.id.listFragment,mListFragment2).commit();
            }
        });
        getFragmentManager().beginTransaction()
                .replace(R.id.listFragment,mListFragment)
                .replace(R.id.bottomFragment,mBottomFragment).commit();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_design_activity;
    }
}
