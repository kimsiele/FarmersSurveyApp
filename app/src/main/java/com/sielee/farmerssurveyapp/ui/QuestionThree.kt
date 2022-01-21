package com.sielee.farmerssurveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
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
        val sharedViewModelFactory = MainViewModelFactory(surveyDatabase)
        sharedViewModel = ViewModelProvider(requireActivity(),sharedViewModelFactory).get(MainViewModel::class.java)

        binding.apply {
            viewModel = sharedViewModel
            questionThreeFragment = this@QuestionThree
            lifecycleOwner = viewLifecycleOwner

            edFarmSize.addTextChangedListener { characters ->
                binding.btnFinish.isEnabled = characters?.count()!! >0
            }

            btnFinish.setOnClickListener {
                finishSurvey(
                    sharedViewModel.questionOneAnswer.value.toString(),
                    sharedViewModel.questionTwoAnswer.value.toString(),
                    sharedViewModel.questionThreeAnswer.value!!.toDouble()
                    )
            }
        }


        return binding.root
    }


    fun finishSurvey(name:String, gender:String, size:Double){
        sharedViewModel.setQuestionThreeAnswer(binding.edFarmSize.text.toString())
        Toast.makeText( requireContext(),"Farmer name: $name\n Gender: $gender\n Farm size: $size",Toast.LENGTH_LONG).show()

    }


}