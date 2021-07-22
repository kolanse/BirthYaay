package com.example.birthyaay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.adapters.utils.AdapterItemHelper
import com.example.birthyaay.databinding.CelebrantBirthdayItemBinding
import com.example.birthyaay.databinding.CelebrantGroupHeaderItemBinding
import com.example.birthyaay.models.UpComingCelebrant
import com.example.birthyaay.shared.GroupAlphabetViewHolder
import com.example.birthyaay.shared.UpComingCelebrantViewHolder
import com.example.navigation.navigation.model.Celebrant
import java.util.*

class UpComingCelebrantAdapter(
    private val onItemClick: (Int, Celebrant) -> Unit,
    private val upComingCelebrantList: MutableList<Celebrant>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return upComingCelebrantList[position].viewType
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)

        when (viewType) {
            AdapterItemHelper.VIEW_TYPE_PERSON -> {
                val binding = CelebrantBirthdayItemBinding.inflate(
                    inflater,
                    viewGroup,
                    false)
                return UpComingCelebrantViewHolder(
                    binding,
                    onItemClick = { position, upComingCelebrant ->
                        onItemClick(position, upComingCelebrant)
                    }
                )
            }

            AdapterItemHelper.VIEW_TYPE_GROUP-> {
                val binding = CelebrantGroupHeaderItemBinding.inflate(
                    inflater,
                    viewGroup,
                    false)
                return GroupAlphabetViewHolder(
                    binding
                )
            }
            else -> {
                val binding = CelebrantBirthdayItemBinding.inflate(
                    inflater,
                    viewGroup,
                    false)
                return UpComingCelebrantViewHolder(
                    binding,
                    onItemClick = { position, upComingCelebrant ->
                        onItemClick(position, upComingCelebrant)
                    }
                )
            }

        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UpComingCelebrantViewHolder) {
            holder.bind(upComingCelebrantList[position])
        } else if (holder is GroupAlphabetViewHolder) {
            holder.bind(upComingCelebrantList[position])
        }
    }

    override fun getItemCount(): Int = upComingCelebrantList.size
}