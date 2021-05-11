package com.example.birthyaay.custom_views.textviews.shimmer

import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import android.view.View

class ShimmerHelper(view: View, paint: Paint, attributeSet: AttributeSet?) {
    interface AnimationSetupCallback {
        fun onSetupAnimation(target: View?)
    }

    private val view: View
    private val paint: Paint

    // center position of the gradient
    private var gradientX = 0f

    // shader applied on the text view
    // only null until the first global layout
    private var linearGradient: LinearGradient? = null

    // shader's local matrix
    // never null
    private var linearGradientMatrix: Matrix? = null
    private var primaryColor = 0

    // shimmer reflection color
    private var reflectionColor = 0

    // true when animating
    var isShimmering = false

    // true after first global layout
    var isSetUp = false
        private set

    // callback called after first global layout
    private var callback: AnimationSetupCallback? = null
    fun getGradientX(): Float {
        return gradientX
    }

    fun setGradientX(gradientX: Float) {
        this.gradientX = gradientX
        view.invalidate()
    }

    fun setAnimationSetupCallback(callback: AnimationSetupCallback?) {
        this.callback = callback
    }

    fun getPrimaryColor(): Int {
        return primaryColor
    }

    fun setPrimaryColor(primaryColor: Int) {
        this.primaryColor = primaryColor
        if (isSetUp) {
            resetLinearGradient()
        }
    }

    fun getReflectionColor(): Int {
        return reflectionColor
    }

    fun setReflectionColor(reflectionColor: Int) {
        this.reflectionColor = reflectionColor
        if (isSetUp) {
            resetLinearGradient()
        }
    }

    private fun init(attributeSet: AttributeSet?) {
        reflectionColor = DEFAULT_REFLECTION_COLOR

        linearGradientMatrix = Matrix()
    }

    private fun resetLinearGradient() {

        // our gradient is a simple linear gradient from textColor to reflectionColor. its axis is at the center
        // when it's outside of the view, the outer color (textColor) will be repeated (Shader.TileMode.CLAMP)
        // initially, the linear gradient is positioned on the left side of the view
        linearGradient = LinearGradient(
            -view.width.toFloat(), 0F, 0F, 0F,
            intArrayOf(
                primaryColor,
                reflectionColor,
                primaryColor
            ),
            floatArrayOf(
                0f,
                0.5f, 1f
            ),
            Shader.TileMode.CLAMP
        )
        paint.shader = linearGradient
    }

    fun onSizeChanged() {
        resetLinearGradient()
        if (!isSetUp) {
            isSetUp = true
            if (callback != null) {
                Log.d("SHIMMERVIEWHELPER", "inside callback viewhelper shimmer")
                callback!!.onSetupAnimation(view)
            }
        }
    }

    /**
     * content of the wrapping view's onDraw(Canvas)
     * MUST BE CALLED BEFORE SUPER STATEMENT
     */
    fun onDraw() {

        // only draw the shader gradient over the text while animating
        if (isShimmering) {
            Log.d("SHIMMERVIEWHELPER", "inside viewhelper shimmer")
            // first onDraw() when shimmering
            if (paint.shader == null) {
                Log.d("SHIMMERVIEWHELPER", "inside paint shader")
                paint.shader = linearGradient
            }

            // translate the shader local matrix
            linearGradientMatrix?.setTranslate(2 * gradientX, 0F)

            // this is required in order to invalidate the shader's position
            linearGradient!!.setLocalMatrix(linearGradientMatrix)
        } else {
            // we're not animating, remove the shader from the paint
            paint.shader = null
        }
    }

    companion object {
        private const val DEFAULT_REFLECTION_COLOR = -0x1
    }

    init {
        this.view = view
        this.paint = paint
        init(attributeSet)
    }
}
