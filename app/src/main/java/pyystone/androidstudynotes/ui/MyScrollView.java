package pyystone.androidstudynotes.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by pyysotne on 2016/1/21.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class MyScrollView extends ScrollView {

    private onScrollListener mOnScrollListener;

    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(onScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }


    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
//    private Handler handler = new Handler() {
//
//        public void handleMessage(android.os.Message msg) {
//            int scrollY = MyScrollView.this.getScrollY();
//
//            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
//            if(lastScrollY != scrollY){
//                lastScrollY = scrollY;
//                handler.sendMessageDelayed(handler.obtainMessage(), 20);
//            }
//            if(mOnScrollListener != null){
//                mOnScrollListener.onScroll(scrollY);
//            }
//
//        };
//
//    };

    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     * 这里的实现方式在滑动的时候会造成因为handler时间过短出现短时间的 lastScrollY == scrolly的情况
     * 时间过长会导致动画不是很流畅
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        if(mOnScrollListener != null){
//            mOnScrollListener.onScroll(lastScrollY = this.getScrollY());
//        }
//        switch(ev.getAction()){
//            case MotionEvent.ACTION_UP:
//                handler.sendMessageDelayed(handler.obtainMessage(), 20);
//                break;
//        }
        return super.onTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mOnScrollListener.onScroll(t);
    }

    /**
     *
     * 滚动的回调接口
     *
     * @author xiaanming
     *
     */
    public interface onScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         *              、
         */
        void onScroll(int scrollY);
    }
}
