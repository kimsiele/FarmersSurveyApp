package com.sielee.farmerssurveyapp.data.repository

import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.network.SurveyApi


class SurveyRepository(private val surveyDatabase: SurveyDatabase) {
    val TAG = "SurveyRepository"

    suspend fun saveSurvey() {
        val networkSurvey = SurveyApi.apiService.getSurvey()
        surveyDatabase.surveyDao.insert(networkSurvey)
    }

    val survey = surveyDatabase.surveyDao.getSurvey()


}