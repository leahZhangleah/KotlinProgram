package com.leahhumlelu.kotlinprogram.realestate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentPropertyDetailBinding

class PropertyDetailFragment : Fragment() {
    private lateinit var binding:FragmentPropertyDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(layoutInflater,R.layout.fragment_property_detail,container,false)
        val args = PropertyDetailFragmentArgs.fromBundle(requireArguments())
        binding.marsProperty=args.marsProperty
        return binding.root
    }
}