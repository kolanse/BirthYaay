package com.example.birthyaay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.databinding.CelebrantBirthdayItemBinding
import com.example.birthyaay.models.UpComingCelebrant
import com.example.birthyaay.shared.CelebrantViewHolder

class UpComingCelebrantAdapter(
    private val onItemClick: (Int, UpComingCelebrant) -> Unit,
    private val upComingCelebrantList: MutableList<UpComingCelebrant>
) : RecyclerView.Adapter<CelebrantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelebrantViewHolder {
        val binding =
            CelebrantBirthdayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CelebrantViewHolder(
            binding,
            onItemClick = { position, upComingCelebrant ->
                onItemClick(position, upComingCelebrant)
            }
        )
    }

    override fun onBindViewHolder(holder: CelebrantViewHolder, position: Int) {
        holder.bind(upComingCelebrantList[position])
    }

    override fun getItemCount(): Int = upComingCelebrantList.size
}