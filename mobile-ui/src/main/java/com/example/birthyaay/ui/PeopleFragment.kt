package com.example.birthyaay.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.birthyaay.R
import com.example.birthyaay.adapters.UpComingCelebrantAdapter
import com.example.birthyaay.adapters.utils.AdapterItemHelper
import com.example.birthyaay.databinding.FragmentPeopleBinding
import com.example.birthyaay.util.isShowOrHideView
import com.example.navigation.navigation.model.Celebrant
import com.google.android.material.appbar.AppBarLayout


class PeopleFragment() : Fragment(R.layout.fragment_people) {

    private var binding : FragmentPeopleBinding? = null
    private val currentBinding get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPeopleBinding.bind(view)


        currentBinding.apply {

            var isShow = false
            var scrollRange = -1

            fragmentPeopleToolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                   findNavController().navigate(R.id.homeFragment)
                }
            }

            fragmentPeopleFab.setOnClickListener {
                findNavController().navigate(R.id.addCelebrantFragment)
            }

            fragmentPeopleInc.fragmentPeopleRv.apply {

                adapter = UpComingCelebrantAdapter(
                    onItemClick = { position, upComingCelebrant ->

                        val actions = PeopleFragmentDirections.
                        actionPeopleFragmentToCelebrantDetailsFragment(upComingCelebrant)

                        findNavController().navigate(actions)
                    },
                    AdapterItemHelper.upComingCelebrantsWithSortedGroupAlphabets
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
                    toolbarTitle.text = getString(R.string.celebrants_str)
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
        binding = null
    }

}