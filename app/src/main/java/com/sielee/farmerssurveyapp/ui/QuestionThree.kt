package com.sielee.farmerssurveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.sielee.farmerssurveyapp.R
import com.sielee.farmerssurveyapp.databinding.FragmentQuestionOneBinding
import com.sielee.farmerssurveyapp.databinding.FragmentQuestionThreeBinding

class QuestionThree : Fragment() {

    lateinit var binding: FragmentQuestionThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionThreeBinding.inflate(inflater,container, false)

        binding.edFarmSize.addTextChangedListener { characters ->
            if (characters?.count()!! >0){
                binding.btnFinish.isEnabled = true
            }

        }
        val argument = requireArguments()
        binding.btnFinish.setOnClickListener {
            Toast.makeText(context," ${argument.getString("farmer_name")!!}\n  ${argument.getString("gender")!!}\n${binding.edFarmSize.text.toString().toInt()}",Toast.LENGTH_LONG).show()
            findNavController().navigate(QuestionThreeDirections.actionQuestionThreeToHome2(
                argument.getString("farmer_name")!!,
                argument.getString("gender")!!,
                binding.edFarmSize.text.toString().toInt()
            ))
        }
        return binding.root
    }

}