package com.example.birthyaay.shared

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Rect
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.birthyaay.R
import com.example.birthyaay.databinding.PictureItemBinding

class PicturesViewHolder(private val binding: PictureItemBinding,
                         private val onLongClick: (String) -> Unit):
    RecyclerView.ViewHolder(binding.root) {


    fun bind(picture: String){
        binding.apply {
            if (picture.isNotEmpty() && picture != "null") {
                imageIv.load(picture){
                    crossfade(true)
                    placeholder(R.drawable.birthday_user_avatar)
                }
                imageIv.setOnLongClickListener {
                    onLongClick.invoke(picture)
                    true
                }
//                imageIv.setOnTouchListener { v, event ->
//                    val rect: Rect = Rect(v.left, v.top, v.right, v.bottom)
//                    when (event.action) {
//                        MotionEvent.ACTION_DOWN ->{
//                            imageIv.setColorFilter(Color.argb(50,0,0,0))
//                        }
//                        MotionEvent.ACTION_UP ->{
//                            imageIv.setColorFilter(Color.argb(0,0,0,0))
//                        }
//                        MotionEvent.ACTION_MOVE ->{
//                            if (!rect.contains(
//                                    v.left + event.x.toInt(), v.top + event.y
//                                        .toInt()
//                                )
//                            ) {
//                                imageIv.setColorFilter(Color.argb(0, 0, 0, 0))
//                            }
//                        }
//                    }
//                    false
//                }
            }
        }
    }
}