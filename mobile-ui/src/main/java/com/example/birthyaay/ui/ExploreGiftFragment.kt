package com.example.birthyaay.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.birthyaay.R
import com.example.birthyaay.adapters.ExploreGiftAdapter
import com.example.birthyaay.databinding.FragmentExploreGiftBinding
import com.example.birthyaay.util.isShowOrHideView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator


class ExploreGiftFragment : Fragment(R.layout.fragment_explore_gift) {

    private var _binding: FragmentExploreGiftBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreGiftAdapter: ExploreGiftAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentExploreGiftBinding.bind(view)


        binding.apply {

            exploreGiftAdapter = ExploreGiftAdapter()

            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    findNavController().navigate(
                        R.id.peopleFragment
                    )
                }
            }

            fragmentExploreGiftInc.apply {
                exploreGiftRv.apply {
                    adapter = exploreGiftAdapter
                }

                TabLayoutMediator(
                    exploreTb, exploreGiftRv
                ){ tab, position ->
                }.attach()

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}