package com.sielee.farmerssurveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sielee.farmerssurveyapp.databinding.FragmentQuestionTwoBinding


class QuestionTwo : Fragment() {
    lateinit var binding: FragmentQuestionTwoBinding
    lateinit var gender:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionTwoBinding.inflate(inflater, container, false)

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            if (group.findViewById<RadioButton>(checkedId).isChecked) {
                binding.nextQuestionThree.isEnabled = true
            }
            gender = when(group.findViewById<RadioButton>(checkedId)){
                binding.rbMale->"Male"
                binding.rbFemale ->"Female"
                else -> "Other"
        }
            val argument =requireArguments()
            binding.nextQuestionThree.setOnClickListener {
                findNavController().navigate(QuestionTwoDirections.actionQuestionTwoToQuestionThree(
                    argument.getString("farmer_name")!!,
                    gender
                ))
            }
        }

        return binding.root
    }

}