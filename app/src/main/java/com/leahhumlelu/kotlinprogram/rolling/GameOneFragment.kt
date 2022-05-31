package com.leahhumlelu.kotlinprogram.rolling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.leahhumlelu.kotlinprogram.R

/**
 * A simple [Fragment] subclass.
 * Use the [GameOneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameOneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val argument = GameOneFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(this.context,"dice result:${argument.diceResult}",Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_game_one, container, false)
    }

}