package com.example.birthyaay.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.birthyaay.databinding.FragmentHomeBinding
import com.google.android.material.appbar.AppBarLayout
import com.example.birthyaay.R
import com.example.birthyaay.adapters.CurrentCelebrantsAdapter
import com.example.birthyaay.adapters.UpComingCelebrantAdapter
import com.example.birthyaay.util.DataResourceGenerator
import com.example.birthyaay.util.isShowOrHideView


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.apply {

            var isShow = false
            var scrollRange = -1

            fragmentHomeInc.fragmentHomeRv.apply {
                adapter = UpComingCelebrantAdapter(
                    onItemClick = { position, upComingCelebrant ->
                        Toast.makeText(
                            requireContext(),
                            upComingCelebrant.celebrantName,
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    DataResourceGenerator.upcomingCelebrants()
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
                    DataResourceGenerator.currentCelebrants(),
                    onCallClick = {

                    },
                    onShareClick = {

                    },
                    onMessageClick = {

                    }
                )

                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 2
                setPadding(150, 0, 150, 0)

//                val marginTransformer = MarginPageTransformer(5)
//
//                setPageTransformer(marginTransformer)
            }


        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}