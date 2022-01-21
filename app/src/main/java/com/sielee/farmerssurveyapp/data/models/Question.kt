package com.sielee.farmerssurveyapp.data.models

data class Question(
    var answer_type: String="",
    var id: String ="",
    var next: String ="",
    var options:List<Option>?,
    var question_text: String ="",
    var question_type: String =""
)

data class Option(
    val display_text: String,
    val value: String
)
