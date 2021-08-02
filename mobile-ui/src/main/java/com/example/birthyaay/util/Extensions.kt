package com.example.birthyaay.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.birthyaay.R
import com.example.birthyaay.adapters.GiftsOrInterestsAdapter
import com.example.birthyaay.databinding.*
import com.example.navigation.navigation.model.Content
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.OutputStream
import com.example.navigation.navigation.model.ContentType


fun Fragment.showSnackWithMessage(message: String, duration: Int) {
    Snackbar.make(requireView(), message, duration).show()
}

fun TextInputEditText.addTextColor(context: Context, color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}

fun Fragment.hideBottomNavigationView() {
    requireActivity().findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        .isVisible = false
}

fun TextView.setCompoundDrawableWithSpecificBounds(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    setCompoundDrawablesWithIntrinsicBounds(
        left,
        top,
        right,
        bottom
    )
}

fun Fragment.pickAndSetDate(textView: TextView) {
    val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
    builder.setTitleText(getString(R.string.select_a_date))

    val picker: MaterialDatePicker<*> = builder.build()

    textView.setOnClickListener {

        try {
            picker.show(parentFragmentManager, picker.toString())
        } catch (e: Exception) {
            Log.i("VIEW_TAG", e.message.toString())
        }
    }

    picker.addOnPositiveButtonClickListener {
        textView.text = picker.headerText
    }
}

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
        is FragmentCelebrantDetailsBinding -> {
            binding.apply {
                fragmentCelebrantDetailsIv.isVisible = isShow
                fragmentCelebrantDetailsTitleTv.isVisible = isShow
                //fragmentAddCelebrantToolbar.isVisible = !isShow
                fragmentCelebrantDetailsToolbarIv.isVisible = !isShow
            }
        }

        is FragmentExploreGiftBinding -> {
            binding.apply {
                //fragmentExploreGiftsIv.isVisible = isShow
                //fragmentExploreGiftsTitleTv.isVisible = isShow
                //fragmentAddCelebrantToolbar.isVisible = !isShow
                fragmentHomeLoveToolbarIv.isVisible = !isShow
            }
        }
    }
}

fun Fragment.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_right).setPopExitAnim(R.anim.slide_out_left)
    return navBuilder
}

fun Activity.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_right).setPopExitAnim(R.anim.slide_out_left)
    return navBuilder
}

fun BottomNavigationView.checkMenuItem(destinationId: Int) {
    for (i in 0 until menu.size()) {
        val item: MenuItem = menu.getItem(i)
        item.isChecked = false
    }
    menu.findItem(destinationId)?.isChecked = true
}


fun Fragment.checkForPermission(
    name: String, requestCode: Int,
    getContent: ActivityResultLauncher<String>
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {

                getContent.launch("image/*")
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) ->
                showRequestPermissionRationaleDialog(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    name,
                    requestCode
                )
            else -> ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                requestCode
            )
        }
    }
}



fun Fragment.checkForPermission(
    name: String, requestCode: Int,
    action: () -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                action.invoke()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) ->
                showRequestPermissionRationaleDialog(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    name,
                    requestCode
                )
            else -> ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE),
                requestCode
            )
        }
    }
}




fun Fragment.checkForMessagePermission(
    name: String, requestCode: Int,
    phoneNum: String, message: String
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED -> {
                sendMessage(phoneNum, message)
            }

            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) ->
                showRequestPermissionRationaleDialog(
                    Manifest.permission.SEND_SMS,
                    name,
                    requestCode
                )

            else -> ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.SEND_SMS),
                requestCode
            )
        }

    }
}



// Show dialog for permission dialog
fun Fragment.showRequestPermissionRationaleDialog(
    permission: String,
    name: String,
    requestCode: Int
) {
    // Alert dialog box
    val builder = AlertDialog.Builder(requireContext())
    builder.apply {
        // setting alert properties
        setMessage(getString(R.string.permission_to_access, name))
        setTitle(getString(R.string.permission_required_title))
        setPositiveButton(getString(R.string.okay_str)) { _, _ ->
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(permission),
                requestCode
            )
        }
    }
    val dialog = builder.create()
    dialog.show()
}


/**
 * This method helps to create a dropdown for TextInputLayout: [interests, gifts]
 * */


fun Fragment.showPopUpWindow(
    textView: TextView,
    contents: MutableList<Content>,
    contentList: MutableList<String>,
    contentNotSuggestedList: MutableList<String>,
    addedSuggestedContentList: MutableList<String>,
    contentType: ContentType
): PopupWindow {

    val inflater =
        requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    val view = inflater.inflate(R.layout.content_pop_up_window, null)

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

            textView.text = interests

            when (contentType) {
                ContentType.INTEREST -> {
                    textView.text = if (interests.trim().isNotEmpty()) {
                        interests
                    } else {
                        getString(R.string.label_interests_str)
                    }
                }
                else -> {
                    textView.text = if (interests.trim().isNotEmpty()) {
                        interests
                    } else {
                        getString(R.string.label_choose_gift_categories_str)
                    }
                }
            }


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
                Content(
                    suggestedContent,
                    false,
                    ContentType.INTEREST,
                    R.drawable.ic_interest
                )
            } else {
                Content(
                    suggestedContent,
                    false,
                    ContentType.GIFT,
                    R.drawable.ic_gift_boxes,
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


    val width = textView.width

    return PopupWindow(
        view,
        width,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

fun Fragment.makeCall(phoneNum: String) {
    Intent(Intent.ACTION_DIAL).apply {
        this.data = Uri.parse("tel:$phoneNum")
        startActivity(this)
    }
}

fun Fragment.sendMessage(phoneNum: String, message: String) {
    Intent(Intent.ACTION_VIEW).apply {
        this.data = Uri.parse("sms:")
        putExtra("address", phoneNum)
        putExtra("sms_body", message)
        startActivity(this)
    }
}

fun Fragment.shareImage(imageUri: Uri){

    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, imageUri))
    } else {
        MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
    }

    val values = ContentValues()
    values.put(MediaStore.Images.Media.TITLE, "title")
    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

    val share = Intent(Intent.ACTION_SEND)
    share.type = "image/jpeg"

    val uri = requireActivity().contentResolver
        .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!

    var outputStream: OutputStream? = null

    try {
        outputStream = requireActivity().contentResolver.openOutputStream(uri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream?.close()
    } catch (e: Exception) {
        Log.d("IMAGE_EXCEPTION", e.toString())
    }

    share.putExtra(Intent.EXTRA_STREAM, uri)
    startActivity(Intent.createChooser(share, "Share Image"))

}