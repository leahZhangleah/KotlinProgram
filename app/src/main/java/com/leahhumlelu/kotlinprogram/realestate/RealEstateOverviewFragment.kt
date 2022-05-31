package com.leahhumlelu.kotlinprogram.realestate

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentRealEstateOverviewBinding

class RealEstateOverviewFragment : Fragment() {

    private lateinit var overviewViewModel: RealEstateOverviewViewModel
    private lateinit var binding:FragmentRealEstateOverviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_real_estate_overview,container,false)
        overviewViewModel = ViewModelProvider(this).get(RealEstateOverviewViewModel::class.java)
        binding.lifecycleOwner = this
        binding.realEstateOverviewModel = overviewViewModel
        val adapter = PropertyAdapter(PropertyClickListener {
            findNavController().navigate(RealEstateOverviewFragmentDirections.actionRealEstateOverviewFragmentToPropertyDetailFragment(it))
        })
        binding.realEstateOverviewRv.adapter = adapter
        overviewViewModel.response.observe(this){
            adapter.submitList(it)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.real_estate_overview_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val type = when(item.itemId){
            R.id.menu_show_buy->PropertyFilter.SHOW_BUY
            R.id.menu_show_rent->PropertyFilter.SHOW_RENT
            else->PropertyFilter.SHOW_ALL
        }
        overviewViewModel.getMarsRealEstateProperties(type)
        return true
    }

}