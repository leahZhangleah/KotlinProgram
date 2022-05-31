package com.leahhumlelu.kotlinprogram.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.FragmentQuizFinishBinding

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFinishFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFinishFragment : Fragment() {
    private lateinit var binding: FragmentQuizFinishBinding
    private lateinit var quizFinishViewModelFactory: QuizFinishViewModelFactory
    private lateinit var quizFinishViewModel: QuizFinishViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_finish,container,false)
        val arguments = QuizFinishFragmentArgs.fromBundle(requireArguments())
        quizFinishViewModelFactory = QuizFinishViewModelFactory(arguments.quizResult)
        quizFinishViewModel = ViewModelProvider(this,quizFinishViewModelFactory).get(
            QuizFinishViewModel::class.java)
        binding.quizResultTxt.text = quizFinishViewModel.score.value.toString()
        return binding.root
    }
}