package com.example.birthyaay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.birthyaay.R
import com.example.birthyaay.databinding.FragmentAddCelebrantBinding
import com.example.birthyaay.util.isShowOrHideView
import com.google.android.material.appbar.AppBarLayout

class AddCelebrantFragment : Fragment(R.layout.fragment_add_celebrant) {
    private var _binding: FragmentAddCelebrantBinding? = null
    private val binding get() = _binding!!

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

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}