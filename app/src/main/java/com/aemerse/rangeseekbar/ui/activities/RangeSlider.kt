package com.aemerse.rangeseekbar.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.aemerse.rangeseekbar.R
import com.aemerse.rangeseekbar.adapters.RangeSeekbarPagerAdapter
import com.aemerse.rangeseekbar.ui.fragments.RangeSeekbar
import com.aemerse.rangeseekbar.ui.fragments.Seekbar
import com.google.android.material.tabs.TabLayout
import java.util.*

class RangeSlider : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.range_slider)
        init()
    }

    private fun <T> getView(resId: Int): T {
        return findViewById<View>(resId) as T
    }

    private fun init() {
        val tabLayout = getView<TabLayout>(R.id.tlSeekbar)
        val viewPager = getView<ViewPager>(R.id.vpSeekbar)

        // set fragments list
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(Seekbar())
        fragments.add(RangeSeekbar())

        // set tabs title
        val tabTitles: MutableList<String> = ArrayList()
        tabTitles.add("Seekbar")
        tabTitles.add("Range Seekbar")


        // create view pager adapter
        val adapter = RangeSeekbarPagerAdapter(supportFragmentManager, fragments, tabTitles)

        // set adapter to pager
        viewPager.adapter = adapter

        // set view pager to tab layout
        tabLayout.setupWithViewPager(viewPager)
    }
}