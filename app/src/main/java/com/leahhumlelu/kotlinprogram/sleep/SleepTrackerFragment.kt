package com.leahhumlelu.kotlinprogram.sleep

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentSleepTrackerBinding

class SleepTrackerFragment : Fragment() {
    private lateinit var viewModel: SleepTrackerViewModel
    private lateinit var binding: FragmentSleepTrackerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_tracker,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val sleepTrackerViewModelFactory = SleepTrackerViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this,sleepTrackerViewModelFactory).get(SleepTrackerViewModel::class.java)
        binding.lifecycleOwner = this
        binding.sleepTrackerViewModel = viewModel
        val adapter = SleepDataAdapter(SleepNightClickListener {nightId->
            Toast.makeText(context,"$nightId clicked",Toast.LENGTH_SHORT).show()
        })
        binding.sleepDataRv.adapter = adapter
        val gridLayoutManager = GridLayoutManager(activity,3)
        gridLayoutManager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int) = when(position){
                0->3
                else->1
            }
        }
        binding.sleepDataRv.layoutManager = gridLayoutManager
        viewModel.nights.observe(this){
            adapter.addHeaderAndSubmitList(it)
        }
        viewModel.navigateToSleepQuality.observe(this){
            it?.let{
                findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(it.nightId))
                viewModel.doneNavigating()
            }

        }
        viewModel.showSnackBarEvent.observe(this){
            if(it){
                Snackbar.make(activity!!.findViewById(android.R.id.content),R.string.cleared_message,Snackbar.LENGTH_SHORT).show()
                viewModel.doneShowingSnackbar()
            }
        }
        return binding.root
    }


}