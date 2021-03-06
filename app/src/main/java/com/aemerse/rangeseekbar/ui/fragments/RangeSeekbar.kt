package com.aemerse.rangeseekbar.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.aemerse.rangeseekbar.R
import com.aemerse.rangeseekbar.interfaces.OnRangeSeekbarChangeListener
import com.aemerse.rangeseekbar.interfaces.OnRangeSeekbarFinalValueListener
import com.aemerse.rangeseekbar.widgets.BubbleThumbRangeSeekbar
import com.aemerse.rangeseekbar.widgets.CrystalRangeSeekbar
import com.aemerse.rangeseekbar.widgets.MyRangeSeekbar

class RangeSeekbar : Fragment() {
    protected var rootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView != null) {
            val parent = rootView!!.parent as ViewGroup
            parent?.removeView(rootView)
        }
        try {
            rootView = inflater.inflate(R.layout.range_seekbar, container, false)
        } catch (e: InflateException) {
            e.printStackTrace()
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        setRangeSeekbar1()
        setRangeSeekbar2()
        setRangeSeekbar3()
        setRangeSeekbar4()
        setRangeSeekbar5()
        setRangeSeekbar6()
        setRangeSeekbar7()
        setRangeSeekbar8()
    }

    private fun setRangeSeekbar1() {

        // get seekbar from view
        val rangeSeekbar = rootView!!.findViewById<View>(R.id.rangeSeekbar1) as CrystalRangeSeekbar

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin1) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax1) as TextView

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })

        // set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(object : OnRangeSeekbarFinalValueListener {
            override fun finalValue(minValue: Number?, maxValue: Number?) {
                Log.d("CRS=>", minValue.toString() + " : " + maxValue.toString())
            }
        })
        Handler().postDelayed({
            rangeSeekbar.setMinValue(6f).setMaxValue(30f).setMinStartValue(7f).setMaxStartValue(10f)
                .apply()
        }, 5000)
    }

    private fun setRangeSeekbar2() {

        // get seekbar from view
        val rangeSeekbar =
            rootView!!.findViewById<View>(R.id.rangeSeekbar2) as BubbleThumbRangeSeekbar

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin2) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax2) as TextView

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })
    }

    private fun setRangeSeekbar3() {

        // get seekbar from view
        val rangeSeekbar = rootView!!.findViewById<View>(R.id.rangeSeekbar3) as CrystalRangeSeekbar

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin3) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax3) as TextView

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })
    }

    private fun setRangeSeekbar4() {

        // get seekbar from view
        val rangeSeekbar = rootView!!.findViewById<View>(R.id.rangeSeekbar4) as CrystalRangeSeekbar

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin4) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax4) as TextView

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })
    }

    private fun setRangeSeekbar5() {

        // get seekbar from view
        val rangeSeekbar = rootView!!.findViewById<View>(R.id.rangeSeekbar5) as CrystalRangeSeekbar

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin5) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax5) as TextView

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })
    }

    private fun setRangeSeekbar6() {

        // get seekbar from view
        val rangeSeekbar = rootView!!.findViewById<View>(R.id.rangeSeekbar6) as CrystalRangeSeekbar

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin6) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax6) as TextView

        // set properties
        rangeSeekbar
            .setCornerRadius(10f)
            .setBarColor(Color.parseColor("#93F9B5"))
            .setBarHighlightColor(Color.parseColor("#16E059"))
            .setMinValue(400f)
            .setMaxValue(800f)
            .setSteps(100f)
            .setLeftThumbDrawable(R.drawable.thumb_android)
            .setLeftThumbHighlightDrawable(R.drawable.thumb_android_pressed)
            .setRightThumbDrawable(R.drawable.thumb_android)
            .setRightThumbHighlightDrawable(R.drawable.thumb_android_pressed)
            .setDataType(CrystalRangeSeekbar.DataType.INTEGER)
            .apply()

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })
    }

    private fun setRangeSeekbar7() {

        // get seekbar from view
        val rangeSeekbar = CrystalRangeSeekbar(requireActivity())

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin7) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax7) as TextView

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })

        // get range seekbar container
        val container = rootView!!.findViewById<View>(R.id.contRangeSeekbar7) as RelativeLayout
        container.addView(rangeSeekbar)
    }

    private fun setRangeSeekbar8() {

        // get seekbar from view
        val rangeSeekbar = rootView!!.findViewById<View>(R.id.rangeSeekbar8) as MyRangeSeekbar

        // get min and max text view
        val tvMin = rootView!!.findViewById<View>(R.id.textMin8) as TextView
        val tvMax = rootView!!.findViewById<View>(R.id.textMax8) as TextView

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(object : OnRangeSeekbarChangeListener {
            override fun valueChanged(minValue: Number?, maxValue: Number?) {
                tvMin.text = minValue.toString()
                tvMax.text = maxValue.toString()
            }
        })
    }
}