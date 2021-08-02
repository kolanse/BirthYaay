package com.example.birthyaay.custom_views.textviews.shimmer

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.birthyaay.R

class CustomGiftLayout(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private val customBackground: Drawable? = this.background

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                this.setBackgroundResource(R.drawable.gift_bg_pressed_stress)
                return true
            }
            MotionEvent.ACTION_UP -> {
                this.background = customBackground
                performClick()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                this.background = customBackground
                return true
            }
        }

        return false
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

}