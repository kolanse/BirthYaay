package com.example.birthyaay.custom_views.textviews

interface ShimmerViewBase {
    fun getGradientX(): Float
    fun setGradientX(gradientX: Float)
    fun isShimmering(): Boolean
    fun setShimmering(isShimmering: Boolean)
    fun isSetUp(): Boolean
    fun setAnimationSetupCallback(callback: ShimmerHelper.AnimationSetupCallback?)
    fun getPrimaryColor(): Int
    fun setPrimaryColor(primaryColor: Int)
    fun getReflectionColor(): Int
    fun setReflectionColor(reflectionColor: Int)
}
