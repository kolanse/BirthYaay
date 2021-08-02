package com.example.birthyaay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.databinding.InterestItemBinding
import com.example.birthyaay.models.Interest
import com.example.birthyaay.shared.InterestsViewHolder

class InterestsAdapter(private val interests: MutableList<Interest>): RecyclerView.Adapter<InterestsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestsViewHolder {
        val binding = InterestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InterestsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InterestsViewHolder, position: Int) {
        holder.bind(interests[position])
    }

    override fun getItemCount(): Int = interests.size

    fun removeAt(position: Int) {
        interests.removeAt(position)
        notifyItemRemoved(position)
    }

}