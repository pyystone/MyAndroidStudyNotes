package com.androidstudynotes.ui.fragmentpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidstudynotes.R


/**
 * Created by pyystone on 16/11/1.
 * email: pyystone@163.com
 * QQ: 862429936
 * github: https://github.com/pyystone
 *
 * 自我控制刷新fragment
 */
class FragmentPager2 : Fragment() {

    var mRootView : View? = null
    var mCount : TextView? = null
    var mTitle : TextView? = null
    var count = 0
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (count > 0) {
            return mRootView
        }
        val view = inflater?.inflate(R.layout.fragment_pager,container,false)
        mRootView = view
        initUI()
        return view
    }

    private fun initUI() {
        mCount = mRootView?.findViewById(R.id.refershCount) as TextView
        mTitle = mRootView?.findViewById(R.id.title) as TextView

        mTitle?.text = "pager2"

    }


    override fun onResume() {
        super.onResume()
        mCount?.text = count++.toString()
    }
}