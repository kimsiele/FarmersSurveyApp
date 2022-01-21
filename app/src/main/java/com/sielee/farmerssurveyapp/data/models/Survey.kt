package com.sielee.farmerssurveyapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity(tableName = "survey_table")
data class Survey(
    @PrimaryKey var id: String,
    @ColumnInfo var start_question_id:String,
    @ColumnInfo var questions:List<Question>?= null,
    @ColumnInfo var strings: Strings
)

class Convertors{
    @TypeConverter
    fun listToJson(value: List<Question>?):String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value:String):List<Question> =
        (Gson().fromJson(value,Array<Question>::class.java) as Array<Question>).toList()

   /* @TypeConverter
    fun optionListToJson(value: List<Option>?):String =
        Gson().toJson(value)

    @TypeConverter
    fun optionJsonToList(value:String):MutableList<Option> =
        (Gson().fromJson(value,Array<Option>::class.java) as Array<Option>).toMutableList()
*/
    @TypeConverter
    fun stringsToJson(value: Strings?): String = Gson().toJson(value)

    @TypeConverter
    fun stringsFromJson(value: String): Strings =
        (Gson().fromJson(value, Strings::class.java) as Strings)
}