package pyystone.androidstudynotes.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.makeramen.roundedimageview.RoundedImageView;

import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2016/1/9.
 * email: pyystone@163.com
 * QQ: 862429936
 * 头像项目地址 ：https://github.com/vinc3m1/RoundedImageView
 *
 */
public class UserHeadFragment extends Fragment {
    private View mRootView;
    private RoundedImageView mHeadView;
    private int mZoomViewMinHeight = 100;
    private int mZoomViewMaxHeight;
    private int mZoomViewHeight = 0;
    private boolean mHasInitHeight = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.user_head_fragment,null);
        initUI();
        return mRootView;
    }

    private void initUI() {
        mHeadView = (RoundedImageView) mRootView.findViewById(R.id.imageView1);
        clacHeight();
    }

    private void clacHeight() {
        ViewTreeObserver observer = mRootView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!mHasInitHeight) {
                    mZoomViewMaxHeight = mZoomViewHeight = mHeadView.getHeight();
                    mHasInitHeight = true;
                }
            }
        });
    }

    public void setZoomViewMinHeight(int zoomViewMinHeight) {
        this.mZoomViewMinHeight = zoomViewMinHeight;
    }

    public void setZoomViewMaxHeight(int zoomViewMaxHeight) {
        mZoomViewMaxHeight = zoomViewMaxHeight;
    }
}
