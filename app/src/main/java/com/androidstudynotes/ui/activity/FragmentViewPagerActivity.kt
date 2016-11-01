package com.androidstudynotes.ui.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.androidstudynotes.R
import com.androidstudynotes.ui.fragmentpager.MyFragmentViewPagerAdapter
import java.util.*

class FragmentViewPagerActivity : AppCompatActivity() {

    var mViewPager : ViewPager? = null
    var mAdapter = MyFragmentViewPagerAdapter(supportFragmentManager)
    var titles = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_view_pager)
        initData()
        initUI()
    }

    private fun initData() {
        titles.add("hello")
        titles.add("world")
    }

    private fun initUI() {
        mViewPager = findViewById(R.id.viewpager) as ViewPager
        mAdapter.setData(titles)
        mViewPager?.adapter = mAdapter
    }
}
