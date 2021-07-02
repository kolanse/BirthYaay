package com.example.birthyaay.util

import android.app.Activity
import android.content.Context
import android.text.SpannableStringBuilder
import android.view.*
import android.widget.PopupWindow
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.birthyaay.R
import com.example.birthyaay.adapters.GiftsOrInterestsAdapter
import com.example.birthyaay.databinding.FragmentAddCelebrantBinding
import com.example.birthyaay.databinding.FragmentHomeBinding
import com.example.birthyaay.databinding.FragmentPeopleBinding
import com.example.birthyaay.models.GiftOrInterestContent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun Fragment.isShowOrHideView(isShow: Boolean, binding: ViewBinding) {

    when (binding) {
        is FragmentHomeBinding -> {
            binding.apply {
                fragmentHomeGreetingLoveIv.isVisible = isShow
                fragmentHomeGreetingTitleTv.isVisible = isShow
                fragmentHomeSearchEt.isVisible = isShow
                fragmentHomeLoveToolbarIv.isVisible = !isShow
                fragmentHomeSearchToolbarIv.isVisible = !isShow
            }
        }
        is FragmentPeopleBinding -> {
            binding.apply {
                fragmentPeopleGreetingLoveIv.isVisible = isShow
                fragmentPeopleGreetingTitleTv.isVisible = isShow
                fragmentPeopleSearchEt.isVisible = isShow
                fragmentPeopleLoveToolbarIv.isVisible = !isShow
                fragmentPeopleSearchToolbarIv.isVisible = !isShow
            }
        }
        is FragmentAddCelebrantBinding -> {
            binding.apply {
                fragmentAddCelebrantBalloonIv.isVisible = isShow
                fragmentAddCelebrantTitleTv.isVisible = isShow
                //fragmentAddCelebrantToolbar.isVisible = !isShow
                fragmentAddCelebrantToolbarIv.isVisible = !isShow
            }
        }
    }
}

fun Fragment.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.fade_in).setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in).setPopExitAnim(R.anim.fade_out)
    return navBuilder
}

fun Activity.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.fade_in).setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in).setPopExitAnim(R.anim.fade_out)
    return navBuilder
}

fun BottomNavigationView.checkMenuItem(destinationId: Int) {
    for (i in 0 until menu.size()) {
        val item: MenuItem = menu.getItem(i)
        item.isChecked = false
    }
    menu.findItem(destinationId)?.isChecked = true
}


fun Fragment.showPopUpWindow(
    textInputLayout: TextInputLayout,
    editText: TextInputEditText,
    contents: MutableList<GiftOrInterestContent>,
    contentList: MutableList<String>,
    contentNotSuggestedList: MutableList<String>,
    addedSuggestedContentList: MutableList<String>,
    contentType: ContentType
): PopupWindow {

    val inflater =
        requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    val view = inflater.inflate(R.layout.interest_pop_up_window, null)

    val contentRecyclerView = view.findViewById<RecyclerView>(R.id.content_rv)
    val contentAddLayout = view.findViewById<ConstraintLayout>(R.id.add_content_root)
    val contentTextInputLayout = view.findViewById<TextInputLayout>(R.id.add_content_til)
    val contentAddButton = view.findViewById<MaterialButton>(R.id.add_btn)
    val contentEditText = contentTextInputLayout?.editText

    when (contentType) {
        ContentType.INTEREST -> {
            contentAddButton.apply {
                setText(R.string.add_str)
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_interest_white, 0)
            }
        }
        else -> {
            contentAddButton.apply {
                setText(R.string.add_gift_str)
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_gift_boxes, 0)
            }
        }
    }


    val contentAdapter = GiftsOrInterestsAdapter(
        onItemClick = { content, position ->

            if (!contentList.contains(content.title) &&
                content.title != getString(
                    R.string.not_suggested_str
                )
            ) {
                contentList.add(content.title)
            } else {
                contentList.remove(content.title)
            }

            val interests = Converters.toString(contentList)

            textInputLayout.editText?.text =
                SpannableStringBuilder(interests)

            if (content.title == getString(
                    R.string.not_suggested_str
                ) &&
                !contentNotSuggestedList.contains(
                    getString(R.string.not_suggested_str)
                )
            ) {
                contentNotSuggestedList.add(content.title)
            } else {
                contentNotSuggestedList.remove(content.title)
            }

            contentAddLayout.isVisible =
                contentNotSuggestedList.isNotEmpty()

        },
        contents
    )

    contentRecyclerView.apply {
        adapter = contentAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    contentAddButton.setOnClickListener {
        val suggestedContent = contentEditText?.text.toString()

        if (suggestedContent.trim()
                .isNotEmpty() && !addedSuggestedContentList.contains(
                suggestedContent
            )
        ) {

            val content = if (contentType == ContentType.INTEREST) {
                GiftOrInterestContent(
                    suggestedContent,
                    false,
                    R.drawable.ic_interest,
                    ContentType.INTEREST
                )
            } else {
                GiftOrInterestContent(
                    suggestedContent,
                    false,
                    R.drawable.ic_gift_boxes,
                    ContentType.GIFT
                )
            }

            contentList.add(suggestedContent)
            addedSuggestedContentList.add(suggestedContent)
            contentEditText!!.text!!.clear()
            contentAdapter.addGiftOrInterest(content, ZERO_INDEX)
            contentAdapter.removeAndAddNotSuggestedGiftOrInterest()
            contentAddLayout.isVisible =
                contentNotSuggestedList.isNotEmpty()

        } else if (addedSuggestedContentList.contains(
                suggestedContent
            )
        ) {
            Toast.makeText(
                requireContext(),
                getString(R.string.interest_already_added_error, suggestedContent),
                Toast.LENGTH_SHORT
            )
                .show()
        } else {

            if (contentType == ContentType.INTEREST) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.empty_interest_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            Toast.makeText(
                requireContext(),
                getString(R.string.empty_gift_error),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }


    val width = editText.width

    return PopupWindow(
        view,
        width,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}