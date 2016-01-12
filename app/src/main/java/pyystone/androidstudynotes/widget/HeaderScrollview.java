package pyystone.androidstudynotes.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.TextView;

import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2016/1/9.
 * email: pyystone@163.com
 * QQ: 862429936
 *
 * 感谢赵新gg的demo里面的dispatchTouchEvent 提供思路
 * @zhaoxin http://github.com/zhaoxin1943
 */
public class HeaderScrollview extends LinearLayout {
    private final static int MAX_Y_VELOCITY = 8000;
    private final static int MIN_TOP_VIEW = 15;

    private ImageView mImageView;
    private View mHeadView;
    private View mContentView;
    private TextView mUserName;
    private TextView mLvName;
    private View mBack;
    private View mUserInfo;
    private View mTopView;

    private VelocityTracker mVelocityTracker;
    private int mActivePointerId = -1;
    private int mMinFlingVelocity ;
    private int mActionBarHeight = 100;
    private int mMaxTopHeight;
    private boolean mIsInCanMoveView;


    private OverScroller mScroller;
    private int headViewMaxHeight;
    private int headZoomHeight;
    private int imageViewMaxHeight;
    private int imageViewZoomHeight;
    private int nowZoomHeight;
    private int userInfoViewMaxHeight;
    private int userInfoViewZoomHeight;


    private float mLastY;
    private float mLastX;
    private boolean hasInitHeight = false;


    private FlingRunnable mFlingRunnable;

    public HeaderScrollview(Context context) {
        super(context);
        initUI();
    }

    public HeaderScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public HeaderScrollview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI();
    }

    private void initUI() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.user_head_fragment,this,true);
        mImageView = (ImageView) findViewById(R.id.imageView1);
        mHeadView = findViewById(R.id.llHeadView);
        mContentView = findViewById(R.id.contentView);
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

        ViewConfiguration vc = ViewConfiguration.get(getContext());
        mMinFlingVelocity = vc.getScaledMinimumFlingVelocity();
        mScroller = new OverScroller(getContext());
        mFlingRunnable = new FlingRunnable();
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
        int contentViewHeightSpec = MeasureSpec.makeMeasureSpec(mContentView.getMeasuredHeight() + headViewMaxHeight, MeasureSpec.EXACTLY);
        int contentViewWidthSpec = MeasureSpec.makeMeasureSpec(mContentView.getMeasuredWidth() + headViewMaxHeight, MeasureSpec.EXACTLY);
        mContentView.measure(contentViewWidthSpec,contentViewHeightSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight() + headViewMaxHeight,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(ev);
                mActivePointerId = MotionEventCompat.getPointerId(ev,0);
                final float initialDownY = getMotionEventY(ev,mActivePointerId);
                final float initialDownX = getMotionEventX(ev,mActivePointerId);
                mLastX = initialDownX;
                mLastY = initialDownY;

                if (isTouchInsideView(ev,mContentView)) {
                    mIsInCanMoveView = true;
                } else {
                    mIsInCanMoveView = false;
                }
                if (initialDownX == -1 || initialDownY == -1 || isTouchInsideView(ev,mContentView) || isTouchInsideView(ev,mBack)) {
                    return super.dispatchTouchEvent(ev);
                }

                if (getScrollY() < headZoomHeight) {
                    return true;
                }
                if (!mFlingRunnable.isFinished()) {
                    mFlingRunnable.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == -1) {
                    return super.dispatchTouchEvent(ev);
                }
                mVelocityTracker.addMovement(ev);
                final float y = getMotionEventY(ev,mActivePointerId);
                final float x = getMotionEventX(ev,mActivePointerId);
                if (y == -1 || x == -1) {
                    return super.dispatchTouchEvent(ev);
                }


                final float yDiff = y - mLastY;
                final float xDiff = x - mLastX;
                mLastY = y;
                mLastX = x;

                // 到顶部的时候不做处理
                if (!mIsInCanMoveView || nowZoomHeight == headZoomHeight && yDiff < 0 || (nowZoomHeight == 0 && yDiff > 0) ) {
                    return super.dispatchTouchEvent(ev);
                }
                if (yDiff < 0) {
                    zoomHeadView((int) yDiff);
                } else {
                    if (mContentView.getScrollY() != 0 && nowZoomHeight == headZoomHeight) {
                        return super.dispatchTouchEvent(ev);
                    }
                    zoomHeadView((int) yDiff);
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mVelocityTracker.addMovement(ev);
                mVelocityTracker.computeCurrentVelocity(1000);
                float yVelovity = mVelocityTracker.getYVelocity(mActivePointerId);
                if (Math.abs(yVelovity) > mMinFlingVelocity) {
                    handleFling(yVelovity);
                }
                mActivePointerId = -1;
                mVelocityTracker.clear();
                mLastX = 0;
                mLastY = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void handleFling(float yVelocity) {
        if (yVelocity > MAX_Y_VELOCITY) {
            smoothScrollTo(getScrollY() + (headZoomHeight - nowZoomHeight),-1);
        } else if (-yVelocity > MAX_Y_VELOCITY) {
            smoothScrollTo(headZoomHeight +mContentView.getMeasuredHeight(),1);
        } else {
            int dy = (int) (Math.abs(yVelocity) / 50);
//            if (yVelocity > 0) {
//                if (getScrollY() < dy) {
//                    dy = getScrollY();
//                }
//            } else {
//                if (getScrollY() + dy > headZoomHeight) {
//                    dy = headZoomHeight - getScrollY();
//                }
//            }
            smoothScrollTo(dy, yVelocity > 0 ? -1 : 1);
        }
    }

    private boolean isTouchInsideView(MotionEvent ev, View view) {
        float viewY = ViewCompat.getY(view) - getScrollY();
        RectF rect = new RectF(ViewCompat.getX(view), viewY, ViewCompat.getX(view) + view.getWidth(), viewY + view.getHeight());
        float x = getMotionEventX(ev, mActivePointerId);
        float y = getMotionEventY(ev, mActivePointerId);
        return rect.contains(x, y);
    }

    private void smoothScrollTo(int dy , int direction) {
        mScroller.startScroll(0,getScrollY(),0,direction > 0 ? dy : -dy);
        invalidate();
    }

    private void zoomHeadView(int yDiff) {
        nowZoomHeight -= yDiff;
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
        int aphal = (int) ((1 - c) * 255);
        mUserName.setTextColor(Color.argb(aphal,255,255,255));
        mLvName.setTextColor(Color.argb(aphal,255,255,255));
        mLvName.setBackgroundColor(Color.argb(aphal,255,125,0));
    }

    /**
     * 获得坐标点
     */
    private float getMotionEventY(MotionEvent ev, int activePointerId) {
        final int index = MotionEventCompat.findPointerIndex(ev, activePointerId);
        if (index < 0) {
            return -1;
        }
        return MotionEventCompat.getY(ev, index);
    }

    private float getMotionEventX(MotionEvent ev, int activePointerId) {
        final int index = MotionEventCompat.findPointerIndex(ev, activePointerId);
        if (index < 0) {
            return -1;
        }
        return MotionEventCompat.getX(ev, index);
    }

    class FlingRunnable implements Runnable {
        long mDuration;
        boolean mIsFinished = true;
        float mScale;
        long mStartTime;
        int zoomHeight;
        int imageZoomHeight;
        int scrollY;
        int lastMoveY;

        FlingRunnable() {
        }

        public void abortAnimation() {
            mIsFinished = true;
        }

        public boolean isFinished() {
            return mIsFinished;
        }

        public void run() {
            int diffy;
            if ((!mIsFinished)) {
                float f1 = ((float) SystemClock.currentThreadTimeMillis() - (float) mStartTime) / (float) mDuration;
                diffy = (int) (zoomHeight * sInterpolator.getInterpolation(f1));
                int tempDiff = diffy - lastMoveY;
                lastMoveY = diffy;
                diffy = tempDiff;
                if (diffy < zoomHeight) {
                    if (diffy > 0) {
                        if (nowZoomHeight > diffy) {
                            diffy = 0;
                            zoomHeadView(diffy);
                        } else {
                            diffy -= nowZoomHeight;
                            zoomHeadView(nowZoomHeight);
                        }
                        if (diffy != 0) {
                            mContentView.scrollTo(0, diffy);
                        }
                    } else {
                        if (mContentView.getScrollY() > diffy) {
                            diffy = 0;
                            mContentView.scrollTo(0,diffy);
                        } else {
                            diffy += mContentView.getScrollY();
                            mContentView.scrollTo(0,mContentView.getScrollY());
                        }
                        if (diffy != 0) {
                            zoomHeadView(diffy);
                        }
                    }
                    post(this);
                    return;
                }
                mIsFinished = true;
            }
        }

        public void startAnimation(long paramLong,int ydiif) {
            mStartTime = SystemClock.currentThreadTimeMillis();
            mDuration = paramLong;
            mIsFinished = false;
            zoomHeight = ydiif;
            imageZoomHeight = nowZoomHeight;
            scrollY = mContentView.getScrollY();
            lastMoveY = 0;
            post(this);
        }
    }
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float paramAnonymousFloat) {
            float f = paramAnonymousFloat - 1.0F;
            return 1.0F + f * (f * (f * (f * f)));
        }
    };
}
