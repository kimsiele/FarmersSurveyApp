package com.sielee.farmerssurveyapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.models.Response
import com.sielee.farmerssurveyapp.data.models.Survey
import com.sielee.farmerssurveyapp.data.repository.SurveyRepository
import com.sielee.farmerssurveyapp.workers.UploadWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class MainViewModel(private val surveyDatabase: SurveyDatabase, private val application: Application) : ViewModel() {

    private val TAG = "MainViewModel"
    private val surveyRepository = SurveyRepository(surveyDatabase)

    private val _survey = MutableLiveData<List<Survey>>()
    val survey: LiveData<List<Survey>> = _survey

    private val _questionOneAnswer = MutableLiveData<String>()
    val questionOneAnswer: LiveData<String> = _questionOneAnswer

    private val _questionTwoAnswer = MutableLiveData<String>()
    val questionTwoAnswer: LiveData<String> = _questionTwoAnswer

    private val _questionThreeAnswer = MutableLiveData<String>()
    val questionThreeAnswer: LiveData<String> = _questionThreeAnswer

    val genders = getGenderOption()
    val questions = getQuestionsList()


    fun setQuestionOneAnswer(answer: String) {
        _questionOneAnswer.value = answer
    }

    fun setQuestionTwoAnswer(answer: String) {
        _questionTwoAnswer.value = answer
    }

    fun setQuestionThreeAnswer(answer: String) {
        _questionThreeAnswer.value = answer
    }

    private fun getGenderOption(): List<String> {
        val options = mutableListOf<String>()
        survey.observeForever {
            val male = it[0].strings.en.opt_male
            val female = it[0].strings.en.opt_female
            val other = it[0].strings.en.opt_other
            options.addAll(listOf(male, female, other))

        }
        return options
    }

    private fun getQuestionsList(): List<String> {
        val questions = mutableListOf<String>()
        survey.observeForever {
            val questionName = it[0].strings.en.q_farmer_name
            val questionGender = it[0].strings.en.q_farmer_gender
            val questionSize = it[0].strings.en.q_size_of_farm
            questions.addAll(listOf(questionName, questionGender, questionSize))
        }
        return questions

    }

    private fun resetSurvey() {
        _questionOneAnswer.value = "farmer_x"
        _questionThreeAnswer.value = "farmer_gender"
        _questionThreeAnswer.value = "0.0"
    }

    private fun refreshSurvey() {
        viewModelScope.launch {
            try {
                surveyRepository.saveSurvey()
                _survey.postValue(surveyRepository.getSurvey())
            } catch (e: Exception) {
                Log.d(TAG, "getSurvey: Error::${e.message}")
            }
        }
    }

    init {
        refreshSurvey()
        resetSurvey()
    }

    fun saveResponse(response: Response) {
        viewModelScope.launch {
            surveyDatabase.responseDao.insertResponse(response)
        }
    }

    fun startUpload() {
        val workManager = WorkManager.getInstance(application)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<UploadWorker>(
            15, TimeUnit.MINUTES
        ).setConstraints(constraints)
            .build()
        workManager.enqueue(periodicWorkRequest)
    }
}

class MainViewModelFactory(private val surveyDatabase: SurveyDatabase,private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(surveyDatabase,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}
