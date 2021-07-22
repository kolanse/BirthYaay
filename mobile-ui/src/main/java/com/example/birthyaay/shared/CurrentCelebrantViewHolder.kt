package com.example.birthyaay.shared

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.birthyaay.R
import com.example.birthyaay.databinding.CurrentCelebrantItemBinding
import com.example.navigation.navigation.model.Celebrant


class CurrentCelebrantViewHolder(
    private val binding: CurrentCelebrantItemBinding,
    private val onItemClick: (Celebrant) -> Unit,
    private val onShareClick: (Celebrant) -> Unit,
    private val onMessageClick: (Celebrant) -> Unit,
    private val onCallClick: (Celebrant) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(celebrant: Celebrant) {
        binding.apply {
            currentCelebrantImageIv.load(celebrant.image?.first()){
                crossfade(true)
                placeholder(R.drawable.birthday_user_avatar)
            }
            currentCelebrantNameTv.text =
                root.context.getString(R.string.celebratory_prompt_with_name, celebrant.name)
            currentCelebrantShareIv.setOnClickListener {
                onShareClick(celebrant)
            }
            currentCelebrantMessageIv.setOnClickListener {
                onMessageClick(celebrant)
            }
            currentCelebrantCallIv.setOnClickListener {
                onCallClick(celebrant)
            }
            root.setOnClickListener {
                onItemClick(celebrant)
            }
        }
    }

}