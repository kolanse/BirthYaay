package com.example.birthyaay.custom_views.textviews.shimmer

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.view.View

class Shimmer {
    val ANIMATION_DIRECTION_LTR = 0
    val ANIMATION_DIRECTION_RTL = 1

    private val DEFAULT_REPEAT_COUNT = ValueAnimator.INFINITE
    private val DEFAULT_DURATION: Long = 1000
    private val DEFAULT_START_DELAY: Long = 0
    private val DEFAULT_DIRECTION = ANIMATION_DIRECTION_LTR

    private var repeatCount = 0
    private var duration: Long = 0
    private var startDelay: Long = 0
    private var direction = 0
    private var animatorListener: Animator.AnimatorListener? = null

    private var animator: ObjectAnimator? = null

    init {
        repeatCount = DEFAULT_REPEAT_COUNT
        duration = DEFAULT_DURATION
        startDelay = DEFAULT_START_DELAY
        direction = DEFAULT_DIRECTION
    }

    fun getRepeatCount(): Int {
        return repeatCount
    }

    fun setRepeatCount(repeatCount: Int): Shimmer? {
        this.repeatCount = repeatCount
        return this
    }

    fun getDuration(): Long {
        return duration
    }

    fun setDuration(duration: Long): Shimmer? {
        this.duration = duration
        return this
    }

    fun getStartDelay(): Long {
        return startDelay
    }

    fun setStartDelay(startDelay: Long): Shimmer? {
        this.startDelay = startDelay
        return this
    }

    fun getDirection(): Int {
        return direction
    }

    fun setDirection(direction: Int): Shimmer? {
        require(!(direction != ANIMATION_DIRECTION_LTR && direction != ANIMATION_DIRECTION_RTL)) { "The animation direction must be either ANIMATION_DIRECTION_LTR or ANIMATION_DIRECTION_RTL" }
        this.direction = direction
        return this
    }

    fun getAnimatorListener(): Animator.AnimatorListener? {
        return animatorListener
    }

    fun setAnimatorListener(animatorListener: Animator.AnimatorListener?): Shimmer? {
        this.animatorListener = animatorListener
        return this
    }

    fun <V> start(shimmerView: V) where V : View?, V : ShimmerViewBase? {
        if (isAnimating()) {
            return
        }
        val animate = Runnable {
            shimmerView!!.setShimmering(true)
            var fromX = 0f
            var toX: Float = shimmerView.getWidth().toFloat()
            if (direction == ANIMATION_DIRECTION_RTL) {
                fromX = shimmerView.getWidth().toFloat()
                toX = 0f
            }
            animator = ObjectAnimator.ofFloat(shimmerView, "gradientX", fromX, toX)
            animator?.setRepeatCount(repeatCount)
            animator?.setDuration(duration)
            animator?.setStartDelay(startDelay)
            animator?.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    shimmerView.setShimmering(false)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        shimmerView.postInvalidate()
                    } else {
                        shimmerView.postInvalidateOnAnimation()
                    }
                    animator = null
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            if (animatorListener != null) {
                animator?.addListener(animatorListener)
            }
            animator?.start()
        }
        if (!shimmerView!!.isSetUp()) {
            shimmerView.setAnimationSetupCallback(object : ShimmerHelper.AnimationSetupCallback {
                override fun onSetupAnimation(target: View?) {
                    animate.run()
                }
            })
        } else {
            animate.run()
        }
    }

    fun cancel() {
        if (animator != null) {
            animator!!.cancel()
        }
    }

    fun isAnimating(): Boolean {
        return animator != null && animator!!.isRunning
    }
}
