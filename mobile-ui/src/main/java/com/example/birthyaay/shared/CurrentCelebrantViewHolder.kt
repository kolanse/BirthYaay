package com.example.birthyaay.shared

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.birthyaay.R
import com.example.birthyaay.databinding.CurrentCelebrantItemBinding
import com.example.birthyaay.models.CurrentCelebrant


class CurrentCelebrantViewHolder(
    private val binding: CurrentCelebrantItemBinding,
    private val onShareClick: (CurrentCelebrant) -> Unit,
    private val onMessageClick: (CurrentCelebrant) -> Unit,
    private val onCallClick: (CurrentCelebrant) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(currentCelebrant: CurrentCelebrant) {
        binding.apply {
            currentCelebrantImageIv.load(currentCelebrant.image){
                crossfade(true)
                placeholder(R.drawable.birthday_user_avatar)
            }
            currentCelebrantNameTv.text =
                root.context.getString(R.string.celebratory_prompt_with_name, currentCelebrant.name)
            currentCelebrantShareIv.setOnClickListener {
                onShareClick(currentCelebrant)
            }
            currentCelebrantMessageIv.setOnClickListener {
                onMessageClick(currentCelebrant)
            }
            currentCelebrantCallIv.setOnClickListener {
                onCallClick(currentCelebrant)
            }
        }
    }

}