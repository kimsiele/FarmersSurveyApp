package com.sielee.farmerssurveyapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sielee.farmerssurveyapp.data.models.Convertors
import com.sielee.farmerssurveyapp.data.models.Survey

@Database(entities = [Survey::class],version = 2, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class SurveyDatabase: RoomDatabase() {
    abstract val surveyDao:SurveyDao

    companion object{
        @Volatile
        private var INSTANCE:SurveyDatabase? = null

        fun getInstance(context: Context):SurveyDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SurveyDatabase::class.java,
                        "survey_database"
                        ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}