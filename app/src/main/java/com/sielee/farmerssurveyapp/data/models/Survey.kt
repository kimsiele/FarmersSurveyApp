package com.sielee.farmerssurveyapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "survey_table")
data class Survey(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    @ColumnInfo(name = "questions_response")
    var questions: List<Question>,
    @ColumnInfo(name = "start_question_id")
    var start_question_id: String,
    @ColumnInfo(name = "strings")
    var strings: Strings
)

class Convertors{
    @TypeConverter
    fun listToJson(value: MutableList<Question>?):String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value:String):MutableList<Question> =
        (Gson().fromJson(value,Array<Question>::class.java) as Array<Question>).toMutableList()

    @TypeConverter
    fun optionListToJson(value: MutableList<Option>?):String =
        Gson().toJson(value)

    @TypeConverter
    fun optionJsonToList(value:String):MutableList<Option> =
        (Gson().fromJson(value,Array<Option>::class.java) as Array<Option>).toMutableList()
    @TypeConverter
    fun stringsToJson(value: Strings?): String = Gson().toJson(value)

    @TypeConverter
    fun stringsFromJson(value: String): Strings =
        (Gson().fromJson(value, Strings::class.java) as Strings)
}