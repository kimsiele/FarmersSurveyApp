package com.sielee.farmerssurveyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sielee.farmerssurveyapp.R
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.databinding.FragmentHomeBinding
import com.sielee.farmerssurveyapp.viewmodels.MainViewModel
import com.sielee.farmerssurveyapp.viewmodels.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
@DelicateCoroutinesApi
class Home : Fragment() {

    val TAG = "Home"
    lateinit var binding: FragmentHomeBinding

    lateinit var viewModel: MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =   FragmentHomeBinding.inflate(inflater,container,false)
        binding.btnTakeSurvey.setOnClickListener {
            findNavController().navigate(HomeDirections.actionHome2ToQuestionOne())
        }
        val database = SurveyDatabase.getInstance(requireContext())
        viewModelFactory = MainViewModelFactory(database)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        viewModel.survey.observe(viewLifecycleOwner,{
            Log.d(TAG, it.id)
        })
        return binding.root
    }

}