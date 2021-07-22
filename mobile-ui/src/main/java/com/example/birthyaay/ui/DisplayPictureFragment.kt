package com.example.birthyaay.ui

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.birthyaay.R
import com.example.birthyaay.databinding.FragmentDisplayPictureBinding
import com.example.birthyaay.util.*
import com.example.navigation.navigation.model.Celebrant
import com.google.android.material.snackbar.Snackbar


class DisplayPictureFragment : Fragment() {

    private var _binding: FragmentDisplayPictureBinding? = null
    private val binding get() = _binding!!
    private val args: DisplayPictureFragmentArgs by navArgs()
    private var imageUrl: String? = null
    private var pictureUri: Uri? = null
    private var celebrant: Celebrant? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Handle the returned Uri
            if (uri != null) {
                pictureUri = uri
            }

        }

    companion object {
        const val PICK_IMAGE_REQUEST_CODE = 100
        const val SHARE_IMAGE_REQUEST_CODE = 1000

        fun instance(celebrant: Celebrant): DisplayPictureFragment {
            val bundle = Bundle()
            val displayPictureFragment = DisplayPictureFragment()
            bundle.putParcelable(
                CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY,
                celebrant
            )
            displayPictureFragment.arguments = bundle
            return displayPictureFragment
        }

        fun saveArgument(celebrant: Celebrant) {
            val bundle = Bundle()
            val displayPictureFragment = DisplayPictureFragment()
            bundle.putParcelable(
                CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY,
                celebrant
            )
            displayPictureFragment.arguments = bundle
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisplayPictureBinding.inflate(inflater)

        hideBottomNavigationView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        celebrant = args.celebrant

        imageUrl = celebrant?.image?.first()


        binding.apply {

            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)

            toolbar.setNavigationOnClickListener {

                val action =
                    DisplayPictureFragmentDirections.actionDisplayPictureFragmentToCelebrantDetailsFragment(
                        celebrant
                    )
                findNavController().navigate(action)
            }

            profileTitleTv.text = if (celebrant?.name.isNullOrEmpty()) {
                getString(R.string.profile_picture_str)
            } else {
                celebrant?.name
            }


            if (imageUrl != "null") {
                profilePictureIv.load(imageUrl) {
                    crossfade(false)
                    placeholder(R.drawable.user_avatar)
                }
            } else {
                profilePictureIv.load(R.drawable.user_avatar)
            }


//            profilePictureIv.setImageURI(uri?.toUri())

            profileMenuIv.setOnClickListener {
                val popUpMenu = PopupMenu(requireContext(), profileMenuIv)

                popUpMenu.inflate(R.menu.profile_image_menu)

                popUpMenu.setOnMenuItemClickListener { menuItem ->

                    when (menuItem.itemId) {
                        R.id.edit -> {
                            REQUEST_CODE = PICK_IMAGE_REQUEST_CODE
                            checkForPermission(
                                PERMISSION_NAME, READ_STORAGE_REQUEST_CODE,
                                action = {
                                    getContent.launch("image/*")
                                }
                            )
                        }
                        R.id.delete -> {
                            Toast.makeText(requireContext(), "Clicked Delete", Toast.LENGTH_SHORT)
                                .show()
                        }
                        R.id.share -> {
                            if (imageUrl != "null") {
                                REQUEST_CODE = SHARE_IMAGE_REQUEST_CODE
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
                    }

                    true
                }

                popUpMenu.show()
            }

        }
    }

    override fun onResume() {
        super.onResume()

        if (pictureUri != null) {
            imageUrl = pictureUri.toString()
            celebrant = celebrant?.copy(image = listOf(imageUrl!!))
        }

        binding.apply {
            if (imageUrl != "null") {
                profilePictureIv.load(imageUrl) {
                    crossfade(false)
                    placeholder(R.drawable.user_avatar)
                }
            } else {
                profilePictureIv.load(R.drawable.user_avatar)
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

                    if (imageUrl != "null" && REQUEST_CODE == SHARE_IMAGE_REQUEST_CODE) {

                        imageUrl?.let {
                            shareImage(it.toUri())
                        }

                    } else if (REQUEST_CODE == PICK_IMAGE_REQUEST_CODE) {

                        getContent.launch("image/*")

                    } else if (imageUrl == "null" && REQUEST_CODE == SHARE_IMAGE_REQUEST_CODE) {

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