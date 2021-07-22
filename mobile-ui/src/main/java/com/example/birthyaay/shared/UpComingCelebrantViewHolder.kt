package com.example.birthyaay.shared

import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.birthyaay.R
import com.example.birthyaay.databinding.CelebrantBirthdayItemBinding
import com.example.birthyaay.models.UpComingCelebrant
import com.example.birthyaay.util.ColorSelector
import com.example.navigation.navigation.model.Celebrant

class UpComingCelebrantViewHolder(
    private val binding: CelebrantBirthdayItemBinding,
    private val onItemClick: (Int, Celebrant) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(upComingCelebrant: Celebrant) {
        binding.apply {
            celebrantImageIv.load(upComingCelebrant.image?.first()){
                placeholder(R.drawable.birthday_user_avatar)
            }
            celebrantNameTv.text = upComingCelebrant.name
            celebrantDateTv.text = upComingCelebrant.dateOfBirth
            celebrantRemainingDayTv.text = upComingCelebrant.daysLeftToBirthday

            val nameFirstCharacter = upComingCelebrant.name?.first()
            celebrantInfoPipeCv.backgroundTintList =
                nameFirstCharacter?.let { ColorSelector.selectColorByCharacter(it) }?.let {
                    ColorStateList.valueOf(
                        it
                    )
                }

            root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION )
                    onItemClick(adapterPosition, upComingCelebrant)
            }

        }
    }


}