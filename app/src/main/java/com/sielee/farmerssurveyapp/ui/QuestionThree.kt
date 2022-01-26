package com.sielee.farmerssurveyapp.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sielee.farmerssurveyapp.R
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.models.Response
import com.sielee.farmerssurveyapp.databinding.FragmentQuestionThreeBinding
import com.sielee.farmerssurveyapp.viewmodels.MainViewModel
import com.sielee.farmerssurveyapp.viewmodels.MainViewModelFactory

class QuestionThree : Fragment() {

    lateinit var binding: FragmentQuestionThreeBinding

    private lateinit var sharedViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionThreeBinding.inflate(inflater,container, false)
        val surveyDatabase = SurveyDatabase.getInstance(requireContext())
        val application = Application()
        val sharedViewModelFactory = MainViewModelFactory(surveyDatabase, application)
        sharedViewModel = ViewModelProvider(requireActivity(),sharedViewModelFactory).get(MainViewModel::class.java)

        binding.apply {
            viewModel = sharedViewModel
            questionThreeFragment = this@QuestionThree
            lifecycleOwner = viewLifecycleOwner

            edFarmSize.addTextChangedListener { characters ->
                binding.btnFinish.isEnabled = characters?.count()!! >0
                sharedViewModel.setQuestionThreeAnswer(characters.toString())
            }

            btnFinish.setOnClickListener {
                val response=Response(
                    farmer_name = sharedViewModel.questionOneAnswer.value.toString(),
                    gender = sharedViewModel.questionTwoAnswer.value.toString(),
                    farm_size = sharedViewModel.questionThreeAnswer.value!!.toDouble())
                sharedViewModel.saveResponse(response)
                finishSurvey()
                findNavController().navigate(R.id.action_questionThree_to_home2)

            }
        }


        return binding.root
    }


    fun finishSurvey(){
        Toast.makeText( requireContext(),"Response saved successfully",Toast.LENGTH_LONG).show()

    }


}