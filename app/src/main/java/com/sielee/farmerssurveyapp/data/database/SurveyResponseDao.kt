package com.sielee.farmerssurveyapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sielee.farmerssurveyapp.data.models.Response

@Dao
interface SurveyResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponse(response: Response)

    @Query("SELECT * From survey_response_table")
    suspend fun getResponse():List<Response>
}