package com.sielee.farmerssurveyapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.models.*
import com.sielee.farmerssurveyapp.data.network.SurveyApi
import com.sielee.farmerssurveyapp.data.repository.SurveyRepository
import kotlinx.coroutines.launch


class MainViewModel(surveyDatabase: SurveyDatabase) :ViewModel() {

    private val TAG = "MainViewModel"
    private val surveyRepository = SurveyRepository(surveyDatabase)

    private val _survey = MutableLiveData<Survey>()
    val survey:LiveData<Survey> = _survey

    private val _questionOneAnswer = MutableLiveData<String>()
    val questionOneAnswer:LiveData<String> = _questionOneAnswer

    private val _questionTwoAnswer = MutableLiveData<String>()
    val questionTwoAnswer:LiveData<String> = _questionTwoAnswer

    private val _questionThreeAnswer = MutableLiveData<String>()
    val questionThreeAnswer:LiveData<String> = _questionThreeAnswer

    val genders = getGenderOption()
    val questions = getQuestionsList()

    fun setQuestionOneAnswer(answer:String){
        _questionOneAnswer.value = answer
    }

    fun setQuestionTwoAnswer(answer:String){
        _questionTwoAnswer.value = answer
    }

    fun setQuestionThreeAnswer(answer:String){
        _questionThreeAnswer.value = answer
    }

    private fun getGenderOption():List<String>{
        val options = mutableListOf<String>()
        survey.observeForever {survey->
            val male = survey.strings.en.opt_male
            val female = survey.strings.en.opt_female
            val other = survey.strings.en.opt_other
            options.addAll(listOf(male,female,other))
        }
        return options

    }

    private fun getQuestionsList():List<String>{
        val questions = mutableListOf<String>()
        survey.observeForever { survey ->
            val questionName = survey.strings.en.q_farmer_name
            val questionGender = survey.strings.en.q_farmer_gender
            val questionSize = survey.strings.en.q_size_of_farm
            questions.addAll(listOf(questionName, questionGender, questionSize))
        }
        return questions

    }
    private fun resetSurvey(){
        _questionOneAnswer.value ="farmer_x"
        _questionThreeAnswer.value = "farmer_gender"
        _questionThreeAnswer.value = "0.0"

       /* val survey = Survey(
            id="farmer_survey",
            start_question_id="q_farmer_name",
            questions= listOf(
                Question(
                answer_type="SINGLE_LINE_TEXT",
                id="q_farmer_name",
                next="q_farmer_gender",
                options= mutableListOf(),
                question_text="q_farmer_name",
                question_type="FREE_TEXT"),
                Question(
                    answer_type = "SINGLE_LINE_TEXT",
                    id = "q_farmer_gender",
                    next = "q_size_of_farm",
                    options = listOf(
                        Option(
                            display_text = "opt_male",
                            value = "MALE"
                        ),
                        Option(display_text = "opt_female", value = "FEMALE"),
                        Option(display_text = "opt_other", value = "OTHER")
                    ),
                    question_text = "q_farmer_gender",
                    question_type = "SELECT_ONE"
                )
            ),
            strings= Strings(en= En(
                opt_female = "Female",
                opt_male = "Male",
                opt_other = "Other",
                q_farmer_gender = "What is the gender of the farmer ?",
                q_farmer_name = "What is the name of the farmer ?",
                q_size_of_farm = "What is the size of the farm in hectares ?"
            )
            )
        )
            surveyDatabase.surveyDao.insert(survey)
            Log.d(TAG, "resetSurvey: ${surveyDatabase.surveyDao.getSurvey().value}")
        */
    }

    init {
            getSurvey()
            resetSurvey()
    }
    fun getSurvey() {
        viewModelScope.launch {
            try {
                surveyRepository.saveSurvey()
                _survey.postValue(surveyRepository.survey.value)
            }catch (e:Exception){
                Log.d(TAG, "getSurvey: Error::${e.message}")
            }
        }

    }
}
class MainViewModelFactory(private val surveyDatabase: SurveyDatabase) :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(surveyDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}
