package com.example.birthyaay.ui

import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
import com.example.navigation.navigation.model.Celebrant
import com.example.navigation.navigation.model.Content
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.example.navigation.navigation.model.ContentType
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddCelebrantFragment : Fragment() {
    private var _binding: FragmentAddCelebrantBinding? = null
    private val binding get() = _binding!!
    private var interestPopupWindow: PopupWindow? = null
    private var giftPopupWindow: PopupWindow? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Handle the returned Uri
            if (uri != null) {
                imageUri = uri
            }

        }

    companion object {
        const val NAME = "name"
        const val NAME_ERROR_MESSAGE = "Name must be at least 2 characters long."
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddCelebrantBinding.inflate(inflater)

        hideBottomNavigationView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            validateFields()

            fragmentAddCelebrantInc.addCelebrantInterestEt.setOnClickListener {

                dismissPopup()
                interestPopupWindow = showPopUpWindow(
                    textView = fragmentAddCelebrantInc.addCelebrantInterestEt,
                    contents = provideInterests(),
                    contentList = interestsList,
                    contentNotSuggestedList = notSuggestedList,
                    addedSuggestedContentList = addedSuggestedInterests,
                    contentType = com.example.navigation.navigation.model.ContentType.INTEREST
                )
                interestPopupWindow!!.apply {
                    isOutsideTouchable = true
                    isFocusable = true
                    elevation = 10F
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    showAsDropDown(fragmentAddCelebrantInc.addCelebrantInterestEt)
                }

            }


            fragmentAddCelebrantInc.addCelebrantGiftEt.setOnClickListener {

                dismissPopup()
                giftPopupWindow = showPopUpWindow(
                    textView = fragmentAddCelebrantInc.addCelebrantGiftEt,
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

            }


            fragmentAddCelebrantInc.addCelebrantPictureEt.setOnClickListener {
                checkForPermission(
                    PERMISSION_NAME, READ_STORAGE_REQUEST_CODE,
                    action = {
                        getContent.launch("image/*")
                    }
                )
            }


            pickAndSetDate(fragmentAddCelebrantInc.addCelebrantDateEt)

            fragmentAddCelebrantInc.submitBtn.setOnClickListener {

                if (fragmentAddCelebrantInc.addCelebrantDateEt.text.toString() == getString(R.string.date_of_birth_str)) {
                    Toast.makeText(
                        requireContext(),
                        "Date of Birth can't be empty.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    collectCelebrantData()
                }

            }

        }

    }

    override fun onResume() {
        super.onResume()

        binding.apply {

            if (imageUri != null) {
                fragmentAddCelebrantInc.apply {
                    addCelebrantPictureEt.apply {
                        text = "  Picture added successfully."
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
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
                        text = getString(R.string.label_add_celebrant_picture_str)
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_grey))
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
            READ_STORAGE_REQUEST_CODE -> {
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


    private fun String.validateStringLen(): Boolean {
        return this.length >= 2
    }


    private fun validateFields() {
        binding.apply {
            fragmentAddCelebrantInc.apply {
                addCelebrantNameEt.editText?.doOnTextChanged { text, start, before, count ->
                    if (!text.toString().trim().validateStringLen()) {
                        addCelebrantNameEt.error = NAME_ERROR_MESSAGE
                        submitBtn.isEnabled = false
                        submitBtn.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grey_70
                            )
                        )
                    } else {
                        addCelebrantNameEt.error = null
                        submitBtn.isEnabled = true
                        submitBtn.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.purple_100
                            )
                        )
                    }
                }
            }
        }
    }


    fun collectCelebrantData() {
        binding.apply {
            fragmentAddCelebrantInc.apply {
                val name = addCelebrantNameEt.editText?.text.toString()
                val phoneNumber = addCelebrantPhoneEt.editText?.text.toString()
                val email = addCelebrantEmailEt.editText?.text.toString()
                val dateOfBirth =
                    if (addCelebrantDateEt.text.toString() != getString(R.string.date_of_birth_str)) {
                        addCelebrantDateEt.text.toString()
                    } else {
                        ""
                    }
                val interestsInString = if (addCelebrantInterestEt.text.toString()
                    != getString(R.string.label_interests_str)
                ) {
                    addCelebrantInterestEt.text.toString()
                } else {
                    ""
                }
                val interests = if (interestsInString.trim().isNotEmpty()) {
                    interestsInString
                        .split(",")
                        .map {
                            Content(it, true, ContentType.INTEREST)
                        }
                } else {
                    emptyList()
                }

                val giftsInString = if (addCelebrantGiftEt.text.toString()
                    != getString(R.string.label_choose_gift_categories_str)
                ) {
                    addCelebrantGiftEt.text.toString()
                } else {
                    ""
                }

                val gifts = if (giftsInString.trim().isNotEmpty()) {
                    giftsInString
                        .split(",")
                        .map {
                            Content(it, true, ContentType.GIFT)
                        }
                } else {
                    emptyList()
                }


                val pictureUri = imageUri.toString()
                val note = addCelebrantNoteEt.editText?.text.toString()

                val celebrant = Celebrant(
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    dateOfBirth = dateOfBirth,
                    interests = interests,
                    gifts = gifts,
                    image = listOf(pictureUri),
                    note = note
                )

                val actions =
                    AddCelebrantFragmentDirections
                        .actionAddCelebrantFragmentToCelebrantDetailsFragment(celebrant)
                findNavController().navigate(actions)
            }
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


