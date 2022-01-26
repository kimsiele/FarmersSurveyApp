package com.sielee.farmerssurveyapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "survey_response_table")
data class Response (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var farmer_name:String,
    var gender:String,
    var farm_size:Double
        )