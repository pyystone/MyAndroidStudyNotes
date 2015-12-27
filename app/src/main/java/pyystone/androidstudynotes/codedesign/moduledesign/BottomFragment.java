package pyystone.androidstudynotes.codedesign.moduledesign;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2015/12/25.
 * email: pyystone@163.com
 * QQ: 862429936
 * 底层组件
 * // TODO: 2015/12/25  使用这个类需要初始化2个btn按钮的文案 和 click，时间关系不进行细节设计
 */
public class BottomFragment extends Fragment {
    public View.OnClickListener mRightClickListener;
    public View.OnClickListener mLeftClickListener;
    private View mRootView;
    private String leftTxt;
    private String rightTxt;

    public BottomFragment(String leftBtn,String rightBtn) {
        leftTxt = leftBtn;
        rightTxt = rightBtn;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.bottom_fragment,null);
        initUI();
        return mRootView;
    }

    private void initUI() {
        ((Button)mRootView.findViewById(R.id.btn1)).setText(leftTxt);
        ((Button)mRootView.findViewById(R.id.btn2)).setText(rightTxt);

        mRootView.findViewById(R.id.btn1).setOnClickListener(mLeftClickListener);
        mRootView.findViewById(R.id.btn2).setOnClickListener(mRightClickListener);
    }

    public void setRightClickListener(View.OnClickListener rightClickListener) {
        mRightClickListener = rightClickListener;
    }

    public void setLeftClickListener(View.OnClickListener leftClickListener) {
        mLeftClickListener = leftClickListener;
    }

}
