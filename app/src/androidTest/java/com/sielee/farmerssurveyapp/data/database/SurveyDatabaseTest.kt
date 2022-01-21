package com.sielee.farmerssurveyapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sielee.farmerssurveyapp.data.models.*
import junit.framework.Assert.assertSame
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4ClassRunner::class)
class SurveyDatabaseTest{
private lateinit var surveyDatabase: SurveyDatabase
private lateinit var surveyDao: SurveyDao

@Before
fun createDb() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    surveyDatabase = Room.inMemoryDatabaseBuilder(
        context, SurveyDatabase::class.java).build()
    surveyDao = surveyDatabase.surveyDao
}
    @After
    @Throws(IOException::class)
    fun closeDb(){
        surveyDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadSurvey(){
        val survey = Survey(
            id="farmer_survey",
            start_question_id="q_farmer_name",
            questions= listOf(
                Question(
                    answer_type="SINGLE_LINE_TEXT",
                    id="q_farmer_name",
                    next="q_farmer_gender",
                    options= mutableListOf(),
                    question_text="q_farmer_name",
                    question_type="FREE_TEXT"),
                Question(
                    answer_type = "SINGLE_LINE_TEXT",
                    id = "q_farmer_gender",
                    next = "q_size_of_farm",
                    options = listOf(
                        Option(
                            display_text = "opt_male",
                            value = "MALE"
                        ),
                        Option(display_text = "opt_female", value = "FEMALE"),
                        Option(display_text = "opt_other", value = "OTHER")
                    ),
                    question_text = "q_farmer_gender",
                    question_type = "SELECT_ONE"
                )
            ),
            strings= Strings(en= En(
                opt_female = "Female",
                opt_male = "Male",
                opt_other = "Other",
                q_farmer_gender = "What is the gender of the farmer ?",
                q_farmer_name = "What is the name of the farmer ?",
                q_size_of_farm = "What is the size of the farm in hectares ?"
            )
            )
        )
        runBlocking {
            surveyDao.insert(survey)
            val sv =surveyDao.getSurvey()

            assertSame(sv.value?.id,(survey.id))
        }

    }



}