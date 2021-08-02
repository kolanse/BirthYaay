package com.example.birthyaay.shared

import android.graphics.drawable.GradientDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.R
import com.example.birthyaay.databinding.GiftItemBinding
import com.example.birthyaay.models.Gift
import com.example.birthyaay.util.ColorSelector

class GiftsViewHolder(private val binding: GiftItemBinding, private val onLongClick: (Gift) -> Unit ): RecyclerView.ViewHolder(binding.root) {

    fun bind(gift: Gift) {
        binding.apply {
            giftTv.text = gift.title
            if (gift.title.trim().isNotEmpty()) {
                val firstCharacter = gift.title.first()
                val drawable: GradientDrawable = root.background as GradientDrawable
                drawable.setStroke(2, ColorSelector.selectColorByCharacter(firstCharacter))
            }
            root.setOnLongClickListener {
                onLongClick.invoke(gift)
                true
            }
        }
    }
}