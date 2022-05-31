package com.leahhumlelu.kotlinprogram.rolling

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.leahhumlelu.kotlinprogram.R

/**
 * A simple [Fragment] subclass.
 * Use the [GameTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameTwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //Toast.makeText(this.context,"dice result:${argument.diceResult}", Toast.LENGTH_SHORT).show()
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_game_two, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.result_menu,menu)
        if(createShareIntent().resolveActivity(requireActivity().packageManager)==null){
            menu.findItem(R.id.share_btn).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share_btn ->startActivity(createShareIntent())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createShareIntent(): Intent {
        val argument = GameTwoFragmentArgs.fromBundle(requireArguments())
        return Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,argument.diceResult))
    }
}
















