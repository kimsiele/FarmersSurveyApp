package com.sielee.farmerssurveyapp.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sielee.farmerssurveyapp.R
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.databinding.FragmentQuestionOneBinding
import com.sielee.farmerssurveyapp.viewmodels.MainViewModel
import com.sielee.farmerssurveyapp.viewmodels.MainViewModelFactory

class QuestionOne : Fragment() {

    lateinit var binding: FragmentQuestionOneBinding
    private lateinit var sharedViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionOneBinding.inflate(inflater,container, false)

        val surveyDatabase = SurveyDatabase.getInstance(requireContext())
        val application = Application()
        val sharedViewModelFactory = MainViewModelFactory(surveyDatabase,application)
        sharedViewModel = ViewModelProvider(requireActivity(),sharedViewModelFactory).get(MainViewModel::class.java)

        binding.apply {
            viewModel = sharedViewModel
            questionOneFragment = this@QuestionOne
            lifecycleOwner = viewLifecycleOwner
        }

        binding.edFarmerName.addTextChangedListener { characters ->
            binding.nextQuestionTwo.isEnabled = (characters?.count()!! >3)
        }
        return binding.root
    }

    fun goNextQuestion() {
        sharedViewModel.setQuestionOneAnswer(binding.edFarmerName.text.toString())
        findNavController().navigate(R.id.action_questionOne_to_questionTwo)
    }
}