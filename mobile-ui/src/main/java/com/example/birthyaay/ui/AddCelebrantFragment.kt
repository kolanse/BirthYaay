package com.example.birthyaay.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.PopupWindow
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.birthyaay.R
import com.example.birthyaay.databinding.FragmentAddCelebrantBinding
import com.example.birthyaay.util.*
import com.example.birthyaay.util.DataResourceGenerator.addedSuggestedGifts
import com.example.birthyaay.util.DataResourceGenerator.addedSuggestedInterests
import com.example.birthyaay.util.DataResourceGenerator.giftsList
import com.example.birthyaay.util.DataResourceGenerator.interestsList
import com.example.birthyaay.util.DataResourceGenerator.notSuggestedGiftList
import com.example.birthyaay.util.DataResourceGenerator.notSuggestedList
import com.example.birthyaay.util.DataResourceGenerator.provideGifts
import com.example.birthyaay.util.DataResourceGenerator.provideInterests
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar

class AddCelebrantFragment : Fragment(R.layout.fragment_add_celebrant) {
    private var _binding: FragmentAddCelebrantBinding? = null
    private val binding get() = _binding!!
    private var interestPopupWindow: PopupWindow? = null
    private var giftPopupWindow: PopupWindow? = null

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        if (uri != null) {
            imageUri = uri
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddCelebrantBinding.bind(view)

        binding.apply {

            var isShow = false
            var scrollRange = -1

            fragmentAddCelebrantToolbar.apply {
                setNavigationOnClickListener {
                    findNavController().navigate(R.id.homeFragment)
                }
            }

            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    toolbarTitle.text = getString(R.string.add_celebrant_str)
                    isShowOrHideView(false, this)
                } else if (isShow) {
                    isShow = false
                    toolbarTitle.text = ""
                    isShowOrHideView(true, this)
                }

            })

            fragmentAddCelebrantInc.addCelebrantInterestEt.setOnTouchListener { v, event ->

                dismissPopup()
                interestPopupWindow = showPopUpWindow(
                    textInputLayout = fragmentAddCelebrantInc.addCelebrantInterestTil,
                    editText = fragmentAddCelebrantInc.addCelebrantInterestEt,
                    contents = provideInterests(),
                    contentList = interestsList,
                    contentNotSuggestedList = notSuggestedList,
                    addedSuggestedContentList = addedSuggestedInterests,
                    contentType = ContentType.INTEREST
                )
                interestPopupWindow!!.apply {
                    isOutsideTouchable = true
                    isFocusable = true
                    elevation = 10F
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    showAsDropDown(fragmentAddCelebrantInc.addCelebrantInterestEt)
                }
                true
            }

            fragmentAddCelebrantInc.addCelebrantGiftEt.setOnTouchListener { v, event ->

                dismissPopup()
                giftPopupWindow = showPopUpWindow(
                    textInputLayout = fragmentAddCelebrantInc.addCelebrantGiftsTil,
                    editText = fragmentAddCelebrantInc.addCelebrantGiftEt,
                    contents = provideGifts(),
                    contentList = giftsList,
                    contentNotSuggestedList = notSuggestedGiftList,
                    addedSuggestedContentList = addedSuggestedGifts,
                    contentType = ContentType.GIFT
                )

                giftPopupWindow!!.apply {
                    isOutsideTouchable = true
                    isFocusable = true
                    elevation = 10F
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    showAsDropDown(fragmentAddCelebrantInc.addCelebrantGiftEt)
                }
                true
            }

            fragmentAddCelebrantInc.addCelebrantPictureEt.setOnTouchListener { v, event ->
                checkForPermission(
                    PERMISSION_NAME, PICK_IMAGE_REQUEST_CODE,
                    getContent
                )
                true
            }

            pickAndSetDate(fragmentAddCelebrantInc.addCelebrantDateEt)

        }
    }

    override fun onResume() {
        super.onResume()

        binding.apply {

            if (imageUri != null) {
                fragmentAddCelebrantInc.apply {
                    addCelebrantPictureEt.apply {
                        text = SpannableStringBuilder("  Picture added successfully.")
                        addTextColor(requireContext(), R.color.black)
                        setCompoundDrawableWithSpecificBounds(
                            left = R.drawable.ic_check
                        )
                        isEnabled = false
                    }
                    addCelebrantRemoveBinIv.isVisible = true
                }
            }

            fragmentAddCelebrantInc.apply {
                addCelebrantRemoveBinIv.setOnClickListener {
                    addCelebrantPictureEt.apply {
                        text = SpannableStringBuilder(getString(R.string.label_add_celebrant_picture_str))
                        addTextColor(requireContext(), R.color.dark_grey)
                        setCompoundDrawableWithSpecificBounds(
                            right = R.drawable.gallery_solid
                        )
                        isEnabled = true
                    }
                    addCelebrantRemoveBinIv.isVisible = false
                    imageUri = null
                }
            }

        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PICK_IMAGE_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showSnackWithMessage(
                        getString(R.string.permission_refused_message),
                        Snackbar.LENGTH_SHORT
                    )
                } else {
                    getContent.launch("image/*")
                }
            }
        }

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


    override fun onStop() {
        super.onStop()
        dismissPopup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


