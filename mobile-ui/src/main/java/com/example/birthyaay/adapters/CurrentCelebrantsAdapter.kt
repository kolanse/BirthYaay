package com.example.birthyaay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.databinding.CurrentCelebrantItemBinding
import com.example.birthyaay.shared.CurrentCelebrantViewHolder
import com.example.navigation.navigation.model.Celebrant

class CurrentCelebrantsAdapter(
    private val celebrants: MutableList<Celebrant>,
    private val onItemClick: (Celebrant) -> Unit,
    private val onShareClick: (Celebrant) -> Unit,
    private val onMessageClick: (Celebrant) -> Unit,
    private val onCallClick: (Celebrant) -> Unit
    ) : RecyclerView.Adapter<CurrentCelebrantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentCelebrantViewHolder {

        val binding = CurrentCelebrantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrentCelebrantViewHolder(binding,
            onItemClick = { currentCelebrants ->
                onItemClick(currentCelebrants)
            },
            onShareClick ={ currentCelebrants ->
                onShareClick(currentCelebrants)
            },
            onMessageClick = { currentCelebrants ->
                onMessageClick(currentCelebrants)
            },
            onCallClick = { currentCelebrants ->
                onCallClick(currentCelebrants)
            }
        )
    }

    override fun onBindViewHolder(holder: CurrentCelebrantViewHolder, position: Int) {
        holder.bind(celebrants[position])
    }

    override fun getItemCount(): Int = celebrants.size
}