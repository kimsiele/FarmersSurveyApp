package com.sielee.farmerssurveyapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.repository.SurveyRepository
import kotlinx.coroutines.DelicateCoroutinesApi
@DelicateCoroutinesApi
class MainViewModel(surveyDatabase: SurveyDatabase):ViewModel() {

    private val surveyRepository = SurveyRepository(surveyDatabase)
    val survey = surveyRepository.fetchFromDb()
}
@DelicateCoroutinesApi
class MainViewModelFactory(private val surveyDatabase: SurveyDatabase):ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(surveyDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}
