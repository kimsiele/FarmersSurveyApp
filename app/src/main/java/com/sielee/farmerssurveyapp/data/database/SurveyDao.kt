package com.sielee.farmerssurveyapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sielee.farmerssurveyapp.data.models.Survey

@Dao
interface SurveyDao {
    @Insert
    suspend fun insertSurvey(survey: Survey)

    @Query("SELECT * FROM survey_table")
    fun getSurvey():LiveData<Survey>
}