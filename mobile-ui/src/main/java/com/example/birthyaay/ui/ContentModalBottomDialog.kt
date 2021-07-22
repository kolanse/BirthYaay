package com.example.birthyaay.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.birthyaay.R
import com.example.birthyaay.adapters.GiftsOrInterestsAdapter
import com.example.birthyaay.adapters.utils.AdapterItemHelper
import com.example.birthyaay.databinding.ModalBottomDialogSheetBinding
import com.example.birthyaay.util.DataResourceGenerator
import com.example.navigation.navigation.model.Content
import com.example.navigation.navigation.model.ContentType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ContentModalBottomDialog : BottomSheetDialogFragment() {

    private var _binding: ModalBottomDialogSheetBinding? = null
    private val binding get() = _binding!!
    private var selectedContents: Array<Content>? = null
    private var contentType: String? = null

    companion object {

        const val CONTENT_MODAL_BOTTOM_DIALOG_TAG = "ContentModalBottomDialog"
        const val CONTENT_TYPE = "CONTENT_TYPE"
        const val CONTENT_INTEREST = "INTEREST"
        const val CONTENT_GIFT = "GIFT"

        fun newInstance(content: Array<Content>, contentType: String): ContentModalBottomDialog {

            val bundle = Bundle()
            val contentModalBottomDialog = ContentModalBottomDialog()
            bundle.putParcelableArray(
                CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY,
                content
            )
            bundle.putString(CONTENT_TYPE, contentType)
            contentModalBottomDialog.arguments = bundle
            return contentModalBottomDialog
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ModalBottomDialogSheetBinding.inflate(layoutInflater)

        selectedContents = arguments?.getParcelableArray(
            CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY
        ) as Array<Content>?

        contentType = arguments?.getString(CONTENT_TYPE)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            var allCelebrantContentsOptions = mutableListOf<Content>()

            if (selectedContents != null) {

                if (selectedContents?.size!! > 0) {
                    val allContents =
                        if (contentType == CONTENT_INTEREST) {
                            DataResourceGenerator.provideInterests()
                        } else {
                            DataResourceGenerator.provideGifts()
                        }

                    if (allContents.size > 0 && selectedContents?.size!! > 0) {
                        val contentSet = hashSetOf<String>()

                        for (selectedContent in selectedContents!!) {
                            contentSet.add(selectedContent.title.trim())
                        }

                        for (allContent in allContents) {
                            if (!contentSet.contains(allContent.title)) {
                                allCelebrantContentsOptions.add(allContent)
                            }
                        }

                    }
                } else {
                    allCelebrantContentsOptions = if (contentType == CONTENT_INTEREST) {
                        DataResourceGenerator.provideInterests()
                    } else {
                        DataResourceGenerator.provideGifts()
                    }
                }
            }


            //content?.forEach { contents.add(it) }

            val contentAdapter = GiftsOrInterestsAdapter(
                onItemClick = { content, position ->

                    val notSuggestedList = DataResourceGenerator.notSuggestedList

                    if (content.title == getString(
                            R.string.not_suggested_str
                        ) &&
                        !notSuggestedList.contains(
                            getString(R.string.not_suggested_str)
                        )
                    ) {
                        notSuggestedList.add(content.title)
                    } else {
                        notSuggestedList.remove(content.title)
                    }

                    addContentRoot.isVisible = notSuggestedList.isNotEmpty()

                },
                contents = allCelebrantContentsOptions
            )
            contentRv.apply {
                adapter = contentAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            closeIv.setOnClickListener {
                dismiss()
            }
        }

    }

}