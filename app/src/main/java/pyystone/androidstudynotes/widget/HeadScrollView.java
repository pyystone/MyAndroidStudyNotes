package pyystone.androidstudynotes.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pyystone.androidstudynotes.R;
import pyystone.androidstudynotes.ui.MyScrollView;

/**
 * Created by pyysotne on 2016/1/9.
 * email: pyystone@163.com
 * QQ: 862429936
 *
 * 感谢赵新gg的demo里面的dispatchTouchEvent 提供思路
 * @zhaoxin http://github.com/zhaoxin1943
 */
public class HeadScrollView extends LinearLayout {
    private final static int MIN_TOP_VIEW = 15;

    private ImageView mImageView;
    private View mHeadView;
    private MyScrollView mContentView;
    private TextView mUserName;
    private TextView mLvName;
    private View mBack;
    private View mUserInfo;
    private View mTopView;

    private int mActionBarHeight = 100;
    private int mMaxTopHeight;

    private int headViewMaxHeight;
    private int headZoomHeight;
    private int imageViewMaxHeight;
    private int imageViewZoomHeight;
    private int nowZoomHeight;
    private int userInfoViewMaxHeight;
    private int userInfoViewZoomHeight;


    private boolean hasInitHeight = false;


    public HeadScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public HeadScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI();
    }

    private void initUI() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.user_head_fragment,this,true);
        mImageView = (ImageView) findViewById(R.id.imageView1);
        mHeadView = findViewById(R.id.llHeadView);
        mContentView = (MyScrollView) findViewById(R.id.contentView);
        mUserInfo = findViewById(R.id.llUesrInfo);
        mUserName = (TextView) findViewById(R.id.tvUserName);
        mLvName = (TextView) findViewById(R.id.tvLvName);
        mBack = findViewById(R.id.btnBack);
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTopView = findViewById(R.id.topView);
        mContentView.setOnScrollListener(new MyScrollView.onScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY <= headZoomHeight) {
                    zoomHeadView(scrollY);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!hasInitHeight) {
            hasInitHeight = true;
            mActionBarHeight = mBack.getMeasuredHeight();
            imageViewMaxHeight = mImageView.getMeasuredHeight();
            userInfoViewMaxHeight = mUserInfo.getMeasuredHeight();
            headViewMaxHeight = findViewById(R.id.llHeadView).getMeasuredHeight();
            mMaxTopHeight = mTopView.getMeasuredHeight();
            headZoomHeight = headViewMaxHeight - mActionBarHeight;
            imageViewZoomHeight = imageViewMaxHeight - mActionBarHeight + MIN_TOP_VIEW * 2;
            userInfoViewZoomHeight = (int) (userInfoViewMaxHeight * 0.7);
        }
    }

    private void zoomHeadView(int yDiff) {
        nowZoomHeight = yDiff;
        if (nowZoomHeight >= headZoomHeight) {
            nowZoomHeight = headZoomHeight;
        } else if (nowZoomHeight <= 0) {
            nowZoomHeight = 0;
        }
        ViewGroup.LayoutParams params = mHeadView.getLayoutParams();
        params.height = headViewMaxHeight - nowZoomHeight;
        mHeadView.setLayoutParams(params);

        float c = (float)nowZoomHeight / (float) headZoomHeight;

        ViewGroup.LayoutParams imageParams = mImageView.getLayoutParams();
        imageParams.height = (int) (imageViewMaxHeight - c * imageViewZoomHeight);
        mImageView.setLayoutParams(imageParams);

        ViewGroup.LayoutParams userParams = mUserInfo.getLayoutParams();
        userParams.height = (int)  ((1- c) * userInfoViewMaxHeight);
        mUserInfo.setLayoutParams(userParams);

        ViewGroup.LayoutParams topParams = mTopView.getLayoutParams();
        topParams.height = (int) (mMaxTopHeight - c * (mMaxTopHeight - MIN_TOP_VIEW));
        mTopView.setLayoutParams(topParams);

        c = c < 0.5 ? c * 2 : 1;
        float aphal =  (1 - c);
        mUserName.setAlpha(aphal);
        mLvName.setAlpha(aphal);
        mLvName.setAlpha(aphal);
    }

}
