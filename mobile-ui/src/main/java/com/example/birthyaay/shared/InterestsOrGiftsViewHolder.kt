package com.example.birthyaay.shared

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.R
import com.example.birthyaay.databinding.InterestGiftContentBinding
import com.example.birthyaay.models.GiftOrInterestContent
import com.example.birthyaay.util.ContentType
import com.example.birthyaay.util.DataResourceGenerator


class InterestsOrGiftsViewHolder(
    private val binding: InterestGiftContentBinding,
    private val onItemClick: (GiftOrInterestContent, Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(giftOrInterestContent: GiftOrInterestContent) {
        binding.apply {
            contentCb.isChecked = giftOrInterestContent.isClicked
            contentTv.text = giftOrInterestContent.title
            contentIv.setImageResource(giftOrInterestContent.drawableRes)

            if (giftOrInterestContent.title == root.context.getString(R.string.not_suggested_str)) {
                contentIv.visibility = View.GONE
            }

            root.isSelected = false

            root.setOnClickListener {
                onItemClick(giftOrInterestContent, adapterPosition)

                contentCb.isChecked = !it.isSelected

                it.isSelected = !it.isSelected

                if (it.isSelected && giftOrInterestContent.title != root.context.getString(R.string.not_suggested_str)) {
                    root.setBackgroundColor(ContextCompat.getColor(root.context, R.color.purple_50))
                } else {
                    root.setBackgroundColor(ContextCompat.getColor(root.context, R.color.white))
                }

            }

            if ((DataResourceGenerator.listener == DataResourceGenerator.CHECKED_INTEREST
                        && giftOrInterestContent.title != root.context.getString(R.string.not_suggested_str)) ||
                (DataResourceGenerator.listener == DataResourceGenerator.CHECKED_INTEREST
                        && giftOrInterestContent.title != root.context.getString(R.string.not_suggested_str))

            ) {
                root.isSelected = true
                contentCb.isChecked = root.isSelected
                root.setBackgroundColor(ContextCompat.getColor(root.context, R.color.purple_50))

                when (giftOrInterestContent.contentType) {
                    ContentType.INTEREST -> {
                        DataResourceGenerator.listener = DataResourceGenerator.UNCHECKED_INTEREST
                    }
                    ContentType.GIFT -> {
                        DataResourceGenerator.listener = DataResourceGenerator.UNCHECKED_GIFT
                    }
                    else -> {

                    }
                }


            }

        }
    }

}
