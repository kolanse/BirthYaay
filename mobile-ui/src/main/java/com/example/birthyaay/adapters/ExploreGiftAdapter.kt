package com.example.birthyaay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.databinding.ExploreGiftItemBinding
import com.example.birthyaay.models.ExploreGift
import com.example.birthyaay.shared.ExploreGiftViewHolder
import com.example.birthyaay.util.DataResourceGenerator

class ExploreGiftAdapter: RecyclerView.Adapter<ExploreGiftViewHolder>() {

    private val exploreGifts = DataResourceGenerator.provideExploreGift()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreGiftViewHolder {
        val binding = ExploreGiftItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploreGiftViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExploreGiftViewHolder, position: Int) {
        holder.bind(exploreGifts[position])
    }

    override fun getItemCount(): Int = exploreGifts.size

}