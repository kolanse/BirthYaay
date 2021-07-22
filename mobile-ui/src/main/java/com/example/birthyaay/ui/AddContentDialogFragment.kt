package com.example.birthyaay.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupWindow
import androidx.fragment.app.DialogFragment
import com.example.birthyaay.R
import com.example.birthyaay.databinding.ContentLayoutBinding
import com.example.navigation.navigation.model.ContentType
import com.example.birthyaay.util.DataResourceGenerator
import com.example.birthyaay.util.showPopUpWindow

class AddContentDialogFragment: DialogFragment(R.layout.content_layout) {

    private var _binding: ContentLayoutBinding? = null
    private val binding get() = _binding!!
    var viewType: ContentType? = null
    private var interestPopupWindow: PopupWindow? = null
    private var giftPopupWindow: PopupWindow? = null
    private var width: Int = 0
    private var height: Int = 0

    companion object {
        fun newInstance() =
            AddContentDialogFragment()
        const val ADD_CONTENT_DIALOG_TAG = "ADD_CONTENT_DIALOG_FRAGMENT"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ContentLayoutBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.apply {

            initDialogDimens()

            when (viewType) {
                ContentType.INTEREST -> {
                    addGiftTv.text = getString(R.string.add_interest_str)
                    addContentEt.text = getString(R.string.choose_interest_str)
                    submitBtn.text = getString(R.string.add_interest_str)

                    addContentEt.setOnClickListener {
                        dismissPopup()

                        interestPopupWindow = showPopUpWindow(
                            textView = addContentEt,
                            contents = DataResourceGenerator.provideInterests(),
                            contentList = DataResourceGenerator.interestsList,
                            contentNotSuggestedList = DataResourceGenerator.notSuggestedList,
                            addedSuggestedContentList = DataResourceGenerator.addedSuggestedInterests,
                            contentType = ContentType.INTEREST
                        )
                        interestPopupWindow!!.apply {
                            isOutsideTouchable = true
                            isFocusable = true
                            elevation = 10F
                            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            showAsDropDown(addContentEt)
                        }

                        initDialogDimens()
                    }

                }
                ContentType.GIFT -> {
                    addGiftTv.text = getString(R.string.add_gift_str)
                    addContentEt.text = getString(R.string.label_choose_gift_categories_str)
                    submitBtn.text = getString(R.string.add_gift_str)

                    addContentEt.setOnClickListener {
                        dismissPopup()
                        giftPopupWindow = showPopUpWindow(
                            textView = addContentEt,
                            contents = DataResourceGenerator.provideGifts(),
                            contentList = DataResourceGenerator.giftsList,
                            contentNotSuggestedList = DataResourceGenerator.notSuggestedGiftList,
                            addedSuggestedContentList = DataResourceGenerator.addedSuggestedGifts,
                            contentType = ContentType.GIFT
                        )

                        giftPopupWindow!!.apply {
                            isOutsideTouchable = true
                            isFocusable = true
                            elevation = 10F
                            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            showAsDropDown(addContentEt)
                        }
                    }

                }
                ContentType.NEUTRAL -> {

                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
       _binding = null
    }

    private fun initDialogDimens() {

        println("Popwindow: ${interestPopupWindow?.isShowing}")
        Log.d("Popwindow: ", "${interestPopupWindow?.isShowing}")

        if (interestPopupWindow?.isShowing == true) {
            width = (resources.displayMetrics.widthPixels * 0.9).toInt()
            height = (resources.displayMetrics.heightPixels * 0.9).toInt()
        } else {
            width = (resources.displayMetrics.widthPixels * 0.9).toInt()
            height = (resources.displayMetrics.heightPixels * 0.4).toInt()
        }

        dialog!!.window?.setLayout(width, height)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
    }

    private fun dismissPopup() {
        interestPopupWindow?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            interestPopupWindow = null
        }

        giftPopupWindow?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            giftPopupWindow = null
        }
    }


}