package com.example.birthyaay.shared

import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.birthyaay.databinding.CelebrantGroupHeaderItemBinding
import com.example.birthyaay.models.UpComingCelebrant
import com.example.birthyaay.util.ColorSelector
import com.example.navigation.navigation.model.Celebrant

class GroupAlphabetViewHolder(
    private val binding: CelebrantGroupHeaderItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(upComingCelebrant: Celebrant) {
        binding.apply {
            val nameFirstCharacter = upComingCelebrant.name?.first()
            val drawable = nameFirstCharacter?.let { ColorSelector.selectColorByCharacter(it) }?.let {
                TextDrawable
                    .builder()
                    .buildRound(nameFirstCharacter.toString(),
                        it
                    )
            }
            celebrantGroupNameIv.setImageDrawable(drawable)
        }
    }

}