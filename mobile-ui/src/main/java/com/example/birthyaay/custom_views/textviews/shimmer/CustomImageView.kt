package com.example.birthyaay.custom_views.textviews.shimmer

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView

class CustomImageView(context: Context, attributeSet: AttributeSet): AppCompatImageView (context, attributeSet){

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        val rect = Rect(this.left, this.top, this.right, this.bottom)
        when (event?.action) {
            MotionEvent.ACTION_DOWN ->{
                this.setColorFilter(Color.argb(70,0,0,0))
                return true
            }
            MotionEvent.ACTION_UP ->{
                this.setColorFilter(Color.argb(0,0,0,0))
                performClick()
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                if (!rect.contains(
                        this.left + event.x.toInt(), this.top + event.y
                            .toInt()
                    )
                ) {
                    this.setColorFilter(Color.argb(0, 0, 0, 0))
                }
                return true
            }
        }

        return false
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

}