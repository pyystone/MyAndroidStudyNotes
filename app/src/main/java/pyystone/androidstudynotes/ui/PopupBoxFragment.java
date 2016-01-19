package pyystone.androidstudynotes.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import pyystone.androidstudynotes.R;


/**
 * Created by pyysotne on 2016/01/18/0021.
 * email: pyystone@163.com
 * QQ: 862429936
 * 消息弹出提示框
 */
public class PopupBoxFragment extends Fragment {

    private int mMoveYDiff = 200;
    private View mPopUpView;
    private View mRootView;
    private TextView mMessage;

    public PopupBoxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PopupBoxFragment.
     */
    public static PopupBoxFragment newInstance() {
        PopupBoxFragment fragment = new PopupBoxFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_popup_box, null);
        initUI();
        return mRootView;
    }


    public void showPopUpBox() {
        if (mRootView != null) {
            resetView();
            startAnimation();
        }
    }

    private void resetView() {
        mPopUpView.setTranslationY(mMoveYDiff);
        mPopUpView.setAlpha(0f);
        mRootView.setAlpha(1f);
    }

    private void initUI() {
        mPopUpView = mRootView.findViewById(R.id.popUpView);
        mMessage = (TextView) mRootView.findViewById(R.id.tvMessage);
        mMessage.setText(Html.fromHtml("完成任务，金币<font color=\"#ff9602\">+1</font>"));
        showPopUpBox();
    }

    private void startAnimation() {
        mPopUpView.animate().setDuration(400).translationYBy(-mMoveYDiff).alphaBy(1f).setInterpolator(new DecelerateInterpolator(0.8f));
        mRootView.animate().setStartDelay(1600).setDuration(200).alpha(0f);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
