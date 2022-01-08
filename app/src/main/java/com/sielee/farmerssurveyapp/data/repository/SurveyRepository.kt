package com.sielee.farmerssurveyapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.models.Survey
import com.sielee.farmerssurveyapp.data.network.SurveyApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SurveyRepository(private val surveyDatabase: SurveyDatabase) {
    init {
        GlobalScope.launch{
            fetchSaveSurvey()
        }

    }

    private suspend fun fetchSaveSurvey() {
        val survey = SurveyApi.apiService.getSurvey()
        Log.d("SurveyRepository", "$survey")

        surveyDatabase.surveyDao.insertSurvey(survey)

    }
    fun fetchFromDb(): LiveData<Survey> {
        return surveyDatabase.surveyDao.getSurvey()
    }
}