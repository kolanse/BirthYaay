package com.example.birthyaay.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.birthyaay.R
import com.example.birthyaay.databinding.ContentLayoutBinding
import com.example.navigation.navigation.model.ContentType


class RemoveContentDialogFragment : DialogFragment(R.layout.content_layout) {

    private var _binding: ContentLayoutBinding? = null
    private val binding get() = _binding!!
    var viewType: ContentType? = null
    private var width: Int = 0
    private var height: Int = 0

    companion object {
        fun newInstance() =
            RemoveContentDialogFragment()

        const val REMOVE_CONTENT_DIALOG_TAG = "ADD_CONTENT_DIALOG_FRAGMENT"
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
                ContentType.GIFT -> {
                    binding.apply {
                        removeContentTv.text = getString(R.string.remove_gift_str)
                        removeBtn.text = getString(R.string.remove_gift_str)
                        removeContentMessageTv.text = getString(R.string.remove_message_str, getString(
                                                    R.string.a_gift))
                    }
                }
                ContentType.INTEREST -> {

                }
                ContentType.PICTURE -> {
                    binding.apply {
                        removeContentTv.text = getString(R.string.remove_picture)
                        removeBtn.text = getString(R.string.remove_picture)
                        removeContentMessageTv.text = getString(R.string.remove_message_str, getString(
                                                    R.string.a_picture))
                    }
                }
                ContentType.NEUTRAL -> {

                }
            }

            closeIv.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDialogDimens() {

        width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        height = (resources.displayMetrics.heightPixels * 0.4).toInt()

        dialog!!.window?.setLayout(width, height)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
    }


}