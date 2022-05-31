package com.leahhumlelu.kotlinprogram

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.leahhumlelu.kotlinprogram.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {
    private lateinit var titleBinding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        titleBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_title,container,false)
        setHasOptionsMenu(true)
        titleBinding.playBtn.setOnClickListener {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToRollingFragment())
        }
        titleBinding.quizBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_quizFragment)
        }
        titleBinding.recordSleepBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_sleepTrackerFragment)
        }
        titleBinding.realEstateBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_realEstateOverviewFragment)
        }
        titleBinding.devByteBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_devByteOverviewFragment)
        }
        return titleBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        return item.onNavDestinationSelected(navController)||super.onOptionsItemSelected(item)
    }
}