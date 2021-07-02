package com.example.birthyaay.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.R
import com.example.birthyaay.databinding.InterestGiftContentBinding
import com.example.birthyaay.models.GiftOrInterestContent
import com.example.birthyaay.shared.InterestsOrGiftsViewHolder
import com.example.birthyaay.util.ContentType
import com.example.birthyaay.util.DataResourceGenerator

class GiftsOrInterestsAdapter(
    private val onItemClick: (GiftOrInterestContent, Int) -> Unit,
    private val giftOrInterestContents: MutableList<GiftOrInterestContent>
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
        holder.bind(giftOrInterestContents[position])
    }

    override fun getItemCount(): Int = giftOrInterestContents.size

    fun addGiftOrInterest(giftOrInterestContent: GiftOrInterestContent, position: Int) {
        this.giftOrInterestContents.add(position, giftOrInterestContent)

        when (giftOrInterestContent.contentType) {
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
            GiftOrInterestContent("Not Suggested? Add yours...",
                false,
                R.drawable.ic_interest,
                ContentType.NEUTRAL
            ),
            giftOrInterestContents.lastIndex
        )

        for (i in giftOrInterestContents.indices) {
            if (giftOrInterestContents[i].title == context?.getString(R.string.not_suggested_str)) {
                this.giftOrInterestContents.removeAt(i)
                notifyItemRemoved(i)
                this.giftOrInterestContents.add(
                    giftOrInterestContents.lastIndex + 1,
                    GiftOrInterestContent(
                        context?.getString(R.string.not_suggested_str)!!,
                        false,
                        R.drawable.ic_interest,
                        ContentType.NEUTRAL
                    )
                )
            }
        }

    }

}