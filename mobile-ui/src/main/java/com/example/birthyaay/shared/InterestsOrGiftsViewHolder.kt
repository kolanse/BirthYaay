package com.example.birthyaay.shared

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.R
import com.example.birthyaay.adapters.utils.AdapterItemHelper
import com.example.birthyaay.databinding.InterestGiftContentBinding
import com.example.birthyaay.models.GiftOrInterestContent
import com.example.navigation.navigation.model.ContentType
import com.example.birthyaay.util.DataResourceGenerator
import com.example.navigation.navigation.model.Content


class InterestsOrGiftsViewHolder(
    private val binding: InterestGiftContentBinding,
    private val onItemClick: (Content, Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Content) {
        binding.apply {
            contentCb.isChecked = content.isClicked
            contentTv.text = content.title
            contentIv.setImageResource(content.drawableRes)

            if (content.title == root.context.getString(R.string.not_suggested_str)) {
                contentIv.visibility = View.GONE
            }

            root.isSelected = false

            root.setOnClickListener {
                onItemClick(content, adapterPosition)

                contentCb.isChecked = !it.isSelected

                it.isSelected = !it.isSelected

                if (it.isSelected && content.title != root.context.getString(R.string.not_suggested_str)) {

                    if (content.viewType == AdapterItemHelper.VIEW_TYPE_ADD_INTEREST) {
                        root.setBackgroundColor(ContextCompat.getColor(root.context, R.color.purple_50))
                    } else {
                        root.setBackgroundColor(ContextCompat.getColor(root.context, R.color.dark_brown))
                    }

                } else {
                    root.setBackgroundColor(Color.TRANSPARENT)
                }

            }

            if ((DataResourceGenerator.listener == DataResourceGenerator.CHECKED_INTEREST
                        && content.title != root.context.getString(R.string.not_suggested_str)) ||
                (DataResourceGenerator.listener == DataResourceGenerator.CHECKED_GIFT
                        && content.title != root.context.getString(R.string.not_suggested_str))

            ) {
                root.isSelected = true
                contentCb.isChecked = root.isSelected

                if (content.viewType == AdapterItemHelper.VIEW_TYPE_ADD_INTEREST) {
                    root.setBackgroundColor(ContextCompat.getColor(root.context, R.color.purple_50))
                } else {
                    root.setBackgroundColor(ContextCompat.getColor(root.context, R.color.dark_brown))
                }

                when (content.contentType) {
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
