package com.sielee.farmerssurveyapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sielee.farmerssurveyapp.data.models.Survey

@Dao
interface SurveyDao {

   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(survey: Survey)

   @Query("Select * From survey_table")
   suspend fun getSurvey(): List<Survey>

   @Query("SELECT EXISTS(SELECT * FROM SURVEY_TABLE)")
   suspend fun isExists():Boolean

}