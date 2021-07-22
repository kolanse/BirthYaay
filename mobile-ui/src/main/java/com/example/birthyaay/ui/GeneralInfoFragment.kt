package com.example.birthyaay.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.birthyaay.R
import com.example.birthyaay.databinding.FragmentGeneralInfoBinding
import com.example.navigation.navigation.model.Celebrant

class GeneralInfoFragment : Fragment(R.layout.fragment_general_info) {

    private var _binding: FragmentGeneralInfoBinding? = null
    private val binding get() = _binding!!
    private var celebrant: Celebrant? = null

    companion object {
        fun instance(celebrant: Celebrant): GeneralInfoFragment {
            val generalInfoFragment = GeneralInfoFragment()
            val bundle = Bundle()
            bundle.putParcelable(
                CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY,
                celebrant
            )
            generalInfoFragment.arguments = bundle
            return generalInfoFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGeneralInfoBinding.bind(view)

        celebrant = arguments?.getParcelable<Celebrant>(
            CelebrantDetailsFragment.CELEBRANT_DETAILS_ARGS_BUNDLE_KEY
        )

        binding.apply {
            phoneNumTv.text = celebrant?.phoneNumber
            emailAddressTv.text = celebrant?.email
            dateOfBirthTv.text = celebrant?.dateOfBirth
            noteTv.text = celebrant?.note
        }

    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding  = null
    }

}