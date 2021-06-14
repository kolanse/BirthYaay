package com.example.birthyaay

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.birthyaay.databinding.FragmentHomeBinding
import com.google.android.material.appbar.AppBarLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout.Behavior.DragCallback


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.apply {

            var isShow = false
            var scrollRange = -1
            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    //showOption()
                    toolbarTitle.text = getString(R.string.good_morning_str)
                    fragmentHomeLoveToolbarIv.isVisible = true
                    fragmentHomeSearchToolbarIv.isVisible = true
                    fragmentHomeGreetingLoveIv.isVisible = false
                    fragmentHomeGreetingTitleTv.isVisible = false
                    fragmentHomeSearchEt.isVisible = false
                } else if (isShow) {
                    isShow = false
                    ///hideOption()
                    toolbarTitle.text = ""
                    fragmentHomeLoveToolbarIv.isVisible = false
                    fragmentHomeSearchToolbarIv.isVisible = false
                    fragmentHomeGreetingLoveIv.isVisible = true
                    fragmentHomeGreetingTitleTv.isVisible = true
                    fragmentHomeSearchEt.isVisible = true
                }

            })


        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}