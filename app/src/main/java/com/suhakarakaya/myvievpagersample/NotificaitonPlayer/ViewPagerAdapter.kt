package com.suhakarakaya.myvievpagersample.NotificaitonPlayer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    var manager: FragmentManager
) : FragmentPagerAdapter(manager) {

    var fragments = ArrayList<Fragment>()
    var titles = ArrayList<String>()

    fun addFragments(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }


    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }
}