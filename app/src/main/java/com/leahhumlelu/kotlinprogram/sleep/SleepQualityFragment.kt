package com.leahhumlelu.kotlinprogram.sleep

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentSleepQualityBinding

class SleepQualityFragment : Fragment() {
    private lateinit var viewModel: SleepQualityViewModel
    private lateinit var binding:FragmentSleepQualityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_quality,container,false)
        val application = requireNotNull(activity?.application)
        val args = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val nightKey = args.sleepNightKey
        val sleepDao = SleepDatabase.getInstance(application).sleepDatabaseDao
        val factory = SleepQualityViewModelFactory(nightKey,sleepDao)
        viewModel = ViewModelProvider(this,factory).get(SleepQualityViewModel::class.java)
        binding.lifecycleOwner=this
        binding.sleepQualityViewModel=viewModel
        viewModel.navigateToSleepTracker.observe(this){
            if(it==true){
                findNavController().navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                viewModel.doneNavigating()
            }
        }
        return binding.root
    }

}