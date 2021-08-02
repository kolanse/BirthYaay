package com.example.birthyaay.ui

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.birthyaay.R
import com.example.birthyaay.adapters.GiftsAdapter
import com.example.birthyaay.adapters.InterestsAdapter
import com.example.birthyaay.adapters.PicturesAdapter
import com.example.birthyaay.databinding.FragmentExtraInfoBinding
import com.example.birthyaay.models.Gift
import com.example.birthyaay.models.Interest
import com.example.birthyaay.util.*
import com.example.navigation.navigation.model.ContentType
import com.example.birthyaay.util.DataResourceGenerator.celebrants
import com.example.birthyaay.util.DataResourceGenerator.provideGifts
import com.example.birthyaay.util.DataResourceGenerator.provideInterests
import com.example.navigation.navigation.model.Celebrant
import com.example.navigation.navigation.model.Content
import com.google.android.material.snackbar.Snackbar

class ExtraInfoFragment : Fragment(R.layout.fragment_extra_info) {

    private var _binding: FragmentExtraInfoBinding? = null
    private val binding get() = _binding!!
    private var celebrant: Celebrant? = null
    private var pictureUri: Uri? = null
    private lateinit var picturesAdapter: PicturesAdapter

    companion object {
        fun instance(celebrant: Celebrant): ExtraInfoFragment {
            val bundle = Bundle()
            val extraInfoFragment = ExtraInfoFragment()
            bundle.putParcelable(
                CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY,
                celebrant
            )
            extraInfoFragment.arguments = bundle
            return extraInfoFragment
        }

        const val RECYCLER_VIEW_IMAGE_SPAN_COUNT = 2
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Handle the returned Uri
            if (uri != null) {
                pictureUri = uri
            }

        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExtraInfoBinding.bind(view)

        celebrant = arguments?.getParcelable<Celebrant>(
            CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY
        )

        picturesAdapter = PicturesAdapter(
            onLongClick = { pictureUrl ->

                val menuBottomSheetDialog =
                    MenuBottomSheetDialog.newInstance(MenuBottomSheetDialog.CONTENT_TYPE_PICTURE)

                menuBottomSheetDialog.show(
                    parentFragmentManager.beginTransaction(),
                    MenuBottomSheetDialog.TAG
                )

            }
        )


        celebrant?.image?.forEach {
            if (!DataResourceGenerator.pictureStorage.contains(it)) {
                DataResourceGenerator.pictureStorage.add(it)
            }
        }

        binding.apply {

            val interests =
                celebrant?.interests?.filter { it.title != getString(R.string.not_suggested_str) }
                    ?.map {
                        Interest(it.title.trim())
                    } as MutableList<Interest>
            val gifts =
                celebrant?.gifts?.filter { it.title != getString(R.string.not_suggested_str) }
                    ?.map {
                        Gift(it.title.trim())
                    }


            interestRv.apply {
                adapter = InterestsAdapter(interests)
                layoutManager = LinearLayoutManager(requireContext())

                val swipeHandler = provideSwipeHandler()
                val itemTouchHelper = ItemTouchHelper(swipeHandler)
                itemTouchHelper.attachToRecyclerView(this)

            }

            giftRv.apply {
                adapter = gifts?.let { gifts ->
                    GiftsAdapter(gifts,
                        onLongClick = {

                            val menuBottomSheetDialog =
                                MenuBottomSheetDialog.newInstance(MenuBottomSheetDialog.CONTENT_TYPE_GIFT)

                            menuBottomSheetDialog.show(
                                parentFragmentManager.beginTransaction(),
                                MenuBottomSheetDialog.TAG
                            )

                        }
                    )
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            pictureRv.apply {
                adapter = picturesAdapter
                layoutManager = GridLayoutManager(
                    requireContext(),
                    RECYCLER_VIEW_IMAGE_SPAN_COUNT
                )
            }

            addInterestIv.setOnClickListener {

                val interests = (celebrant?.interests)?.toTypedArray()

                val contentModalBottomDialog = interests?.let { interests ->
                    ContentModalBottomDialog.newInstance(
                        interests,
                        ContentModalBottomDialog.CONTENT_INTEREST
                    )
                }

                contentModalBottomDialog?.show(
                    parentFragmentManager.beginTransaction(),
                    ContentModalBottomDialog.CONTENT_MODAL_BOTTOM_DIALOG_TAG
                )

            }

            addGiftIv.setOnClickListener {

                val gifts = (celebrant?.gifts)?.toTypedArray()

                val contentModalBottomDialog = gifts?.let { gifts ->
                    ContentModalBottomDialog.newInstance(
                        gifts,
                        ContentModalBottomDialog.CONTENT_GIFT
                    )
                }

                contentModalBottomDialog?.show(
                    parentFragmentManager.beginTransaction(),
                    ContentModalBottomDialog.CONTENT_MODAL_BOTTOM_DIALOG_TAG
                )

            }

            addPictureIv.setOnClickListener {
                checkForPermission(
                    PERMISSION_NAME, READ_STORAGE_REQUEST_CODE,
                    action = {
                        getContent.launch("image/*")
                    }
                )
            }

        }

    }

    private fun provideSwipeHandler(): SwipeToDeleteCallback {
        return object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.interestRv.adapter as InterestsAdapter
                adapter.removeAt(viewHolder.adapterPosition)
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

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()

        if (pictureUri != null) {
            picturesAdapter.addPicture(pictureUri.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}