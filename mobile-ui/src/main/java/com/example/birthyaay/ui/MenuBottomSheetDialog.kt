package com.example.birthyaay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.birthyaay.databinding.MenuBottomSheetBinding
import com.example.navigation.navigation.model.ContentType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: MenuBottomSheetBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val CONTENT_TYPE_KEY = "content_type_key"
        const val CONTENT_TYPE_GIFT = "GIFT"
        const val CONTENT_TYPE_PICTURE = "PICTURE"
        const val TAG = "MenuBottomSheetDialogTag"
        fun newInstance(contentType: String): MenuBottomSheetDialog {
            val bundle = Bundle()
            val menuBottomSheetDialog = MenuBottomSheetDialog()
            bundle.putString(CONTENT_TYPE_KEY, contentType)
            menuBottomSheetDialog.arguments = bundle

            return menuBottomSheetDialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MenuBottomSheetBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contentType = arguments?.getString(CONTENT_TYPE_KEY)

        binding.apply {
            deleteTv.setOnClickListener {

                if (contentType == CONTENT_TYPE_GIFT) {
                    val removeContentDialogFragment = RemoveContentDialogFragment.newInstance()
                    removeContentDialogFragment.viewType = ContentType.GIFT
                    removeContentDialogFragment.show(
                        parentFragmentManager.beginTransaction(),
                        RemoveContentDialogFragment.REMOVE_CONTENT_DIALOG_TAG
                    )
                } else if (contentType == CONTENT_TYPE_PICTURE) {
                    val removeContentDialogFragment = RemoveContentDialogFragment.newInstance()
                    removeContentDialogFragment.viewType = ContentType.PICTURE
                    removeContentDialogFragment.show(
                        parentFragmentManager.beginTransaction(),
                        RemoveContentDialogFragment.REMOVE_CONTENT_DIALOG_TAG
                    )
                }

                dismiss()
            }
        }

    }

}