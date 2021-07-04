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

class UpComingCelebrantViewHolder(
    private val binding: CelebrantBirthdayItemBinding,
    private val onItemClick: (Int, UpComingCelebrant) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(upComingCelebrant: UpComingCelebrant) {
        binding.apply {
            celebrantImageIv.load(upComingCelebrant.celebrantImage){
                placeholder(R.drawable.birthday_user_avatar)
            }
            celebrantNameTv.text = upComingCelebrant.celebrantName
            celebrantDateTv.text = upComingCelebrant.celebrantDateOfBirth
            celebrantRemainingDayTv.text = upComingCelebrant.daysLeftToBirthday

            val nameFirstCharacter = upComingCelebrant.celebrantName?.first()
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