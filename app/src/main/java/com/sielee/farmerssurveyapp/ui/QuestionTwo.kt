package com.sielee.farmerssurveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sielee.farmerssurveyapp.R
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.databinding.FragmentQuestionTwoBinding
import com.sielee.farmerssurveyapp.viewmodels.MainViewModel
import com.sielee.farmerssurveyapp.viewmodels.MainViewModelFactory


class QuestionTwo : Fragment() {
    lateinit var binding: FragmentQuestionTwoBinding
    private lateinit var sharedViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionTwoBinding.inflate(inflater, container, false)
        val surveyDatabase = SurveyDatabase.getInstance(requireContext())
        val sharedViewModelFactory = MainViewModelFactory(surveyDatabase)
        sharedViewModel = ViewModelProvider(requireActivity(),sharedViewModelFactory).get(MainViewModel::class.java)

        binding.apply {
            viewModel = sharedViewModel
            questionTwoFragment = this@QuestionTwo
            lifecycleOwner = viewLifecycleOwner

        }

        return binding.root
    }

    fun goNextQuestion() {
        findNavController().navigate(R.id.action_questionTwo_to_questionThree)
    }

    fun setAnswer() {
        val selectedGender =
            binding.root.findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId)
        sharedViewModel.setQuestionTwoAnswer(selectedGender.text.toString())
    }
}