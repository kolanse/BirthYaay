package com.example.birthyaay.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.birthyaay.databinding.FragmentHomeBinding
import com.google.android.material.appbar.AppBarLayout
import com.example.birthyaay.R
import com.example.birthyaay.adapters.CurrentCelebrantsAdapter
import com.example.birthyaay.adapters.UpComingCelebrantAdapter
import com.example.birthyaay.util.*

import com.example.navigation.navigation.model.Celebrant
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var phoneNum: String? = null
    private var message: String? = null

    companion object {
        const val SEND_SMS = "Send SMS"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.apply {

            var isShow = false
            var scrollRange = -1

            fragmentHomeInc.fragmentHomeRv.apply {
                adapter = UpComingCelebrantAdapter(
                    onItemClick = { position, upComingCelebrant ->

                        val action =
                            HomeFragmentDirections.actionHomeFragmentToCelebrantDetailsFragment(
                                upComingCelebrant
                            )

                        findNavController().navigate(action)

                    },
                    DataResourceGenerator.celebrants()
                )
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

            }

            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    toolbarTitle.text = getString(R.string.good_morning_str)
                    isShowOrHideView(false, this)
                } else if (isShow) {
                    isShow = false
                    toolbarTitle.text = ""
                    isShowOrHideView(true, this)
                }

            })

            fragmentHomeInc.currentCelebrantHorizontalVp.apply {
                adapter = CurrentCelebrantsAdapter(
                    DataResourceGenerator.celebrants(),
                    onItemClick = { currentCelebrant ->

                        val action = HomeFragmentDirections
                            .actionHomeFragmentToCelebrantDetailsFragment(currentCelebrant)

                        findNavController().navigate(action)
                    },
                    onCallClick = { currentCelebrant ->
                        currentCelebrant.phoneNumber?.let { makeCall(it) }
                    },
                    onShareClick = {

//                        if (imageUri != null) {
//                            checkForPermission(
//                                PERMISSION_NAME, READ_STORAGE_REQUEST_CODE,
//                                action = {
//                                    imageUri?.let { shareImage(it) }
//                                }
//                            )
//                        } else {
//                            showSnackWithMessage("There's no image to share", Snackbar.LENGTH_LONG)
//                        }

                    },
                    onMessageClick = { currentCelebrant ->

                        val (image, name, phoneNumber, note) = currentCelebrant

                        if (phoneNumber != null) {
                            phoneNum = phoneNumber
                        }

                        if (note != null) {
                            message = note
                        }

                        checkForMessagePermission(
                            SEND_SMS,
                            SEND_SMS_REQUEST_CODE,
                            phoneNum!!,
                            message!!
                        )

                    }
                )

                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 2
                setPadding(150, 0, 150, 0)

            }

            fragmentHomeFab.setOnClickListener {
                findNavController().navigate(R.id.addCelebrantFragment)
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

                    if (phoneNum != null && message != null) {
                        sendMessage(phoneNum!!, message!!)
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