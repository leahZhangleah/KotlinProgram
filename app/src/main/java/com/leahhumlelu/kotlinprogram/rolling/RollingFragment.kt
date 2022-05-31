package com.leahhumlelu.kotlinprogram.rolling

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentRollingBinding

/**
 * A simple [Fragment] subclass.
 * Use the [RollingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RollingFragment : Fragment() {
    private lateinit var fragmentRollingBinding: FragmentRollingBinding
    private lateinit var rolling: Rolling
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentRollingBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_rolling,container,false)
        rolling= Rolling(1, R.drawable.empty_dice)
        fragmentRollingBinding.rolling=rolling
        fragmentRollingBinding.rollBtn.setOnClickListener {
            rolling.rollDice()
            //fragmentRollingBinding.invalidateAll()
            if(rolling.nextInt % 2 ==0){
                it.findNavController().navigate(RollingFragmentDirections.actionRollingFragmentToGameTwoFragment(rolling.nextInt,rolling.imgResourceId))
            }else{
                it.findNavController().navigate(RollingFragmentDirections.actionRollingFragmentToGameOneFragment(rolling.nextInt,rolling.imgResourceId))
            }
        }
        return fragmentRollingBinding.root
    }


}


















