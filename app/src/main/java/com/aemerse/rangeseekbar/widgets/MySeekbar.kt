package com.aemerse.rangeseekbar.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.aemerse.rangeseekbar.R

class MySeekbar : CrystalSeekbar {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr)

    override fun getMinValue(typedArray: TypedArray): Float {
        return 5f
    }

    override fun getMaxValue(typedArray: TypedArray): Float {
        return 90f
    }

    override fun getMinStartValue(typedArray: TypedArray): Float {
        return 20f
    }

    override fun getBarColor(typedArray: TypedArray): Int {
        return Color.parseColor("#A0E3F7")
    }

    override fun getBarHighlightColor(typedArray: TypedArray): Int {
        return Color.parseColor("#53C9ED")
    }

    override fun getThumbColor(typedArray: TypedArray): Int {
        return Color.parseColor("#058EB7")
    }

    override fun getThumbColorPressed(typedArray: TypedArray): Int {
        return Color.parseColor("#046887")
    }

    override fun getThumbDrawable(typedArray: TypedArray): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.thumb)
    }

    override fun getThumbDrawablePressed(typedArray: TypedArray): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.thumb_pressed)
    }
}