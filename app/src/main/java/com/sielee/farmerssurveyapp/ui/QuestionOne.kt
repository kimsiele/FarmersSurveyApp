package com.sielee.farmerssurveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.sielee.farmerssurveyapp.R
import com.sielee.farmerssurveyapp.databinding.FragmentQuestionOneBinding

class QuestionOne : Fragment() {

    lateinit var binding: FragmentQuestionOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionOneBinding.inflate(inflater,container, false)

        binding.edFarmerName.addTextChangedListener { characters ->
            if (characters?.count()!! >0){
                binding.nextQuestionTwo.isEnabled = true
            }

        }
        binding.nextQuestionTwo.setOnClickListener {
            findNavController().navigate(QuestionOneDirections.actionQuestionOneToQuestionTwo(binding.edFarmerName.text.toString()))
        }

        return binding.root
    }

}