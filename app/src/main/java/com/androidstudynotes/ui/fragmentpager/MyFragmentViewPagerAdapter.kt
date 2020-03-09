package com.androidstudynotes.ui.fragmentpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import java.util.*

/**
 * Created by pyystone on 16/11/1.
 * email: pyystone@163.com
 * QQ: 862429936
 * github: https://github.com/pyystone
 */
class MyFragmentViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    var mData = ArrayList<page>()

    fun setData(datas : ArrayList<String>) {
        datas.forEach { it ->
            addPage(it,FragmentPager1())
            addPage(it+"2",FragmentPager2())
            addPage(it+"3",FragmentPager3())
            addPage(it+"4",FragmentPager4())
        }
    }

    fun addPage(title : String, fragment: Fragment) {
        mData.add(page(title,fragment))
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    /**
     * 对可能耗时的view 进行缓存
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        if (getItem(position) is FragmentPager4) {
            return
        }
        super.destroyItem(container, position, `object`)
    }



    override fun getItem(position: Int): Fragment {
        return mData[position].fragment!!
    }

    override fun getCount(): Int {
        return mData.size
    }

    class page {
        constructor(title: String,fragment: Fragment) {
            this.title = title
            this.fragment = fragment
        }
        var fragment : Fragment? = null
        var title = ""
    }

}