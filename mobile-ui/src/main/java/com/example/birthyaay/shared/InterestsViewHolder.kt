package com.example.birthyaay.shared

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.birthyaay.databinding.InterestItemBinding
import com.example.birthyaay.models.Interest
import com.example.birthyaay.util.ColorSelector

class InterestsViewHolder(
    private val binding: InterestItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(interest: Interest) {
        binding.apply {
            contentTv.text = interest.title
            if (interest.title.trim().isNotEmpty()) {
                val character = interest.title.first()
                viewBg.setBackgroundColor(ColorSelector.selectColorByCharacter(character))
            }
        }
    }

}