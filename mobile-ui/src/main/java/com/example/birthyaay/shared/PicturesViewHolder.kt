package com.example.birthyaay.shared

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import com.example.birthyaay.R
import com.example.birthyaay.databinding.PictureItemBinding

class PicturesViewHolder(private val binding: PictureItemBinding,
                         private val onItemClick: (String) -> Unit):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: String){
        binding.apply {
            if (picture.isNotEmpty() && picture != "null") {
                imageIv.load(picture){
                    crossfade(true)
                    placeholder(R.drawable.birthday_user_avatar)
                }
                imageIv.setOnClickListener {
                    onItemClick.invoke(picture)
                }
            }
        }
    }
}