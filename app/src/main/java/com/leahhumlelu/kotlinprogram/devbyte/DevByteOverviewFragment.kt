package com.leahhumlelu.kotlinprogram.devbyte

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentDevByteOverviewBinding

class DevByteOverviewFragment : Fragment() {

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onViewCreated(), which we
     * do in this Fragment.
     */
    private val viewModel: DevByteOverviewViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewmodel after onViewCreated()"
        }
        ViewModelProvider(this,DevByteViewModelFactory(activity.application)).get(DevByteOverviewViewModel::class.java)
    }

    private lateinit var devByteAdapter:DevByteAdapter
    private lateinit var binding:FragmentDevByteOverviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dev_byte_overview,container,false)
        binding.lifecycleOwner=this
        devByteAdapter = DevByteAdapter()
        binding.devByteOverviewRv.adapter = devByteAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.devByteViewModel=viewModel
        viewModel.playlist.observe(this){
            devByteAdapter.submitList(it)
        }
    }




}