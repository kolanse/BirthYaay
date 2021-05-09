package com.example.birthyaay.custom_views.textviews.shimmer

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.example.birthyaay.R

/**
 * Creates a Textview that has a shimmering effect
 */
class ShimmerTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
) : AppCompatTextView(context, attributeSet, defStyle), ShimmerViewBase {

    var shimmerViewHelper: ShimmerHelper

    var shimmer = Shimmer()

    init {

        shimmerViewHelper = ShimmerHelper(this, paint, null)
        shimmerViewHelper!!.setPrimaryColor(currentTextColor)
        setReflectionColor(R.color.teal_200)
        shimmer.start(this)
    }

    override fun getGradientX(): Float {
        return shimmerViewHelper.getGradientX()
    }

    override fun setGradientX(gradientX: Float) {
        shimmerViewHelper.setGradientX(gradientX)
    }

    override fun isShimmering(): Boolean {
        return shimmerViewHelper.isShimmering
    }

    override fun setShimmering(isShimmering: Boolean) {
        shimmerViewHelper.isShimmering = isShimmering
    }

    override fun isSetUp(): Boolean {
        return shimmerViewHelper.isSetUp
    }

    override fun setAnimationSetupCallback(callback: ShimmerHelper.AnimationSetupCallback?) {
        shimmerViewHelper.setAnimationSetupCallback(callback)
    }

    override fun getPrimaryColor(): Int {
        return shimmerViewHelper.getPrimaryColor()
    }

    override fun setPrimaryColor(primaryColor: Int) {
        shimmerViewHelper.setPrimaryColor(primaryColor)
    }

    override fun getReflectionColor(): Int {
        return shimmerViewHelper.getReflectionColor()
    }

    override fun setReflectionColor(reflectionColor: Int) {
        shimmerViewHelper.setReflectionColor(reflectionColor)
    }

    override fun setTextColor(color: Int) {
        super.setTextColor(color)
        if (shimmerViewHelper != null) {
            shimmerViewHelper.setPrimaryColor(currentTextColor)
        }
    }

    override fun setTextColor(colors: ColorStateList?) {
        super.setTextColor(colors)
        if (shimmerViewHelper != null) {
            shimmerViewHelper.setPrimaryColor(currentTextColor)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (shimmerViewHelper != null) {
            shimmerViewHelper.onSizeChanged()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (shimmerViewHelper != null) {
            Log.d("SHIMMERTEXTVIEW", "i should call")
            shimmerViewHelper.onDraw()
        }
        super.onDraw(canvas)
    }
}
