package com.leahhumlelu.kotlinprogram.quiz

import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentQuizBinding

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {

    private lateinit var binding:FragmentQuizBinding

   private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.i("QuizFragment","quizViewModel created in QuizFragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz,container,false)
        binding.quizViewModel=quizViewModel
        binding.lifecycleOwner=this
        quizViewModel.gameFinished.observe(this){hasFinished->
            if(hasFinished){
                findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToQuizFinishFragment(quizViewModel.score.value!!))
                quizViewModel.onGameFinishComplete()
            }
        }
        quizViewModel.eventBuzz.observe(this){buzzType->
            if(buzzType != QuizViewModel.BuzzType.NO_BUZZ){
                buzz(buzzType.pattern)
                quizViewModel.onBuzzComplete()
            }
        }
        return binding.root
    }

    private fun buzz(pattern:LongArray){
        val buzzer = activity?.getSystemService<Vibrator>()
        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                buzzer.vibrate(VibrationEffect.createWaveform(pattern,-1))
            }else{
                buzzer.vibrate(pattern,-1)
            }
        }

    }


}
























