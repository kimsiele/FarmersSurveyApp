package com.sielee.farmerssurveyapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sielee.farmerssurveyapp.data.models.Survey

@Dao
interface SurveyDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(survey: Survey)

   @Query("Select * From survey_table")
   fun getSurvey():LiveData<Survey>
}