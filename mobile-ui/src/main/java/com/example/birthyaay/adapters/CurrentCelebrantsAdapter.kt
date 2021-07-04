package com.example.birthyaay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.databinding.CurrentCelebrantItemBinding
import com.example.birthyaay.models.CurrentCelebrant
import com.example.birthyaay.shared.CurrentCelebrantViewHolder

class CurrentCelebrantsAdapter(
    private val currentCelebrants: MutableList<CurrentCelebrant>,
    private val onShareClick: (CurrentCelebrant) -> Unit,
    private val onMessageClick: (CurrentCelebrant) -> Unit,
    private val onCallClick: (CurrentCelebrant) -> Unit
    ) : RecyclerView.Adapter<CurrentCelebrantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentCelebrantViewHolder {

        val binding = CurrentCelebrantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrentCelebrantViewHolder(binding,
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
        holder.bind(currentCelebrants[position])
    }

    override fun getItemCount(): Int = currentCelebrants.size
}