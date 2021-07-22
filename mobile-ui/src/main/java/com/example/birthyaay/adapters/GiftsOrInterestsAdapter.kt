package com.example.birthyaay.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.R
import com.example.birthyaay.databinding.InterestGiftContentBinding
import com.example.birthyaay.models.GiftOrInterestContent
import com.example.birthyaay.shared.InterestsOrGiftsViewHolder
import com.example.navigation.navigation.model.ContentType
import com.example.birthyaay.util.DataResourceGenerator
import com.example.navigation.navigation.model.Content

class GiftsOrInterestsAdapter(
    private val onItemClick: (Content, Int) -> Unit,
    private val contents: MutableList<Content>
) : RecyclerView.Adapter<InterestsOrGiftsViewHolder>() {

    private var context: Context? = null
    private var binding: InterestGiftContentBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestsOrGiftsViewHolder {
        binding =
            InterestGiftContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = binding!!.root.context

        return InterestsOrGiftsViewHolder(binding!!, onItemClick)
    }

    override fun onBindViewHolder(holder: InterestsOrGiftsViewHolder, position: Int) {
        holder.bind(contents[position])
    }

    override fun getItemCount(): Int = contents.size

    fun addGiftOrInterest(content: Content, position: Int) {
        this.contents.add(position, content)

        when (content.contentType) {
            ContentType.INTEREST -> {
                DataResourceGenerator.listener = DataResourceGenerator.CHECKED_INTEREST
            }
            ContentType.GIFT -> {
                DataResourceGenerator.listener = DataResourceGenerator.CHECKED_GIFT
            }
            else ->{

            }
        }


        notifyItemInserted(position)
    }

    fun removeAndAddNotSuggestedGiftOrInterest() {
        onItemClick.invoke(
            Content("Not Suggested? Add yours...",
                false,
                ContentType.NEUTRAL,
                R.drawable.ic_interest,
            ),
            contents.lastIndex
        )

        for (i in contents.indices) {
            if (contents[i].title == context?.getString(R.string.not_suggested_str)) {
                this.contents.removeAt(i)
                notifyItemRemoved(i)
                this.contents.add(
                    contents.lastIndex + 1,
                    Content(
                        context?.getString(R.string.not_suggested_str)!!,
                        false,
                        ContentType.INTEREST,
                        R.drawable.ic_interest,
                    )
                )
            }
        }

    }

}