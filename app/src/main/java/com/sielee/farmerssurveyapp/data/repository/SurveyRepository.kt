package com.sielee.farmerssurveyapp.data.repository

import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import com.sielee.farmerssurveyapp.data.models.Survey
import com.sielee.farmerssurveyapp.data.network.SurveyApi


class SurveyRepository(private val surveyDatabase: SurveyDatabase) {

    suspend fun saveSurvey() {
        if (!surveyDatabase.surveyDao.isExists()) {
            val networkSurvey = SurveyApi.apiService.getSurvey()
            surveyDatabase.surveyDao.insert(networkSurvey)
        }
    }

    suspend fun getSurvey(): List<Survey> {
       return surveyDatabase.surveyDao.getSurvey()
    }


}