package com.example.birthyaay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.databinding.GiftItemBinding
import com.example.birthyaay.models.Gift
import com.example.birthyaay.shared.GiftsViewHolder

class GiftsAdapter(private val gifts: List<Gift>, private val onLongClick: (Gift) -> Unit): RecyclerView.Adapter<GiftsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftsViewHolder {
        val binding = GiftItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiftsViewHolder(binding, onLongClick = { gift ->
            onLongClick(gift)
        })
    }

    override fun onBindViewHolder(holder: GiftsViewHolder, position: Int) {
        holder.bind(gifts[position])
    }

    override fun getItemCount(): Int = gifts.size
}