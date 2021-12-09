package com.aemerse.rangeseekbar.widgets

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.util.AttributeSet
import android.view.animation.OvershootInterpolator
import com.aemerse.rangeseekbar.R

class BubbleThumbRangeSeekbar : CrystalRangeSeekbar {
    //////////////////////////////////////////
    // PRIVATE CONSTANTS
    //////////////////////////////////////////
    //private static final float BUBBLE_WITH   = 200f;
    //private static final float BUBBLE_HEIGHT = 200f;
    //////////////////////////////////////////
    // PRIVATE VAR
    //////////////////////////////////////////
    private var animate = false
    private var isPressedLeftThumb = false
    private var isPressedRightThumb = false
    private var thumbPressedRect: BubbleRect? = null

    //////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr)

    //////////////////////////////////////////
    // INITIALIZATION
    //////////////////////////////////////////
    override fun init() {
        thumbPressedRect = BubbleRect()
        super.init()
    }

    //////////////////////////////////////////
    // OVERRIDE METHODS
    //////////////////////////////////////////
    override fun touchDown(x: Float, y: Float) {
        super.touchDown(x, y)
        animate = true
        when {
            Thumb.MIN == pressedThumb -> {
                isPressedLeftThumb = true
                startAnimationUp(Thumb.MIN)
            }
            Thumb.MAX == pressedThumb -> {
                isPressedRightThumb = true
                startAnimationUp(Thumb.MAX)
            }
        }
    }

    override fun touchUp(x: Float, y: Float) {
        super.touchUp(x, y)
        animate = true
        if (Thumb.MIN == pressedThumb) {
            startAnimationDown(Thumb.MIN)
        } else {
            startAnimationDown(Thumb.MAX)
        }
    }

    override fun drawLeftThumbWithColor(canvas: Canvas, paint: Paint?, rect: RectF?) {
        when {
            isPressedLeftThumb -> {
                when {
                    !animate -> {
                        rect!!.left = rect.left - (bubbleWith / 2 - getThumbWidth() / 2)
                        rect.right = rect.left + bubbleWith
                        rect.top = leftThumbRect!!.top - (bubbleHeight / 2 - getThumbHeight() / 2)
                        rect.bottom = leftThumbRect!!.bottom + (bubbleHeight / 2 - getThumbHeight() / 2)
                    }
                    else -> {
                        rect!!.left = thumbPressedRect!!.left
                        rect.right = thumbPressedRect!!.right
                        rect.top = thumbPressedRect!!.top
                        rect.bottom = thumbPressedRect!!.bottom
                    }
                }
                canvas.drawOval(rect, paint!!)
            }
            else -> {
                canvas.drawOval(rect!!, paint!!)
            }
        }
    }

    override fun drawRightThumbWithColor(canvas: Canvas, paint: Paint?, rect: RectF?) {
        if (isPressedRightThumb) {
            if (!animate) {
                rect!!.left = rect.left - (bubbleWith / 2 - getThumbWidth() / 2)
                rect.right = rect.left + bubbleWith
                rect.top = rightThumbRect!!.top - (bubbleHeight / 2 - getThumbHeight() / 2)
                rect.bottom = rightThumbRect!!.bottom + (bubbleHeight / 2 - getThumbHeight() / 2)
            } else {
                rect!!.left = thumbPressedRect!!.left
                rect.right = thumbPressedRect!!.right
                rect.top = thumbPressedRect!!.top
                rect.bottom = thumbPressedRect!!.bottom
            }
            canvas.drawOval(rect, paint!!)
        } else {
            canvas.drawOval(rect!!, paint!!)
        }
    }

    override fun drawLeftThumbWithImage(
        canvas: Canvas,
        paint: Paint?,
        rect: RectF?,
        image: Bitmap?
    ) {
        var image = image
        if (isPressedLeftThumb) {
            when {
                !animate -> {
                    image = resizeImage(bubbleWith.toInt(), bubbleHeight.toInt(), image!!)
                    rect!!.top = leftThumbRect!!.top - (bubbleHeight / 2 - getThumbHeight() / 2)
                    rect.left = rect.left - (bubbleWith / 2 - getThumbWidth() / 2)
                }
                else -> {
                    image = resizeImage(thumbPressedRect!!.imageWith.toInt(),
                        thumbPressedRect!!.imageHeight.toInt(),
                        image!!)
                    rect!!.top = thumbPressedRect!!.top
                    rect.left = thumbPressedRect!!.left
                }
            }
            canvas.drawBitmap(image, rect.left, rect.top, paint)
        } else {
            canvas.drawBitmap(image!!, rect!!.left, rect.top, paint)
        }
    }

    override fun drawRightThumbWithImage(
        canvas: Canvas,
        paint: Paint?,
        rect: RectF?,
        image: Bitmap?
    ) {
        var image = image
        if (isPressedRightThumb) {
            if (!animate) {
                image = resizeImage(bubbleWith.toInt(), bubbleHeight.toInt(), image!!)
                rect!!.top = rightThumbRect!!.top - (bubbleHeight / 2 - getThumbHeight() / 2)
                rect.left = rect.left - (bubbleWith / 2 - getThumbWidth() / 2)
            } else {
                image = resizeImage(thumbPressedRect!!.imageWith.toInt(),
                    thumbPressedRect!!.imageHeight.toInt(),
                    image!!)
                rect!!.top = thumbPressedRect!!.top
                rect.left = thumbPressedRect!!.left
            }
            canvas.drawBitmap(image, rect.left, rect.top, paint)
        } else {
            canvas.drawBitmap(image!!, rect!!.left, rect.top, paint)
        }
    }

    //////////////////////////////////////////
    // PROTECTED METHODS
    //////////////////////////////////////////
    private val bubbleWith: Float get() = resources.getDimension(R.dimen.bubble_thumb_width)
    private val bubbleHeight: Float get() = resources.getDimension(R.dimen.bubble_thumb_height)

    private fun startAnimationUp(thumb: Thumb) {
        val toRect = BubbleRect()

        // left thumb
        val fromRect: RectF? = when (Thumb.MIN) {
            thumb -> {
                leftThumbRect
            }
            else -> {
                rightThumbRect
            }
        }
        toRect.left = fromRect!!.left - (bubbleWith / 2 - getThumbWidth() / 2)
        toRect.right = toRect.left + bubbleWith
        toRect.top = fromRect.top - (bubbleHeight / 2 - getThumbHeight() / 2)
        toRect.bottom = fromRect.bottom + (bubbleHeight / 2 - getThumbHeight() / 2)
        val leftValueHolder = PropertyValuesHolder.ofFloat("left", fromRect.left, toRect.left)
        val rightValueHolder = PropertyValuesHolder.ofFloat("right", fromRect.right, toRect.right)
        val topValueHolder = PropertyValuesHolder.ofFloat("top", fromRect.top, toRect.top)
        val bottomValueHolder =
            PropertyValuesHolder.ofFloat("bottom", fromRect.bottom, toRect.bottom)
        val imageWithValueHolder = PropertyValuesHolder.ofFloat("width", getThumbWidth(), bubbleWith)
        val imageHeightValueHolder =
            PropertyValuesHolder.ofFloat("height", getThumbHeight(), bubbleHeight)
        val animation = ValueAnimator.ofPropertyValuesHolder(leftValueHolder,
            rightValueHolder,
            topValueHolder,
            bottomValueHolder,
            imageWithValueHolder,
            imageHeightValueHolder)
        animation.duration = 200
        animation.interpolator = OvershootInterpolator(5f)
        animation.addUpdateListener { animation ->
            thumbPressedRect!!.left = animation.getAnimatedValue("left") as Float
            thumbPressedRect!!.right = animation.getAnimatedValue("right") as Float
            thumbPressedRect!!.top = animation.getAnimatedValue("top") as Float
            thumbPressedRect!!.bottom = animation.getAnimatedValue("bottom") as Float
            thumbPressedRect!!.imageWith = animation.getAnimatedValue("width") as Float
            thumbPressedRect!!.imageHeight = animation.getAnimatedValue("height") as Float
            invalidate()
        }
        animation.start()
        Handler().postDelayed({ animate = false }, 200)
    }

    private fun startAnimationDown(thumb: Thumb) {
        val toRect = RectF()

        // left thumb
        val fromRect: RectF? = when (Thumb.MIN) {
            thumb -> {
                leftThumbRect
            }
            else -> {
                rightThumbRect
            }
        }
        toRect.left = fromRect!!.left + (bubbleWith / 2 - getThumbWidth() / 2)
        toRect.right = toRect.left + getThumbWidth()
        toRect.top = 0f
        toRect.bottom = getThumbHeight()
        val leftValueHolder = PropertyValuesHolder.ofFloat("left", fromRect.left, toRect.left)
        val rightValueHolder = PropertyValuesHolder.ofFloat("right", fromRect.right, toRect.right)
        val topValueHolder = PropertyValuesHolder.ofFloat("top", fromRect.top, toRect.top)
        val bottomValueHolder =
            PropertyValuesHolder.ofFloat("bottom", fromRect.bottom, toRect.bottom)
        val imageWithValueHolder = PropertyValuesHolder.ofFloat("width", bubbleWith, getThumbWidth())
        val imageHeightValueHolder =
            PropertyValuesHolder.ofFloat("height", bubbleHeight, getThumbHeight())
        val animation = ValueAnimator.ofPropertyValuesHolder(leftValueHolder,
            rightValueHolder,
            topValueHolder,
            bottomValueHolder,
            imageWithValueHolder,
            imageHeightValueHolder)
        animation.duration = 300
        animation.interpolator = OvershootInterpolator(3f)
        animation.addUpdateListener { animation ->
            thumbPressedRect!!.left = animation.getAnimatedValue("left") as Float
            thumbPressedRect!!.right = animation.getAnimatedValue("right") as Float
            thumbPressedRect!!.top = animation.getAnimatedValue("top") as Float
            thumbPressedRect!!.bottom = animation.getAnimatedValue("bottom") as Float
            thumbPressedRect!!.imageWith = animation.getAnimatedValue("width") as Float
            thumbPressedRect!!.imageHeight = animation.getAnimatedValue("height") as Float
            invalidate()
        }
        animation.start()
        Handler().postDelayed({
            animate = false
            isPressedLeftThumb = false
            isPressedRightThumb = false
        }, 300)
    }

    //////////////////////////////////////////
    // PUBLIC METHODS
    //////////////////////////////////////////
    //////////////////////////////////////////
    // PRIVATE METHODS
    //////////////////////////////////////////
    private fun resizeImage(iconWidth: Int, iconHeight: Int, bmp: Bitmap): Bitmap {
        val width = bmp.width
        val height = bmp.height

        // calculate the scale
        val scaleWidth = iconWidth.toFloat() / width
        val scaleHeight = iconHeight.toFloat() / height

        // create a matrix for the manipulation
        val matrix = Matrix()
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight)

        // if you want to rotate the Bitmap
        // matrix.postRotate(45);

        // recreate the new Bitmap

        // make a Drawable from Bitmap to allow to set the Bitmap
        // to the ImageView, ImageButton or what ever
        return Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true)
    }

    //////////////////////////////////////////
    // PRIVATE CLASS
    //////////////////////////////////////////
    private inner class BubbleRect {
        var left = 0f
        var right = 0f
        var top = 0f
        var bottom = 0f
        var imageWith = 0f
        var imageHeight = 0f
    }
}