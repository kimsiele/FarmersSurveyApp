package com.sielee.farmerssurveyapp.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.work.*
import com.sielee.farmerssurveyapp.R
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.models.*
import com.sielee.farmerssurveyapp.databinding.FragmentStartSurveyBinding
import com.sielee.farmerssurveyapp.viewmodels.MainViewModel
import com.sielee.farmerssurveyapp.viewmodels.MainViewModelFactory

class Home : Fragment() {

    private lateinit var binding: FragmentStartSurveyBinding
    private lateinit var sharedViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =   FragmentStartSurveyBinding.inflate(inflater,container,false)
        val surveyDatabase = SurveyDatabase.getInstance(requireContext())
        val application = Application()
        val sharedViewModelFactory = MainViewModelFactory(surveyDatabase, application)
        sharedViewModel = ViewModelProvider(requireActivity(),sharedViewModelFactory)[MainViewModel::class.java]

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            homeFragment = this@Home
        }
       sharedViewModel.startUpload()
        return binding.root
    }

    fun startSurvey() = findNavController().navigate(R.id.action_start_survey_to_questionOne)
}