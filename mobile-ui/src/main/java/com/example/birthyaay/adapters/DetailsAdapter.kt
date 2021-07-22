package com.example.birthyaay.adapters

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.birthyaay.ui.ExtraInfoFragment
import com.example.birthyaay.ui.GeneralInfoFragment
import com.example.navigation.navigation.model.Celebrant


class DetailsAdapter(
    fragmentActivity: FragmentActivity,
    private val argument: Celebrant
): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GeneralInfoFragment.instance(argument)
            else -> ExtraInfoFragment.instance(argument)
        }
    }

}