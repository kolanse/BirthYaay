package com.example.birthyaay.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.birthyaay.R
import com.example.birthyaay.adapters.DetailsAdapter
import com.example.birthyaay.databinding.FragmentCelebrantDetailsBinding
import com.example.birthyaay.ui.HomeFragment.Companion.SEND_SMS
import com.example.birthyaay.util.*
import com.example.navigation.navigation.model.Celebrant
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.reflect.typeOf

class CelebrantDetailsFragment : Fragment(R.layout.fragment_celebrant_details) {

    private var _binding: FragmentCelebrantDetailsBinding? = null
    val binding get() = _binding!!
    private val args: CelebrantDetailsFragmentArgs by navArgs()
    private var imageUrl: String? = null
    private var note: String? = null
    private var phoneNumber: String? = null

    companion object {
        const val CELEBRANT_DETAILS_ARGS_BUNDLE_KEY = "celebrant_details_bundle_key"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBottomNavigationView()
        _binding = FragmentCelebrantDetailsBinding.bind(view)

        val celebrant = args.celebrant
        imageUrl = celebrant?.image?.first()
        note = celebrant?.note
        phoneNumber = celebrant?.phoneNumber

        binding.apply {

            var isShow = false
            var scrollRange = -1

            fragmentCelebrantDetailsToolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
//                    findNavController().navigate(
//                        R.id.peopleFragment
//                    )
                }
            }

            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    toolbarTitle.text = getString(R.string.birthday_celebrant)
                    isShowOrHideView(false, this)
                } else if (isShow) {
                    isShow = false
                    toolbarTitle.text = ""
                    isShowOrHideView(true, this)
                }

            })


            fragmentCelebrantDetailsInc.apply {

                celebrantNameTv.text = celebrant?.name

                celebrantDetailsVp.apply {
                    adapter = DetailsAdapter(requireActivity(), celebrant!!)
                }

                TabLayoutMediator(
                    tableLayout,
                    celebrantDetailsVp
                ) { tab, position ->

                    when (position) {
                        0 -> {
                            tab.icon =
                                ContextCompat.getDrawable(requireContext(), R.drawable.avatar)
                        }
                        else -> {
                            tab.icon =
                                ContextCompat.getDrawable(requireContext(), R.drawable.information)
                        }
                    }

                }.attach()

            }


            fragmentCelebrantDetailsInc.apply {

                currentCelebrantImageIv.load(imageUrl) {
                    crossfade(false)
                    error(R.drawable.user_avatar)
                }

                callIv.setOnClickListener {

                    if (phoneNumber.isNullOrEmpty()) {
                        showSnackWithMessage(
                            getString(R.string.no_phone_error),
                            Snackbar.LENGTH_LONG
                        )
                    } else {
                        makeCall(phoneNumber!!)
                    }

                }

                envelopeIv.setOnClickListener {

                    when {

                        note.isNullOrEmpty() -> {
                            showSnackWithMessage(
                                getString(R.string.no_note_error),
                                Snackbar.LENGTH_LONG
                            )
                        }

                        phoneNumber.isNullOrEmpty() -> {

                            showSnackWithMessage(
                                getString(R.string.no_phone_error),
                                Snackbar.LENGTH_LONG
                            )

                        }
                        else -> {
                            checkForMessagePermission(
                                SEND_SMS,
                                SEND_SMS_REQUEST_CODE,
                                phoneNumber!!,
                                note!!
                            )
                        }
                    }

                }

                shareIv.setOnClickListener {

                    if (imageUrl != "null") {
                        checkForPermission(
                            PERMISSION_NAME, READ_STORAGE_REQUEST_CODE,
                            action = {
                                imageUrl?.let {
                                    shareImage(it.toUri())
                                }
                            }
                        )
                    } else {
                        showSnackWithMessage(
                            getString(R.string.no_image_error),
                            Snackbar.LENGTH_LONG
                        )
                    }
                }

                currentCelebrantImageIv.setOnClickListener {
                    val action = CelebrantDetailsFragmentDirections
                        .actionCelebrantDetailsFragmentToDisplayPictureFragment(celebrant)

                    findNavController().navigate(action)
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
            SEND_SMS_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showSnackWithMessage(
                        getString(R.string.permission_refused_message),
                        Snackbar.LENGTH_SHORT
                    )
                } else {

                    if (phoneNumber != null && note != null) {
                        sendMessage(phoneNumber!!, note!!)
                    } else {
                        showSnackWithMessage(
                            getString(R.string.empty_phone_or_message),
                            Snackbar.LENGTH_SHORT
                        )
                    }
                }
            }

            READ_STORAGE_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showSnackWithMessage(
                        getString(R.string.permission_refused_message),
                        Snackbar.LENGTH_SHORT
                    )
                } else {

                    if (imageUrl != "null") {

                        imageUrl?.let {
                            shareImage(it.toUri())
                        }

                    } else {
                        showSnackWithMessage(
                            getString(R.string.no_image_error),
                            Snackbar.LENGTH_LONG
                        )
                    }

                }
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}